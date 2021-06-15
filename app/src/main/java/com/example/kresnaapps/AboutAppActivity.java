package com.example.kresnaapps;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.kresnaapps.databinding.ActivityAboutAppBinding;

public class AboutAppActivity extends AppCompatActivity {

    private ActivityAboutAppBinding binding;
    private int mediaPlayerState;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

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
            mediaPlayer = MediaPlayer.create(AboutAppActivity.this, R.raw.about_kresna);
            mediaPlayer.start();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stopPlaying();
    }
}