package cn.itcast.item.service;

import java.util.List;

import cn.itcast.item.bean.Item;
import cn.itcast.item.bean.wrapper.PageItemBean;

/**
 * 	This is the service interface
 * @author Vince Xu Yuan
 */
public interface ItemService {

	public int queryTotalItemsByCriteria(PageItemBean pageItemBean) throws Exception;

	public List<Item> queryItemListByCriteria(PageItemBean pageItemBean) throws Exception;

	public Item queryItemById(Integer id) throws Exception;

	public Item putItemOnSaleById(Integer id) throws Exception;

	public void putItemOffSaleById(Integer id) throws Exception;

	public List<Item> putItemsOnSaleByIds(List<Integer> ids) throws Exception;

	public void putItemsOffSaleByIds(List<Integer> ids) throws Exception;
	
	public void addNewItem(Item item) throws Exception;

	public Item republishItemById(Integer id) throws Exception;

	public List<Item> republishItemsByIds(List<Integer> ids) throws Exception;

}
