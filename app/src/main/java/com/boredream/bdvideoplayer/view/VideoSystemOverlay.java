package com.boredream.bdvideoplayer.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.player.sunplayer.R;

public class VideoSystemOverlay extends FrameLayout {
    private TextView a;
    private ImageView b;
    private ProgressBar c;

    public VideoSystemOverlay(Context context) {
        super(context);
        a(context);
    }

    public VideoSystemOverlay(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.video_overlay_system, this);
        this.a = (TextView) findViewById(R.id.system_ui_title);
        this.b = (ImageView) findViewById(R.id.system_ui_image);
        this.c = (ProgressBar) findViewById(R.id.system_ui_seek_bar);
        a();
    }

    public void a() {
        setVisibility(GONE);
    }
}
