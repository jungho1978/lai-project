package com.lge.common.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.google.common.annotations.VisibleForTesting;

public abstract class DAOFactory {
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASSWORD = "password";

    private static final String DRIVER = "com.mysql.jdbc.Driver";

    public static DAOFactory getInstance(String name) throws DAOException {
        if (name == null) {
            throw new DAOException("Database name is null");
        }

        DAOProperties properties = new DAOProperties(name);
        String url = properties.getProperty(PROPERTY_URL, true);
        String username = properties.getProperty(PROPERTY_USERNAME, true);
        String password = properties.getProperty(PROPERTY_PASSWORD, true);

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new DAOException("Driver class '" + DRIVER + "' is missing.", e);
        }

        return new DriverManagerDAOFactory(url, username, password);
    }

    public ASBDAO getASBDAO() {
        return new ASBDAO(this);
    }

    public ASBCategoryDAO getASBCategoryDAO(long asbId) {
        return new ASBCategoryDAO(asbId, this);
    }

    public ASBMimeDAO getASBMimeDAO(long asbId) {
        return new ASBMimeDAO(asbId, this);
    }

    public ASBUriDAO getASBUriDAO(long asbId) {
        return new ASBUriDAO(asbId, this);
    }
    
    public ProviderDAO getProviderDAO() {
        return new ProviderDAO(this);
    }

    abstract Connection getConnection() throws SQLException;

    @VisibleForTesting abstract String getUrl();

    @VisibleForTesting abstract String getUsername();

    @VisibleForTesting abstract String getPassword();
}

class DriverManagerDAOFactory extends DAOFactory {
    private String url;
    private String username;
    private String password;

    public DriverManagerDAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    @Override public String getUrl() {
        return this.url;
    }

    @Override public String getUsername() {
        return this.username;
    }

    @Override public String getPassword() {
        return this.password;
    }
}
