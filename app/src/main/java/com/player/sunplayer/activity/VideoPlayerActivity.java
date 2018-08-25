package com.player.sunplayer.activity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.OrientationEventListener;

import com.boredream.bdvideoplayer.a.a;
import com.boredream.bdvideoplayer.widget.BDCloudVideoView;
import com.player.sunplayer.MyApplication;
import com.player.sunplayer.R;
import com.player.sunplayer.bean.DownloadInfo;
import com.player.sunplayer.bean.VideoInfo;
import com.player.sunplayer.e.k;
import com.xunlei.downloadlib.XLTaskHelper;
import java.util.HashMap;
import java.util.Map;

public class VideoPlayerActivity extends BaseActivity {
    OrientationEventListener o;
    private BDCloudVideoView p;
    private VideoInfo q;
    private DownloadInfo r;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_video_detail);
        getWindow().addFlags(NotificationCompat.FLAG_HIGH_PRIORITY);
        this.q = (VideoInfo) getIntent().getParcelableExtra("videoInfo");
        this.r = (DownloadInfo) getIntent().getSerializableExtra("downloadInfo");
        if (!(this.q != null || bundle == null || bundle.getParcelable("videoInfo") == null)) {
            this.q = (VideoInfo) bundle.getParcelable("videoInfo");
            this.r = (DownloadInfo) bundle.getSerializable("downloadInfo");
        }
        if (this.q == null || TextUtils.isEmpty(this.q.getUrl())) {
            b(getString(R.string.play_error));
            return;
        }
        BDCloudVideoView.setAK("4795f6d00635461ebfe00288c4a645bb");
        this.p = (BDCloudVideoView) findViewById(R.id.vv);
        if (!TextUtils.isEmpty(this.q.getCookie())) {
            Map hashMap = new HashMap();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" ");
            stringBuilder.append(this.q.getCookie());
            hashMap.put("Cookie", stringBuilder.toString());
            this.p.setHeaders(hashMap);
        }
        this.p.setVideoInfo(this.q);
        this.p.setOnVideoControlListener(new a() {
            public void a(int i) {
            }

            public void a() {
                VideoPlayerActivity.this.finish();
            }
        });
        o();
        if (this.q.getUrl().contains("TorrentPlayer/")) {

        } else {
            this.p.setOnHaveSpeedListener(new BDCloudVideoView.a() {
                public void a() {

                }
            });
        }
        this.p.start();
        m();
    }

    private void o() {
        this.o = new OrientationEventListener(this, 3) {
            public void onOrientationChanged(int i) {
                if (i != -1 && i <= 350 && i >= 10) {
                    if (i > 80 && i < 100) {
                        VideoPlayerActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                    } else if ((i <= 170 || i >= 190) && i > 260 && i < 280) {
                        VideoPlayerActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    }
                }
            }
        };
        if (this.o.canDetectOrientation()) {
            this.o.enable();
        } else {
            this.o.disable();
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable("videoInfo", this.q);
        if (this.r != null) {
            bundle.putSerializable("downloadInfo", this.r);
        }
        super.onSaveInstanceState(bundle);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        k.a("onConfigurationChanged", "onConfigurationChanged");
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
        } else if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        if (configuration.orientation == 1) {
            getWindow().clearFlags(1024);
        } else if (configuration.orientation == 2) {
            getWindow().addFlags(1024);
        }
    }

    protected void onRestart() {
        super.onRestart();
        if (this.p != null) {
            this.p.f();
        }
    }

    protected void onStop() {
        if (this.p != null) {
            this.p.e();
        }
        super.onStop();
    }

    protected void onDestroy() {
        if (this.o != null) {
            this.o.disable();
        }
        if (this.p != null) {
            this.p.b();
        }
        if (this.r != null) {
            XLTaskHelper.instance(MyApplication.d()).deleteTaskFile(this.r.getId().longValue(), this.r.getPath());
        }
        super.onDestroy();
    }

    protected void m() {
        try {
            if (VERSION.SDK_INT > 11 && VERSION.SDK_INT < 19) {
                getWindow().getDecorView().setSystemUiVisibility(8);
            } else if (VERSION.SDK_INT >= 19) {
                getWindow().getDecorView().setSystemUiVisibility(4102);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
