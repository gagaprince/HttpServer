package com.prince.server.http;

import java.io.OutputStream;

/**
 * Created by zidong.wang on 2016/8/25.
 */
public class Response {
    private OutputStream output;
    public Response(OutputStream output){
        this.output = output;
    }
}
