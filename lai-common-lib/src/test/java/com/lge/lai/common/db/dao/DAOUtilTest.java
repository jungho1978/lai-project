package com.lge.lai.common.db.dao;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.lge.lai.common.db.dao.DAOException;
import com.lge.lai.common.db.dao.DAOFactory;
import com.lge.lai.common.db.dao.DAOUtil;

public class DAOUtilTest {
    private static final String SPECIFIC_KEY = "LGAppIF.test.db";
    private Connection connection;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws SQLException {
        DAOFactory factory = DAOFactory.getInstance(SPECIFIC_KEY);
        connection = factory.getConnection();
    }

    @Test
    public void prepareStatment() throws SQLException {
        String sql = "SELECT * FROM LGAppIF.asb WHERE _id = ? AND _pkg_name = ?";
        Object[] values = { "1234", "com.example.helloworld" };
        PreparedStatement statment = DAOUtil.prepareStatement(connection, sql, false, values);
        assertTrue(statment.toString().endsWith("SELECT * FROM LGAppIF.asb WHERE _id = '1234' AND _pkg_name = 'com.example.helloworld'"));
    }

    @Test
    public void close_catchSQLException() throws Exception {
        exception.expect(DAOException.class);
        ResultSet mockRS = mock(ResultSet.class);
        doThrow(new SQLException()).when(mockRS).close();
        DAOUtil.close(mockRS, null, null);
    }
}
