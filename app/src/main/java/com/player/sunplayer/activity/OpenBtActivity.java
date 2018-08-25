package com.player.sunplayer.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;

import com.player.sunplayer.R;
import com.player.sunplayer.a.FileBtAdapter;
import com.player.sunplayer.bean.History;
import com.player.sunplayer.bean.PlayHistory;
import com.player.sunplayer.e.TorrentUtils;
import com.player.sunplayer.e.f;
import java.io.File;
import java.util.List;

public class OpenBtActivity extends BaseActivity {
    FileBtAdapter o;
    private RecyclerView p;
    private File q;
    private View r;

    public Context n() {
        return this;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_open_bt);
        c(R.string.add_bt);
        o();
    }

    private void o() {
        this.p = (RecyclerView) findViewById(R.id.itemlist);
        this.r = findViewById(R.id.tv_file_goback);
        this.r.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                OpenBtActivity.this.m();
            }
        });
        this.q = Environment.getExternalStorageDirectory().getParentFile();
        if (this.q == null || this.q.listFiles() == null) {
            this.q = Environment.getExternalStorageDirectory();
        }
        this.p.setLayoutManager(new LinearLayoutManager(n()));
        this.o = new FileBtAdapter(n(), this.q);
        this.p.setAdapter(this.o);
        this.o.a(new FileBtAdapter.b() {
            public void a(View view, int i) {
                List b = f.b(OpenBtActivity.this.q.listFiles());
                f.a(b);
                File file = (File) b.get(i);
                if (file.isDirectory()) {
                    OpenBtActivity.this.q = file;
                    OpenBtActivity.this.o.a(OpenBtActivity.this.q);
                    OpenBtActivity.this.o.notifyDataSetChanged();
                    OpenBtActivity.this.p();
                } else if (f.a(file)) {
                    new PlayHistory(OpenBtActivity.this.n().getApplicationContext()).save(new History(file.getPath(), file.getName()));
                    Intent intent = new Intent(OpenBtActivity.this.n(), MainActivity.class);
                    intent.putExtra("url", file.getPath());
                    intent.putExtra("title", file.getName());
                    OpenBtActivity.this.n().startActivity(intent);
                    new PlayHistory(OpenBtActivity.this.n()).save(new History(file.getPath(), file.getName()));
                } else if (f.e(file)) {
                    OpenBtActivity.this.a(file, "audio/*");
                } else if (f.f(file)) {
                    new PlayHistory(OpenBtActivity.this.n().getApplicationContext()).save(new History(file.getPath(), file.getName()));
                    TorrentUtils.a(OpenBtActivity.this.n(), file.getPath());
                } else if (f.c(file)) {
                    OpenBtActivity.this.a(file, "image/*");
                } else if (f.d(file)) {
                    OpenBtActivity.this.a(file, "text/plain");
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
        Intent intent = new Intent("android.intent.action.VIEW");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("file://");
        stringBuilder.append(file.getPath());
        intent.setDataAndType(Uri.parse(stringBuilder.toString()), str);
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
