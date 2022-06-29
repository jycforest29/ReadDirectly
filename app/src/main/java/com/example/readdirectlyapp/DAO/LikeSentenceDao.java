package com.example.readdirectlyapp.DAO;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.readdirectlyapp.model.room.LikeSentence;

import java.util.List;

public interface LikeSentenceDao {
    @Query("SELECT * FROM LikeSentence")
    List<LikeSentence> getAll();
    @Insert
    void insert(LikeSentence sentence);
    @Delete
    void delete(LikeSentence sentence);
}
