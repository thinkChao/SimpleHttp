package com.cn.simplehttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by aChao on 2017/6/20.
 */

public class HttpUrlConnectionUtil {

    public static HttpURLConnection execute(Request request) throws HttpException {
        switch (request.method){
            case GET:
                return get(request);
            case POST:
                return post(request);
        }
        return null;
    }

    private static HttpURLConnection get(Request request) throws HttpException {
        //声明一个URL连接
        HttpURLConnection conn = null;
        try {
            URL newUrl = new URL(request.url);
            conn = (HttpURLConnection)newUrl.openConnection();
            conn.setRequestMethod(request.method.toString());
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setDoInput(true);
            //添加header
            addHeader(conn,request.header);
        } catch (IOException e) {
            throw new HttpException(e.getMessage());
        }
        return conn;
    }

    private static HttpURLConnection  post(Request request) throws HttpException {
        HttpURLConnection conn = null;
        try {
            //声明一个URL连接
            URL newUrl = new URL(request.url);
            conn = (HttpURLConnection)newUrl.openConnection();
            conn.setRequestMethod(request.method.toString());
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            //添加header
            addHeader(conn,request.header);
            //将参数传到服务器
            OutputStream out = conn.getOutputStream();
            out.write(request.content.getBytes());
        } catch (IOException e) {
            throw new HttpException(e.getMessage());
        }
        return conn;
    }

    private static void addHeader(HttpURLConnection conn,Map<String,String> header){
        if(header == null || header.size() == 0)
            return;
        for (Map.Entry<String,String> entry : header.entrySet()){
            conn.addRequestProperty(entry.getKey(),entry.getValue());
        }
    }
}
