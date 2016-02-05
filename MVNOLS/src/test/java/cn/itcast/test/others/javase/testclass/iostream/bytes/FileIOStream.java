package cn.itcast.test.others.javase.testclass.iostream.bytes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 	This is the class for file byte IO (read & wirte)
 * @author Vince Xu Yuan
 */
public class FileIOStream {

	/**
	 * 	This is the static method to read a file from disk using FileInputStream
	 *  1. Buffer + while loop is relatively best way to read files
	 *     -- More time-efficieint & less CPU-consuming at the cost of space (namely buffer)
	 * @param filePath
	 * @throws Exception
	 */
	public static void readFromFile (int bufferSize, String filePath) throws Exception {
		
		/*	Set up the stream (namely data channel) for the file	*/
		FileInputStream fileInputStream = new FileInputStream(new File(filePath));
		
		/*	
		 * 	Read the data in the file
		 * 	1. Use a byte array to allow to read multiple data once
		 * 	2. Use an int-type data to record the actual read-in data length
		 *  3. Use while loop to completely read the file
		 *     -- When read to the end of the file, it will return -1	
		 */
		byte[] buffer = new byte[bufferSize];
		int lengthOfActualReadInData = 0;
		while ((lengthOfActualReadInData = fileInputStream.read(buffer)) != -1) {
			System.out.print(new String(buffer, 0, lengthOfActualReadInData));	// Here use the default decoding rules (GBK) to get a string
		}
		
		/*	Close the stream: release the resource (i.e., the file)	 */
		fileInputStream.close();
	}
	
	/**
	 * 	This is the static method to write the data to a file on disk using FileOutputStream
	 * @param data
	 * @param filePath
	 * @throws Exception
	 */
	public static void writeToFile (String data, String filePath) throws Exception {
		
		/*	Set up the stream (namely data channel) for the file	*/
		FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
	
		/*	
		 * 	Write the data to the file
		 * 	1. If the file does not exist, the file will be created automatically
		 * 	2. If the file has existed, then the content in the file will be completely recovered
		 *     -- If want to append contents in the exising file, use the constructor "new FileOutputStream(file, true)"	
		 */
		fileOutputStream.write(data.getBytes());		// Here "getByte()" does the encoding 
		
		/*	Close the stream: release the resource (i.e., the file)	 */
		fileOutputStream.close();
	}
	
	/**
	 * 	This is the static method to copy a file using both FileInputStream & FileOutputStream
	 * @param bufferSize
	 * @param originalFilePath
	 * @param copyFilePath
	 * @throws Exception
	 */
	public static void copyFile (int bufferSize, String originalFilePath, String copyFilePath) throws Exception {
		
		/*	Set up the stream (namely data channel) for the file	*/
		FileInputStream fileInputStream = new FileInputStream(new File(originalFilePath));
		FileOutputStream fileOutputStream = new FileOutputStream(new File(copyFilePath));
	
		/*	
		 * 	Read & write data simultaneously to copy the file	
		 * 	1. Use a byte array to allow to read multiple data once
		 * 	2. Use an int-type data to record the actual read-in data length
		 *  3. Use while loop to completely copy the file
		 *     -- When read to the end of the file, it will return -1
		 *     -- After read the data, write whatever is read, until the loop is over	
		 */
		byte[] buffer = new byte[bufferSize];
		int lengthOfActualReadInData = 0;
		while ((lengthOfActualReadInData = fileInputStream.read(buffer)) != -1) {
			fileOutputStream.write(buffer, 0, lengthOfActualReadInData);
		}
		
		/*	Close the stream: release the resource (i.e., the files)	 */
		fileOutputStream.close();
		fileInputStream.close();
	}
}
