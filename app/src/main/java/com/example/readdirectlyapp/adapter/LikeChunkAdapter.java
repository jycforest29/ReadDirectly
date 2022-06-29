package com.example.readdirectlyapp.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readdirectlyapp.DB.AppDatabase;
import com.example.readdirectlyapp.R;
import com.example.readdirectlyapp.model.room.LikeChunk;

import java.util.List;


public class LikeChunkAdapter extends RecyclerView.Adapter<LikeChunkAdapter.LikeChunkViewHolder>{
    private List<LikeChunk> likeChunkList;

    public LikeChunkAdapter(List<LikeChunk> likeChunkList) {
        this.likeChunkList = likeChunkList;
    }

    @NonNull
    @Override
    public LikeChunkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LikeChunkViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.like_chunk_item, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull LikeChunkViewHolder holder, @SuppressLint("RecyclerView") int position) {
        LikeChunk likeChunk = likeChunkList.get(position);
        holder.before.setText(likeChunk.getBefore());
        holder.after.setText(likeChunk.getAfter());
        holder.pronunciation.setText(likeChunk.getPronunciation());
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db  = AppDatabase.getInstance(v.getContext());
                db.likeChunkDao().delete(likeChunk);
                likeChunkList.remove(position);
                notifyDataSetChanged();
            }
        });
    }



    @Override
    public int getItemCount() {
        return likeChunkList.size();
    }

    public static class LikeChunkViewHolder extends RecyclerView.ViewHolder {
        private TextView before;
        private TextView after;
        private TextView pronunciation;
        private ImageButton deleteBtn;

        public LikeChunkViewHolder(@NonNull View itemView) {
            super(itemView);
            this.before = itemView.findViewById(R.id.like_chunk_before);
            this.after = itemView.findViewById(R.id.like_chunk_after);
            this.pronunciation = itemView.findViewById(R.id.like_chunk_pronc);
            this.deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }
    }
}