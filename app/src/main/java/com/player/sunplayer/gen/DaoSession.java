package com.player.sunplayer.gen;

import com.player.sunplayer.bean.DownloadInfo;
import java.util.Map;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

/* compiled from: DaoSession */
public class DaoSession extends AbstractDaoSession {
    private  DaoConfig a;
    private final DownloadInfoDao b = new DownloadInfoDao(this.a, this);

    public DaoSession(Database database, IdentityScopeType identityScopeType, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> map) {
        super(database);
        this.a = ((DaoConfig) map.get(DownloadInfoDao.class)).clone();
        this.a.initIdentityScope(identityScopeType);
        registerDao(DownloadInfo.class, this.b);
    }

    public DownloadInfoDao a() {
        return this.b;
    }
}
