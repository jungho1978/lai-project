package com.lge.lai.common.db;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.lge.lai.common.data.Feature;
import com.lge.lai.common.data.FeatureProvider;

public class LGAppInterfaceDBTest {
    static final String SPECIFIC_KEY = "LGAppIF.test.db";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        LGAppInterfaceDB.reloadDAOFactory(SPECIFIC_KEY);
    }

    @Test
    public void checkThrowException() {
        exception.expect(LGAppInterfaceDBException.class);
        LGAppInterfaceDB.write(null);
    }

    @Test
    public void allOperations() {
        FeatureProvider fp = getDummyData();
        try {
            LGAppInterfaceDB.write(fp);
            FeatureProvider insertedFp = LGAppInterfaceDB.find(fp.packageName, fp.versionName);
            if (!insertedFp.equals(fp)) {
                fail("created object is not same with expected object");
            }
        } catch (LGAppInterfaceDBException e) {
            fail("LGAppIF DB operation exception occrred");
        } finally {
            LGAppInterfaceDB.delete(fp.packageName, fp.versionName);
        }
    }

    private static FeatureProvider getDummyData() {
        FeatureProvider fp = new FeatureProvider();
        fp.packageName = "com.example.helloworld";
        fp.versionName = "1.0.0";

        Feature asbFeature = new Feature();
        asbFeature.type = "activity";
        asbFeature.className = fp.packageName + ".MainActivity";
        asbFeature.actionName = "android.intent.ACTION.GET_CONTENT";
        asbFeature.addCategory("android.intent.CATEGORY.MAIN");
        asbFeature.addCategory("android.intent.CATEGORY.DEFAULT");
        asbFeature.addScheme("http");
        asbFeature.addScheme("file");
        asbFeature.addMimeType("image/*");
        asbFeature.addMimeType("audio/*");
        fp.addFeature(asbFeature);

        Feature providerFeature = new Feature();
        providerFeature.type = "provider";
        providerFeature.className = fp.packageName + ".MainProvider";
        providerFeature.readPermission = "android.permission.READ";
        providerFeature.writePermission = "android.permission.WRITE";
        providerFeature.authorities = "hellworld.authrotieis";
        fp.addFeature(providerFeature);

        return fp;
    }
}
