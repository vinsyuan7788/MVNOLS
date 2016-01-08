package cn.itcast.test.others.javase;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**
 * 	This is the class to test operators
 */
public class TestOperators {
	
	/**
	 * 	Test String plus int: addition operator
	 */
	@Test
	public void testStringPlusInt () throws Exception {
		
		/*	Get a String object & an int object	  */
		String dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		System.out.println("The string is: " + dateFormat);
		int randomFormat = (int)(Math.random()*899+100);		// random number between 100 and 999
		System.out.println("The integer is: " + randomFormat);
		
		/*	Add the String object & int object	*/
		String fileName = dateFormat + "_" + randomFormat + "_" + "fileName.ext";
		System.out.println("The full path name: " + fileName);
		
		/*	Get the hex string from the hash code of the file name	*/
		int hashCode = fileName.hashCode();
		String hexString = Integer.toHexString(hashCode);
		System.out.println("The corresponding hex string of the hash code: " + hexString);
		
		/*	Print out the corresponding folder path	*/
		File folderPath = new File("http://localhost:8081" + "/" + hexString.charAt(0) + "/" + hexString.charAt(1));
		System.out.println("The corresponding folder path: " + folderPath.toString());
		//	folderPath.mkdirs();
	}
}
