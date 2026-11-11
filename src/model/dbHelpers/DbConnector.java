/**
 * 
 */
package model.dbHelpers;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 * @author helmahdy
 *
 */
public class DbConnector {
	
	public static Connection connectToDb() throws SQLException{

	/////////////////Connection Pooling//////////////////////////
	BasicDataSource source = new BasicDataSource();
	source.setDriverClassName("com.mysql.jdbc.Driver");
	///////////////////////Development//////////////////////////////////////////
	
	String url = "jdbc:mysql://localhost:3306/";	 
	String userName = "root";
	String password = "root";
	String database = "demolms20200507";
	
	
	////////////////////////////Production///////////////////////////////////////
/*	String url = "jdbc:mysql://eduhappydb.cpq5ktgfqicm.eu-west-1.rds.amazonaws.com:3306/";	 
	String userName = "admin";
	String password = "h_emad015";
	String database = "eduhappylms";*/
	/////////////////////////////////////////////////////////////////////////
	////////////////////////////Production (All in One Server)//////////////////////////////////////
	 
	//String url = "jdbc:mysql://ec2-54-75-86-195.eu-west-1.compute.amazonaws.com:3306/";	 
/*    String url = "jdbc:mysql://172.31.34.16:3306/";
	String userName = "hossam"; 
	String password = "h_emad015";
	String database = "eduhappyproduction";	
	*/
	////////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////////////////////
	source.setUsername(userName);
	source.setPassword(password);
	String unicode= "?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
	source.setUrl(url+database+unicode);
	Connection connection = source.getConnection();
	source.close();
	return connection;
	
	}
}

