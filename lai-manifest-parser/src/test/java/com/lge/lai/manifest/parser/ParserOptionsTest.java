package com.lge.lai.manifest.parser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ParserOptionsTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    ParserOptions options;

    @Test
    public void hasOption() {
        String[] args = new String[] { "-m", "./src/test/resources/AndroidManifest.xml", "-o", "output", "-u" };
        options = new ParserOptions(args);
        assertTrue(options.hasOption("m"));
        assertTrue(options.hasOption("o"));
        assertTrue(options.hasOption("u"));
    }
    
    @Test
    public void checkException_noGivingMandatoryOption() {
        exception.expect(RuntimeException.class);
        
        String[] args = new String[] { "-o", "output", "-u" };
        options = new ParserOptions(args);
    }
}
