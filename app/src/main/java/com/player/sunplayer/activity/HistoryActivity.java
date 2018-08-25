package com.player.sunplayer.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.player.sunplayer.R;
import com.player.sunplayer.a.PlayerHistoryAdapter;
import com.player.sunplayer.bean.History;
import com.player.sunplayer.bean.PlayHistory;
import com.player.sunplayer.bean.VideoInfo;
import com.player.sunplayer.e.DownloadUtil;
import com.player.sunplayer.e.TorrentUtils;
import java.io.File;
import java.util.List;

public class HistoryActivity extends BaseActivity implements OnItemClickListener, OnItemLongClickListener {
    private List<History> o;
    private PlayerHistoryAdapter p;

    private void m() {
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_history_magnet);
        c(R.string.play_record);
        this.o = new PlayHistory(getApplicationContext()).getAll();
        a(this.o);
        m();
    }

    private void a(List<History> list) {
        ListView listView = (ListView) findViewById(R.id.ll_details);
        this.p = new PlayerHistoryAdapter(this, list);
        listView.setAdapter(this.p);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        String url = ((History) this.o.get(i)).getUrl();
        new PlayHistory(getApplicationContext()).save(new History(url, ((History) this.o.get(i)).getTitle()));
        if (url.startsWith("magnet")) {
            DownloadUtil.a(url, this);
        } else if (!url.toLowerCase().endsWith(".torrent")) {
            Parcelable videoInfo = new VideoInfo(((History) this.o.get(i)).getTitle(), url);
            Intent intent = new Intent(this, VideoPlayerActivity.class);
            intent.putExtra("videoInfo", videoInfo);
            startActivity(intent);
        } else if (new File(url).exists()) {
            TorrentUtils.a((Context) this, url);
        } else {
            Toast.makeText(getApplicationContext(), R.string.no_file, Toast.LENGTH_SHORT).show();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_play_history, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        n();
        return super.onOptionsItemSelected(menuItem);
    }

    private void n() {
        AlertDialog.Builder aVar = new AlertDialog.Builder(this);
        aVar.setTitle((int) R.string.confirm_delete_all_history);
        aVar.setPositiveButton((int) R.string.confirm, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                new PlayHistory(HistoryActivity.this.getApplicationContext()).deleteAll();
                HistoryActivity.this.p.a();
                Toast.makeText(HistoryActivity.this, R.string.empty, Toast.LENGTH_SHORT).show();
            }
        });
        aVar.setNegativeButton((int) R.string.cancel, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        aVar.create();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long j) {
        AlertDialog.Builder aVar = new AlertDialog.Builder(this);
        aVar.setTitle((int) R.string.confirm_delete_this_history);
        aVar.setPositiveButton((int) R.string.confirm, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                new PlayHistory(HistoryActivity.this.getApplicationContext()).delete(i);
                HistoryActivity.this.p.a(i);
                Toast.makeText(HistoryActivity.this, R.string.deleted, Toast.LENGTH_SHORT).show();
            }
        });
        aVar.setNegativeButton((int) R.string.cancel, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        aVar.create();
        return true;
    }
}
