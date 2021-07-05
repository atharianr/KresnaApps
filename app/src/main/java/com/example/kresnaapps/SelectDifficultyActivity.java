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
    public static final String LEVEL_ADD = "levelAdd";
    public static final String LEVEL_SUBS = "levelSubs";
    public static final String LEVEL_MULTI = "levelMulti";
    public static final String LEVEL_SOCIAL = "levelSocial";
    public static final String LEVEL_QUIZ = "levelQuiz";

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
        } else if (category == 5) {
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
        } else if (category == 6) {
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
                if (category == 1) {
                    intentKeSoalGambarJawabanText();
                } else if (category == 2) {
                    intentKeSoalGambarJawabanText();
                } else if (category == 3) {
                    intentKeSoalGambarJawabanText();
                } else if (category == 4) {
                    intentKeSoalGambarJawabanText();
                } else if (category == 5) {
                } else if (category == 6) {
                }
            }
        });

        binding.btnMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                difficulty = button.getText().toString();
                if (category == 1) {
                    intentKeSoalGambarJawabanText();
                } else if (category == 2) {
                    intentKeSoalGambarJawabanText();
                } else if (category == 3) {
                    intentKeSoalGambarJawabanText();
                } else if (category == 4) {
                    intentKeSoalGambarJawabanText();
                } else if (category == 5) {
                } else if (category == 6) {
                }
            }
        });

        binding.btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                difficulty = button.getText().toString();
                if (category == 1) {
                    intentKeSoalGambarJawabanText();
                } else if (category == 2) {
                    intentKeSoalGambarJawabanText();
                } else if (category == 3) {
                    intentKeSoalGambarJawabanText();
                } else if (category == 4) {
                    intentKeSoalGambarJawabanText();
                } else if (category == 5) {
                } else if (category == 6) {
                }
            }
        });
    }

    private void intentKeSoalTextJawabanGambar() {
        Intent intent = new Intent(SelectDifficultyActivity.this, LearnNumberActivity.class);
        intent.putExtra("DIFFICULTY", difficulty);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("NAMA", "kamoe");
        startActivity(intent);
        finish();
    }

    private void intentKeSoalGambarJawabanText() {
        Intent intent = new Intent(SelectDifficultyActivity.this, SoalGambarJawabanTextActivity.class);
        intent.putExtra("DIFFICULTY", difficulty);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("NAMA", "kamoe");
        startActivity(intent);
        finish();
    }

    private void ambilSharedPrefs() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        levelNumber = sharedPreferences.getInt(LEVEL_NUMBER, 0);
        levelAdd = sharedPreferences.getInt(LEVEL_ADD, 0);
        levelSubs = sharedPreferences.getInt(LEVEL_SUBS, 0);
        levelMulti = sharedPreferences.getInt(LEVEL_MULTI, 0);
        levelSocial = sharedPreferences.getInt(LEVEL_SOCIAL, 0);
        levelQuiz = sharedPreferences.getInt(LEVEL_QUIZ, 0);
        Toast.makeText(SelectDifficultyActivity.this,
                String.valueOf(levelNumber) +
                        String.valueOf(levelAdd) +
                        String.valueOf(levelSubs) +
                        String.valueOf(levelMulti) +
                        String.valueOf(levelSocial) +
                        String.valueOf(levelQuiz),
                Toast.LENGTH_SHORT).show();
    }
}