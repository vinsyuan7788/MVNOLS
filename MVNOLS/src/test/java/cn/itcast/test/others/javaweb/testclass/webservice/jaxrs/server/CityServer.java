package cn.itcast.test.others.javaweb.testclass.webservice.jaxrs.server;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

import cn.itcast.test.others.javaweb.testclass.webservice.jaxrs.server.impl.CityServiceImpl;

/**
 * 	This is the city server to publish RESTful web service
 */
public class CityServer {

	/**
	 * 	This is a main function to publish RESTful web service
	 * 	1. This code demonstrates the order of publishing web service using CXF through programming
	 *     -- This server is runnable
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*	Use JAXRSServerFactoryBean to publish RESTful web service	*/
		JAXRSServerFactoryBean jaxrsServerFactoryBean = new JAXRSServerFactoryBean();
		
		/*	Specify the address of web service	*/
		jaxrsServerFactoryBean.setAddress("http://127.0.0.1:12345/rest");
	
		/*	Specify the object of web service class	*/
		jaxrsServerFactoryBean.setServiceBean(new CityServiceImpl());
		
		/*	Specify the resource of web service class	*/
		jaxrsServerFactoryBean.setResourceClasses(CityServiceImpl.class);
		
		/*	Publish the web service	 */
		jaxrsServerFactoryBean.create();
	}
}
