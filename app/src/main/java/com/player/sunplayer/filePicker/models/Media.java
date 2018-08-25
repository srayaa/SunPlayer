package com.player.sunplayer.filePicker.models;

import android.os.Parcel;
import android.os.Parcelable;

import droidninja.filepicker.models.BaseFile;

public class Media extends BaseFile implements Parcelable {
    public static final Creator<Media> CREATOR = new Creator<Media>() {
        public /* synthetic */ Media createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Media[] newArray(int i) {
            return a(i);
        }

        public Media a(Parcel parcel) {
            return new Media(parcel);
        }

        public Media[] a(int i) {
            return new Media[i];
        }
    };
    private int d;
    private long e;
    private long f;

    public int describeContents() {
        return 0;
    }

    public long a() {
        return this.e;
    }

    public long b() {
        return this.f;
    }

    public Media(int i, String str, String str2, int i2, long j, long j2) {
        super(i, str, str2);
        this.d = i2;
        this.e = j;
        this.f = j2;
    }

    public Media() {
        super(0, null, null);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Media)) {
            return false;
        }
        if (this.id != ((Media) obj).id) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return this.id;
    }

    public String c() {
        return this.path != null ? this.path : "";
    }

    public int d() {
        return this.id;
    }

    public String e() {
        return this.name;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.d);
        parcel.writeInt(this.id);
        parcel.writeLong(this.f);
        parcel.writeLong(this.e);
        parcel.writeString(this.name);
        parcel.writeString(this.path);
    }

    protected Media(Parcel parcel) {
        this.d = parcel.readInt();
        this.id = parcel.readInt();
        this.f = parcel.readLong();
        this.e = parcel.readLong();
        this.name = parcel.readString();
        this.path = parcel.readString();
    }
}
