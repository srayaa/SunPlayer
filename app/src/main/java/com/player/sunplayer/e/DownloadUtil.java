package com.player.sunplayer.e;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;
import com.baidu.cloud.media.download.DownloadObserver;
import com.baidu.cloud.media.download.DownloadableVideoItem;
import com.baidu.cloud.media.download.VideoDownloadManager;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloader;
import com.player.sunplayer.R;
import com.player.sunplayer.MyApplication;
import com.player.sunplayer.b.MagnetHandler;
import com.player.sunplayer.bean.DownloadInfo;
import com.player.sunplayer.c.DownloadInfoDaoUtils;
import com.player.sunplayer.service.DownloadService;
import com.xunlei.downloadlib.XLTaskHelper;
import com.xunlei.downloadlib.parameter.XLTaskInfo;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: DownloadUtil */
public class DownloadUtil {
    public static final String a = f.a();
    public static final String b;
    public static final String c;
    private static Handler d = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 0) {
                long longValue = ((Long) message.obj).longValue();
                XLTaskInfo taskInfo = DownloadUtil.d().getTaskInfo(longValue);
                if (taskInfo.mTaskStatus == 2) {
                    Context applicationContext = MyApplication.f().getApplicationContext();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(f.a());
                    stringBuilder.append("/");
                    stringBuilder.append(taskInfo.mFileName);
                    TorrentUtils.a(applicationContext, stringBuilder.toString());
                    return;
                }
                DownloadUtil.d.sendMessageDelayed(DownloadUtil.d.obtainMessage(0, Long.valueOf(longValue)), 1000);
            }
        }
    };

    /* compiled from: DownloadUtil */
    private static class a {
        private HashSet<Integer> a;
        private HashSet<DownloadInfo> b;

        private a() {
        }
        public HashSet<Integer> a() {
            return this.a;
        }

        public void a(HashSet<Integer> hashSet) {
            this.a = hashSet;
        }

        public HashSet<DownloadInfo> b() {
            return this.b;
        }

        public void b(HashSet<DownloadInfo> hashSet) {
            this.b = hashSet;
        }
    }

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(f.a());
        stringBuilder.append("/bt");
        b = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(f.a());
        stringBuilder.append("/tem");
        c = stringBuilder.toString();
    }

    public static void a(DownloadInfo downloadInfo) {
        MyApplication.e().remove(downloadInfo);
        DownloadInfoDaoUtils.a(downloadInfo.get_id().longValue(), -2, downloadInfo.getSoFarBytes(), downloadInfo.getTotalBytes());
        if (downloadInfo.getType() == 2) {
            VideoDownloadManager.getInstance(MyApplication.d(), "me").pauseDownloader(downloadInfo.getUrl());
        } else if (downloadInfo.getType() == 1) {
            d().stopTask(downloadInfo.getId().longValue());
            a f = f(downloadInfo);
            HashSet a = f.a();
            if (a.size() != 0) {
                long addBt = d().addBt(downloadInfo.getUrl(), downloadInfo.getBtDirectory(), true, a);
                if (a.size() != 0) {
                    Iterator it = f.b().iterator();
                    while (it.hasNext()) {
                        ((DownloadInfo) it.next()).setId(Long.valueOf(addBt));
                    }
                }
            }
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("pause_id:");
            stringBuilder.append(downloadInfo.getId());
            k.a("DownloadUtil", stringBuilder.toString());
            FileDownloader.getImpl().pause(downloadInfo.getId().intValue());
        }
    }

    public static void b(DownloadInfo downloadInfo) {
        if (downloadInfo.getType() == 1) {
            XLTaskHelper.instance(MyApplication.d()).stopTask(downloadInfo.getId().longValue());
            a f = f(downloadInfo);
            HashSet a = f.a();
            if (a.size() != 0) {
                long addBt = d().addBt(downloadInfo.getUrl(), downloadInfo.getBtDirectory(), true, a);
                if (a.size() != 0) {
                    Iterator it = f.b().iterator();
                    while (it.hasNext()) {
                        ((DownloadInfo) it.next()).setId(Long.valueOf(addBt));
                    }
                }
            }
        }
    }

    private static a f(DownloadInfo downloadInfo) {
        a aVar = new a();
        HashSet hashSet = new HashSet();
        HashSet e = MyApplication.e();
        HashSet hashSet2 = new HashSet();
        Boolean valueOf = Boolean.valueOf(false);
        Iterator it = e.iterator();
        while (it.hasNext()) {
            DownloadInfo downloadInfo2 = (DownloadInfo) it.next();
            if (downloadInfo2.getUrl().equals(downloadInfo.getUrl())) {
                hashSet.add(Integer.valueOf(downloadInfo2.getIndex()));
                hashSet2.add(downloadInfo2);
                if (!valueOf.booleanValue()) {
                    valueOf = Boolean.valueOf(true);
                    d().stopTask(downloadInfo2.getId().longValue());
                }
            }
        }
        aVar.a(hashSet);
        aVar.b(hashSet2);
        return aVar;
    }

    public static void c(DownloadInfo downloadInfo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("info");
        stringBuilder.append(downloadInfo.getUrl());
        k.a("DownloadUtil", stringBuilder.toString());
        switch (downloadInfo.getType()) {
            case 0:
                h(downloadInfo);
                break;
            case 1:
                g(downloadInfo);
                break;
            case 2:
                i(downloadInfo);
                break;
        }
        c();
        Toast.makeText(MyApplication.d(), R.string.download_start, Toast.LENGTH_SHORT).show();
    }

    public static void a(String str, Activity activity) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(TorrentUtils.a(str).toUpperCase());
            stringBuilder.append(".torrent");
            String stringBuilder2 = stringBuilder.toString();
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(b);
            stringBuilder3.append("/");
            stringBuilder3.append(stringBuilder2);
            String stringBuilder4 = stringBuilder3.toString();
            if (new File(stringBuilder4).exists()) {
                TorrentUtils.a((Context) activity, stringBuilder4);
                return;
            }
            File file = new File(b);
            if (!file.exists()) {
                file.mkdirs();
            }
            StringBuilder stringBuilder5 = new StringBuilder();
            stringBuilder5.append(b);
            stringBuilder5.append(str);
            stringBuilder5.append(stringBuilder2);
            k.a(stringBuilder5.toString());
            new MagnetHandler(activity, Looper.getMainLooper(), stringBuilder4).sendMessageDelayed(d.obtainMessage(0, Long.valueOf(XLTaskHelper.instance(MyApplication.d()).addMagentTask(str, b, stringBuilder2))), 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void g(DownloadInfo downloadInfo) {
        if (!MyApplication.e().contains(downloadInfo)) {
            String url = downloadInfo.getUrl();
            String name = downloadInfo.getName();
            int index = downloadInfo.getIndex();
            if (!TextUtils.isEmpty(url) && !TextUtils.isEmpty(name)) {
                HashSet a;
                boolean a2 = DownloadInfoDaoUtils.a(url);
                HashSet hashSet = new HashSet();
                HashSet hashSet2 = new HashSet();
                HashSet hashSet3 = new HashSet();
                if (a2) {
                    a f = f(downloadInfo);
                    a = f.a();
                    if (a.size() != 0) {
                        hashSet.addAll(a);
                        hashSet3 = f.b();
                    }
                } else {
                    a = hashSet2;
                }
                hashSet.add(Integer.valueOf(index));
                File file = new File(downloadInfo.getBtDirectory());
                if (!file.exists()) {
                    file.mkdirs();
                }
                long addBt = d().addBt(url, downloadInfo.getBtDirectory(), a2, hashSet);
                if (a.size() != 0) {
                    Iterator it = hashSet3.iterator();
                    while (it.hasNext()) {
                        ((DownloadInfo) it.next()).setId(Long.valueOf(addBt));
                    }
                }
                DownloadInfo downloadInfo2 = new DownloadInfo(1, Long.valueOf(addBt), url, index, downloadInfo.getBtDirectory(), downloadInfo.getPath(), name);
                downloadInfo2.set_id(DownloadInfoDaoUtils.b(downloadInfo2));
                MyApplication.a(downloadInfo2);
            }
        }
    }

    public static DownloadInfo d(DownloadInfo downloadInfo) {
        String url = downloadInfo.getUrl();
        CharSequence name = downloadInfo.getName();
        int index = downloadInfo.getIndex();
        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(name)) {
            return null;
        }
        File file = new File(c);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (MyApplication.e().contains(downloadInfo)) {
            return null;
        }
        boolean a = DownloadInfoDaoUtils.a(url, index);
        HashSet hashSet = new HashSet();
        hashSet.add(Integer.valueOf(index));
        return new DownloadInfo(1, Long.valueOf(d().addBt(url, c, a, hashSet)), url, index, downloadInfo.getPath());
    }

    private static void h(DownloadInfo downloadInfo) {
        String url = downloadInfo.getUrl();
        String name = downloadInfo.getName();
        String path = downloadInfo.getPath();
        String cookie = downloadInfo.getCookie();
        if (!TextUtils.isEmpty(url)) {
            String stringBuilder;
            String str = name == null ? url : name;
            if (TextUtils.isEmpty(path)) {
                String obj = a;
                if (!TextUtils.isEmpty(obj)) {
                    name = i.a(url);
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(obj);
                    stringBuilder2.append("/");
                    stringBuilder2.append(str.replace("/", ""));
                    stringBuilder2.append(name);
                    stringBuilder = stringBuilder2.toString();
                } else {
                    return;
                }
            }
            stringBuilder = path;
            FileDownloader.setup(MyApplication.f().getApplicationContext());
            BaseDownloadTask a = FileDownloader.getImpl().create(url);
            a.setPath(stringBuilder).setAutoRetryTimes(1000);
            if (!TextUtils.isEmpty(cookie)) {
                a.addHeader("Cookie", cookie);
            }
            a.ready();
            DownloadInfo downloadInfo2 = new DownloadInfo(0, Long.valueOf((long) a.start()), url, stringBuilder, str, cookie);
            downloadInfo2.set_id(Long.valueOf(DownloadInfoDaoUtils.a(downloadInfo2)));
            MyApplication.a(downloadInfo2);
        }
    }

    private static void c() {
        MyApplication.d().startService(new Intent(MyApplication.d(), DownloadService.class));
    }

    private static void i(DownloadInfo downloadInfo) {
        String url = downloadInfo.getUrl();
        if (!TextUtils.isEmpty(url)) {
            if (TextUtils.isEmpty(downloadInfo.getName())) {
                downloadInfo.setName(url);
            }
            VideoDownloadManager instance = VideoDownloadManager.getInstance(MyApplication.d(), "me");
            instance.startOrResumeDownloader(downloadInfo.getUrl(), new DownloadObserver() {
                public void update(DownloadableVideoItem downloadableVideoItem) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("FailReason:");
                    stringBuilder.append(downloadableVideoItem.getFailReason());
                    k.a("DownloadUtil", stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("progress:");
                    stringBuilder.append(downloadableVideoItem.getProgress());
                    k.a("DownloadUtil", stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("status:");
                    stringBuilder.append(downloadableVideoItem.getStatus());
                    k.a("DownloadUtil", stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("ErrorCode:");
                    stringBuilder.append(downloadableVideoItem.getErrorCode());
                    k.a("DownloadUtil", stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Path:");
                    stringBuilder.append(downloadableVideoItem.getLocalAbsolutePath());
                    k.a("DownloadUtil", stringBuilder.toString());
                }
            });
            downloadInfo.setPath(instance.getDownloadableVideoItemByUrl(downloadInfo.getUrl()).getLocalAbsolutePath());
            downloadInfo.setState(-2);
            downloadInfo.set_id(Long.valueOf(DownloadInfoDaoUtils.a(downloadInfo)));
            MyApplication.a(downloadInfo);
        }
    }

    private static XLTaskHelper d() {
        return XLTaskHelper.instance(MyApplication.d());
    }

    public static void e(DownloadInfo downloadInfo) {
        DownloadInfoDaoUtils.a(downloadInfo.get_id().longValue());
        MyApplication.e().remove(downloadInfo);
        switch (downloadInfo.getType()) {
            case 1:
                if (TextUtils.isEmpty(downloadInfo.getBtDirectory()) || !downloadInfo.getBtDirectory().contains("TorrentPlayer/") || DownloadInfoDaoUtils.a(downloadInfo.getUrl())) {
                    XLTaskHelper.instance(MyApplication.d()).deleteTaskFile(downloadInfo.getId().longValue(), downloadInfo.getPath());
                } else {
                    XLTaskHelper.instance(MyApplication.d()).deleteTaskDir(downloadInfo.getId().longValue(), downloadInfo.getBtDirectory());
                }
                a(downloadInfo);
                return;
            case 2:
                VideoDownloadManager.getInstance(MyApplication.d(), "me").deleteDownloader(downloadInfo.getUrl());
                return;
            default:
                FileDownloader.getImpl().clear(downloadInfo.getId().intValue(), downloadInfo.getPath());
                return;
        }
    }
}
