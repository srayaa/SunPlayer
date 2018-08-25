package com.player.sunplayer.filePicker.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import droidninja.filepicker.models.BaseFile;
import java.util.ArrayList;
import java.util.List;

public class MediaDirectory extends BaseFile implements Parcelable {
    public static final Creator<MediaDirectory> CREATOR = new Creator<MediaDirectory>() {
        public /* synthetic */ MediaDirectory createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ MediaDirectory[] newArray(int i) {
            return a(i);
        }

        public MediaDirectory a(Parcel parcel) {
            return new MediaDirectory(parcel);
        }

        public MediaDirectory[] a(int i) {
            return new MediaDirectory[i];
        }
    };
    private String d;
    private String e;
    private String f;
    private long g;
    private List<Media> h = new ArrayList();

    public int describeContents() {
        return 0;
    }

    public MediaDirectory(){}

    protected MediaDirectory(Parcel parcel) {
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readLong();
        parcel.readTypedList(this.h, Media.CREATOR);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaDirectory)) {
            return false;
        }
        MediaDirectory mediaDirectory = (MediaDirectory) obj;
        boolean isEmpty = true ^ TextUtils.isEmpty(mediaDirectory.d);
        if ((TextUtils.isEmpty(this.d) ^ true) || isEmpty || !TextUtils.equals(this.d, mediaDirectory.d)) {
            return false;
        }
        return TextUtils.equals(this.f, mediaDirectory.f);
    }

    public int hashCode() {
        if (!TextUtils.isEmpty(this.d)) {
            int hashCode = this.d.hashCode();
            if (TextUtils.isEmpty(this.f)) {
                return hashCode;
            }
            return (hashCode * 31) + this.f.hashCode();
        } else if (TextUtils.isEmpty(this.f)) {
            return 0;
        } else {
            return this.f.hashCode();
        }
    }

    public String a() {
        return this.f;
    }

    public void a(String str) {
        this.f = str;
    }

    public void a(long j) {
        this.g = j;
    }

    public List<Media> b() {
        return this.h;
    }

    public void a(int i, String str, String str2, int i2, long j, long j2) {
        this.h.add(new Media(i, str, str2, i2, j, j2));
    }

    public void b(String str) {
        this.d = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeLong(this.g);
        parcel.writeTypedList(this.h);
    }
}
