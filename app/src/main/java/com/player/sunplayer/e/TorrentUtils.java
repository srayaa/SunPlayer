package com.player.sunplayer.e;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;
import com.player.sunplayer.R;
import com.player.sunplayer.MyApplication;
import com.player.sunplayer.activity.MagnetDetailsActivity;
import com.player.sunplayer.activity.VideoPlayerActivity;
import com.player.sunplayer.bean.DownloadInfo;
import com.player.sunplayer.bean.MagnetInfo;
import com.player.sunplayer.bean.MagnetInfoList;
import com.player.sunplayer.bean.VideoInfo;
import com.xunlei.downloadlib.XLTaskHelper;
import com.xunlei.downloadlib.parameter.TorrentFileInfo;
import com.xunlei.downloadlib.parameter.TorrentInfo;
import java.io.Serializable;
import java.util.ArrayList;

/* compiled from: TorrentUtils */
public class TorrentUtils {
    public static void a(Context context, String str) {
        TorrentInfo torrentInfo = b().getTorrentInfo(str);
        ArrayList arrayList = new ArrayList();
        String obj = torrentInfo.mMultiFileBaseFolder;
        if (torrentInfo.mSubFileInfo == null || torrentInfo.mSubFileInfo.length == 0) {
            Toast.makeText(context, R.string.parse_failed, Toast.LENGTH_SHORT).show();
            return;
        }
        for (TorrentFileInfo torrentFileInfo : torrentInfo.mSubFileInfo) {
            if (f.b(torrentFileInfo.mFileName)) {
                MagnetInfo magnetInfo = new MagnetInfo();
                magnetInfo.setFile_size(torrentFileInfo.mFileSize);
                magnetInfo.setFileIndex(String.valueOf(torrentFileInfo.mFileIndex));
                if (TextUtils.isEmpty(obj)) {
                    magnetInfo.setBaseFolder(torrentInfo.mInfoHash);
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(obj);
                    stringBuilder.append(torrentInfo.mInfoHash.hashCode());
                    magnetInfo.setBaseFolder(stringBuilder.toString());
                }
                magnetInfo.setSubfile(torrentFileInfo.mSubPath);
                magnetInfo.setName(torrentFileInfo.mFileName);
                arrayList.add(magnetInfo);
            }
        }
        if (arrayList.size() == 0) {
            Toast.makeText(context, R.string.no_player_rec, Toast.LENGTH_SHORT).show();
            return;
        }
        MagnetInfoList magnetInfoList = new MagnetInfoList();
        magnetInfoList.setTorrentPath(str);
        magnetInfoList.setName(torrentInfo.mMultiFileBaseFolder);
        magnetInfoList.setInfolist(arrayList);
        magnetInfoList.setHash(torrentInfo.mInfoHash);
        Intent intent = new Intent(context, MagnetDetailsActivity.class);
        intent.putExtra("magnets", magnetInfoList);
        context.startActivity(intent);
    }

    private static XLTaskHelper b() {
        return XLTaskHelper.instance(MyApplication.d());
    }

    public static String a(String str) {
        String[] split;
        try {
            split = str.split(":")[3].split("&");
        } catch (Exception e) {
            e.printStackTrace();
            split = new String[]{str};
        }
        return split[0];
    }

    public static void a(Activity activity, MagnetInfo magnetInfo, String str, ProgressDialog progressDialog) {
        int parseInt;
        final DownloadInfo downloadInfo = new DownloadInfo(1, str);
        downloadInfo.setName(magnetInfo.getName());
        try {
            parseInt = Integer.parseInt(magnetInfo.getFileIndex());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            parseInt = 0;
        }
        downloadInfo.setIndex(parseInt);
        if (MyApplication.e().contains(downloadInfo)) {
            downloadInfo.setPath(c(magnetInfo));
        } else {
            downloadInfo.setPath(b(magnetInfo));
        }
        final DownloadInfo d = DownloadUtil.d(downloadInfo);
        final Activity activity2 = activity;
        final MagnetInfo magnetInfo2 = magnetInfo;
        final ProgressDialog progressDialog2 = progressDialog;
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                String path;
                if (d == null) {
                    path = downloadInfo.getPath();
                } else {
                    path = d.getPath();
                }
                k.a("TorrentUtils", path);
                path = TorrentUtils.b().getLoclUrl(path);
                Intent intent = new Intent(activity2, VideoPlayerActivity.class);
                intent.putExtra("videoInfo", new VideoInfo(magnetInfo2.getName(), path));
                intent.putExtra("downloadInfo", d);
                activity2.startActivity(intent);
                try {
                    if (!activity2.isFinishing() && progressDialog2.isShowing()) {
                        progressDialog2.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 4000);
    }

    public static void a(MagnetInfo magnetInfo, String str) {
        int parseInt;
        DownloadInfo downloadInfo = new DownloadInfo(1, str);
        downloadInfo.setName(magnetInfo.getName());
        try {
            parseInt = Integer.parseInt(magnetInfo.getFileIndex());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            parseInt = 0;
        }
        downloadInfo.setIndex(parseInt);
        downloadInfo.setPath(c(magnetInfo));
        downloadInfo.setBtDirectory(a(magnetInfo));
        DownloadUtil.c(downloadInfo);
    }

    private static String a(MagnetInfo magnetInfo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DownloadUtil.a);
        stringBuilder.append("/");
        stringBuilder.append(magnetInfo.getBaseFolder());
        return stringBuilder.toString();
    }

    public static void a(Context context, DownloadInfo downloadInfo) {
        if (!MyApplication.e().contains(downloadInfo)) {
            DownloadUtil.c(downloadInfo);
        }
        String loclUrl = b().getLoclUrl(downloadInfo.getPath());
        Intent intent = new Intent(context, VideoPlayerActivity.class);
        intent.putExtra("videoInfo", new VideoInfo(downloadInfo.getName(), loclUrl));
        context.startActivity(intent);
    }

    private static String b(MagnetInfo magnetInfo) {
        StringBuilder stringBuilder;
        if (TextUtils.isEmpty(magnetInfo.getSubfile())) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(DownloadUtil.c);
            stringBuilder.append("/");
            stringBuilder.append(magnetInfo.getName());
            return stringBuilder.toString();
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(DownloadUtil.c);
        stringBuilder.append("/");
        stringBuilder.append(magnetInfo.getSubfile());
        stringBuilder.append("/");
        stringBuilder.append(magnetInfo.getName());
        return stringBuilder.toString();
    }

    private static String c(MagnetInfo magnetInfo) {
        StringBuilder stringBuilder;
        if (TextUtils.isEmpty(magnetInfo.getSubfile())) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(DownloadUtil.a);
            stringBuilder.append("/");
            stringBuilder.append(magnetInfo.getBaseFolder());
            stringBuilder.append("/");
            stringBuilder.append(magnetInfo.getName());
            return stringBuilder.toString();
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(DownloadUtil.a);
        stringBuilder.append("/");
        stringBuilder.append(magnetInfo.getBaseFolder());
        stringBuilder.append("/");
        stringBuilder.append(magnetInfo.getSubfile());
        stringBuilder.append("/");
        stringBuilder.append(magnetInfo.getName());
        return stringBuilder.toString();
    }
}
