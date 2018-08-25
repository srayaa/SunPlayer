package com.player.sunplayer.bean;

import android.text.TextUtils;

import com.player.sunplayer.e.DownloadUtil;
import java.io.Serializable;

public class DownloadInfo implements Serializable {
    private static final long serialVersionUID = 7247714666080613254L;
    private Long _id;
    private String btDirectory;
    private String cookie;
    private Long id;
    private int index;
    private String name;
    private String path;
    private long soFarBytes;
    private int speed;
    private int state = -2;
    private long totalBytes;
    private int type;
    private String url;

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public String getBtDirectory() {
        if (TextUtils.isEmpty(this.btDirectory)) {
            return DownloadUtil.a;
        }
        return this.btDirectory;
    }

    public void setBtDirectory(String str) {
        this.btDirectory = str;
    }

    public String getCookie() {
        return this.cookie;
    }

    public void setCookie(String str) {
        this.cookie = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public DownloadInfo(int i, String str, String str2) {
        this.type = i;
        this.name = str2;
        this.url = str;
    }

    public DownloadInfo(int i, String str) {
        this.type = i;
        this.url = str;
    }

    public DownloadInfo(int i, int i2, Long l, Long l2, int i3, long j, long j2, String str, String str2, String str3, String str4, String str5) {
        this.type = i;
        this.index = i2;
        this.id = l;
        this._id = l2;
        this.state = i3;
        this.totalBytes = j;
        this.soFarBytes = j2;
        this.path = str;
        this.btDirectory = str2;
        this.name = str3;
        this.url = str4;
        this.cookie = str5;
    }

    public DownloadInfo(int i, Long l, String str, int i2, String str2, String str3, long j) {
        this.type = i;
        this.index = i2;
        this.id = l;
        this.totalBytes = j;
        this.path = str2;
        this.name = str3;
        this.url = str;
    }

    public DownloadInfo(int i, Long l, String str, int i2, String str2, String str3, String str4) {
        this.type = i;
        this.index = i2;
        this.id = l;
        this.path = str3;
        this.name = str4;
        this.url = str;
        this.btDirectory = str2;
    }

    public DownloadInfo(int i, Long l, String str, String str2, String str3, String str4) {
        this.type = i;
        this.cookie = str4;
        this.id = l;
        this.path = str2;
        this.name = str3;
        this.url = str;
    }

    public DownloadInfo(int i, Long l, String str, int i2, String str2) {
        this.type = i;
        this.index = i2;
        this.id = l;
        this.path = str2;
        this.url = str;
    }

    public long getSoFarBytes() {
        return this.soFarBytes;
    }

    public void setSoFarBytes(long j) {
        this.soFarBytes = j;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int i) {
        this.speed = i;
    }

    public int getState() {
        return this.state;
    }

    public long getTotalBytes() {
        return this.totalBytes;
    }

    public void setTotalBytes(long j) {
        this.totalBytes = j;
    }

    public void setState(int i) {
        this.state = i;
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long l) {
        this._id = l;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DownloadInfo)) {
            return super.equals(obj);
        }
        DownloadInfo downloadInfo = (DownloadInfo) obj;
        if (!(this.url.equals(downloadInfo.getUrl()) && this.index == downloadInfo.getIndex())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return this.url.hashCode();
    }
}
