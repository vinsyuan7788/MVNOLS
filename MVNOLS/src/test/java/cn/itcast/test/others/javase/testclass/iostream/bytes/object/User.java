package cn.itcast.test.others.javase.testclass.iostream.bytes.object;

import java.io.Serializable;

/**
 * 	This is a JavaBean to be instantiated for writing out to a file on disk
 * 	1. To be written out a file on disk, MUST be implement Serializable
 * @author Vince Xu Yuan
 */
public class User implements Serializable  {

	/**
	 * 	Specify a default serial version ID, so the ID won't be calculated automatically
	 * 	1. To avoid the ID imcompatibility if the class is modified and become incompatible with the serialized class on disk
	 */
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	private String gender;
	private transient double income;		// Use "transient" to skip the serialization
	private Address address;				// Any class maintained by the class that will be serialized MUST implement Serializable as well
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password
				+ ", gender=" + gender + ", income=" + income + ", address="
				+ address + "]";
	}
}
