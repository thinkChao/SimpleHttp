package com.cn.simplehttp;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by aChao on 2017/6/22.
 */

public interface ICallback<T> {
    void onSuccess(T result);
    void onFailure(HttpException e);
    T parse(HttpURLConnection conn) throws HttpException;
}
