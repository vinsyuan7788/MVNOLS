package cn.itcast.user.bean.wrapper;

import java.io.Serializable;

import cn.itcast.user.bean.User;

/**
 * 	This is the JavaBeans wrapper for convenience of JavaBean extension 
 *  1. Contains some extended fields
 *  2. In this case: this class will be contained in QueryWrapper for comprehensive query
 */
public class UserWrapper extends User implements Serializable {

	private String groupid;

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	@Override
	public String toString() {
		return "UserWrapper [groupid=" + groupid + "]";
	}
}
