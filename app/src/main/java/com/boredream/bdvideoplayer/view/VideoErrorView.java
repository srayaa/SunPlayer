package com.boredream.bdvideoplayer.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.boredream.bdvideoplayer.a.a;
import com.player.sunplayer.R;

public class VideoErrorView extends FrameLayout {
    private int a;
    private TextView b;
    private Button c;
    private a d;

    public int getCurStatus() {
        return this.a;
    }

    public VideoErrorView(Context context) {
        super(context);
        b();
    }

    public VideoErrorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public VideoErrorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    private void b() {
        LayoutInflater.from(getContext()).inflate(R.layout.video_controller_error, this);
        this.b = (TextView) findViewById(R.id.video_error_info);
        this.c = (Button) findViewById(R.id.video_error_retry);
        this.c.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (VideoErrorView.this.d != null) {
                    VideoErrorView.this.d.a(VideoErrorView.this.a);
                }
            }
        });
        a();
    }

    public void a(int i) {
        setVisibility(VISIBLE);
        if (this.a != i) {
            this.a = i;
            switch (i) {
                case 1:
                    Log.i("DDD", "showVideoDetailError");
                    this.b.setText(R.string.load_fail);
                    this.c.setText(R.string.retry);
                    break;
                case 2:
                    Log.i("DDD", "showVideoSrcError");
                    this.b.setText(R.string.load_fail);
                    this.c.setText(R.string.retry);
                    break;
                case 3:
                    Log.i("DDD", "showUnWifiError");
                    this.b.setText(R.string.warm_tip);
                    this.c.setText(R.string.resume_play);
                    break;
                case 4:
                    Log.i("DDD", "showNoNetWorkError");
                    this.b.setText(R.string.net_fail);
                    this.c.setText(R.string.retry);
                    break;
            }
        }
    }

    public void a() {
        Log.i("DDD", "hideError");
        setVisibility(GONE);
        this.a = 0;
    }

    public void setOnVideoControlListener(a aVar) {
        this.d = aVar;
    }
}
