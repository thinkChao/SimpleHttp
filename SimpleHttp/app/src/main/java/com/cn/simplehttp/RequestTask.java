package com.cn.simplehttp;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by aChao on 2017/6/22.
 */

public class RequestTask extends AsyncTask {

    private Request request;

    public RequestTask(Request request) {
        this.request = request;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] params) {
        try {
           HttpURLConnection conn = HttpUrlConnectionUtil.execute(request);
            return request.iCallBack.parse(conn);
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if(o instanceof Exception){
            request.iCallBack.onFailure((Exception) o);
        }else {
            request.iCallBack.onSuccess(o);
        }
    }
}
