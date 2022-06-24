package com.example.readdirectlyapp.model;

import com.google.gson.annotations.SerializedName;

public class Sentence {
    @SerializedName("input")
    private String input;

    public Sentence(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
