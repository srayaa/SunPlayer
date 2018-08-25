package com.player.sunplayer.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
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
import com.player.sunplayer.MyApplication;
import com.player.sunplayer.R;
import com.player.sunplayer.activity.VideoPlayerActivity;
import com.player.sunplayer.bean.DownloadInfo;
import com.player.sunplayer.bean.VideoInfo;
import com.player.sunplayer.e.DownloadUtil;
import com.player.sunplayer.e.TorrentUtils;
import com.player.sunplayer.e.d;
import com.player.sunplayer.e.f;
import java.io.File;
import java.util.List;

/* compiled from: DownloadedListAdapter */
public class DownloadedListAdapter extends BaseAdapter {
    private Context a;
    private LayoutInflater b = LayoutInflater.from(this.a);
    private List<DownloadInfo> c;

    /* compiled from: DownloadedListAdapter */
    public class a {
        long a;
        TextView b;
        TextView c;
        RelativeLayout d;
        ImageView e;

        public void a(long j) {
            this.a = j;
        }

        public a(View view) {
            this.b = (TextView) view.findViewById(R.id.tv_file_name);
            this.c = (TextView) view.findViewById(R.id.tv_file_size);
            this.d = (RelativeLayout) view.findViewById(R.id.ll_download_main);
            this.e = (ImageView) view.findViewById(R.id.iv_file_icon);
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public DownloadedListAdapter(Context context, List<DownloadInfo> list) {
        this.a = context;
        this.c = list;
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
            view = this.b.inflate(R.layout.list_downloaded, null);
            aVar = new a(view);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a(downloadInfo.get_id().longValue());
        String name = downloadInfo.getName();
        aVar.b.setText(name);
        aVar.c.setText(f.b(downloadInfo.getTotalBytes()));
        aVar.d.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View view) {
                DownloadedListAdapter.this.a(downloadInfo, i);
                return false;
            }
        });
        aVar.d.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DownloadedListAdapter.this.a(downloadInfo);
            }
        });
        if (f.b(name) || downloadInfo.getType() == 2) {
            aVar.e.setImageResource(R.mipmap.icon_file_video);
        } else if (f.c(name)) {
            aVar.e.setImageResource(R.mipmap.icon_file_audio);
        } else if (f.d(name)) {
            aVar.e.setImageResource(R.mipmap.icon_file_text);
        } else if (f.e(name)) {
            aVar.e.setImageResource(R.mipmap.icon_file_picture);
        } else {
            aVar.e.setImageResource(R.mipmap.icon_file_other);
        }
        return view;
    }

    public void a(final DownloadInfo downloadInfo, final int i) {
        d.a(this.a, R.string.warn_download_delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    DownloadUtil.e(downloadInfo);
                    MyApplication.e().remove(downloadInfo);
                    DownloadedListAdapter.this.c.remove(i);
                    DownloadedListAdapter.this.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void a(DownloadInfo downloadInfo) {
        String name = downloadInfo.getName();
        String path = downloadInfo.getPath();
        if (!new File(path).exists()) {
            Toast.makeText(this.a, R.string.file_no_exist, Toast.LENGTH_SHORT).show();
        } else if (f.b(name) || downloadInfo.getType() == 2) {
            Parcelable videoInfo = new VideoInfo(name, path);
            Intent intent = new Intent(this.a, VideoPlayerActivity.class);
            intent.putExtra("videoInfo", videoInfo);
            this.a.startActivity(intent);
        } else if (f.c(name)) {
            a(path, "audio/*");
        } else if (f.e(name)) {
            a(path, "image/*");
        } else if (path.endsWith(".torrent")) {
            TorrentUtils.a(this.a, path);
        } else if (f.d(name)) {
            a(path, "text/plain");
        } else {
            Toast.makeText(this.a, R.string.cannot_open_file, Toast.LENGTH_SHORT).show();
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
