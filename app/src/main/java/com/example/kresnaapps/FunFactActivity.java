package com.example.kresnaapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.kresnaapps.databinding.ActivityFunFactBinding;

import java.util.List;

public class FunFactActivity extends AppCompatActivity {

    private ActivityFunFactBinding binding;
    private List<Question> questionList;
    private int score, category;
    private String difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFunFactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        difficulty = getIntent().getExtras().getString("DIFFICULTY");
        category = getIntent().getExtras().getInt("CATEGORY", 0);
        score = getIntent().getExtras().getInt("SCORE", 0);
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        questionList = dbHelper.getQuestion(category, difficulty);

        binding.tvNilaiScore.setText(String.valueOf(score) + "/" + String.valueOf(questionList.size()));

    }
}