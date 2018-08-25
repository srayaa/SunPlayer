package com.player.sunplayer.e;

import java.text.SimpleDateFormat;
import java.util.Date;

/* compiled from: StringUtils */
public class n {
    public static String a(long j) {
        return new SimpleDateFormat("HH:mm:ss ").format(new Date(j - 288000000));
    }

    public static boolean a(String str) {
        return str == null || str.length() == 0;
    }
}
