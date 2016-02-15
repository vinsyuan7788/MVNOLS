package cn.itcast.global.utils;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

/**
 * 	This is the utility class for file processing in web projects
 *  1. Any class implementing "ServletContextAware" will get the ServletContext object in Spring
 * @author Vince Xu Yuan
 */
public class FileUtils implements ServletContextAware {

	/*	This part is to obtain the ServletCotnext object from ServletContextAware	*/
	private ServletContext servletContext;
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	/**
	 * 	This is an instance method to get the real path of the directory in a web project
	 * @param directory
	 * @return
	 */
	public final String getRealPath (String directory) {
		return servletContext.getRealPath(directory);
	}
	
	/**
	 * 	This is a static method to create the parent directory of a file if the parent directory does not exist
	 * @param realPath
	 * @return
	 */
	public static final void createParentDirectory (File file) {
		
		/*	Create parent directory if the parent directory does not exist	*/
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		} 
	}

}
