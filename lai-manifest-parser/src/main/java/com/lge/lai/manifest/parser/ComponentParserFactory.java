package com.lge.lai.manifest.parser;

import org.w3c.dom.Element;

public class ComponentParserFactory {
    public static ComponentParser create(String type, Element element, ExcelWriter writer) {
        if (type.equals(Manifest.PROVIDER)) {
            return new ProviderParser(element, writer);
        } else if (type.equals(Manifest.ACTIVITY)
                || type.equals(Manifest.ACTIVITY_ALIAS)
                || type.equals(Manifest.SERVICE)
                || type.equals(Manifest.RECEIVER)) {
            return new ASBParser(element, writer);
        } else {
            throw new RuntimeException("Unknown type");
        }
    }
}
