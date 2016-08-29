package com.prince.server.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by zidong.wang on 2016/8/25.
 */
public class WebApplication {
    private String webRoot;
    private String root;

    private WebApplication(){
        init();
    }
    public static WebApplication instance;
    public static WebApplication getInstance(){
        if(instance==null){
            instance = new WebApplication();
        }
        return instance;
    }
    public void init(){
        Properties pro = new Properties();
        try {
            InputStream in = this.getClass().getResourceAsStream("/config.properties");
            pro.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String userDir = pro.getProperty("user.dir");
        root = userDir;
        this.webRoot = userDir+"webroot/";
    }

    public String getWebRoot() {
        return webRoot;
    }
    public String getRoot(){
        return root;
    }
}
