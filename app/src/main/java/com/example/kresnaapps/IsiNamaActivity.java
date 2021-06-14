package com.example.kresnaapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.kresnaapps.databinding.ActivityIsiNamaBinding;

public class IsiNamaActivity extends AppCompatActivity {

    private ActivityIsiNamaBinding binding;
    private String nama, difficulty;
    private int category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIsiNamaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        category = getIntent().getExtras().getInt("CATEGORY", 0);
        difficulty = getIntent().getExtras().getString("DIFFICULTY");

        binding.btnMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama = binding.etNama.getText().toString();
                if (nama.isEmpty() || nama.length() == 0 || nama == null || nama.matches("")) {
                    Toast.makeText(IsiNamaActivity.this, "Masukkan nama kamu", Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(getApplicationContext(), LearnNumberActivity.class);
        intent.putExtra("DIFFICULTY", difficulty);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("NAMA", nama);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finishAffinity();
    }

    private void intentAdd() {
        Intent intent = new Intent(getApplicationContext(), LearnAdditionActivity.class);
        intent.putExtra("DIFFICULTY", difficulty);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("NAMA", nama);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finishAffinity();
    }

    private void intentSubstract() {
        Intent intent = new Intent(getApplicationContext(), LearnSubstractionActivity.class);
        intent.putExtra("DIFFICULTY", difficulty);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("NAMA", nama);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finishAffinity();
    }

    private void intentMultiply() {
        Intent intent = new Intent(getApplicationContext(), LearnMultiplicationActivity.class);
        intent.putExtra("DIFFICULTY", difficulty);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("NAMA", nama);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finishAffinity();
    }

    private void intentSocial() {
        Intent intent = new Intent(getApplicationContext(), LearnSocialActivity.class);
        intent.putExtra("DIFFICULTY", difficulty);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("NAMA", nama);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finishAffinity();
    }

    private void intentQuiz() {
        Intent intent = new Intent(getApplicationContext(), LearnQuizActivity.class);
        intent.putExtra("DIFFICULTY", difficulty);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("NAMA", nama);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finishAffinity();
    }

}