package DomParserDemo.modifyXml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class ModifyXmlDemo {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        File file = new File("src/DomParserDemo/ModifyXml/student.xml");
        Document document = builder.parse(file);

        Element root = document.getDocumentElement();
        NodeList nodeList = root.getChildNodes();

        int id = 0;
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                NodeList studentNodeList = node.getChildNodes();
                node.getAttributes().getNamedItem("rollno").setTextContent(String.valueOf((300 + ++id)));
                for (int j = 0; j < studentNodeList.getLength(); j++) {
                    Node studentNode = studentNodeList.item(j);
                    if (studentNode.getNodeType() == Node.ELEMENT_NODE) {
                        if (studentNode.getNodeName().equals("marks")) {
                            Attr attr = document.createAttribute("subject");
                            attr.setValue("all");
                            ((Element) studentNode).setAttributeNode(attr);
                        } else if (studentNode.getNodeName().equals("firstname")) {
                            Attr attr = document.createAttribute("id");
                            attr.setValue("ST" + id);
                            ((Element) studentNode).setAttributeNode(attr);
                        }
                    }
                }
            }
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);

        StreamResult result = new StreamResult(new File("src/DomParserDemo/ModifyXml/student.xml"));
        transformer.transform(source, result);

        result = new StreamResult(System.out);
        transformer.transform(source, result);
    }
}
