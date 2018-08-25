package com.boredream.bdvideoplayer.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* compiled from: NetworkUtils */
public class b {
    public static boolean a(Context context) {
        if (context != null) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static boolean b(Context context) {
        if (context != null) {
            NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1);
            if (networkInfo != null) {
                return networkInfo.isConnected();
            }
        }
        return false;
    }

    public static boolean c(Context context) {
        if (context != null) {
            NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(0);
            if (networkInfo != null) {
                return networkInfo.isConnected();
            }
        }
        return false;
    }
}
