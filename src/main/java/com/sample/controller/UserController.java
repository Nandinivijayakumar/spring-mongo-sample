package com.sample.controller;

import com.sample.dao.MongoFactory;
import com.sample.service.UserService;
import com.sample.util.RequestValidator;
import com.sample.vo.RequestVO;
import com.sample.vo.ResponseVO;
import com.sample.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.sample.util.APIMessage.BACKEND_SERVER_EXCEPTION;
import static com.sample.util.APIMessage.REQUEST_VALIDATION_FAIL;

import java.util.List;

import org.bson.Document;


/**
 * Created by WS on 8/12/16.
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    RequestValidator requestValidator;
    
    

    @Autowired
    ResponseVO responseVO;

    String response=null;

    /**
     * validates the login and return the role of the user
     * @param requestVO
     * @return
     */
    @RequestMapping(path="/get", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO getUserInfo(@RequestBody RequestVO requestVO){

    
        if(!requestValidator.validateRequest(requestVO)){
        	 responseVO.setResponse("FAIL");
            responseVO.setStatus(ResponseVO.FAIL);
            responseVO.setMessage(REQUEST_VALIDATION_FAIL);
            responseVO.setUserVO(null);
            return responseVO;
        }
        
        try {
            UserVO userVO = userService.getUser(requestVO);
            if(userVO.username==null)
            {
            	responseVO.setUserVO(null);
                responseVO.setStatus(ResponseVO.FAIL);
                responseVO.setMessage("No such User");
                responseVO.setResponse("FAIL");
            }
            else{
            responseVO.setUserVO(userVO);
            responseVO.setStatus(ResponseVO.SUCCESS);
            responseVO.setMessage("Got successfully");
            responseVO.setResponse("OK");
            }
        } catch (Exception e) {
            responseVO.setUserVO(null);
            responseVO.setResponse("FAIL");
            responseVO.setStatus(ResponseVO.FAIL);
            responseVO.setMessage(BACKEND_SERVER_EXCEPTION);
        }
        return responseVO;
    }
    
    
    @RequestMapping(path="/create",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO addUserInfo(@RequestBody UserVO userVO){

        	 try {
            String b=userService.addUser(userVO);
           if(b.equals("success"))
           {
           responseVO.setUserVO(userVO);
           responseVO.setStatus(ResponseVO.SUCCESS);
           responseVO.setMessage("Added successfully");
           responseVO.setResponse("OK");
           }
           else
           {
        	   //responseVO.setUserVO(userVO);
               responseVO.setStatus(ResponseVO.FAIL);
               responseVO.setMessage("Already Exists");
               responseVO.setResponse("Fail");
           }
        	 }
        catch (Exception e) {
        	 responseVO.setResponse("Fail");
           responseVO.setUserVO(null);
           responseVO.setStatus(ResponseVO.FAIL);
           responseVO.setMessage(BACKEND_SERVER_EXCEPTION);
        }
        return responseVO;
    }
        	 
    
    
    
    
    @RequestMapping(path="/update",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateUserInfo(@RequestBody UserVO userVO){

       
        try {
            UserVO uv=userService.updateUser(userVO);
           
            if(uv.username!=null)
            {
            responseVO.setUserVO(uv);
            responseVO.setStatus(ResponseVO.SUCCESS);
            responseVO.setMessage("Updated successfully");
            responseVO.setResponse("OK");
            }
            else{
                    	responseVO.setUserVO(null);
                        responseVO.setStatus(ResponseVO.FAIL);
                        responseVO.setMessage("No such user");
                        responseVO.setResponse("FAIL");
                    }
            
        } catch (Exception e) {
        	responseVO.setUserVO(null);
        	 responseVO.setResponse("FAIL");
            responseVO.setStatus(ResponseVO.FAIL);
            responseVO.setMessage(BACKEND_SERVER_EXCEPTION);
        }
        return responseVO;
    }
    
    @RequestMapping(path="/delete",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO deleteUserInfo(@RequestBody RequestVO requestVO){

        
        try {
            String a=userService.deleteUser(requestVO);
            if(a.equals("success"))
            {
            responseVO.setStatus(ResponseVO.SUCCESS);
            responseVO.setMessage("Deleted successfully");
            responseVO.setResponse("OK");
            }
            else
            {
            	responseVO.setStatus(ResponseVO.FAIL);
                responseVO.setMessage("No such user");
                responseVO.setResponse("FAIL");
                }
            
            	
        } catch (Exception e) {
        	
           responseVO.setUserVO(null);
           responseVO.setStatus(ResponseVO.FAIL);
           responseVO.setMessage(BACKEND_SERVER_EXCEPTION);
        }
        return responseVO;
    }
    
    
    @RequestMapping(path="/documents",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Document> listOfDocumentsInfo(){
    	
    	List<Document> list=userService.listDocument();
    	
		return list;
    
    
    
    
    
    
    

}
}
