package com.lge.lai.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileToText {
    private File file;
    private String charset;
    private StringBuilder data = new StringBuilder();

    /**
     * Create FileToText with given file
     * 
     * @param file file to be converted to text
     * @throws IOException throws while file is being read
     */
    public FileToText(File file) throws IOException {
        this.file = file;
        this.charset = "UTF-8";
        convert();
    }

    /**
     * Create FileToText with given file and charset
     * 
     * @param file file to be coverted to text
     * @param charset encoding type
     * @throws IOException throws while file is being read
     */
    public FileToText(File file, String charset) throws IOException {
        this.file = file;
        this.charset = charset;
        convert();
    }

    private void convert() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
        String line = null;
        String ls = System.getProperty("line.separator");

        while ((line = reader.readLine()) != null) {
            data.append(line);
            data.append(ls);
        }

        reader.close();
    }

    /**
     * Get String encoded as given charset
     * 
     * @return String converted from given file
     */
    public String get() {
        return data.toString();
    }
}
