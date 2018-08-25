package com.player.sunplayer.e;

import android.text.TextUtils;

import com.player.sunplayer.Contants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import java.io.IOException;

/* compiled from: PayUtils */
public class m {
    public static boolean a(String str) {
        try {
            PostFormBuilder post = OkHttpUtils.post();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://");
            stringBuilder.append(Contants.h);
            stringBuilder.append(".net/vip.php");
            String string = ((PostFormBuilder) post.url(stringBuilder.toString())).addParams("token", str).build().execute().body().string();
            if (TextUtils.isEmpty(string)) {
                return false;
            }
            return string.equals("have");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
