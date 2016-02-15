package cn.itcast.test.others.javaweb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import cn.itcast.test.others.javaweb.testclass.dao.bean.User;
import cn.itcast.test.others.javaweb.testclass.dao.jdbc.JDBCDao;
import cn.itcast.test.others.javaweb.testclass.dao.jdbc.impl.JDBCDaoImpl;
import cn.itcast.test.others.javaweb.testclass.dao.jdbc.utils.JDBCUtils;

/**
 * 	This is the class to perform testing regarding JDBC
 * @author Vince Xu Yuan
 */
public class TestJDBC {

	/**
	 * 	Test the steps of JDBC 
	 * 	1. Get the connection 
	 * 	2. Prepare the SQL statement
	 * 	3. Execute the SQL statement (& if there is a return result, process it)
	 *  4. Catch the exception & process it
	 *  5. Close the resources
	 * @throws Exception 
	 * @throws Exception
	 */
	@Test
	public void testJDBC () throws Exception {
		
		/*	These are the necessary references for JDBC	 */
		Connection connection = null;  
		PreparedStatement pStatement = null;
		ResultSet result = null;
		String sql = null;
		
		/*	Here are the steps of JDBC	*/
		try {
			
			/*	Get a connection	*/
			connection = JDBCUtils.getConnection();
			
			/*	Prepare the SQL statement	*/
			sql = "SELECT * FROM user WHERE username LIKE ? AND score > ?";
			pStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pStatement.setString(1, "%V%");
			pStatement.setFloat(2, 80F);
			
			/*	Execute the SQL statement	*/
			pStatement.execute();
			
			/*	Here there is a return result, so get the return result & process it	*/
			result = pStatement.getResultSet();
			while (result.next()) {
				Object id = result.getObject("id"); 						// getObject() is used when the column type is unknown
				String username = result.getString("username");				// or String name = result.getString(2);
				String gender = result.getString("gender");
				Date birthday = result.getDate("birthday");
				float score = result.getInt("score");
				System.out.println(id + " " + username + " " + gender + " " + birthday + " " + score);
			}
		
		/*	Catch the exception & process it	*/
		} catch (Exception e) {
			throw new RuntimeException(e);
			
		/*	Close the resources	 */
		} finally {
			if (result != null)	result.close();
			if (pStatement != null) pStatement.close();
			if (connection != null) connection.close();	
		}
	}
	
	/**
	 * 	Test TxQueryRunner class
	 * 	1. TxQueryRunner is used to simplify the DAO programming using JDBC
	 * 	2. The transaction can be realized by AOP through declarative transaction management in actual development
	 *     -- Refer to "src/main/resources/spring/config/transaction.xml"
	 * @throws Exception
	 */
	/*	The instantiation here can be realized by Spring IOP (IOC & DI) in the actual development	*/
	private JDBCDao jdbcDao = new JDBCDaoImpl();
	@Test
	public void testDAOWithTxQueryRunner () throws Exception {
		
		/*	Begin a transaction	 */
		JDBCUtils.beginTranscation();
		
		/*	Invoke the method in JDBC DAO	*/
		List<User> userList = jdbcDao.queryUsers("V", 80F);
		
		/*	Process the return value	*/
		for (User user : userList) {
			System.out.println(user.getId() + " " + user.getUsername() + " " + user.getGender() + " " + user.getBirthday() + " " + user.getScore());
		}
		
		/*	Commit a transaction	*/
		JDBCUtils.commitTransaction();
	}
}
