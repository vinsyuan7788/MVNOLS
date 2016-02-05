package cn.itcast.test.others.javase.testclass.iostream.bytes.object;

import java.io.Serializable;

/**
 * 	This is the class to provide the address for User class
 * 	1. Since the class is maintained by User (which is serializible & will be serialized), this class MUST implement Serializable as well
 * @author Vince Xu Yuan
 */
public class Address implements Serializable {

	private String country;
	private String city;
	private String street;
	
	public Address(String country, String city, String street) {
		super();
		this.country = country;
		this.city = city;
		this.street = street;
	}

	@Override
	public String toString() {
		return "Address [country=" + country + ", city=" + city + ", street="
				+ street + "]";
	}
}
