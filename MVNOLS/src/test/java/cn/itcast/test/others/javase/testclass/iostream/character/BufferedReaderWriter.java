package cn.itcast.test.others.javase.testclass.iostream.character;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 	This is the class for buffered character IO (read & wirte)
 * @author Vince Xu Yuan
 */
public class BufferedReaderWriter {

	/**
	 * 	This is the static method to read a file from disk using BufferedReader
	 * 	1. BufferedReader extends the ability: "readLine()"
	 * @param filePath
	 * @throws Exception
	 */
	public static void readFromFile (String filePath) throws Exception {
		
		/*	Set up the stream (namely data channel) for the file	*/
		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filePath)));
		
		/*	
		 * 	Read the data in the file
		 * 	1. Use an String-type data to record the actual read-in data line
		 *  2. Use while loop to completely read the file
		 *     -- When read to the end of the file, it will return -1	
		 */
		String lineOfActualReadInData = null;
		while ((lineOfActualReadInData = bufferedReader.readLine()) != null) {
			System.out.println(lineOfActualReadInData);
		}
		
		/*	Close the stream: release the resource (i.e., the file)	 */
		bufferedReader.close();
	}
	
	/**
	 * 	This is the static method to write the data to a file on disk using BufferedWriter
	 * 	1. BufferedWriter extends the ability: "newLine()"
	 * @param data
	 * @param filePath
	 * @throws Exception
	 */
	public static void writeToFile (String data, String filePath) throws Exception {
		
		/*	Set up the stream (namely data channel) for the file	*/
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(filePath)));
	
		/*	
		 * 	Write the data to the file
		 * 	1. If the file does not exist, the file will be created automatically
		 * 	2. If the file has existed, then the content in the file will be completely recovered
		 *     -- If want to append contents in the exising file, use "new FileWriter(file, true)" as the argument for BufferedWriter constructor
		 *  3. When to actually write out the data to a file (i.e., flush out the data from the internal buffer to a file) 
		 *     -- When invoke "flush()" (refer to source code)
		 *     -- When the internal buffer is full, then flush out the data automatically (refer to source code)
		 *     -- When invoke "close()", it will flush the data before closing the stream (refer to source code)  	
		 */
		bufferedWriter.write(data);		
//		bufferedWriter.flush();			// If need to flush out the data without closing the stream, invoke this method
		
		/*	Close the stream: release the resource (i.e., the file)	 */
		bufferedWriter.close();
	}
	
	/**
	 * 	This is the static method to copy a file using both BufferedReader & BufferedWriter
	 * @param bufferSize
	 * @param originalFilePath
	 * @param copyFilePath
	 * @throws Exception
	 */
	public static void copyFile (String originalFilePath, String copyFilePath) throws Exception {
		
		/*	Set up the stream (namely data channel) for the file	*/
		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(originalFilePath)));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(copyFilePath)));
	
		/*	
		 * 	Read & write data simultaneously to copy the file	
		 * 	1. Use an String-type data to record the actual read-in data line
		 *  2. Use while loop to completely copy the file
		 *     -- When read to the end of the file, it will return -1
		 *     -- After read the data, write whatever is read, until the loop is over	
		 */
		String lineOfActualReadInData = null;
		while ((lineOfActualReadInData = bufferedReader.readLine()) != null) {
			bufferedWriter.write(lineOfActualReadInData);
		}
		
		/*	Close the stream: release the resource (i.e., the files)	 */
		bufferedWriter.close();
		bufferedReader.close();
	}
}
