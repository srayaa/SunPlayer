package com.boredream.bdvideoplayer.view;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import com.boredream.bdvideoplayer.a.a;
import com.boredream.bdvideoplayer.bean.IVideoInfo;
import com.boredream.bdvideoplayer.widget.BDCloudVideoView;
import com.player.sunplayer.R;

public class VideoControllerView extends FrameLayout {
    private View a;
    private View b;
    private TextView c;
    private View d;
    private SeekBar e;
    private ImageView f;
    private TextView g;
    private TextView h;
    private ImageView i;
    private VideoErrorView j;
    private boolean k;
    private boolean l;
    private boolean m;
    private BDCloudVideoView n;
    private IVideoInfo o;
    private a p;
    private final Runnable q = new Runnable() {
        public void run() {
            VideoControllerView.this.i();
        }
    };
    private boolean r;
    private long s;
    private final Runnable t = new Runnable() {
        public void run() {
            int f = VideoControllerView.this.j();
            if (VideoControllerView.this.n != null && !VideoControllerView.this.r && VideoControllerView.this.l && VideoControllerView.this.n.isPlaying()) {
                VideoControllerView.this.postDelayed(VideoControllerView.this.t, (long) (1000 - (f % 1000)));
            }
        }
    };
    private final OnSeekBarChangeListener u = new OnSeekBarChangeListener() {
        public void onStartTrackingTouch(SeekBar seekBar) {
            VideoControllerView.this.a(3600000);
            VideoControllerView.this.r = true;
            VideoControllerView.this.removeCallbacks(VideoControllerView.this.t);
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z && VideoControllerView.this.n != null) {
                VideoControllerView.this.s = (((long) VideoControllerView.this.n.getDuration()) * ((long) i)) / 1000;
                if (VideoControllerView.this.g != null) {
                    VideoControllerView.this.g.setText(com.boredream.bdvideoplayer.b.c.a((int) VideoControllerView.this.s));
                }
            }
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            if (VideoControllerView.this.n != null) {
                VideoControllerView.this.n.seekTo((int) VideoControllerView.this.s);
                VideoControllerView.this.r();
                VideoControllerView.this.r = false;
                VideoControllerView.this.s = 0;
                VideoControllerView.this.post(VideoControllerView.this.t);
            }
        }
    };
    private OnClickListener v = new OnClickListener() {
        public void onClick(View view) {
            VideoControllerView.this.p();
        }
    };

    public void setOnVideoControlListener(a aVar) {
        this.p = aVar;
    }

    public VideoControllerView(Context context) {
        super(context);
        g();
    }

    public VideoControllerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        g();
    }

    public VideoControllerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        g();
    }

    private void g() {
        LayoutInflater.from(getContext()).inflate(R.layout.video_media_controller, this);
        h();
    }

    private void h() {
        this.a = findViewById(R.id.video_back);
        this.a.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (VideoControllerView.this.p != null) {
                    VideoControllerView.this.p.a();
                }
            }
        });
        this.b = findViewById(R.id.video_controller_title);
        this.c = (TextView) this.b.findViewById(R.id.video_title);
        this.d = findViewById(R.id.video_controller_bottom);
        this.e = (SeekBar) this.d.findViewById(R.id.player_seek_bar);
        this.f = (ImageView) this.d.findViewById(R.id.player_pause);
        this.g = (TextView) this.d.findViewById(R.id.player_progress);
        this.h = (TextView) this.d.findViewById(R.id.player_duration);
        this.f.setOnClickListener(this.v);
        this.f.setImageResource(R.drawable.ic_video_pause);
        this.e.setOnSeekBarChangeListener(this.u);
        this.i = (ImageView) findViewById(R.id.player_lock_screen);
        this.i.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (VideoControllerView.this.k) {
                    VideoControllerView.this.m();
                } else {
                    VideoControllerView.this.l();
                }
                VideoControllerView.this.b();
            }
        });
        this.j = (VideoErrorView) findViewById(R.id.video_controller_error);
        this.j.setOnVideoControlListener(new com.boredream.bdvideoplayer.a.b() {
            public void a(int i) {
                VideoControllerView.this.b(i);
            }
        });
        this.e.setMax(1000);
    }

    public void setMediaPlayer(BDCloudVideoView bDCloudVideoView) {
        this.n = bDCloudVideoView;
        e();
    }

    public void setVideoInfo(IVideoInfo iVideoInfo) {
        this.o = iVideoInfo;
        this.c.setText(iVideoInfo.getVideoTitle());
    }

    public void a() {
        if (this.l) {
            i();
        } else {
            b();
        }
    }

    public void b() {
        a(3000);
    }

    public void a(int i) {
        j();
        if (this.k) {
            if (!com.boredream.bdvideoplayer.b.a.a(getContext())) {
                this.a.setVisibility(INVISIBLE);
            }
            this.b.setVisibility(INVISIBLE);
            this.d.setVisibility(INVISIBLE);
        } else {
            this.a.setVisibility(VISIBLE);
            this.b.setVisibility(VISIBLE);
            this.d.setVisibility(VISIBLE);
        }
        if (!com.boredream.bdvideoplayer.b.a.a(getContext())) {
            this.i.setVisibility(VISIBLE);
        }
        this.l = true;
        e();
        post(this.t);
        if (i > 0) {
            removeCallbacks(this.q);
            postDelayed(this.q, (long) i);
        }
    }

    private void i() {
        if (this.l) {
            if (!com.boredream.bdvideoplayer.b.a.a(getContext())) {
                this.a.setVisibility(INVISIBLE);
            }
            this.b.setVisibility(INVISIBLE);
            this.d.setVisibility(INVISIBLE);
            this.i.setVisibility(INVISIBLE);
            removeCallbacks(this.t);
            this.l = false;
        }
    }

    private int j() {
        if (this.n == null || this.r) {
            return 0;
        }
        int currentPosition = this.n.getCurrentPosition();
        int duration = this.n.getDuration();
        if (this.e != null) {
            if (duration > 0) {
                this.e.setProgress((int) ((((long) currentPosition) * 1000) / ((long) duration)));
            }
            this.e.setSecondaryProgress(this.n.getBufferPercentage() * 10);
        }
        this.g.setText(com.boredream.bdvideoplayer.b.c.a(currentPosition));
        this.h.setText(com.boredream.bdvideoplayer.b.c.a(duration));
        return currentPosition;
    }

    public void a(boolean z) {
        boolean a = com.boredream.bdvideoplayer.b.b.a(getContext());
        boolean c = com.boredream.bdvideoplayer.b.b.c(getContext());
        boolean b = com.boredream.bdvideoplayer.b.b.b(getContext());
        if (!a) {
            this.n.pause();
            c(4);
        } else if (this.j.getCurStatus() == 4 && (!c || b)) {
        } else {
            if (this.o == null) {
                c(1);
            } else if (c && !b && !this.m) {
                this.j.a(3);
                this.n.pause();
            } else if (b && z && this.j.getCurStatus() == 3) {
                o();
            } else if (!z) {
                c(2);
            }
        }
    }

    public void c() {
        this.j.a();
    }

    private void k() {
        this.n.start();
        c();
    }

    private void b(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("STATUS:");
        stringBuilder.append(i);
        Log.e("RETRY", stringBuilder.toString());
        switch (i) {
            case 1:
                if (this.p != null) {
                    this.p.a(i);
                    return;
                }
                return;
            case 2:
                k();
                return;
            case 3:
                n();
                return;
            case 4:
                if (!com.boredream.bdvideoplayer.b.b.a(getContext())) {
                    Toast.makeText(getContext(), R.string.no_web, Toast.LENGTH_SHORT).show();
                    return;
                } else if (this.o == null) {
                    b(1);
                    return;
                } else if (this.n.d()) {
                    this.n.start();
                    return;
                } else {
                    k();
                    return;
                }
            default:
                return;
        }
    }

    private void c(int i) {
        this.j.a(i);
        i();
        if (this.k) {
            m();
        }
    }

    public boolean d() {
        return this.k;
    }

    private void l() {
        Log.i("DDD", "lock");
        this.k = true;
        this.i.setImageResource(R.drawable.video_locked);
    }

    private void m() {
        Log.i("DDD", "unlock");
        this.k = false;
        this.i.setImageResource(R.drawable.video_unlock);
    }

    private void n() {
        Log.i("DDD", "allowUnWifiPlay");
        this.m = true;
        o();
    }

    private void o() {
        Log.i("DDD", "playFromUnWifiError");
        if (this.n != null) {
            if (this.n.d()) {
                this.n.start();
            } else {
                this.n.start();
            }
        }
    }

    public void e() {
        if (this.n != null) {
            if (this.n.isPlaying()) {
                this.f.setImageResource(R.drawable.ic_video_pause);
            } else {
                this.f.setImageResource(R.drawable.ic_video_play);
            }
        }
    }

    private void p() {
        if (this.n != null) {
            if (this.n.isPlaying()) {
                q();
            } else {
                r();
            }
        }
    }

    private void q() {
        this.n.pause();
        this.f.setImageResource(R.drawable.ic_video_play);
        removeCallbacks(this.q);
    }

    private void r() {
        this.n.start();
        b();
        this.f.setImageResource(R.drawable.ic_video_pause);
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        f();
    }

    void f() {
        if (com.boredream.bdvideoplayer.b.a.a(getContext())) {
            this.a.setVisibility(VISIBLE);
            this.i.setVisibility(INVISIBLE);
        } else if (this.l) {
            this.i.setVisibility(VISIBLE);
        }
    }
}
