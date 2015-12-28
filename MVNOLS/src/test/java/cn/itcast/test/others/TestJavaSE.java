package cn.itcast.test.others;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import cn.itcast.global.utils.DateUtils;

/**
 * 	This is the class to test JavaSE
 */
public class TestJavaSE {

	/**
	 * 	Test String plus int
	 */
	@Test
	public void testStringPlusInt () throws Exception {
		
		/*	Get a String object & an int object	  */
		String dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		System.out.println("The String is: " + dateFormat);
		int randomFormat = (int)(Math.random()*899+100);
		System.out.println("The int is: " + randomFormat);
		
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
	
	/**
	 * 	Test "final"
	 */
	@Test
	public void testFinal () throws Exception {
		
		final int a = 2;
		System.out.println(a);
		//	a = 3;					//	Cannot be reassigned since it is finalized
		//	System.out.println(a);
	}
	
	/**
	 * 	Test file separator:
	 * 	1. In Java: "\\" == "/"
	 */
	@Test
	public void testFileSeparator () throws Exception {
		
		/*	Create a directory using "\\"	*/
		File file1 = new File("D:\\template\\");
		file1.mkdirs();
		
		/*	Check if "\\" == "/"	*/
		File file2 = new File("D:/template/");
		if (file2.exists()) {
			System.out.println("\"\\\\\" == \"/\" in Java directory");
		} else {
			System.out.println("\"\\\\\" != \"/\" in Java directory");
		}
	}
	
	/**
	 * 	Test comparing Date 
	 * 	1. "Switch case" is only workable for int-typed expression or the expression whose result can be automatically
	 * 	   converted to int type (namely byte|short|char|int)
	 */
	@Test
	public void testCompareDate () throws Exception {
		
		/*	Get 2 date objects by directly instantiating current date & parsing a string respectively	*/
		Date date1 = new Date();
		Date date2 = new SimpleDateFormat("MM/dd/yyyy").parse("12/13/2016");
		
		/*	Do the date comparison by swtich case	*/
		switch (DateUtils.compareDates(date1, date2)) {
		case 1: 
			System.out.println(date1 + " is earlier than " + date2);
			break;
		case 0: 
			System.out.println(date1 + " is the same as " + date2);
			break;
		case -1: 
			System.out.println(date1 + "is later than " + date2); 
			break;
		default:
			break;
		}
	}
	
	/**
	 * 	Test the difference between "==" and "equals()" for String objects
	 */
	@Test
	public void testEqualsForString () throws Exception {
		
		/*	Create a string object & a null string reference	 */
		String string1 = "This is a test String";
		String string2 = null;
		
		/*	Do the comparison between the strings with same reference and value	*/
		string2 = "This is a test String";
		if (string1 == string2) {
			System.out.println("string1 and string2 have the same reference");
		} else {
			System.out.println("string1 and string2 have different references");
		}
		if (string1.equals(string2)) {
			System.out.println("string1 and string2 have the same value");
		} else {
			System.out.println("string1 and string2 have difference values");
		}
		
		/*	Do the comparison between the strings with different references and same value	*/
		string2 = new String("This is a test String");
		if (string1 == string2) {
			System.out.println("string1 and string3 have the same reference");
		} else {
			System.out.println("string1 and string2 have different references");
		}
		if (string1.equals(string2)) {
			System.out.println("string1 and string2 have the same value");
		} else {
			System.out.println("string1 and string2 have difference values");
		}
	}
	
	/**
	 * 	Test the "==" for Integer objects
	 * 	1. If the integer value is within the range between -128 and 127, the obejcts will be stored in IntegerCache class
	 * 	   so that the value can be referenced repeatedly & improve the reusabilty
	 *  2. If the integer value is out of range (-128, 127), the JVM will assign a new reference to the integer object
	 */
	@Test
	public void testEqualsForInteger () throws Exception {
		
		/*	Create 2 null integer references  */
		Integer a = null;
		Integer b = null;
		
		/*	Compare the references of value with range [-200, 199) 	 */
		int sameReferenceCount = 0;
		int diffReferenceCount = 0;
		List<Integer> sameReferenceValues = new ArrayList<Integer>();
		for (a = -200, b = -200; a < 200 && b < 200; a++, b++) {
			if (a == b) {
				sameReferenceCount += 1;
				sameReferenceValues.add(a);
			} else {
				diffReferenceCount += 1;
			}	
		}
		System.out.println("The same reference count: " + sameReferenceCount);
		System.out.println("The different reference count: " + diffReferenceCount);
		System.out.println("Total counts: " + (sameReferenceCount + diffReferenceCount));
		System.out.println("The range of values for same reference: [" + sameReferenceValues.get(0) + ", " + sameReferenceValues.get(sameReferenceValues.size()-1) + "]");
		System.out.println("The values out of above range have different references");
	}
}
