package com.prince.server.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zidong.wang on 2016/8/23.
 */
public class HttpServer {
    public int port;

    public HttpServer(){
        init();
    }

    private void init(){
        initWebApp();
    }

    private void initWebApp(){
        WebApplication webApplication = WebApplication.getInstance();
        webApplication.init();
    }

    private void initServer(int port){
        this.port = port;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port,1);
            System.out.println("端口"+port+" 服务器已经启动了......");
            while(true){
                Socket socket = null;
                socket = serverSocket.accept();
                System.out.println("有一个请求进来了哟！");
                //启动一个线程处理socket请求
                new SocketThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void listen(int port){
        initServer(port);
    }
}
