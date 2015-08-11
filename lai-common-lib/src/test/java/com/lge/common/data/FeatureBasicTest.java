package com.lge.common.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FeatureBasicTest {
    Feature feature;

    @Before public void setUp() {
        feature = new Feature();
    }

    @Test public void checkInstanceNotNull() {
        assertNotNull(feature);
    }

    @Test public void getCategroies() {
        feature.addCategory("android.intent.category.DEFAULT");
        feature.addCategory("android.intent.category.MAIN");
        String[] expected = new String[] { "android.intent.category.DEFAULT",
                "android.intent.category.MAIN" };
        assertArrayEquals(expected, feature.getCategories().split("\n"));
    }

    @Test public void getSchemes() {
        feature.addScheme("http");
        feature.addScheme("file");
        String[] expected = new String[] { "http", "file" };
        assertArrayEquals(expected, feature.getSchemes().split("\n"));
    }

    @Test public void getHosts() {
        feature.addHost("www.google.com");
        feature.addHost("www.lge.com");
        String[] expected = new String[] { "www.google.com", "www.lge.com" };
        assertArrayEquals(expected, feature.getHosts().split("\n"));
    }

    @Test public void getPorts() {
        feature.addPort("1111");
        feature.addPort("2222");
        String[] expected = new String[] { "1111", "2222" };
        assertArrayEquals(expected, feature.getPorts().split("\n"));
    }

    @Test public void getPaths() {
        feature.addPath("/path1");
        feature.addPath("/path2");
        String[] expected = new String[] { "/path1", "/path2" };
        assertArrayEquals(expected, feature.getPaths().split("\n"));
    }

    @Test public void getPathPatterns() {
        feature.addPathPattern("/store/apps/details?id=.*");
        feature.addPathPattern("/video/.*/detail");
        String[] expected = new String[] { "/store/apps/details?id=.*", "/video/.*/detail" };
        assertArrayEquals(expected, feature.getPathPatterns().split("\n"));
    }

    @Test public void getPathPrefixes() {
        feature.addPathPrefix("/pathprefix1");
        feature.addPathPrefix("/pathprefix2");
        String[] expected = new String[] { "/pathprefix1", "/pathprefix2" };
        assertArrayEquals(expected, feature.getPathPrefixes().split("\n"));
    }

    @Test public void getMimeTypes() {
        feature.addMimeType("image/*");
        feature.addMimeType("audio/*");
        String[] expected = new String[] { "image/*", "audio/*" };
        assertArrayEquals(expected, feature.getMimeTypes().split("\n"));
    }
}
