package cn.itcast.test.others.javaweb.testclass.dao.jdbc.utils;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 	This is a JDBC utility class regarding connection
 * 	1. process connection for DAO
 * 	2. process transaction for service layer
 * @author Vince Xu Yuan
 */
public class JDBCUtils {
	
	/*	Instantiate a private static connection pool for ONCE for one database	*/
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	/*	
	 *	Instantiate a static thread-local object to store the transaction-dedicated connection:
	 * 	1. to ensure connection consistency of a transaction in current thread	
	 */
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	/*	
	 *	Configuration MUST be static, to make sure, the configuration is obtained for once and for all. (Same as Version 1.0 in day17-1)
	 *	Otherwise each time accessing the same database will get different connection pool, hence the connection will be not consistent (i.e. last connection pool will be covered)	
	 */
	static {		
		try {
			dataSource.setAcquireIncrement(3);
			dataSource.setInitialPoolSize(10);
			dataSource.setMinPoolSize(2);
			dataSource.setMaxPoolSize(10);
			
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
			dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test?useServerPrepStmts=true&cachePrepStmts=true&rewriteBatchedStatements=true");
			dataSource.setUser("root");
			dataSource.setPassword("123");	
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//------------------------------------------------- Connection -----------------------------------------------------
	/**
	 * 	This is a static method to get the transaction.
	 * 	1. Get the regular connection from the connection pool
	 * 	2. Or get the transactional connection from current thread
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection () throws SQLException {
		
		/*	Get the transactional connection from current thread	*/
		Connection threadLocalConnection = tl.get();	
		
		/*	If current thread has a connection, return the connection	*/
		if (threadLocalConnection != null) {
			return threadLocalConnection;
		
		/*	Otherwise get a new connection from the connection pool	*/
		} else {
			return dataSource.getConnection();
		}
	}
	
	/**
	 * 	This is a static method to release un-transactional connections.
	 * @param connection
	 * @throws SQLException
	 */
	public static void releaseConnection (Connection connection) throws SQLException {
		
		/*	Get the transactional connection from current thread	*/
		Connection threadLocalConnection = tl.get();
		
		/*	If current thread does not have a connection, close the connection	*/
		if (threadLocalConnection == null) {
			connection.close();
		
		/*	Otherwise get a new connection from the connection pool	*/
		} else if (threadLocalConnection != connection) {
			connection.close();
		}
	}
	
	/**
	 * 	This is a static method to return connection pool.
	 * @return
	 * @throws Exception
	 */
	public static DataSource getDataSource () throws Exception {
		return dataSource;
	}
	
	//------------------------------------------------- Transaction -----------------------------------------------------
	/**
	 * 	This is a static method to begin a transaction.
	 * @throws SQLException
	 * @throws PropertyVetoException
	 */
	public static void beginTranscation () throws Exception {
		
		/*	Get a connection from current thread	*/
		Connection threadLocalConnection = tl.get(); 
		
		/*	If there is a thread local connection, it means current thread has opened a transaction, so throw an exception with a message	 */
		if (threadLocalConnection != null) {
			throw new SQLException("Transcation has already begun, DO NOT begin the transaction again");
		}

		/*	Otherwise get a thread-local transaction for current thread & set manually commit for the transaction	*/
		threadLocalConnection = getConnection();
		threadLocalConnection.setAutoCommit(false);
		
		/*	Save the thread-local connection into current thread  */
		tl.set(threadLocalConnection);					
	}
	
	/**
	 * 	This is a static method to commit the transaction.
	 * @throws SQLException
	 */
	public static void commitTransaction () throws Exception {
		
		/*	Get a connection from current thread	*/
		Connection threadLocalConnection = tl.get();
		
		/*	If there is no thread local connection, it means current thread does not have a transaction, so throw an exception with a message	 */
		if (threadLocalConnection == null) {
			throw new SQLException("Transcation has not begun yet, commit is NOT allowed");
		}
		
		/*	Commit the transaction & close the thread-local connection	*/
		threadLocalConnection.commit();
		threadLocalConnection.close();
		
		/*	Remove the thread-local connection from curernt thread	*/
		tl.remove();
	}
	
	/**
	 * 	This is a static method to rollback the transaction.
	 * @throws SQLException
	 */
	public static void rollbackTransaction() throws Exception {
		
		/*	Get a connection from current thread	*/
		Connection threadLocalConnection = tl.get();	
		
		/*	If there is no thread local connection, it means current thread does not have a transaction, so throw an exception with a message	 */
		if (threadLocalConnection == null) {
			throw new SQLException("Transcation has not begun yet, rollback is NOT allowed");
		}
		
		/*	Rollback the transaction & close the thread-local connection	*/
		threadLocalConnection.rollback();
		threadLocalConnection.close();
		
		/*	Remove the thread-local connection from curernt thread	*/
		tl.remove();								
	}
}
