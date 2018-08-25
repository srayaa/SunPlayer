package com.player.sunplayer.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;
import com.player.sunplayer.R;
import com.player.sunplayer.a.FileAdapter;
import com.player.sunplayer.activity.MainActivity;
import com.player.sunplayer.bean.History;
import com.player.sunplayer.bean.PlayHistory;
import com.player.sunplayer.e.TorrentUtils;
import java.io.File;
import java.util.List;

/* compiled from: FileManagerFragment */
public class FileManagerFragment extends Fragment {
    FileAdapter a;
    private RecyclerView b;
    private View c;
    private File d;
    private View e;
    private boolean f;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.c = layoutInflater.inflate(R.layout.fragment_file_manager, null);
        return this.c;
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z && this.f) {
            b();
            this.f = false;
        }
    }

    private void b() {
        this.b = (RecyclerView) this.c.findViewById(R.id.itemlist);
        this.e = this.c.findViewById(R.id.tv_file_goback);
        this.e.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FileManagerFragment.this.a();
            }
        });
        this.d = Environment.getExternalStorageDirectory().getParentFile();
        if (this.d == null || this.d.listFiles() == null) {
            this.d = Environment.getExternalStorageDirectory();
        }
        this.b.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.a = new FileAdapter(getActivity(), this.d);
        this.b.setAdapter(this.a);
        this.a.a(new FileAdapter.b() {
            public void a(View view, int i) {
                List a = com.player.sunplayer.e.f.a(FileManagerFragment.this.d.listFiles());
                com.player.sunplayer.e.f.a(a);
                File file = (File) a.get(i);
                if (file.isDirectory()) {
                    if (file.listFiles() == null || file.listFiles().length == 0) {
                        Toast.makeText(FileManagerFragment.this.getActivity(), R.string.empty_folder, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    FileManagerFragment.this.d = file;
                    FileManagerFragment.this.a.a(FileManagerFragment.this.d);
                    FileManagerFragment.this.a.notifyDataSetChanged();
                    FileManagerFragment.this.c();
                } else if (com.player.sunplayer.e.f.a(file)) {
                    Intent intent = new Intent(FileManagerFragment.this.getActivity(), MainActivity.class);
                    intent.putExtra("url", file.getPath());
                    intent.putExtra("title", file.getName());
                    FileManagerFragment.this.getActivity().startActivity(intent);
                    new PlayHistory(FileManagerFragment.this.getActivity()).save(new History(file.getPath(), file.getName()));
                } else if (com.player.sunplayer.e.f.e(file)) {
                    FileManagerFragment.this.a(file, "audio/*");
                } else if (com.player.sunplayer.e.f.f(file)) {
                    TorrentUtils.a(FileManagerFragment.this.getActivity(), file.getPath());
                } else if (com.player.sunplayer.e.f.c(file)) {
                    FileManagerFragment.this.a(file, "image/*");
                } else if (com.player.sunplayer.e.f.d(file)) {
                    FileManagerFragment.this.a(file, "text/plain");
                } else {
                    Snackbar.make(view, (int) R.string.not_opened, -1).show();
                }
            }
        });
    }

    private void c() {
        if (d()) {
            this.e.setVisibility(View.VISIBLE);
        } else {
            this.e.setVisibility(View.GONE);
        }
    }

    private boolean d() {
        File parentFile = this.d.getParentFile();
        return (parentFile == null || parentFile.listFiles() == null) ? false : this.d.getPath().equals(Environment.getExternalStorageDirectory().getParent()) ^ true;
    }

    private void a(File file, String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("file://");
        stringBuilder.append(file.getPath());
        intent.setDataAndType(Uri.parse(stringBuilder.toString()), str);
        getActivity().startActivity(intent);
    }

    public boolean a() {
        if (this.d.getPath().equals(Environment.getExternalStorageDirectory().getParent())) {
            return false;
        }
        this.d = this.d.getParentFile();
        this.a.a(this.d);
        this.a.notifyDataSetChanged();
        c();
        return true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f = true;
    }
}
