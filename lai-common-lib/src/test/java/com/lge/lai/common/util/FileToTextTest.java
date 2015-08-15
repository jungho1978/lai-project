package com.lge.lai.common.util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

public class FileToTextTest {
    private static final String SUT = "./src/main/java/com/lge/lai/common/util/FileToText.java";

    @Test
    public void checkAPI() throws IOException {
        FileToText file2txt = new FileToText(new File(SUT));
        assertTrue(file2txt.get().length() > 0);
    }
}
