package com.lge.lai.manifest.parser;

import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.android.org.chromium.com.google.common.collect.Lists;
import com.lge.lai.common.data.Feature;

public class ProviderParser implements ComponentParser {
    private Element component;
    private ExcelWriter writer;
    private List<Feature> features = Lists.newArrayList();

    public ProviderParser(Element component, ExcelWriter writer) {
        this.component = component;
        this.writer = writer;
    }

    @Override
    public boolean validates() {
        if (hasExceptionTag(component)) {
            return false;
        }

        String exportedValue = component.getAttribute(Manifest.EXPORTED);
        if (!exportedValue.equals("true")) {
            return false;
        }

        return true;
    }

    @Override
    public void parse() {
        Feature feature = new Feature();
        feature.type = component.getNodeName();
        feature.className = component.getAttribute(Manifest.NAME);
        feature.authorities = component.getAttribute(Manifest.AUTHORITIES);
        feature.readPermission = component.getAttribute(Manifest.READ_PERMISSION);
        feature.writePermission = component.getAttribute(Manifest.WRITE_PERMISSION);
        features.add(feature);
        writer.setData(component.getNodeName(), feature);
    }

    @Override
    public List<Feature> getFeatures() {
        return this.features;
    }

    private boolean hasExceptionTag(Element element) {
        NodeList nodeList = element.getElementsByTagName(Manifest.META_DATA);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            String name = ((Element)node).getAttribute(Manifest.NAME);
            if (name.startsWith(Manifest.LGAPI_EXCEPTION)) {
                return true;
            }
        }
        return false;
    }

}
