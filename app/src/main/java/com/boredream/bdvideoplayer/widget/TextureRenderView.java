package com.boredream.bdvideoplayer.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.baidu.cloud.media.player.IMediaPlayer;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@TargetApi(14)
/* compiled from: TextureRenderView */
public class TextureRenderView extends TextureView implements IRenderView {
    private MeasureHelper a;
    private int b = 0;
    private SurfaceTexture c;
    private b d;

    /* compiled from: TextureRenderView */
    private static final class a implements IRenderView.b {
        private TextureRenderView a;

        public a(TextureRenderView textureRenderViewVar) {
            this.a = textureRenderViewVar;
        }

        @TargetApi(16)
        public void a(IMediaPlayer iMediaPlayer) {
            if (iMediaPlayer != null && this.a.getSurfaceTexture() != null) {
                if (iMediaPlayer.hashCode() != this.a.getCurrentMediaPlayerCode()) {
                    iMediaPlayer.setSurface(b());
                } else if (!this.a.getLastSurfaceTexture().equals(this.a.getSurfaceTexture())) {
                    this.a.setSurfaceTexture(this.a.getLastSurfaceTexture());
                }
                this.a.setCurrentMediaPlayerCode(iMediaPlayer.hashCode());
            }
        }

        public IRenderView a() {
            return this.a;
        }

        public Surface b() {
            return new Surface(this.a.getSurfaceTexture());
        }
    }

    /* compiled from: TextureRenderView */
    private static final class b implements SurfaceTextureListener {
        private SurfaceTexture a;
        private boolean b;
        private int c;
        private int d;
        private volatile boolean e = false;
        private WeakReference<TextureRenderView> f;
        private Map<ISurfaceHolder, Object> g = new ConcurrentHashMap();

        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

        public b(TextureRenderView textureRenderViewVar) {
            this.f = new WeakReference(textureRenderViewVar);
        }

        public void a(boolean z) {
            this.e = z;
        }

        public void a(ISurfaceHolder aVar) {
            IRenderView.b aVar2;
            this.g.put(aVar, aVar);
            if (this.a != null) {
                aVar2 = new a((TextureRenderView) this.f.get());
                aVar.a(aVar2, this.c, this.d);
            } else {
                aVar2 = null;
            }
            if (this.b) {
                if (aVar2 == null) {
                    aVar2 = new a((TextureRenderView) this.f.get());
                }
                aVar.a(aVar2, 0, this.c, this.d);
            }
        }

        public void b(ISurfaceHolder aVar) {
            this.g.remove(aVar);
        }

        @TargetApi(16)
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            this.a = surfaceTexture;
            if (this.f.get() == null) {
                Log.e("TextureRenderView", "!!!!!Too bad, textureview in callback is released. function will not work normally");
            } else if (((TextureRenderView) this.f.get()).getLastSurfaceTexture() == null) {
                ((TextureRenderView) this.f.get()).setLastSurfaceTexture(surfaceTexture);
            }
            this.b = false;
            this.c = 0;
            this.d = 0;
            IRenderView.b aVar = new a((TextureRenderView) this.f.get());
            for (ISurfaceHolder a : this.g.keySet()) {
                a.a(aVar, 0, 0);
            }
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            this.a = surfaceTexture;
            this.b = true;
            this.c = i;
            this.d = i2;
            IRenderView.b aVar = new a((TextureRenderView) this.f.get());
            for (ISurfaceHolder a : this.g.keySet()) {
                a.a(aVar, 0, i, i2);
            }
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            this.a = surfaceTexture;
            this.b = false;
            this.c = 0;
            this.d = 0;
            IRenderView.b aVar = new a((TextureRenderView) this.f.get());
            for (ISurfaceHolder a : this.g.keySet()) {
                a.a(aVar);
            }
            return this.e;
        }
    }

    public boolean a() {
        return false;
    }

    public View getView() {
        return this;
    }

    public TextureRenderView(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.a = new MeasureHelper(this);
        this.d = new b(this);
        setSurfaceTextureListener(this.d);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d("TextureRenderView", "onDetachedFromWindow");
    }

    public void a(int i, int i2) {
        if (i > 0 && i2 > 0) {
            this.a.a(i, i2);
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
        this.a.a(i);
        setRotation((float) i);
    }

    public void setAspectRatio(int i) {
        this.a.b(i);
        requestLayout();
    }

    protected void onMeasure(int i, int i2) {
        this.a.c(i, i2);
        setMeasuredDimension(this.a.a(), this.a.b());
    }

    public void setCurrentMediaPlayerCode(int i) {
        this.b = i;
    }

    public int getCurrentMediaPlayerCode() {
        return this.b;
    }

    public SurfaceTexture getLastSurfaceTexture() {
        return this.c;
    }

    public void setLastSurfaceTexture(SurfaceTexture surfaceTexture) {
        this.c = surfaceTexture;
    }

    public IRenderView.b getSurfaceHolder() {
        return new a(this);
    }

    public void a(ISurfaceHolder aVar) {
        this.d.a(aVar);
    }

    public void b(ISurfaceHolder aVar) {
        this.d.b(aVar);
    }

    @TargetApi(16)
    public void b() {
        if (this.c == null) {
            return;
        }
        if (isAvailable()) {
            this.d.a(true);
            return;
        }
        this.c.release();
        this.c = null;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(TextureRenderView.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(TextureRenderView.class.getName());
    }
}
