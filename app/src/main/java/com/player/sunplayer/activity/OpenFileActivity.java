package com.player.sunplayer.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;

import com.player.sunplayer.R;
import com.player.sunplayer.a.FileAdapter;
import com.player.sunplayer.bean.History;
import com.player.sunplayer.bean.PlayHistory;
import com.player.sunplayer.bean.VideoInfo;
import com.player.sunplayer.e.TorrentUtils;
import com.player.sunplayer.e.f;
import java.io.File;
import java.util.List;

public class OpenFileActivity extends BaseActivity {
    FileAdapter o;
    private RecyclerView p;
    private File q;
    private View r;

    public Context n() {
        return this;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_open_bt);
        c(R.string.open_file);
        o();
    }

    private void o() {
        this.p = (RecyclerView) findViewById(R.id.itemlist);
        this.r = findViewById(R.id.tv_file_goback);
        this.r.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                OpenFileActivity.this.m();
            }
        });
        this.q = Environment.getExternalStorageDirectory().getParentFile();
        if (this.q == null || this.q.listFiles() == null) {
            this.q = Environment.getExternalStorageDirectory();
        }
        this.p.setLayoutManager(new LinearLayoutManager(n()));
        this.o = new FileAdapter(n(), this.q);
        this.p.setAdapter(this.o);
        this.o.a(new com.player.sunplayer.a.FileAdapter.b() {
            public void a(View view, int i) {
                List a = f.a(OpenFileActivity.this.q.listFiles());
                f.a(a);
                File file = (File) a.get(i);
                if (file.isDirectory()) {
                    OpenFileActivity.this.q = file;
                    OpenFileActivity.this.o.a(OpenFileActivity.this.q);
                    OpenFileActivity.this.o.notifyDataSetChanged();
                    OpenFileActivity.this.p();
                } else if (f.a(file)) {
                    new PlayHistory(OpenFileActivity.this.n().getApplicationContext()).save(new History(file.getPath(), file.getName()));
                    Parcelable videoInfo = new VideoInfo(file.getName(), file.getPath());
                    Intent intent = new Intent(OpenFileActivity.this, VideoPlayerActivity.class);
                    intent.putExtra("videoInfo", videoInfo);
                    OpenFileActivity.this.startActivity(intent);
                    new PlayHistory(OpenFileActivity.this.n()).save(new History(file.getPath(), file.getName()));
                } else if (f.e(file)) {
                    OpenFileActivity.this.a(file, "audio/*");
                } else if (f.f(file)) {
                    new PlayHistory(OpenFileActivity.this.n().getApplicationContext()).save(new History(file.getPath(), file.getName()));
                    TorrentUtils.a(OpenFileActivity.this.n(), file.getPath());
                } else if (f.c(file)) {
                    OpenFileActivity.this.a(file, "image/*");
                } else if (f.d(file)) {
                    OpenFileActivity.this.a(file, "text/plain");
                } else {
                    Snackbar.make(view, (int) R.string.not_opened, -1).show();
                }
            }
        });
    }

    private void p() {
        if (q()) {
            this.r.setVisibility(View.VISIBLE);
        } else {
            this.r.setVisibility(View.GONE);
        }
    }

    private boolean q() {
        File parentFile = this.q.getParentFile();
        return (parentFile == null || parentFile.listFiles() == null) ? false : this.q.getPath().equals(Environment.getExternalStorageDirectory().getParent()) ^ true;
    }

    private void a(File file, String str) {
        Uri a;
        Intent intent = new Intent("android.intent.action.VIEW");
        if (VERSION.SDK_INT >= 24) {
            Context applicationContext = getApplicationContext();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getPackageName());
            stringBuilder.append(".fileprovider");
            a = FileProvider.getUriForFile(applicationContext, stringBuilder.toString(), file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            a = Uri.fromFile(file);
        }
        intent.setDataAndType(a, str);
        n().startActivity(intent);
    }

    public void m() {
        if (!this.q.getPath().equals(Environment.getExternalStorageDirectory().getParent())) {
            this.q = this.q.getParentFile();
            this.o.a(this.q);
            this.o.notifyDataSetChanged();
            p();
        }
    }

    public void onBackPressed() {
        if (q()) {
            m();
        } else {
            super.onBackPressed();
        }
    }
}
