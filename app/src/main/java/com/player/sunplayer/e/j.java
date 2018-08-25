package com.player.sunplayer.e;

import com.player.sunplayer.bean.VideoInfo;

/* compiled from: MagnetUtils */
public class j {

    /* compiled from: MagnetUtils */
    public interface a {
        void a();

        void a(VideoInfo videoInfo);
    }

    public static void a(final String str, final int i, final a aVar) {
        p.a(str, i, new a() {
            public void a() {
                h.a(str, i, aVar);
            }

            public void a(VideoInfo videoInfo) {
                aVar.a(videoInfo);
            }
        });
    }
}
