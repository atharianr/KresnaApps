package com.example.kresnaapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.kresnaapps.databinding.ActivityFunFactBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FunFactActivity extends AppCompatActivity {

    private ActivityFunFactBinding binding;
    private List<Question> questionList;
    private int score, category, mediaPlayerState, tipeSoal;
    private String difficulty, nama;
    private MediaPlayer mediaPlayer;
    private ArrayList<Integer> arraySalah;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter salahAdapter;
    private String[] judul = new String[]{
            "Rencong", // number
            "Pesa'an", // add
            "Joglo",
            "Keris",
            "Baju Bodo", // subs
            "Rumah Gadang",
            "Keris Bugis",
            "Baju Adat Salempang", // multi
            "Bolon",
            "Kerambit",
            "Klepon", // social
            "Honai", // quiz
    };
    private String[] desc = new String[]{
            //number
            "Rencong atau yang dalam Bahasa Aceh yang ditulis dengan huruf latin dibaca Rintjong adalah senjata khas Suku Aceh. Rencong merupakan simbol identitas diri, keberanian, dan ketangguhan Suku Aceh. Ada beberapa jenis rencong. Pertama Rencong Pudoi yaitu Rencong yang belum sempurna pada bentuk gagangnya.",
            // add
            "Pesa'an adalah baju adat khas dari Madura, provinsi Jawa Timur.Baju Pesa'an menjadi salah satu simbol utama yang menjadi wakil budaya baju adat Jawa Timur di Nusantara. Baju Pesa'an ini bisa digunakan pada acara-acara penting masyarakat Madura seperti acara upacara pernikahan ataupun acara penting Iainnya.",
            "Joglo adalah rumah tradisional masyarakat Jawa atau daerah lain di Indonesia yang terdiri atas 4 tiang utama. Rumah tradisional Jawa terbagi menjadi dua bagian, yakni rumah induk dan rumah tambahan.",
            "Keris adalah sejenis senjata tajam yang memiliki tempat terhormat dalam masyarakat Jawa. la tidak hanya berfungsi sebagai senjata, namun juga sebagai perlengkapan busana, simbol status, pemberi kewibawaan, dan sebagai perlengkapan dalam upacara adat.",
            // subs
            "Baju Bodo merupakan pakaian adat suku Bugis -Makassar yang berasal dari Provinsi Sulawesi Selatan. Baju adat tersebut dikenal sebagai salah satu busana tertua di dunia.",
            "Rumah Gadang adalah nama untuk rumah adat Minangkabau yang merupakan rumah tradisional dan banyak di jumpai di Provinsi Sumatra Barat, Indonesia. ",
            "Keris Bugis dan Keris Jawa amatlah mirip. Hanya saja pada beberapa keris Bugis,Iuk-nya tidak begitu kentara (malah ada gajang yang bentuknya lurus, hampir mirip badik).",
            // multi
            "Baju adat Salempang Simbol salempang menyiratkan makna bahwa wanita harus Iebih waspada terhadap segala kondisi dan mempunyai welas asih terhadap anak dan cucunya.",
            "Bolon adalah rumah adat khas Batak yang juga menjadi simbol status sosial masyarakat Tapanuli. Rumah adat ini berbentuk empat persegi panjang dengan denah dalamnya merupakan ruangan terbuka tanpa kamar atau pun sekat pemisah.",
            "Kerambit adalah salah satu dari daftar nama senjata tradisional Indonesia yang Iebih terkenal dibandingkan senjata Sumatera Utara Iainnya sebab serangan yang disebabkan kerambit sangat mematikan bahkan sampai diadopsi US Marshal sebagai senjata wajib tentaranya.",
            // social
            "Klepon atau kelepon adalah sejenis makanan tradisional atau kue tradisional Indonesia yang termasuk ke dalam kelompok jajanan pasar. Makanan enak ini terbuat dari tepung beras ketan yang dibentuk seperti bola-bola kecil dan diisi dengan gula merah lalu direbus dalam air mendidih.",
            // quiz
            "Honai merupakan rumah adat Papua khususnya di Bagian Pengunungan.... Di mana bentuk dasar rumah Honai adalah Iingkaran dengan rangka dari kayu dan berdinding anyaman serta atap kerucut yang terbuat dari jerami.",
    };

    private Object[] voDesc = new Object[]{
            // num
            null,
            // add
            R.raw.dae,
            R.raw.dam,
            R.raw.dah,
            // subs
            R.raw.dse,
            R.raw.dsm,
            R.raw.dsh,
            // multi
            R.raw.dme,
            R.raw.dmm,
            R.raw.dmh,
            // soc
            R.raw.de,
            // quiz
            R.raw.dq
    };

    private Object[] gambar = new Object[]{
            // num
            R.drawable.funfact_rencang,
            // add
            R.drawable.fae,
            R.drawable.fam,
            R.drawable.fah,
            // subs
            R.drawable.fse,
            R.drawable.fsm,
            R.drawable.fsh,
            // multi
            R.drawable.fme,
            R.drawable.fmm,
            R.drawable.fmh,
            // soc
            R.drawable.fe,
            // quiz
            R.drawable.fq,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFunFactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        difficulty = getIntent().getExtras().getString("DIFFICULTY");
        category = getIntent().getExtras().getInt("CATEGORY", 0);
        score = getIntent().getExtras().getInt("SCORE", 0);
        nama = getIntent().getExtras().getString("NAMA");

        //arraySalah = new ArrayList<Integer>();
        arraySalah = (ArrayList<Integer>) getIntent().getSerializableExtra("ARRAY_SALAH");

        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        questionList = dbHelper.getQuestion(category, difficulty);

        tampilFunFact();

        mediaPlayerState = 0;

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.rvNilai.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        salahAdapter = new SalahAdapter(arraySalah);
        binding.rvNilai.setLayoutManager(layoutManager);
        binding.rvNilai.setAdapter(salahAdapter);
    }

    private void tampilFunFact() {
        switch (category) {
            case 1:
                tipeSoal = 0;
                binding.ivFunFact.setImageResource(Integer.valueOf((Integer) gambar[tipeSoal]));
                binding.tvJudul.setText(judul[tipeSoal]);
                binding.tvDeskripsi.setText(desc[tipeSoal]);
                break;
            case 2:
                if (difficulty.equals("easy")) {
                    tipeSoal = 1;
                    binding.ivFunFact.setImageResource(Integer.valueOf((Integer) gambar[tipeSoal]));
                    binding.tvJudul.setText(judul[tipeSoal]);
                    binding.tvDeskripsi.setText(desc[tipeSoal]);
                    binding.btnPlayAudio.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (mediaPlayerState == 0) {
                                mediaPlayerState++;
                                startPlaying(voDesc[tipeSoal]);
                            } else {
                                mediaPlayerState = 0;
                                stopPlaying();
                            }
                        }
                    });
                } else if (difficulty.equals("medium")) {
                    tipeSoal = 2;
                    binding.ivFunFact.setImageResource(Integer.valueOf((Integer) gambar[tipeSoal]));
                    binding.tvJudul.setText(judul[tipeSoal]);
                    binding.tvDeskripsi.setText(desc[tipeSoal]);
                    binding.btnPlayAudio.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (mediaPlayerState == 0) {
                                mediaPlayerState++;
                                startPlaying(voDesc[tipeSoal]);
                            } else {
                                mediaPlayerState = 0;
                                stopPlaying();
                            }
                        }
                    });
                } else {
                    tipeSoal = 3;
                    binding.ivFunFact.setImageResource(Integer.valueOf((Integer) gambar[tipeSoal]));
                    binding.tvJudul.setText(judul[tipeSoal]);
                    binding.tvDeskripsi.setText(desc[tipeSoal]);
                    binding.btnPlayAudio.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (mediaPlayerState == 0) {
                                mediaPlayerState++;
                                startPlaying(voDesc[tipeSoal]);
                            } else {
                                mediaPlayerState = 0;
                                stopPlaying();
                            }
                        }
                    });
                }
                break;
            case 3:
                if (difficulty.equals("easy")) {
                    tipeSoal = 4;
                    binding.ivFunFact.setImageResource(Integer.valueOf((Integer) gambar[tipeSoal]));
                    binding.tvJudul.setText(judul[tipeSoal]);
                    binding.tvDeskripsi.setText(desc[tipeSoal]);
                    binding.btnPlayAudio.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (mediaPlayerState == 0) {
                                mediaPlayerState++;
                                startPlaying(voDesc[tipeSoal]);
                            } else {
                                mediaPlayerState = 0;
                                stopPlaying();
                            }
                        }
                    });
                } else if (difficulty.equals("medium")) {
                    tipeSoal = 5;
                    binding.ivFunFact.setImageResource(Integer.valueOf((Integer) gambar[tipeSoal]));
                    binding.tvJudul.setText(judul[tipeSoal]);
                    binding.tvDeskripsi.setText(desc[tipeSoal]);
                    binding.btnPlayAudio.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (mediaPlayerState == 0) {
                                mediaPlayerState++;
                                startPlaying(voDesc[tipeSoal]);
                            } else {
                                mediaPlayerState = 0;
                                stopPlaying();
                            }
                        }
                    });
                } else {
                    tipeSoal = 6;
                    binding.ivFunFact.setImageResource(Integer.valueOf((Integer) gambar[tipeSoal]));
                    binding.tvJudul.setText(judul[tipeSoal]);
                    binding.tvDeskripsi.setText(desc[tipeSoal]);
                    binding.btnPlayAudio.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (mediaPlayerState == 0) {
                                mediaPlayerState++;
                                startPlaying(voDesc[tipeSoal]);
                            } else {
                                mediaPlayerState = 0;
                                stopPlaying();
                            }
                        }
                    });
                }
                break;
            case 4:
                if (difficulty.equals("easy")) {
                    tipeSoal = 7;
                    binding.ivFunFact.setImageResource(Integer.valueOf((Integer) gambar[tipeSoal]));
                    binding.tvJudul.setText(judul[tipeSoal]);
                    binding.tvDeskripsi.setText(desc[tipeSoal]);
                    binding.btnPlayAudio.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (mediaPlayerState == 0) {
                                mediaPlayerState++;
                                startPlaying(voDesc[tipeSoal]);
                            } else {
                                mediaPlayerState = 0;
                                stopPlaying();
                            }
                        }
                    });
                } else if (difficulty.equals("medium")) {
                    tipeSoal = 8;
                    binding.ivFunFact.setImageResource(Integer.valueOf((Integer) gambar[tipeSoal]));
                    binding.tvJudul.setText(judul[tipeSoal]);
                    binding.tvDeskripsi.setText(desc[tipeSoal]);
                    binding.btnPlayAudio.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (mediaPlayerState == 0) {
                                mediaPlayerState++;
                                startPlaying(voDesc[tipeSoal]);
                            } else {
                                mediaPlayerState = 0;
                                stopPlaying();
                            }
                        }
                    });
                } else {
                    tipeSoal = 9;
                    binding.ivFunFact.setImageResource(Integer.valueOf((Integer) gambar[tipeSoal]));
                    binding.tvJudul.setText(judul[tipeSoal]);
                    binding.tvDeskripsi.setText(desc[tipeSoal]);
                    binding.btnPlayAudio.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (mediaPlayerState == 0) {
                                mediaPlayerState++;
                                startPlaying(voDesc[tipeSoal]);
                            } else {
                                mediaPlayerState = 0;
                                stopPlaying();
                            }
                        }
                    });
                }
                break;
            case 5:
                tipeSoal = 10;
                binding.ivFunFact.setImageResource(Integer.valueOf((Integer) gambar[tipeSoal]));
                binding.tvJudul.setText(judul[tipeSoal]);
                binding.tvDeskripsi.setText(desc[tipeSoal]);
                binding.btnPlayAudio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mediaPlayerState == 0) {
                            mediaPlayerState++;
                            startPlaying(voDesc[tipeSoal]);
                        } else {
                            mediaPlayerState = 0;
                            stopPlaying();
                        }
                    }
                });
                break;
            case 6:
                tipeSoal = 11;
                binding.ivFunFact.setImageResource(Integer.valueOf((Integer) gambar[tipeSoal]));
                binding.tvJudul.setText(judul[tipeSoal]);
                binding.tvDeskripsi.setText(desc[tipeSoal]);
                binding.btnPlayAudio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mediaPlayerState == 0) {
                            mediaPlayerState++;
                            startPlaying(voDesc[tipeSoal]);
                        } else {
                            mediaPlayerState = 0;
                            stopPlaying();
                        }
                    }
                });
                break;
        }
    }

    private void stopPlaying() {
        if (mediaPlayer != null) {
            binding.btnPlayAudio.setImageResource(R.drawable.sound);
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void startPlaying(Object voDescs) {
        if (mediaPlayer == null) {
            binding.btnPlayAudio.setImageResource(R.drawable.ic_baseline_stop_24);
            mediaPlayer = MediaPlayer.create(FunFactActivity.this, Integer.valueOf((Integer) voDescs));
            mediaPlayer.start();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FunFactActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void intentDataKeAdapter() {
        Intent intent = new Intent(FunFactActivity.this, SalahAdapter.class);
        intent.putExtra("ARRAY_SALAH", (Serializable) arraySalah);
    }
}