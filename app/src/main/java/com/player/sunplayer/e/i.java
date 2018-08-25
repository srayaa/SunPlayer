package com.player.sunplayer.e;

import android.support.v4.app.NotificationCompat;
import java.security.MessageDigest;

/* compiled from: MD5 */
public class i {
    public static String a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("md5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer("");
            for (int i : digest) {
                int i2 = i;
                if (i2 < 0) {
                    i2 += NotificationCompat.FLAG_LOCAL_ONLY;
                }
                if (i2 < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i2));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
