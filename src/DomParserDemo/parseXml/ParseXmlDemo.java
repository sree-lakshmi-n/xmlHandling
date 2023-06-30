package DomParserDemo.parseXml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ParseXmlDemo {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse(new File("src/DomParserDemo/ParseXml/employee.xml"));

        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());
        System.out.println("------------------------");
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                System.out.println("\nDepartment: " + node.getAttributes().item(0).getTextContent());
                System.out.println("ID: " + element.getElementsByTagName("name").item(0).getAttributes().item(0).getTextContent());
                System.out.println("Name: " + element.getElementsByTagName("name").item(0).getTextContent());
                System.out.println("Position: "+element.getElementsByTagName("position").item(0).getTextContent());
                System.out.println("Joining year: "+element.getElementsByTagName("joinyear").item(0).getTextContent());
                System.out.println("Salary: "+element.getElementsByTagName("salary").item(0).getTextContent());
            }
        }
    }
}
