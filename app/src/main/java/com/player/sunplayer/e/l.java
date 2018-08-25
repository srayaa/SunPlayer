package com.player.sunplayer.e;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.player.sunplayer.R;
import com.player.sunplayer.MyApplication;
import com.player.sunplayer.activity.VideoPlayerActivity;
import com.player.sunplayer.bean.DownloadInfo;
import com.player.sunplayer.bean.MagnetInfo;
import com.player.sunplayer.bean.VideoInfo;
import com.player.sunplayer.e.j.a;
import java.util.List;

/* compiled from: ParseUtils */
public class l {
    public static void a(final Activity activity, List<MagnetInfo> list, final MagnetInfo magnetInfo, final String str, String str2) {
        if (!activity.isFinishing()) {
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                if (f.b(((MagnetInfo) list.get(i3)).getName())) {
                    if (((MagnetInfo) list.get(i3)).equals(magnetInfo)) {
                        k.a("ParseUtils", magnetInfo.getFileIndex());
                        i = i2;
                    }
                    i2++;
                }
            }
            final ProgressDialog progressDialog = new ProgressDialog(activity);
            progressDialog.setMessage(activity.getString(R.string.loading));
            progressDialog.setCancelable(false);
            progressDialog.show();
            if (MyApplication.c()) {
                j.a(str2, i, new a() {
                    public void a() {
                        TorrentUtils.a(activity, magnetInfo, str, progressDialog);
                    }

                    public void a(VideoInfo videoInfo) {
                        videoInfo.setName(magnetInfo.getName());
                        Intent intent = new Intent(activity, VideoPlayerActivity.class);
                        intent.putExtra("videoInfo", videoInfo);
                        activity.startActivity(intent);
                        try {
                            if (!activity.isFinishing() && progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                TorrentUtils.a(activity, magnetInfo, str, progressDialog);
            }
        }
    }

    public static void b(final Activity activity, List<MagnetInfo> list, final MagnetInfo magnetInfo, final String str, String str2) {
        if (!activity.isFinishing()) {
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                if (f.b(((MagnetInfo) list.get(i3)).getName())) {
                    if (((MagnetInfo) list.get(i3)).equals(magnetInfo)) {
                        i = i2;
                    }
                    i2++;
                }
            }
            final ProgressDialog progressDialog = new ProgressDialog(activity);
            progressDialog.setMessage(activity.getString(R.string.loading));
            progressDialog.setCancelable(false);
            progressDialog.show();
            if (MyApplication.c()) {
                j.a(str2, i, new a() {
                    public void a() {
                        TorrentUtils.a(magnetInfo, str);
                        try {
                            if (!activity.isFinishing() && progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    public void a(VideoInfo videoInfo) {
                        String trim = magnetInfo.getName().trim();
                        String url = videoInfo.getUrl();
                        if (url.contains(".m3u8")) {
                            DownloadInfo downloadInfo = new DownloadInfo(2, url, trim);
                            downloadInfo.setTotalBytes(magnetInfo.getFile_size());
                            downloadInfo.setType(2);
                            DownloadUtil.c(downloadInfo);
                        } else {
                            DownloadInfo downloadInfo2 = new DownloadInfo(0, videoInfo.getUrl());
                            downloadInfo2.setCookie(videoInfo.getCookie());
                            downloadInfo2.setName(trim);
                            DownloadUtil.c(downloadInfo2);
                        }
                        try {
                            if (!activity.isFinishing() && progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        try {
                            TorrentUtils.a(magnetInfo, str);
                            if (!activity.isFinishing() && progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 2000);
            }
        }
    }
}
