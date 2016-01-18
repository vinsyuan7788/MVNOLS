package cn.itcast.test.others.javaweb.testclass.webservice.jaxws.server.interfaces;

import javax.jws.WebService;

/**
 * 	This is a SEI (Service Endpoint Interface)
 * 	1. Use annotation to change the default name space in the interface when using CXF for web service
 */
@WebService(targetNamespace="http://server.client.jaxws.webservice.testclass.javaweb.others.test.itcast.cn/")
public interface Weather {

	public String queryWeather (String city) throws Exception;
}
