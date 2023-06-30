package SaxParserDemo.parseXml;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
//
public class SAXParseXml {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        File file = new File("src/SaxParserDemo/parseXml/employee.xml");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        UserHandler userHandler = new UserHandler();
        parser.parse(file, userHandler);
    }
}

class UserHandler extends DefaultHandler {
    boolean bAge = false;
    boolean bName = false;
    boolean bGender = false;
    boolean bRole = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("employee")) {
            String id = attributes.getValue("id");
            System.out.println("\nID: " + id);
        } else if (qName.equalsIgnoreCase("age")) {
            bAge = true;
        } else if (qName.equalsIgnoreCase("name")) {
            bName = true;
        } else if (qName.equalsIgnoreCase("gender")) {
            bGender = true;
        } else if (qName.equalsIgnoreCase("role")) {
            bRole = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("employee")) {
            System.out.println("\nEnd element: " + qName);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (bAge) {
            System.out.println("Age: " + new String(ch, start, length));
            bAge = false;
        } else if (bName) {
            System.out.println("Name: " + new String(ch, start, length));
            bName = false;
        } else if (bGender) {
            System.out.println("Gender: " + new String(ch, start, length));
            bGender = false;
        } else if (bRole) {
            System.out.println("Role: " + new String(ch, start, length));
            bRole = false;
        }
    }
}
