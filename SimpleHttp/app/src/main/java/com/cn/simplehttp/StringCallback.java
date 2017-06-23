package com.cn.simplehttp;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by aChao on 2017/6/23.
 */

public abstract class StringCallback extends Callback<String> {
    @Override
    protected String parseResult(String result) throws HttpException {
        return result;
    }

}
