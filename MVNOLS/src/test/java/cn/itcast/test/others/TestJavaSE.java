package cn.itcast.test.others;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	public void testStringPlusDouble () throws Exception {
		
		String dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		int randomFormat = (int)(Math.random()*899+100);
		String fileName = dateFormat + "_" + randomFormat + "_" + "fileName.ext";
		System.out.println(fileName);
		
		int hashCode = fileName.hashCode();
		String hexString = Integer.toHexString(hashCode);
		System.out.println(hexString);
		
		File folderPath = new File("http://localhost:8081" + "/" + hexString.charAt(0) + "/" + hexString.charAt(1));
		System.out.println(folderPath.toString());
		folderPath.mkdirs();
	}
	
	/**
	 * 	Test "final"
	 */
	@Test
	public void testFinal () throws Exception {
		
		final int a = 2;
		System.out.println(a);
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
	 */
	@Test
	public void testCompareDate () throws Exception {
		
		Date date1 = new Date();
		Date date2 = new SimpleDateFormat("MM/dd/yyyy").parse("13/13/2013");
		System.out.println(DateUtils.compareDate(date1, date2));
	}
}
