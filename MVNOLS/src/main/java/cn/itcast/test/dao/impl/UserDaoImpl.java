package cn.itcast.test.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import cn.itcast.test.dao.UserDao;
import cn.itcast.user.bean.User;

/**
 * 	This is the DAO implementation class:
 * 	1. Extends SqlSessionDaoSupport
 *     -- Similar as Hibernate: extends HibernateDaoSupport
 *  2. The Session creation, commit, close will be done in SqlSessionDaoSupport
 *     -- Similar as HibernateDaoSupport
 * @author Vince Xu Yuan
 */
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {
	
	@Override
	public User queryUserById(int id) throws Exception {
		
		/*	Obtain the sqlSession	*/
		SqlSession sqlSession = this.getSqlSession();
		
		return sqlSession.selectOne("user.queryUserById", id);
	}

}

