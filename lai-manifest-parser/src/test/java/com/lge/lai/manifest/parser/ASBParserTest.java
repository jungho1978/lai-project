package com.lge.lai.manifest.parser;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.w3c.dom.Element;

import com.lge.lai.common.data.Feature;

@RunWith(Parameterized.class)
public class ASBParserTest extends ComponentParserTest {
    private Feature expectedFeature;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { Manifest.ACTIVITY, getExpectedActivityFeacture() },
                { Manifest.SERVICE, getExpectedServiceFeature() },
                { Manifest.RECEIVER, getExpectedReceiverFeature() }
        });
    }

    public ASBParserTest(String type, Feature feature) {
        super(type, "./src/test/resources/FeatureParser.xml");
        this.expectedFeature = feature;
    }

    @Test
    public void getFeature() {
        ASBParser parser = new ASBParser((Element)node, mock(ExcelWriter.class));
        assertTrue(parser.validates());

        parser.parse();
        List<Feature> features = parser.getFeatures();
        assertEquals(1, features.size());

        assertEquals(expectedFeature, features.get(0));
    }

    private static Feature getExpectedActivityFeacture() {
        Feature feature = new Feature();
        feature.type = Manifest.ACTIVITY;
        feature.className = "com.example.helloworld.Activity";
        feature.actionName = "android.intent.action.ACTIVITY";
        feature.addCategory("android.intent.category.ACTIVITY");
        return feature;
    }

    private static Feature getExpectedServiceFeature() {
        Feature feature = new Feature();
        feature.type = Manifest.SERVICE;
        feature.className = "com.example.helloworld.Service";
        feature.actionName = "android.intent.action.SERVICE";
        feature.addCategory("android.intent.category.SERVICE");
        return feature;
    }

    private static Feature getExpectedReceiverFeature() {
        Feature feature = new Feature();
        feature.type = Manifest.RECEIVER;
        feature.className = "com.example.helloworld.Receiver";
        feature.actionName = "android.intent.action.RECEIVER";
        feature.addCategory("android.intent.category.RECEIVER");
        return feature;
    }

}
