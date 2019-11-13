package ORParserXMLJSON;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class XMLParser {

	public String parser(String xPath) throws DocumentException {
	SAXReader sax = new SAXReader();
	Document doc = sax.read(System.getProperty("user.dir")+"/src/test/resources/OR/OR.xml");
	String s = doc.selectSingleNode(xPath.toLowerCase().replace(".", "//")).getText();
	return s;
	}
}
