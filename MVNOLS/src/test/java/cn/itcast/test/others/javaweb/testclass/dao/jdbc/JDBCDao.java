package cn.itcast.test.others.javaweb.testclass.dao.jdbc;

import java.util.List;

import cn.itcast.test.others.javaweb.testclass.dao.bean.User;

/**
 * 	This is a DAO interface for testing
 * @author Vince Xu Yuan
 */
public interface JDBCDao {
 
	public List<User> queryUsers (String username, Float score) throws Exception;
}
