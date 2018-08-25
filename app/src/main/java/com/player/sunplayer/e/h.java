package com.player.sunplayer.e;

import android.support.v4.app.NotificationCompat;

import com.player.sunplayer.Contants;
import com.player.sunplayer.bean.VideoInfo;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.callback.StringCallback;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import okhttp3.Call;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: JiSu */
public class h {
    static final Pattern a = Pattern.compile("\\\\u([0-9a-zA-Z]{4})");
    private static String b = Contants.f;
    private static String c = Contants.g;

    private static String b(String str, String str2) {
        try {
            return new c(str2).a(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String c(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(str2);
        return a(stringBuilder.toString()).toUpperCase().substring(0, 10);
    }

    public static String a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            byte[] digest = instance.digest();
            for (int i : digest) {
                int i2 = i;
                if (i2 < 0) {
                    i2 += NotificationCompat.FLAG_LOCAL_ONLY;
                }
                if (i2 < 16) {
                    stringBuilder.append("0");
                }
                stringBuilder.append(Integer.toHexString(i2));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void a(String str, int i, j.a aVar) {
        b(str, i, aVar);
    }

    public static void a(String str, String str2, final j.a aVar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.");
        stringBuilder.append(c);
        stringBuilder.append(".com/api3.0/oof/get_down_url1.php?hash=");
        stringBuilder.append(str);
        stringBuilder.append("&index=");
        stringBuilder.append(str2);
        stringBuilder.append("&k=");
        stringBuilder.append(c(str, b));
        str = stringBuilder.toString();
        k.a("JiSu", str);
        ((GetBuilder) ((GetBuilder) OkHttpUtils.get().url(str)).addHeader("user-agent", "Mozilla/5.0 (compatible; MSIE 5.01; Windows NT 5.0)")).build().connTimeOut(5000).execute(new StringCallback() {
            public /* synthetic */ void onResponse(String obj, int i) {
                a((String) obj, i);
            }

            public void onError(Call call, Exception exception, int i) {
                aVar.a();
                k.a("JiSu", "onError");
            }

            public void a(String str, int i) {
                try {
                    str = h.b(str, h.b);
                    k.a("JiSu", str);
                    JSONObject jSONObject = new JSONObject(str);
                    str = jSONObject.getString("download_url");
                    String string = jSONObject.getString("download_cookie");
                    k.a("JiSu", str);
                    VideoInfo videoInfo = new VideoInfo("name", str);
                    videoInfo.setCookie(string);
                    aVar.a(videoInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                    aVar.a();
                }
            }
        });
    }

    public static void b(String str, final int i, final j.a aVar) {
        str = str.toLowerCase();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.");
        stringBuilder.append(c);
        stringBuilder.append(".com/api3.0/oof/get_torrent_list.php?hash=");
        stringBuilder.append(str);
        stringBuilder.append("&k=");
        stringBuilder.append(c(str, b));
        ((GetBuilder) OkHttpUtils.get().url(stringBuilder.toString())).build().connTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS).execute(new StringCallback() {
            public /* synthetic */ void onResponse(String obj, int i) {
                a((String) obj, i);
            }

            public void onError(Call call, Exception exception, int i) {
                aVar.a();
            }

            public void a(String str, int i) {
                try {
                    str = h.b(str, h.b);
                    k.a("JiSu", str);
                    JSONObject jSONObject = new JSONObject(str);
                    int i2 = jSONObject.getInt("code");
                    List arrayList = new ArrayList();
                    if (i2 == 1) {
                        JSONArray jSONArray = jSONObject.getJSONObject("Result").getJSONArray("Record");
                        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                            arrayList.add(((JSONObject) jSONArray.get(i3)).getString("hash_index"));
                        }
                        i2 = arrayList.size();
                        if (i2 <= 0) {
                            aVar.a();
                            return;
                        } else if (i2 == 1) {
                            str = (String) arrayList.get(0);
                            h.a(str.substring(0, 40), str.substring(40), aVar);
                            return;
                        } else if (i < i2) {
                            str = (String) arrayList.get(i);
                            h.a(str.substring(0, 40), str.substring(40), aVar);
                            return;
                        } else {
                            aVar.a();
                            return;
                        }
                    }
                    aVar.a();
                } catch (Exception e) {
                    e.printStackTrace();
                    aVar.a();
                }
            }
        });
    }
}
