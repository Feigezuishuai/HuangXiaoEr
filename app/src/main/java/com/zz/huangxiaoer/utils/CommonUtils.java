package com.zz.huangxiaoer.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Process;
import android.view.View;
import android.widget.TextView;

import com.zz.huangxiaoer.R;
import com.zz.huangxiaoer.app.MyApplication;

/**
 * Created by ${韩永光} on on 2017/11/14 0014 15:52..
 */

public class CommonUtils {
    public static final String TAG = "aladdin";
    public static SharedPreferences sharedPreferences;
    private static Context context = MyApplication.getContext();

    //上下文
    public static Context getContext() {
        return context;
    }

    //打气工具
    public static View inflate(int layoutId) {
        View view = View.inflate(context, layoutId, null);
        return view;
    }

    //dip转换px
    public static int dip2px(int dip) {
        //获取像素密度
        float density = MyApplication.getContext().getResources().getDisplayMetrics().density;
        int px = (int) (dip * density + 0.5f);
        return px;
    }

    //px转换dip
    public static int px2dip(int px) {
        float density = context.getResources().getDisplayMetrics().density;
        int dip = (int) (px / density + 0.5f);
        return dip;
    }

    //获取尺寸
    public static String getDimens(int StringId) {
        String dimens = context.getResources().getString(StringId);
        return dimens;
    }

    //获取Darwable
    public static Drawable getDrawable(int did) {
        Drawable drawable = context.getResources().getDrawable(did);
        return drawable;
    }

    //保存SharedPreferences String类型
    public static void saveSp(String flag, String str) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(TAG, MyApplication.getContext().MODE_PRIVATE);

        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(flag, str);
        edit.commit();
    }

    //获取SharedPreferences String类型
    public static String getSp(String flag) {
        sharedPreferences = context.getSharedPreferences(TAG, MyApplication.getContext().MODE_PRIVATE);
        return sharedPreferences.getString(flag, "");
    }

    //获取SharedPreferences Boolean类型
    public static boolean getBoolean(String tag) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(TAG, MyApplication.getContext().MODE_PRIVATE);
        }
        return sharedPreferences.getBoolean(tag, false);
    }

    //保存SharedPreferences String类型 boolean类型
    public static void putBoolean(String tag, boolean content) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(TAG, context.MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(tag, content);
        edit.commit();
    }

    //清除haredPreferences
    public static void clearSp(String tag) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(TAG, context.MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(tag);
        edit.commit();
    }

    //更新主线程
    public static void runOnUIthread(Runnable runnable) {
        //判断当前线程是主线程OR子线程
        if (Process.myTid() == MyApplication.getMainThreadId()) {
            //在主线程  直接运行
            runnable.run();
        } else {
            //如果当前任务在子线程中，则将其传输的主线程
            MyApplication.getHandler().post(runnable);
        }

    }

    //使用线程池
    public static void executeRunnalbe(Runnable runnable) {
        MyApplication.getThreadPool().execute(runnable);
    }
    //创建跳转方法
     public static void startActivity(Activity activity,Class goTo){
         Intent intent=new Intent(activity,goTo);
         activity.startActivity(intent);
         //跳转动画
         activity.overridePendingTransition(R.anim.xin_right, R.anim.xout_left);

     }
    //返回
    public static void finishActivity(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.xin_left, R.anim.xout_right);
    }
    //获取文本文档
    public static String getText(TextView v) {
        return v.getText().toString().trim();
    }

}
