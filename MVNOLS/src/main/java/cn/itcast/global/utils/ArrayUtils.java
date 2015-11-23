package cn.itcast.global.utils;

/**
 * 	This is an utility class to process Array data
 */
public class ArrayUtils {

	/**
	 * 	This is a static method to convert String array to String
	 * 	1. Different from org.apache.commons.lang3.ArrayUtils.toString()
	 *     -- org.apache.commons.lang3.ArrayUtils.toString() will add braces at the both end of the String, which is not desirable
	 * @param stringArray
	 * @return
	 */
	public static String toString (String[] stringArray) {
		
		/* If the String[] is not null, accept each element using StringBuffer & converts to String */
		if (stringArray != null && stringArray.length > 0) {
			
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < stringArray.length; i++) {
				if (i > 0) sb.append(",");
				sb.append(stringArray[i]);
			}
			return sb.toString();
			
		/* If the String[] is null, then directly return null */
		} else {
			return null;
		}
	}
}
