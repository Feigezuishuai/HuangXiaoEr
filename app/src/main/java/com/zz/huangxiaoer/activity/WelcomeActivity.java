package com.zz.huangxiaoer.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.zhy.autolayout.AutoLayoutActivity;
import com.zz.huangxiaoer.R;
import com.zz.huangxiaoer.manager.LazyViewPager;
import com.zz.huangxiaoer.myadapter.VpAdapterWelcome;
import com.zz.huangxiaoer.utils.CommonUtils;

import java.util.ArrayList;

public class WelcomeActivity extends AutoLayoutActivity {

    private ImageButton ibtn_welcome;
    private LazyViewPager vp_welcome;
    private ArrayList<Integer> imgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
        //初始化控件
        initView();
        //初始化ViewPager
        initViewPager();
    }

    private void initViewPager() {
        //设置viewPager适配
        vp_welcome.setAdapter(new VpAdapterWelcome(this, imgList));
        //设置ViewPager监听
        vp_welcome.setOnPageChangeListener(new LazyViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ibtn_welcome.setVisibility(position == imgList.size() - 1 ? View.VISIBLE : View.GONE);
                initibtn();
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initibtn() {
        ibtn_welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.startActivity(WelcomeActivity.this, MainActivity.class);
            }
        });
    }

    private void initView() {
        ibtn_welcome = (ImageButton) findViewById(R.id.ibtn_welcome);
        vp_welcome = (LazyViewPager) findViewById(R.id.vp_welcome);
        imgList = new ArrayList<>();
        imgList.add(R.mipmap.wel_one);
        imgList.add(R.mipmap.wel_two);
        imgList.add(R.mipmap.wel_three);
        imgList.add(R.drawable.welcome);


    }
}
