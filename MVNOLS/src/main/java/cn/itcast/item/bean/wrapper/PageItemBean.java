package cn.itcast.item.bean.wrapper;

import cn.itcast.global.pagination.bean.PageBean;
import cn.itcast.item.bean.Item;

/**
 * 	This is a JavaBean for comprehensive query of items
 */
public class PageItemBean extends PageBean<Item> {
	
	private String itemName;
    private String itemPriceInterval;
    private String itemState;
    
	public String getItemState() {
		return itemState;
	}
	public void setItemState(String itemState) {
		this.itemState = itemState;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemPriceInterval() {
		return itemPriceInterval;
	}
	public void setItemPriceInterval(String itemPriceInterval) {
		this.itemPriceInterval = itemPriceInterval;
	}
}
