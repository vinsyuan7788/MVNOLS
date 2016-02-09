package cn.itcast.test.dao;

import cn.itcast.user.bean.User;

/**
 * 	The regular way for DAO development: this is the DAO interface
 * @author Vince Xu Yuan
 */
public interface UserDao {

	public User queryUserById (int id) throws Exception;
}
