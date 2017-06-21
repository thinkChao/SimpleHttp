package com.cn.simplehttp;

import java.util.Map;

/**
 * Created by aChao on 2017/6/20.
 */

public class Request {
    //构造方法的参数都是一些必须要传递的参数，例如url和method
    public Request(String url,RequestMethod method){
        this.url = url;
        this.method = method;
    }
    //如果嫌麻烦，不想设置请求method，我们可以指定默认值
    public Request(String url){
        this.url = url;
        this.method = RequestMethod.GET;
    }

    public enum RequestMethod{GET,POST,PUT,DELETE}
    public RequestMethod method;
    public String url;
    public Map<String,String> header;
    public String content;


}
