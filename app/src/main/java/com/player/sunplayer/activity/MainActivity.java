package com.player.sunplayer.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.player.sunplayer.MyApplication;
import com.player.sunplayer.R;
import com.player.sunplayer.e.DownloadUtil;
import com.player.sunplayer.fragment.WebFragment;
import com.xunlei.downloadlib.XLTaskHelper;
import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    String[] p = new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
    private MenuItem q;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.app_bar_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        n();
        XLTaskHelper.init(getApplicationContext());
        checkStoragePermission();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        EasyPermissions.onRequestPermissionsResult(i, strArr, iArr, this);
    }

    @AfterPermissionGranted(100)
    private void checkStoragePermission() {
        if (!EasyPermissions.hasPermissions((Context) this, this.p)) {
            EasyPermissions.requestPermissions((Activity) this, getString(R.string.need_per), 100, this.p);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int i, List<String> list) {
        if (EasyPermissions.somePermissionPermanentlyDenied((Activity) this, (List) list)) {
            new AppSettingsDialog.Builder(this).setTitle(R.string.permission_title).setRationale(R.string.permission_text).build().show();
        } else if (!EasyPermissions.hasPermissions((Context) this, this.p)) {
            new AppSettingsDialog.Builder(this).setTitle(R.string.permission_title).setRationale(R.string.permission_text).build().show();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 16061 && !EasyPermissions.hasPermissions((Context) this, this.p)) {
            finish();
        }
    }

    private void n() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        viewPager.setOffscreenPageLimit(2);
        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        viewPager.setAdapter(new com.player.sunplayer.a.MainPagerAdapter(getSupportFragmentManager()));
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.file) {
                    viewPager.setCurrentItem(1);
                } else if (itemId == R.id.web) {
                    viewPager.setCurrentItem(0);
                }
                return false;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                if (MainActivity.this.q != null) {
                    MainActivity.this.q.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                MainActivity.this.q = bottomNavigationView.getMenu().getItem(i);
                MainActivity.this.q.setChecked(true);
            }
        });
    }

    protected void onNewIntent(Intent intent) {
        ((WebFragment) com.player.sunplayer.fragment.MainFragmentFactory.a(0)).a(intent);
        super.onNewIntent(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.record) {
            a(HistoryActivity.class);
        } else if (itemId == R.id.add_bt) {
            a(OpenBtActivity.class);
        } else if (itemId == R.id.open_file) {
            a(OpenFileActivity.class);
        } else if (itemId == R.id.rate_app) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("market://details?id=");
            stringBuilder.append(getPackageName());
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString()));
            intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
            startActivity(intent);
        } else if (itemId == R.id.website) {
            Intent intent2 = new Intent();
            intent2.setAction("android.intent.action.VIEW");
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("https://play.google.com/store/apps/details?id=");
            stringBuilder2.append(getPackageName());
            intent2.setData(Uri.parse(stringBuilder2.toString()));
            startActivity(intent2);
        } else if (itemId == R.id.download) {
            a(DownloadActivity.class);
        } else if (itemId == R.id.vip) {
        }
        return true;
    }

    protected void onDestroy() {
        super.onDestroy();
        com.player.sunplayer.fragment.MainFragmentFactory.a();
        File file = new File(DownloadUtil.c);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length > 2) {
                XLTaskHelper.instance(getApplicationContext()).deleteDir(DownloadUtil.c);
            }
        }
    }

    private void o() {
        try {
            URLConnection openConnection = new URL("http://www.baidu.com").openConnection();
            openConnection.connect();
            MyApplication.b(openConnection.getDate());
            if (MyApplication.c()) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        com.player.sunplayer.b.ADBusProvider.a().post((Object) "s");
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
