package cn.itcast.temporary.bean.query.bean;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.temporary.bean.query.utils.ConditionField;
import cn.itcast.temporary.bean.query.utils.OrderField;
import cn.itcast.temporary.bean.query.utils.QueryField;

/**
 * 	This is the class for the SQL in DAO mapper
 * @author Vince Xu Yuan
 */
public class QueryBean {

	/*	For selector  */
	private List<QueryField> queryFields = new ArrayList<QueryField>();

	public List<QueryField> getQueryFields() {
		return queryFields;
	}
	public void setQueryFieldName(String queryFieldName) {
		queryFields.add(new QueryField(queryFieldName));
	}
	
	/*	For conditions	*/
	private List<ConditionField> conditionFields = new ArrayList<ConditionField>();

	public List<ConditionField> getConditionFields() {
		return conditionFields;
	}
	public void setConditionField(String conditionFieldName, String conditionFieldValue, boolean isLike) {
		conditionFields.add(new ConditionField(conditionFieldName, conditionFieldValue, isLike));
	}
	
	/*	For ORDER	*/
	private List<OrderField> orderFields = new ArrayList<OrderField>();
	
	public List<OrderField> getOrderFields() {
		return orderFields;
	}
	
	public void setOrderField(String orderFieldName, boolean isAsc) {
		orderFields.add(new OrderField(orderFieldName, isAsc));
	}
}
