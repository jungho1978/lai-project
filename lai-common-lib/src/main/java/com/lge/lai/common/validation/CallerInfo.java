package com.lge.lai.common.validation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.MoreObjects;
import com.lge.lai.common.annotation.InterfaceWith.Call;
import com.lge.lai.common.annotation.InterfaceWith.Component;

public class CallerInfo {
    static Logger LOGGER = LogManager.getLogger(InterfaceValidator.class.getName());

    public String toPackage;
    public Call callType;
    public Component componentType;

    public CallerInfo(String toPackage, Call callType, Component componentType) {
        this.toPackage = toPackage;
        this.callType = callType;
        this.componentType = componentType;
        LOGGER.info("CallerInfo: " + this);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("toPackage", toPackage)
                .add("callType", callType)
                .add("componentType", componentType)
                .toString();
    }
}
