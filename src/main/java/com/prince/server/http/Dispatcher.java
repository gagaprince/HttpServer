package com.prince.server.http;

/**
 * Created by zidong.wang on 2016/8/25.
 */
public class Dispatcher {
    public static Dispatcher instance;
    private Dispatcher(){}
    public static Dispatcher getInstance(){
        if(instance==null){
            instance = new Dispatcher();
        }
        return instance;
    }
    public Servelet dispatch(Request request){
        //���� request ���ش����servlet
        Servelet servelet = new Servelet();
        return servelet;
    }
}
