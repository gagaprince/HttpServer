package com.prince.server.http;

import java.io.OutputStream;
import java.util.*;

/**
 * Created by zidong.wang on 2016/8/25.
 */
public class Response {
    private int statusCode;
    private String version = "HTTP/1.1";
    private String status;

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
        headMap.put("Cache-Control","private");
        headMap.put("Content-Encoding", "gzip");


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
                "<title></title>" +
                "</head>" +
                "<body>web server test</body>"+
                "</html>";
    }

}
