package com.cn.simplehttp;

import android.util.Log;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aChao on 2017/6/21.
 */
public class TestHttp {

    @Test
    public void post() throws Exception {
        String url = "http://api.stay4it.com";
        Request request = new Request();
        request.url = url;
        String result = HttpUrlConnectionUtil.get(request);
        Log.e("Print result2:",result);
    }

    @Test
    public void get() throws Exception {
        String url = "http://api.stay4it.com";
        Request request = new Request();
        request.url = url;
        String result = HttpUrlConnectionUtil.get(request);
        Log.e("Print result1:",result);
    }

}