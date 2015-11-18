package cn.itcast.item.action;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import cn.itcast.cms.service.StaticPageGenerationService;
import cn.itcast.item.bean.Item;
import cn.itcast.item.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemAction {
	
	/*	IOP: IOC & DI	*/
	@Resource
	private ItemService itemService;
	@Resource
	private StaticPageGenerationService staticPageGenerationService;
	
	/**
	 * 	This is an action method to query the item information by item ID
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryItemById")
	public String queryItemById (Integer id, Model model) throws Exception {

		Item item = itemService.queryItemById(id);
		
		model.addAttribute("item", item);
		return "admin/item/itemDetail";
	}
	
	/**
	 * 	This is an action method to put one item on sale
	 * 	1. Update the state of this item to "active"
	 * 	2. Generate the static page for this item
	 * @param item
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/putItemOnSale")
	public String putItemOnSale (Integer id, Model model) throws Exception {
		
		/*	Update the state of this item	*/
		Item item = itemService.putItemOnSaleById(id);
		
		/*	Generate the static page for this item	*/
		Map<String, Item> dataModel = new HashMap<String, Item>();
		dataModel.put("item", item);
		staticPageGenerationService.itemDetailPageGeneration(dataModel, item.getId());
		
		/*	Save success message & return	*/
		model.addAttribute("successMessage", "Put the item on sale successfully");
		return "forward:/redirection/success.action";
	}
	
	/**
	 * 	This is an action method to put one item off sale
	 * 	1. Update the state of item to "unactive"
	 * @param item
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/putItemOffSale")
	public String putItemOffSale (Item item, Model model) throws Exception {
		
		/*	Update the state of this item	*/
		itemService.putItemOffSaleById(item.getId());
		
		/*	Save success message & return	*/
		model.addAttribute("successMessage", "Put the item off sale successfully");
		return "forward:/redirection/success.action";
	}

	/**
	 * 	This is an action method to put multiple items on sale
	 * 	1. Update the state of this item to "active"
	 * 	2. Generate the static page for this item
	 * @param checkedId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/putItemsOnSale")
	public String putItemsOnSale (Integer[] checkedId, Model model) throws Exception {
		
		/*	Transform the array to List	 */
		List<Integer> ids = Arrays.asList(checkedId);
		
		/*	Update the state of the items & return the corresponding information of the items	*/
		List<Item> items = itemService.putItemsOnSaleByIds(ids);
		
		/*	Generate the static pages for each item respectively  */
		Map<String, Item> dataModel = new HashMap<String, Item>();
		for (Item item : items) {
			dataModel.put("item", item);
			staticPageGenerationService.itemDetailPageGeneration(dataModel, item.getId());
		}
		
		/*	Save success message & return	*/
		model.addAttribute("successMessage", "Put the items on sale successfully");
		return "forward:/redirection/success.action";
	}
	
	/**
	 * 	This is an action method to put multiple items off sale
	 *  1. Update the state of item to "unactive"
	 * @param checkedId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/putItemsOffSale")
	public String putItemsOffSale (Integer[] checkedId, Model model) throws Exception {

		/*	Transform the array to List	 */
		List<Integer> ids = Arrays.asList(checkedId);
		
		/*	Update the state of items	*/
		itemService.putItemsOffSaleByIds(ids);
		
		/*	Save success message & return	*/
		model.addAttribute("successMessage", "Put the items off sale successfully");
		return "forward:/redirection/success.action";
	}
	
	/**
	 * 	This is an action method to add a new item
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addItem")
	public String addItem (Item item, MultipartFile uploadImage, Model model) throws Exception {
		
		/*	Process the "itemImage" field if there is actually no upload image	*/
		if (uploadImage.getSize() == 0) {
			item.setItemImage(null);
		}
		
		/*	Add a new item	*/
		itemService.addNewItem(item);
		
		/*	Save success message & return	*/
		model.addAttribute("successMessage", "Your item is added successfully");
		return "forward:/redirection/success.action"; 
	}
}
