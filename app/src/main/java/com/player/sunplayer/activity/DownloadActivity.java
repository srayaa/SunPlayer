package com.player.sunplayer.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.liulishuo.filedownloader.FileDownloadConnectListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.player.sunplayer.MyApplication;
import com.player.sunplayer.R;
import com.player.sunplayer.a.DownloadPagerAdapter;
import com.player.sunplayer.bean.DownloadInfo;
import com.player.sunplayer.c.DownloadInfoDaoUtils;
import com.player.sunplayer.e.DownloadUtil;
import com.player.sunplayer.e.d;
import com.xunlei.downloadlib.XLTaskHelper;
import java.lang.ref.WeakReference;
import java.util.HashSet;

public class DownloadActivity extends BaseActivity {
    Handler o = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 0 && DownloadActivity.this.isVisiable) {
                DownloadActivity.this.r();
                DownloadActivity.this.o.sendMessageDelayed(DownloadActivity.this.o.obtainMessage(0), 1000);
            }
        }
    };
    private DownloadPagerAdapter p;
    private boolean isVisiable;
    private FileDownloadConnectListener r;

    private void o() {
    }

    private void q() {
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FileDownloader.setup(getApplicationContext());
        if (!FileDownloader.getImpl().isServiceConnected()) {
            FileDownloader.getImpl().bindService();
            a(new WeakReference(this));
        }
        setContentView((int) R.layout.activity_download);
        o();
        c(R.string.download_manager);
        n();
        q();
        XLTaskHelper.init(getApplicationContext());
        ((TextView) findViewById(R.id.tv_download_message)).setText(getString(R.string.download_document));
    }

    private void n() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        this.p = new DownloadPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(this.p);
        tabLayout.setTabMode(1);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_download, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.deleteAll) {
            p();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void p() {
        d.a(this, R.string.warn_download_delete_all, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    for (DownloadInfo e : DownloadInfoDaoUtils.a()) {
                        DownloadUtil.e(e);
                    }
                    FileDownloader.getImpl().clearAllTaskData();
                    DownloadInfoDaoUtils.b();
                    com.player.sunplayer.fragment.DownloadFragment bVar = (com.player.sunplayer.fragment.DownloadFragment) DownloadActivity.this.p.getItem(0);
                    ((com.player.sunplayer.fragment.DownloadedFragment) DownloadActivity.this.p.getItem(1)).onResume();
                    bVar.onResume();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    protected void onResume() {
        super.onResume();
        this.isVisiable = true;
        this.o.sendMessageDelayed(this.o.obtainMessage(0), 1000);
    }

    private void r() {
        HashSet e = MyApplication.e();
        DownloadInfo[] downloadInfoArr = (DownloadInfo[]) e.toArray(new DownloadInfo[e.size()]);
        int i = 0;
        com.player.sunplayer.fragment.DownloadFragment bVar = (com.player.sunplayer.fragment.DownloadFragment) this.p.getItem(0);
        int length = downloadInfoArr.length;
        while (i < length) {
            bVar.a(downloadInfoArr[i]);
            i++;
        }
    }

    protected void onPause() {
        super.onPause();
        this.isVisiable = false;
    }

    protected void onDestroy() {
        super.onDestroy();
        s();
    }

    private void a(final WeakReference<DownloadActivity> weakReference) {
        if (this.r != null) {
            FileDownloader.getImpl().removeServiceConnectListener(this.r);
        }
        this.r = new FileDownloadConnectListener() {
            @Override
            public void connected() {
                if (weakReference != null && weakReference.get() != null) {
                    ((DownloadActivity) weakReference.get()).m();
                }
            }

            @Override
            public void disconnected() {
                if (weakReference != null && weakReference.get() != null) {
                    ((DownloadActivity) weakReference.get()).m();
                }
            }
        };
        FileDownloader.getImpl().addServiceConnectListener(this.r);
    }

    public void m() {
        runOnUiThread(new Runnable() {
            public void run() {
                ((com.player.sunplayer.fragment.DownloadFragment) DownloadActivity.this.p.getItem(0)).d();
            }
        });
    }

    private void s() {
        FileDownloader.getImpl().removeServiceConnectListener(this.r);
        this.r = null;
    }
}
