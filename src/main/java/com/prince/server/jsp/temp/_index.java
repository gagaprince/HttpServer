package com.prince.server.jsp.temp;
import com.prince.server.http.Request;
import com.prince.server.http.Response;
import java.io.IOException;
import java.io.OutputStream;
public class _index{
   public void out(Request request,Response response){
       OutputStream out = response.getOutputStream();
       try {
           out.write("<!doctype html>".getBytes());
           out.write("<html lang=\"en\">".getBytes());
           out.write("<head>".getBytes());
           out.write("    <meta charset=\"UTF-8\">".getBytes());
           out.write("    <meta name=\"viewport\" content=\"width=device-width,height=device-height,initial-scale=1.0,maximum-scale=1.0,user-scalable=no\" />".getBytes());
           out.write("    <meta name=\"apple-mobile-web-app-capable\" content=\"yes\" /><!-- ����WebӦ����ȫ��ģʽ���� -->".getBytes());
           out.write("    <meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\" /><!-- ����״̬�� -->".getBytes());
           out.write("    <meta name=\"format-detection\" content=\"telephone=no\" /><!-- �����豸���Խ�ҳ���е�����ʶ��Ϊ�绰���� -->".getBytes());
           out.write("    <title>��ҳ</title>".getBytes());
           out.write("</head>".getBytes());
           out.write("<body>".getBytes());
           out.write("    <p>��ҳ</p>".getBytes());
           out.write("".getBytes());
           out.write("<script>".getBytes());
           out.write("".getBytes());
           out.write("</script>".getBytes());
           out.write("".getBytes());
           out.write("</body>".getBytes());
           out.write("</html>".getBytes());
           out.write("".getBytes());
           out.flush();
           out.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}
