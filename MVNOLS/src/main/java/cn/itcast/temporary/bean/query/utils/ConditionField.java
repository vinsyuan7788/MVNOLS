package cn.itcast.temporary.bean.query.utils;

public class ConditionField {

	private String conditionFieldName;
	private String conditionFieldValue;
	private boolean isLike;

	public String getConditionFieldName() {
		return conditionFieldName;
	}
	public boolean isLike() {
		return isLike;
	}
	public Object getConditionFieldValue() {
		return conditionFieldValue;
	}
	
	public ConditionField(String conditionFieldName, String conditionFieldValue, boolean isLike) {
		this.conditionFieldName = conditionFieldName;
		this.conditionFieldValue = conditionFieldValue;
		this.isLike = isLike;
	}
}
