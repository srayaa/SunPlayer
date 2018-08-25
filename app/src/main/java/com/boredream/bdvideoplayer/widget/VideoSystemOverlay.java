package com.boredream.bdvideoplayer.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.player.sunplayer.R;

/* compiled from: VideoSystemOverlay */
public class VideoSystemOverlay extends FrameLayout {
    private TextView a;
    private ImageView b;
    private ProgressBar c;

    /* compiled from: VideoSystemOverlay */
    public enum SCREEN_SWITCH {
        VOLUME,
        BRIGHTNESS
    }

    public VideoSystemOverlay(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.video_overlay_system, this);
        this.a = (TextView) findViewById(R.id.system_ui_title);
        this.b = (ImageView) findViewById(R.id.system_ui_image);
        this.c = (ProgressBar) findViewById(R.id.system_ui_seek_bar);
        a();
    }

    public void a(SCREEN_SWITCH aVar, int i, int i2) {
        if (aVar == SCREEN_SWITCH.BRIGHTNESS) {
            this.a.setText(R.string.brightness);
            this.b.setImageResource(R.drawable.system_ui_brightness);
        } else if (aVar == SCREEN_SWITCH.VOLUME) {
            this.a.setText(R.string.volume);
            this.b.setImageResource(i2 == 0 ? R.drawable.system_ui_no_volume : R.drawable.system_ui_volume);
        }
        this.c.setMax(i);
        this.c.setProgress(i2);
        setVisibility(VISIBLE);
    }

    public void a() {
        setVisibility(GONE);
    }
}
