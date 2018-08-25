package com.player.sunplayer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.player.sunplayer.R;
import com.player.sunplayer.a.DownloadedListAdapter;
import com.player.sunplayer.c.DownloadInfoDaoUtils;

import java.util.List;

/* compiled from: DownloadedFragment */
public class DownloadedFragment extends BaseFragment {
    private ListView b;
    private TextView c;
    private DownloadedListAdapter d;

    public void c() {
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_downloaded, viewGroup, false);
    }

    protected void b() {
        this.b = (ListView) a((int) R.id.list_video);
        this.c = (TextView) a((int) R.id.txt_local_remind);
    }

    public void onResume() {
        super.onResume();
        List c = DownloadInfoDaoUtils.c();
        if (c.size() == 0) {
            e();
            return;
        }
        d();
        if (this.d == null) {
            this.d = new DownloadedListAdapter(this.a, c);
            this.b.setAdapter(this.d);
            return;
        }
        this.d.a(c);
    }

    private void d() {
        this.c.setVisibility(View.INVISIBLE);
        this.b.setVisibility(View.VISIBLE);
    }

    private void e() {
        this.c.setVisibility(View.VISIBLE);
        this.b.setVisibility(View.INVISIBLE);
    }

    public void onDestroy() {
        super.onDestroy();
        this.d = null;
    }
}
