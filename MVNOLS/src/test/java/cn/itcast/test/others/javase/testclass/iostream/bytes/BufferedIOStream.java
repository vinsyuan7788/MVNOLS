package cn.itcast.test.others.javase.testclass.iostream.bytes;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 	This is the class for buffered byte IO (read & wirte)
 *  1. Buffered IO stream can be replaced by File IO Stream
 * @author Vince Xu Yuan
 */
public class BufferedIOStream {

	/**
	 * 	This is the static method to read a file from disk using BufferedInputStream
	 *  1. The efficiency of reading file is actually not as high as using FileInputStream
	 *     -- Since when use "read()", BufferedInputStream will do the predication each time (refer to the source code)
	 *     -- Lower efficient (than FileInputStream) but more convenient to write the codes (since there is a default value for the buffered size, refer to source code)
	 * @param filePath
	 * @throws Exception
	 */
	public static void readFromFile (String filePath) throws Exception {
		
		/*	Set up the stream (namely data channel) for the file	*/
		BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(filePath)));
		
		/*	
		 * 	Read the data in the file
		 * 	1. Use an int-type data to record the actual read-in data length
		 *  2. Use while loop to completely read the file
		 *     -- When read to the end of the file, it will return -1	
		 */
		int lengthOfActualReadInData = 0;
		while ((lengthOfActualReadInData = bufferedInputStream.read()) != -1) {
			System.out.print((char)lengthOfActualReadInData);
		}
		
		/*	Close the stream: release the resource (i.e., the file)	 */
		bufferedInputStream.close();
	}
	
	/**
	 * 	This is the static method to write the data to a file on disk using BufferedOutputStream
	 * @param data
	 * @param filePath
	 * @throws Exception
	 */
	public static void writeToFile (String data, String filePath) throws Exception {
		
		/*	Set up the stream (namely data channel) for the file	*/
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
	
		/*	
		 * 	Write the data to the file
		 * 	1. If the file does not exist, the file will be created automatically
		 * 	2. If the file has existed, then the content in the file will be completely recovered
		 *     -- If want to append contents in the exising file, use "new FileOutputStream(file, true)" as the argument for BufferedOutputStream constructor
		 *  3. When to actually write out the data to a file (i.e., flush out the data from the internal buffer to a file) 
		 *     -- When invoke "flush()" (refer to source code)
		 *     -- When the internal buffer is full, then flush out the data automatically (refer to source code)
		 *     -- When invoke "close()", it will flush the data before closing the stream (refer to source code)  	
		 */
		bufferedOutputStream.write(data.getBytes());		// Here "getByte()" does the encoding 
//		bufferedOutputStream.flush();						// If need to flush out the data without closing the stream, invoke this method
		
		/*	Close the stream: release the resource (i.e., the file)	 */
		bufferedOutputStream.close();
	}
	
	/**
	 * 	This is the static method to copy a file using both BufferedInputStream & BufferedOutputStream
	 * @param bufferSize
	 * @param originalFilePath
	 * @param copyFilePath
	 * @throws Exception
	 */
	public static void copyFile (String originalFilePath, String copyFilePath) throws Exception {
		
		/*	Set up the stream (namely data channel) for the file	*/
		BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(originalFilePath)));
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(copyFilePath)));
	
		/*	
		 * 	Read & write data simultaneously to copy the file	
		 * 	1. Use an int-type data to record the actual read-in data length
		 *  2. Use while loop to completely copy the file
		 *     -- When read to the end of the file, it will return -1
		 *     -- After read the data, write whatever is read, until the loop is over	
		 */
		int lengthOfActualReadInData = 0;
		while ((lengthOfActualReadInData = bufferedInputStream.read()) != -1) {
			bufferedOutputStream.write((char)lengthOfActualReadInData);
		}
		
		/*	Close the stream: release the resource (i.e., the files)	 */
		bufferedOutputStream.close();
		bufferedInputStream.close();
	}
}
