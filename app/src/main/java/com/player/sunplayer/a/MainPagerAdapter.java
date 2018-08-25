package com.player.sunplayer.a;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.player.sunplayer.R;
import com.player.sunplayer.MyApplication;
import com.player.sunplayer.fragment.MainFragmentFactory;

/* compiled from: MainPagerAdapter */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private int[] a = new int[]{R.string.url, R.string.local};

    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
    }

    public MainPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }


    public Fragment getItem(int i) {
        return MainFragmentFactory.a(i);
    }

    public int getCount() {
        return this.a.length;
    }

    public CharSequence getPageTitle(int i) {
        return MyApplication.f().getText(this.a[i]);
    }
}
