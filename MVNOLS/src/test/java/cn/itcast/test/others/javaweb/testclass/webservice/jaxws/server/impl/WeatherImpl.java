package cn.itcast.test.others.javaweb.testclass.webservice.jaxws.server.impl;

import cn.itcast.test.others.javaweb.testclass.webservice.jaxws.server.interfaces.Weather;

/**
 * 	This is an web service class that implements "Weather" SEI
 */
public class WeatherImpl implements Weather {

	@Override
	public String queryWeather(String city) throws Exception {
		
		System.out.println("From client: " + city);
		
		String result = "Sunny";
		System.out.println("The weather: " + result);
		return result;
	}

}
