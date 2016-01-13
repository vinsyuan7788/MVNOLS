package cn.itcast.test.others.javase.testclass.factory.configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * 	This is a class for factory pattern: get instances based on the configuration file
 * 	1. Factory pattern always adopts reflection
 * 	
 * 	Assuming the following rules:
 * 	1. The 1st line: offers the full class name for the subject class
 * 	2. The following line: offers the field value for each field in the subject class
 */
public class ConfigurationFactory {

	/**
	 * 	This is the public static method to get instance from txt configuration file
	 * 	@return
	 * 	@throws Exception
	 */
	public static Object getInstance () throws Exception {
		
		/*	Read the configuration txt file	 */
		BufferedReader bufferedReader = new BufferedReader(new FileReader("src/test/java/cn/itcast/test/others/javase/testclass/factory/configuration/config/config.txt"));
		
		/*	Read the 1st line to acquire the full class name & the Class object	 */
		Class clazz = Class.forName(bufferedReader.readLine());
		
		/*	Get the subject class object  */
		Constructor constructor = clazz.getDeclaredConstructor(null);
		constructor.setAccessible(true);			// Set to allow to access private constructor
		Object object = constructor.newInstance(null);
		
		/*	Read the following lines & set the value for each field	 */
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			String[] dataArray = line.split("=");
			Field field = clazz.getDeclaredField(dataArray[0]);
			field.setAccessible(true);				// Set to allow to access private constructor
			if (field.getType() == int.class) {
				field.set(object, Integer.parseInt(dataArray[1]));
			} else {
				field.set(object, dataArray[1]);
			}
		}
		
		/*	Return the object|instance	*/
		return object;
	}
}
