package com.example.kresnaapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.kresnaapps.databinding.ActivitySelectDifficultyBinding;

public class SelectDifficultyActivity extends AppCompatActivity {

    private ActivitySelectDifficultyBinding binding;
    private String difficulty, nama;
    private int category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectDifficultyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        category = getIntent().getExtras().getInt("CATEGORY", 0);

        binding.btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                difficulty = button.getText().toString();
                nama = binding.etNama.getText().toString();
                if (nama.isEmpty() || nama.length() == 0 || nama == null || nama.matches("")){
                    Toast.makeText(SelectDifficultyActivity.this, "Isi nama kamu", Toast.LENGTH_SHORT).show();
                } else{
                    if (category == 1) {
                        intentNumber();
                    } else if (category == 2) {
                        intentAdd();
                    } else if (category == 3) {
                        intentSubstract();
                    } else if (category == 4) {
                        intentMultiply();
                    } else if (category == 5) {
                        intentSocial();
                    } else if (category == 6) {
                        intentQuiz();
                    }
                }
            }
        });

        binding.btnMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                difficulty = button.getText().toString();
                nama = binding.etNama.getText().toString();
                if (nama.isEmpty() || nama.length() == 0 || nama == null || nama.matches("")) {
                    Toast.makeText(SelectDifficultyActivity.this, "Isi nama kamu", Toast.LENGTH_SHORT).show();
                } else {
                    if (category == 1) {
                        intentNumber();
                    } else if (category == 2) {
                        intentAdd();
                    } else if (category == 3) {
                        intentSubstract();
                    } else if (category == 4) {
                        intentMultiply();
                    } else if (category == 5) {
                        intentSocial();
                    } else if (category == 6) {
                        intentQuiz();
                    }
                }
            }
        });

        binding.btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                difficulty = button.getText().toString();
                nama = binding.etNama.getText().toString();
                if (nama.isEmpty() || nama.length() == 0 || nama == null || nama.matches("")) {
                    Toast.makeText(SelectDifficultyActivity.this, "Isi nama kamu", Toast.LENGTH_SHORT).show();
                } else {
                    if (category == 1) {
                        intentNumber();
                    } else if (category == 2) {
                        intentAdd();
                    } else if (category == 3) {
                        intentSubstract();
                    } else if (category == 4) {
                        intentMultiply();
                    } else if (category == 5) {
                        intentSocial();
                    } else if (category == 6) {
                        intentQuiz();
                    }
                }
            }
        });
    }

    private void intentNumber() {
        Intent intent = new Intent(SelectDifficultyActivity.this, LearnNumberActivity.class);
        intent.putExtra("DIFFICULTY", difficulty);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("NAMA", nama);
        startActivity(intent);
        finish();
    }

    private void intentAdd() {
        Intent intent = new Intent(SelectDifficultyActivity.this, LearnAdditionActivity.class);
        intent.putExtra("DIFFICULTY", difficulty);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("NAMA", nama);
        startActivity(intent);
        finish();
    }

    private void intentSubstract() {
        Intent intent = new Intent(SelectDifficultyActivity.this, LearnSubstractionActivity.class);
        intent.putExtra("DIFFICULTY", difficulty);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("NAMA", nama);
        startActivity(intent);
        finish();
    }

    private void intentMultiply() {
        Intent intent = new Intent(SelectDifficultyActivity.this, LearnMultiplicationActivity.class);
        intent.putExtra("DIFFICULTY", difficulty);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("NAMA", nama);
        startActivity(intent);
        finish();
    }

    private void intentSocial() {
        Intent intent = new Intent(SelectDifficultyActivity.this, LearnSocialActivity.class);
        intent.putExtra("DIFFICULTY", difficulty);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("NAMA", nama);
        startActivity(intent);
        finish();
    }

    private void intentQuiz() {
        Intent intent = new Intent(SelectDifficultyActivity.this, LearnQuizActivity.class);
        intent.putExtra("DIFFICULTY", difficulty);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("NAMA", nama);
        startActivity(intent);
        finish();
    }
}