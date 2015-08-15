package com.lge.lai.common.db.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.lge.lai.common.db.dao.DAOException;
import com.lge.lai.common.db.dao.DAOFactory;

public class DAOFactoryTest {
    private static final String SPECIFIC_KEY = "LGAppIF.test.db";

    private DAOFactory factory;

    @Before
    public void setUp() {
        factory = DAOFactory.getInstance(SPECIFIC_KEY);
    }

    @Test
    public void getInstance() throws SQLException {
        assertNotNull(factory.getConnection());
    }

    @Test(expected = DAOException.class)
    public void getInstance_FailureCase() throws SQLException {
        DAOFactory.getInstance(null);
    }

    @Test
    public void checkConnectionParams() {
        assertEquals("jdbc:mysql://905205.iptime.org", factory.getUrl());
        assertEquals("guest", factory.getUsername());
        assertEquals("guest", factory.getPassword());
    }
}
