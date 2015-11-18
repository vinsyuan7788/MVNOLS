package cn.itcast.user.bean.wrapper;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

import cn.itcast.global.pagination.bean.PageBean;
import cn.itcast.user.bean.User;

/**
 * 	This is a pagination bean for comprehensive query of user
 * 	1. This class contains the fields for comprehensive query of user, extending PageBean<User> class
 *  2. If there are query conditions, then corresponding action method & mapper.xml should be modified
 */
public class PageUserBean extends PageBean<User> {
	
    private String username;
    private String gender;
    private String state;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "PageUserBean [username=" + username + ", gender=" + gender
				+ ", state=" + state + "]";
	}
}