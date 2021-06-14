package com.example.kresnaapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.kresnaapps.databinding.ActivitySelectDifficultyBinding;

public class SelectDifficultyActivity extends AppCompatActivity {

    private ActivitySelectDifficultyBinding binding;
    private String difficulty;
    private int category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectDifficultyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        category = getIntent().getExtras().getInt("CATEGORY", 0);

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

    private void intentKeNama(){
        Intent intent = new Intent(SelectDifficultyActivity.this, IsiNamaActivity.class);
        intent.putExtra("DIFFICULTY", difficulty);
        intent.putExtra("CATEGORY", category);
        startActivity(intent);
    }
}