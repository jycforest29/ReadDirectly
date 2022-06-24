package com.example.readdirectlyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.readdirectlyapp.api.RetrofitClient;
import com.example.readdirectlyapp.databinding.ActivityMainBinding;
import com.example.readdirectlyapp.model.Chunk;
import com.example.readdirectlyapp.model.Sentence;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{
    private ActivityMainBinding binding;
    private Sentence sentence;
    private List<Chunk> chunkList;
    private ChunkAdapter chunkAdapter;
    private String langs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.langs, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        binding.spinner.setAdapter(adapter);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                langs = binding.spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sentence = new Sentence(binding.input.getText().toString());
                langsToCode();
                Log.d("langs", langs);
                translate(langs, sentence);
            }
        });
    }

    private void langsToCode(){
        if(langs.equals("영어")){
            langs = "en";
        }
        else if(langs.equals("일본어")){
            langs = "ja";
        }
        else{
            langs = "ko";
        }
    }

    private void translate(String dest, Sentence sentence){
        Call<List<Chunk>> call = RetrofitClient.getInstance().getApi().getChunks(dest, sentence);

        call.enqueue(new Callback<List<Chunk>>() {
            @Override
            public void onResponse(Call<List<Chunk>> call, Response<List<Chunk>> response) {
                List<Chunk> chunkResponse = response.body();

                chunkList = chunkResponse;
                chunkAdapter = new ChunkAdapter(chunkList);
                binding.mainRecycler.setAdapter(chunkAdapter);
                binding.mainRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }

            @Override
            public void onFailure(Call<List<Chunk>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }


}