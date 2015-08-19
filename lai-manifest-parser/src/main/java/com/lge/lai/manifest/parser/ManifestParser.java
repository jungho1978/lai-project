package com.lge.lai.manifest.parser;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.android.org.chromium.com.google.common.collect.Lists;
import com.lge.lai.common.data.Feature;
import com.lge.lai.common.data.FeatureProvider;
import com.lge.lai.common.db.LGAppInterfaceDB;

public class ManifestParser {
    static Logger LOGGER = LogManager.getLogger(ManifestParser.class.getName());

    private ExcelWriter writer;
    private boolean upload;

    public ManifestParser(ExcelWriter writer, boolean upload) {
        this.writer = writer;
        this.upload = upload;
    }

    public void parse(String path) {
        FeatureProvider fp = new FeatureProvider();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document doc = null;

        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(path);
        } catch (Exception e) {
            throw new RuntimeException();
        }

        doc.getDocumentElement().normalize();
        Element manifest = doc.getDocumentElement();
        if (manifest.getNodeName().equals(Manifest.MANIFEST)) {
            parseManifest(fp, manifest);
            fp.addAllFeatures(getFeatures(manifest, Manifest.ACTIVITY));
            fp.addAllFeatures(getFeatures(manifest, Manifest.ACTIVITY_ALIAS));
            fp.addAllFeatures(getFeatures(manifest, Manifest.SERVICE));
            fp.addAllFeatures(getFeatures(manifest, Manifest.RECEIVER));
            fp.addAllFeatures(getFeatures(manifest, Manifest.PROVIDER));
            System.out.println(fp);
        }

        if (upload) {
            writeDatabase(fp, path);
        }
    }

    private List<Feature> getFeatures(Element manifest, String component) {
        List<Feature> features = Lists.newArrayList();
        NodeList nodeList = manifest.getElementsByTagName(component);
        writer.addCategory(component);
        for (int i = 0; i < nodeList.getLength(); i++) {
            ComponentParser parser = ComponentParserFactory.create(component, (Element)nodeList.item(i), writer);
            if (parser.validates()) {
                parser.parse();
                features.addAll(parser.getFeatures());
            }
        }
        return features;
    }

    private void parseManifest(FeatureProvider fp, Element element) {
        fp.packageName = element.getAttribute(Manifest.PACKAGE);
        fp.versionName = element.getAttribute(Manifest.VERSION);
    }

    private void writeDatabase(FeatureProvider newProvider, String manifestPath) {
        LGAppInterfaceDB.find(newProvider.packageName, newProvider.versionName);
        LGAppInterfaceDB.delete(newProvider.packageName, newProvider.versionName);
        LGAppInterfaceDB.write(newProvider, manifestPath);
    }
}
