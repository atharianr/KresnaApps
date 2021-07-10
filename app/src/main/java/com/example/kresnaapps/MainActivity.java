package com.example.kresnaapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.kresnaapps.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding.btnLearnNumber.setEnabled(true);
        binding.btnLearnAddition.setEnabled(true);
        binding.btnLearnSubstraction.setEnabled(true);
        binding.btnLearnMultiply.setEnabled(true);
        binding.btnSocial.setEnabled(true);
        binding.btnQuiz.setEnabled(true);

        binding.btnLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentKeAbout();
            }
        });

        binding.btnLearnNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = 1;
                intentPakeData();
            }
        });

        binding.btnLearnAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = 2;
                intentPakeData();
            }
        });

        binding.btnLearnSubstraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = 3;
                intentPakeData();
            }
        });

        binding.btnLearnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = 4;
                intentPakeData();
            }
        });

        binding.btnSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = 5;
                intentSocialExperiment();
            }
        });

        binding.btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = 6;
                intentQuiz();
            }
        });
    }

    private void intentPakeData() {
        Intent intent = new Intent(MainActivity.this, SelectDifficultyActivity.class);
        intent.putExtra("CATEGORY", category);
        startActivity(intent);
    }

    private void intentKeAbout() {
        Intent intent = new Intent(MainActivity.this, AboutAppActivity.class);
        startActivity(intent);
    }

    private void intentSocialExperiment() {
        Intent intent = new Intent(MainActivity.this, SoalTextJawabanTextActivity.class);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("DIFFICULTY", "easy");
        intent.putExtra("NAMA", "kamoe");
        startActivity(intent);
        finish();
    }

    private void intentQuiz() {
        Intent intent = new Intent(MainActivity.this, SoalGambarJawabanTextActivity .class);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("DIFFICULTY", "easy");
        intent.putExtra("NAMA", "kamoe");
        startActivity(intent);
        finish();
    }
}
