package cn.itcast.global.utils;

import java.security.MessageDigest;
import org.apache.commons.codec.binary.Hex;

/**
 * 	This class is used to encrypt any necessary string
 * 	1. For password encryption
 */
public class EncryptionUtils {

	/**
	 * 	This utility method adopts MD5 and hex code for encryption
	 * @param string
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public String encrypt (String string) throws Exception {
		
		/*	MD5 encryption	*/
		byte[] digest = MessageDigest.getInstance("MD5").digest(string.getBytes());
		
		/*	Hex code encoding	*/
		char[] encodedHex = Hex.encodeHex(digest);
		
		/*	Return the encrypted string	 */
		return new String(encodedHex);
	}
}
