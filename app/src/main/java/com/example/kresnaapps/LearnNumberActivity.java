package com.example.kresnaapps;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kresnaapps.databinding.ActivityLearnNumberBinding;

import java.util.Collections;
import java.util.List;

public class LearnNumberActivity extends AppCompatActivity {
    private ActivityLearnNumberBinding binding;

    private List<Question> questionList;
    private Question currentQuestion;
    private int questionCounter, questionCountTotal, score, category;
    private String selectedAnswer, difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLearnNumberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        difficulty = getIntent().getExtras().getString("DIFFICULTY");
        category = getIntent().getExtras().getInt("CATEGORY", 0);

        //QuizDbHelper dbHelper = new QuizDbHelper(this);
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        questionList = dbHelper.getQuestion(category, difficulty);
        Collections.shuffle(questionList);
        questionCountTotal = questionList.size();
        showNextQuestion();

        binding.btnOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                selectedAnswer = button.getText().toString();
                checkAnswer();
            }
        });

        binding.btnOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                selectedAnswer = button.getText().toString();
                checkAnswer();
            }
        });

        binding.btnOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                selectedAnswer = button.getText().toString();
                checkAnswer();
            }
        });

        binding.btnOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                selectedAnswer = button.getText().toString();
                checkAnswer();
            }
        });
    }

    private void checkAnswer() {

        binding.btnOption1.setEnabled(false);
        binding.btnOption2.setEnabled(false);
        binding.btnOption3.setEnabled(false);
        binding.btnOption4.setEnabled(false);
        binding.btnLanjut.setVisibility(View.VISIBLE);
        binding.btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNextQuestion();
            }
        });

        Log.d("selectedAnswer", currentQuestion.getAnswerStr());
        if (selectedAnswer.equals(currentQuestion.getAnswerStr())) {
            Toast.makeText(LearnNumberActivity.this, "Benar!", Toast.LENGTH_SHORT).show();
            score++;
            binding.tvScore.setText("Score: " + score);
        } else {
            Toast.makeText(LearnNumberActivity.this, "Salah!", Toast.LENGTH_SHORT).show();
            binding.tvScore.setText("Score: " + score);
        }

        showSolution();
    }

    @SuppressLint("ResourceType")
    private void showNextQuestion() {

        if (questionCounter < questionCountTotal) {

            binding.btnOption1.setEnabled(true);
            binding.btnOption2.setEnabled(true);
            binding.btnOption3.setEnabled(true);
            binding.btnOption4.setEnabled(true);
            binding.btnOption1.setBackgroundColor(Color.parseColor("#FFFCDB"));
            binding.btnOption2.setBackgroundColor(Color.parseColor("#FFFCDB"));
            binding.btnOption3.setBackgroundColor(Color.parseColor("#FFFCDB"));
            binding.btnOption4.setBackgroundColor(Color.parseColor("#FFFCDB"));
            binding.btnLanjut.setVisibility(View.INVISIBLE);

            currentQuestion = questionList.get(questionCounter);
            binding.tvSoal.setText(currentQuestion.getQuestion());
            /*Glide.with(LearnNumberActivity.this)
                    .load(Integer.valueOf(currentQuestion.getQuestion()))
                    .into(binding.ivSoal);*/
            Log.d("questionnya", currentQuestion.getQuestion());
            binding.btnOption1.setText(currentQuestion.getOption1());
            binding.btnOption2.setText(currentQuestion.getOption2());
            binding.btnOption3.setText(currentQuestion.getOption3());
            binding.btnOption4.setText(currentQuestion.getOption4());

            questionCounter++;
        } else {
            Intent intent = new Intent(LearnNumberActivity.this, FunFactActivity.class);
            intent.putExtra("SCORE", score);
            intent.putExtra("DIFFICULTY", difficulty);
            intent.putExtra("CATEGORY", category);
            startActivity(intent);
            finish();
        }
    }

    private void showSolution() {
        binding.btnOption1.setBackgroundColor(Color.RED);
        binding.btnOption2.setBackgroundColor(Color.RED);
        binding.btnOption3.setBackgroundColor(Color.RED);
        binding.btnOption4.setBackgroundColor(Color.RED);

        String btn1str, btn2str, btn3str, btn4str;

        btn1str = binding.btnOption1.getText().toString();
        btn2str = binding.btnOption2.getText().toString();
        btn3str = binding.btnOption3.getText().toString();
        btn4str = binding.btnOption4.getText().toString();

        if (btn1str.equals(currentQuestion.getAnswerStr())) {
            binding.btnOption1.setBackgroundColor(Color.GREEN);
        } else if (btn2str.equals(currentQuestion.getAnswerStr())) {
            binding.btnOption2.setBackgroundColor(Color.GREEN);
        } else if (btn3str.equals(currentQuestion.getAnswerStr())) {
            binding.btnOption3.setBackgroundColor(Color.GREEN);
        } else if (btn4str.equals(currentQuestion.getAnswerStr())) {
            binding.btnOption4.setBackgroundColor(Color.GREEN);
        }
    }
}