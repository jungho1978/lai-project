package com.lge.common.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.lge.common.dto.ASB;

public class ASBDAOTest {
    private static final String SPECIFIC_KEY = "LGAppIF.db";

    DAOFactory daoFactory;

    @Before public void setUp() {
        daoFactory = DAOFactory.getInstance(SPECIFIC_KEY);
    }

    @Test public void allOperations() {
        String versionName = "1.0";
        String type = "activity";
        String desc = "description";
        String packageName = "com.example.helloworld";
        String className = packageName + ".MainActivity";
        String actionName = "android.intent.action.GET_CONTENT";
        String updatedBy = "manifest";
        ASB asb = new ASB(versionName, type, desc, packageName, className, actionName, updatedBy);

        ASBDAO asbDAO = daoFactory.getASBDAO();
        long asbId = asbDAO.create(asb);
        if (asbId == -1) {
            fail("duplicated entry exists");
        }
        ASB insertedRow = (ASB)asbDAO.find(asbId);
        if (!insertedRow.equals(asb)) {
            fail("created object is not same with expected object");
        }
        asbDAO.delete(asbId);
    }
}
