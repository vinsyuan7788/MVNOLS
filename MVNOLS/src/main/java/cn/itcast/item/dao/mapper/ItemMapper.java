package cn.itcast.item.dao.mapper;

import cn.itcast.item.bean.Item;
import cn.itcast.item.bean.ItemExample;
import cn.itcast.item.bean.wrapper.PageItemBean;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemMapper {
    int countByExample(ItemExample example);

    int deleteByExample(ItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Item record);

    int insertSelective(Item record);

    List<Item> selectByExampleWithBLOBs(ItemExample example);

    List<Item> selectByExample(ItemExample example);

    Item selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Item record, @Param("example") ItemExample example);

    int updateByExampleWithBLOBs(@Param("record") Item record, @Param("example") ItemExample example);

    int updateByExample(@Param("record") Item record, @Param("example") ItemExample example);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKeyWithBLOBs(Item record);

    int updateByPrimaryKey(Item record);

	int queryTotalItemsByCriteria(PageItemBean pageItemBean);

	List<Item> queryItemListByCriteria(PageItemBean pageItemBean);
	
	List<Item> queryItemListByCriteria_Test(PageItemBean pageItemBean);
}