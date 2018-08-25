package com.player.sunplayer.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* compiled from: BaseFragment */
public abstract class BaseFragment extends Fragment {
    public com.player.sunplayer.activity.BaseActivity a;
    private View b;

    public abstract View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    protected void a() {
    }

    protected abstract void b();

    public abstract void c();

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.b = a(layoutInflater, viewGroup, bundle);
        this.a = (com.player.sunplayer.activity.BaseActivity) getActivity();
        return this.b;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        b();
        c();
        a();
    }

    protected View a(int i) {
        return this.b.findViewById(i);
    }

    protected void a(String str) {
        Snackbar.make(this.b, (CharSequence) str, -1).show();
    }
}
