package com.example.kresnaapps.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.kresnaapps.R;
import com.example.kresnaapps.databinding.ActivitySoalGambarJawabanGambarBinding;
import com.example.kresnaapps.db.Question;
import com.example.kresnaapps.db.QuizDbHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SoalGambarJawabanGambarActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String LEVEL_NUMBER = "levelNumber";
    public static final String LEVEL_ADD = "levelAdd";
    public static final String LEVEL_SUBS = "levelSubs";
    public static final String LEVEL_MULTI = "levelMulti";
    public static final String LEVEL_SOCIAL = "levelSocial";
    public static final String LEVEL_QUIZ = "levelQuiz";

    private ActivitySoalGambarJawabanGambarBinding binding;
    private List<Question> questionList;
    private ArrayList<Integer> arraySalah;
    private Question currentQuestion;
    private int questionCounter, questionCountTotal, score, category, salah, levelNumber,
            levelAdd, levelSubs, levelMulti, levelSocial, levelQuiz;
    private String selectedAnswer, difficulty, nama;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySoalGambarJawabanGambarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        difficulty = getIntent().getExtras().getString("DIFFICULTY");
        category = getIntent().getExtras().getInt("CATEGORY", 0);
        nama = getIntent().getExtras().getString("NAMA");
        ambilSharedPrefs();

        arraySalah = new ArrayList<Integer>();

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

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void checkAnswer() {

        binding.btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopPlaying();
                showNextQuestion();
            }
        });

        if (selectedAnswer.equals(currentQuestion.getAnswerStr())) {
            score++;

            binding.btnOption1.setEnabled(false);
            binding.btnOption2.setEnabled(false);
            binding.btnLanjut.setVisibility(View.VISIBLE);

            arraySalah.add(salah);

            startPlayingBenar();
            showSolution();

        } else {
            salah++;
            startPlayingSalah();
        }
    }

    @SuppressLint("ResourceType")
    private void showNextQuestion() {

        if (questionCounter < questionCountTotal) {

            salah = 0;

            binding.btnOption1.setEnabled(true);
            binding.btnOption2.setEnabled(true);
            binding.btnOption1Indicator.setBackgroundColor(Color.parseColor("#FFFCDB"));
            binding.btnOption2Indicator.setBackgroundColor(Color.parseColor("#FFFCDB"));
            binding.btnLanjut.setVisibility(View.INVISIBLE);

            currentQuestion = questionList.get(questionCounter);

            // SET NOMOR SOAL
            binding.tvNomorSoal.setText(String.format("Soal %d", questionCounter + 1));

            // SET VOICE OVER
            startPlayingSoal(currentQuestion.getVoiceOver());

            // SET SOAL
            binding.ivSoal.setImageResource(Integer.valueOf(currentQuestion.getQuestion()));

            Log.d("questionnya", currentQuestion.getQuestion());

            // Set TAG buat cek jawaban
            binding.btnOption1.setTag(Integer.valueOf(currentQuestion.getOption1()));
            binding.btnOption2.setTag(Integer.valueOf(currentQuestion.getOption2()));

            // SET TEXT JAWABAN
            binding.btnOption1.setImageResource(Integer.valueOf(currentQuestion.getOption1()));
            binding.btnOption2.setImageResource(Integer.valueOf(currentQuestion.getOption2()));

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
                case 6:
                    Intent intent = new Intent(SoalGambarJawabanGambarActivity.this, FunFactActivity.class);
                    intent.putExtra("SCORE", score);
                    intent.putExtra("DIFFICULTY", difficulty);
                    intent.putExtra("CATEGORY", category);
                    intent.putExtra("NAMA", nama);
                    intent.putExtra("ARRAY_SALAH", (Serializable) arraySalah);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    }

    private void showSolution() {
        binding.btnOption1Indicator.setBackgroundColor(Color.parseColor("#B64A44"));
        binding.btnOption2Indicator.setBackgroundColor(Color.parseColor("#B64A44"));

        String btn1str, btn2str;

        btn1str = String.valueOf(binding.btnOption1.getTag());
        btn2str = String.valueOf(binding.btnOption2.getTag());

        if (btn1str.equals(currentQuestion.getAnswerStr())) {
            binding.btnOption1Indicator.setBackgroundColor(Color.parseColor("#5EAE5E"));
        } else if (btn2str.equals(currentQuestion.getAnswerStr())) {
            binding.btnOption2Indicator.setBackgroundColor(Color.parseColor("#5EAE5E"));
        }
    }

    private void openDialog() {
        final Dialog dialog = new Dialog(SoalGambarJawabanGambarActivity.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_dialog_box);
        dialog.show();
        Button btn_yes = dialog.findViewById(R.id.btn_yes);
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                Intent intent = new Intent(SoalGambarJawabanGambarActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Button btn_no = dialog.findViewById(R.id.btn_no);
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
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
        levelAdd = sharedPreferences.getInt(LEVEL_ADD, 0);
        levelSubs = sharedPreferences.getInt(LEVEL_SUBS, 0);
        levelMulti = sharedPreferences.getInt(LEVEL_MULTI, 0);
        levelSocial = sharedPreferences.getInt(LEVEL_SOCIAL, 0);
        levelQuiz = sharedPreferences.getInt(LEVEL_QUIZ, 0);
    }

    private void levelUpNumber() {
        Intent intent = new Intent(SoalGambarJawabanGambarActivity.this, FunFactActivity.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("DIFFICULTY", difficulty);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("NAMA", nama);
        intent.putExtra("ARRAY_SALAH", (Serializable) arraySalah);

        if (difficulty.equals("easy") && levelNumber < 1) {
            levelNumber++;
            saveSharedPrefs();
            startActivity(intent);
            finish();
        } else if (difficulty.equals("medium") && levelNumber < 2) {
            levelNumber++;
            saveSharedPrefs();
            startActivity(intent);
            finish();
        } else {
            startActivity(intent);
            finish();
        }
    }

    private void levelUpAdd() {
        Intent intent = new Intent(SoalGambarJawabanGambarActivity.this, FunFactActivity.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("DIFFICULTY", difficulty);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("NAMA", nama);
        intent.putExtra("ARRAY_SALAH", (Serializable) arraySalah);

        if (difficulty.equals("easy") && levelAdd < 1) {
            levelAdd++;
            saveSharedPrefs();
            startActivity(intent);
            finish();
        } else if (difficulty.equals("medium") && levelAdd < 2) {
            levelAdd++;
            saveSharedPrefs();
            startActivity(intent);
            finish();
        } else {
            startActivity(intent);
            finish();
        }
    }

    private void levelUpSubs() {
        Intent intent = new Intent(SoalGambarJawabanGambarActivity.this, FunFactActivity.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("DIFFICULTY", difficulty);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("NAMA", nama);
        intent.putExtra("ARRAY_SALAH", (Serializable) arraySalah);

        if (difficulty.equals("easy") && levelSubs < 1) {
            levelSubs++;
            saveSharedPrefs();
            startActivity(intent);
            finish();
        } else if (difficulty.equals("medium") && levelSubs < 2) {
            levelSubs++;
            saveSharedPrefs();
            startActivity(intent);
            finish();
        } else {
            startActivity(intent);
            finish();
        }
    }

    private void levelUpMulti() {
        Intent intent = new Intent(SoalGambarJawabanGambarActivity.this, FunFactActivity.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("DIFFICULTY", difficulty);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("NAMA", nama);
        intent.putExtra("ARRAY_SALAH", (Serializable) arraySalah);

        if (difficulty.equals("easy") && levelMulti < 1) {
            levelMulti++;
            saveSharedPrefs();
            startActivity(intent);
            finish();
        } else if (difficulty.equals("medium") && levelMulti < 2) {
            levelMulti++;
            saveSharedPrefs();
            startActivity(intent);
            finish();
        } else {
            startActivity(intent);
            finish();
        }
    }

    private void stopPlaying() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void startPlayingBenar() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(SoalGambarJawabanGambarActivity.this, R.raw.jawaban_benar);
            mediaPlayer.start();
        } else if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            startPlayingBenar();
        }
    }

    private void startPlayingSalah() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(SoalGambarJawabanGambarActivity.this, R.raw.jawaban_salah);
            mediaPlayer.start();
        } else if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            startPlayingSalah();
        }
    }

    private void startPlayingSoal(String voiceOver) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(SoalGambarJawabanGambarActivity.this, Integer.valueOf(voiceOver));
            mediaPlayer.start();
        } else if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            startPlayingSoal(voiceOver);
        }
    }
}