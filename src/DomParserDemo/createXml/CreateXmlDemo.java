package DomParserDemo.createXml;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class CreateXmlDemo {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.newDocument();
        Element root = document.createElement("class");
        document.appendChild(root);

        String[] firstNames = {"Pavithr", "Gwen", "Miles"};
        String[] lastNames = {"Prabhakar", "Stacy", "Morales"};
        String[] nickNames = {"Pav", "Guen", "Miles"};
        int[] markList = {85, 95, 90};

        for (int i = 0; i < 3; i++) {
            Element student = document.createElement("student");
            Attr attr = document.createAttribute("rollno");
            attr.setValue(String.valueOf(i + 1));
            student.setAttributeNode(attr);

            Element firstName = document.createElement("firstName");
            firstName.appendChild(document.createTextNode(firstNames[i]));
            student.appendChild(firstName);
            Element lastName = document.createElement("lastName");
            lastName.appendChild(document.createTextNode(lastNames[i]));
            student.appendChild(lastName);
            Element nickName = document.createElement("nickName");
            nickName.appendChild(document.createTextNode(nickNames[i]));
            student.appendChild(nickName);
            Element marks = document.createElement("marks");
            marks.appendChild(document.createTextNode(String.valueOf(markList[i])));
            student.appendChild(marks);

            root.appendChild(student);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File("src/DomParserDemo/CreateXml/student.xml"));
        transformer.transform(source, result);

        result = new StreamResult(System.out);
        transformer.transform(source, result);
    }
}
