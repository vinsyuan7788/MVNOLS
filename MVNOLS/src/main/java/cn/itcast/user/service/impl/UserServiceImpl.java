package cn.itcast.user.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import cn.itcast.global.exception.CustomException;
import cn.itcast.global.pagination.bean.PageBean;
import cn.itcast.user.bean.User;
import cn.itcast.user.bean.UserExample;
import cn.itcast.user.bean.Userupload;
import cn.itcast.user.bean.wrapper.PageUserBean;
import cn.itcast.user.dao.mapper.UserMapper;
import cn.itcast.user.dao.mapper.UseruploadMapper;
import cn.itcast.user.service.UserService;

/**
 * 	This is the implementation of service interface
 */
public class UserServiceImpl implements UserService {

	/*	IOP: IOC & DI	*/
	@Resource
	private UserMapper userMapper;
	@Resource
	private UseruploadMapper useruploadMapper;
	
	/**
	 * 	This is a service method
	 */
	@Override
	public User queryUserById(int id) throws Exception {
		return userMapper.selectByPrimaryKey(id);
	}

	/**
	 * 	This is a service method
	 */
	@Override
	public void insertAndUpdateUsers(User user1, User user2) throws Exception {
		/*
		 * 	In this method, update another user with an existing name in DB
		 *  Hence will result in a transaction roll-back if the transaction is correctly configured
		 */
		userMapper.insert(user1);
		userMapper.updateByPrimaryKeySelective(user2);
	}

	/**
	 * 	This is a service method
	 */
	@Override
	public int queryTotalUsers() throws Exception {
		return userMapper.countByExample(null);
	}

	/**
	 * 	This is a service method
	 */
	@Override
	public List<User> queryAllUsers() throws Exception {
		return userMapper.selectByExample(null);
	}

	/**
	 * 	This is a service method
	 */
	@Override
	public void insertUser(User user) throws Exception {
		userMapper.insert(user);
	}

	/**
	 * 	This is a service method
	 */
	@Override
	public void updateUser(User user) throws Exception {
		userMapper.updateByPrimaryKeySelective(user);
	}

	/**
	 * 	This is a service method
	 */
	@Override
	public void insertUserupload(Userupload userupload) throws Exception {
		useruploadMapper.insert(userupload);
	}
	
	/**
	 * 	This is a service method
	 */
	@Override
	public void insertUser(User user, Userupload userupload) throws Exception {
		if (userupload.getUploadfile() == null) {
			userMapper.insert(user);
		} else {
			userMapper.insert(user);
			useruploadMapper.insert(userupload);
		}
	}
	
	/**
	 * 	This is a service method to update user information
	 * 	1. If no upload file, userupload is null, then just update user information
	 *  2. If there is upload file, then update user information & insert a new record into userupload table
	 *  3. The update method here MUST be "updateByPrimaryKey" instead of "updateByPrimaryKeySelective"
	 *     -- Otherwise user cannot update the information as NULL in the database
	 *     -- Those fields that can not be NULL (e.g. id and roleid) should be put in <input type = "hidden" .../> tags in JSP view, such that these information will not get lost during the udpate
	 */
	@Override
	public void updateUser(User user, Userupload userupload) throws Exception {
		
		/*	If there is no upload file, just update user information	*/
		if (userupload.getUploadfile() == null) {
			userMapper.updateByPrimaryKey(user);
		
		/*	If there is an upload file, then update user information & insert a new record to userupload table	*/
		} else {
			userMapper.updateByPrimaryKey(user);
			useruploadMapper.insert(userupload);
		}
	}

	/**
	 * 	This is a service method to activate user
	 * 	1. If cannot find the user according to activation code: the activation code is invalid
	 *     -- throw an exception
	 *  2. If the state of user found by activation code is "active": then repeat activation
	 *     -- throw an exception
	 *  3. If use is found & user state is "unactive", then update to "active"
	 */
	@Override
	public User activate(String activationuuid) throws Exception {
		
		/*	Query user according to activation code	*/
		UserExample userExample = new UserExample();
		UserExample.Criteria criteria = userExample.createCriteria();
		criteria.andActivationuuidEqualTo(activationuuid);
		List<User> users = userMapper.selectByExample(userExample);

		/*	If there is no result, then activation code is invalid	 */
		if (users.size() == 0) {
			throw new CustomException("The activation code is invalid!");
		}
		
		/*	If the user state is "active", then the activation repeats	*/
		User user = users.get(0);			// UUID is not unique, hence List<user> contains ONLY 1 user.
		if (user.getState().equals("active")) {
			throw new CustomException("Current use is activated. Do not repeat Activation!");
		}
		
		/*	If user is found & user state is "unactive", then update to "active"	*/
		user.setState("active");
		userMapper.updateByExampleSelective(user, userExample);
		
		return null;
	}

	/**
	 * 	This is a service method to query if there is a user according to login information
	 */
	@Override
	public List<User> queryUserByLoginInfo(User user) throws Exception {
		
		/*	Set the query conditions	*/
		UserExample userExample = new UserExample();
		UserExample.Criteria criteria = userExample.createCriteria();
		criteria.andUsernameEqualTo(user.getUsername());
		criteria.andPasswordEqualTo(user.getPassword());
		
		/*	Query if there is any existing user according to query condition	*/
		List<User> existingUsers = userMapper.selectByExample(userExample);
		return existingUsers;
	}

	/**
	 * 	This is a method to query if there is a user according to username
	 */
	@Override
	public int queryUserbyUsername(User user) throws Exception {
		
		/*	Set the query conditions   */
		UserExample userExample = new UserExample();
		UserExample.Criteria criteria = userExample.createCriteria();
		criteria.andUsernameEqualTo(user.getUsername());
		
		/*	Query if there is any existing user according to username	*/
		return userMapper.countByExample(userExample);
	}

	/**
	 * 	This is a service method
	 */
	@Override
	public int queryUserbyUsername(String username) throws Exception {
		
		UserExample userExample = new UserExample();
		UserExample.Criteria criteria = userExample.createCriteria();
		criteria.andUsernameEqualTo(username);
		
		return userMapper.countByExample(userExample);
	}

	/**
	 * 	This is a service method
	 */
	@Override
	public int queryUserByLoginInfo(String username, String password)
			throws Exception {
		
		/*	Set the query conditions	*/
		UserExample userExample = new UserExample();
		UserExample.Criteria criteria = userExample.createCriteria();
		criteria.andUsernameEqualTo(username);						// set username
		criteria.andPasswordEqualTo(password);						// set password
		criteria.andStateIn(Arrays.asList("unactive", "active"));	// set state
		
		/*	Do the query	*/
		return userMapper.countByExample(userExample);
	}

	/**
	 * 	This is a service method for paging query of user
	 */
	@Override
	public List<User> queryUserList(PageBean<User> pageBean) throws Exception {
		
		/*	Paging query the user	*/
		List<User> users = userMapper.queryUserList(pageBean);
		return users;
	}

	/**
	 * 	This is a service method to query total of users by criteria
	 */
	@Override
	public int queryTotalUsersByCriteria(PageUserBean pageUserBean) throws Exception {
		int total = userMapper.queryTotalUserByCriteria(pageUserBean);
		return total;
	}

	/**
	 * 	This is a service method to query the information of total users by criteria
	 */
	@Override
	public List<User> queryUserListByCriteria(PageUserBean pageUserBean)
			throws Exception {
		
		List<User> users = userMapper.queryUserListByCriteria(pageUserBean);
		return users;
	}

	/**
	 * 	This is a service method to delete the user
	 *  1. When deleting the user, the user is not actually removed from database:
	 *     -- The state of those deleted users to be "disabled"
	 */
	@Override
	public void deleteUsersById(List<Integer> ids) throws Exception {
		
		/*	Set the update conditions	*/
		UserExample userExample = new UserExample();
		UserExample.Criteria criteria = userExample.createCriteria();
		criteria.andIdIn(ids);
		
		/*	Update the state to be "disabled" for selected users	*/
		User user = new User();
		user.setState("disabled");
		userMapper.updateByExampleSelective(user, userExample);
	}

	/**
	 * 	This is a service method to recover users in batches
	 * 	1. When recovering a user, the user state will be set "unactive"
	 *  2. Then query the information of these recovered users
	 */
	@Override
	public List<User> recoverUsersById(List<Integer> ids) throws Exception {
		
		/*	Set the update conditions	*/
		UserExample userExample = new UserExample();
		UserExample.Criteria criteria = userExample.createCriteria();
		criteria.andIdIn(ids);
		
		/*	Update the state to be "unactive" for selected users	*/
		User user = new User();
		user.setState("unactive");
		userMapper.updateByExampleSelective(user, userExample);
		
		/*	Query the selected users & return	*/
		List<User> users = userMapper.selectByExample(userExample);
		return users;
	}
	
	/**
	 * 	This is a service method to query information of users in batches
	 */
	@Override
	public List<User> queryUsersByIds(List<Integer> ids) throws Exception {
		
		/*	Set the query conditions	*/
		UserExample userExample = new UserExample();
		UserExample.Criteria criteria = userExample.createCriteria();
		criteria.andIdIn(ids);
		
		/*	Query the selected users & return	*/
		List<User> users = userMapper.selectByExample(userExample);
		return users;
	}
}
