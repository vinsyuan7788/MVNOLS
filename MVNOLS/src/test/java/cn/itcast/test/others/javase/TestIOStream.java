package cn.itcast.test.others.javase;

import org.junit.Test;

import cn.itcast.test.others.javase.testclass.iostream.bytes.BufferedIOStream;
import cn.itcast.test.others.javase.testclass.iostream.bytes.FileIOStream;
import cn.itcast.test.others.javase.testclass.iostream.bytes.ObjectIOStream;
import cn.itcast.test.others.javase.testclass.iostream.bytes.PrintOutStream;
import cn.itcast.test.others.javase.testclass.iostream.bytes.SequenceInStream;
import cn.itcast.test.others.javase.testclass.iostream.bytes.object.Address;
import cn.itcast.test.others.javase.testclass.iostream.bytes.object.User;
import cn.itcast.test.others.javase.testclass.iostream.character.BufferedReaderWriter;
import cn.itcast.test.others.javase.testclass.iostream.character.ByteStreamReaderWriter;
import cn.itcast.test.others.javase.testclass.iostream.character.FileReaderWriter;

/**
 * 	This is the class to perform testing regarding I/O stream (Input/Output Stream)
 * 	1. Byte stream: handle I/O of raw binary data (In actual implementation, char-type casting, String constructor & "getByte()" can be manually used to handle translation, which however is not favorable)
 *     -- InputStream & OutputStream (which are abstract classes) are the superclasses of all byte I/O streams
 *     -- File IO: FileInputStream; FileOutputStream
 *     -- Buffered IO: BufferedInputStream & BufferedOutputStream
 *     -- Sequence input: SequenceInputStream
 *     -- Object IO: ObjectInputStream & ObjectOutputStream
 *     -- Print output: PrintStream
 *  2. Character stream: handle I/O of character data, automatically handling translation to and from the local character set (namely "character stream = byte stream + encoding|decoding")
 *     -- Reader & Writer (which are abstract classes) are the superclasses of all character I/O streams
 *     -- File IO: FileReader; FileWriter
 *     -- Buffered IO: BufferedReader; BufferedWriter
 *     -- Byte stream IO: InputStreamReader & OutputStreamWriter 
 *        -- Since usually what stream is returned to use is not determined by us. This stream can convert the (returned) byte stream to character stream
 *        -- Able to specify the encoding|decoding rules (UTF-8, GBK, etc.)
 *        
 *  When to use byte|character stream?
 *  1. If the read|write data involves character (e.g., text, etc.), use character stream
 *     -- Altough byte stream is adoptable, it is risky: if the read-in size does not meet some thresholds, it may mis-encode|decode the characters 
 *  2. If the read|write data does not involve character (e.g., image, audio, etc.), use byte stream
 *  
 * @author Vince Xu Yuan
 */
public class TestIOStream {

	//-------------------------------------------  Byte Stream  -------------------------------------------------
	/**
	 * 	Test read a file from disk with FileInputStream
	 * @throws Exception
	 */
	@Test
	public void testFileInputStream () throws Exception {
		
		/*	Specify the buffer size & file path|URL	 */
		int bufferSize = 128;
//		String filePath = "D:\\example.java";				// This is English file: can be read since String constructor does the encoding|decoding|translation
		String filePath = "D:\\testChinese_UTF8.txt";		// This is Chinese file: can be read since String constructor does the encoding|decoding|translation using UTF8 (specified by current platform MyEclipse10)
//		String filePath = "D:\\testChinese_ANSI.txt";		// This is Chinese file: cannot be read since String constructor does the encoding|decoding|translation using UTF8 (specified by current platform MyEclipse10, not ANSI)
		
		/*	Read the file from disk	 */
		FileIOStream.readFromFile(bufferSize, filePath);
	}
	
	/**
	 * 	Test write data to a file on disk with FileOutputStream
	 * @throws Exception
	 */
	@Test
	public void testFileOutputStream () throws Exception {
		
		/*	Specify the data & file path|URL	*/
		String data = "We are the champions. Today is the lucky day!! Ooh-ahh like!";	// This is English data: can be written since "getByte()" does the encoding & notepad (, etc.) does the decoding
//		String data = "大家好大家好，哇哈哈哈哈哈哈 Howdy Hello~~~";						// This is Chinese data: can be written since "getByte()" does the encoding & notepad (, etc.) does the decoding
		String filePath = "D:\\JavaWeb\\FileOutpuStream.txt";
		
		/*	Write the data to the file on disk	*/
		FileIOStream.writeToFile(data, filePath);
	}
	
	/**
	 * 	Test copy a file with FileInputStream & FileOutputStream
	 * @throws Exception
	 */
	@Test
	public void testCopyImageWithFileIOStream () throws Exception {
		
		/*	Specify the buffer size, original file path & the copy file path	*/
		int bufferSize = 128;
		String originalFilePath = "D:\\JavaWeb\\servlet3.0upload.jpg";
		String copyFilePath = "D:\\JavaWeb\\CopyImageWithFileIOStream.jpg";
		
		/*	Copy the file	*/
		FileIOStream.copyFile(bufferSize, originalFilePath, copyFilePath);
	}
	
	/**
	 * 	Test read a file from disk with BufferedInputStream
	 * @throws Exception
	 */
	@Test
	public void testBufferedInputStream () throws Exception {
		
		/*	Specify the buffer size & file path|URL	*/
		String filePath = "D:\\example.java";				// This is English file: can be read since BufferedInputStream addresses the English character decoding when printing out to console
//		String filePath = "D:\\testChinese_UTF8.txt";		// This is Chinese file: cannot be read since BufferedInputStream does not address the Chinese character decoding when printing out to console
//		String filePath = "D:\\testChinese_ANSI.txt";		// This is Chinese file: cannot be read since BufferedInputStream does not address the Chinese character decoding when printing out to console
		
		/*	Read the file from disk	 */
		BufferedIOStream.readFromFile(filePath);
	}
	
	/**
	 * 	Test write data to a file on disk with BufferedOutputStream
	 * @throws Exception
	 */
	@Test
	public void testBufferedOutputStream () throws Exception {
		
		/*	Specify the data & file path|URL	*/
		String data = "We are the champions. Today is the lucky day!! Ooh-ahh like!";	// This is English data: can be written 
//		String data = "大家好大家好，哇哈哈哈哈哈哈 Howdy Hello~~~";						// This is Chinese data: can be written since "getByte()" does the encoding & notepad (, etc.) does the decoding
		String filePath = "D:\\JavaWeb\\BufferedOutpuStream.txt";
		
		/*	Write the data to the file on disk	*/
		FileIOStream.writeToFile(data, filePath);
	}
	
	/**
	 * 	Test copy a file with FileInputStream & FileOutputStream
	 * @throws Exception
	 */
	@Test
	public void testCopyImageWithBufferedIOStream () throws Exception {
		
		/*	Specify the buffer size, original file path & the copy file path	*/
		String originalFilePath = "D:\\JavaWeb\\servlet3.0upload.jpg";
		String copyFilePath = "D:\\JavaWeb\\CopyImageWithBufferedIOStream.jpg";
		
		/*	Copy the file	*/
		BufferedIOStream.copyFile(originalFilePath, copyFilePath);
	}
	
	/**
	 * 	Test write an object out to a file on disk with ObjectOututStream
	 * @throws Exception
	 */
	@Test
	public void testObjectOutputStream () throws Exception {
		
		/*	Specify a JavaBean object & file path	*/
		User user = new User();
		user.setUserName("Admin");
		user.setPassword("123");
		user.setGender("Male");
		user.setIncome(12000.0);
		user.setAddress(new Address("USA", "FL", "Portdoom Street, No.109"));
		String filePath = "D:\\JavaWeb\\ObjectOutputStream.txt";
		
		/*	Write the object to a file on disk	*/
		ObjectIOStream.writeToFile(user, filePath);
	}
	
	/**
	 * 	Test read an object from a file on disk with ObjectInputStream
	 * @throws Exception
	 */
	@Test
	public void testObjectInputStream () throws Exception {
		
		/*	Specify the file path	*/
		String filePath = "D:\\JavaWeb\\ObjectOutputStream.txt";
		
		/*	Read the object from a file on disk	 */
		ObjectIOStream.readFromFile(filePath);
	}
	
	/**
	 * 	Test concatenate files with SequenceInputStream
	 * @throws Exception
	 */
	@Test
	public void testSequenceInputStream () throws Exception {
		
		/*	Specify the buffer size, the original file paths & new file paths	*/
		int bufferSize = 128;
		String[] originalFilePaths = {"D:\\example.java", "D:\\example1.java", "D:\\example2.java"};
		String newFilePath = "D:\\JavaWeb\\SequenceInputStream.txt";
		
		/*	Do the file concatenation	*/
		SequenceInStream.concatenateFiles(bufferSize, originalFilePaths, newFilePath);
	}
	
	/**
	 * 	Test print data to a file on disk with PrintStream
	 * @throws Exception
	 */
	@Test
	public void testPrintToFile () throws Exception {
		
		/*	Specify the data & file path	*/
		User user = new User();
		user.setUserName("Admin");
		user.setPassword("123");
		user.setGender("Male");
		user.setIncome(12000.0);
		user.setAddress(new Address("USA", "FL", "Portdoom Street, No.109"));
		String filePath = "D:\\JavaWeb\\PrintStream_PrintToFile.txt";
		
		/*	Print the data to a file	*/
		PrintOutStream.printToFile(user, filePath);
	}
	
	/**
	 * 	Test print exception information to a log file with PrintStream
	 * @throws Exception
	 */
	@Test
	public void testPrintToLog () throws Exception {
		
		/*	Specify the file path of log to store exception inforatmion	 */
		String logFilePath = "D:\\JavaWeb\\PrintStream_PrintExceptionLog.txt";
		
		/*	Print the exception information to the log file	 */
		PrintOutStream.printExceptionLog(logFilePath);
	}
	
	//--------------------------------------  Character Stream  -------------------------------------------------
	/**
	 * 	Test read a file from disk with FileReader
	 * @throws Exception
	 */
	@Test
	public void testFileReader () throws Exception {
		
		/*	Specify the buffer size & file path|URL	 */
		int bufferSize = 128;
		String filePath = "D:\\example.java";				// This is English file: can be read since FileReader (adopting the rules of current platform MyEclipse10, which is UTF-8) adopts the same encoding rule as notepad's decoding
//		String filePath = "D:\\testChinese_UTF8.txt";		// This is Chinese file: can be read since FileReader (adopting the rules of current platform MyEclipse10, which is UTF-8) adopts the same encoding rule as notepad's decoding
//		String filePath = "D:\\testChinese_ANSI.txt";		// This is Chinese file: cannot be read since FileReader (adopting the rules of current platform MyEclipse10, which is UTF-8) adopts the different encoding rule as notepad's decoding	
		
		/*	Read the file from disk	 */
		FileReaderWriter.readFromFile(bufferSize, filePath);
	}
	
	/**
	 * 	Test write data to a file on disk with FileWriter
	 * @throws Exception
	 */
	@Test
	public void testFileWriter () throws Exception {
		
		/*	Specify the data & file path|URL	*/
		String data = "We are the champions. Today is the lucky day!! Ooh-ahh like!";	// This is English data: can be written since FileWriter does the encoding (hence no need to use "getByte()") & notepad (, etc.) does the decoding
//		String data = "大家好大家好，哇哈哈哈哈哈哈 Howdy Hello~~~";						// This is Chinese data: can be written since FileWriter does the encoding (hence no need to use "getByte()") & notepad (, etc.) does the decoding
		String filePath = "D:\\JavaWeb\\FileWriter.txt";
		
		/*	Write the data to the file on disk	*/
		FileReaderWriter.writeToFile(data, filePath);
	}
	
	/**
	 * 	Test copy a file with FileReader & FileWriter
	 * 	1. Using FileReader & FileWriter to copy images will lose the data due to the difference of encoding|decoding rules between FileReader|Writer and image software
	 *     -- Hence read|write data which does not involve character (e.g., image, audio, etc.) should be processed with byte stream
	 * @throws Exception
	 */
	@Test
	public void testCopyImageWithFileReaderWriter () throws Exception {
		
		/*	Specify the buffer size, original file path & the copy file path	*/
		int bufferSize = 128;
		String originalFilePath = "D:\\JavaWeb\\servlet3.0upload.jpg";
		String copyFilePath = "D:\\JavaWeb\\CopyImageWithFileReaderWriter.jpg";
		
		/*	Copy the file	*/
		FileReaderWriter.copyFile(bufferSize, originalFilePath, copyFilePath);
	}
	
	/**
	 * 	Test read a file from disk with BufferedReader
	 * @throws Exception
	 */
	@Test
	public void testBufferedReader () throws Exception {
		
		/*	Specify the buffer size & file path|URL	*/
		String filePath = "D:\\example.java";				// This is English file: can be read since BufferedReader (adopting the rules of current platform MyEclipse10, which is UTF-8) adopts the same encoding rule as notepad's decoding
//		String filePath = "D:\\testChinese_UTF8.txt";		// This is Chinese file: can be read since BufferedReader (adopting the rules of current platform MyEclipse10, which is UTF-8) adopts the same encoding rule as notepad's decoding
//		String filePath = "D:\\testChinese_ANSI.txt";		// This is Chinese file: can be read since BufferedReader (adopting the rules of current platform MyEclipse10, which is UTF-8) adopts the different encoding rule as notepad's decoding
		
		/*	Read the file from disk	 */
		BufferedReaderWriter.readFromFile(filePath);
	}
	
	/**
	 * 	Test write data to a file on disk with BufferedWriter
	 * @throws Exception
	 */
	@Test
	public void testBufferedWriter () throws Exception {
		
		/*	Specify the data & file path|URL	*/
		String data = "We are the champions. Today is the lucky day!! Ooh-ahh like!";	// This is English data: can be written since BufferedWriter does the encoding (hence no need to use "getByte()") & notepad (, etc.) does the decoding
//		String data = "大家好大家好，哇哈哈哈哈哈哈 Howdy Hello~~~";						// This is Chinese data: can be written since BufferedWriter does the encoding (hence no need to use "getByte()") & notepad (, etc.) does the decoding
		String filePath = "D:\\JavaWeb\\BufferedWriter.txt";
		
		/*	Write the data to the file on disk	*/
		BufferedReaderWriter.writeToFile(data, filePath);
	}
	
	/**
	 * 	Test copy a file with BufferedReader & BufferedWriter
	 * 	1. Using BufferedReader & BufferedWriter to copy images will lose the data due to the difference of encoding|decoding rules between BufferedReader|Writer and image software, which is same as using FileReader|Writer
	 *     -- Hence read|write data which does not involve character (e.g., image, audio, etc.) should be processed with byte stream
	 * @throws Exception
	 */
	@Test
	public void testCopyImageWithBufferedReaderWriter () throws Exception {
		
		/*	Specify the buffer size, original file path & the copy file path	*/
		String originalFilePath = "D:\\JavaWeb\\servlet3.0upload.jpg";
		String copyFilePath = "D:\\JavaWeb\\CopyImageWithBufferedReaderWriter.jpg";
		
		/*	Copy the file	*/
		BufferedReaderWriter.copyFile(originalFilePath, copyFilePath);
	}
	
	/**
	 * 	Test InputStreamReader: byte input stream --> character input stream 
	 * @throws Exception
	 */
	@Test
	public void testInputStreamReader_ReadFromConsole () throws Exception {
		
		/*	Read & display whatever is input from console, input "exit" to exit the program	 */
		ByteStreamReaderWriter.readFromConsole();
	}

	/**
	 * 	Test InputStreamReader: byte input stream --> character input stream 
	 * @throws Exception
	 */
	@Test 
	public void testInputStreamReader_ReadFromFile () throws Exception {
		
		/*	Specify the buffer size & file path|URL & charset	*/
		String filePath = "D:\\example.java";				// This is English file: can be read since InputStreamReader specifies the same encoding rule (UTF-8) as notepad's decoding
//		String filePath = "D:\\testChinese_UTF8.txt";		// This is Chinese file: can be read since InputStreamReader specifies the same encoding rule (UTF-8) as notepad's decoding
//		String filePath = "D:\\testChinese_ANSI.txt";		// This is Chinese file: cannot be read since InputStreamReader specifies the same encoding rule (UTF-8) as notepad's decoding
		String charset = "UTF-8";
		
		/*	Read the file from disk	 */
		ByteStreamReaderWriter.readFromFile(filePath, charset);
	}
	
	/**
	 * 	Test OutputStreamWriter: byte output stream --> character output stream 
	 * @throws Exception
	 */
	@Test
	public void testOutputStreamWriter () throws Exception {
		
		/*	Specify the data & file path|URL & charset	*/
		String data = "We are the champions. Today is the lucky day!! Ooh-ahh like!";	// This is English data: can be written since OutputStreamWriter does the encoding (hence no need to use "getByte()") & notepad (, etc.) does the decoding
//		String data = "大家好大家好，哇哈哈哈哈哈哈 Howdy Hello~~~";						// This is Chinese data: can be written since OutputStreamWriter does the encoding (hence no need to use "getByte()") & notepad (, etc.) does the decoding
		String filePath = "D:\\JavaWeb\\OutputStreamWriter.txt";
		String charset = "UTF-8";
		
		/*	Write the data to the file on disk	*/
		ByteStreamReaderWriter.writeToFile(data, filePath, charset);
	}
}
