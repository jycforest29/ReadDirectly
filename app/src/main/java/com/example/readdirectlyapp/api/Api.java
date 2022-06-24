package com.example.readdirectlyapp.api;

import com.example.readdirectlyapp.model.Chunk;
import com.example.readdirectlyapp.model.Sentence;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    String BASE_URL = "http://192.168.1.83:8000/";

    @POST("sentence/{dest}")
    Call<List<Chunk>> getChunks(@Path("dest") String dest, @Body Sentence sentence);

}
