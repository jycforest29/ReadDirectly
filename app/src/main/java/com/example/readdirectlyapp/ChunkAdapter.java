package com.example.readdirectlyapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readdirectlyapp.model.Chunk;

import java.util.List;

public class ChunkAdapter extends RecyclerView.Adapter<ChunkAdapter.ChunkViewHolder>{
    private List<Chunk> chunkList;

    public ChunkAdapter(List<Chunk> chunkList) {
        this.chunkList = chunkList;
    }

    @NonNull
    @Override
    public ChunkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChunkViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chunk_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChunkViewHolder holder, int position) {
        Chunk chunk = chunkList.get(position);
        holder.before.setText(chunk.getBefore());
        holder.after.setText(chunk.getAfter());
        holder.pronunciation.setText(chunk.getPronunciation());
    }

    @Override
    public int getItemCount() {
        return chunkList.size();
    }

    public static class ChunkViewHolder extends RecyclerView.ViewHolder {
        private TextView before;
        private TextView after;
        private TextView pronunciation;

        public ChunkViewHolder(@NonNull View itemView) {
            super(itemView);
            this.before = itemView.findViewById(R.id.before);
            this.after = itemView.findViewById(R.id.after);
            this.pronunciation = itemView.findViewById(R.id.pronunciation);
        }
    }
}
