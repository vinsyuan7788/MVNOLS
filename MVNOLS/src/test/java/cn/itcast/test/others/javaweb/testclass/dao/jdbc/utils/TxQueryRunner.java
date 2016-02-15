package cn.itcast.test.others.javaweb.testclass.dao.jdbc.utils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;


/**
 * 	This is a DAO utility class to simplify the DAO programming using JDBC
 * 	1. Extend the QueryRunner class
 * 	2. Overide all the methods by adding below codes around:
 *     -- Connection conn = JDBCUtils.getConnection();
 *     -- JDBCUtils.releaseConnection(conn);
 *     
 * @author Vince Xu Yuan
 */
public class TxQueryRunner extends QueryRunner {

	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		
		Connection conn = JDBCUtils.getConnection();
		int[] result = super.batch(conn, sql, params);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T query(String sql, Object param, ResultSetHandler<T> rsh)
			throws SQLException {
		
		Connection conn = JDBCUtils.getConnection();
		T result = super.query(conn, sql, param, rsh);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T query(String sql, Object[] params, ResultSetHandler<T> rsh)
			throws SQLException {

		Connection conn = JDBCUtils.getConnection();
		T result = super.query(conn, sql, params, rsh);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params)
			throws SQLException {

		Connection conn = JDBCUtils.getConnection();
		T result = super.query(conn, sql, rsh, params);
		JDBCUtils.releaseConnection(conn);
		return result;
	}
	
	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {

		Connection conn = JDBCUtils.getConnection();
		T result = super.query(conn, sql, rsh);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public int update(String sql, Object... params) throws SQLException {

		Connection conn = JDBCUtils.getConnection();
		int result = super.update(conn, sql, params);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public int update(String sql, Object param) throws SQLException {

		Connection conn = JDBCUtils.getConnection();
		int result = super.update(conn, sql, param);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public int update(String sql) throws SQLException {

		Connection conn = JDBCUtils.getConnection();
		int result = super.update(conn, sql);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	
}

