package com.player.sunplayer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.player.sunplayer.R;
import com.player.sunplayer.a.DownloadListAdapter;
import com.player.sunplayer.bean.DownloadInfo;
import com.player.sunplayer.c.DownloadInfoDaoUtils;
import com.player.sunplayer.e.k;
import java.util.List;

/* compiled from: DownloadFragment */
public class DownloadFragment extends BaseFragment {
    private ListView b;
    private TextView c;
    private com.player.sunplayer.a.DownloadListAdapter d;

    public void c() {
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_download, viewGroup, false);
    }

    protected void b() {
        this.b = (ListView) a((int) R.id.list_video);
        this.c = (TextView) a((int) R.id.txt_local_remind);
    }

    public void onResume() {
        super.onResume();
        List d = DownloadInfoDaoUtils.d();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onResume");
        stringBuilder.append(d.size());
        k.a("onResume", stringBuilder.toString());
        if (d.size() == 0) {
            f();
            return;
        }
        e();
        if (this.d == null) {
            this.d = new DownloadListAdapter(this.a, d);
            this.b.setAdapter(this.d);
            return;
        }
        this.d.a(d);
    }

    private void e() {
        this.c.setVisibility(View.INVISIBLE);
        this.b.setVisibility(View.VISIBLE);
    }

    private void f() {
        this.c.setVisibility(View.VISIBLE);
        this.b.setVisibility(View.INVISIBLE);
    }

    public void onDestroy() {
        super.onDestroy();
        this.d = null;
    }

    public void d() {
        if (this.d != null) {
            this.d.notifyDataSetChanged();
        }
    }

    public void a(DownloadInfo downloadInfo) {
        if (this.d != null) {
            this.d.a(downloadInfo);
        }
    }
}
