package com.player.sunplayer.jni;

public class MyKey {
    public static native String get1(Object obj);

    public static native String get10(Object obj);

    public static native String get4(Object obj);

    public static native String get5(Object obj);

    public static native String get6(Object obj);

    public static native String get7(Object obj);

    public static native String get8(Object obj);

    public static native String get9(Object obj);

    static {
        System.loadLibrary("bdUtils");
    }
}
