package com.prince.server.http;

import java.io.InputStream;

/**
 * Created by zidong.wang on 2016/8/25.
 */
public class Request {
    private InputStream input;
    public Request(InputStream input){
        this.input = input;
    }
}
