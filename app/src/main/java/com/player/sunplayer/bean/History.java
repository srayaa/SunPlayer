package com.player.sunplayer.bean;

import java.io.Serializable;

public class History implements Serializable {
    private String title;
    private String url;

    public History(String str, String str2) {
        this.url = str;
        this.title = str2;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
