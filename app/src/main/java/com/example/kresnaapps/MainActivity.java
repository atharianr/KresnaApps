package com.example.kresnaapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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
                intentPakeData();
            }
        });

        binding.btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = 6;
                intentPakeData();
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
}