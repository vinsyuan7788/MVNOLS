package cn.itcast.user.dao.mapper;

import cn.itcast.user.bean.Userupload;
import cn.itcast.user.bean.UseruploadExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UseruploadMapper {
    int countByExample(UseruploadExample example);

    int deleteByExample(UseruploadExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Userupload record);

    int insertSelective(Userupload record);

    List<Userupload> selectByExample(UseruploadExample example);

    Userupload selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Userupload record, @Param("example") UseruploadExample example);

    int updateByExample(@Param("record") Userupload record, @Param("example") UseruploadExample example);

    int updateByPrimaryKeySelective(Userupload record);

    int updateByPrimaryKey(Userupload record);
}