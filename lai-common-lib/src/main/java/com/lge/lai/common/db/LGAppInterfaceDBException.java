package com.lge.lai.common.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LGAppInterfaceDBException extends RuntimeException {
    static Logger LOGGER = LogManager.getLogger(LGAppInterfaceDBException.class.getName());
    private static final long serialVersionUID = 1L;

    public LGAppInterfaceDBException(String message) {
        super(message);
        LOGGER.error(message);
    }

    public LGAppInterfaceDBException(Throwable cause) {
        super(cause);
    }

    public LGAppInterfaceDBException(String message, Throwable cause) {
        super(message, cause);
        LOGGER.error(message);
    }
}
