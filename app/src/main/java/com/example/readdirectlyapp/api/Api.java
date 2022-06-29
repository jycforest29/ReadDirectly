package com.example.readdirectlyapp.api;

import android.app.LauncherActivity;

import com.example.readdirectlyapp.model.api.Chunk;
import com.example.readdirectlyapp.model.api.Sentence;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    String BASE_URL = "http://192.168.1.83:8000/";

    @POST("chunk/{dest}")
    Call<List<Chunk>> getChunks(@Path("dest") String dest, @Body Sentence sentence);

    @POST("sentence/{dest}")
    Call<Sentence> getSentence(@Path("dest") String dest, @Body Sentence sentence);
}
