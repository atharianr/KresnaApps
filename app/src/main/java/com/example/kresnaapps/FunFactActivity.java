package com.example.kresnaapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.kresnaapps.databinding.ActivityFunFactBinding;

import java.util.ArrayList;
import java.util.List;

public class FunFactActivity extends AppCompatActivity {

    private ActivityFunFactBinding binding;
    private List<Question> questionList;
    private int score, category, mediaPlayerState;
    private String difficulty, nama;
    private MediaPlayer mediaPlayer;
    private List<Integer> arraySalah;

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
        arraySalah = (List<Integer>) getIntent().getSerializableExtra("ARRAY_SALAH");

        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        questionList = dbHelper.getQuestion(category, difficulty);

        binding.tvArraySalah.setText("Array: " + arraySalah.toString());
        binding.tvAdalahScore.setText("Nilai " + nama + " adalah");
        binding.tvNilaiScore.setText(String.valueOf(score) + "/" + String.valueOf(questionList.size()));

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
    }
}