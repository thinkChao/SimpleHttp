package com.cn.simplehttp;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by aChao on 2017/6/23.
 */

public abstract class XmlCallback<T> extends Callback<T> {
    @Override
    protected T parseResult(String result) throws Exception {
        //TODO : parse Xml
        return null;
    }

}
