package com.player.sunplayer.b;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/* compiled from: ADBusProvider */
public class ADBusProvider {
    private static final Bus a = new Bus(ThreadEnforcer.MAIN);

    public static Bus a() {
        return a;
    }
}
