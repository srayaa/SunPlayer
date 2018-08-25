package com.player.sunplayer.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class MagnetInfoList implements Serializable {
    private ArrayList<MagnetInfo> Infolist;
    private String hash;
    private String name;
    private String torrentPath;

    public String getTorrentPath() {
        return this.torrentPath;
    }

    public void setTorrentPath(String str) {
        this.torrentPath = str;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String str) {
        this.hash = str;
    }

    public ArrayList<MagnetInfo> getInfolist() {
        return this.Infolist;
    }

    public void setInfolist(ArrayList<MagnetInfo> arrayList) {
        this.Infolist = arrayList;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }
}
