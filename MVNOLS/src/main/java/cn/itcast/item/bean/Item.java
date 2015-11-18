package cn.itcast.item.bean;

import cn.itcast.global.configuration.BusinessConstants;
import cn.itcast.global.upload.utils.UploadUtils;

public class Item implements UploadUtils {
    private Integer id;

    private String itemName;

    private Float itemPrice;

    private String itemPlaceOfManufacture;

    private String itemImage;

    private String itemState;

    private String itemDetail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public Float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemPlaceOfManufacture() {
        return itemPlaceOfManufacture;
    }

    public void setItemPlaceOfManufacture(String itemPlaceOfManufacture) {
        this.itemPlaceOfManufacture = itemPlaceOfManufacture == null ? null : itemPlaceOfManufacture.trim();
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage == null ? null : itemImage.trim();
    }

    public String getItemState() {
        return itemState;
    }

    public void setItemState(String itemState) {
        this.itemState = itemState == null ? null : itemState.trim();
    }

    public String getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(String itemDetail) {
        this.itemDetail = itemDetail == null ? null : itemDetail.trim();
    }

	@Override
	public String getFullUploadURL() throws Exception {
		return BusinessConstants.UPLOAD_IMAGE_URL + itemImage;
	}
}