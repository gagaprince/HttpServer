package com.prince.server.http;

import com.prince.server.jsp.CreateJSPFile;

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
            String ext = path.split("\\.")[1];
            if(ext.equals("jsp")){
                CreateJSPFile c = new CreateJSPFile();
                c.parseJsp(path);
            }else{
                response.out();
            }
        }

    }
}
