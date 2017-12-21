package com.zz.huangxiaoer.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;

import com.zhy.autolayout.AutoLayoutActivity;
import com.zz.huangxiaoer.R;

/**
 * Created by ${韩永光} on on 2017/11/15 0015 11:11..
 */

public class BaseActivity extends AutoLayoutActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_base);
    }
    //监听返回
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
            overridePendingTransition(R.anim.xin_left, R.anim.xout_right);
        }
        return super.onKeyDown(keyCode, event);
    }
}
