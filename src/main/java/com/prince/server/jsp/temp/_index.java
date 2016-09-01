package com.prince.server.jsp.temp;
import com.prince.server.http.Request;
import com.prince.server.http.Response;
import java.io.IOException;
import java.io.OutputStream;
public class _index{
   public void out(Request request,Response response){
       OutputStream out = response.getOutputStream();
       try {
           out.write("<!doctype html><html lang=\"en\"><head>    <meta charset=\"UTF-8\">    <meta name=\"viewport\" content=\"width=device-width,height=device-height,initial-scale=1.0,maximum-scale=1.0,user-scalable=no\" />    <meta name=\"apple-mobile-web-app-capable\" content=\"yes\" /><!-- 设置Web应用以全屏模式运行 -->    <meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\" /><!-- 隐藏状态栏 -->    <meta name=\"format-detection\" content=\"telephone=no\" /><!-- 告诉设备忽略将页面中的数字识别为电话号码 -->    <title>首页</title></head><body>  ".getBytes("is0-8859-1"));
            for(int i=0;i<10;i++){
           out.write("    <p>首页</p>  ".getBytes("is0-8859-1"));
           }
           out.write("<script></script></body></html>".getBytes("is0-8859-1"));
           out.flush();
           out.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}
