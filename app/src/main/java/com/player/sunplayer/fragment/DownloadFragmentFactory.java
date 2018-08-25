package com.player.sunplayer.fragment;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

/* compiled from: DownloadFragmentFactory */
public class DownloadFragmentFactory {
    private static SparseArray<Fragment> a = new SparseArray();

    public static Fragment a(int i) {
        Fragment fragment = (Fragment) a.get(i);
        if (fragment == null) {
            switch (i) {
                case 0:
                    fragment = new DownloadFragment();
                    break;
                case 1:
                    fragment = new DownloadedFragment();
                    break;
            }
            a.put(i, fragment);
        }
        return fragment;
    }
}
