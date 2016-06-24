package cn.itcast.test.others.javaweb.testclass.webservice.jaxws.server;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import cn.itcast.test.others.javaweb.testclass.webservice.jaxws.server.impl.WeatherImpl;
import cn.itcast.test.others.javaweb.testclass.webservice.jaxws.server.interfaces.Weather;

/**
 * 	This is a server class to publish SOAP web service using CXF
 */
public class WeatherServer {

	/**
	 * 	This is a main function to publish the SOAP web service class
	 * 	1. This code demonstrates the steps of publishing web service using CXF through programming
	 *     -- This server is runnable, while the corresponding client is not, since the client should be separated into another project
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*	Use JaxWsServerFactoryBean to publish SOAP web service	*/
		JaxWsServerFactoryBean jaxWsServerFactoryBean = new JaxWsServerFactoryBean();
		
		/*	Specify the address of web service	*/
		jaxWsServerFactoryBean.setAddress("http://127.0.0.1:12345/weather");
		
		/*	Specify the class of web service (or portType)	*/
		jaxWsServerFactoryBean.setServiceClass(Weather.class);
	
		/*	Specify the object of web service	*/
		jaxWsServerFactoryBean.setServiceBean(new WeatherImpl());
		
		/*	Publish the web service	 */
		jaxWsServerFactoryBean.create();
	}
}
