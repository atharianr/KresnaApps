package com.example.kresnaapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.kresnaapps.databinding.ActivityFunFactBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FunFactActivity extends AppCompatActivity {

    private ActivityFunFactBinding binding;
    private List<Question> questionList;
    private int score, category, mediaPlayerState;
    private String difficulty, nama;
    private MediaPlayer mediaPlayer;
    private ArrayList<Integer> arraySalah;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter salahAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFunFactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        difficulty = getIntent().getExtras().getString("DIFFICULTY");
        category = getIntent().getExtras().getInt("CATEGORY", 0);
        score = getIntent().getExtras().getInt("SCORE", 0);
        nama = getIntent().getExtras().getString("NAMA");

        //arraySalah = new ArrayList<Integer>();
        arraySalah = (ArrayList<Integer>) getIntent().getSerializableExtra("ARRAY_SALAH");

        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        questionList = dbHelper.getQuestion(category, difficulty);

        binding.tvArraySalah.setText("Array: " + arraySalah.toString());
        tampilFunFact();

        mediaPlayerState = 0;
        binding.btnPlayAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayerState == 0){
                    mediaPlayerState++;
                    startPlaying();
                } else {
                    mediaPlayerState = 0;
                    stopPlaying();
                }
            }
        });

        binding.rvNilai.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        salahAdapter = new SalahAdapter(arraySalah);
        binding.rvNilai.setLayoutManager(layoutManager);
        binding.rvNilai.setAdapter(salahAdapter);

    }

    private void tampilFunFact(){
        switch (category){
            case 2:
                if (difficulty.equals("easy")){
                    binding.ivFunFact.setImageResource(R.drawable.fae);
                } else if (difficulty.equals("medium")){
                    binding.ivFunFact.setImageResource(R.drawable.fam);
                } else {
                    binding.ivFunFact.setImageResource(R.drawable.fah);
                }
                break;
            case 3:
                if (difficulty.equals("easy")){
                    binding.ivFunFact.setImageResource(R.drawable.fse);
                } else if (difficulty.equals("medium")){
                    binding.ivFunFact.setImageResource(R.drawable.fsm);
                } else {
                    binding.ivFunFact.setImageResource(R.drawable.fsh);
                }
                break;
            case 4:
                if (difficulty.equals("easy")){
                    binding.ivFunFact.setImageResource(R.drawable.fme);
                } else if (difficulty.equals("medium")){
                    binding.ivFunFact.setImageResource(R.drawable.fmm);
                } else {
                    binding.ivFunFact.setImageResource(R.drawable.fmh);
                }
                break;
            case 5:
                binding.ivFunFact.setImageResource(R.drawable.fe);
                break;
            case 6:
                binding.ivFunFact.setImageResource(R.drawable.fq);
                break;
        }
    }

    private void stopPlaying() {
        if (mediaPlayer != null) {
            binding.btnPlayAudio.setImageResource(R.drawable.sound);
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void startPlaying() {
        if (mediaPlayer == null){
            binding.btnPlayAudio.setImageResource(R.drawable.ic_baseline_stop_24);
            mediaPlayer = MediaPlayer.create(FunFactActivity.this, R.raw.music);
            mediaPlayer.start();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FunFactActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void intentDataKeAdapter(){
        Intent intent = new Intent(FunFactActivity.this, SalahAdapter.class);
        intent.putExtra("ARRAY_SALAH", (Serializable) arraySalah);
    }
}