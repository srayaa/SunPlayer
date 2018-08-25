package com.boredream.bdvideoplayer.widget;

import android.graphics.Bitmap;
import android.view.View;
import com.baidu.cloud.media.player.IMediaPlayer;

/* compiled from: IRenderView */
public interface IRenderView {

    /* compiled from: IRenderView */
    public interface ISurfaceHolder {
        void a(b bVar);

        void a(b bVar, int i, int i2);

        void a(b bVar, int i, int i2, int i3);
    }

    /* compiled from: IRenderView */
    public interface b {
        IRenderView a();

        void a(IMediaPlayer iMediaPlayer);
    }

    void a(int i, int i2);

    void a(ISurfaceHolder aVar);

    boolean a();

    void b();

    void b(int i, int i2);

    void b(ISurfaceHolder aVar);

    Bitmap getBitmap();

    View getView();

    void setAspectRatio(int i);

    void setVideoRotation(int i);
}
