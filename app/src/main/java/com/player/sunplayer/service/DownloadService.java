package com.player.sunplayer.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import com.player.sunplayer.MyApplication;
import com.player.sunplayer.bean.DownloadInfo;
import com.player.sunplayer.c.DownloadInfoDaoUtils;
import com.player.sunplayer.e.DownloadUtil;
import com.player.sunplayer.e.o;
import com.xunlei.downloadlib.parameter.XLTaskInfo;
import java.util.HashSet;
import java.util.Iterator;

public class DownloadService extends Service {
    Handler a = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1 && DownloadService.this.a()) {
                DownloadService.this.a.sendMessageDelayed(DownloadService.this.a.obtainMessage(1), 5000);
            }
        }
    };

    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        this.a.removeCallbacksAndMessages(null);
        this.a.sendMessageDelayed(this.a.obtainMessage(1), 5000);
        return super.onStartCommand(intent, i, i2);
    }

    private boolean a() {
        HashSet e = MyApplication.e();
        if (e.size() == 0) {
            return false;
        }
        try {
            Iterator it = e.iterator();
            while (it.hasNext()) {
                DownloadInfo downloadInfo = (DownloadInfo) it.next();
                XLTaskInfo b = o.b(downloadInfo);
                if (b.mTaskStatus == 2 || b.mTaskStatus == 3) {
                    DownloadInfoDaoUtils.a(downloadInfo.get_id().longValue(), b.mTaskStatus, b.mDownloadSize, b.mFileSize);
                    it.remove();
                    if (downloadInfo.getType() == 1) {
                        DownloadUtil.b(downloadInfo);
                    }
                } else {
                    DownloadInfoDaoUtils.a(downloadInfo.get_id().longValue(), b.mDownloadSize, b.mFileSize);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return true;
    }
}
