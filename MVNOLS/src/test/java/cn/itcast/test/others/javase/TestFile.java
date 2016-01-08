package cn.itcast.test.others.javase;

import java.io.File;

import org.junit.Test;

/**
 * 	This class is to perform testing regarding files
 */
public class TestFile {

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
}
