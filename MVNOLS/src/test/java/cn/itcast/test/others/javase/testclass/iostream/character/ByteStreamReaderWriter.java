package cn.itcast.test.others.javase.testclass.iostream.character;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 	This is the class for InputStreamReader & OutputStreamWriter
 * 	1. Can convert the byte IO stream to character IO stream
 *  2. Can specify the encoding|decoding rules (UTF-8, GBK, etc.)
 * @author Vince Xu Yuan
 */
public class ByteStreamReaderWriter {

	/**
	 * 	This is the static method to read line from console through InputStreamReader
	 * 	1. Use InputStreamReader to convert the byte input stream to character input stream: byte input stream --> character input stream
	 *     -- Hence the input bytes will be converted to characters: bytes --> characters
	 * @throws Exception
	 */
	public static void readFromConsole () throws Exception {
		
		/*	Get the BufferedReader object from System input stream through InputStreamReader: byte input stream --> character input stream	*/
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		/*	Loop to read whatever is input from console	 */
		String lineOfActualReadInData = null;
		while ((lineOfActualReadInData = bufferedReader.readLine()) != null) {
			
			/*	If input is "exit", then exit the program	*/
			if (lineOfActualReadInData.equalsIgnoreCase("exit")) {
				break;
				
			/*	Else output whatever is input from console	*/
			} else {
				System.out.println(lineOfActualReadInData);
			}
		}
		
		/*	Close the stream: release the resource	*/
		bufferedReader.close();
	}
	
	/**
	 * 	This is the static method to read the data from a file through InputStreamReader
	 * 	1. Use InputStreamReader to convert the byte input stream to character input stream: byte input stream --> character input stream
	 *     -- Hence the input bytes will be converted to characters: bytes --> characters
	 * @param bufferSize
	 * @param filePath
	 * @param charset
	 * @throws Exception
	 */
	public static void readFromFile (String filePath, String charset) throws Exception {
		
		/*	Get an BufferedReader instance from FileInputStream through InputStreamReader: byte input stream --> character input stream	*/
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)), charset));
		
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
	 * 	This is the static method to write data to a file through OutputStreamWriter
	 * 1. Use OutputStreamReader to convert the byte output stream to character output stream: byte output stream --> character output stream
	 *    -- Hence the characters can be written out becoming bytes: characters --> bytes
	 * @param data
	 * @param filePath
	 * @throws Exception
	 */
	public static void writeToFile (String data, String filePath, String charset) throws Exception {
		
		/*	Get an OutputStreamWriter instance from FileOutputStream: byte output stream --> character output stream	 */
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(filePath)), charset));
		
		/*	
		 * 	Write data to the file	 
		 * 	1. If the file does not exist, the file will be created automatically
		 * 	2. If the file has existed, then the content in the file will be completely recovered
		 *     -- If want to append contents in the exising file, use "new FileOutputStream(file, true)" for OutputStreamWriter constructor
		 *  3. When to actually write out the data to a file (i.e., flush out the data from the internal buffer to a file) 
		 *     -- When invoke "flush()" (refer to source code)
		 *     -- When the internal buffer is full, then flush out the data automatically (refer to source code)
		 *     -- When invoke "close()", it will flush the data before closing the stream (refer to source code) 
		 */
		bufferedWriter.write(data);
//		bufferedWriter.flush();			// If need to flush out the data without closing the stream, invoke this method
		
		/*	Close the stream: release the resource	*/
		bufferedWriter.close();
	}
}
