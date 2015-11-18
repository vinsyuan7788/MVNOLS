package cn.itcast.cms.service;

import java.util.Map;

import cn.itcast.item.bean.Item;

/**
 * 	This interface is to offer method for static page generation
 */
public interface StaticPageGenerationService {

	public void freemarkerPageGeneration (Map<String, Object> dataModel, String fileName) throws Exception;
	public void itemDetailPageGeneration (Map<String, Item> dataModel, Integer itemId) throws Exception;
}
