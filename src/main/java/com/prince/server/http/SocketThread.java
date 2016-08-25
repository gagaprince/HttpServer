package com.prince.server.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by zidong.wang on 2016/8/25.
 */
public class SocketThread extends Thread {
    private Socket socket;
    private Request request;
    private Response response;
    private Dispatcher dispatcher;



    public SocketThread(Socket socket){
        this.socket = socket;
        this.dispatcher = Dispatcher.getInstance();
        try {
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            this.request = new Request(input);
            this.response = new Response(output);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void run(){
        try {
            Request request = this.request;
            Response response = this.response;

            request.parse();
            Servelet servelet = dispatcher.dispatch(request);
            servelet.doGet(request,response);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
