package com.lge.lai.common.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.lge.lai.common.data.FeatureProvider;

public class FeatureProviderBasicTest {
    FeatureProvider provider;

    @Before
    public void setUp() {
        provider = new FeatureProvider();
    }

    @Test
    public void checkInstanceNotNull() {
        assertNotNull(provider);
    }
}
