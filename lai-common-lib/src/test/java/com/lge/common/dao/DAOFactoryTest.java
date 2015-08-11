package com.lge.common.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class DAOFactoryTest {
    private static final String SPECIFIC_KEY = "LGAppIF.db";

    private DAOFactory factory;

    @Before public void setUp() {
        factory = DAOFactory.getInstance(SPECIFIC_KEY);
    }

    @Test public void getInstance() throws SQLException {
        assertNotNull(factory.getConnection());
    }

    @Test(expected = DAOException.class) public void getInstance_FailureCase() throws SQLException {
        DAOFactory.getInstance(null);
    }

    @Test public void checkConnectionParams() {
        assertEquals("jdbc:mysql://10.168.142.78", factory.getUrl());
        assertEquals("ase", factory.getUsername());
        assertEquals("2014ase", factory.getPassword());
    }
}
