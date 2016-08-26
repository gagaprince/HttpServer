package com.prince.server.http;

import java.io.*;
import java.util.*;

/**
 * Created by zidong.wang on 2016/8/25.
 */
public class Response {
    private int statusCode = 200;
    private String version = "HTTP/1.1";
    private String status = "OK";

    private String header;

    private Map<String,String> headMap;



    private OutputStream output;
    public Response(OutputStream output){
        this.output = output;
        initHeader();
    }

    private void initHeader(){
        headMap = new HashMap<String, String>();
        headMap.put("Date",new Date().toString());
        headMap.put("Content-Type","text/html;charset=utf-8");
        headMap.put("Connection","keep-alive");
        headMap.put("Cache-Control", "private");
//        headMap.put("Content-Encoding", "gzip");
    }

    public void setHeader(String key,String value){
        headMap.put(key,value);
    }

    private String giveMeHeader(){
        StringBuffer sb = new StringBuffer();
        Set<String> keySet = headMap.keySet();
        Iterator<String> it = keySet.iterator();
        while(it.hasNext()){
            String key = it.next();
            String value = headMap.get(key);
            sb.append(key+":"+value);
            sb.append("\r\n");
        }
        return sb.toString();
    }

    private String giveMeStatus(){
        return version+" "+statusCode+" "+status;
    }
    private String giveMeContent(){
        return "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\">"+
                "<title>哈哈哈</title>" +
                "</head>" +
                "<body>web server test</body>"+
                "</html>";
    }

    public OutputStream getOutputStream(){
        return output;
    }

    public void out(){
//        setHeader("Content-Length",giveMeContent().length()+"");

        StringBuffer outStr = new StringBuffer();
        outStr.append(giveMeStatus()).append("\r\n");
        outStr.append(giveMeHeader()).append("\r\n");
        outStr.append(giveMeContent()).append("\r\n");

        System.out.println(outStr.toString());

        try {
            output.write(outStr.toString().getBytes("utf-8"));
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void forward(String path){
        File f = new File(path);
        writeHeader();
        writeFile(f);
    }
    private void writeHeader(){
        StringBuffer outStr = new StringBuffer();
        outStr.append(giveMeStatus()).append("\r\n");
        outStr.append(giveMeHeader()).append("\r\n");
        try {
            output.write(outStr.toString().getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeFile(File f){
        try {
            FileInputStream fileInputStream = new FileInputStream(f);
            byte[] buf = new byte[1024];
            int len = 0;
            while((len=fileInputStream.read(buf))>0){
                output.write(buf,0,len);
            }
            output.flush();
            output.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
