package cn.itcast.user.dao.mapper;

import cn.itcast.global.pagination.bean.PageBean;
import cn.itcast.user.bean.User;
import cn.itcast.user.bean.UserExample;
import cn.itcast.user.bean.wrapper.PageUserBean;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
	
	List<User> queryUserList(PageBean<User> pageBean);

	int queryTotalUserByCriteria(PageUserBean pageUserBean);

	List<User> queryUserListByCriteria(PageUserBean pageUserBean);
}