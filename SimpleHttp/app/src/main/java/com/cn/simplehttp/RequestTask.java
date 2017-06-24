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
            if (request.enableProgressUpdated == true) {
                return request.iCallBack.parse(conn, new OnProgressUpdatedListener() {
                    @Override
                    public void onProgressUpdated(Object curLen, Object totalLen) {
                        publishProgress(curLen,totalLen);
                    }
                });
            } else {
                return request.iCallBack.parse(conn);
            }
        } catch (HttpException e) {
            return e;   //在这里就不抛出了，因为已经到达最外层了
        }
    }
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if(o instanceof Exception){
            request.iCallBack.onFailure((HttpException) o);
        }else {
            request.iCallBack.onSuccess(o);
        }
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
        request.iCallBack.onProgressUpdated(values[0],values[1]);
    }
}
