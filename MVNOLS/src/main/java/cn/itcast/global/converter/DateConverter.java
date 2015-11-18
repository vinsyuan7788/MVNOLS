package cn.itcast.global.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 	This is a type converter configured in "springmvc.xml" for parameter binding|acquisition in global
 *  1. ANY String that are received in Date type will go through this converter
 * 	2. Needs to implement Converter interface
 *	3. Must be configured in "springmvc.xml"
 */
public class DateConverter implements Converter<String, Date> {

	/**	
	 * 	Any parameter that needs to be received as Date will go through this converter
	 *  1. This converter is to convert String to Date
	 * 	   -- e.g. Can convert the class of "birthday" field	
	 */
	@Override
	public Date convert(String source) {
				
		/*	If the source string is not null string or not null, then convert the String to Date	*/
		if (source.trim().length() != 0 || source != null) {
			
			/*	Try to convert any String with "yyyy-MM-dd HH:mm:ss" format to Date   */
	    	try {
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(source);
			} catch (ParseException e) {
				/*	Try to convert any String with "yyyy-MM-dd HH:mm:ss" format to Date  */
				try {
					return new SimpleDateFormat("yyyy-MM-dd").parse(source);
				} catch (ParseException e1) {
					/*	For those need to be converted but unconvertable (i.e. format is neither "yyyy-MM-dd HH:mm:ss" or "yyyy-MM-dd"), return null  */
					return null;
				}
			}	
		}
		
		/*	If the source is null string or null, then directly return null   */
		return null;
	}
}
