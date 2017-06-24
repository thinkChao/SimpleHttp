package com.cn.simplehttp;

import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by aChao on 2017/6/23.
 */

public abstract class Callback<T> implements ICallback<T> {

    private String path;
    private int status;
    private String reponseMessage;

    @Override
    public T parse(HttpURLConnection conn) throws HttpException {
        return parse(conn,null);
    }

    @Override
    public T parse(HttpURLConnection conn,OnProgressUpdatedListener listener) throws HttpException{
        try {
            //发起请求
           // conn.connect(); 这行代码可以不要
            status = conn.getResponseCode();
            reponseMessage = conn.getResponseMessage();
            if(path == null){
                    //获取结果
                    InputStream in = conn.getInputStream();
                    InputStreamReader isReader = new InputStreamReader(in);//将字节转成字符，适配器模式
                    BufferedReader reader = new BufferedReader(isReader);
                    StringBuilder result = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null){
                        result.append(line);
                    }
                    in.close();
                    //返回结果
                    return parseResult(result.toString());
                }else {
                    //获取结果
                    FileOutputStream out = new FileOutputStream(path);//如果找不到文件，会新建一个文件
                    InputStream in = conn.getInputStream();
                    byte[] buffer = new byte[2048];
                    int totalLen = conn.getContentLength();
                    int curLen = 0;
                    int len = 0;

                    while((len = in.read(buffer)) != -1){
                        out.write(buffer,0,len);
                        curLen += len;
                        listener.onProgressUpdated(curLen,totalLen);
                    }
                    in.close();
                    out.flush();
                    out.close();
                    //返回结果
                    return parseResult(path);
                }

        } catch (Exception e) {
            throw new HttpException(status,reponseMessage);
        }

    }


    @Override
    public void onProgressUpdated(Object curLen, Object totalLen) {

    }

    //解析过程交由子类来实现
    protected abstract T parseResult(String result) throws HttpException;

    public ICallback setPath(String path){
        this.path = path;
        return this;
    }
}
