package cn.itcast.test.others.javase.testclass.iostream.bytes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * 	This is the class for PrintStream
 * 	1. PrintStream will automatically convert the data to bytes before printing out
 *  2. System.out: this "out" is an object of PrintStream
 *  
 *  Functions of PrintStream:
 *  1. Automatic conversion for output
 *  2. Collect exception infomration
 *  
 * @author Vince Xu Yuan
 */
public class PrintOutStream {

	/**
	 * 	This is a static method to print out any type of data with PrintStream
	 * @param data
	 * @param filePath
	 * @throws Exception
	 */
	public static void printToFile (Object data, String filePath) throws Exception {
		
		/*	Set up the stream (data channel)  */
		PrintStream printStream = new PrintStream(new File(filePath));
		
		/*	
		 * 	Print out the data
		 * 	1. If the file does not exist, the file will be created automatically
		 * 	2. If the file has existed, then the content in the file will be completely recovered
		 *     -- If want to append contents in the exising file, use "new FileOutputStream(file, true)" for PrintStream constructor	
		 */
		printStream.println(data);
		
		/*	Close the stream: release the resource (e.g., the file)	 */
		printStream.close();
	}
	
	/**
	 * 	This is a static method to print out exception information
	 * @throws FileNotFoundException 
	 * @throws Exception
	 */
	public static void printExceptionLog (String logFilePath) throws FileNotFoundException {
		
		/*	
		 * 	Specify a PrintStream object to print the exception infomration to a file on disk 
		 * 	1. If the file does not exist, the file will be created automatically
		 * 	2. If the file has existed, then the content in the file will be completely recovered
		 *     -- If want to append contents in the exising file, use "new FileOutputStream(file, true)" for PrintStream constructor, like this case	
		 */
		PrintStream printStream = new PrintStream(new FileOutputStream(new File(logFilePath), true));
		
		/*	Collection the exception infomration to the log file	*/
		for (int i = 0; i < 100; i++) {
			try {
				System.out.println("The division result: " + 4 / 0);
			} catch (Exception e) {
				e.printStackTrace(printStream);
			}
		}
	}
}
