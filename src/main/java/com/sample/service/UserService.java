package com.sample.service;

import com.sample.dao.UserDAO;
import com.sample.vo.RequestVO;

import com.sample.vo.UserVO;

import java.util.List;

import org.bson.Document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by WS on 8/12/16.
 */
@Service
public class UserService {

    @Autowired
    @Qualifier("userDAOMongo")
    UserDAO userDAO;

    /**
     *
     * @param userVO
     */
    public UserVO getUser(RequestVO userVO){

        UserVO user = userDAO.getUser(userVO);

        return user;
    }

    public String addUser(UserVO userVO){
        String b=userDAO.addUser(userVO);
        return b;
    }

    public UserVO updateUser(UserVO userVO){
        UserVO uservo=userDAO.updateUser(userVO);
		return uservo;
    }
    public String deleteUser(RequestVO requestVO){
        String b=userDAO.deleteUser(requestVO);
       
        return b; 
    }
    public List<Document> listDocument(){
        List<Document> lt=userDAO.listOfDocuments();
       
        return lt; 
    }


}
