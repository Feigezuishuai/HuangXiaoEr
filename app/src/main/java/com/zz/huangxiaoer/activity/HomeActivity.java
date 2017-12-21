package com.zz.huangxiaoer.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.widget.RadioGroup;

import com.zhy.autolayout.AutoLayoutActivity;
import com.zz.huangxiaoer.R;
import com.zz.huangxiaoer.factory.FragmentFactory;

public class HomeActivity extends AutoLayoutActivity {

    private ViewPager vp_home;
    private RadioGroup rg_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        initView();
        //初始化viewPager
        initViewPager();
        //初始化RadioGroup
        initRadioGroup();
    }

    private void initRadioGroup() {
        rg_home.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
               switch (checkedId){
                   case R.id.rb_bespeak:
                       vp_home.setCurrentItem(0);
                       break;
                   case  R.id.rb_main:
                       vp_home.setCurrentItem(1);
                       break;

               }
            }
        });
    }

    private void initViewPager() {
        vp_home.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = FragmentFactory.getFragment(position);
                return fragment;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }

    private void initView() {
        vp_home = (ViewPager) findViewById(R.id.vp_home);
        rg_home = (RadioGroup) findViewById(R.id.rg_home);

    }
}
