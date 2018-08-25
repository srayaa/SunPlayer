package com.player.sunplayer.a;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.player.sunplayer.MyApplication;
import com.player.sunplayer.R;
import com.player.sunplayer.fragment.MainFragmentFactory;

/* compiled from: DownloadPagerAdapter */
public class DownloadPagerAdapter extends FragmentPagerAdapter {
    private String[] a = new String[]{MyApplication.f().getString(R.string.downloading), MyApplication.f().getString(R.string.completed)};

    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
    }

    public DownloadPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public Fragment getItem(int i) {
        return MainFragmentFactory.a(i);
    }

    public int getCount() {
        return this.a.length;
    }

    public CharSequence getPageTitle(int i) {
        return this.a[i];
    }
}
