package com.pandora.jdbc.nosql.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.util.JSON;

public class MongoDbFind {

	public static void main(String[] args) {
//		System.out.println(findJson("test",new BasicDBObject("name", "/a../")));
	}

	/**
	 * 根据序列名返回全表内容
	 * @param collName
	 * @return
	 */
	public static String findAllJson(String collName){
		DBCollection collection = MongoDbManager.getCollection(collName);
		return JSON.serialize(collection.find());
	}
	
	/**
	 * 根据序列名和条件 进行查询
	 * @param collName
	 * @param Condition
	 * @return
	 */
	public static String findJson(String collName, BasicDBObject ref){
		DBCollection collection = MongoDbManager.getCollection(collName);
		return JSON.serialize(collection.find(ref));
	}
	
//	public static DBCursor findModif(String collName){
//		DBCollection collection = MongoDbManager.getCollection(collName);
//		collection.finda
//		
//	}
	
}
