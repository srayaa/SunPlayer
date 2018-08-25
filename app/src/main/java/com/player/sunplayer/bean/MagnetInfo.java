package com.player.sunplayer.bean;

import java.io.Serializable;

public class MagnetInfo implements Serializable {
    private String baseFolder;
    private String fileIndex;
    private long file_size;
    private String name;
    private String subfile;
    private String type;

    public String getBaseFolder() {
        return this.baseFolder;
    }

    public void setBaseFolder(String str) {
        this.baseFolder = str;
    }

    public String getSubfile() {
        return this.subfile;
    }

    public void setSubfile(String str) {
        this.subfile = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public MagnetInfo(){

    }
    public MagnetInfo(String str, long j, String str2) {
        this.name = str;
        this.file_size = j;
        this.fileIndex = str2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public long getFile_size() {
        return this.file_size;
    }

    public void setFile_size(long j) {
        this.file_size = j;
    }

    public String getFileIndex() {
        return this.fileIndex;
    }

    public void setFileIndex(String str) {
        this.fileIndex = str;
    }
}
