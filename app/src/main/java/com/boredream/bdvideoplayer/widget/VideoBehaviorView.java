package com.boredream.bdvideoplayer.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.provider.Settings.System;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;

/* compiled from: VideoBehaviorView */
public class VideoBehaviorView extends FrameLayout implements OnGestureListener {
    private GestureDetector a;
    private int b;
    private float c;
    private int d;
    private int e;
    protected Activity f;
    protected AudioManager g;
    private int h;

    protected void a(int i) {
    }

    protected void a(int i, int i2) {
    }

    protected void b(int i) {
    }

    protected void b(int i, int i2) {
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    public VideoBehaviorView(Context context) {
        super(context);
        a();
    }

    public VideoBehaviorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public VideoBehaviorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    @TargetApi(21)
    public VideoBehaviorView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a();
    }

    private void a() {
        Context context = getContext();
        if (context instanceof Activity) {
            this.a = new GestureDetector(context.getApplicationContext(), this);
            this.f = (Activity) context;
            this.g = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            this.d = this.g.getStreamMaxVolume(3);
            this.h = 255;
            return;
        }
        throw new RuntimeException("VideoBehaviorView context must be Activity");
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.a.onTouchEvent(motionEvent);
        int action = motionEvent.getAction() & 255;
        if (action != 1) {
            switch (action) {
                case 3:
                case 4:
                    break;
            }
        }
        a(this.b);
        return true;
    }

    public boolean onDown(MotionEvent motionEvent) {
        this.b = -1;
        this.c = (float) this.g.getStreamVolume(3);
        try {
            this.e = (int) (this.f.getWindow().getAttributes().screenBrightness * ((float) this.h));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        int width = getWidth();
        int height = getHeight();
        if (width <= 0 || height <= 0) {
            return false;
        }
        if (this.b < 0) {
            if (Math.abs(motionEvent2.getX() - motionEvent.getX()) >= Math.abs(motionEvent2.getY() - motionEvent.getY())) {
                this.b = 1;
            } else if (motionEvent.getX() <= ((float) (width / 2))) {
                this.b = 3;
            } else {
                this.b = 2;
            }
        }
        switch (this.b) {
            case 1:
                b((int) ((((f * 1.0f) / ((float) width)) * 480.0f) * 1000.0f));
                break;
            case 2:
                float f3 = (((float) this.d) * (f2 / ((float) height))) + this.c;
                if (f3 <= 0.0f) {
                    f3 = 0.0f;
                }
                if (f3 >= ((float) this.d)) {
                    f3 = (float) this.d;
                }
                this.g.setStreamVolume(3, Math.round(f3), 0);
                a(this.d, Math.round(f3));
                this.c = f3;
                break;
            case 3:
                try {
                    if (System.getInt(getContext().getContentResolver(), "screen_brightness_mode") == 1) {
                        System.putInt(getContext().getContentResolver(), "screen_brightness_mode", 0);
                    }
                    int i = (int) ((((float) this.h) * (f2 / ((float) height))) + ((float) this.e));
                    if (i <= 0) {
                        i = 0;
                    }
                    if (i >= this.h) {
                        i = this.h;
                    }
                    Window window = this.f.getWindow();
                    WindowManager.LayoutParams attributes = window.getAttributes();
                    attributes.screenBrightness = ((float) i) / ((float) this.h);
                    window.setAttributes(attributes);
                    b(this.h, i);
                    this.e = i;
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
        }
        return false;
    }
}
