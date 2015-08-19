package com.lge.lai.manifest.parser;

import java.io.File;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class MainTest {
    static final String OUTPUT = "test";

    @Before
    public void setUp() {
        String filepath = "./" + OUTPUT + ".xls";
        File f = new File(filepath);
        if (f.exists()) {
            f.delete();
        }
    }

    @Test
    @Ignore
    public void test() {
        String[] args = new String[] { "-m", "./src/test/resources/AndroidManifest.xml", "-o", OUTPUT };
        Main.main(args);
    }
}
