package com.prince.server.jsp.temp;
import com.prince.server.http.Request;
import com.prince.server.http.Response;
import java.io.IOException;
import java.io.OutputStream;
public class _index{
   public void out(Request request,Response response){
       OutputStream out = response.getOutputStream();
       try {
           out.write("<!doctype html><html lang=\"en\"><head>    <meta charset=\"UTF-8\">    <meta name=\"viewport\" content=\"width=device-width,height=device-height,initial-scale=1.0,maximum-scale=1.0,user-scalable=no\" />    <meta name=\"apple-mobile-web-app-capable\" content=\"yes\" /><!-- ����WebӦ����ȫ��ģʽ���� -->    <meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\" /><!-- ����״̬�� -->    <meta name=\"format-detection\" content=\"telephone=no\" /><!-- �����豸���Խ�ҳ���е�����ʶ��Ϊ�绰���� -->    <title>��ҳ</title></head><body>  ".getBytes("utf-8"));
            for(int i=0;i<10;i++){
           out.write("    <p>��ҳ".getBytes("utf-8"));
           out.write((i+"").getBytes("utf-8"));
           out.write("</p>  ".getBytes("utf-8"));
           }
           out.write("<script></script></body></html>".getBytes("utf-8"));
           out.flush();
           out.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}
