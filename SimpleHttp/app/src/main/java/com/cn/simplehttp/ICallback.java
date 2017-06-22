package com.cn.simplehttp;

/**
 * Created by aChao on 2017/6/22.
 */

public interface ICallback {
    void onSuccess(String result);
    void onFailure(Exception e);
}
