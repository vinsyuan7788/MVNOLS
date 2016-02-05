package cn.itcast.test.others.javase.testclass.iostream.bytes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

/**
 * 	This is the class for SequenceInputStream
 * 	1. To logically concatenate byte input streams,
 * @author Vince Xu Yuan
 */
public class SequenceInStream {

	/**
	 * 	This is a static method to concatenate files using SequenceInputStream 
	 * @param bufferSize
	 * @param newFilePath
	 * @param originalfilePaths
	 * @throws Exception
	 */
	public static void concatenateFiles (int bufferSize, String[] originalFilePaths, String newFilePath) throws Exception {
		
		/*	Prepare the input stream to be concatenated	 */
		Vector<FileInputStream> fileInputStreamVector = new Vector<FileInputStream>();
		for (int i = 0; i < originalFilePaths.length; i++) {
			fileInputStreamVector.add(new FileInputStream(new File(originalFilePaths[i])));
		}
		Enumeration<FileInputStream> fileInputStreams = fileInputStreamVector.elements();
		
		/*	Set up the sequence input stream & file output stream	*/
		SequenceInputStream sequenceInputStream = new SequenceInputStream(fileInputStreams);
		FileOutputStream fileOutputStream = new FileOutputStream(new File(newFilePath));
		
		/*	
		 * 	Read & write data simultaneously to concatenate the file	
		 * 	1. Use a byte array to allow to read multiple data once
		 * 	2. Use an int-type data to record the actual read-in data length
		 *  3. Use while loop to completely copy the file
		 *     -- When read to the end of the file, it will return -1
		 *     -- After read the data, write whatever is read, until the loop is over	
		 */
		byte[] buffer = new byte[bufferSize];
		int lengthOfActualReadInData = 0;
		while ((lengthOfActualReadInData = sequenceInputStream.read(buffer)) != -1) {
			fileOutputStream.write(buffer, 0, lengthOfActualReadInData);
		}
	
		/*	Close the stream: release the resource (i.e., the files & the input streams to be concatenated)	 */
		fileOutputStream.close();
		sequenceInputStream.close();
	}
}
