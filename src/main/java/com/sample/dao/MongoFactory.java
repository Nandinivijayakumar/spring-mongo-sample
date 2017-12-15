package com.sample.dao;

import com.mongodb.MongoClient;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;



import org.bson.Document;
import org.springframework.stereotype.Component;

/**
 * Created by WS on 12/12/16.
 */
@Component
public class MongoFactory {

    /**
     * Retrieves the mongo collection
     * @param collection
     * @return
     */
    public MongoCollection<Document> getCollection(String collection){

        //MongoClientOptions options = MongoClientOptions.builder().sslEnabled(true).sslInvalidHostNameAllowed(true).build();

       // MongoClientOptions options = MongoClientOptions.builder().sslEnabled(true).build();

        /**Local mongo db connection*/
       
		//MongoClient mongoClient = new MongoClient("localhost", options);
    	 MongoClient mongoClient = new MongoClient("localhost", 27017);
        /** mongo db database name*/
    	
        MongoDatabase mongoDB = mongoClient.getDatabase("wiziqdb");
      
        

      
        
        

        MongoCollection<Document> mongoCollection = mongoDB.getCollection(collection);
     
        return mongoCollection;
        
        
        
    }
    
    
}
