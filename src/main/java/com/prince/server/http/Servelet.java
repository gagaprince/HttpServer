package com.prince.server.http;

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
    }
}
