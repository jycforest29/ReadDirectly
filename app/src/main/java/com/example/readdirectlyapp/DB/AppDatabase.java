package com.example.readdirectlyapp.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.readdirectlyapp.DAO.LikeChunkDao;
import com.example.readdirectlyapp.model.room.LikeChunk;

@Database(entities = {LikeChunk.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LikeChunkDao likeChunkDao();
    private static AppDatabase INSTANCE = null;

    public static AppDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "rd_database").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
    public static void destroyInstance(){
        INSTANCE = null;
    }
}
