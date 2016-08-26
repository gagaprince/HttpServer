package com.prince.server.http;

import java.applet.Applet;

/**
 * Created by zidong.wang on 2016/8/25.
 */
public class Servelet {
    public void doGet(Request request, Response response){
        String location = request.getLocation();
        String av = request.getParameter("a");
        String path = request.getPath();

        System.out.println(location);
        System.out.println(av);
        System.out.println(path);

//        response.out();

        if(path.indexOf(".")==-1){
            WebApplication webApplication = WebApplication.getInstance();
            String dirPath = webApplication.getWebRoot()+path+".html";
            response.forward(dirPath);
        }else{
            response.out();
        }

    }
}
