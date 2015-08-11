package com.lge.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DAOCallback {
    public Object convertToDTO(ResultSet rs) throws SQLException;
}
