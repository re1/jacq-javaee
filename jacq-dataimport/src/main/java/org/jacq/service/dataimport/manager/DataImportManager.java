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

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
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
            importRecord.setOrganizationId(Long.valueOf(record.get(i++)));
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
     * Processes a record entry and writes the data to the database
     *
     * @param importRecord
     */
    @Transactional(rollbackOn = {Exception.class})
    public void importRecord(ImportRecord importRecord) {
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
            TypedQuery<TblOrganisation> organisationQuery = em.createNamedQuery("TblOrganisation.findById", TblOrganisation.class);
            organisationQuery.setParameter("id", importRecord.getOrganizationId());
            TblOrganisation organisation = organisationQuery.getSingleResult();
            if (organisation == null) {
                throw new IllegalArgumentException("Unable to find organisation");
            }

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
        }

    }
}
