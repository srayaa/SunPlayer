package com.player.sunplayer.bean;

public class DownloadInfoStatus {
    public static final int COMPLETED = 2;
    public static final int CONNECTED = 0;
    public static final int DOWNLOADING = 1;
    public static final int ERROR = 3;
    public static final int PAUSED = -2;
    public static final int blockComplete = 4;
    public static final int progress = 3;
    public static final int retry = 5;
    public static final int toFileDownloadService = 11;
    public static final int toLaunchPool = 10;
    public static final int warn = -4;
}
