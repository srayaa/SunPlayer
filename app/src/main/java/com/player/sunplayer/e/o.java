package com.player.sunplayer.e;

import com.baidu.cloud.media.download.DownloadableVideoItem;
import com.baidu.cloud.media.download.DownloadableVideoItem.DownloadStatus;
import com.baidu.cloud.media.download.VideoDownloadManager;
import com.liulishuo.filedownloader.FileDownloader;
import com.player.sunplayer.MyApplication;
import com.player.sunplayer.bean.DownloadInfo;
import com.xunlei.downloadlib.XLTaskHelper;
import com.xunlei.downloadlib.parameter.XLTaskInfo;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: TaskInfoUtils */
public class o {
    static HashMap<String, Float> a = new HashMap();
    static HashMap<String, Long> b = new HashMap();
    static FileDownloader c = FileDownloader.getImpl();

    private static int a(byte b) {
        if (b == (byte) 1) {
            return 0;
        }
        if (b == (byte) 3) {
            return 1;
        }
        if (b == (byte) 6) {
            return 0;
        }
        switch (b) {
            case (byte) -3:
                return 2;
            case (byte) -2:
                return -2;
            case (byte) -1:
                return 3;
            default:
                return 0;
        }
    }

    public static XLTaskInfo a(DownloadInfo downloadInfo) {
        XLTaskInfo xLTaskInfo;
        Iterator it = MyApplication.e().iterator();
        while (it.hasNext()) {
            DownloadInfo downloadInfo2 = (DownloadInfo) it.next();
            if (downloadInfo2.equals(downloadInfo)) {
                downloadInfo = downloadInfo2;
                break;
            }
        }
        switch (downloadInfo.getType()) {
            case 1:
                xLTaskInfo = XLTaskHelper.instance(MyApplication.d()).getBtSubTaskInfo(downloadInfo.getId().longValue(), downloadInfo.getIndex()).mTaskInfo;
                break;
            case 2:
                DownloadableVideoItem downloadableVideoItemByUrl = VideoDownloadManager.getInstance(MyApplication.d(), "me").getDownloadableVideoItemByUrl(downloadInfo.getUrl());
                xLTaskInfo = new XLTaskInfo();
                if (downloadableVideoItemByUrl == null) {
                    xLTaskInfo.mTaskStatus = 3;
                    break;
                }
                float progress = downloadableVideoItemByUrl.getProgress();
                xLTaskInfo.mTaskStatus = a(downloadableVideoItemByUrl.getStatus());
                xLTaskInfo.mDownloadSize = (long) ((((float) downloadInfo.getTotalBytes()) * progress) / 100.0f);
                Float f = (Float) a.get(downloadInfo.getUrl());
                if (f == null) {
                    f = Float.valueOf(0.0f);
                }
                xLTaskInfo.mDownloadSpeed = ((long) (((float) downloadInfo.getTotalBytes()) * (progress - f.floatValue()))) / 100;
                a.put(downloadInfo.getUrl(), Float.valueOf(progress));
                xLTaskInfo.mFileSize = downloadInfo.getTotalBytes();
                break;
            default:
                xLTaskInfo = new XLTaskInfo();
                xLTaskInfo.mTaskStatus = a(c.getStatus(downloadInfo.getUrl(), downloadInfo.getPath()));
                xLTaskInfo.mDownloadSize = c.getSoFar(downloadInfo.getId().intValue());
                xLTaskInfo.mFileSize = c.getTotal(downloadInfo.getId().intValue());
                Long l = (Long) b.get(downloadInfo.getUrl());
                if (l == null) {
                    l = Long.valueOf(0);
                }
                xLTaskInfo.mDownloadSpeed = xLTaskInfo.mDownloadSize - l.longValue();
                b.put(downloadInfo.getUrl(), Long.valueOf(xLTaskInfo.mDownloadSize));
                break;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("status:");
        stringBuilder.append(xLTaskInfo.mTaskStatus);
        k.a("TaskInfoUtils", stringBuilder.toString());
        if (xLTaskInfo.mTaskStatus != 3) {
            int i = xLTaskInfo.mTaskStatus;
        }
        return xLTaskInfo;
    }

    public static XLTaskInfo b(DownloadInfo downloadInfo) {
        Iterator it = MyApplication.e().iterator();
        while (it.hasNext()) {
            DownloadInfo downloadInfo2 = (DownloadInfo) it.next();
            if (downloadInfo2.equals(downloadInfo)) {
                downloadInfo = downloadInfo2;
                break;
            }
        }
        XLTaskInfo xLTaskInfo;
        switch (downloadInfo.getType()) {
            case 1:
                xLTaskInfo = XLTaskHelper.instance(MyApplication.d()).getBtSubTaskInfo(downloadInfo.getId().longValue(), downloadInfo.getIndex()).mTaskInfo;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("url:");
                stringBuilder.append(downloadInfo.getUrl());
                stringBuilder.append("    index:");
                stringBuilder.append(downloadInfo.getIndex());
                stringBuilder.append("    id:");
                stringBuilder.append(downloadInfo.getId());
                stringBuilder.append("        _id:");
                stringBuilder.append(downloadInfo.get_id());
                k.a("TaskInfoUtils", stringBuilder.toString());
                return xLTaskInfo;
            case 2:
                DownloadableVideoItem downloadableVideoItemByUrl = VideoDownloadManager.getInstance(MyApplication.d(), "me").getDownloadableVideoItemByUrl(downloadInfo.getUrl());
                xLTaskInfo = new XLTaskInfo();
                if (downloadableVideoItemByUrl != null) {
                    float progress = downloadableVideoItemByUrl.getProgress();
                    xLTaskInfo.mTaskStatus = a(downloadableVideoItemByUrl.getStatus());
                    xLTaskInfo.mDownloadSize = (long) ((((float) downloadInfo.getTotalBytes()) * progress) / 100.0f);
                    xLTaskInfo.mFileSize = downloadInfo.getTotalBytes();
                    return xLTaskInfo;
                }
                xLTaskInfo.mTaskStatus = 3;
                return xLTaskInfo;
            default:
                xLTaskInfo = new XLTaskInfo();
                xLTaskInfo.mTaskStatus = a(c.getStatus(downloadInfo.getUrl(), downloadInfo.getPath()));
                xLTaskInfo.mDownloadSize = c.getSoFar(downloadInfo.getId().intValue());
                xLTaskInfo.mFileSize = c.getTotal(downloadInfo.getId().intValue());
                return xLTaskInfo;
        }
    }

    private static int a(DownloadStatus downloadStatus) {
        switch (downloadStatus) {
            case DOWNLOADING:
                return 1;
            case COMPLETED:
                return 2;
            case PAUSED:
                return -2;
            case ERROR:
                return 3;
            default:
                return 0;
        }
    }
}
