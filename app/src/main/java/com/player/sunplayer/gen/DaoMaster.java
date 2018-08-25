package com.player.sunplayer.gen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.identityscope.IdentityScopeType;

/* compiled from: DaoMaster */
public class DaoMaster extends AbstractDaoMaster {

    /* compiled from: DaoMaster */
    public static abstract class b extends DatabaseOpenHelper {
        public b(Context context, String str) {
            super(context, str, 1);
        }

        public void onCreate(Database database) {
            Log.i("greenDAO", "Creating tables for schema version 1");
            DaoMaster.a(database, false);
        }
    }

    /* compiled from: DaoMaster */
    public static class a extends b {
        public a(Context context, String str) {
            super(context, str);
        }

        public void onUpgrade(Database database, int i, int i2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Upgrading schema from version ");
            stringBuilder.append(i);
            stringBuilder.append(" to ");
            stringBuilder.append(i2);
            stringBuilder.append(" by dropping all tables");
            Log.i("greenDAO", stringBuilder.toString());
            DaoMaster.b(database, true);
            onCreate(database);
        }
    }

    public /* synthetic */ AbstractDaoSession newSession() {
        return a();
    }

    public /* synthetic */ AbstractDaoSession newSession(IdentityScopeType identityScopeType) {
        return a(identityScopeType);
    }

    public static void a(Database database, boolean z) {
        DownloadInfoDao.a(database, z);
    }

    public static void b(Database database, boolean z) {
        DownloadInfoDao.b(database, z);
    }

    public DaoMaster(SQLiteDatabase sQLiteDatabase) {
        this(new StandardDatabase(sQLiteDatabase));
    }

    public DaoMaster(Database database) {
        super(database, 1);
        registerDaoClass(DownloadInfoDao.class);
    }

    public DaoSession a() {
        return new DaoSession(this.db, IdentityScopeType.Session, this.daoConfigMap);
    }

    public DaoSession a(IdentityScopeType identityScopeType) {
        return new DaoSession(this.db, identityScopeType, this.daoConfigMap);
    }
}
