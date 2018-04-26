/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.common.manager;

import java.util.HashMap;

/**
 * Global bean template for accessing the config parameters for jacq
 *
 * @author wkoller
 */
public abstract class JacqConfig {

    protected static final String CONFIG_SEPARATOR = ".";

    protected static final String BIRT = "birt";
    public static final String BIRT_WORK_LABEL = BIRT + CONFIG_SEPARATOR + "work_label";
    protected static final String TREERECORD = "treerecord";
    public static final String TREERECORD_PDF_PATH = TREERECORD + CONFIG_SEPARATOR + "pdf_path";
    protected static final String CLASSIFICATION = "classification";
    public static final String CLASSIFICATION_FAMILY_REFERENCE_ID = CLASSIFICATION + CONFIG_SEPARATOR + "familyReferenceId";
    protected static final String GENERAL = "general";
    public static final String GENERAL_INDET_ID = GENERAL + CONFIG_SEPARATOR + "indet_id";
    protected static final String SERVICE = "service";
    protected static final String SERVICE_GEONAMES = SERVICE + CONFIG_SEPARATOR + "geonames";
    public static final String SERVICE_GEONAMES_URL = SERVICE_GEONAMES + CONFIG_SEPARATOR + "url";
    public static final String SERVICE_GEONAMES_USERNAME = SERVICE_GEONAMES + CONFIG_SEPARATOR + "username";
    protected static final String DATABASE = "database";
    public static final String DATABASE_PRODUCTIVSYSTEM = DATABASE + CONFIG_SEPARATOR + "productivsystem";

    protected HashMap<String, String> config;

    protected String[] portalConfigKeys = {GENERAL_INDET_ID, DATABASE_PRODUCTIVSYSTEM};

    /**
     * Returns all settings which should be exposed to the portal
     *
     * @return
     */
    public HashMap<String, String> getPortalConfig() {
        HashMap<String, String> portalConfig = new HashMap<>();

        for (String configKey : portalConfigKeys) {
            portalConfig.put(configKey, this.getString(configKey));
        }

        return portalConfig;
    }

    /**
     * Return a config value as string
     *
     * @param name Name of configuration value to return
     * @return
     */
    public String getString(String name) {
        return config.get(name);
    }

    /**
     * Return a config value as Long, in case of a parse error returns 0
     *
     * @param name Name of configuration value to return
     * @return
     */
    public Long getLong(String name) {
        try {
            return Long.parseLong(config.get(name));
        } catch (NumberFormatException e) {
            return 0L;
        }
    }
}
