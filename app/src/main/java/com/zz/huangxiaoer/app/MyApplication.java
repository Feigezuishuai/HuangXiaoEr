package com.zz.huangxiaoer.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.xutils.x;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ${韩永光} on on 2017/11/14 0014 15:30..
 */

public class MyApplication extends Application{

    private static Context context;
    private static Handler handler;
    private static int mainThreadId;
    private static ExecutorService threadPool;
    public static boolean isLogin = false;//判定是否登录
    @Override
    public void onCreate() {
        x.Ext.init(this);
        x.Ext.setDebug(false); //输出debug日志，开启会影响性能
        //获取当前应用上下文
        context = getApplicationContext();
        //创建线程
        handler = new Handler();
        //获取主线程ID
        mainThreadId = Process.myTid();
        //创建线程池
        threadPool = Executors.newFixedThreadPool(5);
        //初始化Fresco
        Fresco.initialize(context);


    }
    public static Context getContext(){
        return  context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

    public static ExecutorService getThreadPool() {
        return threadPool;
    }
}
