package cn.itcast.test.others.javaweb.testclass.webservice.jaxrs.server.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.itcast.test.others.javaweb.testclass.webservice.jaxrs.bean.City;
import cn.itcast.test.others.javaweb.testclass.webservice.jaxrs.server.interfaces.CityService;

/**
 * 	This is a web service class that implements "CityService" SEI
 */
public class CityServiceImpl implements CityService {

	/**
	 * 	This is the service method to return city information
	 * 	1. Use static data to simulate the return result
	 */
	@Override
	public City queryCity(String cityName) throws Exception {
		
		City city = new City();
		city.setCityName("Bethlehem");
		city.setCityId("PABLH");
		city.setCityScale("Small");
		city.setCityPopulation(109200);
		city.setCityFoundationDate(new Date());
		
		return city;
	}

	/**
	 * 	This is the service method to return infomration of cities
	 * 	1. Use static data to simulate the return result
	 */
	@Override
	public List<City> queryCities(String cityScale) throws Exception {
		
		List<City> cityList = new ArrayList<City>();
		
		City city1 = new City();
		city1.setCityName("Bethlehem");
		city1.setCityId("PABLH");
		city1.setCityScale("Small");
		city1.setCityPopulation(109200);
		city1.setCityFoundationDate(new Date());
		
		City city2 = new City();
		city2.setCityName("Allentown");
		city2.setCityId("PAALT");
		city2.setCityScale("Small");
		city2.setCityPopulation(164800);
		city2.setCityFoundationDate(new Date());
		
		cityList.add(city1);
		cityList.add(city2);
		return cityList;
	}
}
