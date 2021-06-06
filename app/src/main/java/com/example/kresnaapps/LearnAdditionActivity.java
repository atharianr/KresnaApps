package com.example.kresnaapps;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kresnaapps.databinding.ActivityLearnAdditionBinding;

import java.util.Collections;
import java.util.List;

public class LearnAdditionActivity extends AppCompatActivity {
    private ActivityLearnAdditionBinding binding;

    private List<Question> questionList;
    private Question currentQuestion;
    private int questionCounter, questionCountTotal, score, category;
    private String selectedAnswer, difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLearnAdditionBinding.inflate(getLayoutInflater());
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

    private void checkAnswer(){
        Log.d("selectedAnswer", currentQuestion.getAnswerStr());
        if (selectedAnswer.equals(currentQuestion.getAnswerStr())){
            Toast.makeText(LearnAdditionActivity.this, "Benar!", Toast.LENGTH_SHORT).show();
            score++;
            binding.tvScore.setText("Score: " + score);
            showNextQuestion();
        } else {
            Toast.makeText(LearnAdditionActivity.this, "Salah!", Toast.LENGTH_SHORT).show();
            binding.tvScore.setText("Score: " + score);
        }
    }

    @SuppressLint("ResourceType")
    private void showNextQuestion() {

        if (questionCounter < questionCountTotal){
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
            Intent intent = new Intent(LearnAdditionActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
}