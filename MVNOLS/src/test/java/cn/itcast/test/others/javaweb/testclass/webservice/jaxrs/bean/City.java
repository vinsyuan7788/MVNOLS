package cn.itcast.test.others.javaweb.testclass.webservice.jaxrs.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 	This is the JavaBean for RESTful WS (Web Service)
 * 	1. Use annotation for JavaBean binding: similar as request binding in SpringMVC
 */
@XmlRootElement(name="city")
public class City {

	private String cityName;
	private String cityId;
	private String cityScale;
	private Integer cityPopulation;
	private Date cityFoundationDate;
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public Integer getCityPopulation() {
		return cityPopulation;
	}
	public void setCityPopulation(Integer cityPopulation) {
		this.cityPopulation = cityPopulation;
	}
	public Date getCityFoundationDate() {
		return cityFoundationDate;
	}
	public void setCityFoundationDate(Date cityFoundationDate) {
		this.cityFoundationDate = cityFoundationDate;
	}
	public String getCityScale() {
		return cityScale;
	}
	public void setCityScale(String cityScale) {
		this.cityScale = cityScale;
	}
}
