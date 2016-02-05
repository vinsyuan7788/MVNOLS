package cn.itcast.test.others.javase.testclass.iostream.bytes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import cn.itcast.test.others.javase.testclass.iostream.bytes.object.User;

/**
 * 	This is the class for object byte IO (read & wirte)
 * @author Vince Xu Yuan
 */
public class ObjectIOStream {
	
	/**
	 * 	This is a static method to write the object to a file with ObjectOutputStream
	 * 	1. Only the object implement Serializable interface can be written to file on disk
	 * @param object
	 * @param filePath
	 * @throws Exception
	 */
	public static void writeToFile (Serializable object, String filePath) throws Exception {
		
		/*	Set up the stream (namely data channel) for the file	*/
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(filePath)));
	
		/*	
		 * 	Write the object to the file
		 * 	1. If the file does not exist, the file will be created automatically
		 * 	2. If the file has existed, then the content in the file will be completely recovered
		 *     -- If want to append contents in the exising file, use the constructor "new FileOutputStream(file, true)"	
		 */
		objectOutputStream.writeObject(object);
		
		/*	Close the stream: release the resource (i.e., the file)	 */
		objectOutputStream.close();
	}
	
	/**
	 * 	This is a static method to read the object from file on disk with ObjectInputStream
	 * @param filePath
	 * @throws Exception
	 */
	public static void readFromFile (String filePath) throws Exception {
		
		/*	Set up the stream (namely data channel) for the file	*/
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File(filePath)));
		
		/*	Read the object in the file	 */
		Object object = objectInputStream.readObject();
		if (object instanceof User) {
			object = (User) object;
		}
		System.out.println("The read object: " + object);
		System.out.println("The class of read object: " + object.getClass());
		
		/*	Close the stream: release the resource (i.e., the file)	 */
		objectInputStream.close();
	}
}
