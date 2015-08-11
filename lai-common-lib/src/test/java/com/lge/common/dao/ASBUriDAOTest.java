package com.lge.common.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lge.common.dto.ASB;
import com.lge.common.dto.ASBUri;

public class ASBUriDAOTest {
    private static final String SPECIFIC_KEY = "LGAppIF.db";

    DAOFactory daoFactory;
    ASB asb;
    long asbId;

    @Before public void setUp() {
        daoFactory = DAOFactory.getInstance(SPECIFIC_KEY);

        String versionName = "1.0";
        String type = "activity";
        String desc = "Description";
        String packageName = "com.example.helloworld";
        String className = packageName + ".MainActivity";
        String actionName = "android.intent.action.GET_CONTENT";
        String updatedBy = "manifest";
        asb = new ASB(versionName, type, desc, packageName, className, actionName, updatedBy);

        ASBDAO asbDAO = daoFactory.getASBDAO();
        asbId = asbDAO.create(asb);
        if (asbId == -1) {
            fail("duplicated entry exists");
        }
    }

    @After public void tearDown() {
        ASBDAO asbDAO = daoFactory.getASBDAO();
        asbDAO.delete(asbId);
    }

    @Test public void allOperations() {
        String uri = "https://www.google.com";
        String uriDesc = "URI description";
        ASBUri asbUri = new ASBUri(asb.versionName, asb.type, asb.desc, asb.packageName, asb.className, asb.actionName,
                uri, uriDesc, asb.updatedBy);

        ASBUriDAO asbUriDAO = daoFactory.getASBUriDAO(asbId);
        long asbUriId = asbUriDAO.create(asbUri);
        if (asbUriId == -1) {
            fail("duplicated entry exists");
        }
        ASBUri insertedRow = (ASBUri)asbUriDAO.find(asbUriId);
        if (!insertedRow.equals(asbUri)) {
            fail("created object is not same with expected object");
        }
        asbUriDAO.delete(asbUriId);
    }

}
