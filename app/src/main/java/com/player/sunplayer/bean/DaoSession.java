package com.player.sunplayer.bean;

import java.util.Map;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

public class DaoSession extends AbstractDaoSession {
    private final DownloadInfoDao downloadInfoDao = new DownloadInfoDao(this.downloadInfoDaoConfig, this);
    private DaoConfig downloadInfoDaoConfig;

    public DaoSession(Database database, IdentityScopeType identityScopeType, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> map) {
        super(database);
        this.downloadInfoDaoConfig = ((DaoConfig) map.get(DownloadInfoDao.class)).clone();
        this.downloadInfoDaoConfig.initIdentityScope(identityScopeType);
        registerDao(DownloadInfo.class, this.downloadInfoDao);
    }

    public void clear() {
        this.downloadInfoDaoConfig.clearIdentityScope();
    }

    public DownloadInfoDao getDownloadInfoDao() {
        return this.downloadInfoDao;
    }
}
