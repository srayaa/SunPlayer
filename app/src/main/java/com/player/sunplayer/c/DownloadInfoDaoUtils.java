package com.player.sunplayer.c;

import com.player.sunplayer.bean.DownloadInfo;
import com.player.sunplayer.gen.DownloadInfoDao;
import com.player.sunplayer.gen.DownloadInfoDao.Properties;
import com.player.sunplayer.gen.GreenDaoManager;
import java.util.List;
import org.greenrobot.greendao.query.WhereCondition;

/* compiled from: DownloadInfoDaoUtils */
public class DownloadInfoDaoUtils {
    private static DownloadInfoDao a = GreenDaoManager.a().b().a();

    public static List<DownloadInfo> a() {
        return a.loadAll();
    }

    public static long a(DownloadInfo downloadInfo) {
        DownloadInfo downloadInfo2 = (DownloadInfo) a.queryBuilder().where(Properties.k.eq(downloadInfo.getUrl()), new WhereCondition[0]).unique();
        if (downloadInfo2 == null) {
            return a.insert(downloadInfo);
        }
        downloadInfo2.setId(downloadInfo.getId());
        long longValue = downloadInfo2.get_id().longValue();
        a.update(downloadInfo2);
        return longValue;
    }

    public static boolean a(String str, int i) {
        return ((DownloadInfo) a.queryBuilder().where(Properties.k.eq(str), Properties.b.eq(Integer.valueOf(i))).unique()) != null;
    }

    public static boolean a(String str) {
        List list = a.queryBuilder().where(Properties.k.eq(str), new WhereCondition[0]).list();
        if (list == null || list.size() == 0) {
            return false;
        }
        return true;
    }

    public static void a(long j) {
        a.deleteByKey(Long.valueOf(j));
    }

    public static void b() {
        a.deleteAll();
    }

    public static void a(long j, int i, long j2, long j3) {
        DownloadInfo downloadInfo = (DownloadInfo) a.queryBuilder().where(Properties.d.eq(Long.valueOf(j)), new WhereCondition[0]).unique();
        if (downloadInfo != null) {
            downloadInfo.setSoFarBytes(j2);
            downloadInfo.setTotalBytes(j3);
            downloadInfo.setState(i);
            a.update(downloadInfo);
        }
    }

    public static void a(long j, long j2, long j3) {
        DownloadInfo downloadInfo = (DownloadInfo) a.queryBuilder().where(Properties.d.eq(Long.valueOf(j)), new WhereCondition[0]).unique();
        if (downloadInfo != null) {
            downloadInfo.setSoFarBytes(j2);
            downloadInfo.setTotalBytes(j3);
            a.update(downloadInfo);
        }
    }

    public static List<DownloadInfo> c() {
        return a.queryBuilder().where(Properties.e.eq(Integer.valueOf(2)), new WhereCondition[0]).list();
    }

    public static List<DownloadInfo> d() {
        return a.queryBuilder().where(Properties.e.notEq(Integer.valueOf(2)), new WhereCondition[0]).list();
    }

    public static Long b(DownloadInfo downloadInfo) {
        long insert;
        DownloadInfo downloadInfo2 = (DownloadInfo) a.queryBuilder().where(Properties.k.eq(downloadInfo.getUrl()), Properties.b.eq(Integer.valueOf(downloadInfo.getIndex()))).unique();
        if (downloadInfo2 == null) {
            insert = a.insert(downloadInfo);
        } else {
            downloadInfo2.setId(downloadInfo.getId());
            long longValue = downloadInfo2.get_id().longValue();
            a.update(downloadInfo2);
            insert = longValue;
        }
        return Long.valueOf(insert);
    }
}
