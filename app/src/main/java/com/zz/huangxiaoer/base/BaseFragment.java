package com.zz.huangxiaoer.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zz.huangxiaoer.interfaces.IOnResetShowingPage;
import com.zz.huangxiaoer.views.ShowingPage;

/**
 * Created by ${韩永光} on on 2017/11/17 0017 11:17..
 */

public abstract class BaseFragment extends Fragment {

    public ShowingPage showingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //因为不知道具体的展示title  需要继续抽象
        showingPage = new ShowingPage(getContext()) {
            @Override
            public View setSuccessView() {
                //因为不知道具体的展示title  需要继续抽象
                return CreateSuccessView();
            }
        };
        return showingPage;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onload();
        showingPage.setIOnResetShowingPage(new IOnResetShowingPage() {
            @Override
            public void onReset(View v) {
                onload();
            }
        });
    }

    protected abstract void onload();

    public abstract View CreateSuccessView();
}