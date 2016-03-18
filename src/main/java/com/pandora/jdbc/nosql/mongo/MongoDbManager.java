package com.pandora.jdbc.nosql.mongo;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Properties;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.util.JSON;

/**
 * Mongo连接池 
 * @author Mordor
 *
 */
public class MongoDbManager {
	
	/**
	 * Mongo连接 最主要 内容
	 */
	private static Mongo mongo ;
	private static String dbName;
	
	/**
	 * 初始化线程池
	 * 最大连接数等于 最大连接乘队列数 connectionsPerHost * threadsAllowedToBlockForConnectionMultiplier
	 * 连接参数由properites文件提供
	 */
	private static void init(){
		Properties properties = new Properties();
		try {
			//获取mongo配置
			properties.load(MongoDbManager.class.getResourceAsStream("mongo.properties"));
			dbName = properties.getProperty("dbname");
			mongo = new Mongo(properties.getProperty("ip"),Integer.parseInt(properties.getProperty("port")));
			MongoOptions mo = mongo.getMongoOptions();//获取线程池
			mo.connectionsPerHost = Integer.parseInt(properties.getProperty("poolMax"));	//每个主机最大连接数
			mo.threadsAllowedToBlockForConnectionMultiplier = Integer.parseInt(properties.getProperty("blockMax")) ;//线程池队列数
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建Mongo对象
	 * @param ip
	 * @param port
	 * @return
	 */
	public static Mongo getMongo(){
		if(mongo == null)
			init();
		return mongo;
	}
	
	/**
	 * 获取库名
	 * @return
	 */
	public static String getDBName(){
		return MongoDbManager.dbName;
	}
	
	/**
	 * 创建DB对象根据库名
	 * @param ip
	 * @param port
	 * @return
	 */
	public static DB getDB(String dbname){
		if(mongo == null)
			init();
		return mongo.getDB(dbname);
	}
	
	/**
	 * 创建DB对象根据库名
	 * @param ip
	 * @param port
	 * @return
	 */
	public static DB getDB(){
		if(mongo == null)
			init();
		return mongo.getDB(dbName);
	}
	/**
	 * 获取序列
	 * @param dbname	库名
	 * @param collName	序列名
	 * @param mongo		mongo连接
	 * @return
	 */
	public static DBCollection getCollection(String dbname, String collName){
		if(mongo == null)
			init();
		return mongo.getDB(dbname).getCollection(collName);
	}
	
	/**
	 * 获取序列
	 * @param dbname	库名
	 * @param collName	序列名
	 * @param mongo		mongo连接
	 * @return
	 */
	public static DBCollection getCollection(String collName){
		if(mongo == null)
			init();
		return mongo.getDB(dbName).getCollection(collName);
	}
	
	/**
	 * 关闭链接
	 * @param mongo
	 */
	public static void closeMongo(Mongo mongo){
		mongo.close();
	}

	public static void main(String[] args) {
		Mongo mongo = MongoDbManager.getMongo();
		//原始方式
//		DB db = mongo.getDB("test");
//		DBCollection collection = db.getCollection("test");
		
		//获取DB
//		DB db = MongoDbUtil.getDB("test");
		
		//获取序列
		DBCollection collection = MongoDbManager.getCollection("test", "test");
		
		//执行检索
		DBCursor cursor = collection.find();
		
		//逐行输出
		while(cursor.hasNext()){
			System.out.println(cursor.next());
		}
		
		//转成Json格式
		System.out.println(JSON.serialize(cursor));
		
		//关闭连接
		MongoDbManager.closeMongo(mongo);
	}
}
