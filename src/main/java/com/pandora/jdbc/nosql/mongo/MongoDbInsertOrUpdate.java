package com.pandora.jdbc.nosql.mongo;

import com.mongodb.DBObject;

public class MongoDbInsertOrUpdate {

	/**
	 * 插入单条数据
	 * @param collectionName
	 * @param dbObject
	 */
	public static void save(String collectionName, DBObject dbObject ){
		MongoDbManager.getCollection(collectionName).insert(dbObject);
	}
	
}
