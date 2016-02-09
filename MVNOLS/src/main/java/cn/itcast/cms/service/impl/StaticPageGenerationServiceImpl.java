package cn.itcast.cms.service.impl;

import java.io.Writer;
import java.util.Map;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import cn.itcast.cms.service.StaticPageGenerationService;
import cn.itcast.cms.service.utils.StaticPageGenerationUtils;
import cn.itcast.item.bean.Item;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 	This is a service class to generate static pages
 * 	1. Notice that "StaticPageGenerationUtils" is no need to configure in Spring
 *     -- Only "StaticPageGenerationUtils" is compiled by JVM to ".class" file, it can be extended
 *     -- Spring is only responsible for instantiation in IOP (i.e. IOC & DI), not compilation
 *        -- This is why if JVM fails to compile to ".class" files, Spring will always report errors regarding "ClassIsNotFound", refers to "about_regular_problems"
 *        
 * @author Vince Xu Yuan       
 */
public class StaticPageGenerationServiceImpl extends StaticPageGenerationUtils implements StaticPageGenerationService {

	/*	IOP: IOC & DI	*/
	private Configuration configuration;
	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
		this.configuration = freeMarkerConfigurer.getConfiguration();
	}
	
	/**
	 * 	This is a service method for static page generation
	 */
	public void freemarkerPageGeneration (Map<String, Object> dataModel, String filename) throws Exception {
		
		/*	Get the template	*/
		Template template = configuration.getTemplate("freemarker.html");
		
		/*	
		 * 	Get the writer:
		 * 	1. In this project, static pages are regulated to be put under "html" file & end with ".html"
		 * 	2. The write-out encoding adopts "UTF-8", same as the "defaultEncoding" configured in "spring/config/freemarker.xml" for write-in
		 */
		Writer out = this.getWriter("/html/" + filename + ".html", "UTF-8");
		
		/*	Generate the static page	*/
		template.process(dataModel, out);
		
		/*	Close the stream	*/
		if (out != null) {
			out.flush();
			out.close();
		}
	}

	/**
	 * 	This is a service method for static page generation
	 */
	@Override
	public void itemDetailPageGeneration(Map<String, Item> dataModel, Integer itemId) throws Exception {

		/*	Get the template	*/
		Template template = configuration.getTemplate("item/itemDetail.html");
		
		/*	
		 * 	Get the writer:
		 * 	1. In this project, static pages are regulated to be put under "html" file & end with ".html"
		 * 	2. The write-out encoding adopts "UTF-8", same as the "defaultEncoding" configured in "spring/config/freemarker.xml" for write-in
		 */
		Writer out = this.getWriter("/html/item/" + itemId + ".html", "UTF-8");
		
		/*	Generate the static page	*/
		template.process(dataModel, out);
		
		/*	Close the stream	*/
		if (out != null) {
			out.flush();
			out.close();
		}
	}
}
