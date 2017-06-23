package com.cn.simplehttp;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by aChao on 2017/6/23.
 */

public abstract class Callback<T> implements ICallback<T> {

    @Override
    public T parse(HttpURLConnection conn) throws Exception {
        //发起请求
        conn.connect();
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
    }

    //解析过程交由子类来实现
    protected abstract T parseResult(String result) throws Exception;
}
