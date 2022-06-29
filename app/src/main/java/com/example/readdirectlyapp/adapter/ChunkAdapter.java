package com.example.readdirectlyapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readdirectlyapp.DB.AppDatabase;
import com.example.readdirectlyapp.R;
import com.example.readdirectlyapp.model.api.Chunk;
import com.example.readdirectlyapp.model.room.LikeChunk;

import java.util.List;

public class ChunkAdapter extends RecyclerView.Adapter<ChunkAdapter.ChunkViewHolder>{
    private List<Chunk> chunkList;
    private static String dest;

    public ChunkAdapter(List<Chunk> chunkList, String dest) {
        this.chunkList = chunkList;
        this.dest = dest;
    }

    @NonNull
    @Override
    public ChunkAdapter.ChunkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChunkAdapter.ChunkViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chunk_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChunkAdapter.ChunkViewHolder holder, int position) {
        Chunk chunk = chunkList.get(position);
        holder.c_before.setText(chunk.getBefore());
        holder.c_after.setText(chunk.getAfter());
        holder.c_pronc.setText(chunk.getPronunciation());
    }

    @Override
    public int getItemCount() {
        return chunkList.size();
    }

    public static class ChunkViewHolder extends RecyclerView.ViewHolder {
        private TextView c_before;
        private TextView c_after;
        private TextView c_pronc;
        private ImageButton insertBtn;

        public ChunkViewHolder(@NonNull View itemView) {
            super(itemView);
            this.c_before = itemView.findViewById(R.id.c_before);
            this.c_after = itemView.findViewById(R.id.c_after);
            this.c_pronc = itemView.findViewById(R.id.c_pronc);
            this.insertBtn = itemView.findViewById(R.id.insertBtn);
//            findLikeChunk(this.c_before.getText().toString(), dest);
            this.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    insertBtn.setImageResource(R.drawable.ic_baseline_favorite_24);
                    insertLikeChunk(c_before.getText().toString(), dest, c_after.getText().toString(), c_pronc.getText().toString());
                }
            });
        }

//        private void findLikeChunk(String before, String dest) {
//            AppDatabase db  = AppDatabase.getInstance(itemView.getContext());
//            db.likeChunkDao().findLikeChunk(before, dest);
//        }


        private void insertLikeChunk(String text, String text1, String text2, String text3) {
            LikeChunk likeChunk = new LikeChunk();
            likeChunk.setBefore(text);
            likeChunk.setDest(text1);
            likeChunk.setAfter(text2);
            likeChunk.setPronunciation(text3);

            AppDatabase db  = AppDatabase.getInstance(itemView.getContext());
            db.likeChunkDao().insert(likeChunk);
        }
    }
}

