package cn.itcast.test.others.javase.testclass.observer;

import cn.itcast.test.others.javase.testclass.observer.subject.interfaces.WeatherNotifier;

/**
 * 	This is a class that will do corresponding processing according to the current weather: serving as an observer
 * 	1. Implement the interface released by subject
 * 	2. Override the notification method provided by the interface to act correspondingly
 */
public class Employee implements WeatherNotifier {

	/*	This is an instance variable	*/
	private String employeeName = null;
	
	/*	This is a constructor with argument	 */
	public Employee(String employeeName) {
		this.employeeName = employeeName;
	}
	
	/**
	 * 	Override the notification method provided by the interface to act correspondingly	
	 */
	@Override
	public void actAccordingtoCurrentWeather (String currentWeather) {
		
		if ("Sunny".equals(currentWeather)) {
			System.out.println(employeeName + " can go to work happily.");
		} else if ("Snowy".equals(currentWeather)) {
			System.out.println(employeeName + " can go to work with warm clothes.");
		} else if ("Windy".equals(currentWeather)) {
			System.out.println(employeeName + " can go to work with an umbrealla.");
		} else if ("Hailing".equals(currentWeather)) {
			System.out.println(employeeName + " can go to work in car.");
		} else if ("Hazy".equals(currentWeather)) {
			System.out.println(employeeName + " can go to work with a mask.");
		}
	}
}
