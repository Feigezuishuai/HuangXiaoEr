package com.zz.huangxiaoer.myadapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by ${韩永光} on on 2017/11/15 0015 17:10..
 */

public class VpAdapterWelcome extends PagerAdapter {
    private final Context context;
    private final ArrayList<Integer> imgList;

    public VpAdapterWelcome(Context context, ArrayList<Integer> imgList) {
        super();
        this.context = context;
        this.imgList = imgList;

    }

    @Override
    public int getCount() {
        return imgList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        GifImageView gifImageView = new GifImageView(context);
        gifImageView.setImageResource(imgList.get(position));
        container.addView(gifImageView);

        return gifImageView ;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
