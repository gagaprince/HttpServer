package com.prince.server.jsp.temp;

import com.prince.server.http.Request;
import com.prince.server.http.Response;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by zidong.wang on 2016/8/30.
 */
public class TestJsp {

    public void out(Request request,Response response){
        OutputStream out = response.getOutputStream();
        try {
            out.write("sss".getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
