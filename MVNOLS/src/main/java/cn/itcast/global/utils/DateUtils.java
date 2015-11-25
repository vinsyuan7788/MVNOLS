package cn.itcast.global.utils;

import java.util.Date;

/**
 * 	This is an utility class to compare 2 Dates
 */
public class DateUtils {

	/**
	 * 	Compare 2 dates
	 * 	1. If date1 is earlier than date2, then return 1.
	 * 	2. If date1 is later than date2, then return -1.
	 * 	3. If 2 dates are the same, return 0.
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareDates (Date date1, Date date2) {
		
		/*	If date1 is earlier than date2, return 1	*/
        if (date1.getTime() < date2.getTime()) {
            return 1;
            
        /*	If date1 is later than date2, then return -1	*/
        } else if (date1.getTime() > date2.getTime()) {
            return -1;
            
        /*	If 2 dates are the same, return 0	*/
        } else {
            return 0;
        }
	}
}
