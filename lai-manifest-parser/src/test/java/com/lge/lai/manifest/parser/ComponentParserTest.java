package com.lge.lai.manifest.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ComponentParserTest {
    public Node node;

    public ComponentParserTest(String component, String uri) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document doc = null;

        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(uri);
        } catch (Exception e) {
            e.printStackTrace();
            fail("setup initialize error");
        }

        doc.getDocumentElement().normalize();
        NodeList nodes = doc.getDocumentElement().getElementsByTagName(component);
        assertEquals(1, nodes.getLength());
        node = nodes.item(0);
    }
}
