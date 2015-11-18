package cn.itcast.nationality.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cn.itcast.nationality.bean.Nationality;
import cn.itcast.nationality.dao.mapper.NationalityMapper;
import cn.itcast.nationality.service.NationalityService;

public class NationalityServiceImpl implements NationalityService {

	/*	IOP: IOC & DI	*/
	@Resource
	private NationalityMapper nationalityMapper;
	
	/**
	 * 	This is a service method to query nationality
	 * 	1. First query all nationality JavaBean
	 *  2. Then get the nationality from the JavaBean & save them into a new list
	 */
	@Override
	public List<String> queryNationalities() throws Exception {
		
		/*	"null" means no where conditions for query (i.e. query all records)	*/
		List<Nationality> nationalityBeanList = nationalityMapper.selectByExample(null);
		
		/*	Create a new list to store the nationality (i.e. remove the id)	*/
		List<String> nationalities = new ArrayList<String>();
		for (Nationality nationality : nationalityBeanList) {
			nationalities.add(nationality.getNationality());
		}
		
		/*	Return the new list	*/
		return nationalities;
	}

}
