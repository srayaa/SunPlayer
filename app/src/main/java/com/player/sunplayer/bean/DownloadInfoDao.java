package com.player.sunplayer.bean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class DownloadInfoDao extends AbstractDao<DownloadInfo, Long> {
    public static final String TABLENAME = "DOWNLOAD_INFO";

    public static class Properties {
        public static final Property BtDirectory = new Property(8, String.class, "btDirectory", false, "BT_DIRECTORY");
        public static final Property Cookie = new Property(11, String.class, "cookie", false, "COOKIE");
        public static final Property Id = new Property(2, Long.class, "id", false, "ID");
        public static final Property Index = new Property(1, Integer.TYPE, "index", false, "INDEX");
        public static final Property Name = new Property(9, String.class, "name", false, "NAME");
        public static final Property Path = new Property(7, String.class, "path", false, "PATH");
        public static final Property SoFarBytes = new Property(6, Long.TYPE, "soFarBytes", false, "SO_FAR_BYTES");
        public static final Property State = new Property(4, Integer.TYPE, "state", false, "STATE");
        public static final Property TotalBytes = new Property(5, Long.TYPE, "totalBytes", false, "TOTAL_BYTES");
        public static final Property Type = new Property(0, Integer.TYPE, "type", false, "TYPE");
        public static final Property Url = new Property(10, String.class, "url", false, "URL");
        public static final Property _id = new Property(3, Long.class, "_id", true, "_id");
    }

    protected final boolean isEntityUpdateable() {
        return true;
    }

    public DownloadInfoDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DownloadInfoDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE ");
        stringBuilder.append(str);
        stringBuilder.append("\"DOWNLOAD_INFO\" (\"TYPE\" INTEGER NOT NULL ,\"INDEX\" INTEGER NOT NULL ,\"ID\" INTEGER,\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"STATE\" INTEGER NOT NULL ,\"TOTAL_BYTES\" INTEGER NOT NULL ,\"SO_FAR_BYTES\" INTEGER NOT NULL ,\"PATH\" TEXT,\"BT_DIRECTORY\" TEXT,\"NAME\" TEXT,\"URL\" TEXT,\"COOKIE\" TEXT);");
        database.execSQL(stringBuilder.toString());
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DROP TABLE ");
        stringBuilder.append(z ? "IF EXISTS " : "");
        stringBuilder.append("\"DOWNLOAD_INFO\"");
        database.execSQL(stringBuilder.toString());
    }

    protected final void bindValues(DatabaseStatement databaseStatement, DownloadInfo downloadInfo) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, (long) downloadInfo.getType());
        databaseStatement.bindLong(2, (long) downloadInfo.getIndex());
        Long id = downloadInfo.getId();
        if (id != null) {
            databaseStatement.bindLong(3, id.longValue());
        }
        id = downloadInfo.get_id();
        if (id != null) {
            databaseStatement.bindLong(4, id.longValue());
        }
        databaseStatement.bindLong(5, (long) downloadInfo.getState());
        databaseStatement.bindLong(6, downloadInfo.getTotalBytes());
        databaseStatement.bindLong(7, downloadInfo.getSoFarBytes());
        String path = downloadInfo.getPath();
        if (path != null) {
            databaseStatement.bindString(8, path);
        }
        path = downloadInfo.getBtDirectory();
        if (path != null) {
            databaseStatement.bindString(9, path);
        }
        path = downloadInfo.getName();
        if (path != null) {
            databaseStatement.bindString(10, path);
        }
        path = downloadInfo.getUrl();
        if (path != null) {
            databaseStatement.bindString(11, path);
        }
        String cookie = downloadInfo.getCookie();
        if (cookie != null) {
            databaseStatement.bindString(12, cookie);
        }
    }

    protected final void bindValues(SQLiteStatement sQLiteStatement, DownloadInfo downloadInfo) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, (long) downloadInfo.getType());
        sQLiteStatement.bindLong(2, (long) downloadInfo.getIndex());
        Long id = downloadInfo.getId();
        if (id != null) {
            sQLiteStatement.bindLong(3, id.longValue());
        }
        id = downloadInfo.get_id();
        if (id != null) {
            sQLiteStatement.bindLong(4, id.longValue());
        }
        sQLiteStatement.bindLong(5, (long) downloadInfo.getState());
        sQLiteStatement.bindLong(6, downloadInfo.getTotalBytes());
        sQLiteStatement.bindLong(7, downloadInfo.getSoFarBytes());
        String path = downloadInfo.getPath();
        if (path != null) {
            sQLiteStatement.bindString(8, path);
        }
        path = downloadInfo.getBtDirectory();
        if (path != null) {
            sQLiteStatement.bindString(9, path);
        }
        path = downloadInfo.getName();
        if (path != null) {
            sQLiteStatement.bindString(10, path);
        }
        path = downloadInfo.getUrl();
        if (path != null) {
            sQLiteStatement.bindString(11, path);
        }
        String cookie = downloadInfo.getCookie();
        if (cookie != null) {
            sQLiteStatement.bindString(12, cookie);
        }
    }

    public Long readKey(Cursor cursor, int i) {
        i += 3;
        return cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
    }

    public DownloadInfo readEntity(Cursor cursor, int i) {
        Cursor cursor2 = cursor;
        int i2 = i + 2;
        int i3 = i + 3;
        int i4 = i + 7;
        int i5 = i + 8;
        int i6 = i + 9;
        int i7 = i + 10;
        int i8 = i + 11;
        return new DownloadInfo(cursor2.getInt(i + 0), cursor2.getInt(i + 1), cursor2.isNull(i2) ? null : Long.valueOf(cursor2.getLong(i2)), cursor2.isNull(i3) ? null : Long.valueOf(cursor2.getLong(i3)), cursor2.getInt(i + 4), cursor2.getLong(i + 5), cursor2.getLong(i + 6), cursor2.isNull(i4) ? null : cursor2.getString(i4), cursor2.isNull(i5) ? null : cursor2.getString(i5), cursor2.isNull(i6) ? null : cursor2.getString(i6), cursor2.isNull(i7) ? null : cursor2.getString(i7), cursor2.isNull(i8) ? null : cursor2.getString(i8));
    }

    public void readEntity(Cursor cursor, DownloadInfo downloadInfo, int i) {
        downloadInfo.setType(cursor.getInt(i + 0));
        downloadInfo.setIndex(cursor.getInt(i + 1));
        int i2 = i + 2;
        String str = null;
        downloadInfo.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        i2 = i + 3;
        downloadInfo.set_id(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        downloadInfo.setState(cursor.getInt(i + 4));
        downloadInfo.setTotalBytes(cursor.getLong(i + 5));
        downloadInfo.setSoFarBytes(cursor.getLong(i + 6));
        i2 = i + 7;
        downloadInfo.setPath(cursor.isNull(i2) ? null : cursor.getString(i2));
        i2 = i + 8;
        downloadInfo.setBtDirectory(cursor.isNull(i2) ? null : cursor.getString(i2));
        i2 = i + 9;
        downloadInfo.setName(cursor.isNull(i2) ? null : cursor.getString(i2));
        i2 = i + 10;
        downloadInfo.setUrl(cursor.isNull(i2) ? null : cursor.getString(i2));
        i += 11;
        if (!cursor.isNull(i)) {
            str = cursor.getString(i);
        }
        downloadInfo.setCookie(str);
    }

    protected final Long updateKeyAfterInsert(DownloadInfo downloadInfo, long j) {
        downloadInfo.set_id(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DownloadInfo downloadInfo) {
        return downloadInfo != null ? downloadInfo.get_id() : null;
    }

    public boolean hasKey(DownloadInfo downloadInfo) {
        return downloadInfo.get_id() != null;
    }
}
