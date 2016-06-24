package cn.itcast.test.others.javaweb.testclass.webservice.jaxws.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import cn.itcast.test.others.javaweb.testclass.webservice.jaxws.client.server.Weather;

/**
 * 	This is the client class to invoke the SOAP web service using CXF
 *  -- CXF can be merged with Spring
 */
public class WeatherClient {
	
	/**
	 * 	This is the main function to invoke the SOAP web service class method
	 * 	1. This code demonstrates the steps of invoking web service using CXF through programming
	 *     -- This client is not runnable, while the server is runnable, since the client should be separated into another project
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		/*	Use JaxWsProxyFactoryBean to invoke SOAP web service	 */
		JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
		
		/*	Specify the address of web service	*/
		jaxWsProxyFactoryBean.setAddress("http://127.0.0.1:12345/weather?wsdl");
		
		/*	Specify the class of web service (or portType)	*/
		jaxWsProxyFactoryBean.setServiceClass(Weather.class);
		
		/*	Get the object of web service	*/
		Weather weatherImpl = (Weather) jaxWsProxyFactoryBean.create();
		
		/*	Invoke the method provided by web service	*/
		String weather = weatherImpl.queryWeather("Bethlehem");
		System.out.println("The weather: " + weather);
	}
}
