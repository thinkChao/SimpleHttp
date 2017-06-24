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
            public void onFailure(HttpException e) {
                e.printStackTrace();
            }
        });

    }

    @Test
    public void testDownload() throws Exception {
        String url = "http://api.stay4it.com/uploads/test.jpg";
        final Request request = new Request(url);
        request.enableProgressUpdated(true);
        RequestTask task = new RequestTask(request);
        String path = Environment.getExternalStorageDirectory() + File.separator+"demo.jpg";
        Log.e("Print path",path);
        request.setCallback(new FileCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("Print result:","success!");
            }

            @Override
            public void onProgressUpdated(Object curLen, Object totalLen) {
                Log.e("Print progresss",curLen+"/"+totalLen);
            }

            @Override
            public void onFailure(HttpException e) {
                Log.e("Print result:","fail!");
                e.printStackTrace();
            }
        }.setPath(path));
        task.execute();

    }
}