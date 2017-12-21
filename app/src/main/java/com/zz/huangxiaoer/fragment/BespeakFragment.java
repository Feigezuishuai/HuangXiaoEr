package com.zz.huangxiaoer.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zz.huangxiaoer.R;
import com.zz.huangxiaoer.activity.AddressActivity;
import com.zz.huangxiaoer.base.BaseFragment;
import com.zz.huangxiaoer.utils.CommonUtils;
import com.zz.huangxiaoer.views.ShowingPage;

/**
 * Created by ${韩永光} on on 2017/11/17 0017 15:44..
 */

public class BespeakFragment extends BaseFragment {

    private TextView tv_bespeakfragment;
    private final static int REQUESTCODE = 1; // 返回的结果码

    @Override
    protected void onload() {
        CommonUtils.runOnUIthread(new Runnable() {
            @Override
            public void run() {
                showingPage.setCurrentState(ShowingPage.StateType.STATE_LOAD_SUCCESS);

                tv_bespeakfragment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                          CommonUtils.startActivity(getActivity(), AddressActivity.class);
                        // 意图实现activity的跳转
                        Intent intent =  new Intent(getActivity(), AddressActivity.class);
                        startActivityForResult(intent, 0);
                    }
                });
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0&&resultCode==0) {
            String name = data.getStringExtra("name");
            tv_bespeakfragment.setText(name);
        }
    }

    @Override
    public View CreateSuccessView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_bespeak, null);
        tv_bespeakfragment = (TextView) view.findViewById(R.id.tv_bespeakfragment);
        return view;
    }


}
