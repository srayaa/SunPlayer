package com.player.sunplayer.b;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/* compiled from: PayBusProvider */
public class PayBusProvider {
    private static final Bus a = new Bus(ThreadEnforcer.MAIN);

    public static Bus a() {
        return a;
    }
}
