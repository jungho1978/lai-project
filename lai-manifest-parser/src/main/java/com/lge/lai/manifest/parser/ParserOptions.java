package com.lge.lai.manifest.parser;

import java.io.File;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class ParserOptions {
    static final String ANDROIDMANIFEST = "AndroidManifest.xml";
    
    CommandLine commandLine;

    public ParserOptions(String[] args) {
        Options options = new Options();
        options.addOption("m", "manifest", true, "AndroidManifest.xml file path");
        options.addOption("o", "output", true, "output file name");
        options.addOption("u", "upload", false, "upload result to database");

        try {
            commandLine = new DefaultParser().parse(options, args);
            if (!commandLine.hasOption("m")) {
                showHelp(options);
                throw new RuntimeException("mandatory option not included");
            } else {
                String manifest = commandLine.getOptionValue("m");
                if (!manifest.endsWith(ANDROIDMANIFEST)
                        || !new File(manifest).exists()) {
                    throw new RuntimeException("file not found '" + manifest + "'");
                }
            }
        } catch (ParseException e) {
            showHelp(options);
            throw new RuntimeException(e);
        }
    }

    public String getOption(String opt) {
        return commandLine.getOptionValue(opt);
    }

    public boolean hasOption(String opt) {
        return commandLine.hasOption(opt);
    }

    private void showHelp(Options opts) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setWidth(100);
        formatter.printHelp("java -jar lai-manifest-parser.java", opts);
    }
}
