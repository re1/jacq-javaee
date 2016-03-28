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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;
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
import org.jacq.common.model.jpa.TblImportProperties;
import org.jacq.common.model.jpa.TblLivingPlant;
import org.jacq.common.model.jpa.TblLocationCoordinates;
import org.jacq.common.model.jpa.TblOrganisation;
import org.jacq.common.model.jpa.TblSeparation;
import org.jacq.common.model.jpa.TblSeparationType;
import org.jacq.common.model.names.JsonRpcRequest;
import org.jacq.common.model.names.taxamatch.Result;
import org.jacq.common.model.names.taxamatch.Searchresult;
import org.jacq.common.model.names.taxamatch.Species;
import org.jacq.common.model.names.taxamatch.TaxamatchOptions;
import org.jacq.common.model.names.taxamatch.TaxamatchResponse;
import org.jacq.common.rest.names.ScientificNamesService;
import org.jacq.service.dataimport.util.ServicesUtil;

/**
 *
 * @author wkoller
 */
@ManagedBean
@Transactional
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
    public void importBromiMdb() throws IOException, ParseException {
        // glashaus lookup table
        HashMap<String, String> glashausLookup = new HashMap<>();
        glashausLookup.put("BH", "G12 (Bromelien)");
        glashausLookup.put("JH", "G18 (Jacquinhaus)");
        glashausLookup.put("QG", "G9 (Orchideen)");
        glashausLookup.put("GH5", "G5 (Sukkulentenhaus West)");
        glashausLookup.put("XH", "G11 (Xerophyten)");
        glashausLookup.put("WH1", "G8 (Warmhaus 1)");
        glashausLookup.put("TOH", "G15 (Orchideen temp. Haus)");
        glashausLookup.put("WOH", "G16 (Orchideen Warmhaus)");
        glashausLookup.put("KH", "G13 (Orchideen-Kalthaus)");
        glashausLookup.put("WH2", "G7 (Warmhaus 2)");
        glashausLookup.put("SUK", "Sukkulente");
        glashausLookup.put("GH", "Sukkulente");
        glashausLookup.put("Sukh", "Sukkulente");
        glashausLookup.put("TOH+F367", "G15 (Orchideen temp. Haus)");
        glashausLookup.put("TROPH", "G6 (Tropenhaus öffentlich)");
        glashausLookup.put("WH", "Warmhaus");
        glashausLookup.put("XH (WH)", "G11 (Xerophyten)");
        glashausLookup.put("XH  (KH)", "G11 (Xerophyten)");
        glashausLookup.put("", "Botanischer Garten");
        glashausLookup.put("AG", "Orchideen");
        glashausLookup.put("Augarten", "Orchideen");

        Database db = DatabaseBuilder.open(new File("/tmp/bromi.mdb"));
        Table table = db.getTable("Tabelle");
        int rowCount = table.getRowCount();
        int counter = 0;

        for (Row row : table) {
            ImportRecord importRecord = new ImportRecord();

            // remember original id
            importRecord.setOriginalId(Long.valueOf(row.getInt("ID")));

            // translate glashaus (location / organization)
            String glashaus = row.getString("Glashaus");
            if (StringUtils.isEmpty(glashaus)) {
                glashaus = "";
            }
            glashaus = glashaus.trim();
            glashaus = glashausLookup.get(glashaus);
            if (glashaus == null) {
                LOGGER.log(Level.WARNING, "Invalid Glashaus for entry ''{0}''! ({1})", new Object[]{row.getInt("ID"), row.getString("Glashaus")});
                continue;
            }
            importRecord.setOrganization(glashaus);

            // clean the scientific name
            String gattung = row.getString("Gattung");
            String art = row.getString("Art");
            String feld1 = row.getString("Feld1");
            String scientificName = gattung;
            if (art != null) {
                scientificName += " " + art;
            }
            if (feld1 != null) {
                scientificName += " " + feld1;
            }
            if (StringUtils.isEmpty(scientificName)) {
                LOGGER.log(Level.WARNING, "No Scientific Name for entry ''{0}''!", new Object[]{row.getInt("ID")});
                scientificName = "Indeterminatae dicot";
            }

            importRecord.setScientificName(scientificName);
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

                // try to parse the date as "normal" date
                try {
                    importRecord.setSeparationDate(DateFormat.getDateInstance(DateFormat.SHORT).parse(pflanzenAbgang));
                    importRecord.setSeparationType("dead (eliminated)");
                } catch (ParseException pe) {
                    // separate date information
                    String pflanzenAbgangParts[] = pflanzenAbgang.split(" |\\.");

                    if (pflanzenAbgangParts.length >= 2) {
                        if (pflanzenAbgangParts[0].equals("ca")) {
                            if (pflanzenAbgangParts.length > 3) {
                                pflanzenAbgangParts[0] = pflanzenAbgangParts[2];
                                pflanzenAbgangParts[1] = pflanzenAbgangParts[3];
                            }
                            else {
                                pflanzenAbgangParts[0] = pflanzenAbgangParts[1];
                                pflanzenAbgangParts[1] = pflanzenAbgangParts[2];
                            }
                        }
                        else if (pflanzenAbgangParts.length > 2) {
                            pflanzenAbgangParts[1] = pflanzenAbgangParts[2];
                        }

                        int month = -1;
                        switch (pflanzenAbgangParts[0]) {
                            case "Jänner":
                            case "Jan":
                            case "Jän":
                                month = 0;
                                break;
                            case "Februar":
                            case "Feb":
                                month = 1;
                                break;
                            case "März":
                                month = 2;
                                break;
                            case "April":
                            case "Apr":
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
                            case "Aug":
                                month = 7;
                                break;
                            case "September":
                            case "Sep":
                                month = 8;
                                break;
                            case "Oktober":
                            case "Okt":
                                month = 9;
                                break;
                            case "November":
                            case "Nov":
                                month = 10;
                                break;
                            case "Dezember":
                            case "Dez":
                                month = 11;
                                break;
                            default:
                                LOGGER.log(Level.WARNING, "Invalid Month-Format for entry ''{0}''! ({1} / {2})", new Object[]{row.getInt("ID"), pflanzenAbgangParts[0], pflanzenAbgang});
                                break;
                        }

                        if (month >= 0) {
                            try {
                                Date separationDate = new Date(Integer.valueOf(pflanzenAbgangParts[1]) + 100, month, 1);
                                importRecord.setSeparationDate(separationDate);
                                importRecord.setSeparationType("dead (eliminated)");
                            } catch (Exception e) {
                                LOGGER.log(Level.WARNING, "Unable to parse date for entry ''{0}''! ({1})", new Object[]{row.getInt("ID"), row.getString("Pflanzen Abgang")});
                            }
                        }

                    }
                    else {
                        LOGGER.log(Level.WARNING, "Invalid Date-Format for entry ''{0}''! ({1})", new Object[]{row.getInt("ID"), row.getString("Pflanzen Abgang")});
                    }
                }
            }

            // Augarten entries have their own separation (static)
            if (glashaus.equals(glashausLookup.get("AG"))) {
                importRecord.setSeparationDate(DateFormat.getDateInstance(DateFormat.SHORT).parse("17.12.2012"));
                importRecord.setSeparationType("separated");
                importRecord.setSeparationAnnotation("Augarten");
            }

            // finally run the import
            this.importRecord(importRecord);

            counter++;

            LOGGER.log(Level.INFO, "Processed entry {0} of {1}", new Object[]{counter, rowCount});
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
            List<TblAlternativeAccessionNumber> alternativeAccessionNumbers = alternativeAccessionNumberQuery.getResultList();
            if (alternativeAccessionNumbers.size() > 0) {
                LOGGER.log(Level.INFO, "Living-Plant Entry already exists: ''{0}''", importRecord.getLivingPlantNumber());
                return;
            }

            // no entry exists yet, start creating the data
            // lookup default acqusition type
            TypedQuery<TblAcquisitionType> acquisitionTypeQuery = em.createNamedQuery("TblAcquisitionType.findById", TblAcquisitionType.class);
            acquisitionTypeQuery.setParameter("id", 1);
            List<TblAcquisitionType> acquisitionTypes = acquisitionTypeQuery.getResultList();
            if (acquisitionTypes.size() <= 0) {
                throw new IllegalArgumentException("Unable to find acquisition type: '" + 1 + "'");
            }
            TblAcquisitionType acquisitionType = acquisitionTypes.get(0);

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
            List<TblOrganisation> organisations = organisationQuery.getResultList();
            if (organisations.size() <= 0) {
                throw new IllegalArgumentException("Unable to find organisation: '" + importRecord.getOrganization() + "'");
            }
            TblOrganisation organisation = organisations.get(0);

            // lookup scientific name id through taxamatch service
            // 'vienna', $model_importSpecies->getScientificName(), array('showSyn' => false, 'NearMatch' => false)
            TaxamatchOptions taxamatchOptions = new TaxamatchOptions();
            taxamatchOptions.setNearMatch(false);
            taxamatchOptions.setShowSyn(false);

            ArrayList<Object> params = new ArrayList<>();
            params.add("vienna");
            params.add(importRecord.getScientificName());
            params.add(taxamatchOptions);

            JsonRpcRequest jsonRpcRequest = new JsonRpcRequest();
            jsonRpcRequest.setId(1L);
            jsonRpcRequest.setMethod("getMatchesService");
            jsonRpcRequest.setParams(params);

            ScientificNamesService scientificNamesService = ServicesUtil.getScientificNamesService();
            TaxamatchResponse response = scientificNamesService.taxamatchMdld(jsonRpcRequest);

            Long scientificNameId = 0L;
            Long genusScientificNameId = 0L;
            for (Result result : response.getResult().getResult()) {
                for (Searchresult searchResult : result.getSearchresult()) {
                    // check for genus match
                    if (searchResult.getDistance() == 0) {
                        genusScientificNameId = searchResult.getID();
                    }

                    for (Species species : searchResult.getSpecies()) {
                        if (species.getDistance() <= 0L) {
                            scientificNameId = species.getTaxonID();
                            break;
                        }
                    }

                    if (scientificNameId != 0L) {
                        break;
                    }
                }

                if (scientificNameId != 0L) {
                    break;
                }
            }
            if (scientificNameId == 0L) {
                if (genusScientificNameId == 0L) {
                    LOGGER.log(Level.INFO, "No scientific name id found for ''{0}''. Pointing to indet.", importRecord.getScientificName());
                    scientificNameId = 46236L;
                }
                else {
                    LOGGER.log(Level.INFO, "No exact scientific name match found for ''{0}''. Pointing to genus entry.", importRecord.getScientificName());
                    scientificNameId = genusScientificNameId;
                }

            }

            // setup the botanical object
            TblBotanicalObject botanicalObject = new TblBotanicalObject();
            botanicalObject.setAcquisitionEventId(acquisitionEvent);
            botanicalObject.setRecordingDate(new Date());
            botanicalObject.setAnnotation(importRecord.getGenericAnnotation());
            botanicalObject.setScientificNameId(scientificNameId);
            botanicalObject.setOrganisationId(organisation);
            em.persist(botanicalObject);

            // setup living plant object
            TblLivingPlant livingPlant = new TblLivingPlant();
            livingPlant.setId(botanicalObject.getId());
            livingPlant.setLabelAnnotation(importRecord.getLabelAnnotation());
            em.persist(livingPlant);

            // store alternative accession number
            TblAlternativeAccessionNumber alternativeAccessionNumber = null;
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
                separationTypeQuery.setParameter("type", importRecord.getSeparationType());
                List<TblSeparationType> separationTypes = separationTypeQuery.getResultList();
                if (separationTypes == null || separationTypes.size() <= 0) {
                    throw new IllegalArgumentException("Unable to find separation-type: '" + importRecord.getSeparationType() + "'");
                }
                TblSeparationType separationType = separationTypes.get(0);

                TblSeparation separation = new TblSeparation();
                separation.setBotanicalObjectId(botanicalObject);
                separation.setSeparationTypeId(separationType);
                separation.setDate(importRecord.getSeparationDate());
                separation.setAnnotation(importRecord.getSeparationAnnotation());
                em.persist(separation);
            }

            // persist the import properties
            TblImportProperties importProperties = new TblImportProperties();
            importProperties.setBotanicalObjectId(botanicalObject);
            importProperties.setIDPflanze(importRecord.getOriginalId());
            importProperties.setSpeciesName(importRecord.getScientificName());
            em.persist(importProperties);
        }
    }
}
