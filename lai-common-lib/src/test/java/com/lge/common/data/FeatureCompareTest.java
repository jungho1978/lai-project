package com.lge.common.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FeatureCompareTest {
    Feature feature1;
    Feature feature2;
    
    @Before
    public void setUp() {
        feature1 = new Feature();
        feature2 = new Feature();
    }

    @Test
    public void compareInitialObjects() {
        assertEquals(feature1, feature2);
    }
    
    @Test
    public void compareObjectsWithSimpleString() {
        feature1.actionName = "android.intent.ACTION.GET_CONTENT";
        feature2.actionName = "android.intent.ACTION.GET_CONTENT "; // it has white space at the end of string
        assertEquals(feature1, feature2);
    }
    
    @Test
    public void compareObjectsWithSimpleString_FailureCase() {
        feature1.actionName = "android.intent.ACTION.GET_CONTENT";
        feature2.actionName = null;
        assertNotEquals(feature1, feature2);
    }
    
    @Test
    public void compareObjectsWithSimpleStringList() {
        feature1.addCategory("android.intent.CATEGORY.DEFAULT");
        feature1.addCategory("android.intent.CATEGORY.MAIN");
        feature2.addCategory("android.intent.CATEGORY.DEFAULT ");   // it has white space at the end of string
        feature2.addCategory("android.intent.CATEGORY.MAIN");
        assertEquals(feature1, feature2);
    }
    
    @Test
    public void compareObjectsWithSimpleStringList_FailureCase() {
        feature1.addCategory("android.intent.CATEGORY.DEFAULT");
        feature2.addCategory("android.intent.CATEGORY.MAIN");
        assertNotEquals(feature1, feature2);
    }
}
