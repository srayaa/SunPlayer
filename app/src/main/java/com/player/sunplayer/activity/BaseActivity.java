package com.player.sunplayer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.player.sunplayer.R;

/* compiled from: BaseActivity */
public class BaseActivity extends AppCompatActivity {

    protected void onResume() {
        super.onResume();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    protected void onPause() {
        super.onPause();
    }

    protected void c(int i) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(i);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon((int) R.mipmap.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BaseActivity.this.finish();
            }
        });
    }

    protected void a(String str) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle((CharSequence) str);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon((int) R.mipmap.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BaseActivity.this.finish();
            }
        });
    }

    protected void a(Class cls) {
        startActivity(new Intent(this, cls));
    }

    protected void b(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    protected void d(int i) {
        Toast.makeText(this, i, Toast.LENGTH_SHORT).show();
    }
}
