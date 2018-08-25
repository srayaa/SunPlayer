package com.player.sunplayer.e;

import android.util.Base64;
import com.player.sunplayer.bean.MagnetInfo;
import com.player.sunplayer.bean.VideoInfo;
import com.player.sunplayer.e.j.a;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;
import java.net.URLDecoder;
import java.security.Key;
import java.security.SecureRandom;
import java.util.ArrayList;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import okhttp3.Call;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: TianTian */
public class p {
    private static final String[] a = new String[]{"http://xfcd.ctfs.ftn.qq.com/", "http://xfxa.ctfs.ftn.qq.com/", "http://xa.ctfs.ftn.qq.com/", "http://sh.ctfs.ftn.qq.com/", "http://xfsh.ctfs.ftn.qq.com/", "http://hz.ftn.qq.com/", "http://tj.ctfs.ftn.qq.com/", "http://cd.ctfs.ftn.qq.com/", "http://sz.ctfs.ftn.qq.com/", "http://xfcd.ctfs.ftn.11.com/", "http://sh.btfs.ftn.qq.com/", "http://xg.ctfs.ftn.qq.com/", "http://xa.btfs.ftn.qq.com/", "http://szmail.tfs.ftn.qq.com/", "http://xflx.szbtfs.ftn.qq.com/", "http://xflx.hz.ftn.qq.com/", "http://xflx.store.cd.qq.com/", "http://xflx.sz.ftn.qq.com/", "http://xflx.xa.ftn.qq.com/", "http://xflx.shbtfs.ftn.qq.com/", "http://xflx.sh.ftn.qq.com/", "http://xflxsrc.store.qq.com/", "http://xflx.cdbtfs.ftn.qq.com/", "http://xflx.cd.ftn.qq.com/", "http://xflx.xabtfs.ftn.qq.com/", "http://xflx.tjctfs.ftn.qq.com/", "http://xflx.tjbtfs.ftn.qq.com/", "http://xflx.szmail.ftn.qq.com/", "http://xflx.xatfs.ftn.qq.com/", "http://xflx.store.sh.qq.com/"};

    public static void a(String str, int i, a aVar) {
        b(str, i, aVar);
    }

    public static void a(String str, String str2, final a aVar) {
        ((PostFormBuilder) ((PostFormBuilder) OkHttpUtils.post().url("http://upingmediaservice.cxtinfo.com/service")).addHeader("Content-Type", "application/json")).addParams("magnetHash", str.toUpperCase()).addParams("fileIndex", String.valueOf(str2)).addParams("channel", "yaoyuan").addParams("appid", String.valueOf(1)).addParams("method", "QueryQQXuanFengPlayInfo3").build().connTimeOut(5000).execute(new StringCallback() {
            public /* synthetic */ void onResponse(String obj, int i) {
                a((String) obj, i);
            }

            public void onError(Call call, Exception exception, int i) {
                aVar.a();
            }

            public void a(String str, int i) {
                k.a("TianTian", str);
                try {
                    String str2 = new String(p.b(Base64.decode(new JSONObject(str).getString("data").getBytes(), 0), com.player.sunplayer.Contants.a.getBytes()));
                    k.a("TianTian", str2);
                    JSONObject jSONObject = new JSONObject(str2.replace("\\u003d", "=").replace("\\u0026r", "&"));
                    str = jSONObject.getString("ftn");
                    String string = jSONObject.getString("cookie");
                    k.a("TianTian", str);
                    str = URLDecoder.decode(jSONObject.getString("url"), "UTF-8");
                    k.a("TianTian", str);
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

    public static void b(String str, final int i, final a aVar) {
        str = str.toLowerCase();
        ((PostFormBuilder) ((PostFormBuilder) OkHttpUtils.post().url("http://upingmediaservice.cxtinfo.com/service")).addHeader("Content-Type", "application/x-www-form-urlencoded")).addParams("magnetHash", str).addParams("method", "GetHashVideoPlayInfoList").addParams("channel", "yaoyuan").addParams("appid", String.valueOf(1)).build().connTimeOut(5000).execute(new StringCallback() {
            public /* synthetic */ void onResponse(String obj, int i) {
                a((String) obj, i);
            }

            public void onError(Call call, Exception exception, int i) {
                aVar.a();
            }

            public void a(String str, int i) {
                try {
                    k.a("TianTian", str);
                    JSONArray jSONArray = new JSONObject(str).getJSONArray("results");
                    ArrayList arrayList = new ArrayList();
                    if (jSONArray.length() > 0) {
                        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                            MagnetInfo magnetInfo = new MagnetInfo();
                            JSONObject jSONObject = jSONArray.getJSONObject(i2);
                            int i3 = jSONObject.getInt("fileIndex");
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(i3);
                            stringBuilder.append("");
                            magnetInfo.setFileIndex(stringBuilder.toString());
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(i3);
                            stringBuilder.append(".mp4");
                            magnetInfo.setName(stringBuilder.toString());
                            magnetInfo.setFile_size(jSONObject.getLong("fileSize"));
                            arrayList.add(magnetInfo);
                        }
                        int size = arrayList.size();
                        if (size == 1) {
                            p.a(str, ((MagnetInfo) arrayList.get(0)).getFileIndex(), aVar);
                            return;
                        } else if (i < size) {
                            p.a(str, ((MagnetInfo) arrayList.get(i)).getFileIndex(), aVar);
                            return;
                        } else {
                            aVar.a();
                            return;
                        }
                    }
                    aVar.a();
                } catch (Exception unused) {
                    aVar.a();
                }
            }
        });
    }

    private static byte[] b(byte[] bArr, byte[] bArr2) {
        SecureRandom secureRandom = new SecureRandom();
        try {
            Key generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(bArr2));
            Cipher instance = Cipher.getInstance("DES");
            instance.init(2, generateSecret, secureRandom);
            return instance.doFinal(bArr);
        } catch (Exception unused) {
            return bArr;
        }
    }
}
