package cn.itcast.test.others.javase;

import java.io.File;

import org.junit.Test;

/**
 * 	This class is to perform testing regarding files
 * 	1. In web project: use "servlet.getRealPath()" to get the real path on disk
 *     -- Refer to "src/main/java/cn/itcast/cms/service/utils/StaticPageGenerationUtils.java"
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
	
	/**
	 * 	Test file operation
	 * @throws Exception
	 */
	@Test
	public void testFile () throws Exception {
		
		/*	Instantiate a File object & output necessary information	*/
		File file = new File("D:/JavaWeb/testFile/test1.txt");
		System.out.println("The absolute path: " + file.getAbsolutePath());
		System.out.println("The parent file: " + file.getParentFile());
		System.out.println("The parent path: " + file.getParent());
		System.out.println("The file exists: " + file.exists());
		
		/*	Create the file on disk if it does not exist	*/
		if (!file.exists()) {
			file.mkdirs();
			System.out.println("The file is created: " + file.exists());
		}
		
		/*	Delete the file on disk	 */
		System.out.println("Delete the file: " + file.delete());
		
	}
}
