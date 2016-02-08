package cn.itcast.item.bean;

import java.util.ArrayList;
import java.util.List;

public class ItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ItemExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNull() {
            addCriterion("item_name is null");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNotNull() {
            addCriterion("item_name is not null");
            return (Criteria) this;
        }

        public Criteria andItemNameEqualTo(String value) {
            addCriterion("item_name =", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotEqualTo(String value) {
            addCriterion("item_name <>", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThan(String value) {
            addCriterion("item_name >", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThanOrEqualTo(String value) {
            addCriterion("item_name >=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThan(String value) {
            addCriterion("item_name <", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThanOrEqualTo(String value) {
            addCriterion("item_name <=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLike(String value) {
            addCriterion("item_name like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotLike(String value) {
            addCriterion("item_name not like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameIn(List<String> values) {
            addCriterion("item_name in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotIn(List<String> values) {
            addCriterion("item_name not in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameBetween(String value1, String value2) {
            addCriterion("item_name between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotBetween(String value1, String value2) {
            addCriterion("item_name not between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemPriceIsNull() {
            addCriterion("item_price is null");
            return (Criteria) this;
        }

        public Criteria andItemPriceIsNotNull() {
            addCriterion("item_price is not null");
            return (Criteria) this;
        }

        public Criteria andItemPriceEqualTo(Float value) {
            addCriterion("item_price =", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceNotEqualTo(Float value) {
            addCriterion("item_price <>", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceGreaterThan(Float value) {
            addCriterion("item_price >", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceGreaterThanOrEqualTo(Float value) {
            addCriterion("item_price >=", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceLessThan(Float value) {
            addCriterion("item_price <", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceLessThanOrEqualTo(Float value) {
            addCriterion("item_price <=", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceIn(List<Float> values) {
            addCriterion("item_price in", values, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceNotIn(List<Float> values) {
            addCriterion("item_price not in", values, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceBetween(Float value1, Float value2) {
            addCriterion("item_price between", value1, value2, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceNotBetween(Float value1, Float value2) {
            addCriterion("item_price not between", value1, value2, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPlaceOfManufactureIsNull() {
            addCriterion("item_place_of_manufacture is null");
            return (Criteria) this;
        }

        public Criteria andItemPlaceOfManufactureIsNotNull() {
            addCriterion("item_place_of_manufacture is not null");
            return (Criteria) this;
        }

        public Criteria andItemPlaceOfManufactureEqualTo(String value) {
            addCriterion("item_place_of_manufacture =", value, "itemPlaceOfManufacture");
            return (Criteria) this;
        }

        public Criteria andItemPlaceOfManufactureNotEqualTo(String value) {
            addCriterion("item_place_of_manufacture <>", value, "itemPlaceOfManufacture");
            return (Criteria) this;
        }

        public Criteria andItemPlaceOfManufactureGreaterThan(String value) {
            addCriterion("item_place_of_manufacture >", value, "itemPlaceOfManufacture");
            return (Criteria) this;
        }

        public Criteria andItemPlaceOfManufactureGreaterThanOrEqualTo(String value) {
            addCriterion("item_place_of_manufacture >=", value, "itemPlaceOfManufacture");
            return (Criteria) this;
        }

        public Criteria andItemPlaceOfManufactureLessThan(String value) {
            addCriterion("item_place_of_manufacture <", value, "itemPlaceOfManufacture");
            return (Criteria) this;
        }

        public Criteria andItemPlaceOfManufactureLessThanOrEqualTo(String value) {
            addCriterion("item_place_of_manufacture <=", value, "itemPlaceOfManufacture");
            return (Criteria) this;
        }

        public Criteria andItemPlaceOfManufactureLike(String value) {
            addCriterion("item_place_of_manufacture like", value, "itemPlaceOfManufacture");
            return (Criteria) this;
        }

        public Criteria andItemPlaceOfManufactureNotLike(String value) {
            addCriterion("item_place_of_manufacture not like", value, "itemPlaceOfManufacture");
            return (Criteria) this;
        }

        public Criteria andItemPlaceOfManufactureIn(List<String> values) {
            addCriterion("item_place_of_manufacture in", values, "itemPlaceOfManufacture");
            return (Criteria) this;
        }

        public Criteria andItemPlaceOfManufactureNotIn(List<String> values) {
            addCriterion("item_place_of_manufacture not in", values, "itemPlaceOfManufacture");
            return (Criteria) this;
        }

        public Criteria andItemPlaceOfManufactureBetween(String value1, String value2) {
            addCriterion("item_place_of_manufacture between", value1, value2, "itemPlaceOfManufacture");
            return (Criteria) this;
        }

        public Criteria andItemPlaceOfManufactureNotBetween(String value1, String value2) {
            addCriterion("item_place_of_manufacture not between", value1, value2, "itemPlaceOfManufacture");
            return (Criteria) this;
        }

        public Criteria andItemImageIsNull() {
            addCriterion("item_image is null");
            return (Criteria) this;
        }

        public Criteria andItemImageIsNotNull() {
            addCriterion("item_image is not null");
            return (Criteria) this;
        }

        public Criteria andItemImageEqualTo(String value) {
            addCriterion("item_image =", value, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageNotEqualTo(String value) {
            addCriterion("item_image <>", value, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageGreaterThan(String value) {
            addCriterion("item_image >", value, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageGreaterThanOrEqualTo(String value) {
            addCriterion("item_image >=", value, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageLessThan(String value) {
            addCriterion("item_image <", value, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageLessThanOrEqualTo(String value) {
            addCriterion("item_image <=", value, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageLike(String value) {
            addCriterion("item_image like", value, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageNotLike(String value) {
            addCriterion("item_image not like", value, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageIn(List<String> values) {
            addCriterion("item_image in", values, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageNotIn(List<String> values) {
            addCriterion("item_image not in", values, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageBetween(String value1, String value2) {
            addCriterion("item_image between", value1, value2, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageNotBetween(String value1, String value2) {
            addCriterion("item_image not between", value1, value2, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemStateIsNull() {
            addCriterion("item_state is null");
            return (Criteria) this;
        }

        public Criteria andItemStateIsNotNull() {
            addCriterion("item_state is not null");
            return (Criteria) this;
        }

        public Criteria andItemStateEqualTo(String value) {
            addCriterion("item_state =", value, "itemState");
            return (Criteria) this;
        }

        public Criteria andItemStateNotEqualTo(String value) {
            addCriterion("item_state <>", value, "itemState");
            return (Criteria) this;
        }

        public Criteria andItemStateGreaterThan(String value) {
            addCriterion("item_state >", value, "itemState");
            return (Criteria) this;
        }

        public Criteria andItemStateGreaterThanOrEqualTo(String value) {
            addCriterion("item_state >=", value, "itemState");
            return (Criteria) this;
        }

        public Criteria andItemStateLessThan(String value) {
            addCriterion("item_state <", value, "itemState");
            return (Criteria) this;
        }

        public Criteria andItemStateLessThanOrEqualTo(String value) {
            addCriterion("item_state <=", value, "itemState");
            return (Criteria) this;
        }

        public Criteria andItemStateLike(String value) {
            addCriterion("item_state like", value, "itemState");
            return (Criteria) this;
        }

        public Criteria andItemStateNotLike(String value) {
            addCriterion("item_state not like", value, "itemState");
            return (Criteria) this;
        }

        public Criteria andItemStateIn(List<String> values) {
            addCriterion("item_state in", values, "itemState");
            return (Criteria) this;
        }

        public Criteria andItemStateNotIn(List<String> values) {
            addCriterion("item_state not in", values, "itemState");
            return (Criteria) this;
        }

        public Criteria andItemStateBetween(String value1, String value2) {
            addCriterion("item_state between", value1, value2, "itemState");
            return (Criteria) this;
        }

        public Criteria andItemStateNotBetween(String value1, String value2) {
            addCriterion("item_state not between", value1, value2, "itemState");
            return (Criteria) this;
        }

        public Criteria andItemStockIsNull() {
            addCriterion("item_stock is null");
            return (Criteria) this;
        }

        public Criteria andItemStockIsNotNull() {
            addCriterion("item_stock is not null");
            return (Criteria) this;
        }

        public Criteria andItemStockEqualTo(Integer value) {
            addCriterion("item_stock =", value, "itemStock");
            return (Criteria) this;
        }

        public Criteria andItemStockNotEqualTo(Integer value) {
            addCriterion("item_stock <>", value, "itemStock");
            return (Criteria) this;
        }

        public Criteria andItemStockGreaterThan(Integer value) {
            addCriterion("item_stock >", value, "itemStock");
            return (Criteria) this;
        }

        public Criteria andItemStockGreaterThanOrEqualTo(Integer value) {
            addCriterion("item_stock >=", value, "itemStock");
            return (Criteria) this;
        }

        public Criteria andItemStockLessThan(Integer value) {
            addCriterion("item_stock <", value, "itemStock");
            return (Criteria) this;
        }

        public Criteria andItemStockLessThanOrEqualTo(Integer value) {
            addCriterion("item_stock <=", value, "itemStock");
            return (Criteria) this;
        }

        public Criteria andItemStockIn(List<Integer> values) {
            addCriterion("item_stock in", values, "itemStock");
            return (Criteria) this;
        }

        public Criteria andItemStockNotIn(List<Integer> values) {
            addCriterion("item_stock not in", values, "itemStock");
            return (Criteria) this;
        }

        public Criteria andItemStockBetween(Integer value1, Integer value2) {
            addCriterion("item_stock between", value1, value2, "itemStock");
            return (Criteria) this;
        }

        public Criteria andItemStockNotBetween(Integer value1, Integer value2) {
            addCriterion("item_stock not between", value1, value2, "itemStock");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}