package com.example.kresnaapps;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.example.kresnaapps.databinding.ActivityFunFactBinding;

import java.util.List;

public class FunFactActivity extends AppCompatActivity {

    private ActivityFunFactBinding binding;
    private List<Question> questionList;
    private int score, category, mediaPlayerState;
    private String difficulty, nama;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFunFactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        difficulty = getIntent().getExtras().getString("DIFFICULTY");
        category = getIntent().getExtras().getInt("CATEGORY", 0);
        score = getIntent().getExtras().getInt("SCORE", 0);
        nama = getIntent().getExtras().getString("NAMA");
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        questionList = dbHelper.getQuestion(category, difficulty);

        binding.tvNamaScore.setText(nama);
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
            binding.btnPlayAudio.setImageResource(R.drawable.ic_baseline_play_arrow_24);
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
}