package com.zzq.paul_tools.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import java.util.List;

/**
 * Description:  通用ViewPager+Fragment适配器
 * Author:       张丹枫
 * CreateDate:   2019/3/18 14:08
 */
public class CommonViewPagerAdapter extends FragmentStatePagerAdapter {

    private FragmentManager mFragmentManager;
    //是否销毁Item（默认滑动超过指定加载页便销毁item）
    private boolean destroyItem = true;
    //Fragment数据集
    private List<Fragment> fragments;

    public CommonViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        this(fm, fragments, true);
    }

    public CommonViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, boolean destroyItem) {
        super(fm);
        this.destroyItem = destroyItem;
        this.fragments = fragments;
        this.mFragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (destroyItem) {
            super.destroyItem(container, position, object);
        }
    }

    /**
     * 移除所有fragment
     */
    public void removeAll() {
        for (Fragment fragment : fragments) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commitNowAllowingStateLoss();
        }
        fragments.clear();
    }
}
