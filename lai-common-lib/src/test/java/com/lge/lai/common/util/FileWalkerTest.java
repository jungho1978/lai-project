package com.lge.lai.common.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class FileWalkerTest {

    @Test
    public void checkAPI() {
        FileWalker walker = new FileWalker(".", new String[] { "java" });
        assertTrue(walker.getDirCount() > 1);
        assertTrue(walker.getFileCount() > 1);
        assertTrue(walker.getFilePathArray().length > 1);
    }
}
