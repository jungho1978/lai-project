package com.lge.lai.common.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.lge.lai.common.data.Feature;
import com.lge.lai.common.data.FeatureProvider;

public class FeatureProviderCompareTest {
    FeatureProvider provider1;
    FeatureProvider provider2;

    @Before
    public void setUp() {
        provider1 = new FeatureProvider();
        provider2 = new FeatureProvider();
    }

    @Test
    public void compareObjectsWithoutFeatureList() {
        provider1.packageName = "com.example.helloworld";
        provider1.versionName = "1.0.0";
        provider2.packageName = "com.example.helloworld "; // it has white space at the end of string
        provider2.versionName = "1.0.0 "; // it has white space at the end of string
        assertEquals(provider1, provider2);
    }

    @Test
    public void compareObjectsWithoutFeatureList_FailureCase() {
        provider1.packageName = "com.example.helloworld";
        provider1.versionName = "1.0.0";
        provider2.packageName = "com.example.helloworld";
        provider2.versionName = null;
        assertNotEquals(provider1, provider2);
    }

    @Test
    public void compareObjectsWithFeatureList() {
        Feature feature = new Feature();
        feature.actionName = "android.intent.ACTION.GET_CONTENT";
        feature.addCategory("android.intent.CATEGORY.DEFAULT");
        feature.addMimeType("image/*");

        provider1.addFeature(feature);
        provider2.addFeature(feature);
        assertEquals(provider1, provider2);
    }

    @Test
    public void compareObjectsWithFeatureList_FailureCase() {
        Feature feature = new Feature();
        provider1.addFeature(feature);
        provider2.addFeature(null);
        assertNotEquals(provider1, provider2);
    }
}
