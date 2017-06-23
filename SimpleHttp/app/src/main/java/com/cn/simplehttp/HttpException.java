package com.cn.simplehttp;

import java.security.PublicKey;

/**
 * Created by aChao on 2017/6/23.
 */

public class HttpException extends Exception {

    public int status;
    public String responseMessage;

    public HttpException(int status,String responseMessage){
        super(responseMessage);
        this.status = status;
        this.responseMessage = responseMessage;
    }

    public HttpException(String detailMessage){
        super(detailMessage);
    }


}
