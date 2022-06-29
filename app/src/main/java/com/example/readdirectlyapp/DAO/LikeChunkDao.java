package com.example.readdirectlyapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.readdirectlyapp.model.room.LikeChunk;

import java.util.List;

@Dao
public interface LikeChunkDao {

    @Query("SELECT * FROM like_chunk_table")
    List<LikeChunk> getAll();

    @Insert
    void insert(LikeChunk likeChunk);

    @Delete
    void delete(LikeChunk likeChunk);

    @Query("DELETE FROM like_chunk_table")
    void deleteAll();

}
