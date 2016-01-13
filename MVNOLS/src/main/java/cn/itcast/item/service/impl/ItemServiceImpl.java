package cn.itcast.item.service.impl;

import java.util.List;

import javax.annotation.Resource;

import cn.itcast.item.bean.Item;
import cn.itcast.item.bean.ItemExample;
import cn.itcast.item.bean.wrapper.PageItemBean;
import cn.itcast.item.dao.mapper.ItemMapper;
import cn.itcast.item.service.ItemService;

public class ItemServiceImpl implements ItemService {

	/*	IOP: IOC & DI	*/
	@Resource
	private ItemMapper itemMapper;
	
	/**
	 * 	This is a service method to query the total of items by criteria
	 */
	@Override
	public int queryTotalItemsByCriteria(PageItemBean pageItemBean)
			throws Exception {
		
		int total = itemMapper.queryTotalItemsByCriteria(pageItemBean);
		return total;
	}

	/**
	 * 	This is a service method to query the information of total items by criteria
	 */
	@Override
	public List<Item> queryItemListByCriteria(PageItemBean pageItemBean)
			throws Exception {
		
		List<Item> items = itemMapper.queryItemListByCriteria(pageItemBean);
		return items;
	}

	/**
	 * 	This is a service method to query the item information by item ID
	 */
	@Override
	public Item queryItemById(Integer id) throws Exception {
		
		Item item = itemMapper.selectByPrimaryKey(id);
		return item;
	}

	/**
	 * 	This is a service method to set the item state to be "active"
	 * 	1. Create a new record & set the values for update
	 * 	2. Update the record with those not-null fields (i.e. selective fields)  
	 *  3. "selectByPrimaryKey()" method has included the query of BLOB field "itemDetail"  
	 */
	@Override
	public Item putItemOnSaleById(Integer id) throws Exception {
		
		/*	Set the update condition  */
		ItemExample itemExample = new ItemExample();
		ItemExample.Criteria criteria = itemExample.createCriteria();
		criteria.andIdEqualTo(id);
		
		/*	Update the item state	*/
		Item item = new Item();
		item.setItemState("active");
		itemMapper.updateByExampleSelective(item, itemExample);
		
		/*	Query the latest information of the user	*/
		Item latestItem = itemMapper.selectByPrimaryKey(id);
		return latestItem;
	}

	/**
	 * 	This is a service method to set the item state to be "unactive"
	 * 	1. Create a new record & set the values for update
	 * 	2. Update the record with those not-null fields (i.e. selective fields)  
	 */
	@Override
	public void putItemOffSaleById(Integer id) throws Exception {
		
		/*	Set the update condition  */
		Item item = new Item();
		item.setId(id);
		item.setItemState("unactive");
		
		/*	Update the item state	*/
		itemMapper.updateByPrimaryKeySelective(item);
	}

	/**
	 * 	This is a service method to re-update the item state to "active"
	 * 	1. Update the item state to "active", then to "active"
	 */
	@Override
	public Item republishItemById(Integer id) throws Exception {
		
		/*	Update the item state to "unactive", then to "active"	*/
		this.putItemOffSaleById(id);
		Item item = this.putItemOnSaleById(id);
		return item;
	}
	
	/**
	 * 	This is a service method to update the state of items to "active" & query the updated information of the items
	 * 	1. Since Item class has an "itemDetail" field with BLOB type in database & it needs to be queried, hence:
	 *     -- Adopt "selectByExampleWithBLOBs()" method
	 *  2. When update the record, "itemDetail" field is not involved, hence just adopt "updateByExampleSelective()" method
	 */
	@Override
	public List<Item> putItemsOnSaleByIds(List<Integer> ids) throws Exception {
		
		/*	Set the update condition	*/
		ItemExample itemExample = new ItemExample();
		ItemExample.Criteria criteria = itemExample.createCriteria();
		criteria.andIdIn(ids);
		
		/*	Update the item state	*/
		Item item = new Item();
		item.setItemState("active");
		itemMapper.updateByExampleSelective(item, itemExample);
		
		/*	Query the latest information of these items	 */
		List<Item> items = itemMapper.selectByExampleWithBLOBs(itemExample);
		return items;
	}
	
	/**
	 * 	This is a service method to update the state of items to "unactive"
	 *  1. When update the record, "itemDetail" field is not involved, hence just adopt "updateByExampleSelective()" method
	 */
	@Override
	public void putItemsOffSaleByIds(List<Integer> ids) throws Exception {
		
		/*	Set the update condition	*/
		ItemExample itemExample = new ItemExample();
		ItemExample.Criteria criteria = itemExample.createCriteria();
		criteria.andIdIn(ids);
		
		/*	Update the item state	*/
		Item item = new Item();
		item.setItemState("unactive");
		itemMapper.updateByExampleSelective(item, itemExample);
	}

	/**
	 * 	This is a service method to re-update the state of items ot "active"
	 * 	1. Update the state of items to "unactive", then to "active"
	 */
	@Override
	public List<Item> republishItemsByIds(List<Integer> ids) throws Exception {
		
		/*	Update the state of items to "unactive", then to "active"	*/
		this.putItemsOffSaleByIds(ids);
		List<Item> items = this.putItemsOnSaleByIds(ids);
		return items;
	}
	
	/**
	 * 	This is a service method to add a new item
	 * 	1. When new item is added, it is "unactive" state
	 */
	@Override
	public void addNewItem(Item item) throws Exception {
		
		/*	Set the state for item	*/
		item.setItemState("unactive");
		
		/*	Add the item into database	*/
		itemMapper.insert(item);
	}
}
