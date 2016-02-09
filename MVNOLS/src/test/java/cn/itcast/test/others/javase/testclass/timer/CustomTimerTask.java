package cn.itcast.test.others.javase.testclass.timer;

import java.util.TimerTask;

/**
 * 	This is the class for timer task for the testing of Timer class
 * @author Vince Xu Yuan
 */
public class CustomTimerTask extends TimerTask {

	@Override
	public void run() {
		System.out.println("My task is running...");
	}

}
