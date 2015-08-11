package com.lge.common.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lge.common.dto.ASB;
import com.lge.common.dto.ASBCategory;

public class ASBCategoryDAOTest {
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
        String category = "android.intent.category.MAIN";
        ASBCategory asbCategory = new ASBCategory(asb.versionName, asb.type, asb.desc, asb.packageName, asb.className,
                asb.actionName, category, asb.updatedBy);

        ASBCategoryDAO asbCategoryDAO = daoFactory.getASBCategoryDAO(asbId);
        long asbCategoryId = asbCategoryDAO.create(asbCategory);
        if (asbCategoryId == -1) {
            fail("duplicated entry exists");
        }
        ASBCategory insertedRow = (ASBCategory)asbCategoryDAO.find(asbCategoryId);
        if (!insertedRow.equals(asbCategory)) {
            fail("created object is not same with expected object");
        }
        asbCategoryDAO.delete(asbCategoryId);
    }

}
