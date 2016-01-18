package cn.itcast.test.others.javaweb.testclass.webservice.jaxrs.server.interfaces;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cn.itcast.test.others.javaweb.testclass.webservice.jaxrs.bean.City;

/**
 * 	This is a SEI (Service Endpoint Interface)
 * 	1. Use annotation to change the default name space in the interface when using CXF for web service
 * 	2. Use annotation to specify the RESTful URL path
 */
@WebService(targetNamespace="http://server.client.jaxrs.webservice.testclass.javaweb.others.test.itcast.cn/")
@Path("/city")
public interface CityService {

	/**
	 * 	This is the service method to query city information
	 *  1. @GET: specify "get" method in HTTP
	 *  2. @Produce: set the media type of the return result
	 *     -- Pay attention to encoding problem 
	 *  3. @Path & @PathParameter: map the parameter as path
	 * @param cityName
	 * @return
	 * @throws Exception
	 */
	@GET
	@Produces({"application/json;charset=utf-8", MediaType.APPLICATION_XML})
	@Path("/{cityName}")
	public City queryCity (@PathParam("cityName") String cityName) throws Exception;
	
	/**
	 * 	This is the service method to query information of cities
	 * @param cityScale
	 * @return
	 * @throws Exception
	 */
	@GET
	@Produces({"application/json;charset=utf-8", MediaType.APPLICATION_XML})
	@Path("/list/{cityScale}")
	public List<City> queryCities (@PathParam("cityScale") String cityScale) throws Exception;
}
