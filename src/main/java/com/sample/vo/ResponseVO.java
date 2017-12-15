package com.sample.vo;

import org.springframework.stereotype.Component;

/**
 * Created by WS on 12/12/16.
 */
@Component
public class ResponseVO {

    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";

    private String response;
    private String status;
    private String message;

    private UserVO userVO;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }
}
