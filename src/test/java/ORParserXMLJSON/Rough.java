package ORParserXMLJSON;

import java.io.IOException;

import org.dom4j.DocumentException;

public class Rough {

	public static void main(String[] args) throws DocumentException, IOException {
		
		XMLParser p = new XMLParser();
		System.out.println(p.parser("//header.username.xpath"));
		
		System.out.println(JSONParsers.name("locators.homepage.username.xpath").toString());
	}

}
