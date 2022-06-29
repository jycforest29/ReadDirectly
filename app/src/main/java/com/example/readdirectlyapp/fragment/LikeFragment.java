package com.example.readdirectlyapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readdirectlyapp.DB.AppDatabase;
import com.example.readdirectlyapp.R;
import com.example.readdirectlyapp.adapter.ChunkAdapter;
import com.example.readdirectlyapp.adapter.LikeChunkAdapter;
import com.example.readdirectlyapp.model.room.LikeChunk;

import java.util.List;

public class LikeFragment  extends androidx.fragment.app.Fragment {
    private View view;
    private RecyclerView likeChunkRecycler;
    private LikeChunkAdapter likeChunkAdapter;
    private List<LikeChunk> likeChunkList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_like, container, false);
//        view binding
        likeChunkRecycler = view.findViewById(R.id.likeChunkRecycler);
//        load likeChunks
        likeChunkList = loadLikeChunks();
        likeChunkAdapter = new LikeChunkAdapter(likeChunkList);
        likeChunkRecycler.setAdapter(likeChunkAdapter);
        likeChunkRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }


    private List<LikeChunk> loadLikeChunks() {
        AppDatabase db  = AppDatabase.getInstance(getContext());
        return db.likeChunkDao().getAll();
    }
}
