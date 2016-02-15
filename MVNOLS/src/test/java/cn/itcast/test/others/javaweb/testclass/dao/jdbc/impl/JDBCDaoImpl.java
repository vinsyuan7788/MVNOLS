package cn.itcast.test.others.javaweb.testclass.dao.jdbc.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.test.others.javaweb.testclass.dao.bean.User;
import cn.itcast.test.others.javaweb.testclass.dao.jdbc.JDBCDao;
import cn.itcast.test.others.javaweb.testclass.dao.jdbc.utils.TxQueryRunner;

/**
 * 	This is the class for DAO development using JDBC & QueryRunner
 * @author Vince Xu Yuan
 */
public class JDBCDaoImpl implements JDBCDao {

	/*	The instantiation here can be realized by Spring IOP (IOC & DI) in the actual development	*/
	private QueryRunner queryRunner = new TxQueryRunner();
	
	/**
	 * 	This is the method to query users with specific conditions
	 */
	@Override
	public List<User> queryUsers(String username, Float score) throws Exception {
		
		/*	Prepare the SQL & parameters	*/
		StringBuffer sql = new StringBuffer("SELECT * FROM user WHERE 1 = 1");
		List<Object> params = new ArrayList<Object>();
		if (username != null && !username.trim().isEmpty()) {
			sql.append(" AND username LIKE ?");
			params.add("%" + username + "%");
		}
		if (score != null && !score.isInfinite() && !score.isNaN()) {
			sql.append(" AND score > ?");
			params.add(score.floatValue());
		}

		/*	Execute the SQL with parameters	*/
		return queryRunner.query(sql.toString(), new BeanListHandler<User>(User.class), params.toArray());
	}
}
