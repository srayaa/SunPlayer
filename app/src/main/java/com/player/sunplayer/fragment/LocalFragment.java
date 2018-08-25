package com.player.sunplayer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.player.sunplayer.R;
import com.player.sunplayer.a.LocalVideoFileAdapter;
import com.player.sunplayer.activity.MediaListActivity;
import com.player.sunplayer.filePicker.b.MediaStoreHelper;
import com.player.sunplayer.filePicker.models.MediaDirectory;
import droidninja.filepicker.cursors.loadercallbacks.FileResultCallback;

import java.util.List;

/* compiled from: LocalFragment */
public class LocalFragment extends BaseFragment {
    private ListView b;
    private TextView c;
    private LocalVideoFileAdapter d;
    private boolean e;
    private List<MediaDirectory> f;

    public void c() {
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_local, viewGroup, false);
    }

    protected void b() {
        this.b = (ListView) a((int) R.id.list_video);
        this.c = (TextView) a((int) R.id.txt_local_remind);
    }

    private void d() {
        this.b.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                MediaDirectory mediaDirectory = (MediaDirectory) LocalFragment.this.f.get(i);
                Bundle bundle = new Bundle();
                bundle.putParcelable("MediaDirectory", mediaDirectory);
                Intent intent = new Intent(LocalFragment.this.a, MediaListActivity.class);
                intent.putExtras(bundle);
                LocalFragment.this.startActivity(intent);
            }
        });
    }

    private void e() {
        this.c.setVisibility(View.INVISIBLE);
        this.b.setVisibility(View.VISIBLE);
    }

    private void f() {
        this.c.setVisibility(View.VISIBLE);
        this.b.setVisibility(View.INVISIBLE);
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z && this.e) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("SHOW_GIF", false);
            bundle.putInt("EXTRA_FILE_TYPE", 3);
            MediaStoreHelper.a(this.a, bundle, new FileResultCallback<MediaDirectory>() {
                @Override
                public void onResultCallback(List<MediaDirectory> files) {
                    ((MediaDirectory) files.get(0)).a(LocalFragment.this.getString(R.string.all_video));
                    LocalFragment.this.a((List) files);
                }
            });
            this.e = false;
        }
    }

    private void a(List<MediaDirectory> list) {
        if (list.size() == 0) {
            f();
            return;
        }
        this.f = list;
        if (this.d == null) {
            this.d = new LocalVideoFileAdapter(this.a, this.f);
            this.b.setAdapter(this.d);
        } else {
            this.d.a(this.f);
        }
        d();
        e();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.e = true;
    }

    public void onDestroy() {
        super.onDestroy();
        this.d = null;
        this.e = false;
    }
}
