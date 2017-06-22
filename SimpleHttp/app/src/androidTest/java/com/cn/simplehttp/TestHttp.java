package com.cn.simplehttp;

import android.util.Log;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aChao on 2017/6/21...
 */
public class TestHttp {

    @Test
    public void post() throws Exception {
        String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
        String content = "account=stay4it&password=123456";
        Request request = new Request(url, Request.RequestMethod.POST);
        request.content = content;
        String result = HttpUrlConnectionUtil.execute(request);
        Log.e("Print result2:",result);
    }

    @Test
    public void get() throws Exception {
        String url = "http://api.stay4it.com";
        Request request = new Request(url);
        String result = HttpUrlConnectionUtil.execute(request);
        Log.e("Print result1:",result);
    }

}