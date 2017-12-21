package com.zz.huangxiaoer.base;

import android.os.Bundle;
import android.view.View;

import com.zz.huangxiaoer.interfaces.IOnResetShowingPage;
import com.zz.huangxiaoer.utils.NetUtils;
import com.zz.huangxiaoer.views.ShowingPage;


/**
 * Created by 韩用光
 * on 2017/11/17 13:11.
 */

public abstract class BaseShowingPageActivity extends BaseActivity {

    public ShowingPage showingPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showingPage = new ShowingPage(this) {
            @Override
            public View setSuccessView() {
                return createSuccessView();
            }

        };

        setContentView(showingPage);
        createTitleView();

        showingPage.setIOnResetShowingPage(new IOnResetShowingPage() {
            @Override
            public void onReset(View v) {
                onLoad();
            }
        });

        //对网络进行判断
        if (NetUtils.isNoNet()) {
            showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
        } else {
            onLoad();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    //要求加载数据
    protected abstract void onLoad();

    //设置Title
    protected abstract void createTitleView();

    //创建我的成功视图
    protected abstract View createSuccessView();

    public void showCurrentPage(ShowingPage.StateType stateType) {
        //调用showingPage的方法
        if (showingPage != null) {
            showingPage.setCurrentState(stateType);
        }
    }
}
