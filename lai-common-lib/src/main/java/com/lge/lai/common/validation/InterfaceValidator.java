package com.lge.lai.common.validation;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import android.content.ComponentName;
import android.content.Intent;

import com.lge.lai.common.annotation.InterfaceWith;
import com.lge.lai.common.annotation.InterfaceWith.Call;
import com.lge.lai.common.annotation.InterfaceWith.Component;
import com.lge.lai.common.db.dao.DAOFactory;

public class InterfaceValidator {
    static Logger LOGGER = LogManager.getLogger(InterfaceValidator.class.getName());
    private static final String SPECIFIC_KEY = "LGAppIF.db";

    private DAOFactory daoFactory;
    private Class<?> clazz;

    public InterfaceValidator(Class<?> clazz) {
        this.clazz = clazz;
        daoFactory = DAOFactory.getInstance(SPECIFIC_KEY);
    }

    public boolean validates(Intent intent) {
        String caller = getCallerMethodName();
        LOGGER.info("caller method: " + caller);
        return true;
    }

    public boolean validates(String caller, Intent intent) {
        Method m = null;
        try {
            m = clazz.getDeclaredMethod(caller);
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }

        InterfaceWith interfaceWith = m.getAnnotation(InterfaceWith.class);
        return validates(interfaceWith.toPackage(), interfaceWith.callType(), interfaceWith.componentType(), intent);
    }

    public boolean validates(String toPackage, Call callType, Component componentType, Intent intent) {
        if (callType == Call.EXPLICIT) {
            return false;
        } else {
            return false;
        }
    }
    
    private boolean processExplicitCall(CallerInfo callerInfo, Intent intent) {
        ComponentName componentName = intent.getComponent();
        String packageName = componentName.getPackageName();
        String className = componentName.getClassName();
        return false;
    }

    private String getCallerMethodName() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        StackTraceElement e = stackTraceElements[3];
        return e.getMethodName();
    }
}
