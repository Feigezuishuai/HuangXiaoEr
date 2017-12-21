package com.zz.huangxiaoer.factory;

import android.support.v4.app.Fragment;

import com.zz.huangxiaoer.fragment.BespeakFragment;
import com.zz.huangxiaoer.fragment.MineFragmeng;

import java.util.HashMap;

/**
 * Created by ${韩永光} on on 2017/11/17 0017 10:51..
 */

public class FragmentFactory {
    public static HashMap<Integer,Fragment> fragmentHashMap=new HashMap<>();

    public static Fragment getFragment(int position) {
        Fragment fragment = fragmentHashMap.get(position);
        if (fragment != null) {
            return fragment;
        }
        switch (position) {
            case 0:
                fragment = new BespeakFragment();
                break;
            case 1:
                fragment = new MineFragmeng();
                break;

        }
        fragmentHashMap.put(position,fragment);
        return fragment;
    }
}
