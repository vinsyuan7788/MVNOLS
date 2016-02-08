package cn.itcast.cms.service.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

/**
 * 	This is a static page generation utility class that can be extended to get the real path & writer
 * 	1. Any class implementing "ServletContextAware" will get the ServletContext object in Spring
 */
public class StaticPageGenerationUtils implements ServletContextAware {

	/*	This part is to obtain the ServletCotnext object from ServletContextAware	*/
	private ServletContext servletContext;
	@Override
	public final void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	/**
	 * 	This is a method to get the real path through ServletContext instance
	 */
	public final String getRealPath (String directory) throws Exception {
		return servletContext.getRealPath(directory);
	}
	
	/**
	 * 	This is a method to get the Writer object  
	 */
	public final Writer getWriter (String filePath, String encoding) throws Exception {
		
		/*	Get the real path of the directory	*/
		String realPath = this.getRealPath(filePath);
		
		/*	Create the parent directory  */
		File file = new File(realPath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		} 
		
		/*	Get the Writer object with the real path & return	*/
		return new OutputStreamWriter(new FileOutputStream(file), encoding);
	}
}
