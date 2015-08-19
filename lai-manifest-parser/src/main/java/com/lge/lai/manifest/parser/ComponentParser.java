package com.lge.lai.manifest.parser;

import java.util.List;

import com.lge.lai.common.data.Feature;

public interface ComponentParser {
    public boolean validates();

    public void parse();

    public List<Feature> getFeatures();
}
