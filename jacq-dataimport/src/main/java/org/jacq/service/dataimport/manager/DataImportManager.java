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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
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
import org.jacq.common.model.jpa.TblCultivar;
import org.jacq.common.model.jpa.TblDerivative;
import org.jacq.common.model.jpa.TblDerivativeType;
import org.jacq.common.model.jpa.TblImportProperties;
import org.jacq.common.model.jpa.TblLivingPlant;
import org.jacq.common.model.jpa.TblLocationCoordinates;
import org.jacq.common.model.jpa.TblOrganisation;
import org.jacq.common.model.jpa.TblScientificNameInformation;
import org.jacq.common.model.jpa.TblSeparation;
import org.jacq.common.model.jpa.TblSeparationType;
import org.jacq.common.model.names.JsonRpcRequest;
import org.jacq.common.model.names.taxamatch.Result;
import org.jacq.common.model.names.taxamatch.Searchresult;
import org.jacq.common.model.names.taxamatch.Species;
import org.jacq.common.model.names.taxamatch.TaxamatchOptions;
import org.jacq.common.model.names.taxamatch.TaxamatchResponse;
import org.jacq.common.rest.OrganisationService;
import org.jacq.common.external.rest.ScientificNamesService;
import org.jacq.common.model.jpa.TblAcquisitionEventSource;
import org.jacq.common.model.jpa.TblAcquisitionSource;
import org.jacq.common.model.jpa.TblIdentStatus;
import org.jacq.common.model.jpa.TblLocation;
import org.jacq.common.model.jpa.TblPerson;
import org.jacq.common.model.rest.LocationResult;
import org.jacq.common.rest.GatheringService;
import org.jacq.service.dataimport.util.ServicesUtil;

/**
 *
 * @author wkoller
 */
@ManagedBean
@Transactional
public class DataImportManager {

    /**
     * Possible return values for an import
     */
    public enum ImportStatus {
        INSERTED,
        UPDATED
    };

    private static final Logger LOGGER = Logger.getLogger(DataImportManager.class.getName());

    private static final Long INDET_SCIENTIFIC_NAME_ID = 46996L;

    @PersistenceContext
    protected EntityManager em;

    protected OrganisationService organisationService;

    protected GatheringService gatheringService;

    protected SimpleDateFormat separationDateFormat = new SimpleDateFormat("YYYY-mm-dd");

    protected HashMap<Integer, Long> taxamatchCache = new HashMap<>();

    public DataImportManager() {
    }

    @PostConstruct
    public void init() {
        this.organisationService = ServicesUtil.getOrganisationService();
        this.gatheringService = ServicesUtil.getGatheringService();
    }

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
            importRecord.setAlternativeLivingPlantNumber(record.get(i++));
            importRecord.setSeparationAnnotation(record.get(i++));
            importRecord.setMatchFamily(record.get(i++));
            importRecord.setIpenNumber(record.get(i++));
            importRecord.setCultureNotes(record.get(i++));
            importRecord.setPlaceNumber(record.get(i++));
            importRecord.setCount(Long.valueOf(record.get(i++)));
            importRecord.setSourceName(record.get(i++));
            importRecord.setOriginalBotanicalObjectId(Long.valueOf(record.get(i++)));
            importRecord.setCultivar(record.get(i++));
            importRecord.setCommonNames(record.get(i++));
            importRecord.setPrice(Float.valueOf(record.get(i++)));
            importRecord.setGatheringDate(separationDateFormat.parse(record.get(i++)));
            importRecord.setGatheringSource(record.get(i++));
            importRecord.setAltitudeMin(Long.valueOf(record.get(i++)));
            importRecord.setAltitudeMax(Long.valueOf(record.get(i++)));
            importRecord.setIdentStatus(Long.valueOf(record.get(i++)));
            importRecord.setGatheringPerson(Long.valueOf(record.get(i++)));
            importRecord.setGatheringLocation(record.get(i++));
            importRecord.setDefaultScientificNameId(Long.valueOf(record.get(i++)));

            // call import function
            this.importRecord(importRecord);

        }
    }

    /**
     * Processes a record entry and writes the data to the database
     *
     * @param importRecord
     */
    @Transactional(rollbackOn = {Exception.class})
    protected ImportStatus importRecord(ImportRecord importRecord) {
        // cleanup some properties
        importRecord.setScientificName(importRecord.getScientificName().trim());

        // check if we need to import a living plant
        if (!StringUtils.isEmpty(importRecord.getLivingPlantNumber())) {
            // check if entry already exists
            TypedQuery<TblAlternativeAccessionNumber> alternativeAccessionNumberQuery = em.createNamedQuery("TblAlternativeAccessionNumber.findByNumber", TblAlternativeAccessionNumber.class);
            alternativeAccessionNumberQuery.setParameter("number", importRecord.getLivingPlantNumber());
            List<TblAlternativeAccessionNumber> alternativeAccessionNumbers = alternativeAccessionNumberQuery.getResultList();
            // check if we have an alternative living plant number & try to find existing entry
            if (alternativeAccessionNumbers.size() <= 0 && importRecord.getAlternativeLivingPlantNumber() != null) {
                alternativeAccessionNumberQuery = em.createNamedQuery("TblAlternativeAccessionNumber.findByNumber", TblAlternativeAccessionNumber.class);
                alternativeAccessionNumberQuery.setParameter("number", importRecord.getAlternativeLivingPlantNumber());
                alternativeAccessionNumbers = alternativeAccessionNumberQuery.getResultList();
            }

            // check if we found an existing entry, if yes use the first match and append the info to the annotation field
            if (alternativeAccessionNumbers.size() > 0) {

                TblAlternativeAccessionNumber alternativeAccessionNumber = alternativeAccessionNumbers.get(0);
                TblBotanicalObject botanicalObject = alternativeAccessionNumber.getLivingPlantId().getTblDerivative().getBotanicalObjectId();

                // check if we limit to a certain family
                if (!StringUtils.isEmpty(importRecord.getMatchFamily())) {
                    // check if we can find a family
                    if (!StringUtils.isEmpty(botanicalObject.getViewTaxon().getFamily())) {
                        // compare family, but ignore case
                        if (importRecord.getMatchFamily().equalsIgnoreCase(botanicalObject.getViewTaxon().getFamily())) {
                            LOGGER.log(Level.INFO, "Living-Plant Entry already exists: ''{0}'' / ''{1}''", new Object[]{importRecord.getLivingPlantNumber(), importRecord.getAlternativeLivingPlantNumber()});

                            String annotation = botanicalObject.getAnnotation();

                            // check if we already have an annotation, if yes append a newline
                            if (StringUtils.isEmpty(annotation)) {
                                annotation = importRecord.getImportFile().getFileName() + ": " + importRecord.toAnnotationString();
                            } else {
                                annotation += "\n" + importRecord.getImportFile().getFileName() + ": " + importRecord.toAnnotationString();
                            }
                            botanicalObject.setAnnotation(annotation);
                            em.persist(botanicalObject);

                            LOGGER.log(Level.INFO, "Updated annotation for entry: ''{0}''", botanicalObject.getId());

                            return ImportStatus.UPDATED;
                        } else {
                            LOGGER.log(Level.INFO, "Family of matched entry does not conform to match-family: ''{0}'' vs ''{1}''", new Object[]{botanicalObject.getViewTaxon().getFamily(), importRecord.getMatchFamily()});
                        }
                    } else {
                        LOGGER.log(Level.INFO, "No family entry found for: ''{0}'' ({1})", new Object[]{botanicalObject.getViewScientificName().getScientificName(), botanicalObject.getScientificNameId()});
                    }
                }
            }

            // try to find an original entry by matching the original botanicalobject id and source
            TblBotanicalObject botanicalObject = null;
            TblCultivar cultivar = null;
            if (importRecord.getOriginalBotanicalObjectId() != null && importRecord.getOriginalBotanicalObjectId() > 0) {
                TypedQuery<TblImportProperties> importPropertiesQuery = em.createNamedQuery("TblImportProperties.findByOriginalBotanicalObjectIdAndSourceName", TblImportProperties.class);
                importPropertiesQuery.setParameter("originalBotanicalObjectId", importRecord.getOriginalBotanicalObjectId());
                importPropertiesQuery.setParameter("sourceName", importRecord.getSourceName());
                List<TblImportProperties> importProperties = importPropertiesQuery.getResultList();
                if (importProperties != null & importProperties.size() > 0) {
                    botanicalObject = importProperties.get(0).getDerivativeId().getBotanicalObjectId();
                }
            }

            List<LocationResult> locationResults = new ArrayList<>(); // For IPEN
            // if no previous botanical object is found, create a new entry for it
            if (botanicalObject == null) {
                // no entry exists yet, start creating the data
                // lookup default acqusition type
                TypedQuery<TblAcquisitionType> acquisitionTypeQuery = em.createNamedQuery("TblAcquisitionType.findById", TblAcquisitionType.class);
                acquisitionTypeQuery.setParameter("id", 1L);
                List<TblAcquisitionType> acquisitionTypes = acquisitionTypeQuery.getResultList();
                if (acquisitionTypes.size() <= 0) {
                    throw new IllegalArgumentException("Unable to find acquisition type: '" + 1 + "'");
                }
                TblAcquisitionType acquisitionType = acquisitionTypes.get(0);

                // start with the gathering location coordinates
                TblLocationCoordinates locationCoordinates = new TblLocationCoordinates();
                locationCoordinates.setAltitudeMin(importRecord.getAltitudeMin());
                locationCoordinates.setAltitudeMax(importRecord.getAltitudeMax());
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

                if (importRecord.getGatheringLocation() != null) {
                    locationResults = this.gatheringService.locationFind(importRecord.getGatheringLocation(), 0, 1);
                    acquisitionEvent.setLocationId(em.find(TblLocation.class, locationResults.get(0).getLocationId()));
                }
                List<TblPerson> tblPersonList = acquisitionEvent.getTblPersonList();
                if (importRecord.getGatheringPerson() != null) {
                    if (tblPersonList != null && tblPersonList.size() > 0) {
                        tblPersonList.add(em.find(TblPerson.class, importRecord.getGatheringPerson()));
                    } else {
                        tblPersonList = new ArrayList<>();
                        tblPersonList.add(em.find(TblPerson.class, importRecord.getGatheringPerson()));
                    }
                    acquisitionEvent.setTblPersonList(tblPersonList);
                }
                em.persist(acquisitionEvent);

                // check if gathering source already exists
                if (importRecord.getGatheringSource() != null) {
                    TypedQuery<TblAcquisitionSource> acquisitionSourceQuery = em.createNamedQuery("TblAcquisitionSource.findByName", TblAcquisitionSource.class);
                    acquisitionSourceQuery.setParameter("name", importRecord.getGatheringSource());
                    List<TblAcquisitionSource> acquisitionSourceList = acquisitionSourceQuery.getResultList();
                    TblAcquisitionSource acquisitionSource = null;
                    if (acquisitionSourceList != null && acquisitionSourceList.size() > 0) {
                        acquisitionSource = acquisitionSourceList.get(0);
                    } else {
                        acquisitionSource = new TblAcquisitionSource();
                        acquisitionSource.setName(importRecord.getGatheringSource());
                        em.persist(acquisitionSource);
                    }
                    // save gathering event source
                    TblAcquisitionEventSource acquisitionEventSource = new TblAcquisitionEventSource();
                    acquisitionEventSource.setAcquisitionEventId(acquisitionEvent);
                    acquisitionEventSource.setAcquisitionSourceId(acquisitionSource);
                    acquisitionEventSource.setSourceDate(importRecord.getGatheringDate());
                    em.persist(acquisitionEventSource);
                }

                // lookup scientific name id through taxamatch service, but check cache first
                Long scientificNameId = 0L;
                if (taxamatchCache.get(importRecord.getScientificName().hashCode()) != null) {
                    scientificNameId = taxamatchCache.get(importRecord.getScientificName().hashCode());
                } else {
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

                    Long genusScientificNameId = 0L;
                    for (Result result : response.getResult().getResult()) {
                        for (Searchresult searchResult : result.getSearchresult()) {
                            // check for genus match
                            if (searchResult.getDistance() == 0) {
                                genusScientificNameId = searchResult.getTaxonID();
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
                        if (importRecord.getDefaultScientificNameId() != null) {
                            scientificNameId = importRecord.getDefaultScientificNameId();
                        } else if (genusScientificNameId == 0L) {
                            LOGGER.log(Level.INFO, "No scientific name id found for ''{0}''. Pointing to indet.", importRecord.getScientificName());
                            scientificNameId = INDET_SCIENTIFIC_NAME_ID;
                        } else {
                            LOGGER.log(Level.INFO, "No exact scientific name match found for ''{0}''. Pointing to genus entry.", importRecord.getScientificName());
                            scientificNameId = genusScientificNameId;
                        }
                    }
                    // remember taxamatch result in cache so we don't have to ask again
                    taxamatchCache.put(importRecord.getScientificName().hashCode(), scientificNameId);
                }

                // check for common names
                if (!StringUtils.isBlank(importRecord.getCommonNames())) {
                    // try to find a matching scientific name information entry
                    TypedQuery<TblScientificNameInformation> scientificNameInformationQuery = em.createNamedQuery("TblScientificNameInformation.findByScientificNameId", TblScientificNameInformation.class);
                    scientificNameInformationQuery.setParameter("scientificNameId", scientificNameId);
                    List<TblScientificNameInformation> scientificNameInformations = scientificNameInformationQuery.getResultList();
                    TblScientificNameInformation scientificNameInformation = null;
                    if (scientificNameInformations != null && scientificNameInformations.size() > 0) {
                        scientificNameInformation = scientificNameInformations.get(0);
                    } else {
                        // create a new scientific name information entry
                        scientificNameInformation = new TblScientificNameInformation();
                        scientificNameInformation.setScientificNameId(scientificNameId);

                        LOGGER.log(Level.INFO, "No scientific name information found for id ''{0}''. Added new entry.", scientificNameId);
                    }

                    // check if scientific name information already contains the common names information
                    if (StringUtils.isBlank(scientificNameInformation.getCommonNames()) || !StringUtils.containsIgnoreCase(scientificNameInformation.getCommonNames(), importRecord.getCommonNames())) {
                        scientificNameInformation.setCommonNames(scientificNameInformation.getCommonNames() + "; " + importRecord.getCommonNames());
                    }

                    // finally save the scientific name information
                    em.merge(scientificNameInformation);
                }

                // check for cultivar entry
                if (!StringUtils.isEmpty(importRecord.getCultivar())) {
                    TypedQuery<TblCultivar> cultivarQuery = em.createNamedQuery("TblCultivar.findByCultivarAndScientificNameId", TblCultivar.class);
                    cultivarQuery.setParameter("cultivar", importRecord.getCultivar());
                    cultivarQuery.setParameter("scientificNameId", scientificNameId);

                    List<TblCultivar> cultivars = cultivarQuery.getResultList();
                    if (cultivars != null && cultivars.size() > 0) {
                        cultivar = cultivars.get(0);
                    } else {
                        // try to find a matching scientific name information entry
                        TypedQuery<TblScientificNameInformation> scientificNameInformationQuery = em.createNamedQuery("TblScientificNameInformation.findByScientificNameId", TblScientificNameInformation.class);
                        scientificNameInformationQuery.setParameter("scientificNameId", scientificNameId);
                        List<TblScientificNameInformation> scientificNameInformations = scientificNameInformationQuery.getResultList();
                        TblScientificNameInformation scientificNameInformation = null;
                        if (scientificNameInformations != null && scientificNameInformations.size() > 0) {
                            scientificNameInformation = scientificNameInformations.get(0);
                        } else {
                            // create a new scientific name information entry
                            scientificNameInformation = new TblScientificNameInformation();
                            scientificNameInformation.setScientificNameId(scientificNameId);
                            em.merge(scientificNameInformation);

                            LOGGER.log(Level.INFO, "No scientific name information found for id ''{0}''. Added new entry.", scientificNameId);
                        }

                        // create cultivar entry and save it
                        cultivar = new TblCultivar();
                        cultivar.setCultivar(importRecord.getCultivar());
                        cultivar.setScientificNameId(scientificNameInformation);
                        em.persist(cultivar);

                        LOGGER.log(Level.INFO, "No cultivar entry found for ''{0}''. Added new entry.", importRecord.getCultivar());
                    }
                }

                // setup the botanical object
                botanicalObject = new TblBotanicalObject();
                botanicalObject.setAcquisitionEventId(acquisitionEvent);
                botanicalObject.setRecordingDate(new Date());
                botanicalObject.setAnnotation(importRecord.getGenericAnnotation());
                botanicalObject.setScientificNameId(scientificNameId);
                if (importRecord.getIdentStatus() != null) {
                    botanicalObject.setIdentStatusId(em.find(TblIdentStatus.class, importRecord.getIdentStatus()));
                }
                em.persist(botanicalObject);
            }

            // create empty acquisition date entry, view editing requires it to be set
            TblAcquisitionDate incomingDate = new TblAcquisitionDate();
            em.persist(incomingDate);

            // lookup the organization by name
            // check if organization entry is a number, if yes we assume it is an id which we use directly
            TblOrganisation organisation = null;
            try {
                organisation = em.find(TblOrganisation.class, Long.valueOf(importRecord.getOrganization()));
            } catch (NumberFormatException e) {
            }

            // if no organisation was found or no id was used, try to find by name
            if (organisation == null) {
                TypedQuery<TblOrganisation> organisationQuery = em.createNamedQuery("TblOrganisation.findByDescription", TblOrganisation.class);
                organisationQuery.setParameter("description", importRecord.getOrganization());
                List<TblOrganisation> organisations = organisationQuery.getResultList();
                if (organisations.size() <= 0) {
                    throw new IllegalArgumentException("Unable to find organisation: '" + importRecord.getOrganization() + "'");
                }
                organisation = organisations.get(0);
            }

            // create derivative entry
            TblDerivative derivative = new TblDerivative();
            derivative.setBotanicalObjectId(botanicalObject);
            derivative.setOrganisationId(organisation);
            derivative.setCount(importRecord.getCount());
            derivative.setPrice((importRecord.getPrice() != null) ? importRecord.getPrice() : 0L);
            derivative.setDerivativeTypeId(em.find(TblDerivativeType.class, 1L));
            em.persist(derivative);

            // setup living plant object
            TblLivingPlant livingPlant = new TblLivingPlant();
            livingPlant.setId(derivative.getDerivativeId());
            livingPlant.setLabelAnnotation(importRecord.getLabelAnnotation());
            livingPlant.setIncomingDateId(incomingDate);
            livingPlant.setCultureNotes(importRecord.getCultureNotes());
            livingPlant.setPlaceNumber(importRecord.getPlaceNumber());
            livingPlant.setCultivarId(cultivar);
            // check for valid ipen, if not cast a new one
            if (!StringUtils.isEmpty(importRecord.getIpenNumber())) {
                livingPlant.setIpenType("custom");
                livingPlant.setIpenNumber(importRecord.getIpenNumber());
            } else {
                livingPlant.setIpenType("default");
                livingPlant.setIpenNumber(String.format("%s-%s-%s", locationResults.size() > 0 ? locationResults.get(0).getCountryCode() : "XX", "0", organisationService.getIpenCode(organisation.getId())));
            }

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
                separation.setDerivativeId(derivative);
                separation.setSeparationTypeId(separationType);
                separation.setDate(importRecord.getSeparationDate());
                separation.setAnnotation(importRecord.getSeparationAnnotation());
                em.persist(separation);

                // update botanical object to be separated
                botanicalObject.setSeparated(true);
                em.persist(botanicalObject);
            }

            // persist the import properties
            TblImportProperties importProperties = new TblImportProperties();
            importProperties.setDerivativeId(derivative);
            importProperties.setIDPflanze(importRecord.getOriginalId());
            importProperties.setSpeciesName(importRecord.getScientificName());
            importProperties.setSourceName(importRecord.getSourceName());
            importProperties.setOriginalBotanicalObjectId(importRecord.getOriginalBotanicalObjectId());
            em.persist(importProperties);
        }

        return ImportStatus.INSERTED;
    }
}
