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
    @Override
    public T parse(HttpURLConnection conn) throws Exception {
        //发起请求
        conn.connect();
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
            int len = 0;
            int i=0;
            while((len = in.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            in.close();
            out.flush();
            out.close();
            //返回结果
            return parseResult(path);
        }

    }

    //解析过程交由子类来实现
    protected abstract T parseResult(String result) throws Exception;

    public ICallback setPath(String path){
        this.path = path;
        return this;
    }
}
