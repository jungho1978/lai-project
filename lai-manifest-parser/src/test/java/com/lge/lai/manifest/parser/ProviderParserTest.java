package com.lge.lai.manifest.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Test;
import org.w3c.dom.Element;

import com.lge.lai.common.data.Feature;

public class ProviderParserTest extends ComponentParserTest {

    public ProviderParserTest() {
        super(Manifest.PROVIDER, "./src/test/resources/FeatureParser.xml");
    }

    @Test
    public void getFeautres() {
        ProviderParser parser = new ProviderParser((Element)node, mock(ExcelWriter.class));
        assertTrue(parser.validates());

        parser.parse();
        List<Feature> features = parser.getFeatures();
        assertEquals(1, features.size());

        Feature expected = getExpectedFeature();
        assertEquals(expected, features.get(0));
    }

    private Feature getExpectedFeature() {
        Feature feature = new Feature();
        feature.type = Manifest.PROVIDER;
        feature.className = "com.example.hellworld";
        feature.authorities = "com.example.hellworld.Provider";
        feature.readPermission = "android.permission.READ";
        feature.writePermission = "android.permission.WRITE";
        return feature;
    }
}
