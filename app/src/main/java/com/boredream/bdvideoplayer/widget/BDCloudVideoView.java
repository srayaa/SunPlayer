package com.boredream.bdvideoplayer.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.cloud.media.player.BDCloudMediaPlayer;
import com.baidu.cloud.media.player.IMediaPlayer;
import com.baidu.cloud.media.player.IMediaPlayer.OnBufferingUpdateListener;
import com.baidu.cloud.media.player.IMediaPlayer.OnCompletionListener;
import com.baidu.cloud.media.player.IMediaPlayer.OnErrorListener;
import com.baidu.cloud.media.player.IMediaPlayer.OnInfoListener;
import com.baidu.cloud.media.player.IMediaPlayer.OnPreparedListener;
import com.baidu.cloud.media.player.IMediaPlayer.OnSeekCompleteListener;
import com.baidu.cloud.media.player.IMediaPlayer.OnVideoSizeChangedListener;
import com.boredream.bdvideoplayer.bean.IVideoInfo;
import com.boredream.bdvideoplayer.view.VideoControllerView;
import com.boredream.bdvideoplayer.view.VideoProgressOverlay;
import com.player.sunplayer.R;

import java.util.Map;

import static android.widget.RelativeLayout.ALIGN_PARENT_LEFT;
import static android.widget.RelativeLayout.BELOW;

public class BDCloudVideoView extends VideoBehaviorView implements MediaPlayerControl {
    private OnSeekCompleteListener A;
    private boolean B = true;
    private boolean C = true;
    private boolean D = true;
    private String E = null;
    private int F = 0;
    private Context G;
    private IRenderView mRenderView;
    private int I;
    private int J;
    private int K = 20000;
    private boolean L = true;
    private int M = 0;
    private boolean N = false;
    private long O = 0;
    private int P = 0;
    private float Q = -1.0f;
    private float mR = -1.0f;
    private boolean S = false;
    private int T = -1;
    private int U = 0;
    private int V = 0;
    private boolean W = false;
    a a;
    private int aa = 0;
    private int ab = -1;
    private float ac = 1.0f;
    private RelativeLayout ad = null;
    private ProgressBar ae = null;
    private TextView af = null;
    private FrameLayout ag = null;
    private Handler ah = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            boolean z = true;
            if (message.what == 1) {
                BDCloudVideoView bDCloudVideoView = BDCloudVideoView.this;
                if (message.arg1 != 1) {
                    z = false;
                }
                bDCloudVideoView.setCachingHintViewVisibility(z);
            }
        }
    };
    private int ai = 0;
    private boolean aj = true;
    private VideoSystemOverlay ak;
    private VideoControllerView al;
    private VideoProgressOverlay am;
    private IVideoInfo an;
    private OnCompletionListener ao = new OnCompletionListener() {
        public void onCompletion(IMediaPlayer iMediaPlayer) {
            BDCloudVideoView.this.al.e();
            BDCloudVideoView.this.a(false);
            BDCloudVideoView.this.setCurrentState(c.STATE_PLAYBACK_COMPLETED);
            BDCloudVideoView.this.l = false;
            if (BDCloudVideoView.this.t != null) {
                BDCloudVideoView.this.t.onCompletion(BDCloudVideoView.this.n);
            }
        }
    };
    private OnInfoListener ap = new OnInfoListener() {
        public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onInfo: arg1=");
            stringBuilder.append(i);
            stringBuilder.append("; arg2=");
            stringBuilder.append(i2);
            Log.d("BDCloudVideoView", stringBuilder.toString());
            if (BDCloudVideoView.this.x != null) {
                BDCloudVideoView.this.x.onInfo(iMediaPlayer, i, i2);
            }
            StringBuilder stringBuilder2;
            switch (i) {
                case 3:
                    Log.d("BDCloudVideoView", "MEDIA_INFO_VIDEO_RENDERING_START:");
                    break;
                case IMediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING /*700*/:
                    Log.d("BDCloudVideoView", "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                    break;
                case IMediaPlayer.MEDIA_INFO_BUFFERING_START /*701*/:
                    Log.d("BDCloudVideoView", "MEDIA_INFO_BUFFERING_START:");
                    BDCloudVideoView.this.a(true);
                    break;
                case IMediaPlayer.MEDIA_INFO_BUFFERING_END /*702*/:
                    Log.e("BDCloudVideoView", "MEDIA_INFO_BUFFERING_END");
                    BDCloudVideoView.this.a(false);
                    break;
                case IMediaPlayer.MEDIA_INFO_NETWORK_BANDWIDTH /*703*/:
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("MEDIA_INFO_NETWORK_BANDWIDTH: ");
                    stringBuilder2.append(i2);
                    Log.d("BDCloudVideoView", stringBuilder2.toString());
                    break;
                case IMediaPlayer.MEDIA_INFO_BAD_INTERLEAVING /*800*/:
                    Log.d("BDCloudVideoView", "MEDIA_INFO_BAD_INTERLEAVING:");
                    break;
                case IMediaPlayer.MEDIA_INFO_NOT_SEEKABLE /*801*/:
                    Log.d("BDCloudVideoView", "MEDIA_INFO_NOT_SEEKABLE:");
                    break;
                case IMediaPlayer.MEDIA_INFO_METADATA_UPDATE /*802*/:
                    Log.d("BDCloudVideoView", "MEDIA_INFO_METADATA_UPDATE:");
                    break;
                case IMediaPlayer.MEDIA_INFO_UNSUPPORTED_SUBTITLE /*901*/:
                    Log.d("BDCloudVideoView", "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                    break;
                case IMediaPlayer.MEDIA_INFO_SUBTITLE_TIMED_OUT /*902*/:
                    Log.d("BDCloudVideoView", "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                    break;
                case IMediaPlayer.MEDIA_INFO_VIDEO_ROTATION_CHANGED /*10001*/:
                    BDCloudVideoView.this.s = i2;
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("MEDIA_INFO_VIDEO_ROTATION_CHANGED: ");
                    stringBuilder2.append(i2);
                    Log.d("BDCloudVideoView", stringBuilder2.toString());
                    if (BDCloudVideoView.this.mRenderView != null) {
                        BDCloudVideoView.this.mRenderView.setVideoRotation(i2);
                        break;
                    }
                    break;
                case IMediaPlayer.MEDIA_INFO_AUDIO_RENDERING_START /*10002*/:
                    if (BDCloudVideoView.this.aj && BDCloudVideoView.this.a != null && BDCloudVideoView.this.an != null && BDCloudVideoView.this.an.getVideoPath().startsWith("http")) {
                        BDCloudVideoView.this.aj = false;
                        BDCloudVideoView.this.a.a();
                    }
                    Log.d("BDCloudVideoView", "MEDIA_INFO_AUDIO_RENDERING_START:");
                    break;
            }
            return true;
        }
    };
    private OnErrorListener aq = new OnErrorListener() {
        public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
            BDCloudVideoView.this.al.a(false);
            BDCloudVideoView.this.setCurrentState(c.STATE_ERROR);
            BDCloudVideoView.this.l = false;
            BDCloudVideoView.this.a(false);
            return (BDCloudVideoView.this.w == null || BDCloudVideoView.this.w.onError(BDCloudVideoView.this.n, i, i2)) ? true : true;
        }
    };
    private OnBufferingUpdateListener ar = new OnBufferingUpdateListener() {
        public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
            BDCloudVideoView.this.v = i;
            if (BDCloudVideoView.this.z != null) {
                BDCloudVideoView.this.z.onBufferingUpdate(iMediaPlayer, i);
            }
        }
    };
    private OnSeekCompleteListener as = new OnSeekCompleteListener() {
        public void onSeekComplete(IMediaPlayer iMediaPlayer) {
            BDCloudVideoView.this.a(false);
            if (BDCloudVideoView.this.A != null) {
                BDCloudVideoView.this.A.onSeekComplete(iMediaPlayer);
            }
        }
    };
    Handler b = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 0) {
                long downloadSpeed = BDCloudVideoView.this.getDownloadSpeed() / 1024;
                if (downloadSpeed > 100) {
                    BDCloudVideoView.this.ai = BDCloudVideoView.this.ai + 1;
                    if (BDCloudVideoView.this.aj && BDCloudVideoView.this.ai > 3 && BDCloudVideoView.this.a != null) {
                        BDCloudVideoView.this.aj = false;
                        BDCloudVideoView.this.a.a();
                    }
                }
                if (BDCloudVideoView.this.af != null && BDCloudVideoView.this.af.getVisibility() == VISIBLE) {
                    TextView d = BDCloudVideoView.this.af;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(BDCloudVideoView.this.getResources().getText(R.string.cache).toString());
                    stringBuilder.append("\n");
                    stringBuilder.append(downloadSpeed);
                    stringBuilder.append("kb/s");
                    d.setText(stringBuilder.toString());
                }
                BDCloudVideoView.this.b.sendMessageDelayed(BDCloudVideoView.this.b.obtainMessage(0), 800);
            }
        }
    };
    OnVideoSizeChangedListener videoSizeChangedListener = new OnVideoSizeChangedListener() {
        public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onVideoSizeChanged width=");
            stringBuilder.append(i);
            stringBuilder.append(";height=");
            stringBuilder.append(i2);
            stringBuilder.append(";sarNum=");
            stringBuilder.append(i3);
            stringBuilder.append(";sarDen=");
            stringBuilder.append(i4);
            Log.d("BDCloudVideoView", stringBuilder.toString());
            BDCloudVideoView.this.o = iMediaPlayer.getVideoWidth();
            BDCloudVideoView.this.p = iMediaPlayer.getVideoHeight();
            BDCloudVideoView.this.I = iMediaPlayer.getVideoSarNum();
            BDCloudVideoView.this.J = iMediaPlayer.getVideoSarDen();
            if (BDCloudVideoView.this.o != 0 && BDCloudVideoView.this.p != 0) {
                if (BDCloudVideoView.this.mRenderView != null) {
                    BDCloudVideoView.this.mRenderView.a(BDCloudVideoView.this.o, BDCloudVideoView.this.p);
                    BDCloudVideoView.this.mRenderView.b(BDCloudVideoView.this.I, BDCloudVideoView.this.J);
                }
                BDCloudVideoView.this.requestLayout();
            }
        }
    };
    OnPreparedListener d = new OnPreparedListener() {
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            BDCloudVideoView.this.al.b();
            BDCloudVideoView.this.al.c();
            BDCloudVideoView.this.setCurrentState(c.STATE_PREPARED);
            BDCloudVideoView.this.a(false);
            BDCloudVideoView.this.o = iMediaPlayer.getVideoWidth();
            BDCloudVideoView.this.p = iMediaPlayer.getVideoHeight();
            if (BDCloudVideoView.this.u != null) {
                BDCloudVideoView.this.u.onPrepared(BDCloudVideoView.this.n);
            }
            if (BDCloudVideoView.this.o == 0 || BDCloudVideoView.this.p == 0) {
                if (BDCloudVideoView.this.l) {
                    BDCloudVideoView.this.start();
                }
            } else if (BDCloudVideoView.this.mRenderView != null) {
                BDCloudVideoView.this.mRenderView.a(BDCloudVideoView.this.o, BDCloudVideoView.this.p);
                BDCloudVideoView.this.mRenderView.b(BDCloudVideoView.this.I, BDCloudVideoView.this.J);
                if ((!BDCloudVideoView.this.mRenderView.a() || (BDCloudVideoView.this.q == BDCloudVideoView.this.o && BDCloudVideoView.this.r == BDCloudVideoView.this.p)) && BDCloudVideoView.this.l) {
                    BDCloudVideoView.this.start();
                }
            }
        }
    };
    IRenderView.ISurfaceHolder e = new IRenderView.ISurfaceHolder() {
        public void a(IRenderView.b bVar, int i, int i2, int i3) {
            Log.d("BDCloudVideoView", "mSHCallback onSurfaceChanged");
            if (bVar.a() != BDCloudVideoView.this.mRenderView) {
                Log.e("BDCloudVideoView", "onSurfaceChanged: unmatched render callback\n");
                return;
            }
            BDCloudVideoView.this.q = i2;
            BDCloudVideoView.this.r = i3;
            boolean o = BDCloudVideoView.this.l;
            Object obj = (!BDCloudVideoView.this.mRenderView.a() || (BDCloudVideoView.this.o == i2 && BDCloudVideoView.this.p == i3)) ? 1 : null;
            if (!(BDCloudVideoView.this.n == null || !o || obj == null)) {
                BDCloudVideoView.this.start();
            }
        }

        public void a(IRenderView.b bVar, int i, int i2) {
            if (bVar.a() != BDCloudVideoView.this.mRenderView) {
                Log.e("BDCloudVideoView", "onSurfaceCreated: unmatched render callback\n");
                return;
            }
            BDCloudVideoView.this.m = bVar;
            if (BDCloudVideoView.this.n != null) {
                BDCloudVideoView.this.a(BDCloudVideoView.this.n, bVar);
            } else {
                BDCloudVideoView.this.l();
            }
        }

        public void a(IRenderView.b bVar) {
            if (bVar.a() != BDCloudVideoView.this.mRenderView) {
                Log.e("BDCloudVideoView", "onSurfaceDestroyed: unmatched render callback\n");
                return;
            }
            BDCloudVideoView.this.m = null;
            BDCloudVideoView.this.m();
        }
    };
    private boolean h = true;
    private Uri i;
    private Map<String, String> j;
    private c k = c.STATE_IDLE;
    private boolean l = false;
    private IRenderView.b m = null;
    private BDCloudMediaPlayer n = null;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private OnCompletionListener t;
    private OnPreparedListener u;
    private int v;
    private OnErrorListener w;
    private OnInfoListener x;
    private b y;
    private OnBufferingUpdateListener z;

    public interface a {
        void a();
    }

    public interface b {
        void a(c cVar);
    }

    public enum c {
        STATE_ERROR(-1),
        STATE_IDLE(0),
        STATE_PREPARING(1),
        STATE_PREPARED(2),
        STATE_PLAYING(3),
        STATE_PAUSED(4),
        STATE_PLAYBACK_COMPLETED(5);
        
        private int h;

        private c(int i) {
            this.h = i;
        }
    }

    public int getAudioSessionId() {
        return 0;
    }

    public c getCurrentPlayerState() {
        return this.k;
    }

    private void setCurrentState(c cVar) {
        if (this.k != cVar) {
            this.k = cVar;
            if (this.y != null) {
                this.y.a(this.k);
            }
        }
    }

    public void setOnPlayerStateListener(b bVar) {
        this.y = bVar;
    }

    public BDCloudVideoView(Context context) {
        super(context);
        a(context);
    }

    public BDCloudVideoView(Context context, boolean z) {
        super(context);
        this.h = z;
        a(context);
    }

    public BDCloudVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public BDCloudVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    @TargetApi(21)
    public BDCloudVideoView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context);
    }

    public void setOnHaveSpeedListener(a aVar) {
        this.a = aVar;
    }

    private void a(Context context) {
        this.G = context.getApplicationContext();
        this.ag = new FrameLayout(context);
        addView(this.ag, new LayoutParams(-1, -1));
        a();
        i();
        k();
        j();
        h();
        this.o = 0;
        this.p = 0;
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        setCurrentState(c.STATE_IDLE);
        this.b.sendMessage(this.b.obtainMessage(0));
    }

    private void h() {
        this.am = new VideoProgressOverlay(getContext());
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2, 17);
        this.am.setVisibility(GONE);
        addView(this.am, layoutParams);
    }

    private void i() {
        this.al = new VideoControllerView(getContext());
        addView(this.al, new LayoutParams(-1, -1));
    }

    private void j() {
        this.ak = new VideoSystemOverlay(getContext());
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2, 17);
        this.ak.setVisibility(INVISIBLE);
        addView(this.ak, layoutParams);
    }

    private void k() {
        this.ad = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.ad.setVisibility(INVISIBLE);
        addView(this.ad, layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        this.ae = new ProgressBar(getContext());
        this.ae.setId(R.id.progressBar);
        this.ae.setMax(100);
        this.ae.setProgress(10);
        this.ae.setSecondaryProgress(100);
        this.ad.addView(this.ae, layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(ALIGN_PARENT_LEFT);
        layoutParams.addRule(BELOW, R.id.progressBar);
        this.af = new TextView(getContext());
        this.af.setTextColor(-1);
        this.af.setText(R.string.cache);
        this.af.setGravity(1);
        this.ad.addView(this.af, layoutParams);
    }

    private void setCachingHintViewVisibility(boolean z) {
        if (z) {
            this.ad.setVisibility(VISIBLE);
        } else {
            this.ad.setVisibility(INVISIBLE);
        }
    }

    private void a(boolean z) {
        if (this.L) {
            Message obtainMessage = this.ah.obtainMessage();
            obtainMessage.what = 1;
            obtainMessage.arg1 = z ? 1 : 0;
            this.ah.sendMessage(obtainMessage);
        }
    }

    protected void setRenderView(IRenderView aVar) {
        if (this.mRenderView != null) {
            if (this.n != null) {
                this.n.setDisplay(null);
            }
            View view = this.mRenderView.getView();
            this.mRenderView.b(this.e);
            this.mRenderView.b();
            this.mRenderView = null;
            this.m = null;
            this.ag.removeView(view);
        }
        if (aVar != null) {
            this.mRenderView = aVar;
            aVar.setAspectRatio(this.F);
            if (this.o > 0 && this.p > 0) {
                aVar.a(this.o, this.p);
            }
            if (this.I > 0 && this.J > 0) {
                aVar.b(this.I, this.J);
            }
            View view2 = this.mRenderView.getView();
            view2.setLayoutParams(new LayoutParams(-2, -2, 17));
            this.ag.addView(view2);
            this.mRenderView.a(this.e);
            this.mRenderView.setVideoRotation(this.s);
        }
    }

    public void a() {
        if (!this.h || VERSION.SDK_INT < 16) {
            setRenderView(new SurfaceRenderView(getContext()));
            return;
        }
        TextureRenderView cVar = new TextureRenderView(getContext());
        if (this.n != null) {
            cVar.getSurfaceHolder().a(this.n);
            cVar.a(this.n.getVideoWidth(), this.n.getVideoHeight());
            cVar.b(this.n.getVideoSarNum(), this.n.getVideoSarDen());
            cVar.setAspectRatio(this.F);
        }
        setRenderView(cVar);
    }

    private void setVideoPath(String str) {
        a(str, null);
    }

    public void setVideoInfo(IVideoInfo iVideoInfo) {
        a(iVideoInfo.getVideoPath(), null);
        this.an = iVideoInfo;
    }

    public void a(String str, String str2) {
        this.E = str2;
        setVideoURI(Uri.parse(str));
    }

    public void setHeaders(Map<String, String> map) {
        this.j = map;
    }

    private void setVideoURI(Uri uri) {
        this.i = uri;
        l();
        requestLayout();
        invalidate();
    }

    public void b() {
        if (this.n != null) {
            this.ai = 0;
            this.aj = true;
            this.n.stop();
            this.n.release();
            this.n = null;
            setCurrentState(c.STATE_IDLE);
            this.l = false;
            ((AudioManager) this.G.getSystemService(Context.AUDIO_SERVICE)).abandonAudioFocus(null);
            this.b.removeCallbacksAndMessages(null);
        }
    }

    @TargetApi(14)
    private void l() {
        StringBuilder stringBuilder;
        if (this.i != null && this.m != null) {
            b(false);
            ((AudioManager) this.G.getSystemService(Context.AUDIO_SERVICE)).requestAudioFocus(null, 3, 1);
            try {
                this.n = c();
                if (!TextUtils.isEmpty(this.E)) {
                    this.n.setDecryptTokenForHLS(this.E);
                }
                this.n.setOnPreparedListener(this.d);
                this.n.setOnVideoSizeChangedListener(this.videoSizeChangedListener);
                this.n.setOnCompletionListener(this.ao);
                this.n.setOnErrorListener(this.aq);
                this.n.setOnInfoListener(this.ap);
                this.n.setOnBufferingUpdateListener(this.ar);
                this.n.setOnSeekCompleteListener(this.as);
                this.v = 0;
                this.n.setDataSource(this.G, this.i, this.j);
                a(this.n, this.m);
                this.n.setAudioStreamType(3);
                this.n.setScreenOnWhilePlaying(true);
                this.n.prepareAsync();
                a(true);
                this.al.setMediaPlayer(this);
                setCurrentState(c.STATE_PREPARING);
            } catch (Throwable e) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Unable to open content: ");
                stringBuilder.append(this.i);
                Log.w("BDCloudVideoView", stringBuilder.toString(), e);
                setCurrentState(c.STATE_ERROR);
                this.l = false;
                this.aq.onError(this.n, 1, 0);
            }
        }
    }

    public BDCloudMediaPlayer c() {
        BDCloudMediaPlayer bDCloudMediaPlayer = new BDCloudMediaPlayer(getContext());
        bDCloudMediaPlayer.setLogEnabled(this.N);
        bDCloudMediaPlayer.setDecodeMode(this.M);
        if (this.O > -1) {
            bDCloudMediaPlayer.setInitPlayPosition(this.O);
            this.O = -1;
        }
        if (this.P > 0) {
            bDCloudMediaPlayer.setWakeMode(getContext(), this.P);
        }
        if (this.Q > -1.0f && this.mR > -1.0f) {
            bDCloudMediaPlayer.setVolume(this.Q, this.mR);
        }
        boolean z = this.S;
        if (this.K > 0) {
            bDCloudMediaPlayer.setBufferTimeInMs(this.K);
        }
        if (this.T >= 0) {
            bDCloudMediaPlayer.setMaxProbeTime(this.T);
        }
        if (this.U > 0) {
            bDCloudMediaPlayer.setMaxProbeSize(this.U);
        }
        if (this.V > 0) {
            bDCloudMediaPlayer.setMaxCacheSizeInBytes(this.V);
        }
        if (this.W) {
            bDCloudMediaPlayer.setLooping(this.W);
        }
        if (this.aa > 0) {
            bDCloudMediaPlayer.setBufferSizeInBytes(this.aa);
        }
        bDCloudMediaPlayer.setSpeed(this.ac);
        return bDCloudMediaPlayer;
    }

    public void setBufferTimeInMs(int i) {
        this.K = i;
        if (this.n != null) {
            this.n.setBufferTimeInMs(this.K);
        }
    }

    public void setBufferSizeInBytes(int i) {
        this.aa = i;
        if (this.n != null) {
            this.n.setBufferSizeInBytes(this.aa);
        }
    }

    public void setLooping(boolean z) {
        this.W = z;
        if (this.n != null) {
            this.n.setLooping(this.W);
        }
    }

    public void setMaxCacheSizeInBytes(int i) {
        this.V = i;
        if (this.n != null) {
            this.n.setMaxCacheSizeInBytes(this.V);
        }
    }

    public void setMaxProbeSize(int i) {
        this.U = i;
        if (this.n != null) {
            this.n.setMaxProbeSize(this.U);
        }
    }

    public void setMaxProbeTime(int i) {
        this.T = i;
        if (this.n != null) {
            this.n.setMaxProbeTime(this.T);
        }
    }

    public void setUseApmDetect(boolean z) {
        this.S = z;
        BDCloudMediaPlayer bDCloudMediaPlayer = this.n;
    }

    public void setSpeed(float f) {
        this.ac = f;
        if (this.n != null) {
            this.n.setSpeed(this.ac);
        }
    }

    public void setInitPlayPosition(long j) {
        this.O = j;
        if (this.n != null) {
            this.n.setInitPlayPosition(this.O);
            this.O = -1;
        }
    }

    public void setLogEnabled(boolean z) {
        this.N = z;
        if (this.n != null) {
            this.n.setLogEnabled(this.N);
        }
    }

    public void setDecodeMode(int i) {
        this.M = i;
        if (this.n != null) {
            this.n.setDecodeMode(this.M);
        }
    }

    public IMediaPlayer getCurrentMediaPlayer() {
        return this.n;
    }

    public void setOnPreparedListener(OnPreparedListener onPreparedListener) {
        this.u = onPreparedListener;
    }

    public void setOnCompletionListener(OnCompletionListener onCompletionListener) {
        this.t = onCompletionListener;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.w = onErrorListener;
    }

    public void setOnInfoListener(OnInfoListener onInfoListener) {
        this.x = onInfoListener;
    }

    public void setOnBufferingUpdateListener(OnBufferingUpdateListener onBufferingUpdateListener) {
        this.z = onBufferingUpdateListener;
    }

    public void setOnSeekCompleteListener(OnSeekCompleteListener onSeekCompleteListener) {
        this.A = onSeekCompleteListener;
    }

    private void a(IMediaPlayer iMediaPlayer, IRenderView.b bVar) {
        if (iMediaPlayer != null) {
            if (bVar == null) {
                iMediaPlayer.setDisplay(null);
            } else {
                bVar.a(iMediaPlayer);
            }
        }
    }

    private void m() {
        if (this.n != null && (this.mRenderView instanceof SurfaceRenderView)) {
            this.n.setDisplay(null);
        }
    }

    private void b(boolean z) {
        if (this.n != null) {
            this.n.release();
            this.n = null;
            setCurrentState(c.STATE_IDLE);
            if (z) {
                this.l = false;
            }
            ((AudioManager) this.G.getSystemService(Context.AUDIO_SERVICE)).abandonAudioFocus(null);
        }
    }

    public void start() {
        this.al.setVideoInfo(this.an);
        if ((this.n != null && this.k == c.STATE_ERROR) || this.k == c.STATE_PLAYBACK_COMPLETED) {
            if (this.k == c.STATE_PLAYBACK_COMPLETED) {
                this.n.stop();
            }
            this.n.prepareAsync();
            a(true);
            setCurrentState(c.STATE_PREPARING);
        } else if (d()) {
            this.n.start();
            setCurrentState(c.STATE_PLAYING);
        }
        this.l = true;
    }

    public void pause() {
        if (d() && this.n.isPlaying()) {
            this.n.pause();
            setCurrentState(c.STATE_PAUSED);
        }
        this.l = false;
    }

    public String getCurrentPlayingUrl() {
        return this.i != null ? this.i.toString() : null;
    }

    public int getDuration() {
        return d() ? (int) this.n.getDuration() : 0;
    }

    public int getCurrentPosition() {
        return d() ? (int) this.n.getCurrentPosition() : 0;
    }

    public void seekTo(int i) {
        if (d()) {
            this.n.seekTo((long) i);
            a(true);
        }
    }

    public boolean isPlaying() {
        return d() && this.n.isPlaying();
    }

    public int getBufferPercentage() {
        return this.n != null ? this.v : 0;
    }

    public boolean d() {
        return (this.n == null || this.k == c.STATE_ERROR || this.k == c.STATE_IDLE || this.k == c.STATE_PREPARING) ? false : true;
    }

    public boolean canPause() {
        return this.B;
    }

    public boolean canSeekBackward() {
        return this.C;
    }

    public boolean canSeekForward() {
        return this.D;
    }

    public int getVideoWidth() {
        return this.o;
    }

    public int getVideoHeight() {
        return this.p;
    }

    public void setVideoScalingMode(int i) {
        if (i == 1 || i == 2 || i == 3) {
            if (i == 1) {
                this.F = 0;
            } else if (i == 2) {
                this.F = 1;
            } else {
                this.F = 3;
            }
            if (this.mRenderView != null) {
                this.mRenderView.setAspectRatio(this.F);
                return;
            }
            return;
        }
        Log.e("BDCloudVideoView", "setVideoScalingMode: param should be VID");
    }

    public String[] getVariantInfo() {
        return this.n != null ? this.n.getVariantInfo() : null;
    }

    public long getDownloadSpeed() {
        return this.n != null ? this.n.getDownloadSpeed() : 0;
    }

    public Bitmap getBitmap() {
        return this.mRenderView != null ? this.mRenderView.getBitmap() : null;
    }

    public static void setAK(String str) {
        BDCloudMediaPlayer.setAK(str);
    }

    public void e() {
        if (this.mRenderView != null && (this.mRenderView instanceof TextureRenderView)) {
            this.ag.removeView(this.mRenderView.getView());
        }
    }

    public void f() {
        if (this.mRenderView != null && (this.mRenderView instanceof TextureRenderView)) {
            View view = this.mRenderView.getView();
            if (view.getParent() == null) {
                view.setLayoutParams(new LayoutParams(-2, -2, 17));
                this.ag.addView(view);
                return;
            }
            Log.d("BDCloudVideoView", "enterForeground; but getParent() is not null");
        }
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        this.al.a();
        return super.onSingleTapUp(motionEvent);
    }

    public boolean onDown(MotionEvent motionEvent) {
        if (g()) {
            return false;
        }
        return super.onDown(motionEvent);
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (g()) {
            return false;
        }
        return super.onScroll(motionEvent, motionEvent2, f, f2);
    }

    public boolean g() {
        return this.al.d();
    }

    protected void a(int i) {
        switch (i) {
            case 1:
                seekTo(this.am.getTargetProgress());
                this.am.a();
                return;
            case 2:
            case 3:
                this.ak.a();
                return;
            default:
                return;
        }
    }

    protected void b(int i) {
        this.am.a(i, getCurrentPosition(), getDuration());
    }

    protected void a(int i, int i2) {
        this.ak.a(VideoSystemOverlay.SCREEN_SWITCH.VOLUME, i, i2);
    }

    protected void b(int i, int i2) {
        this.ak.a(VideoSystemOverlay.SCREEN_SWITCH.BRIGHTNESS, i, i2);
    }

    public void setOnVideoControlListener(com.boredream.bdvideoplayer.a.a aVar) {
        this.al.setOnVideoControlListener(aVar);
    }
}
