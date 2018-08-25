package com.player.sunplayer.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import com.player.sunplayer.R;
import com.player.sunplayer.activity.VideoPlayerActivity;
import com.player.sunplayer.bean.History;
import com.player.sunplayer.bean.PlayHistory;
import com.player.sunplayer.bean.VideoInfo;
import com.player.sunplayer.e.DownloadUtil;
import com.player.sunplayer.e.TorrentUtils;
import com.player.sunplayer.e.r;
import com.squareup.otto.Subscribe;

import java.io.File;

/* compiled from: WebFragment */
public class WebFragment extends BaseFragment {
    boolean b = true;
    private EditText c;
    private Button d;

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_web, viewGroup, false);
    }

    protected void b() {
        this.c = (EditText) a((int) R.id.edt_web_url);
        this.d = (Button) a((int) R.id.bt_web_play);
    }

    @Override
    public void c() {

    }


    @Subscribe
    public void onVipChange(String str) {

    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        com.player.sunplayer.b.ADBusProvider.a().register((Object) this);
    }

    public void onResume() {
        super.onResume();
        if (this.b) {
            b(this.a.getIntent().getDataString());
            this.b = false;
        }
    }

    public void a(Intent intent) {
        if (intent != null) {
            b(intent.getDataString());
        }
    }

    private void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (str.toLowerCase().endsWith(".torrent")) {
                str = com.player.sunplayer.e.b.a(getActivity().getApplicationContext(), Uri.parse(str));
                if (!TextUtils.isEmpty(str)) {
                    File file = new File(str);
                    if (file.exists()) {
                        new PlayHistory(getActivity().getApplicationContext()).save(new History(file.getPath(), file.getName()));
                        TorrentUtils.a(getActivity(), str);
                    }
                }
            } else if (this.c != null) {
                this.c.setText(str);
            }
        }
    }

    protected void a() {
        this.d.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WebFragment.this.e();
            }
        });
    }

    private void e() {
        String obj = this.c.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            a(getString(R.string.download_address));
        } else if (obj.startsWith("magnet")) {
            DownloadUtil.a(obj, getActivity());
            new PlayHistory(getActivity()).save(new History(obj, obj));
        } else if (r.b(obj)) {
            new PlayHistory(getActivity()).save(new History(obj, obj));
            Parcelable videoInfo = new VideoInfo(obj, obj);
            Intent intent = new Intent(getActivity(), VideoPlayerActivity.class);
            intent.putExtra("videoInfo", videoInfo);
            startActivity(intent);
        } else {
            a(getString(R.string.incorrect));
        }
    }

    public void onPause() {
        super.onPause();
        f();
    }

    private void f() {
        if (this.c != null) {
            this.c.postDelayed(new Runnable() {
                public void run() {
                    ((InputMethodManager) WebFragment.this.a.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(WebFragment.this.c.getWindowToken(), 0);
                }
            }, 100);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.b = true;
        com.player.sunplayer.b.ADBusProvider.a().unregister((Object) this);
    }
}
