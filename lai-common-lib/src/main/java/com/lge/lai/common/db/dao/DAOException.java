package com.lge.lai.common.db.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DAOException extends RuntimeException {
    static Logger LOGGER = LogManager.getLogger(DAOException.class.getName());
    private static final long serialVersionUID = 1L;

    public DAOException(String message) {
        super(message);
        LOGGER.error(message);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
        LOGGER.error(message);
    }
}
