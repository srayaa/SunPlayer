package com.boredream.bdvideoplayer.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.baidu.cloud.media.player.IMediaPlayer;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SurfaceRenderView extends SurfaceView implements IRenderView {
    private MeasureHelper a;
    private SurfaceCallback SurfaceCallback;

    private static final class InternalSurfaceHolder implements IRenderView.b {
        private SurfaceRenderView a;
        private SurfaceHolder b;

        public InternalSurfaceHolder(SurfaceRenderView surfaceRenderView, SurfaceHolder surfaceHolder) {
            this.a = surfaceRenderView;
            this.b = surfaceHolder;
        }

        public void a(IMediaPlayer iMediaPlayer) {
            if (iMediaPlayer != null) {
                iMediaPlayer.setDisplay(this.b);
            }
        }
        @Override
        public SurfaceRenderView a() {
            return this.a;
        }
    }

    private static final class SurfaceCallback implements Callback {
        private SurfaceHolder a;
        private boolean b;
        private int c;
        private int d;
        private int e;
        private WeakReference<SurfaceRenderView> f;
        private Map<ISurfaceHolder, Object> g = new ConcurrentHashMap();

        public SurfaceCallback(SurfaceRenderView surfaceRenderView) {
            this.f = new WeakReference(surfaceRenderView);
        }

        public void a(ISurfaceHolder aVar) {
            IRenderView.b aVar2;
            this.g.put(aVar, aVar);
            if (this.a != null) {
                aVar2 = new InternalSurfaceHolder((SurfaceRenderView) this.f.get(), this.a);
                aVar.a(aVar2, this.d, this.e);
            } else {
                aVar2 = null;
            }
            if (this.b) {
                if (aVar2 == null) {
                    aVar2 = new InternalSurfaceHolder((SurfaceRenderView) this.f.get(), this.a);
                }
                aVar.a(aVar2, this.c, this.d, this.e);
            }
        }

        public void b(ISurfaceHolder aVar) {
            this.g.remove(aVar);
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            this.a = surfaceHolder;
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = 0;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("surfaceCreated: ");
            stringBuilder.append(surfaceHolder.getSurface());
            Log.d("SurfaceRenderView", stringBuilder.toString());
            IRenderView.b aVar = new InternalSurfaceHolder((SurfaceRenderView) this.f.get(), this.a);
            for (ISurfaceHolder a : this.g.keySet()) {
                a.a(aVar, 0, 0);
            }
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            this.a = null;
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = 0;
            IRenderView.b aVar = new InternalSurfaceHolder((SurfaceRenderView) this.f.get(), this.a);
            for (ISurfaceHolder a : this.g.keySet()) {
                a.a(aVar);
            }
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            this.a = surfaceHolder;
            this.b = true;
            this.c = i;
            this.d = i2;
            this.e = i3;
            IRenderView.b aVar = new InternalSurfaceHolder((SurfaceRenderView) this.f.get(), this.a);
            for (ISurfaceHolder a : this.g.keySet()) {
                a.a(aVar, i, i2, i3);
            }
        }
    }

    public boolean a() {
        return true;
    }

    public void b() {
    }

    public Bitmap getBitmap() {
        return null;
    }

    public View getView() {
        return this;
    }

    public SurfaceRenderView(Context context) {
        super(context);
        a(context);
    }

    public SurfaceRenderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public SurfaceRenderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    @TargetApi(21)
    public SurfaceRenderView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context);
    }

    private void a(Context context) {
        this.a = new MeasureHelper(this);
        this.SurfaceCallback = new SurfaceCallback(this);
        getHolder().addCallback(this.SurfaceCallback);
        getHolder().setType(0);
    }

    public void a(int i, int i2) {
        if (i > 0 && i2 > 0) {
            this.a.a(i, i2);
            getHolder().setFixedSize(i, i2);
            requestLayout();
        }
    }

    public void b(int i, int i2) {
        if (i > 0 && i2 > 0) {
            this.a.b(i, i2);
            requestLayout();
        }
    }

    public void setVideoRotation(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SurfaceView doesn't support rotation (");
        stringBuilder.append(i);
        stringBuilder.append(")!\n");
        Log.e("", stringBuilder.toString());
    }

    public void setAspectRatio(int i) {
        this.a.b(i);
        requestLayout();
    }

    protected void onMeasure(int i, int i2) {
        this.a.c(i, i2);
        setMeasuredDimension(this.a.a(), this.a.b());
    }

    public void a(ISurfaceHolder aVar) {
        this.SurfaceCallback.a(aVar);
    }

    public void b(ISurfaceHolder aVar) {
        this.SurfaceCallback.b(aVar);
    }

    @TargetApi(14)
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(SurfaceRenderView.class.getName());
    }

    @TargetApi(14)
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (VERSION.SDK_INT >= 14) {
            accessibilityNodeInfo.setClassName(SurfaceRenderView.class.getName());
        }
    }
}
