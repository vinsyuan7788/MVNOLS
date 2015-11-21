package cn.itcast.test.others;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.item.bean.Item;
import cn.itcast.item.bean.wrapper.PageItemBean;
import cn.itcast.temporary.bean.query.bean.QueryBean;
import cn.itcast.temporary.bean.query.utils.ConditionField;
import cn.itcast.temporary.bean.query.utils.OrderField;
import cn.itcast.temporary.bean.query.utils.QueryField;
import cn.itcast.test.dao.mapper.OptimizationMapper;

/**
 * 	This class is to test the general optimization for MyBatis DAO (i.e the optimal query bean can be applied on all JavaBeans instead of for a specific module)
 * 	1. In where condition part: 
 * 	   -- The type of condition value varies: String, Integer, etc. 
 *        -- In contrast, if the type is determined, then corresponding query fields are fixed for a JavaBean in a specific module, which loses its generality
 * 	   -- The relation between field name & value varies: in, equal, like, between, etc. (which are concluded in the xxxExample class)
 *  2. In order by part:
 *     -- Multiple fields order in ascending & descending should be: order by xxx, zzz, aaa ASC, ddd, yyy, bbb, ccc desc
 *     -- Hence the property in OrderField should be a single string that can accept & concatenate the input according to ascending or descending requirement
 *  3. This optimization should be done in the late-stage of the development
 *  4. This optimization is only workable for single-table query, not workable for multiple-table query
 *  5. This optimization requires the column name in database must be identical to the field name in JavaBean
 *     -- See "testQueryBeanDao()" method
 *     -- Otherwise ${fieldName} cannot be used & all the necessary fields must be manually assigned
 *     
 *  Hence the idea is adoptable, but not implementable during the development due to the high requirement & low cross-module generality
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:spring/applicationContext.xml")
public class TestQueryBean {

	/**
	 * 	Test Query Field: workable in Java
	 * @throws Exception
	 */
	@Test
	public void testQueryField () throws Exception {
		
		QueryBean queryBean = new QueryBean();
		queryBean.setQueryFieldName("itemName");
		queryBean.setQueryFieldName("itemPrice");
		queryBean.setQueryFieldName("itemState");
		
		List<QueryField> queryFields = queryBean.getQueryFields();
		for (QueryField queryField : queryFields) {
			System.out.println(queryField.getQueryFieldName());
		}
	}
	
	/**
	 * 	Test condition field: workable in Java
	 * @throws Exception
	 */
	@Test
	public void testConditionField () throws Exception {

		QueryBean queryBean = new QueryBean();
//		queryBean.setConditionField("itemName", 1, true);
		queryBean.setConditionField("itemPriceInterval", "low", false);
		queryBean.setConditionField("itemState", "active", false);
		
		List<ConditionField> conditionFields = queryBean.getConditionFields();
		for (ConditionField conditionField : conditionFields) {
			System.out.println(conditionField.getConditionFieldName() + " " + conditionField.getConditionFieldValue() + " " +conditionField.isLike());
		}
	}
	
	/**
	 * 	Test order field: workable in Java
	 * @throws Exception
	 */
	@Test
	public void testOrderField () throws Exception {
		
		QueryBean queryBean = new QueryBean();
		queryBean.setOrderField("itemName", true);
		queryBean.setOrderField("itemPriceInterval", false);
		queryBean.setOrderField("itemState", false);
		
		List<OrderField> orderFields = queryBean.getOrderFields();
		for (OrderField orderField : orderFields) {
			System.out.println(orderField.getOrderFieldName() + " " + orderField.isAsc);
		}
	}
	
	/**
	 * 	Test the interaction with database: fail
	 */
	@Resource
	private OptimizationMapper optimizationMapper;
	@Test
	public void testQueryBeanDao () throws Exception {
		
		PageItemBean pageItemBean = new PageItemBean();
		pageItemBean.setQueryFieldName("itemName");
		pageItemBean.setConditionField("itemState", "active", false);
		pageItemBean.setOrderField("itemPrice", true);
		
		List<Item> items = optimizationMapper.queryItemListByCriteria(pageItemBean);
		System.out.println(items.size());
	}
}
