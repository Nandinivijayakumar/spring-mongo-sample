package com.sample.util;


import com.sample.vo.RequestVO;

import org.springframework.stereotype.Service;

/**
 * This class validates the request
 * Created by WS on 24/11/16.
 */
@Service
public class RequestValidator {

    public Boolean validateRequest(RequestVO requestVO){

        if((requestVO.getUsername() == null || "".equals(requestVO.getUsername()) ) && (requestVO.getPassword() ==null || "".equals(requestVO.getPassword())) )
        {
        	
        		return false;
        	
        }
        else
              
        	return true;
}
}
