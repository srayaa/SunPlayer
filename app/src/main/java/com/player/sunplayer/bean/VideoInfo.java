package com.player.sunplayer.bean;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.boredream.bdvideoplayer.bean.IVideoInfo;
import org.json.JSONObject;

public class VideoInfo implements IVideoInfo {
    public static final Creator<VideoInfo> CREATOR = new Creator<VideoInfo>() {
        public VideoInfo createFromParcel(Parcel parcel) {
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            VideoInfo videoInfo = new VideoInfo(readString, readString2);
            videoInfo.setCookie(readString3);
            return videoInfo;
        }

        public VideoInfo[] newArray(int i) {
            return new VideoInfo[i];
        }
    };
    private String cookie;
    private long duration;
    private int id;
    private String name;
    private long size;
    private String url;

    public int describeContents() {
        return 0;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getVideoTitle() {
        return this.name;
    }

    public String getVideoPath() {
        return this.url;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public String getCookie() {
        return this.cookie;
    }

    public void setCookie(String str) {
        this.cookie = str;
    }

    public VideoInfo(String str, String str2) {
        this.name = "";
        this.url = "";
        this.cookie = "";
        this.name = str;
        this.url = str2;
    }

    public VideoInfo() {
        this.name = "";
        this.url = "";
        this.cookie = "";
        this.name = this.name;
        this.url = this.url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("url", this.url);
            jSONObject.put("name", this.name);
            jSONObject.put("cookie", this.cookie);
            jSONObject.put("duration", this.duration);
            jSONObject.put("size", this.size);
            jSONObject.put("id", this.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static VideoInfo fromJson(JSONObject jSONObject) {
        VideoInfo videoInfo;
        Exception e;
        try {
            videoInfo = new VideoInfo(jSONObject.getString("name"), jSONObject.getString("url"));
            try {
                videoInfo.setCookie(jSONObject.optString("cookie", ""));
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            videoInfo = null;
            e.printStackTrace();
            return videoInfo;
        }
        return videoInfo;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.url);
        parcel.writeString(this.cookie);
    }
}
