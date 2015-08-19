package com.lge.lai.manifest.parser;

import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.android.org.chromium.com.google.common.collect.Lists;
import com.lge.lai.common.data.Feature;

public class ASBParser implements ComponentParser {
    private Element component;
    private ExcelWriter writer;
    private boolean hasIntentFilter;
    private List<Feature> features = Lists.newArrayList();

    public ASBParser(Element component, ExcelWriter writer) {
        this.component = component;
        this.writer = writer;
        if (component != null) {
            this.hasIntentFilter = component.getElementsByTagName(Manifest.INTENT_FILTER).getLength() > 0;
        }
    }

    @Override
    public boolean validates() {
        if (hasExceptionTag(component)) {
            return false;
        }

        String exportedValue = component.getAttribute(Manifest.EXPORTED);
        if (hasIntentFilter && exportedValue.equals("false")) {
            return false;
        } else if (!hasIntentFilter && !exportedValue.equals("true")) {
            return false;
        }

        return true;
    }

    @Override
    public void parse() {
        if (hasIntentFilter) {
            NodeList actionNodeList = component.getElementsByTagName(Manifest.ACTION);
            for (int i = 0; i < actionNodeList.getLength(); i++) {
                Feature feature = new Feature();
                parseComponent(feature, component);

                Node actionNode = actionNodeList.item(i);
                parseAction(feature, (Element)actionNode);

                Node filterNode = actionNode.getParentNode();
                parseIntentFilter(feature, (Element)filterNode);

                features.add(feature);
                writer.setData(component.getNodeName(), feature);
            }
        } else {
            Feature feature = new Feature();
            parseComponent(feature, component);
            features.add(feature);
            writer.setData(component.getNodeName(), feature);
        }
    }

    private void parseComponent(Feature feature, Element element) {
        feature.type = element.getNodeName();
        feature.className = element.getAttribute(Manifest.NAME);
    }

    private void parseAction(Feature feature, Element element) {
        feature.actionName = element.getAttribute(Manifest.NAME);
    }

    private void parseIntentFilter(Feature feature, Element element) {
        NodeList categoryNodeList = element.getElementsByTagName(Manifest.CATEGORY);
        for (int i = 0; i < categoryNodeList.getLength(); i++) {
            feature.addCategory(((Element)categoryNodeList.item(i)).getAttribute(Manifest.NAME));
        }

        NodeList dataNodeList = element.getElementsByTagName(Manifest.DATA);
        for (int i = 0; i < dataNodeList.getLength(); i++) {
            parseData(feature, (Element)dataNodeList.item(i));
        }
    }

    private void parseData(Feature feature, Element element) {
        if (element.hasAttribute(Manifest.SCHEME)) {
            feature.addScheme(element.getAttribute(Manifest.SCHEME));
        }
        if (element.hasAttribute(Manifest.HOST)) {
            feature.addHost(element.getAttribute(Manifest.HOST));
        }
        if (element.hasAttribute(Manifest.PORT)) {
            feature.addPort(element.getAttribute(Manifest.PORT));
        }
        if (element.hasAttribute(Manifest.PATH)) {
            feature.addPath(element.getAttribute(Manifest.PATH));
        }
        if (element.hasAttribute(Manifest.PATH_PATTERN)) {
            feature.addPathPattern(element.getAttribute(Manifest.PATH_PATTERN));
        }
        if (element.hasAttribute(Manifest.PATH_PREFIX)) {
            feature.addPathPrefix(element.getAttribute(Manifest.PATH_PREFIX));
        }
        if (element.hasAttribute(Manifest.MIME_TYPE)) {
            feature.addMimeType(element.getAttribute(Manifest.MIME_TYPE));
        }
    }

    @Override
    public List<Feature> getFeatures() {
        return features;
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
