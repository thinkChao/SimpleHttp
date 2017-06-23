package com.cn.simplehttp;

import android.util.Log;

/**
 * Created by aChao on 2017/6/23.
 */

public abstract class FileCallback extends Callback<String> {
    @Override
    protected String parseResult(String path) throws Exception {
        return path;
    }

}
