package com.player.sunplayer.e;

import android.util.Log;

/* compiled from: MyLog */
public class k {
    private static boolean a;

    public static void a(String str, String str2) {
        if (a) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(str2);
            Log.e("MyLog", stringBuilder.toString());
        }
    }

    public static void a(String str) {
        if (a) {
            Log.e("MyLog", str);
        }
    }
}
