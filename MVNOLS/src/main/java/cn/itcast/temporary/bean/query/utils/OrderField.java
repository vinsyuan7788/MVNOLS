package cn.itcast.temporary.bean.query.utils;

public class OrderField {

	public String orderFieldName;
	public boolean isAsc;

	public String getOrderFieldName() {
		return orderFieldName;
	}

	public boolean isAsc() {
		return isAsc;
	}

	public OrderField(String orderFieldName, boolean isAsc) {
		this.orderFieldName = orderFieldName;
		this.isAsc = isAsc;
	}
}
