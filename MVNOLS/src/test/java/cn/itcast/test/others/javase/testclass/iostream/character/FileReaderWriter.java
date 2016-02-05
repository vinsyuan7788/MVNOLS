package cn.itcast.test.others.javase.testclass.iostream.character;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 	This is the class for file character IO (read & wirte)
 * @author Vince Xu Yuan
 */
public class FileReaderWriter {

	/**
	 * 	This is the static method to read a file from disk using FileReader
	 *  1. Buffer + while loop is relatively best way to read files
	 *     -- More time-efficieint & less CPU-consuming at the cost of space (namely buffer)
	 *  2. FileReader will automatically process the encoding|decoding|translation
	 * @param filePath
	 * @throws Exception
	 */
	public static void readFromFile (int bufferSize, String filePath) throws Exception {
		
		/*	Set up the stream (namely data channel) for the file	*/
		FileReader fileReader = new FileReader(new File(filePath));
		
		/*	
		 * 	Read the data in the file
		 * 	1. Use a character array to allow to read multiple data once
		 * 	2. Use an int-type data to record the actual read-in data length
		 *  3. Use while loop to completely read the file
		 *     -- When read to the end of the file, it will return -1	
		 */
		char[] buffer = new char[bufferSize];
		int lengthOfActualReadInData = 0;
		while ((lengthOfActualReadInData = fileReader.read(buffer)) != -1) {
			System.out.print(new String(buffer, 0, lengthOfActualReadInData));
		}
		
		/*	Close the stream: release the resource (i.e., the file)	 */
		fileReader.close();
	}
	
	/**
	 * 	This is the static method to write the data to a file on disk using FileWriter
	 * 	1. Inside FileWriter, there is a cahce buffer (refer to source code), so may need to invoke "flush()" to write the data out
	 * @param data
	 * @param filePath
	 * @throws Exception
	 */
	public static void writeToFile (String data, String filePath) throws Exception {
		
		/*	Set up the stream (namely data channel) for the file	*/
		FileWriter fileWriter = new FileWriter(new File(filePath));
	
		/*	
		 * 	Write the data to the file
		 * 	1. If the file does not exist, the file will be created automatically
		 * 	2. If the file has existed, then the content in the file will be completely recovered
		 *     -- If want to append contents in the exising file, use the constructor "new FileWriter(file, true)"	
		 *  3. When to actually write out the data to a file (i.e., flush out the data from the internal buffer to a file) 
		 *     -- When invoke "flush()" (refer to source code)
		 *     -- When the internal buffer is full, then flush out the data automatically (refer to source code)
		 *     -- When invoke "close()", it will flush the data before closing the stream (refer to source code)
		 */
		fileWriter.write(data);	
//		fileWriter.flush();				// If need to flush out the data without closing the stream, invoke this method
		
		/*	Close the stream: release the resource (i.e., the file)	 */
		fileWriter.close();
	}
	
	/**
	 * 	This is the static method to copy a file using both FileReader & FileWriter
	 * @param bufferSize
	 * @param originalFilePath
	 * @param copyFilePath
	 * @throws Exception
	 */
	public static void copyFile (int bufferSize, String originalFilePath, String copyFilePath) throws Exception {
		
		/*	Set up the stream (namely data channel) for the file	*/
		FileReader fileReader = new FileReader(new File(originalFilePath));
		FileWriter fileWriter = new FileWriter(new File(copyFilePath));
	
		/*	
		 * 	Read & write data simultaneously to copy the file	
		 * 	1. Use a character array to allow to read multiple data once
		 * 	2. Use an int-type data to record the actual read-in data length
		 *  3. Use while loop to completely copy the file
		 *     -- When read to the end of the file, it will return -1
		 *     -- After read the data, write whatever is read, until the loop is over	
		 */
		char[] buffer = new char[bufferSize];
		int lengthOfActualReadInData = 0;
		while ((lengthOfActualReadInData = fileReader.read(buffer)) != -1) {
			fileWriter.write(buffer, 0, lengthOfActualReadInData);
		}
		
		/*	Close the stream: release the resource (i.e., the files)	 */
		fileWriter.close();
		fileReader.close();
	}
}
