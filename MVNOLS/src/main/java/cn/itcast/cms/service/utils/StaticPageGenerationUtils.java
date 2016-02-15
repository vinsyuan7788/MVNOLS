package cn.itcast.cms.service.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.annotation.Resource;
import cn.itcast.global.utils.FileUtils;

/**
 * 	This is a static page generation utility class to get the Writer instance
 * @author Vince Xu Yuan
 */
public class StaticPageGenerationUtils {
	
	/*	IOP: IOC & DI	*/
	@Resource
	private FileUtils fileUtils;
	
	/**
	 * 	This is the method to get the Writer instance
	 * @param filePath
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public final Writer getWriter (String filePath, String encoding) {
		
		try {
			/*	Get the File instance from the real path	*/
			File file = new File(fileUtils.getRealPath(filePath));
			
			/*	Create the parent directory  */
			FileUtils.createParentDirectory(file);
			
			/*	Get the Writer object with the real path & return	*/
			return new OutputStreamWriter(new FileOutputStream(file), encoding);
		
		/*	If catch any checked exception, throw it out using RuntimeException  */
		} catch (Exception e) {
			throw new RuntimeException("Exception raised by \"OutputStreamWriter()\" or \"FileOutputStream()\"", e);
		}
	}
}
