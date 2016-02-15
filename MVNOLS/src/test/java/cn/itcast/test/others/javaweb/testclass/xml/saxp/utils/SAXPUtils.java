package cn.itcast.test.others.javaweb.testclass.xml.saxp.utils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

/**
 * 	This is a SAX utility class to parse XML with DOM or SAX
 * @author Vince Xu Yuan
 */
public class SAXPUtils {
	
	//--------------------------------------------------- DOM -----------------------------------------------------
	/**
	 * 	This is a static method to get the document object
	 * @param xmlPath
	 * @return
	 */
	public static final Document getDocument(String xmlPath) {
		
		try {
			/*	Instantiate a document builder from document builder factory 	*/
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			
			/*	Use the document builder to parse a document & return	*/
			return documentBuilder.parse(xmlPath);
		
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
	public static final void writeToDocument (Document document, String xmlPath) {
		
		try {
			/*	Instantiate a transformer from transfomer factory	*/
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			
			/*	Use transformer to write back the XML document	*/
			transformer.transform(new DOMSource(document), new StreamResult(xmlPath));
		
		/*	If catch any checked exception, print the stack trace	 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//--------------------------------------------------- SAX -----------------------------------------------------
	/**
	 * 	This is a static method to get a SAX parser
	 * @return
	 */
	public static final SAXParser getSAXParser () {
		
		try {
			/*	Instantiate a SAX parser from SAX parser factory	*/
			SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
			
			/*	Return the sax parser	*/
			return saxParser;
		
		/*	If catch any checked exception, print the stack trace & return null	 */
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
