package com.player.sunplayer.gen;

import com.player.sunplayer.MyApplication;

/* compiled from: GreenDaoManager */
public class GreenDaoManager {
    private DaoMaster a;
    private DaoSession b;

    /* compiled from: GreenDaoManager */
    private static class ab {
        private static final GreenDaoManager a = new GreenDaoManager();
    }

    private GreenDaoManager() {
        c();
    }

    public static GreenDaoManager a() {
        return ab.a;
    }

    private void c() {
        this.a = new DaoMaster(new com.player.sunplayer.gen.DaoMaster.a(MyApplication.f(), "shopping_guide").getWritableDatabase());
        this.b = this.a.a();
    }

    public DaoSession b() {
        return this.b;
    }
}
