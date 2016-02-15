package cn.itcast.global.utils;

import java.security.MessageDigest;
import org.apache.commons.codec.binary.Hex;

/**
 * 	This class is used to encrypt any necessary string
 * 	1. For password encryption
 * @author Vince Xu Yuan
 */
public class EncryptionUtils {

	/**
	 * 	This utility method adopts MD5 and hex code for encryption
	 * @param string
	 * @return
	 */
	public static String encrypt (String string) {
		
		try {
			/*	MD5 encryption	*/
			byte[] digest = MessageDigest.getInstance("MD5").digest(string.getBytes());
			
			/*	Hex code encoding	*/
			char[] encodedHex = Hex.encodeHex(digest);
			
			/*	Return the encrypted string	 */
			return new String(encodedHex);
		
		/*	If catch any checked exception, throw it out using RuntimeException  */
		} catch (Exception e) {
			throw new RuntimeException("Exception raised by \"MessageDigest.getInstance()\"", e);
		}
	}
}
