package com.pandora.jdbc.db.proxool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;

import com.sun.org.apache.bcel.internal.util.ClassPath;

public class JdbcBaseUtil {
	
	private static JdbcBaseUtil uniqueInstance = null;
	static {
		System.out.println("---------   进入Poxrool连接池 并初始化  ------------");
		try {
			JAXPConfigurator.configure(System.getProperty("user.dir") + "/resources/proxool.xml", false);
		} catch (ProxoolException e1) {
			e1.printStackTrace();
		}
	}

	JdbcBaseUtil() {
		super();
	}

	/**
	 * 创建JDBC对象
	 * @return
	 */
	public static JdbcBaseUtil getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new JdbcBaseUtil();
		}
		return uniqueInstance;
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("proxool.Dbpool");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;

	}
	
	public static void main(String[] args) {
		JdbcBaseUtil jdbcBaseUtil = JdbcBaseUtil.getInstance();
//		Connection connection = jdbcBaseUtil.getConnection();
		try {
			List<Map<String, Object>> queryBySql = jdbcBaseUtil.queryBySql("SELECT * FROM WEBSITE_CONFIG");
			System.out.println(queryBySql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 对数据增删改,如果操作成功返回true,否则返回false
	 * 
	 * @param query
	 *            SQL语句
	 * @param params
	 *            sql语句的占位符,可以为空
	 * @return boolean
	 * @exception SQLException
	 */
	public boolean excute(String sql, Object pring[]) {

		boolean flag = false;
		Connection conn = null;
		try {
			QueryRunner qu = new QueryRunner();
			int i = 0;
			conn = getConnection();
			i = qu.update(conn, sql, pring);
			if (i >= 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbUtils.closeQuietly(conn);
		}
		return flag;
	}
	
	/**
	 * 对数据进行批量处理 如果成功返回数组形式 执行成功数量
	 * @param sql
	 * @param params
	 * @return
	 */
	public int[] excute(String sql, Object[][] params) {
		Connection conn = null;
		try {
			QueryRunner qu = new QueryRunner();
			conn = getConnection();
			return qu.batch(conn, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbUtils.closeQuietly(conn);
		}
		return new int[]{0};
	}
	
	/**
	 * 对数据进行批量处理 如果成功返回数组形式 执行成功数量 带有Connection参数
	 * @param conn
	 * @param sql
	 * @param params
	 * @return
	 */
	public int[] excute(Connection conn,String sql, Object[][] params) {
		try {
			QueryRunner qu = new QueryRunner();
			conn = getConnection();
			return qu.batch(conn, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return new int[]{0};
	}
	
	/**
	 * 根据SQL获取内容
	 * @param sql
	 * @param pring
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> queryBySql(String sql) throws SQLException {
		QueryRunner queryRunner = new QueryRunner();
		Connection conn = getConnection();
		try{
			return queryRunner.query(conn, sql, new MapListHandler());
		}catch(Exception e){
			throw e;
		}finally{
			DbUtils.closeQuietly(conn);
		}
	}
	
	/**
	 * 根据SQL和参数获取内容
	 * @param sql
	 * @param rsh
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("deprecation")
	public List<Map<String, Object>> queryBySql(String sql,Object[] rsh) throws SQLException {
		QueryRunner queryRunner = new QueryRunner();
		Connection conn = getConnection();
		try{
			return queryRunner.query(conn, sql, rsh, new MapListHandler());
		}catch(Exception e){
			throw e;
		}finally{
			DbUtils.closeQuietly(conn);
		}
	}
	
	/**
	 * 根据表名和数据 自动存储数据(使用之前保证数据库表存在)
	 * @param tableName
	 * @param content
	 */
	public void  saveData(String tableName, List<Map<String, Object>> content){
		//组装insert语句
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO " + tableName + " (");
		String[] fieldNames = new String[content.get(0).keySet().size()];
		int index = 0;
		for(String field : content.get(0).keySet()){
			fieldNames[index++] = field;
		}
		sb.append(fieldNames[0]);
		for(int i =1 ,length = fieldNames.length ; i < length ; i++){
			sb.append("," + fieldNames[i]);
		}
		sb.append(") VALUES(?");
		for(int i =1 ,length = fieldNames.length ; i < length ; i++){
			sb.append(",?");
		}
		sb.append(")");
		
		//重新拼装数据
		Object[][] params = new Object[content.size()][fieldNames.length];
		for(int i = 0 ,length=content.size() ; i < length ; i++){
			Map<String, Object> contentMap = content.get(i);
			for(int j=0, lengthj = fieldNames.length ; j<lengthj ; j++){
				params[i][j] = contentMap.get(fieldNames[j]);
			}
		}
		System.out.println(sb.toString());
		//批量入库
		JdbcBaseUtil jdbc = JdbcBaseUtil.getInstance();
		jdbc.excute(sb.toString(), params);
	}
}
