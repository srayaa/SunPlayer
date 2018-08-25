package com.player.sunplayer.fragment;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

/* compiled from: MainFragmentFactory */
public class MainFragmentFactory {
    private static SparseArray<Fragment> a = new SparseArray();

    public static Fragment a(int i) {
        Fragment fragment = (Fragment) a.get(i);
        if (fragment == null) {
            switch (i) {
                case 0:
                    fragment = new WebFragment();
                    break;
                case 1:
                    fragment = new LocalFragment();
                    break;
                case 2:
                    fragment = new FileManagerFragment();
                    break;
            }
            a.put(i, fragment);
        }
        return fragment;
    }

    public static void a() {
        a.clear();
    }
}
