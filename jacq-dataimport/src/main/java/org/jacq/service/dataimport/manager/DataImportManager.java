/*
 * Copyright 2016 wkoller.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jacq.service.dataimport.manager;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.jacq.common.model.dataimport.ImportFile;
import org.jacq.common.model.dataimport.ImportRecord;
import org.jacq.common.model.jpa.TblAcquisitionDate;
import org.jacq.common.model.jpa.TblAcquisitionEvent;
import org.jacq.common.model.jpa.TblAcquisitionType;
import org.jacq.common.model.jpa.TblAlternativeAccessionNumber;
import org.jacq.common.model.jpa.TblBotanicalObject;
import org.jacq.common.model.jpa.TblLivingPlant;
import org.jacq.common.model.jpa.TblLocationCoordinates;
import org.jacq.common.model.jpa.TblOrganisation;
import org.jacq.common.model.jpa.TblSeparation;
import org.jacq.common.model.jpa.TblSeparationType;
import org.jacq.common.model.names.JsonRpcRequest;
import org.jacq.common.model.names.TaxamatchOptions;
import org.jacq.common.rest.names.ScientificNamesService;
import org.jacq.service.dataimport.util.ServicesUtil;

/**
 *
 * @author wkoller
 */
@ManagedBean
public class DataImportManager {

    private static final Logger LOGGER = Logger.getLogger(DataImportManager.class.getName());

    @PersistenceContext
    protected EntityManager em;

    protected SimpleDateFormat separationDateFormat = new SimpleDateFormat("YYYY-mm-dd");

    /**
     * @throws java.io.IOException
     * @see
     * DataImportService#dataImport(org.jacq.common.model.dataimport.ImportFile)
     */
    public void dataImport(ImportFile importFile) throws IOException, ParseException {
        // Decode Base64 file content
        importFile.setFileContent(new String(Base64.getDecoder().decode(importFile.getFileContent()), Charset.forName("utf8")));

        // now parse content as CSV file
        StringReader reader = new StringReader(importFile.getFileContent());
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(reader);

        // iterate over each line and try to import it
        for (CSVRecord record : records) {
            // map csv record to import-record
            int i = 0;
            ImportRecord importRecord = new ImportRecord();

            // do the actual mapping of the record to the import record structure (including type conversions etc.)
            importRecord.setSpecimenNumber(record.get(i++));
            importRecord.setOrganization(record.get(i++));
            importRecord.setScientificName(record.get(i++));
            importRecord.setAlternativeNumber(record.get(i++));
            importRecord.setGenericAnnotation(record.get(i++));
            importRecord.setLivingPlantNumber(record.get(i++));
            importRecord.setOriginalId(Long.valueOf(record.get(i++)));
            importRecord.setGatheringNumber(record.get(i++));
            importRecord.setSeparationDate(separationDateFormat.parse(record.get(i++)));
            importRecord.setSeparationType(record.get(i++));
            importRecord.setLabelAnnotation(record.get(i++));
        }
    }

    /**
     * Reads the bromi mdb file and imports it into the system
     */
    public void importBromiMdb() throws IOException {
        // glashaus lookup table
        HashMap<String, String> glashausLookup = new HashMap<>();
        glashausLookup.put("BH", "G12");
        glashausLookup.put("JH", "G18");
        glashausLookup.put("QG", "G9");
        glashausLookup.put("GH5", "G5");
        glashausLookup.put("XH", "G11");
        glashausLookup.put("WH1", "G8");
        glashausLookup.put("TOH", "G15");
        glashausLookup.put("WOH", "G16");
        glashausLookup.put("KH", "G13");
        glashausLookup.put("WH2", "G7");
        glashausLookup.put("SUK", "Sukkulente");
        glashausLookup.put("GH", "Sukkulente");
        glashausLookup.put("Sukh", "Sukkulente");
        glashausLookup.put("TOH+F367", "G15");
        glashausLookup.put("TROPH", "G6");
        glashausLookup.put("WH", "Warmhaus");
        glashausLookup.put("XH (WH)", "G11");

        Database db = DatabaseBuilder.open(new File("/tmp/bromi.mdb"));
        Table table = db.getTable("Tabelle");

        for (Row row : table) {
            ImportRecord importRecord = new ImportRecord();

            // translate glashaus (location / organization)
            String glashaus = glashausLookup.get(row.getString("Glashaus"));
            if (glashaus == null) {
                LOGGER.log(Level.WARNING, "Invalid Glashaus for entry '{0}'! ({1})", new Object[]{row.getString("ID"), row.getString("Glashaus")});
                continue;
            }
            importRecord.setOrganization(glashaus);

            // clean the scientific name
            importRecord.setScientificName(row.getString("Gattung") + " " + row.getString("Art") + " " + row.getString("Feld1"));
            importRecord.setScientificName(importRecord.getScientificName().replaceAll("\\(.*\\)", ""));

            // alternative number
            importRecord.setAlternativeNumber(row.getString("Herkunft-Nr"));

            // generic annotation
            importRecord.setGenericAnnotation("Zuletzt bearbeitet am: " + row.getString("Bearbeitungs Datum") + "; " + row.getString("Unklar"));

            // living plant number
            importRecord.setLivingPlantNumber(row.getString("Garten-Nr"));

            // gathering number
            importRecord.setGatheringNumber(row.getString("Herkunft-Nr"));

            // label annotation
            importRecord.setLabelAnnotation((!StringUtils.isEmpty(row.getString("Garten-Nr"))) ? row.getString("Garten-Nr") : row.getString("Herkunft-Nr"));

            // separation
            if (!StringUtils.isEmpty(row.getString("Pflanzen Abgang"))) {
                String pflanzenAbgang = row.getString("Pflanzen Abgang");

                // separate date information
                String pflanzenAbgangParts[] = pflanzenAbgang.split(" ");

                if (pflanzenAbgangParts.length >= 2) {
                    int month = -1;
                    switch (pflanzenAbgangParts[0]) {
                        case "Jänner":
                            month = 0;
                            break;
                        case "Februar":
                            month = 1;
                            break;
                        case "März":
                            month = 2;
                            break;
                        case "April":
                            month = 3;
                            break;
                        case "Mai":
                            month = 4;
                            break;
                        case "Juni":
                            month = 5;
                            break;
                        case "Juli":
                            month = 6;
                            break;
                        case "August":
                            month = 7;
                            break;
                        case "September":
                            month = 8;
                            break;
                        case "Oktober":
                            month = 9;
                            break;
                        case "November":
                            month = 10;
                            break;
                        case "Dezember":
                            month = 11;
                            break;
                        default:
                            LOGGER.log(Level.WARNING, "Invalid Month-Format for entry '{0}'! ({1})", new Object[]{row.getString("ID"), pflanzenAbgangParts[0]});
                            break;
                    }

                    if (month >= 0) {
                        Date separationDate = new Date(Integer.valueOf(pflanzenAbgangParts[1]) + 100, month, 1);
                        importRecord.setSeparationDate(separationDate);
                        importRecord.setSeparationType("dead");
                    }

                }
                else {
                    LOGGER.log(Level.WARNING, "Invalid Date-Format for entry '{0}'! ({1})", new Object[]{row.getString("ID"), row.getString("Pflanzen Abgang")});
                }
            }
        }
    }

    /**
     * Processes a record entry and writes the data to the database
     *
     * @param importRecord
     */
    @Transactional(rollbackOn = {Exception.class})
    protected void importRecord(ImportRecord importRecord) {
        // check if we need to import a living plant
        if (!StringUtils.isEmpty(importRecord.getLivingPlantNumber())) {
            // check if entry already exists
            TypedQuery<TblAlternativeAccessionNumber> alternativeAccessionNumberQuery = em.createNamedQuery("TblAlternativeAccessionNumber.findByNumber", TblAlternativeAccessionNumber.class);
            alternativeAccessionNumberQuery.setParameter("number", importRecord.getLivingPlantNumber());
            TblAlternativeAccessionNumber alternativeAccessionNumber = alternativeAccessionNumberQuery.getSingleResult();
            if (alternativeAccessionNumber != null) {
                LOGGER.log(Level.INFO, "Living-Plant Entry already exists: '{0}'", importRecord.getLivingPlantNumber());
                return;
            }

            // no entry exists yet, start creating the data
            // lookup default acqusition type
            TypedQuery<TblAcquisitionType> acquisitionTypeQuery = em.createNamedQuery("TblAcquisitionType.findById", TblAcquisitionType.class);
            acquisitionTypeQuery.setParameter("id", 1);
            TblAcquisitionType acquisitionType = acquisitionTypeQuery.getSingleResult();
            if (acquisitionType == null) {
                throw new IllegalArgumentException("Unable to find acquisition type");
            }

            // start with the gathering location coordinates
            TblLocationCoordinates locationCoordinates = new TblLocationCoordinates();
            em.persist(locationCoordinates);

            // setup gathering date
            TblAcquisitionDate acquisitionDate = new TblAcquisitionDate();
            em.persist(acquisitionDate);

            // setup the gathering event
            TblAcquisitionEvent acquisitionEvent = new TblAcquisitionEvent();
            acquisitionEvent.setLocationCoordinatesId(locationCoordinates);
            acquisitionEvent.setAcquisitionDateId(acquisitionDate);
            acquisitionEvent.setAcquisitionTypeId(acquisitionType);
            acquisitionEvent.setNumber(importRecord.getGatheringNumber());
            em.persist(acquisitionEvent);

            // lookup the organization by name
            TypedQuery<TblOrganisation> organisationQuery = em.createNamedQuery("TblOrganisation.findByDescription", TblOrganisation.class);
            organisationQuery.setParameter("description", importRecord.getOrganization());
            TblOrganisation organisation = organisationQuery.getSingleResult();
            if (organisation == null) {
                throw new IllegalArgumentException("Unable to find organisation");
            }

            // lookup scientific name id through taxamatch service
            // 'vienna', $model_importSpecies->getScientificName(), array('showSyn' => false, 'NearMatch' => false)
            TaxamatchOptions taxamatchOptions = new TaxamatchOptions();
            taxamatchOptions.setNearMatch(false);
            taxamatchOptions.setShowSyn(false);

            ArrayList<Object> params = new ArrayList<>();
            params.add("vienna");
            params.add(importRecord.getScientificName());

            JsonRpcRequest jsonRpcRequest = new JsonRpcRequest();
            jsonRpcRequest.setId(1L);
            jsonRpcRequest.setMethod("getMatchesService");
            jsonRpcRequest.setParams(params);

            ScientificNamesService scientificNamesService = ServicesUtil.getScientificNamesService();
            Response results = scientificNamesService.taxamatchMdld(jsonRpcRequest);

            // setup the botanical object
            TblBotanicalObject botanicalObject = new TblBotanicalObject();
            botanicalObject.setAcquisitionEventId(acquisitionEvent);
            botanicalObject.setRecordingDate(new Date());
            botanicalObject.setAnnotation(importRecord.getGenericAnnotation());
            botanicalObject.setScientificNameId(1); // TODO query taxamatch
            botanicalObject.setOrganisationId(organisation);
            em.persist(botanicalObject);

            // setup living plant object
            TblLivingPlant livingPlant = new TblLivingPlant();
            livingPlant.setId(botanicalObject.getId());
            livingPlant.setLabelAnnotation(importRecord.getLabelAnnotation());
            em.persist(livingPlant);

            // store alternative accession number
            if (!StringUtils.isEmpty(importRecord.getAlternativeNumber())) {
                alternativeAccessionNumber = new TblAlternativeAccessionNumber();
                alternativeAccessionNumber.setLivingPlantId(livingPlant);
                alternativeAccessionNumber.setNumber(importRecord.getAlternativeNumber());
                em.persist(alternativeAccessionNumber);
            }

            // store original living plant id as alternative accession number
            alternativeAccessionNumber = new TblAlternativeAccessionNumber();
            alternativeAccessionNumber.setLivingPlantId(livingPlant);
            alternativeAccessionNumber.setNumber(importRecord.getLivingPlantNumber());
            em.persist(alternativeAccessionNumber);

            // store separation data
            if (importRecord.getSeparationDate() != null) {
                // lookup separation type by name
                TypedQuery<TblSeparationType> separationTypeQuery = em.createNamedQuery("TblSeparationType.findByType", TblSeparationType.class);
                organisationQuery.setParameter("type", importRecord.getSeparationType());
                TblSeparationType separationType = separationTypeQuery.getSingleResult();
                if (organisation == null) {
                    throw new IllegalArgumentException("Unable to find separation-type");
                }

                TblSeparation separation = new TblSeparation();
                separation.setBotanicalObjectId(botanicalObject);
                separation.setSeparationTypeId(separationType);
                separation.setDate(importRecord.getSeparationDate());
                em.persist(separation);
            }

        }
    }
}
