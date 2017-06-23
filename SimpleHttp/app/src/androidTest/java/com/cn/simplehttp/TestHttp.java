package com.cn.simplehttp;

import android.os.Environment;
import android.util.Log;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by aChao on 2017/6/21.
 */
public class TestHttp {

    @Test
    public void RequestOnSubThread() throws Exception {
        String url = "http://api.stay4it.com/v1/public/core/";  //这个请求会返回500
        String content = "account=stay4it&password=123456";
        Request request = new Request(url);
        //request.content = content;
        RequestTask task = new RequestTask(request);
        task.execute();
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {

                Log.e("Print result:",result);
            }
            @Override
            public void onFailure(HttpException e) {
                Log.e("Print status:",""+e.status);
                Log.e("Print responseMessage",e.getMessage());
            }
        });

    }
}