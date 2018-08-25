package com.boredream.bdvideoplayer.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.player.sunplayer.R;

public class VideoProgressOverlay extends FrameLayout {
    private ImageView a;
    private TextView b;
    private TextView c;
    private int d = -1;
    private int e = -1;
    private int f = -1;

    public VideoProgressOverlay(Context context) {
        super(context);
        b();
    }

    public VideoProgressOverlay(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public VideoProgressOverlay(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    private void b() {
        LayoutInflater.from(getContext()).inflate(R.layout.video_overlay_progress, this);
        this.a = (ImageView) findViewById(R.id.iv_seek_direction);
        this.b = (TextView) findViewById(R.id.tv_seek_current_progress);
        this.c = (TextView) findViewById(R.id.tv_seek_duration);
    }

    public void a(int i, int i2, int i3) {
        if (i3 > 0) {
            if (this.f == -1) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("show: start seek = ");
                stringBuilder.append(this.f);
                Log.i("DDD", stringBuilder.toString());
                this.f = i2;
            }
            if (getVisibility() != VISIBLE) {
                setVisibility(VISIBLE);
            }
            this.d = i3;
            this.e -= i;
            i2 = getTargetProgress();
            if (i > 0) {
                this.a.setImageResource(R.drawable.ic_video_back);
            } else {
                this.a.setImageResource(R.drawable.ic_video_speed);
            }
            this.b.setText(com.boredream.bdvideoplayer.b.c.a(i2));
            this.c.setText(com.boredream.bdvideoplayer.b.c.a(this.d));
        }
    }

    public int getTargetProgress() {
        if (this.d == -1) {
            return -1;
        }
        int i = this.f + this.e;
        if (i <= 0) {
            i = 0;
        }
        if (i >= this.d) {
            i = this.d;
        }
        return i;
    }

    public void a() {
        this.d = -1;
        this.f = -1;
        this.e = -1;
        setVisibility(GONE);
    }
}
