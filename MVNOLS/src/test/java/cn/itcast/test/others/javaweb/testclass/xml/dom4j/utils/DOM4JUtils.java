package cn.itcast.test.others.javaweb.testclass.xml.dom4j.utils;

import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 	This is a DOM4J utility class to parse XML with SAX or DOM
 * @author Vince Xu Yuan
 */
public class DOM4JUtils {

	//--------------------------------------------------- DOM -----------------------------------------------------
	/**
	 * 	This is a static method to get the document object
	 * @param xmlPath
	 * @return
	 */
	public static Document getDocument (String xmlPath)  {	
		
		try {					
			/* Use SAX reader to parse a document & return	*/
			return new SAXReader().read(xmlPath);

		/*	If catch any checked exception, print the stack trace & return null	 */
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 	This is a static method to write XML back
	 * @param document
	 * @param xmlPath
	 */
	public static void writeBackXML (Document document, String xmlPath) {
		try {
			/* 	Instantiate an XMLWriter object	*/
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(xmlPath), OutputFormat.createPrettyPrint());
			
			/* 	Use XML writer to write back the XML document	*/
			xmlWriter.write(document);
			
			/*	Close the XML writer	*/
			xmlWriter.close();	
			
		/*	If catch any checked exception, print the stack trace & return null	 */
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
