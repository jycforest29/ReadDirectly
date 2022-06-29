package com.example.readdirectlyapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readdirectlyapp.R;
import com.example.readdirectlyapp.adapter.ChunkAdapter;
import com.example.readdirectlyapp.api.RetrofitClient;
import com.example.readdirectlyapp.model.api.Chunk;
import com.example.readdirectlyapp.model.api.Sentence;

import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectFragment extends androidx.fragment.app.Fragment {
//    view
    private View view;
    private String langs;
    private Spinner spinner;
    private Button toTranslateBtn;
    private Button toFileBtn;
    private EditText sentenceBefore;
    private RecyclerView chunkRecycler;
    private TextView sentenceAfter;
    private TextView sentencePronc;
//    instance
    private Sentence sentence;
//    related to call
    private List<Chunk> chunkList;
    private Sentence sentenceObj;
//    adapter
    private ChunkAdapter chunkAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_select, container, false);

        //        view binding
        spinner = view.findViewById(R.id.spinner);
        toTranslateBtn = view.findViewById(R.id.toTranslateBtn);
        sentenceBefore = view.findViewById(R.id.sentenceBefore);
        chunkRecycler = view.findViewById(R.id.chunkRecycler);
        toFileBtn = view.findViewById(R.id.toFileBtn);
        sentenceAfter = view.findViewById(R.id.sentenceAfter);
        sentencePronc = view.findViewById(R.id.sentencePronc);

        //                spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.langs, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                langs = spinner.getSelectedItem().toString();}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //        send request to server
        toTranslateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sentence = new Sentence(sentenceBefore.getText().toString());
                switch(langs){
                    case "영어":
                        langs = "en";
                        break;
                    case "일본어":
                        langs = "ja";
                        break;
                    default:
                        langs = "ko";
                        break;
                }
                translate(langs, sentence);

            }
        });

        //        Fragment transition
        toFileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = new FragmentManager() {};
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, new FileFragment());
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    private void translate(String dest, Sentence sentence){
        Call<List<Chunk>> chunkCall= RetrofitClient.getInstance().getApi().getChunks(dest, sentence);
        Call<Sentence> sentenceCall = RetrofitClient.getInstance().getApi().getSentence(dest, sentence);
        chunkCall.enqueue(new Callback<List<Chunk>>() {
            @Override
            public void onResponse(Call<List<Chunk>> call, Response<List<Chunk>> response) {

                chunkList = response.body();
                chunkAdapter = new ChunkAdapter(chunkList, dest);
                chunkRecycler.setAdapter(chunkAdapter);
                chunkRecycler.setLayoutManager(new LinearLayoutManager(getContext(), chunkRecycler.HORIZONTAL, false));
            }

            @Override
            public void onFailure(Call<List<Chunk>> call, Throwable t) {
                Toast.makeText(getContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
        sentenceCall.enqueue(new Callback<Sentence>() {
            @Override
            public void onResponse(Call<Sentence> call, Response<Sentence> response) {

                sentenceObj = response.body();
                sentence.setAfter(sentenceObj.getAfter());
                sentence.setPronunciation(sentenceObj.getPronunciation());
                sentenceAfter.setText(sentence.getAfter());
                sentencePronc.setText(sentence.getPronunciation());
            }

            @Override
            public void onFailure(Call<Sentence> call, Throwable t) {
                Toast.makeText(getContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
            //                List<Chunk> callResponse = response.body();
//                Sentence sentenceObj = (Sentence) callResponse.get(0);
//                List<Chunk> chunkList = (List<Chunk>) callResponse.get(1);
//                sentence.setAfter(sentenceObj.getAfter());
//                sentence.setPronunciation(sentenceObj.getPronunciation());
//                sentenceAfter.setText(sentence.getAfter());
//                sentencePronc.setText(sentence.getPronunciation());
        });
    }
}


