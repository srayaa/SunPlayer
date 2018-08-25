package com.player.sunplayer.e;

import android.text.TextUtils;
import java.util.regex.Pattern;

/* compiled from: URLUtils */
public class r {
    public static boolean a(String str) {
        return Pattern.compile("[一-龥]").matcher(str).find();
    }

    public static boolean b(String str) {
        if (a(str) || TextUtils.isEmpty(str) || str.split("\\.").length <= 1) {
            return false;
        }
        return true;
    }
}
