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
    public void testDownload() throws Exception {
        String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
        String content = "account=stay4it&password=123456";
        final Request request = new Request(url, Request.RequestMethod.POST);
        request.content = content;
        RequestTask task = new RequestTask(request);
        String path = Environment.getExternalStorageDirectory() + File.separator+"demo.txt";
        Log.e("Print path",path);
        request.setCallback(new FileCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("Print result:","success!");
            }
            @Override
            public void onFailure(Exception e) {
                Log.e("Print result:","fail!");
                e.printStackTrace();
            }
        }.setPath(path));
        task.execute();

    }
}