package com.lge.common.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.lge.common.dto.Provider;

public class ProviderDAOTest {
    private static final String SPECIFIC_KEY = "LGAppIF.db";

    DAOFactory daoFactory;

    @Before public void setUp() {
        daoFactory = DAOFactory.getInstance(SPECIFIC_KEY);
    }

    @Test public void allOperations() {
        String versionName = "1.0";
        String type = "provider";
        String desc = "description";
        String packageName = "com.example.helloworld";
        String className = packageName + ".MainProvider";
        String tableName = "table";
        String readPermission = "android.permission.READ";
        String writePermission = "android.permission.WRITE";
        String authorities = "helloworld";
        String primaryKey = "_id";
        String updatedBy = "manifest";
        Provider provider = new Provider(versionName, type, desc, packageName, className, tableName, readPermission,
                writePermission, authorities, primaryKey, updatedBy);

        ProviderDAO providerDAO = daoFactory.getProviderDAO();
        long providerId = providerDAO.create(provider);
        if (providerId == -1) {
            fail("duplicated entry exists");
        }
        Provider insertedRow = (Provider)providerDAO.find(providerId);
        if (!insertedRow.equals(provider)) {
            fail("created object is not same with expected object");
        }
        providerDAO.delete(providerId);
    }

}
