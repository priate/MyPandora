package com.pandora.jdbc.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 连接数据库测试
 * Oracle Jar ojdbc16.jar
 * MySql Jar mysql-connector-java-5.0.6-bin.jar And mysql-connector-java-5.1.18-bin.jar
 * SqlServer
 * @author ch
 */
public class ConnectionTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		Connection conn = getMySqlConnections("jdbc:mysql://127.0.0.1:3306/datastest", "root", "roots");
		Connection conn = getMySqlConnections("jdbc:mysql://192.168.1.11:3306/test", "root", "root");
//		Connection connOra = getOracleConnections("192.168.1.18", "yk", "nstl", "");
		closeConnection(conn);
	}
	
	/**
	 * 更新内容
	 * @return
	 */
	public String updateQuot(){
		String t = "true";
		//创建连接
//		Connection sqlServerConnFormal = getConnections("jdbc:sqlserver://127.0.0.1:1433; DatabaseName=imidb","sa","123456");
//		Connection mySqlConnFormal = getMySqlConnections("jdbc:mysql://localhost/pzhpengpeng","pzhpengpeng","19890902");
		Connection oracleConnFormal = getOracleConnections("192.168.111.130","eonly","eonly","eonly");
		
		//连接为空直接返回
		if(oracleConnFormal==null){
			System.out.println("Connection is null so Return !");
			return null;
		}
		
		try {
//			String sql = ("select * from TableName");
//			
//			//正式环境 连接
//			Statement stFormal = mySqlConnFormal.createStatement();
//			ResultSet rsFormal = stFormal.executeQuery(sql);
//			if(rsFormal.next()){
//				String user = rsFormal.getString("user");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
			t = "false";
		} finally{
			//关闭连接
			closeConnection(oracleConnFormal);
		}
		
		return t;
	}

	/**
	 * 获取SqlServer连接对象
	 * @param hostName
	 * @return
	 */
	private static Connection getSqlServerConnections(String URL,String name, String pwd){
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 加载JDBC驱动
		String dbURL = URL; // 连接服务器和数据库sample
		String userName = name; // 默认用户名
		String userPwd = pwd; // 密码
		Connection dbConn;
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			System.out.println("Connection Successful!"); // 如果连接成功
			return dbConn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取MySql连接对象
	 * @param hostName
	 * @return
	 */
	private static Connection getMySqlConnections(String URL,String name, String pwd){
		String driverName = "com.mysql.jdbc.Driver"; // 加载JDBC驱动
		String dbURL = URL; // 连接服务器和数据库sample
		String userName = name; // 默认用户名
		String userPwd = pwd; // 密码
		Connection dbConn;
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			System.out.println(userName + " Connection Successful!"); // 如果连接成功
			return dbConn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取Oracle连接对象
	 * @param hostName
	 * @param userName
	 * @param userPwd
	 * @param databaseName
	 * @return
	 */
	private static Connection getOracleConnections(String hostName,String userName,String userPwd,String databaseName){
//		try {
//			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
		String driverName = "oracle.jdbc.OracleDriver"; //加载JDBC驱动
		String dbURL = "jdbc:oracle:thin:@"+hostName+":1521:"+databaseName; //连接服务器和数据库sample
		Connection dbConn;
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			System.out.println("Connection "+hostName+" Successful!"); //如果连接成功
			return dbConn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 关闭Connection连接
	 * @param conn
	 */
	private static void closeConnection(Connection conn){
		try{
			if(conn!=null)
			conn.close();
			System.out.println("数据库连接" + conn.toString() + "已关闭");
		}catch(Exception e){
			System.out.println("数据库连接" + conn.toString() + "关闭失败");
			e.printStackTrace();
		}
	}
}
