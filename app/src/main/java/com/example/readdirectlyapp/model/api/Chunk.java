package com.example.readdirectlyapp.model.api;

import com.google.gson.annotations.SerializedName;

public class Chunk {
    @SerializedName("before")
    private String before;
    @SerializedName("after")
    private String after;
    @SerializedName("pronunciation")
    private String pronunciation;

    public Chunk(String before, String dest, String after, String pronunciation) {
        this.before = before;
        this.after = after;
        this.pronunciation = pronunciation;
    }
    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }
}
