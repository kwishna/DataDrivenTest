package ORParserXMLJSON;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;

public class JSONParsers {
	
	public static Object name(String locator) throws IOException {
		
		Object o = JsonPath.read(new File(System.getProperty("user.dir")+"/src/test/resources/OR/OR.json"), "$."+locator);
		return o;
	}
}
