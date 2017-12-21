package com.zz.huangxiaoer.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.zz.huangxiaoer.R;
import com.zz.huangxiaoer.base.BaseFragment;
import com.zz.huangxiaoer.utils.CommonUtils;
import com.zz.huangxiaoer.views.ShowingPage;

/**
 * Created by ${韩永光} on on 2017/11/17 0017 15:43..
 */

public class MineFragmeng extends BaseFragment {
    @Override
    protected void onload() {
        CommonUtils.runOnUIthread(new Runnable() {
            @Override
            public void run() {
                showingPage.setCurrentState(ShowingPage.StateType.STATE_LOAD_SUCCESS);
            }
        });
    }

    @Override
    public View CreateSuccessView() {
       // View view=CommonUtils.inflate(R.layout.fragment_mine);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_mine, null);
        Log.d("wwwwwww","~~~~~~~~~~~~~~~~~~~");
        return view;
    }
}
