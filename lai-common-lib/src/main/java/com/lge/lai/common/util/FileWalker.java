package com.lge.lai.common.util;

import java.io.File;
import java.util.List;

import javax.sip.InvalidArgumentException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.android.org.chromium.com.google.common.collect.Lists;

public class FileWalker {
    static Logger LOGGER = LogManager.getLogger(FileWalker.class.getName());
    private String path;
    private String[] extensions;
    private List<String> filePaths = Lists.newArrayList();

    private int fileCount = 0;
    private int dirCount = 0;

    /**
     * Create a FileWalker with given path and extensions
     * 
     * @param path target directory path
     * @param extensions file extensions to search
     */
    public FileWalker(String path, String[] extensions) {
        this.path = path;
        this.extensions = extensions;
        resolve(this.path);
    }

    private void resolve(String path) {
        File root = new File(path);
        File[] list = root.listFiles();

        for (File f : list) {
            if (f.isDirectory()) {
                dirCount++;
                resolve(f.getAbsolutePath());
            } else {
                if (matchesWithExtension(f)) {
                    fileCount++;
                    filePaths.add(f.getAbsolutePath());
                }
            }
        }
    }

    private boolean matchesWithExtension(File file) {
        for (String ext : extensions) {
            if (file.getName().endsWith(ext)) {
                LOGGER.info("'" + ext + "' " + file.getAbsolutePath());
                return true;
            }
        }
        return false;
    }

    /**
     * Get the total number of files that has given extension
     * 
     * @return the total number of files that has given extension
     */
    public int getFileCount() {
        return fileCount;
    }

    /**
     * Get the total number of directories in given path
     * 
     * @return the total number of directories in given path
     */
    public int getDirCount() {
        return dirCount;
    }

    /**
     * Get file paths, which has given extension, as string array
     * 
     * @return file paths as string array
     */
    public String[] getFilePathArray() {
        return filePaths.toArray(new String[filePaths.size()]);
    }
}
