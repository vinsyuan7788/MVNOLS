package cn.itcast.test.others.javase;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;

/**
 * 	This is the class to perform testing regarding Properties
 * 	1. Properties is the sub-class of Map: since it implements "hashTable"
 *     -- Hence it is unordered when creating property file
 *  
 *  Exemplary application scenario:
 *  1. Store (create) property file for configuration
 *  2. Load (read) property file for configuration 
 * 	
 * @author Vince Xu Yuan
 */
public class TestProperties {

	/**
	 * 	Test the basics of properties 
	 */
	@Test
	public void testBasicsOfProperties () throws Exception {
		
		/*	Instantiate a Properties object & set necessary properties	*/
		Properties properties = new Properties();
		properties.setProperty("host", "gmail.com");
		properties.setProperty("username", "456");
		properties.setProperty("password", "uio");
		
		/*	Get the properties	*/
		System.out.println("\nThe properties:");
		System.out.println(properties.getProperty("host"));
		System.out.println(properties.getProperty("username"));
		System.out.println(properties.getProperty("password"));
		
		/*	Traverse the properties	 */
		Set<Entry<Object, Object>> entrySet = properties.entrySet();
		System.out.println("The entry set:");
		for (Entry<Object, Object> entry : entrySet) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
	
	/**
	 * 	Test store (create) property file
	 * 	1. since it implements "hashTable", it is unordered when storing (creating) property files
	 * 	2. If Chinese is involved, then MUST use character stream: FileWriter()
	 */
	@Test
	public void testStorePropertyFile () throws Exception {
		
		/*	Instantiate Properties objects & set necessary properties	*/
		Properties properties_eng = new Properties();
		properties_eng.setProperty("host", "gmail.com");
		properties_eng.setProperty("username", "456");
		properties_eng.setProperty("password", "uio");
		Properties properties_chn = new Properties();
		properties_chn.setProperty("host", "gmail.com");
		properties_chn.setProperty("username", "456");
		properties_chn.setProperty("password", "uio");
		properties_chn.setProperty("地点", "旧金山");
		
		/*	Create a property file	*/
		properties_eng.store(new BufferedOutputStream(new FileOutputStream("D:\\JavaWeb\\test_eng.properties")), "This is the testing property file");
		properties_chn.store(new BufferedWriter(new FileWriter("D:\\JavaWeb\\test_chn.properties")), "This is the testing property file");
	}
	
	/**
	 * 	Test load (read) property file
	 * 	1. since it implements "hashTable", it is unordered when loading (reading) property files
	 * 	2. If Chinese is involved, then MUST use character stream: FileReader()
	 */
	@Test
	public void testLoadPropertyFile () throws Exception {
		
		/*	Instantiate Properties objects & load necessary property files	*/
		Properties properties_eng = new Properties();
		properties_eng.load(new BufferedInputStream(new FileInputStream("D:\\JavaWeb\\test_eng.properties")));
		Properties properties_chn = new Properties();
		properties_chn.load(new BufferedReader(new FileReader("D:\\JavaWeb\\test_chn.properties")));
		
		/*	See the result	*/
		System.out.println("The properties_eng:");
		System.out.println(properties_eng.getProperty("host"));
		System.out.println(properties_eng.getProperty("username"));
		System.out.println(properties_eng.getProperty("password"));
		System.out.println("\nThe properties_chn:");
		System.out.println(properties_chn.getProperty("host"));
		System.out.println(properties_chn.getProperty("username"));
		System.out.println(properties_chn.getProperty("password"));
		System.out.println(properties_chn.getProperty("地点"));
	}
}
