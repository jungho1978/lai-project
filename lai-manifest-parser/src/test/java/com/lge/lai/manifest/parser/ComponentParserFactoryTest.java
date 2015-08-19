package com.lge.lai.manifest.parser;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ComponentParserFactoryTest {
    private String type;
    private Class<?> clazz;
    
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { 
                { "activity", ASBParser.class },
                { "service", ASBParser.class },
                { "receiver", ASBParser.class },
                { "provider", ProviderParser.class }
        });
    }

    public ComponentParserFactoryTest(String type, Class<?> clazz) {
        this.type = type;
        this.clazz = clazz;
    }

    @Test
    public void getConcreteClass() {
        ComponentParser parser = ComponentParserFactory.create(this.type, null, null);
        assertEquals(this.clazz, parser.getClass());
    }
}
