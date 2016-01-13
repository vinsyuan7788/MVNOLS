package cn.itcast.test.others.javase.testclass.observer.subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.itcast.test.others.javase.testclass.observer.subject.interfaces.WeatherNotifier;

/**
 * 	This is a class for observer pattern: serving as a subject 
 * 	1. Offer a notifier interface for notification 
 *  2. Maintain a list reference of all observers implementing the offered interface: IOP & polymorphism
 * 	3. Offer add|delete method to add|delete observers
 * 	4. Invoke the notification method of each observer to notify the observer actively
 */
public class WeatherStation {
	
	/*	Variable to store the possible weather	*/
	private String[] weathers = {"Sunny", "Hazy", "Windy", "Hailing", "Snowy"};
	/*	Current weahter	 */
	public String currentWeather = null;
	
	/**
	 * 	Maintain a list reference of all observers implementing the offered interface: IOP & polymorphism
	 */
	List<WeatherNotifier> weatherNotifiers = new ArrayList<WeatherNotifier>();
	
	/**
	 * 	Offer add method to add observers
	 * 	@param weatherNotifier
	 */
	public void addObserver(WeatherNotifier weatherNotifier) {
		weatherNotifiers.add(weatherNotifier);
	}
	
	/**
	 * 	Offer delete method to delete observers
	 * 	@param weatherNotifier
	 */
	public void deleteObserver(WeatherNotifier weatherNotifier) {
		weatherNotifiers.remove(weatherNotifier);
	}
	
	/*	This is the method to have weather station work	 */
	public void startWorking () throws Exception {
		
		/*	Update the weather within 1 to 1.5 seconds constantly	*/
		while (true) {
			
			/*	Update the weather	*/
			this.updateWeather();
			
			/**
			 * 	Invoke the notification method of each observer to notify the observer actively	 
			 */
			for (WeatherNotifier weatherNotifier : weatherNotifiers) {
				weatherNotifier.actAccordingtoCurrentWeather(currentWeather);
			} 
			
			/*	Wati 1 to 1.5 seconds randomly	*/
			Thread.sleep(new Random().nextInt(501)+1000);
		}
	}

	/*	This is the method to update the weather randomly	*/
	private void updateWeather () throws Exception {
		
		/*	Get the index for weather randomly	*/
		int index = new Random().nextInt(weathers.length);
		
		/*	Update current weather	*/
		currentWeather = weathers[index];
		System.out.println("Current weather: " + currentWeather);
	}
}
