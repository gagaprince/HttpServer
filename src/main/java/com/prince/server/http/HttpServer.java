package com.prince.server.http;

import com.sun.corba.se.spi.orbutil.fsm.Input;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by zidong.wang on 2016/8/23.
 */
public class HttpServer {
    public int port;
    public String userDir;
    public String webRoot;

    public HttpServer(){
        init();
    }

    private void init(){
        initUserPath();
    }

    private void initUserPath(){
        Properties pro = new Properties();
        try {
            InputStream in = this.getClass().getResourceAsStream("/config.properties");
            pro.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        userDir = pro.getProperty("user.dir");
        System.out.println(userDir);
    }

    public void listen(int port){

    }
}
