package cn.itcast.test.service;

import java.util.List;

import cn.itcast.test.bean.City;
import cn.itcast.test.bean.Country;
import cn.itcast.test.bean.State;

/**
 * 	This is the service interface for implementation
 */
public interface TestService {

	public List<Country> queryAllCountries() throws Exception;

	public List<State> queryAllStates(Integer countryId) throws Exception;

	public List<City> queryAllCities(Integer stateId) throws Exception;

	public void insertCountryForTransactionTesting() throws Exception;

	public List<String> getReturnURLExperiment(String currentPageURL,
			String currentPageURLWithParameters, String parameterB,
			String parameterC) throws Exception;
}
