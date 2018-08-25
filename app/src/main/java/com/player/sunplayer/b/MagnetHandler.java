package com.player.sunplayer.b;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;
import com.player.sunplayer.R;
import com.player.sunplayer.MyApplication;
import com.player.sunplayer.e.TorrentUtils;
import com.player.sunplayer.e.k;
import com.xunlei.downloadlib.XLTaskHelper;
import com.xunlei.downloadlib.parameter.XLTaskInfo;

/* compiled from: MagnetHandler */
public class MagnetHandler extends Handler {
    private final ProgressDialog a;
    private int b = 0;
    private Activity c;
    private String d;
    private Context e;

    public MagnetHandler(Activity activity, Looper looper, String str) {
        super(looper);
        this.c = activity;
        this.d = str;
        this.e = activity.getApplicationContext();
        this.a = new ProgressDialog(activity);
        this.a.setMessage(activity.getString(R.string.parsing));
        this.a.setCancelable(false);
        this.a.show();
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 0) {
            long longValue = ((Long) message.obj).longValue();
            this.b++;
            if (this.b > 10) {
                a(longValue);
                return;
            }
            XLTaskInfo taskInfo = XLTaskHelper.instance(MyApplication.d()).getTaskInfo(longValue);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(taskInfo.mTaskStatus);
            k.a(stringBuilder.toString());
            if (taskInfo.mTaskStatus == 2) {
                if (!this.c.isFinishing() && this.a.isShowing()) {
                    try {
                        this.a.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                TorrentUtils.a(this.c, this.d);
            } else {
                sendMessageDelayed(obtainMessage(0, Long.valueOf(longValue)), 1000);
            }
        }
    }

    private void a(long j) {
        try {
            XLTaskHelper.instance(this.e).stopTask(j);
            if (!this.c.isFinishing() && this.a.isShowing()) {
                Toast.makeText(this.c, R.string.parse_failed, Toast.LENGTH_SHORT).show();
                this.a.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
