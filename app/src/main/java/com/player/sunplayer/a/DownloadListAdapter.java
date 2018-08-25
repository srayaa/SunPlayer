package com.player.sunplayer.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.player.sunplayer.MyApplication;
import com.player.sunplayer.R;
import com.player.sunplayer.activity.VideoPlayerActivity;
import com.player.sunplayer.bean.DownloadInfo;
import com.player.sunplayer.bean.VideoInfo;
import com.player.sunplayer.c.DownloadInfoDaoUtils;
import com.player.sunplayer.e.DownloadUtil;
import com.player.sunplayer.e.TorrentUtils;
import com.player.sunplayer.e.f;
import com.player.sunplayer.e.k;
import com.player.sunplayer.e.o;
import com.xunlei.downloadlib.parameter.XLTaskInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: DownloadListAdapter */
public class DownloadListAdapter extends BaseAdapter {
    private Context a;
    private LayoutInflater b = LayoutInflater.from(this.a);
    private List<DownloadInfo> c;
    private ArrayList<a> list;

    /* compiled from: DownloadListAdapter */
    public class a {
        long a;
        TextView b;
        ImageView c;
        TextView d;
        RelativeLayout e;
        TextView f;
        NumberProgressBar g;
        ImageView h;

        public long a() {
            return this.a;
        }

        public void a(long j) {
            this.a = j;
        }

        public a(View view) {
            this.b = (TextView) view.findViewById(R.id.tv_file_name);
            this.c = (ImageView) view.findViewById(R.id.tv_file_play);
            this.d = (TextView) view.findViewById(R.id.tv_file_size);
            this.e = (RelativeLayout) view.findViewById(R.id.ll_download_main);
            this.f = (TextView) view.findViewById(R.id.tv_file_speed);
            this.g = (NumberProgressBar) view.findViewById(R.id.pb_file_progress);
            this.h = (ImageView) view.findViewById(R.id.iv_file_icon);
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public DownloadListAdapter(Context context, List<DownloadInfo> list) {
        this.a = context;
        this.c = list;
        this.list = new ArrayList();
    }

    public int getCount() {
        return this.c.size();
    }

    public Object getItem(int i) {
        return this.c.get(i);
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        final DownloadInfo downloadInfo = (DownloadInfo) this.c.get(i);
        if (view == null) {
            view = this.b.inflate(R.layout.list_download, null);
            aVar = new a(view);
            view.setTag(aVar);
            this.list.add(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a(downloadInfo.get_id().longValue());
        String name = downloadInfo.getName();
        aVar.b.setText(name);
        a(aVar, downloadInfo);
        aVar.e.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View view) {
                DownloadListAdapter.this.a(downloadInfo, i);
                return false;
            }
        });
        aVar.e.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DownloadListAdapter.this.c(downloadInfo);
            }
        });
        aVar.c.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DownloadListAdapter.this.b(downloadInfo);
            }
        });
        if (f.b(name) || downloadInfo.getType() == 2) {
            aVar.h.setImageResource(R.mipmap.icon_file_video);
        } else if (f.c(name)) {
            aVar.h.setImageResource(R.mipmap.icon_file_audio);
        } else if (f.d(name)) {
            aVar.h.setImageResource(R.mipmap.icon_file_text);
        } else if (f.e(name)) {
            aVar.h.setImageResource(R.mipmap.icon_file_picture);
        } else {
            aVar.h.setImageResource(R.mipmap.icon_file_other);
        }
        return view;
    }

    private void b(DownloadInfo downloadInfo) {
        VideoInfo videoInfo;
        if (downloadInfo.getState() == 2) {
            videoInfo = new VideoInfo(downloadInfo.getName(), downloadInfo.getPath());
            Intent intent = new Intent(this.a, VideoPlayerActivity.class);
            intent.putExtra("videoInfo", videoInfo);
            this.a.startActivity(intent);
            return;
        }
        switch (downloadInfo.getType()) {
            case 0:
            case 2:
                videoInfo = new VideoInfo(downloadInfo.getName(), downloadInfo.getUrl());
                videoInfo.setCookie(downloadInfo.getCookie());
                Intent intent2 = new Intent(this.a, VideoPlayerActivity.class);
                intent2.putExtra("videoInfo", videoInfo);
                this.a.startActivity(intent2);
                if (!MyApplication.e().contains(downloadInfo)) {
                    DownloadUtil.c(downloadInfo);
                    break;
                }
                break;
            case 1:
                TorrentUtils.a(this.a, downloadInfo);
                break;
        }
    }

    public void a(DownloadInfo downloadInfo) {
        DownloadInfo downloadInfo2 = downloadInfo;
        if (downloadInfo2 != null) {
            a a = a(downloadInfo.get_id().longValue());
            if (a != null) {
                XLTaskInfo a2 = o.a(downloadInfo);
                long j = a2.mDownloadSize;
                long j2 = a2.mFileSize;
                long j3 = a2.mDownloadSpeed;
                int i = a2.mTaskStatus;
                if (j == 0) {
                    j = downloadInfo.getSoFarBytes();
                }
                if (j2 == 0) {
                    j2 = downloadInfo.getTotalBytes();
                }
                if (i == 2 || i == 3) {
                    DownloadInfoDaoUtils.a(downloadInfo.get_id().longValue(), i, j, j2);
                    MyApplication.e().remove(downloadInfo2);
                    DownloadUtil.b(downloadInfo);
                }
                if (i != -2) {
                    switch (i) {
                        case 0:
                            a.f.setText(R.string.started);
                            break;
                        case 1:
                            TextView textView = a.f;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(j3 / 1024);
                            stringBuilder.append("kb/s");
                            textView.setText(stringBuilder.toString());
                            if (j2 != 0) {
                                a.g.setProgress((int) ((100 * j) / j2));
                            }
                            textView = a.d;
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(f.b(j));
                            stringBuilder2.append("/");
                            stringBuilder2.append(f.b(j2));
                            textView.setText(stringBuilder2.toString());
                            break;
                        case 2:
                            a.g.setProgress(100);
                            a.d.setText(f.b(j2));
                            a.f.setText(R.string.finished);
                            break;
                        case 3:
                            a.f.setText(R.string.error_download);
                            break;
                    }
                }
                a.f.setText(R.string.stopped);
            }
        }
    }

    public void a(a aVar, DownloadInfo downloadInfo) {
        a aVar2 = aVar;
        if (aVar2 != null) {
            long j;
            long j2;
            long j3;
            int i;
            if (MyApplication.e().contains(downloadInfo)) {
                XLTaskInfo a = o.a(downloadInfo);
                j = a.mDownloadSize;
                j2 = a.mFileSize;
                j3 = a.mDownloadSpeed;
                i = a.mTaskStatus;
                if (j == 0) {
                    j = downloadInfo.getSoFarBytes();
                }
                if (j2 == 0) {
                    j2 = downloadInfo.getTotalBytes();
                }
                if (i == 2 || i == 3) {
                    DownloadInfoDaoUtils.a(downloadInfo.get_id().longValue(), i, j, j2);
                }
            } else {
                j = downloadInfo.getSoFarBytes();
                j2 = downloadInfo.getTotalBytes();
                i = downloadInfo.getState();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(NotificationCompat.CATEGORY_STATUS);
                stringBuilder.append(i);
                k.a("STATE", stringBuilder.toString());
                j3 = 0;
            }
            TextView textView = aVar2.d;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(f.b(j));
            stringBuilder2.append("/");
            stringBuilder2.append(f.b(j2));
            textView.setText(stringBuilder2.toString());
            if (j2 != 0) {
                aVar2.g.setProgress((int) ((j * 100) / j2));
            }
            if (i != -2) {
                switch (i) {
                    case 0:
                        aVar2.f.setText(R.string.started);
                        break;
                    case 1:
                        TextView textView2 = aVar2.f;
                        StringBuilder stringBuilder3 = new StringBuilder();
                        stringBuilder3.append(j3 / 1024);
                        stringBuilder3.append("kb/s");
                        textView2.setText(stringBuilder3.toString());
                        break;
                    case 2:
                        aVar2.f.setText("");
                        aVar2.f.setText(R.string.finished);
                        aVar2.g.setProgress(100);
                        aVar2.d.setText(f.b(j2));
                        break;
                    case 3:
                        aVar2.f.setText(R.string.error_download);
                        break;
                }
            }
            aVar2.f.setText(R.string.stopped);
        }
    }

    private a a(long j) {
        Iterator it = this.list.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (aVar.a() == j) {
                return aVar;
            }
        }
        return null;
    }

    public void a(final DownloadInfo downloadInfo, final int i) {
        com.player.sunplayer.e.d.a(this.a, R.string.warn_download_delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    DownloadUtil.e(downloadInfo);
                    MyApplication.e().remove(downloadInfo);
                    DownloadListAdapter.this.c.remove(i);
                    DownloadListAdapter.this.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void c(DownloadInfo downloadInfo) {
        int state = downloadInfo.getState();
        if (a(downloadInfo.get_id().longValue()) != null) {
            if (MyApplication.e().contains(downloadInfo)) {
                state = 1;
            }
            if (state != -2) {
                switch (state) {
                    case 0:
                    case 1:
                        DownloadUtil.a(downloadInfo);
                        a(a(downloadInfo.get_id().longValue()), downloadInfo);
                        break;
                    case 2:
                        String name = downloadInfo.getName();
                        String path = downloadInfo.getPath();
                        if (new File(path).exists()) {
                            if (!f.b(name) && downloadInfo.getType() != 2) {
                                if (!f.c(name)) {
                                    if (!f.e(name)) {
                                        if (!path.endsWith(".torrent")) {
                                            if (!f.d(name)) {
                                                Toast.makeText(this.a, R.string.cannot_open_file, Toast.LENGTH_SHORT).show();
                                                break;
                                            } else {
                                                a(path, "text/plain");
                                                break;
                                            }
                                        }
                                        TorrentUtils.a(this.a, path);
                                        break;
                                    }
                                    a(path, "image/*");
                                    break;
                                }
                                a(path, "audio/*");
                                break;
                            }
                            Parcelable videoInfo = new VideoInfo(name, path);
                            Intent intent = new Intent(this.a, VideoPlayerActivity.class);
                            intent.putExtra("videoInfo", videoInfo);
                            this.a.startActivity(intent);
                            break;
                        }
                        Toast.makeText(this.a, R.string.file_no_exist, Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        break;
                }
            }
            DownloadUtil.c(downloadInfo);
            a(a(downloadInfo.get_id().longValue()), downloadInfo);
        }
    }

    private void a(String str, String str2) {
        Intent intent = new Intent("android.intent.action.VIEW");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("file://");
        stringBuilder.append(str);
        intent.setDataAndType(Uri.parse(stringBuilder.toString()), str2);
        this.a.startActivity(intent);
    }

    public void a(List<DownloadInfo> list) {
        this.c.clear();
        this.c.addAll(list);
        notifyDataSetChanged();
    }
}
