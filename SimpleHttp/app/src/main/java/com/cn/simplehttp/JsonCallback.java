package com.cn.simplehttp;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by aChao on 2017/6/23.
 */

public abstract class JsonCallback<T> extends Callback<T> {
    @Override
    protected T parseResult(String result) throws Exception {
        JSONObject json = new JSONObject(result);
        JSONObject data = json.optJSONObject("data");
        Gson gson = new Gson();
        Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return gson.fromJson(data.toString(), type);
    }

}
