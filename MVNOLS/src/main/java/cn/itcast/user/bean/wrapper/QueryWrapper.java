package cn.itcast.user.bean.wrapper;

import java.io.Serializable;
import java.util.Arrays;

import cn.itcast.user.bean.User;

/**
 * 	This is JavaBean wrapper for comprehensive query (*****)
 *  1. Useful if the query condition is cross-JavaBean, namely involves the fields of multiple JavaBeans
 *     -- e.g. the query condition involves some fields in User and some fields in other JavaBeans
 *     -- Such a JavaBean Wrapper can be re-used by 
 *     (1) different statements (i.e. different <select><insert><update><delete> in xxxMapper.xml)
 *     (2) different SQL fragments (i.e. diferent <sql> in xxxMapper.xml)
 *  2. Can contain the JavaBeans or|and the extension of JavaBeans
 *     -- e.g. can contain "User user" or|and "UserExtension userExtension" (latter one is for convenience of extension)
 *  3. Can be also named as: xxxVO (Value Object)|POJO (Plain Old Java Ojbect)|Entity...
 */
public class QueryWrapper implements Serializable {

	/*	Define various JavaBean or fields here for comprehensive query 	*/
	private User user;
	private UserWrapper userWrapper;
	private int[] ids;
	
	public int[] getIds() {
		return ids;
	}
	public void setIds(int[] ids) {
		this.ids = ids;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserWrapper getUserWrapper() {
		return userWrapper;
	}
	public void setUserWrapper(UserWrapper userWrapper) {
		this.userWrapper = userWrapper;
	}
	@Override
	public String toString() {
		return "QueryWrapper [user=" + user + ", userWrapper=" + userWrapper
				+ ", ids=" + Arrays.toString(ids) + "]";
	}

}

