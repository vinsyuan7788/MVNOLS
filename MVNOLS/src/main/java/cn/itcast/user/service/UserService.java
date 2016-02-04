package cn.itcast.user.service;

import java.util.List;

import cn.itcast.global.pagination.bean.PageBean;
import cn.itcast.user.bean.User;
import cn.itcast.user.bean.Userupload;
import cn.itcast.user.bean.wrapper.PageUserBean;

/**
 * 	This is the service interface
 */
public interface UserService {

	public User queryUserById (int id) throws Exception;
	public void insertAndUpdateUsers(User user1, User user2) throws Exception;		// ONLY for testing transaction
	public int queryTotalUsers() throws Exception;
	public List<User> queryAllUsers () throws Exception;
	public void insertUser(User user) throws Exception;
	public void updateUser(User user) throws Exception;
	public void insertUserupload(Userupload userupload) throws Exception;
	public void updateUser(User user, Userupload userupload) throws Exception;
	public void insertUser(User user, Userupload userupload) throws Exception;
	public void activateUser(String activationuuid) throws Exception;
	public List<User> queryUserByLoginInfo(User user) throws Exception;
	public int queryUserByLoginInfo(String username, String password) throws Exception;
	public int queryUserbyUsername(User user) throws Exception;
	public int queryUserbyUsername(String username) throws Exception;
	public List<User> queryUserList(PageBean<User> pageBean) throws Exception;
	public int queryTotalUsersByCriteria(PageUserBean pageUserBean) throws Exception;
	public List<User> queryUserListByCriteria(PageUserBean pageUserBean) throws Exception;
	public void deleteUsersById(List<Integer> ids) throws Exception;
	public List<User> recoverUsersById(List<Integer> ids) throws Exception;
	public List<User> queryUsersByIds(List<Integer> ids) throws Exception;
}
