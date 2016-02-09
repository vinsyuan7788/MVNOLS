package cn.itcast.test.others.javase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import cn.itcast.test.others.javase.testclass.date.DateUtils;

/**
 * 	This class is to perform testing regarding date, calendar, string
 */
public class TestDateAndCalendarAndString {

	/**
	 * 	Test date comparison 
	 * 	1. "Switch case" is only workable for int-typed expression or the expression whose result can be automatically
	 * 	   converted to int type (namely byte|short|char|int)
	 */
	@Test
	public void testCompareDate () throws Exception {
		
		/*	Get 2 date objects by directly instantiating current date & parsing a string respectively	*/
		Date date1 = new Date();
		Date date2 = new SimpleDateFormat("MM/dd/yyyy").parse("12/13/2016");
		
		/*	Do the date comparison by "getTime()" with "swtich case"	*/
		switch (DateUtils.compareDates(date1, date2)) {
		case -1: 
			System.out.println(date1 + " is before " + date2);
			break;
		case 0: 
			System.out.println(date1 + " is equal to " + date2);
			break;
		case 1: 
			System.out.println(date1 + " is after " + date2); 
			break;
		default:
			break;
		}
		
		/*	Do the date comparison by "compareTo()" with "switch case"	*/
		switch (date1.compareTo(date2)) {
		case -1: 
			System.out.println(date1 + " is before " + date2);
			break;
		case 0: 
			System.out.println(date1 + " is equal to " + date2);
			break;
		case 1: 
			System.out.println(date1 + " is after " + date2); 
			break;
		default:
			break;
		}
	}
	
	/**
	 * 	Test the conversion among Calendar, Date and String
	 * 	1. Date: from String and Calendar
	 * 	2. Calendar: from String and Date
	 * 	3. String: from Date and Calendar
	 */
	@Test
	public void testCalendarDateStringConversion () throws Exception {
		
		/*	Date from String	*/
		Date dateFromString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-12-12 14:15:16");
		System.out.println("The date from String: " + dateFromString);
		
		/*	Date from Calendar  */
		Date dateFromCalendar = Calendar.getInstance().getTime();
		System.out.println("The date from calendar: " + dateFromCalendar);
		
		/*	Calendar from String	*/
		Calendar calendarFromString = Calendar.getInstance();
		calendarFromString.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-12-12 14:15:16"));
		System.out.println("The calendar from string: " + 
				calendarFromString.get(Calendar.YEAR) + "-" + (calendarFromString.get(Calendar.MONTH) + 1) + "-" + calendarFromString.get(Calendar.DAY_OF_MONTH) + ", the " +
				calendarFromString.get(Calendar.WEEK_OF_MONTH) + "-th week of this month and the " + (calendarFromString.get(Calendar.DAY_OF_WEEK) - 1) + "-th day of this week, " + 
				calendarFromString.get(Calendar.HOUR_OF_DAY) + ":" + calendarFromString.get(Calendar.MINUTE) + ":" + calendarFromString.get(Calendar.SECOND));
		
		/*	Calendar from Date	*/
		Calendar calendarFromDate = Calendar.getInstance();
		calendarFromDate.setTime(new Date());
		System.out.println("The calendar from string: " + 
				calendarFromDate.get(Calendar.YEAR) + "-" + (calendarFromDate.get(Calendar.MONTH) + 1) + "-" + calendarFromDate.get(Calendar.DAY_OF_MONTH) + ", the " +
				calendarFromDate.get(Calendar.WEEK_OF_MONTH) + "-th week of this month and the " + (calendarFromDate.get(Calendar.DAY_OF_WEEK) - 1) + "-th day of this week, " + 
				calendarFromDate.get(Calendar.HOUR_OF_DAY) + ":" + calendarFromDate.get(Calendar.MINUTE) + ":" + calendarFromDate.get(Calendar.SECOND));
		
		/*	String from Date	*/
		String stringFromDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		System.out.println("The string from date: " + stringFromDate);
		
		/*	String from Calendar	*/
		String stringFromCalender = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		System.out.println("The string from calendar: " + stringFromCalender);
	}
}
