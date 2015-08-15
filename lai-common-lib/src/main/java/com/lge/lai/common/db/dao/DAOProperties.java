package com.lge.lai.common.db.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DAOProperties {
    private static final String PROPERTIES_FILE = "db.properties";
    private static final Properties PROPERTIES = new Properties();

    static {
        if (!(readPropertiesFromExternal() || readPropertiesFromResource())) {
            throw new DAOException("Cannot load properties file '" + PROPERTIES_FILE + "'");
        }
    }

    private static boolean readPropertiesFromExternal() {
        File file = new File(PROPERTIES_FILE);
        try {
            InputStream propertiesFile = new FileInputStream(file);
            PROPERTIES.load(propertiesFile);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private static boolean readPropertiesFromResource() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);
        try {
            PROPERTIES.load(propertiesFile);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private String specificKey;

    public DAOProperties(String specificKey) {
        this.specificKey = specificKey;
    }

    public String getProperty(String key, boolean mandatory) throws DAOException {
        String fullKey = specificKey + "." + key;
        String property = PROPERTIES.getProperty(fullKey);

        if (property == null || property.trim().length() == 0) {
            if (mandatory) {
                throw new DAOException("Required property '" + fullKey + "' is missing in properties file '" + PROPERTIES_FILE + "'.");
            } else {
                property = null;
            }
        }
        return property;
    }
}
