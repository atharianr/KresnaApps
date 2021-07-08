package com.example.kresnaapps;

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
import android.widget.Toast;

import com.example.kresnaapps.databinding.ActivityLearnNumberBinding;
import com.example.kresnaapps.databinding.ActivitySoalGambarJawabanTextBinding;
import com.example.kresnaapps.databinding.ActivitySoalTextJawabanTextBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SoalTextJawabanTextActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String LEVEL_NUMBER = "levelNumber";
    public static final String LEVEL_ADD = "levelAdd";
    public static final String LEVEL_SUBS = "levelSubs";
    public static final String LEVEL_MULTI = "levelMulti";
    public static final String LEVEL_SOCIAL = "levelSocial";
    public static final String LEVEL_QUIZ = "levelQuiz";

    private ActivitySoalTextJawabanTextBinding binding;
    private List<Question> questionList;
    private ArrayList<Integer> arraySalah;
    private Question currentQuestion;
    private int questionCounter, questionCountTotal, score, category, salah, levelNumber,
            levelAdd, levelSubs, levelMulti, levelSocial, levelQuiz;
    private String selectedAnswer, difficulty, nama;
    private MediaPlayer mediaPlayer, mediaPlayerKategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySoalTextJawabanTextBinding.inflate(getLayoutInflater());
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
        //startPlayingKategori();
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
    }

    private void checkAnswer() {
        binding.tvSalah.setText("Salah: " + salah);

        binding.btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopPlaying();
                showNextQuestion();
            }
        });

        if (selectedAnswer.equals(currentQuestion.getAnswerStr())) {
            Toast.makeText(SoalTextJawabanTextActivity.this, "Benar!", Toast.LENGTH_SHORT).show();
            score++;
            binding.tvScore.setText("Score: " + score);

            binding.btnOption1.setEnabled(false);
            binding.btnOption2.setEnabled(false);
            binding.btnLanjut.setVisibility(View.VISIBLE);

            arraySalah.add(salah);
            binding.tvArraySalah.setText("Array Salah: " + arraySalah.toString());

            startPlayingBenar();
            showSolution();

        } else {
            salah++;
            Toast.makeText(SoalTextJawabanTextActivity.this, "Salah!", Toast.LENGTH_SHORT).show();
            binding.tvScore.setText("Score: " + score);
            binding.tvSalah.setText("Salah: " + salah);

            startPlayingSalah();
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
            binding.btnOption1Indicator.setBackgroundColor(Color.parseColor("#FFFCDB"));
            binding.btnOption2Indicator.setBackgroundColor(Color.parseColor("#FFFCDB"));
            binding.btnLanjut.setVisibility(View.INVISIBLE);

            currentQuestion = questionList.get(questionCounter);

            // SET SOAL
            binding.tvSoal.setText(currentQuestion.getQuestion());

            Log.d("questionnya", currentQuestion.getQuestion());

            // Set TAG buat cek jawaban
            binding.btnOption1.setTag(Integer.valueOf(currentQuestion.getOption1()));
            binding.btnOption2.setTag(Integer.valueOf(currentQuestion.getOption2()));

            // SET TEXT JAWABAN
            binding.btnOption1.setText(currentQuestion.getOption1());
            binding.btnOption2.setText(currentQuestion.getOption2());

            questionCounter++;

        } else {
            Intent intent = new Intent(SoalTextJawabanTextActivity.this, FunFactActivity.class);
            intent.putExtra("SCORE", score);
            intent.putExtra("DIFFICULTY", difficulty);
            intent.putExtra("CATEGORY", category);
            intent.putExtra("NAMA", nama);
            intent.putExtra("ARRAY_SALAH", (Serializable) arraySalah);
            startActivity(intent);
            finish();
        }
    }

    private void showSolution() {
        binding.btnOption1Indicator.setBackgroundColor(Color.parseColor("#B64A44"));
        binding.btnOption2Indicator.setBackgroundColor(Color.parseColor("#B64A44"));

        String btn1str, btn2str;

        btn1str = String.valueOf(binding.btnOption1.getText());
        btn2str = String.valueOf(binding.btnOption2.getText());

        if (btn1str.equals(currentQuestion.getAnswerStr())) {
            binding.btnOption1Indicator.setBackgroundColor(Color.parseColor("#5EAE5E"));
        } else if (btn2str.equals(currentQuestion.getAnswerStr())) {
            binding.btnOption2Indicator.setBackgroundColor(Color.parseColor("#5EAE5E"));
        }
    }

    private void openDialog() {
        final Dialog dialog = new Dialog(SoalTextJawabanTextActivity.this);
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
        levelAdd = sharedPreferences.getInt(LEVEL_ADD, 0);
        levelSubs = sharedPreferences.getInt(LEVEL_SUBS, 0);
        levelMulti = sharedPreferences.getInt(LEVEL_MULTI, 0);
        levelSocial = sharedPreferences.getInt(LEVEL_SOCIAL, 0);
        levelQuiz = sharedPreferences.getInt(LEVEL_QUIZ, 0);
        Toast.makeText(SoalTextJawabanTextActivity.this, String.valueOf(levelNumber), Toast.LENGTH_SHORT).show();
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
            mediaPlayer = MediaPlayer.create(SoalTextJawabanTextActivity.this, R.raw.jawaban_benar);
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
            mediaPlayer = MediaPlayer.create(SoalTextJawabanTextActivity.this, R.raw.jawaban_salah);
            mediaPlayer.start();
        } else if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            startPlayingSalah();
        }
    }
}