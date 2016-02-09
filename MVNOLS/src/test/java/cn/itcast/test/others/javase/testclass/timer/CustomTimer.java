package cn.itcast.test.others.javase.testclass.timer;

import java.util.Date;
import java.util.Timer;

/**
 * 	This is the class to test timer
 * @author Vince Xu Yuan
 */
public class CustomTimer {

	/**
	 * 	This is the main method for testing
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*	Get a timer	 */
		Timer timer = new Timer();
		
		/*	Schedule a task starting at current date & repeating within 2000ms	 */
		timer.schedule(new CustomTimerTask(), new Date(), 2000);
		
		/*	Schedule a task starting after delaying 2000ms & then repeating within 2000ms	*/
//		timer.schedule(new CustomTimerTask(), 2000, 2000);
		
		/*	Schedule a task starting at 2/8/2016 00:00:00 & repeating within 24 hours	*/
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(2016, 2, 8, 0, 0, 0);
//		Date date = calendar.getTime();
//		System.out.println("The date: " + date);
//		timer.schedule(new CustomTimerTask(), date, 1000*60*60*24);
		
		/*	Cancel a timer	*/
//		timer.cancel();
	}
}
