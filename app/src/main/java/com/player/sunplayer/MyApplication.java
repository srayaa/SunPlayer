package com.player.sunplayer;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.player.sunplayer.bean.DownloadInfo;
import com.player.sunplayer.e.k;
import java.util.HashSet;

public class MyApplication extends Application {
    public static boolean a;
    private static MyApplication b;
    private static HashSet<DownloadInfo> c = new HashSet();
    private static long d;
    private static long e;

    public static long a() {
        return d;
    }

    public static long b() {
        return e;
    }

    public static boolean c() {
        boolean z = false;
        return z;
    }

    public static void a(long j) {
        d = j;
    }

    public static void b(long j) {
        e = j;
    }

    public static Context d() {
        return b.getApplicationContext();
    }

    public void onCreate() {
        super.onCreate();
        b = this;
        a = false;
    }

    public static void a(DownloadInfo downloadInfo) {
        c.add(downloadInfo);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size:");
        stringBuilder.append(c.size());
        k.a("MyApplication", stringBuilder.toString());
    }

    public static HashSet<DownloadInfo> e() {
        return c;
    }

    public static Context f() {
        return b.getApplicationContext();
    }

    public String getPackageName() {
        if (Log.getStackTraceString(new Throwable()).contains("com.xunlei.downloadlib")) {
            return "com.xunlei.downloadprovider";
        }
        return super.getPackageName();
    }

    public PackageManager getPackageManager() {
        if (Log.getStackTraceString(new Throwable()).contains("com.xunlei.downloadlib")) {
            return new DelegateApplicationPackageManager(super.getPackageManager());
        }
        return super.getPackageManager();
    }
}
