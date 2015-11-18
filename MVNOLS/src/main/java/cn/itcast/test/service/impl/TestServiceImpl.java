package cn.itcast.test.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import cn.itcast.test.bean.City;
import cn.itcast.test.bean.CityExample;
import cn.itcast.test.bean.Country;
import cn.itcast.test.bean.State;
import cn.itcast.test.bean.StateExample;
import cn.itcast.test.dao.mapper.CityMapper;
import cn.itcast.test.dao.mapper.CountryMapper;
import cn.itcast.test.dao.mapper.StateMapper;
import cn.itcast.test.service.TestService;

/**
 * 	This is the service class to implement TestService interface
 */
public class TestServiceImpl implements TestService {

	/*	IOP: IOC & DI	*/
	@Resource
	private CountryMapper countryMapper;
	@Resource
	private StateMapper stateMapper;
	@Resource
	private CityMapper cityMapper;
	
	/**
	 * 	This is a service method to query all countries
	 */
	@Override
	public List<Country> queryAllCountries() throws Exception {
		
		/*	Query all countries: the query conditions is null	*/
		List<Country> countries = countryMapper.selectByExample(null);
		return countries;
	}

	/**
	 * 	This is a service method to query all states according to country id
	 */
	@Override
	public List<State> queryAllStates(Integer countryId) throws Exception {
		
		/*	Set the query conditions	*/
		StateExample stateExample = new StateExample();
		StateExample.Criteria criteria = stateExample.createCriteria();
		criteria.andCountryIdEqualTo(countryId);
		
		/*	Query all cities according to the query conditions & return	 */
		List<State> states = stateMapper.selectByExample(stateExample);
		return states;
	}

	/**
	 * 	This is a service method to query all cities according to state id
	 */
	@Override
	public List<City> queryAllCities(Integer stateId) throws Exception {
		
		/*	Set the query conditions  */
		CityExample cityExample = new CityExample();
		CityExample.Criteria criteria = cityExample.createCriteria();
		criteria.andStateIdEqualTo(stateId);
		
		/*	Query all cities according to the query conditions & return  */
		List<City> cities = cityMapper.selectByExample(cityExample);
		return cities;
	}
	
	/**
	 * 	This is a service method to test transaction
	 * @throws Exception
	 */
	@Override
	public void insertCountryForTransactionTesting() throws Exception {
		
		/*	Insert a country  */
		Country country = new Country();
		country.setCountryName("Brazil");
		countryMapper.insert(country);
		
		/*	Throw an exception	*/
		throw new RuntimeException();
	}

	/**
	 * 	This is a service method to conduct an experiment on returnURL acquisition from currentPageURL
	 */
	@Override
	public List<String> getReturnURLExperiment(String currentPageURL,
			String currentPageURLWithParameters, String parameterB, 
			String parameterC) throws Exception {

		/*	
		 * 	Experiment preparation:
		 * 	1. Put both current page URL into a map
		 * 	2. Generate a random number as a key to randomly pick one URL
		 */
		Map<Integer, String> currentPageURLMap = new HashMap<Integer, String>();
		currentPageURLMap.put(0, currentPageURL);
		currentPageURLMap.put(1, currentPageURLWithParameters);
		
		/*
		 *	Do the experiment:
		 *	1. Create a List object to store the returnURL results
		 *	2. Assuming receiving the current page URL 10 times, each time what is received may bring parameters or not.
		 *     Iteratively process the received current Page URL (i.e. "_currentPageURL") properly to generate a returnURL
		 *  3. Save the returnURL to the list.
		 */
		List<String> returnURLs = new ArrayList<String>();
		
		for (int i = 0; i < 10; i++) {
			String _currentPageURL = currentPageURLMap.get(new Random().nextInt(2));
			
			if (_currentPageURL.lastIndexOf("?") == -1) {
				String returnURL = _currentPageURL;
				returnURLs.add(returnURL);
			} else {
				String parameterName = _currentPageURL.substring(_currentPageURL.lastIndexOf("?")+1, _currentPageURL.lastIndexOf("="));
				String parameterValue = _currentPageURL.substring(_currentPageURL.lastIndexOf("=")+1);
				String returnURL = _currentPageURL.substring(0, _currentPageURL.lastIndexOf("?")) + 
					"?" + parameterName + "=" + parameterValue +
					"&ParameterB=" + parameterB +
					"&ParameterC=" + parameterC;
				returnURLs.add(returnURL);
			}
		}		
		
		/*	Experiment completed & return the returnURLs	*/
		return returnURLs;
	}

}
