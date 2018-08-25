package com.player.sunplayer.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.player.sunplayer.R;
import com.player.sunplayer.a.MagnetDetailAdapter;
import com.player.sunplayer.bean.MagnetInfo;
import com.player.sunplayer.bean.MagnetInfoList;

public class MagnetDetailsActivity extends BaseActivity implements MagnetDetailAdapter.a {
    private ListView o;
    private MagnetInfoList p;
    private MagnetInfo q;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_list_magnet);
        this.p = (MagnetInfoList) getIntent().getSerializableExtra("magnets");
        if (this.p == null) {
            this.p = (MagnetInfoList) bundle.getSerializable("magnets");
        }
        a(this.p);
        c(R.string.file_list);
    }

    private void a(MagnetInfoList magnetInfoList) {
        this.o = (ListView) findViewById(R.id.ll_details);
        MagnetDetailAdapter hVar = new MagnetDetailAdapter(this, magnetInfoList);
        hVar.a((MagnetDetailAdapter.a) this);
        this.o.setAdapter(hVar);
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.p != null) {
            bundle.putSerializable("magnets", this.p);
        }
    }

    @Override
    public void a(MagnetInfo magnetInfo, int i) {

    }

    @Override
    public void b(MagnetInfo magnetInfo, int i) {

    }
}
