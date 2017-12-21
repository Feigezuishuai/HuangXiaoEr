package com.zz.huangxiaoer.utils;

import android.util.Log;

/**
 * Created by ${韩永光} on on 2017/11/14 0014 15:49..
 */

public class LogUtils {
    public static final  boolean isDebug=true;
    public static void i(String TAG,String info){
        if(isDebug){
            Log.i(TAG,info);
        }
    }
    public static void d(String TAG, String info){
        if(isDebug){
            Log.d(TAG,info);
        }
    }
    public static void e(String TAG,String info){
        if(isDebug){
            Log.e(TAG,info);
        }
    }
}
