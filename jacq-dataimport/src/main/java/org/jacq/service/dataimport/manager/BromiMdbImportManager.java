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
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.jacq.common.model.dataimport.ImportFile;
import org.jacq.common.model.dataimport.ImportRecord;
import org.jacq.common.rest.dataimport.DataImportService;

/**
 *
 * @author wkoller
 */
@ManagedBean
public class BromiMdbImportManager {

    private static final Logger LOGGER = Logger.getLogger(BromiMdbImportManager.class.getName());

    @Inject
    protected DataImportManager dataImportManager;

    /**
     * Reads the bromi mdb file and imports it into the system
     *
     * @see DataImportService#importBromiMdb()
     */
    public void importBromiMdb() throws IOException, ParseException {
        int inserted = 0;
        int updated = 0;

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

        // generate importfile object for referencing in the import-record object
        ImportFile importFile = new ImportFile();
        importFile.setFileName("bromi.mdb");
        // as we handle the file directly, we can leave the file content empty
        importFile.setFileSize(0L);
        importFile.setFileContent("");

        // open access file for processing
        Database db = DatabaseBuilder.open(new File("/tmp/bromi.mdb"));
        Table table = db.getTable("Tabelle");
        int rowCount = table.getRowCount();
        int counter = 0;

        // iterate over rows and handle them
        for (Row row : table) {
            ImportRecord importRecord = new ImportRecord();
            importRecord.setImportFile(importFile);

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
            String livingPlantNumber = row.getString("Garten-Nr");
            importRecord.setLivingPlantNumber(livingPlantNumber);
            // alternative living plant-number, if garten-nr does not start with a 'B'
            if (!StringUtils.isEmpty(livingPlantNumber)) {
                if (livingPlantNumber.startsWith("B")) {
                    importRecord.setAlternativeLivingPlantNumber(livingPlantNumber.replaceFirst("B", ""));
                }
                else {
                    importRecord.setAlternativeLivingPlantNumber("B" + livingPlantNumber);
                }
            }

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

            // limit matches to Bromeliaceae
            importRecord.setMatchFamily("Bromeliaceae");

            // set ipen to default one
            importRecord.setIpenNumber("XX-1-WU");

            // finally run the import
            DataImportManager.ImportStatus importStatus = dataImportManager.importRecord(importRecord);
            if (importStatus == DataImportManager.ImportStatus.INSERTED) {
                inserted++;
            }
            else {
                updated++;
            }

            counter++;

            LOGGER.log(Level.INFO, "Processed entry {0} of {1}", new Object[]{counter, rowCount});
        }

        LOGGER.log(Level.INFO, "Import finished: imported / updated: {0} / {1}", new Object[]{inserted, updated});
    }

}
