package com.example.kresnaapps;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.kresnaapps.databinding.ActivityLearnNumberBinding;
import java.util.Collections;
import java.util.List;

public class LearnNumberActivity extends AppCompatActivity {
    private ActivityLearnNumberBinding binding;

    private List<Question> questionList;
    private Question currentQuestion;
    private int questionCounter, questionCountTotal, score, category;
    private String selectedAnswer, difficulty, nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLearnNumberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        difficulty = getIntent().getExtras().getString("DIFFICULTY");
        category = getIntent().getExtras().getInt("CATEGORY", 0);
        nama = getIntent().getExtras().getString("NAMA");

        //QuizDbHelper dbHelper = new QuizDbHelper(this);
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        questionList = dbHelper.getQuestion(category, difficulty);
        Collections.shuffle(questionList);
        questionCountTotal = questionList.size();
        showNextQuestion();

        binding.btnOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswer = view.getTag().toString();
                Log.d("ini tag", selectedAnswer);
                Log.d("selectedAnswer", currentQuestion.getAnswerStr());
                checkAnswer();
            }
        });

        binding.btnOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswer = view.getTag().toString();
                Log.d("ini tag", selectedAnswer);
                Log.d("selectedAnswer", currentQuestion.getAnswerStr());
                checkAnswer();
            }
        });

        binding.btnOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswer = view.getTag().toString();
                Log.d("ini tag", selectedAnswer);
                Log.d("selectedAnswer", currentQuestion.getAnswerStr());
                checkAnswer();
            }
        });

        binding.btnOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswer = view.getTag().toString();
                Log.d("ini tag", selectedAnswer);
                Log.d("selectedAnswer", currentQuestion.getAnswerStr());
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
            binding.btnOption1Indicator.setBackgroundColor(Color.parseColor("#FFFCDB"));
            binding.btnOption2Indicator.setBackgroundColor(Color.parseColor("#FFFCDB"));
            binding.btnOption3Indicator.setBackgroundColor(Color.parseColor("#FFFCDB"));
            binding.btnOption4Indicator.setBackgroundColor(Color.parseColor("#FFFCDB"));
            binding.btnLanjut.setVisibility(View.INVISIBLE);

            currentQuestion = questionList.get(questionCounter);
            binding.tvSoal.setText(currentQuestion.getQuestion());
            /*Glide.with(LearnNumberActivity.this)
                    .load(Integer.valueOf(currentQuestion.getQuestion()))
                    .into(binding.ivSoal);*/
            Log.d("questionnya", currentQuestion.getQuestion());

            // Set TAG buat cek jawaban
            binding.btnOption1.setTag(Integer.valueOf(currentQuestion.getOption1()));
            binding.btnOption2.setTag(Integer.valueOf(currentQuestion.getOption2()));
            binding.btnOption3.setTag(Integer.valueOf(currentQuestion.getOption3()));
            binding.btnOption4.setTag(Integer.valueOf(currentQuestion.getOption4()));

            // Set ImageResource nya
            binding.btnOption1.setImageResource(Integer.valueOf(currentQuestion.getOption1()));
            binding.btnOption2.setImageResource(Integer.valueOf(currentQuestion.getOption2()));
            binding.btnOption3.setImageResource(Integer.valueOf(currentQuestion.getOption3()));
            binding.btnOption4.setImageResource(Integer.valueOf(currentQuestion.getOption4()));

            questionCounter++;

        } else {
            Intent intent = new Intent(LearnNumberActivity.this, FunFactActivity.class);
            intent.putExtra("SCORE", score);
            intent.putExtra("DIFFICULTY", difficulty);
            intent.putExtra("CATEGORY", category);
            intent.putExtra("NAMA", nama);
            startActivity(intent);
            finish();
        }
    }

    private void showSolution() {
        binding.btnOption1Indicator.setBackgroundColor(Color.RED);
        binding.btnOption2Indicator.setBackgroundColor(Color.RED);
        binding.btnOption3Indicator.setBackgroundColor(Color.RED);
        binding.btnOption4Indicator.setBackgroundColor(Color.RED);

        String btn1str, btn2str, btn3str, btn4str;

        btn1str = String.valueOf(binding.btnOption1.getTag());
        btn2str = String.valueOf(binding.btnOption2.getTag());
        btn3str = String.valueOf(binding.btnOption3.getTag());
        btn4str = String.valueOf(binding.btnOption4.getTag());

        if (btn1str.equals(currentQuestion.getAnswerStr())) {
            binding.btnOption1Indicator.setBackgroundColor(Color.GREEN);
        } else if (btn2str.equals(currentQuestion.getAnswerStr())) {
            binding.btnOption2Indicator.setBackgroundColor(Color.GREEN);
        } else if (btn3str.equals(currentQuestion.getAnswerStr())) {
            binding.btnOption3Indicator.setBackgroundColor(Color.GREEN);
        } else if (btn4str.equals(currentQuestion.getAnswerStr())) {
            binding.btnOption4Indicator.setBackgroundColor(Color.GREEN);
        }
    }
}