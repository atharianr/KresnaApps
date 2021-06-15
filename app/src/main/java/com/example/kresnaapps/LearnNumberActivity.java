package com.example.kresnaapps;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.kresnaapps.databinding.ActivityLearnNumberBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LearnNumberActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String LEVEL_NUMBER = "levelNumber";

    private ActivityLearnNumberBinding binding;
    private List<Question> questionList;
    private ArrayList<Integer> arraySalah;
    private Question currentQuestion;
    private int questionCounter, questionCountTotal, score, category, salah, levelNumber;
    private String selectedAnswer, difficulty, nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLearnNumberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        difficulty = getIntent().getExtras().getString("DIFFICULTY");
        category = getIntent().getExtras().getInt("CATEGORY", 0);
        nama = getIntent().getExtras().getString("NAMA");
        ambilSharedPrefs();

        arraySalah = new ArrayList<Integer>();

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

        binding.tvSalah.setText("Salah: " + salah);

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

            binding.btnOption1.setEnabled(false);
            binding.btnOption2.setEnabled(false);
            binding.btnOption3.setEnabled(false);
            binding.btnOption4.setEnabled(false);
            binding.btnLanjut.setVisibility(View.VISIBLE);

            arraySalah.add(salah);
            binding.tvArraySalah.setText("Array Salah: " + arraySalah.toString());

            showSolution();
        } else {
            salah++;
            Toast.makeText(LearnNumberActivity.this, "Salah!", Toast.LENGTH_SHORT).show();
            binding.tvScore.setText("Score: " + score);
            binding.tvSalah.setText("Salah: " + salah);
        }
    }

    @SuppressLint("ResourceType")
    private void showNextQuestion() {

        if (questionCounter < questionCountTotal) {

            salah = 0;
            binding.tvSalah.setText("Salah: " + salah);
            binding.tvArraySalah.setText("Array Salah: " + arraySalah.toString());

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
            intent.putExtra("ARRAY_SALAH", (Serializable) arraySalah);

            if (difficulty.equals("easy") && levelNumber < 1) {
                levelNumber++;
                Toast.makeText(LearnNumberActivity.this, String.valueOf(levelNumber), Toast.LENGTH_SHORT).show();
                saveSharedPrefs();
                startActivity(intent);
                finish();
            } else if (difficulty.equals("medium") && levelNumber < 2) {
                levelNumber++;
                Toast.makeText(LearnNumberActivity.this, String.valueOf(levelNumber), Toast.LENGTH_SHORT).show();
                saveSharedPrefs();
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LearnNumberActivity.this, String.valueOf(levelNumber), Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        }
    }

    private void showSolution() {
        binding.btnOption1Indicator.setBackgroundColor(Color.parseColor("#B64A44"));
        binding.btnOption2Indicator.setBackgroundColor(Color.parseColor("#B64A44"));
        binding.btnOption3Indicator.setBackgroundColor(Color.parseColor("#B64A44"));
        binding.btnOption4Indicator.setBackgroundColor(Color.parseColor("#B64A44"));

        String btn1str, btn2str, btn3str, btn4str;

        btn1str = String.valueOf(binding.btnOption1.getTag());
        btn2str = String.valueOf(binding.btnOption2.getTag());
        btn3str = String.valueOf(binding.btnOption3.getTag());
        btn4str = String.valueOf(binding.btnOption4.getTag());

        if (btn1str.equals(currentQuestion.getAnswerStr())) {
            binding.btnOption1Indicator.setBackgroundColor(Color.parseColor("#5EAE5E"));
        } else if (btn2str.equals(currentQuestion.getAnswerStr())) {
            binding.btnOption2Indicator.setBackgroundColor(Color.parseColor("#5EAE5E"));
        } else if (btn3str.equals(currentQuestion.getAnswerStr())) {
            binding.btnOption3Indicator.setBackgroundColor(Color.parseColor("#5EAE5E"));
        } else if (btn4str.equals(currentQuestion.getAnswerStr())) {
            binding.btnOption4Indicator.setBackgroundColor(Color.parseColor("#5EAE5E"));
        }
    }

    private void openDialog() {
        final Dialog dialog = new Dialog(LearnNumberActivity.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_dialog_box);
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        openDialog();
        return;
    }

    private void saveSharedPrefs() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(LEVEL_NUMBER, levelNumber);
        editor.apply();
    }

    private void ambilSharedPrefs() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        levelNumber = sharedPreferences.getInt(LEVEL_NUMBER, 0);
        Toast.makeText(LearnNumberActivity.this, String.valueOf(levelNumber), Toast.LENGTH_SHORT).show();
    }
}