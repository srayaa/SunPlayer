package com.player.sunplayer;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ChangedPackages;
import android.content.pm.FeatureInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.SharedLibraryInfo;
import android.content.pm.VersionedPackage;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.UserHandle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.util.List;

/* compiled from: DelegateApplicationPackageManager */
public class DelegateApplicationPackageManager extends PackageManager {
    PackageManager a;

    public boolean canRequestPackageInstalls() {
        return true;
    }

    public void clearInstantAppCookie() {
    }

    public ChangedPackages getChangedPackages(int i) {
        return null;
    }

    public int getInstantAppCookieMaxBytes() {
        return 0;
    }

    public List<SharedLibraryInfo> getSharedLibraries(int i) {
        return null;
    }

    public boolean isInstantApp() {
        return true;
    }

    public boolean isInstantApp(String str) {
        return true;
    }

    public void setApplicationCategoryHint(String str, int i) {
    }

    public void updateInstantAppCookie(byte[] bArr) {
    }

    public DelegateApplicationPackageManager(PackageManager packageManager) {
        this.a = packageManager;
    }

    public PackageInfo getPackageInfo(String str, int i) throws NameNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getPackageInfo() :");
        stringBuilder.append(str);
        Log.w("DelegateApplicationPack", stringBuilder.toString());
        PackageInfo packageInfo = this.a.getPackageInfo("com.iiplayer.sunplayer", i);
        packageInfo.applicationInfo.packageName = str;
        packageInfo.packageName = str;
        return packageInfo;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public PackageInfo getPackageInfo(VersionedPackage versionedPackage, int i) throws NameNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getPackageInfo() 2:");
        stringBuilder.append(versionedPackage.getPackageName());
        Log.w("DelegateApplicationPack", stringBuilder.toString());
        PackageInfo packageInfo = this.a.getPackageInfo("com.iiplayer.sunplayer", i);
        packageInfo.applicationInfo.packageName = versionedPackage.getPackageName();
        packageInfo.packageName = versionedPackage.getPackageName();
        return packageInfo;
    }

    public String[] currentToCanonicalPackageNames(String[] strArr) {
        return this.a.currentToCanonicalPackageNames(strArr);
    }

    public String[] canonicalToCurrentPackageNames(String[] strArr) {
        return this.a.canonicalToCurrentPackageNames(strArr);
    }

    public Intent getLaunchIntentForPackage(String str) {
        Log.w("DelegateApplicationPack", "getLaunchIntentForPackage() ");
        return this.a.getLaunchIntentForPackage(str);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Intent getLeanbackLaunchIntentForPackage(String str) {
        Log.w("DelegateApplicationPack", "getLeanbackLaunchIntentForPackage() ");
        return this.a.getLeanbackLaunchIntentForPackage(str);
    }

    public int[] getPackageGids(String str) throws NameNotFoundException {
        Log.w("DelegateApplicationPack", "getPackageGids() ");
        return getPackageGids(str, 0);
    }

    public int[] getPackageGids(String str, int i) throws NameNotFoundException {
        Log.w("DelegateApplicationPack", "getPackageGids() ");
        return getPackageGids(str, i);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getPackageUid(String str, int i) throws NameNotFoundException {
        Log.w("DelegateApplicationPack", "getPackageUid() ");
        return this.a.getPackageUid(str, i);
    }

    public PermissionInfo getPermissionInfo(String str, int i) throws NameNotFoundException {
        Log.w("DelegateApplicationPack", "getPermissionInfo() ");
        return this.a.getPermissionInfo(str, i);
    }

    public List<PermissionInfo> queryPermissionsByGroup(String str, int i) throws NameNotFoundException {
        return this.a.queryPermissionsByGroup(str, i);
    }

    public PermissionGroupInfo getPermissionGroupInfo(String str, int i) throws NameNotFoundException {
        return this.a.getPermissionGroupInfo(str, i);
    }

    public List<PermissionGroupInfo> getAllPermissionGroups(int i) {
        return this.a.getAllPermissionGroups(i);
    }

    public ApplicationInfo getApplicationInfo(String str, int i) throws NameNotFoundException {
        Log.w("DelegateApplicationPack", "getApplicationInfo() ");
        if ("com.xunlei.downloadprovider".equals(str)) {
            str = "com.iiplayer.sunplayer";
        }
        ApplicationInfo applicationInfo = this.a.getApplicationInfo(str, i);
        Bundle bundle = applicationInfo.metaData;
        if (bundle == null) {
            bundle = new Bundle();
            applicationInfo.metaData = bundle;
        }
        bundle.putString("com.xunlei.download.APP_KEY", "bpIzNjAxNTsxNTA0MDk0ODg4LjQyODAwMAOxNw==^a2cec7^10e7f1756b15519e20ffb6cf0fbf671f");
        return applicationInfo;
    }

    public ActivityInfo getActivityInfo(ComponentName componentName, int i) throws NameNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getActivityInfo() ");
        stringBuilder.append(componentName.getClassName());
        Log.w("DelegateApplicationPack", stringBuilder.toString());
        return this.a.getActivityInfo(componentName, i);
    }

    public ActivityInfo getReceiverInfo(ComponentName componentName, int i) throws NameNotFoundException {
        return this.a.getReceiverInfo(componentName, i);
    }

    public ServiceInfo getServiceInfo(ComponentName componentName, int i) throws NameNotFoundException {
        return this.a.getServiceInfo(componentName, i);
    }

    public ProviderInfo getProviderInfo(ComponentName componentName, int i) throws NameNotFoundException {
        return this.a.getProviderInfo(componentName, i);
    }

    public String[] getSystemSharedLibraryNames() {
        return this.a.getSystemSharedLibraryNames();
    }

    public FeatureInfo[] getSystemAvailableFeatures() {
        return this.a.getSystemAvailableFeatures();
    }

    public boolean hasSystemFeature(String str) {
        return this.a.hasSystemFeature(str);
    }

    @TargetApi(24)
    public boolean hasSystemFeature(String str, int i) {
        return this.a.hasSystemFeature(str, i);
    }

    public int checkPermission(String str, String str2) {
        return this.a.checkPermission(str, str2);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean isPermissionRevokedByPolicy(String str, String str2) {
        Log.w("DelegateApplicationPack", "isPermissionRevokedByPolicy() ");
        return this.a.isPermissionRevokedByPolicy(str, str2);
    }

    public boolean addPermission(PermissionInfo permissionInfo) {
        return this.a.addPermission(permissionInfo);
    }

    public boolean addPermissionAsync(PermissionInfo permissionInfo) {
        return this.a.addPermissionAsync(permissionInfo);
    }

    public void removePermission(String str) {
        this.a.removePermission(str);
    }

    public int checkSignatures(String str, String str2) {
        return this.a.checkSignatures(str, str2);
    }

    public int checkSignatures(int i, int i2) {
        return this.a.checkSignatures(i, i2);
    }

    public String[] getPackagesForUid(int i) {
        return this.a.getPackagesForUid(i);
    }

    public String getNameForUid(int i) {
        return this.a.getNameForUid(i);
    }

    public List<PackageInfo> getInstalledPackages(int i) {
        return this.a.getInstalledPackages(i);
    }

    public List<PackageInfo> getPackagesHoldingPermissions(String[] strArr, int i) {
        return this.a.getPackagesHoldingPermissions(strArr, i);
    }

    public List<ApplicationInfo> getInstalledApplications(int i) {
        return this.a.getInstalledApplications(i);
    }

    public ResolveInfo resolveActivity(Intent intent, int i) {
        ComponentName component = intent.getComponent();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("resolveActivity");
        stringBuilder.append(component.getClassName());
        Log.d("DelegateApplicationPack", stringBuilder.toString());
        intent.setComponent(new ComponentName("com.iiplayer.sunplayer", component.getClassName()));
        intent.setPackage("com.iiplayer.sunplayer");
        return this.a.resolveActivity(intent, i);
    }

    public List<ResolveInfo> queryIntentActivities(Intent intent, int i) {
        return this.a.queryIntentActivities(intent, i);
    }

    public List<ResolveInfo> queryIntentActivityOptions(ComponentName componentName, Intent[] intentArr, Intent intent, int i) {
        return this.a.queryIntentActivityOptions(componentName, intentArr, intent, i);
    }

    public List<ResolveInfo> queryBroadcastReceivers(Intent intent, int i) {
        return this.a.queryBroadcastReceivers(intent, i);
    }

    public ResolveInfo resolveService(Intent intent, int i) {
        ComponentName component = intent.getComponent();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("resolveService");
        stringBuilder.append(component.getClassName());
        Log.d("DelegateApplicationPack", stringBuilder.toString());
        intent.setComponent(new ComponentName("com.iiplayer.sunplayer", component.getClassName()));
        intent.setPackage("com.iiplayer.sunplayer");
        return this.a.resolveService(intent, i);
    }

    public List<ResolveInfo> queryIntentServices(Intent intent, int i) {
        return this.a.queryIntentServices(intent, i);
    }

    public List<ResolveInfo> queryIntentContentProviders(Intent intent, int i) {
        return this.a.queryIntentContentProviders(intent, i);
    }

    public ProviderInfo resolveContentProvider(String str, int i) {
        return this.a.resolveContentProvider(str, i);
    }

    public List<ProviderInfo> queryContentProviders(String str, int i, int i2) {
        return this.a.queryContentProviders(str, i, i2);
    }

    public InstrumentationInfo getInstrumentationInfo(ComponentName componentName, int i) throws NameNotFoundException {
        return this.a.getInstrumentationInfo(componentName, i);
    }

    public List<InstrumentationInfo> queryInstrumentation(String str, int i) {
        return this.a.queryInstrumentation(str, i);
    }

    public Drawable getDrawable(String str, int i, ApplicationInfo applicationInfo) {
        Log.w("DelegateApplicationPack", "getDrawable() ");
        return this.a.getDrawable(str, i, applicationInfo);
    }

    public Drawable getActivityIcon(ComponentName componentName) throws NameNotFoundException {
        return this.a.getActivityIcon(componentName);
    }

    public Drawable getActivityIcon(Intent intent) throws NameNotFoundException {
        if (intent.getComponent() != null) {
            return getActivityIcon(intent.getComponent());
        }
        ResolveInfo resolveActivity = resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveActivity != null) {
            return resolveActivity.activityInfo.loadIcon(this);
        }
        throw new NameNotFoundException(intent.toUri(0));
    }

    public Drawable getDefaultActivityIcon() {
        return this.a.getDefaultActivityIcon();
    }

    public Drawable getApplicationIcon(ApplicationInfo applicationInfo) {
        return applicationInfo.loadIcon(this);
    }

    public Drawable getApplicationIcon(String str) throws NameNotFoundException {
        Log.w("DelegateApplicationPack", "getApplicationIcon() ");
        return this.a.getApplicationIcon(str);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    public Drawable getActivityBanner(ComponentName componentName) throws NameNotFoundException {
        return this.a.getActivityBanner(componentName);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    public Drawable getActivityBanner(Intent intent) throws NameNotFoundException {
        return this.a.getActivityBanner(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    public Drawable getApplicationBanner(ApplicationInfo applicationInfo) {
        return this.a.getApplicationBanner(applicationInfo);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    public Drawable getApplicationBanner(String str) throws NameNotFoundException {
        Log.w("DelegateApplicationPack", "getApplicationBanner() ");
        return this.a.getApplicationBanner(str);
    }

    public Drawable getActivityLogo(ComponentName componentName) throws NameNotFoundException {
        return this.a.getActivityLogo(componentName);
    }

    public Drawable getActivityLogo(Intent intent) throws NameNotFoundException {
        if (intent.getComponent() != null) {
            return getActivityLogo(intent.getComponent());
        }
        ResolveInfo resolveActivity = resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveActivity != null) {
            return resolveActivity.activityInfo.loadLogo(this);
        }
        throw new NameNotFoundException(intent.toUri(0));
    }

    public Drawable getApplicationLogo(ApplicationInfo applicationInfo) {
        return applicationInfo.loadLogo(this);
    }

    public Drawable getApplicationLogo(String str) throws NameNotFoundException {
        Log.w("DelegateApplicationPack", "getApplicationLogo() ");
        return this.a.getApplicationLogo(str);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Drawable getUserBadgedIcon(Drawable drawable, UserHandle userHandle) {
        return this.a.getUserBadgedIcon(drawable, userHandle);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Drawable getUserBadgedDrawableForDensity(Drawable drawable, UserHandle userHandle, Rect rect, int i) {
        return this.a.getUserBadgedDrawableForDensity(drawable, userHandle, rect, i);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CharSequence getUserBadgedLabel(CharSequence charSequence, UserHandle userHandle) {
        return this.a.getUserBadgedLabel(charSequence, userHandle);
    }

    public Resources getResourcesForActivity(ComponentName componentName) throws NameNotFoundException {
        return this.a.getResourcesForActivity(componentName);
    }

    public Resources getResourcesForApplication(ApplicationInfo applicationInfo) throws NameNotFoundException {
        return this.a.getResourcesForApplication(applicationInfo);
    }

    public Resources getResourcesForApplication(String str) throws NameNotFoundException {
        Log.w("DelegateApplicationPack", "getResourcesForApplication() ");
        return this.a.getResourcesForApplication(str);
    }

    public boolean isSafeMode() {
        return this.a.isSafeMode();
    }

    public CharSequence getText(String str, int i, ApplicationInfo applicationInfo) {
        Log.w("DelegateApplicationPack", "getText() ");
        return this.a.getText(str, i, applicationInfo);
    }

    public XmlResourceParser getXml(String str, int i, ApplicationInfo applicationInfo) {
        Log.w("DelegateApplicationPack", "getXml() ");
        return this.a.getXml(str, i, applicationInfo);
    }

    public CharSequence getApplicationLabel(ApplicationInfo applicationInfo) {
        Log.w("DelegateApplicationPack", "getApplicationLabel() ");
        return this.a.getApplicationLabel(applicationInfo);
    }

    public void verifyPendingInstall(int i, int i2) {
        Log.w("DelegateApplicationPack", "verifyPendingInstall() ");
        this.a.verifyPendingInstall(i, i2);
    }

    public void extendVerificationTimeout(int i, int i2, long j) {
        Log.w("DelegateApplicationPack", "extendVerificationTimeout() ");
        this.a.extendVerificationTimeout(i, i2, j);
    }

    public void setInstallerPackageName(String str, String str2) {
        Log.w("DelegateApplicationPack", "setInstallerPackageName() ");
        this.a.setInstallerPackageName(str, str2);
    }

    public String getInstallerPackageName(String str) {
        Log.w("DelegateApplicationPack", "getInstallerPackageName() ");
        return this.a.getInstallerPackageName(str);
    }

    public void addPackageToPreferred(String str) {
        Log.w("DelegateApplicationPack", "addPackageToPreferred() ");
        this.a.addPackageToPreferred(str);
    }

    public void removePackageFromPreferred(String str) {
        Log.w("DelegateApplicationPack", "removePackageFromPreferred() is IRenderView no-op");
        this.a.removePackageFromPreferred(str);
    }

    public List<PackageInfo> getPreferredPackages(int i) {
        Log.w("DelegateApplicationPack", "getPreferredPackages() is IRenderView no-op");
        return this.a.getPreferredPackages(i);
    }

    public void addPreferredActivity(IntentFilter intentFilter, int i, ComponentName[] componentNameArr, ComponentName componentName) {
        Log.w("DelegateApplicationPack", "addPreferredActivity() ");
        this.a.addPreferredActivity(intentFilter, i, componentNameArr, componentName);
    }

    public void clearPackagePreferredActivities(String str) {
        Log.w("DelegateApplicationPack", "clearPackagePreferredActivities() ");
        this.a.clearPackagePreferredActivities(str);
    }

    public int getPreferredActivities(List<IntentFilter> list, List<ComponentName> list2, String str) {
        Log.w("DelegateApplicationPack", "getPreferredActivities() ");
        return this.a.getPreferredActivities(list, list2, str);
    }

    public void setComponentEnabledSetting(ComponentName componentName, int i, int i2) {
        this.a.setComponentEnabledSetting(componentName, i, i2);
    }

    public int getComponentEnabledSetting(ComponentName componentName) {
        return this.a.getComponentEnabledSetting(componentName);
    }

    public void setApplicationEnabledSetting(String str, int i, int i2) {
        Log.w("DelegateApplicationPack", "setApplicationEnabledSetting() ");
        this.a.setApplicationEnabledSetting(str, i, i2);
    }

    public int getApplicationEnabledSetting(String str) {
        Log.w("DelegateApplicationPack", "getApplicationEnabledSetting() ");
        return this.a.getApplicationEnabledSetting(str);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PackageInstaller getPackageInstaller() {
        Log.w("DelegateApplicationPack", "getPackageInstaller() ");
        return this.a.getPackageInstaller();
    }

    public byte[] getInstantAppCookie() {
        return new byte[0];
    }
}
