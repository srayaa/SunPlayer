package com.player.sunplayer.a;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.player.sunplayer.R;
import com.player.sunplayer.bean.MagnetInfo;
import com.player.sunplayer.bean.MagnetInfoList;
import com.player.sunplayer.e.f;

import java.util.List;

/* compiled from: MagnetDetailAdapter */
public class MagnetDetailAdapter extends BaseAdapter {
    private List<MagnetInfo> a;
    private Activity b;
    private LayoutInflater c;
    private String d;
    private String e;
    private String strf;
    private a g;

    /* compiled from: MagnetDetailAdapter */
    public interface a {
        void a(MagnetInfo magnetInfo, int i);

        void b(MagnetInfo magnetInfo, int i);
    }

    /* compiled from: MagnetDetailAdapter */
    public static class b {
        TextView a;
        TextView b;
        CardView c;
        CardView d;

        private b(View view) {
            this.a = (TextView) view.findViewById(R.id.tv_magnet_name);
            this.b = (TextView) view.findViewById(R.id.tv_magnet_size);
            this.c = (CardView) view.findViewById(R.id.iv_magnet_download);
            this.d = (CardView) view.findViewById(R.id.card_detail);
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public void a(a aVar) {
        this.g = aVar;
    }

    public MagnetDetailAdapter(Activity activity, MagnetInfoList magnetInfoList) {
        this.a = magnetInfoList.getInfolist();
        this.b = activity;
        this.d = magnetInfoList.getHash();
        this.strf = magnetInfoList.getName();
        if (TextUtils.isEmpty(this.strf)) {
            this.strf = this.d;
        }
        this.e = magnetInfoList.getTorrentPath();
        this.c = LayoutInflater.from(activity);
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.c.inflate(R.layout.list_magnet_detail, viewGroup, false);
        }
        b a = a(view);
        final MagnetInfo magnetInfo = (MagnetInfo) this.a.get(i);
        a.a.setText(magnetInfo.getName());
        a.b.setText(f.b(magnetInfo.getFile_size()));
        if (this.g != null) {
            a.c.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    MagnetDetailAdapter.this.g.b(magnetInfo, i);
                }
            });
            a.d.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    MagnetDetailAdapter.this.g.a(magnetInfo, i);
                }
            });
        }
        return view;
    }

    private b a(View view) {
        b bVar = (b) view.getTag();
        if (bVar != null) {
            return bVar;
        }
        bVar = new b(view);
        view.setTag(bVar);
        return bVar;
    }
}
