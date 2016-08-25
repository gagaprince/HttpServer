package com.prince.server.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zidong.wang on 2016/8/25.
 */
public class Request {
    private InputStream input;
    private String header;
    private String location;
    private String path;
    private String search;
    private String method;
    private Map<String,String> parameters;
    public Request(InputStream input){
        this.input = input;
        this.parameters = new HashMap<String, String>();
    }
    //·ÖÎörequest
    public void parse(){
        header = parseContent();
        parseLocation();


    }

    private String parseContent(){
        StringBuffer sb = new StringBuffer();
        byte[] b = new byte[1024];
        int readbyte=0;
        try {
            while((readbyte=input.read(b))!=-1){
                sb.append(new String(b,0,readbyte));
                if(sb.length()>4){
                    String end = sb.substring(sb.length()-4);
                    if(end.equals("\r\n\r\n")){
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        System.out.println(sb.toString());
//        System.out.println(sb.length());
        return sb.toString();
    }

    private void parseLocation(){
        String header = this.header;
        String[] items = header.split("\r\n");
        if(items.length>0){
            String locationItem = items[0];
            String[] ss = locationItem.split(" ");
            method = ss[0];
            location = ss[1];

            String[] ls = location.split("\\?");
            path = ls[0];
            if(ls.length>1){
                search = ls[1];
                String[] parameterStrs = search.split("&");
                for(String parameterStr:parameterStrs){
                    String[] kv = parameterStr.split("=");
                    parameters.put(kv[0],kv[1]);
                }
            }
        }

    }

    public String getParameter(String key) {
        return parameters.get(key);
    }

    public String getLocation() {
        return location;
    }

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }

    public String getSearch() {
        return search;
    }

    public String getHeader() {
        return header;
    }
}
