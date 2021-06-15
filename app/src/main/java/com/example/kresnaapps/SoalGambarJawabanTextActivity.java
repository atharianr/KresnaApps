package com.example.kresnaapps;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.kresnaapps.databinding.ActivityLearnNumberBinding;
import com.example.kresnaapps.databinding.ActivitySoalGambarJawabanTextBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SoalGambarJawabanTextActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String LEVEL_NUMBER = "levelNumber";
    public static final String LEVEL_ADD = "levelAdd";
    public static final String LEVEL_SUBS = "levelSubs";
    public static final String LEVEL_MULTI = "levelMulti";
    public static final String LEVEL_SOCIAL = "levelSocial";
    public static final String LEVEL_QUIZ = "levelQuiz";

    private ActivitySoalGambarJawabanTextBinding binding;
    private List<Question> questionList;
    private ArrayList<Integer> arraySalah;
    private Question currentQuestion;
    private int questionCounter, questionCountTotal, score, category, salah, levelNumber,
            levelAdd, levelSubs, levelMulti, levelSocial, levelQuiz;
    private String selectedAnswer, difficulty, nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySoalGambarJawabanTextBinding.inflate(getLayoutInflater());
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
            Toast.makeText(SoalGambarJawabanTextActivity.this, "Benar!", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(SoalGambarJawabanTextActivity.this, "Salah!", Toast.LENGTH_SHORT).show();
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
            binding.ivSoal.setImageResource(Integer.valueOf(currentQuestion.getQuestion()));

            Log.d("questionnya", currentQuestion.getQuestion());

            // Set TAG buat cek jawaban
            binding.btnOption1.setTag(Integer.valueOf(currentQuestion.getOption1()));
            binding.btnOption2.setTag(Integer.valueOf(currentQuestion.getOption2()));
            binding.btnOption3.setTag(Integer.valueOf(currentQuestion.getOption3()));
            binding.btnOption4.setTag(Integer.valueOf(currentQuestion.getOption4()));

            // SET TEXT JAWABAN
            binding.btnOption1.setText(currentQuestion.getOption1());
            binding.btnOption2.setText(currentQuestion.getOption2());
            binding.btnOption3.setText(currentQuestion.getOption3());
            binding.btnOption4.setText(currentQuestion.getOption4());

            questionCounter++;

        } else {
            switch (category) {
                case 1:
                    levelUpNumber();
                    break;
                case 2:
                    levelUpAdd();
                    break;
                case 3:
                    levelUpSubs();
                    break;
                case 4:
                    levelUpMulti();
                    break;
            }
        }
    }

    private void showSolution() {
        binding.btnOption1Indicator.setBackgroundColor(Color.parseColor("#B64A44"));
        binding.btnOption2Indicator.setBackgroundColor(Color.parseColor("#B64A44"));
        binding.btnOption3Indicator.setBackgroundColor(Color.parseColor("#B64A44"));
        binding.btnOption4Indicator.setBackgroundColor(Color.parseColor("#B64A44"));

        String btn1str, btn2str, btn3str, btn4str;

        btn1str = String.valueOf(binding.btnOption1.getText());
        btn2str = String.valueOf(binding.btnOption2.getText());
        btn3str = String.valueOf(binding.btnOption3.getText());
        btn4str = String.valueOf(binding.btnOption4.getText());

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
        final Dialog dialog = new Dialog(SoalGambarJawabanTextActivity.this);
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
        editor.putInt(LEVEL_ADD, levelAdd);
        editor.putInt(LEVEL_SUBS, levelSubs);
        editor.putInt(LEVEL_MULTI, levelMulti);
        editor.putInt(LEVEL_SOCIAL, levelSocial);
        editor.putInt(LEVEL_QUIZ, levelQuiz);
        editor.apply();
    }

    private void ambilSharedPrefs() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        levelNumber = sharedPreferences.getInt(LEVEL_NUMBER, 0);
        Toast.makeText(SoalGambarJawabanTextActivity.this, String.valueOf(levelNumber), Toast.LENGTH_SHORT).show();
    }

    private void levelUpNumber() {
    }

    private void levelUpAdd() {
        Intent intent = new Intent(SoalGambarJawabanTextActivity.this, FunFactActivity.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("DIFFICULTY", difficulty);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("NAMA", nama);
        intent.putExtra("ARRAY_SALAH", (Serializable) arraySalah);

        if (difficulty.equals("easy") && levelAdd < 1) {
            levelAdd++;
            Toast.makeText(SoalGambarJawabanTextActivity.this, String.valueOf(levelAdd), Toast.LENGTH_SHORT).show();
            saveSharedPrefs();
            startActivity(intent);
            finish();
        } else if (difficulty.equals("medium") && levelAdd < 2) {
            levelAdd++;
            Toast.makeText(SoalGambarJawabanTextActivity.this, String.valueOf(levelAdd), Toast.LENGTH_SHORT).show();
            saveSharedPrefs();
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(SoalGambarJawabanTextActivity.this, String.valueOf(levelAdd), Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }
    }

    private void levelUpSubs() {
    }

    private void levelUpMulti() {
    }
}