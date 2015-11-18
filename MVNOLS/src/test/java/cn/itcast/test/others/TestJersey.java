package cn.itcast.test.others;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.sun.jersey.api.client.Client;

/**
 * 	This is the class to test Jersey
 */
public class TestJersey {

	/**
	 * 	This is to test transmitting file to another server (e.g. tomcat) using Jersey
	 * @throws Exception
	 */
	@Test
	public void testJersey () throws Exception {
		
		/*	Get the resource that needs to be transmitted (from local disk in this case)	 */
		String FilePath = "D:\\My Study\\留学之路\\出国后生活\\学业\\Jobs\\Job Record.txt";
		byte[] requestEntity = FileUtils.readFileToByteArray(new File(FilePath));
	
		/*	
		 * 	Transmit the resource to specific location of a server
		 * 	For requestURL:	
		 * 	1. The file directory (e.g. /uploadFiles/Files) should be created beforehand under "webapps" file, otherwise the transmission will fail (error code: 409)
		 *     -- to avoid this: write a program for another tomcat to generate directory if the directory does not exist
		 *        -- which means that we needs to use another MyEclipse with configuraiton of another tomcat, then do the programming
		 *           -- e.g. new File (...).mkdirs(), etc.
		 * 	2. If the file name (e.g. xxx.txt) conflicts with the existing file name, then transmission will fail (error code: 204)
		 * 	For put() method:
		 * 	1. String.class: since the resource is transmitted in the form of character string
		 * 	2. requestEntity: the resource that needs to be transmitted	
		 */
		String requestURL = "http://localhost:8081/uploadFiles/Files/sdfsdfs.txt";
		new Client().resource(requestURL).put(String.class, requestEntity);
		System.out.println("Upload file transmission completed");
	}
}
