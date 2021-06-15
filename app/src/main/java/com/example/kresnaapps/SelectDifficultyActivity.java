package com.example.kresnaapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.kresnaapps.databinding.ActivitySelectDifficultyBinding;

public class SelectDifficultyActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String LEVEL_NUMBER = "levelNumber";

    private ActivitySelectDifficultyBinding binding;
    private String difficulty;
    private int category, levelNumber, levelAdd, levelSubs, levelMulti, levelSocial, levelQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectDifficultyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        category = getIntent().getExtras().getInt("CATEGORY", 0);
        ambilSharedPrefs();

        // CEK LEVEL YANG UDA BISA DI MAININ
        if (category == 1) {
            switch (levelNumber) {
                case 0:
                    binding.btnMedium.setEnabled(false);
                    binding.btnHard.setEnabled(false);
                    break;
                case 1:
                    binding.btnMedium.setEnabled(true);
                    binding.btnHard.setEnabled(false);
                    break;
                case 2:
                    binding.btnMedium.setEnabled(true);
                    binding.btnHard.setEnabled(true);
                    break;
            }
        } else if (category == 2) {
            switch (levelAdd) {
                case 0:
                    binding.btnMedium.setEnabled(false);
                    binding.btnHard.setEnabled(false);
                    break;
                case 1:
                    binding.btnMedium.setEnabled(true);
                    binding.btnHard.setEnabled(false);
                    break;
                case 2:
                    binding.btnMedium.setEnabled(true);
                    binding.btnHard.setEnabled(true);
                    break;
            }
        } else if (category == 3) {
            switch (levelSubs) {
                case 0:
                    binding.btnMedium.setEnabled(false);
                    binding.btnHard.setEnabled(false);
                    break;
                case 1:
                    binding.btnMedium.setEnabled(true);
                    binding.btnHard.setEnabled(false);
                    break;
                case 2:
                    binding.btnMedium.setEnabled(true);
                    binding.btnHard.setEnabled(true);
                    break;
            }
        } else if (category == 4) {
            switch (levelMulti) {
                case 0:
                    binding.btnMedium.setEnabled(false);
                    binding.btnHard.setEnabled(false);
                    break;
                case 1:
                    binding.btnMedium.setEnabled(true);
                    binding.btnHard.setEnabled(false);
                    break;
                case 2:
                    binding.btnMedium.setEnabled(true);
                    binding.btnHard.setEnabled(true);
                    break;
            }
        }
        else if (category == 5) {
            switch (levelSocial) {
                case 0:
                    binding.btnMedium.setEnabled(false);
                    binding.btnHard.setEnabled(false);
                    break;
                case 1:
                    binding.btnMedium.setEnabled(true);
                    binding.btnHard.setEnabled(false);
                    break;
                case 2:
                    binding.btnMedium.setEnabled(true);
                    binding.btnHard.setEnabled(true);
                    break;
            }
        }
        else if (category == 6) {
            switch (levelQuiz) {
                case 0:
                    binding.btnMedium.setEnabled(false);
                    binding.btnHard.setEnabled(false);
                    break;
                case 1:
                    binding.btnMedium.setEnabled(true);
                    binding.btnHard.setEnabled(false);
                    break;
                case 2:
                    binding.btnMedium.setEnabled(true);
                    binding.btnHard.setEnabled(true);
                    break;
            }
        }

        binding.btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                difficulty = button.getText().toString();
                intentKeNama();
            }
        });


        binding.btnMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                difficulty = button.getText().toString();
                intentKeNama();
            }
        });

        binding.btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                difficulty = button.getText().toString();
                intentKeNama();
            }
        });
    }

    private void intentKeNama() {
        Intent intent = new Intent(SelectDifficultyActivity.this, IsiNamaActivity.class);
        intent.putExtra("DIFFICULTY", difficulty);
        intent.putExtra("CATEGORY", category);
        startActivity(intent);
    }

    private void ambilSharedPrefs() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        levelNumber = sharedPreferences.getInt(LEVEL_NUMBER, 0);
        Toast.makeText(SelectDifficultyActivity.this, String.valueOf(levelNumber), Toast.LENGTH_SHORT).show();
    }
}