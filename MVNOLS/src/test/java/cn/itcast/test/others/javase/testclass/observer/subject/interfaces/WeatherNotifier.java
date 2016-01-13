package cn.itcast.test.others.javase.testclass.observer.subject.interfaces;

/**
 * 	This is an interface offered by subject for weather notification: serving as a notifier
 * 	1. Offers the notification method
 * 	2. The notifier interface is necessarily unified so that it won't cause confusion to observers
 */
public interface WeatherNotifier {

	/**
	 * 	This is the provided notification method
	 * @param currentWeather
	 */
	public void actAccordingtoCurrentWeather (String currentWeather);
}
