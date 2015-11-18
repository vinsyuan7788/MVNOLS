package cn.itcast.test.dao;

import cn.itcast.user.bean.User;

/**
 * 	The regular way for DAO development:
 *	This is the DAO interface
 */
public interface UserDao {

	public User queryUserById (int id) throws Exception;
}
