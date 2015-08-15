package com.lge.lai.common.db.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.lge.lai.common.db.dao.DAOException;
import com.lge.lai.common.db.dao.DAOProperties;

public class DAOPropertiesTest {
    private static final String SPECIFIC_KEY = "LGAppIF.test.db";
    private static final String URL = "url";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String UNKNOWN = "unknown";

    private DAOProperties properties;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        this.properties = new DAOProperties(SPECIFIC_KEY);
    }

    @Test
    public void getProperties_Url() {
        String url = properties.getProperty(URL, true);
        assertEquals("jdbc:mysql://905205.iptime.org", url);
    }

    @Test
    public void getProperties_Username() {
        String username = properties.getProperty(USERNAME, true);
        assertEquals("guest", username);
    }

    @Test
    public void getProperties_Password() {
        String password = properties.getProperty(PASSWORD, true);
        assertEquals("guest", password);
    }

    @Test
    public void getProperties_GivenKeyDoesNotExists_Optional() {
        String actual = properties.getProperty(UNKNOWN, false);
        assertEquals(null, actual);
    }

    @Test
    public void getProperties_GivenKeyDoesNotExists_Mandatory() {
        exception.expect(DAOException.class);
        properties.getProperty(UNKNOWN, true);
    }
}
