package com.boredream.bdvideoplayer.bean;

import android.os.Parcelable;

public interface IVideoInfo extends Parcelable {
    String getVideoPath();

    String getVideoTitle();
}
