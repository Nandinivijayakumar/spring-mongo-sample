package com.sample.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import com.sample.vo.RequestVO;
import com.sample.vo.UserVO;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WS on 8/12/16.
 */
@Component
@Qualifier("userDAOMongo")
public class UserDAOMongo implements UserDAO {

    @Autowired
    MongoFactory mongoFactory;

    //name of collection created in mongo
    private static final String mongocollection = "user";
  
    /***
     * This method retrieves the user information from mongo db collection based on input criteria
     * @param requestVO
     * @return
     */
    @Override
    public UserVO getUser(RequestVO requestVO) {
    	
    	
    	
    	String userName = requestVO.getUsername();
        String password = requestVO.getPassword();
        MongoCollection<Document> userCollection = mongoFactory.getCollection(mongocollection);
        UserVO us=new UserVO();
        BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
        obj.add(new BasicDBObject("username", userName));
        obj.add(new BasicDBObject("password", password));
        andQuery.put("$and", obj);
        System.out.println(andQuery.toString());

        Document userDoc = userCollection.find(andQuery).first();
        //System.out.println(userDoc.toJson());

        if(userDoc !=null && !userDoc.isEmpty()){
        	us.setId((String) userDoc.get("id"));
        	us.setUsername(userName);
        	us.setPassword(password);
        	us.setRole((String) userDoc.get("role"));
        	us.setFirstName((String) userDoc.get("firstname"));
        	us.setLastName((String) userDoc.get("lastname"));
        	us.setGender((String) userDoc.get("gender"));
        	us.setCompany((String) userDoc.get("company"));
        	us.setEmail((String) userDoc.get("email"));
        	us.setPhone((String) userDoc.get("phone"));
        	us.setAge((String) userDoc.get("age"));
        	System.out.println(" "+us);
        }
        
        return us;
        
    }


    /***
     * Add the user info in the mongo collection
     * @param userVO
     */
    public String addUser(UserVO userVO){

    	String userName = userVO.getUsername();
        String password = userVO.getPassword();
        MongoCollection<Document> userCollection = mongoFactory.getCollection(mongocollection);
        //UserVO us=new UserVO();
        BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
        obj.add(new BasicDBObject("username", userName));
        obj.add(new BasicDBObject("password", password));
        andQuery.put("$and", obj);
        System.out.println(andQuery.toString());

        Document userDoc = userCollection.find(andQuery).first();
        //System.out.println(userDoc.toJson());

        if(userDoc!=null){
        	
        	return "fail";
           }
        else{
        	
        /**Check if user does not exist - If exist then dont add it*/
              
            //Create the document and insert in the collection
        	Document doc = new Document();
            doc.put(("id"), userVO.getId());
            doc.put(("username"), userVO.getUsername());
            doc.put(("password"), userVO.getPassword());
            doc.put(("role"), userVO.getRole());
            doc.put(("firstname"), userVO.getFirstName());
            doc.put(("lastname"), userVO.getLastName());
            doc.put(("gender"), userVO.getGender());
            doc.put("company", userVO.getCompany());
            doc.put(("email"), userVO.getEmail());
            doc.put(("phone"), userVO.getPhone());
            doc.put("age", userVO.getAge());
           //Path of image location e.g. D:/picture/...jpeg
            userCollection.insertOne(doc);
            return "success";
      
    }
    }

    /**
     * Update the collection record
     *
     * @param userVO
     */
    public UserVO updateUser(UserVO userVO){

       // MongoCollection<Document> userCollection1 = mongoFactory.getCollection(mongocollection);
        
        String userName = userVO.getUsername();
        String password = userVO.getPassword();
        MongoCollection<Document> userCollection1 = mongoFactory.getCollection(mongocollection);
        UserVO us=new UserVO();
        BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
        obj.add(new BasicDBObject("username", userName));
        obj.add(new BasicDBObject("password", password));
        andQuery.put("$and", obj);
        System.out.println(andQuery.toString());

        Document userDoc = userCollection1.find(andQuery).first();
        
      

        if(userDoc !=null){
        
        

       // Document whereQuery = new Document("email", userVO.getEmail());

        //update the document and insert in the collection
        Document newDoc = new Document();
        
        
        newDoc.put(("id"), userVO.getId());
        newDoc.put(("username"), userVO.getUsername());
        newDoc.put(("password"), userVO.getPassword());
        newDoc.put(("role"), userVO.getRole());
        newDoc.put(("firstname"), userVO.getFirstName());
        newDoc.put(("lastname"), userVO.getLastName());
        newDoc.put(("gender"), userVO.getGender());
        newDoc.put("company", userVO.getCompany());
        newDoc.put(("email"), userVO.getEmail());
        newDoc.put(("phone"), userVO.getPhone());
        newDoc.put("age", userVO.getAge());
       

        userCollection1.findOneAndReplace(userDoc, newDoc);
        
        
        us.setId((String) newDoc.get("id"));
    	us.setUsername(userName);
    	us.setPassword(password);
    	us.setRole((String) newDoc.get("role"));
    	us.setFirstName((String) newDoc.get("firstname"));
    	us.setLastName((String) newDoc.get("lastname"));
    	us.setGender((String) newDoc.get("gender"));
    	us.setCompany((String) newDoc.get("company"));
    	us.setEmail((String) newDoc.get("email"));
    	us.setPhone((String) newDoc.get("phone"));
    	us.setAge((String) newDoc.get("age"));
                     
    }
		return us;
    }

	
	public String deleteUser(RequestVO requestVO) {
		
		String userName = requestVO.getUsername();
        String password = requestVO.getPassword();
        MongoCollection<Document> userCollection = mongoFactory.getCollection(mongocollection);
        
        BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
        obj.add(new BasicDBObject("username", userName));
        obj.add(new BasicDBObject("password", password));
        andQuery.put("$and", obj);
        System.out.println(andQuery.toString());

        Document userDoc = userCollection.find(andQuery).first();
        if(userDoc !=null && !userDoc.isEmpty()){
		userCollection.deleteMany(userDoc);
		return "success";
        }
		else
		return "fail";
			
	}
	
	
public List<Document> listOfDocuments() {
	MongoCollection<Document> userCollection = mongoFactory.getCollection(mongocollection);
	List<Document> documents = (List<Document>) userCollection.find().into(new ArrayList<Document>());
List<Document> list=new ArrayList<Document>();
           for(Document document : documents){
               System.out.println(document);
               list.add(document);
           }
	return list;
		
		
	}
	
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
