package com.sample.dao;


import java.util.List;

import org.bson.Document;
import com.sample.vo.RequestVO;

import com.sample.vo.UserVO;

/**
 * Created by WS on 6/12/16.
 */
public interface UserDAO {

    public UserVO getUser(RequestVO requestVO);
    public String addUser(UserVO userVO);
    public UserVO updateUser(UserVO userVO);
    public String deleteUser(RequestVO deleteVO);
    public List<Document> listOfDocuments();
}
