package com.player.sunplayer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.player.sunplayer.R;
import com.player.sunplayer.a.LocalVideoAdapter;
import com.player.sunplayer.bean.History;
import com.player.sunplayer.bean.PlayHistory;
import com.player.sunplayer.bean.VideoInfo;
import com.player.sunplayer.filePicker.models.Media;
import com.player.sunplayer.filePicker.models.MediaDirectory;

public class MediaListActivity extends BaseActivity {
    private LocalVideoAdapter o;
    private MediaDirectory p;
    private ListView q;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_list_magnet);
        this.p = (MediaDirectory) getIntent().getParcelableExtra("MediaDirectory");
        if (this.p == null) {
            this.p = (MediaDirectory) bundle.getParcelable("MediaDirectory");
        }
        m();
        a(this.p.a());
    }

    private void m() {
        this.q = (ListView) findViewById(R.id.ll_details);
        this.o = new LocalVideoAdapter(this, this.p.b());
        this.q.setAdapter(this.o);
        this.q.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Media media = (Media) MediaListActivity.this.p.b().get(i);
                Parcelable videoInfo = new VideoInfo(media.e(), media.c());
                Intent intent = new Intent(MediaListActivity.this, VideoPlayerActivity.class);
                intent.putExtra("videoInfo", videoInfo);
                MediaListActivity.this.startActivity(intent);
                new PlayHistory(MediaListActivity.this).save(new History(media.c(), media.e()));
            }
        });
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.p != null) {
            bundle.putParcelable("MediaDirectory", this.p);
        }
    }
}
