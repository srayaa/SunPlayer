package com.boredream.bdvideoplayer.b;

import java.util.Locale;

/* compiled from: StringUtils */
public class c {
    public static String a(int i) {
        i /= 1000;
        int i2 = i % 60;
        int i3 = (i / 60) % 60;
        if (i / 3600 > 0) {
            return String.format(Locale.getDefault(), "%d:%02d:%02d", new Object[]{Integer.valueOf(i / 3600), Integer.valueOf(i3), Integer.valueOf(i2)});
        }
        return String.format(Locale.getDefault(), "%02d:%02d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i2)});
    }
}
