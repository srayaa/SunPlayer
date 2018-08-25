package com.player.sunplayer.e;

import android.util.Base64;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/* compiled from: DES */
public class c {
    private byte[] a;

    public c() {
        this.a = "abcedfgh".getBytes();
    }

    public c(String str) {
        this.a = str.getBytes();
    }

    private static byte[] b(String str) {
        return str == null ? null : Base64.decode(str, 0);
    }

    public String a(String str) throws Exception {
        return new String(a(b(str)));
    }

    public byte[] a(byte[] bArr) throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        SecretKey generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(this.a));
        Cipher instance = Cipher.getInstance("DES");
        instance.init(2, generateSecret, secureRandom);
        return instance.doFinal(bArr);
    }
}
