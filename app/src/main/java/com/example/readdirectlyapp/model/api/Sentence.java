package com.example.readdirectlyapp.model.api;

import com.google.gson.annotations.SerializedName;

public class Sentence {
    @SerializedName("before")
    private String before;
    @SerializedName("after")
    private String after;
    @SerializedName("pronunciation")
    private String pronunciation;

    public Sentence(String before) {
        this.before = before;
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
