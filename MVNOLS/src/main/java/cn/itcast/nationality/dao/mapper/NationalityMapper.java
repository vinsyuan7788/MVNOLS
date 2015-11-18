package cn.itcast.nationality.dao.mapper;

import cn.itcast.nationality.bean.Nationality;
import cn.itcast.nationality.bean.NationalityExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NationalityMapper {
    int countByExample(NationalityExample example);

    int deleteByExample(NationalityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Nationality record);

    int insertSelective(Nationality record);

    List<Nationality> selectByExample(NationalityExample example);

    Nationality selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Nationality record, @Param("example") NationalityExample example);

    int updateByExample(@Param("record") Nationality record, @Param("example") NationalityExample example);

    int updateByPrimaryKeySelective(Nationality record);

    int updateByPrimaryKey(Nationality record);
}