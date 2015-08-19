package com.lge.lai.manifest.parser;

public class Main {
    static final String DEFAULT_OUPUT = "ManifestFeatureList";

    public static void main(String[] args) {
        ParserOptions options = new ParserOptions(args);

        String manifest = options.getOption("m");
        String output = options.hasOption("o") ? options.getOption("o") : DEFAULT_OUPUT;
        boolean upload = options.hasOption("u");

        ManifestParser parser = new ManifestParser(new ExcelWriter(output), upload);
        parser.parse(manifest);
    }
}
