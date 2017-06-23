package com.cn.simplehttp;

import android.util.Log;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aChao on 2017/6/21.
 */
public class TestHttp {




    @Test
    public void test() throws Exception {
        String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
        String content = "account=stay4it&password=123456";
        final Request request = new Request(url, Request.RequestMethod.POST);
        request.content = content;
        RequestTask task = new RequestTask(request);
        task.execute();
        request.setCallback(new JsonCallback<User>() {
            @Override
            public void onSuccess(User result) {
                Log.e("Print result:",result.account);
            }
            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });

    }
}