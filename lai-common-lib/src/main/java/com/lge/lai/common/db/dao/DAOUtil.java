package com.lge.lai.common.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DAOUtil {
    static Logger LOGGER = LogManager.getLogger(DAOUtil.class.getName());

    public static PreparedStatement prepareStatement(Connection connection, String sql, boolean returnGeneratedKeys,
            Object... values) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql,
                returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
        setValues(statement, values);
        return statement;
    }

    private static void setValues(PreparedStatement statement, Object... values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            statement.setObject(i + 1, values[i]);
        }
    }

    public static Timestamp toSqlTimestamp() {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        return new Timestamp(now.getTime());
    }

    public static void close(ResultSet rs, Statement statement, Connection connection) throws DAOException {
        try {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DAOException("SQLException 'Message: " + e.getMessage() + "''Code: " + e.getErrorCode() + "'");
        }
    }
}
