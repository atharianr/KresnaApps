package com.example.kresnaapps.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kresnaapps.R;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "KresnaApps.db";
    private static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context){
        if (instance == null){
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORY_TABLE = "CREATE TABLE " +
                QuizContract.CategoriesTable.TABLE_NAME + " ( " +
                QuizContract.CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.CategoriesTable.COLUMN_CATEGORY + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTION_TABLE = "CREATE TABLE " +
                QuizContract.QuestionTable.TABLE_NAME + " ( " +
                QuizContract.QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_ANSWERSTR + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                QuizContract.QuestionTable.COLUMN_VOICE + " TEXT, " +
                "FOREIGN KEY(" + QuizContract.QuestionTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                QuizContract.CategoriesTable.TABLE_NAME + "(" + QuizContract.CategoriesTable._ID + ")ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORY_TABLE);
        db.execSQL(SQL_CREATE_QUESTION_TABLE);
        fillCategoryTable();
        fillQuestionTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoryTable() {
        Category c1 = new Category("Learn Numbers");
        addCategory(c1);
        Category c2 = new Category("Learn Addition");
        addCategory(c2);
        Category c3 = new Category("Learn Substraction");
        addCategory(c3);
        Category c4 = new Category("Learn Multiplication");
        addCategory(c4);
        Category c5 = new Category("Social Experience");
        addCategory(c5);
        Category c6 = new Category("Quiz");
        addCategory(c6);
    }

    private void addCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.CategoriesTable.COLUMN_CATEGORY, category.getCategory());
        db.insert(QuizContract.CategoriesTable.TABLE_NAME, null, cv);
    }


    private void fillQuestionTable() {
        // LEARN NUMBER - EASY
        Question qne1 = new Question(String.valueOf(R.drawable.qne1), "1", "2", "1", Question.DIFFICULTY_EASY, Category.LEARN_NUMBERS, String.valueOf(R.raw.vne));
        addQuestion(qne1);
        Question qne2 = new Question(String.valueOf(R.drawable.qne2), "2", "3", "2", Question.DIFFICULTY_EASY, Category.LEARN_NUMBERS, String.valueOf(R.raw.vne));
        addQuestion(qne2);
        Question qne3 = new Question(String.valueOf(R.drawable.qne3), "2", "3", "3", Question.DIFFICULTY_EASY, Category.LEARN_NUMBERS, String.valueOf(R.raw.vne));
        addQuestion(qne3);
        Question qne4 = new Question(String.valueOf(R.drawable.qne4), "3", "4", "4", Question.DIFFICULTY_EASY, Category.LEARN_NUMBERS, String.valueOf(R.raw.vne));
        addQuestion(qne4);

        // LEARN NUMBER - MEDIUM
        Question qnm1 = new Question(String.valueOf(R.drawable.qnm1), "4", "5", "5", Question.DIFFICULTY_MEDIUM, Category.LEARN_NUMBERS, String.valueOf(R.raw.vnm));
        addQuestion(qnm1);
        Question qnm2 = new Question(String.valueOf(R.drawable.qnm2), "5", "6", "6", Question.DIFFICULTY_MEDIUM, Category.LEARN_NUMBERS, String.valueOf(R.raw.vnm));
        addQuestion(qnm2);
        Question qnm3 = new Question(String.valueOf(R.drawable.qnm3), "7", "8", "7", Question.DIFFICULTY_MEDIUM, Category.LEARN_NUMBERS, String.valueOf(R.raw.vnm));
        addQuestion(qnm3);

        // LEARN NUMBER - HARD
        Question qnh1 = new Question(String.valueOf(R.drawable.qnh1), "7", "8", "8", Question.DIFFICULTY_HARD, Category.LEARN_NUMBERS, String.valueOf(R.raw.vnh));
        addQuestion(qnh1);
        Question qnh2 = new Question(String.valueOf(R.drawable.qnh2), "9", "8", "9", Question.DIFFICULTY_HARD, Category.LEARN_NUMBERS, String.valueOf(R.raw.vnh));
        addQuestion(qnh2);
        Question qnh3 = new Question(String.valueOf(R.drawable.qnh3), "10", "11", "10", Question.DIFFICULTY_HARD, Category.LEARN_NUMBERS, String.valueOf(R.raw.vnh));
        addQuestion(qnh3);

        // ADDITION - EASY (GAMBAR GAMBAR)
        Question qae1 = new Question(String.valueOf(R.drawable.qae1), String.valueOf(R.drawable.ae8), String.valueOf(R.drawable.ae9),
                String.valueOf(R.drawable.ae8), Question.DIFFICULTY_EASY, Category.LEARN_ADDITION, String.valueOf(R.raw.vah));
        addQuestion(qae1);
        Question qae2 = new Question(String.valueOf(R.drawable.qae2), String.valueOf(R.drawable.ae8), String.valueOf(R.drawable.ae9),
                String.valueOf(R.drawable.ae9), Question.DIFFICULTY_EASY, Category.LEARN_ADDITION, String.valueOf(R.raw.vah));
        addQuestion(qae2);
        Question qae3 = new Question(String.valueOf(R.drawable.qae3), String.valueOf(R.drawable.ae7), String.valueOf(R.drawable.ae6),
                String.valueOf(R.drawable.ae6), Question.DIFFICULTY_EASY, Category.LEARN_ADDITION, String.valueOf(R.raw.vah));
        addQuestion(qae3);
        Question qae4 = new Question(String.valueOf(R.drawable.qae4), String.valueOf(R.drawable.ae5), String.valueOf(R.drawable.ae6),
                String.valueOf(R.drawable.ae5), Question.DIFFICULTY_EASY, Category.LEARN_ADDITION, String.valueOf(R.raw.vah));
        addQuestion(qae4);
        Question qae5 = new Question(String.valueOf(R.drawable.qae5), String.valueOf(R.drawable.ae8), String.valueOf(R.drawable.ae7),
                String.valueOf(R.drawable.ae7), Question.DIFFICULTY_EASY, Category.LEARN_ADDITION, String.valueOf(R.raw.vah));
        addQuestion(qae5);

        // ADDITION - MEDIUM
        Question qam1 = new Question(String.valueOf(R.drawable.qam1), String.valueOf(R.drawable.am7), String.valueOf(R.drawable.am8),
                String.valueOf(R.drawable.am7), Question.DIFFICULTY_MEDIUM, Category.LEARN_ADDITION, String.valueOf(R.raw.vam));
        addQuestion(qam1);
        Question qam2 = new Question(String.valueOf(R.drawable.qam2), String.valueOf(R.drawable.am9), String.valueOf(R.drawable.am10),
                String.valueOf(R.drawable.am9), Question.DIFFICULTY_MEDIUM, Category.LEARN_ADDITION, String.valueOf(R.raw.vam));
        addQuestion(qam2);
        Question qam3 = new Question(String.valueOf(R.drawable.qam3), String.valueOf(R.drawable.am7), String.valueOf(R.drawable.am8),
                String.valueOf(R.drawable.am8), Question.DIFFICULTY_MEDIUM, Category.LEARN_ADDITION, String.valueOf(R.raw.vam));
        addQuestion(qam3);
        Question qam4 = new Question(String.valueOf(R.drawable.qam4), String.valueOf(R.drawable.am10), String.valueOf(R.drawable.am8),
                String.valueOf(R.drawable.am10), Question.DIFFICULTY_MEDIUM, Category.LEARN_ADDITION, String.valueOf(R.raw.vam));
        addQuestion(qam4);
        Question qam5 = new Question(String.valueOf(R.drawable.qam5), String.valueOf(R.drawable.am7), String.valueOf(R.drawable.am9),
                String.valueOf(R.drawable.am7), Question.DIFFICULTY_MEDIUM, Category.LEARN_ADDITION, String.valueOf(R.raw.vam));
        addQuestion(qam5);

        // ADDITION - HARD
        Question qah1 = new Question(String.valueOf(R.drawable.qah1), "2", "3", "2", Question.DIFFICULTY_HARD, Category.LEARN_ADDITION, String.valueOf(R.raw.vae));
        addQuestion(qah1);
        Question qah2 = new Question(String.valueOf(R.drawable.qah2), "3", "4", "3", Question.DIFFICULTY_HARD, Category.LEARN_ADDITION, String.valueOf(R.raw.vae));
        addQuestion(qah2);
        Question qah3 = new Question(String.valueOf(R.drawable.qah3), "3", "4", "4", Question.DIFFICULTY_HARD, Category.LEARN_ADDITION, String.valueOf(R.raw.vae));
        addQuestion(qah3);
        Question qah4 = new Question(String.valueOf(R.drawable.qah4), "6", "5", "5", Question.DIFFICULTY_HARD, Category.LEARN_ADDITION, String.valueOf(R.raw.vae));
        addQuestion(qah4);
        Question qah5 = new Question(String.valueOf(R.drawable.qah5), "7", "6", "6", Question.DIFFICULTY_HARD, Category.LEARN_ADDITION, String.valueOf(R.raw.vae));
        addQuestion(qah5);

        // SUBSTRACTION - EASY (GAMBAR GAMBAR)
        Question qse1 = new Question(String.valueOf(R.drawable.qse1), String.valueOf(R.drawable.se2), String.valueOf(R.drawable.se3),
                String.valueOf(R.drawable.se3), Question.DIFFICULTY_EASY, Category.LEARN_SUBSTRACTION, String.valueOf(R.raw.vsh));
        addQuestion(qse1);
        Question qse2 = new Question(String.valueOf(R.drawable.qse2), String.valueOf(R.drawable.se1), String.valueOf(R.drawable.se2),
                String.valueOf(R.drawable.se1), Question.DIFFICULTY_EASY, Category.LEARN_SUBSTRACTION, String.valueOf(R.raw.vsh));
        addQuestion(qse2);
        Question qse3 = new Question(String.valueOf(R.drawable.qse3), String.valueOf(R.drawable.se2), String.valueOf(R.drawable.se1),
                String.valueOf(R.drawable.se1), Question.DIFFICULTY_EASY, Category.LEARN_SUBSTRACTION, String.valueOf(R.raw.vsh));
        addQuestion(qse3);
        Question qse4 = new Question(String.valueOf(R.drawable.qse4), String.valueOf(R.drawable.se1), String.valueOf(R.drawable.se3),
                String.valueOf(R.drawable.se1), Question.DIFFICULTY_EASY, Category.LEARN_SUBSTRACTION, String.valueOf(R.raw.vsh));
        addQuestion(qse4);
        Question qse5 = new Question(String.valueOf(R.drawable.qse5), String.valueOf(R.drawable.se4), String.valueOf(R.drawable.se5),
                String.valueOf(R.drawable.se4), Question.DIFFICULTY_EASY, Category.LEARN_SUBSTRACTION, String.valueOf(R.raw.vsh));
        addQuestion(qse5);

        // SUBSTRACTION - MEDIUM
        Question qsm1 = new Question(String.valueOf(R.drawable.qsm1), String.valueOf(R.drawable.sm1), String.valueOf(R.drawable.sm2),
                String.valueOf(R.drawable.sm1), Question.DIFFICULTY_MEDIUM, Category.LEARN_SUBSTRACTION, String.valueOf(R.raw.vsm));
        addQuestion(qsm1);
        Question qsm2 = new Question(String.valueOf(R.drawable.qsm2), String.valueOf(R.drawable.sm2), String.valueOf(R.drawable.sm3),
                String.valueOf(R.drawable.sm2), Question.DIFFICULTY_MEDIUM, Category.LEARN_SUBSTRACTION, String.valueOf(R.raw.vsm));
        addQuestion(qsm2);
        Question qsm3 = new Question(String.valueOf(R.drawable.qsm3), String.valueOf(R.drawable.sm4), String.valueOf(R.drawable.sm5),
                String.valueOf(R.drawable.sm4), Question.DIFFICULTY_MEDIUM, Category.LEARN_SUBSTRACTION, String.valueOf(R.raw.vsm));
        addQuestion(qsm3);
        Question qsm4 = new Question(String.valueOf(R.drawable.qsm4), String.valueOf(R.drawable.sm4), String.valueOf(R.drawable.sm5),
                String.valueOf(R.drawable.sm5), Question.DIFFICULTY_MEDIUM, Category.LEARN_SUBSTRACTION, String.valueOf(R.raw.vsm));
        addQuestion(qsm4);
        Question qsm5 = new Question(String.valueOf(R.drawable.qsm5), String.valueOf(R.drawable.sm3), String.valueOf(R.drawable.sm5),
                String.valueOf(R.drawable.sm3), Question.DIFFICULTY_MEDIUM, Category.LEARN_SUBSTRACTION, String.valueOf(R.raw.vsm));
        addQuestion(qsm5);

        // SUBSTRACTION - HARD
        Question qsh1 = new Question(String.valueOf(R.drawable.qsh1), "5", "4", "4", Question.DIFFICULTY_HARD, Category.LEARN_SUBSTRACTION, String.valueOf(R.raw.vse));
        addQuestion(qsh1);
        Question qsh2 = new Question(String.valueOf(R.drawable.qsh2), "5", "6", "6", Question.DIFFICULTY_HARD, Category.LEARN_SUBSTRACTION, String.valueOf(R.raw.vse));
        addQuestion(qsh2);
        Question qsh3 = new Question(String.valueOf(R.drawable.qsh3), "2", "1", "2", Question.DIFFICULTY_HARD, Category.LEARN_SUBSTRACTION, String.valueOf(R.raw.vse));
        addQuestion(qsh3);
        Question qsh4 = new Question(String.valueOf(R.drawable.qsh4), "1", "2", "1", Question.DIFFICULTY_HARD, Category.LEARN_SUBSTRACTION, String.valueOf(R.raw.vse));
        addQuestion(qsh4);
        Question qsh5 = new Question(String.valueOf(R.drawable.qsh5), "4", "5", "5", Question.DIFFICULTY_HARD, Category.LEARN_SUBSTRACTION, String.valueOf(R.raw.vse));
        addQuestion(qsh5);

        // MULTI - EASY (GAMBAR GAMBAR)
        Question qme1 = new Question(String.valueOf(R.drawable.qme1), String.valueOf(R.drawable.me8), String.valueOf(R.drawable.me6),
                String.valueOf(R.drawable.me8), Question.DIFFICULTY_EASY, Category.LEARN_MULTIPLICATION, String.valueOf(R.raw.vmh));
        addQuestion(qme1);
        Question qme2 = new Question(String.valueOf(R.drawable.qme2), String.valueOf(R.drawable.me8), String.valueOf(R.drawable.me9),
                String.valueOf(R.drawable.me9), Question.DIFFICULTY_EASY, Category.LEARN_MULTIPLICATION, String.valueOf(R.raw.vmh));
        addQuestion(qme2);
        Question qme3 = new Question(String.valueOf(R.drawable.qme3), String.valueOf(R.drawable.me10), String.valueOf(R.drawable.me12),
                String.valueOf(R.drawable.me10), Question.DIFFICULTY_EASY, Category.LEARN_MULTIPLICATION, String.valueOf(R.raw.vmh));
        addQuestion(qme3);
        Question qme4 = new Question(String.valueOf(R.drawable.qme4), String.valueOf(R.drawable.me10), String.valueOf(R.drawable.me12),
                String.valueOf(R.drawable.me12), Question.DIFFICULTY_EASY, Category.LEARN_MULTIPLICATION, String.valueOf(R.raw.vmh));
        addQuestion(qme4);
        Question qme5 = new Question(String.valueOf(R.drawable.qme5), String.valueOf(R.drawable.me6), String.valueOf(R.drawable.me8),
                String.valueOf(R.drawable.me6), Question.DIFFICULTY_EASY, Category.LEARN_MULTIPLICATION, String.valueOf(R.raw.vmh));
        addQuestion(qme5);

        // MULTI - MEDIUM
        Question qmm1 = new Question(String.valueOf(R.drawable.qmm1), String.valueOf(R.drawable.mm6), String.valueOf(R.drawable.mm4),
                String.valueOf(R.drawable.mm4), Question.DIFFICULTY_MEDIUM, Category.LEARN_MULTIPLICATION, String.valueOf(R.raw.vmm));
        addQuestion(qmm1);
        Question qmm2 = new Question(String.valueOf(R.drawable.qmm2), String.valueOf(R.drawable.mm6), String.valueOf(R.drawable.mm8),
                String.valueOf(R.drawable.mm6), Question.DIFFICULTY_MEDIUM, Category.LEARN_MULTIPLICATION, String.valueOf(R.raw.vmm));
        addQuestion(qmm2);
        Question qmm3 = new Question(String.valueOf(R.drawable.qmm3), String.valueOf(R.drawable.mm6), String.valueOf(R.drawable.mm8),
                String.valueOf(R.drawable.mm8), Question.DIFFICULTY_MEDIUM, Category.LEARN_MULTIPLICATION, String.valueOf(R.raw.vmm));
        addQuestion(qmm3);
        Question qmm4 = new Question(String.valueOf(R.drawable.qmm4), String.valueOf(R.drawable.mm10), String.valueOf(R.drawable.mm8),
                String.valueOf(R.drawable.mm10), Question.DIFFICULTY_MEDIUM, Category.LEARN_MULTIPLICATION, String.valueOf(R.raw.vmm));
        addQuestion(qmm4);
        Question qmm5 = new Question(String.valueOf(R.drawable.qmm5), String.valueOf(R.drawable.mm3), String.valueOf(R.drawable.mm4),
                String.valueOf(R.drawable.mm3), Question.DIFFICULTY_MEDIUM, Category.LEARN_MULTIPLICATION, String.valueOf(R.raw.vmm));
        addQuestion(qmm5);

        // MULTI - HARD
        Question qmh1 = new Question(String.valueOf(R.drawable.qmh1), "4", "5", "4", Question.DIFFICULTY_HARD, Category.LEARN_MULTIPLICATION, String.valueOf(R.raw.vme));
        addQuestion(qmh1);
        Question qmh2 = new Question(String.valueOf(R.drawable.qmh2), "1", "2", "2", Question.DIFFICULTY_HARD, Category.LEARN_MULTIPLICATION, String.valueOf(R.raw.vme));
        addQuestion(qmh2);
        Question qmh3 = new Question(String.valueOf(R.drawable.qmh3), "5", "6", "5", Question.DIFFICULTY_HARD, Category.LEARN_MULTIPLICATION, String.valueOf(R.raw.vme));
        addQuestion(qmh3);
        Question qmh4 = new Question(String.valueOf(R.drawable.qmh4), "6", "7", "6", Question.DIFFICULTY_HARD, Category.LEARN_MULTIPLICATION, String.valueOf(R.raw.vme));
        addQuestion(qmh4);
        Question qmh5 = new Question(String.valueOf(R.drawable.qmh5), "7", "8", "7", Question.DIFFICULTY_HARD, Category.LEARN_MULTIPLICATION, String.valueOf(R.raw.vme));
        addQuestion(qmh5);

        // SOCIAL - EASY
        Question qee1 = new Question("Ibu membeli 3 Soto Banjar, dan Bapak juga membeli 2 Soto Banjar, berapakah jumlah Soto Banjar yang mereka beli ?",
                "6", "5", "5", Question.DIFFICULTY_EASY, Category.SOCIAL_EXPERIENCE, String.valueOf(R.raw.qse1));
        addQuestion(qee1);
        Question qee2 = new Question("Ibu membuat 5 Ketupat Kandang, lalu ibu memberikan 2 Ketupat Kandang kepada Ayah, berapakah sisa Ketupat kandang yang dimiliki Ibu ?",
                "3", "7", "3", Question.DIFFICULTY_EASY, Category.SOCIAL_EXPERIENCE, String.valueOf(R.raw.qse2));
        addQuestion(qee2);
        Question qee3 = new Question("Ayah membeli Bingka 5 biji, 2 biji Bingka dimakan Ibu, berapa sisa biji Bingka yang ada ?",
                "4", "3", "3", Question.DIFFICULTY_EASY, Category.SOCIAL_EXPERIENCE, String.valueOf(R.raw.qse3));
        addQuestion(qee3);
        Question qee4 = new Question("Budi membeli 3 bungkus Klepon, Andi membeli 1 bungkus Klepon. Berapa bungkus Klepon yang dimiliki Budi dan Andi ?",
                "2", "4", "4", Question.DIFFICULTY_EASY, Category.SOCIAL_EXPERIENCE, String.valueOf(R.raw.qse4));
        addQuestion(qee4);
        Question qee5 = new Question("Ayah membeli 6 Bubur pedas, 2 Bubur pedasnya dimakan oleh ibu, berapa sisa bubur pedas yang dimakan ayah ?",
                "4", "5", "4", Question.DIFFICULTY_EASY, Category.SOCIAL_EXPERIENCE, String.valueOf(R.raw.qse5));
        addQuestion(qee5);

        // SOCIAL - MEDIUM
        // SOCIAL - HARD
        
        // QUIZ - EASY
        Question qqe1 = new Question(String.valueOf(R.drawable.qqe1),"0", "1", "1", Question.DIFFICULTY_EASY, Category.QUIZ, String.valueOf(R.raw.vsm));
        addQuestion(qqe1);
        Question qqe2 = new Question(String.valueOf(R.drawable.qqe2),"3", "2", "3", Question.DIFFICULTY_EASY, Category.QUIZ, String.valueOf(R.raw.vae));
        addQuestion(qqe2);
        Question qqe3 = new Question(String.valueOf(R.drawable.qqe3),"3", "4", "3", Question.DIFFICULTY_EASY, Category.QUIZ, String.valueOf(R.raw.qse3));
        addQuestion(qqe3);
        Question qqe4 = new Question(String.valueOf(R.drawable.qqe4),"2", "4", "4", Question.DIFFICULTY_EASY, Category.QUIZ, String.valueOf(R.raw.qse4));
        addQuestion(qqe4);
        Question qqe5 = new Question(String.valueOf(R.drawable.qqe5),"7", "10", "10", Question.DIFFICULTY_EASY, Category.QUIZ, String.valueOf(R.raw.vmh));
        addQuestion(qqe5);

        // QUIZ - MEDIUM
        // QUIZ - HARD
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionTable.COLUMN_ANSWERSTR, question.getAnswerStr());
        cv.put(QuizContract.QuestionTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuizContract.QuestionTable.COLUMN_CATEGORY_ID, question.getCategoryId());
        cv.put(QuizContract.QuestionTable.COLUMN_VOICE, question.getVoiceOver());
        db.insert(QuizContract.QuestionTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(QuizContract.CategoriesTable._ID)));
                category.setCategory(c.getString(c.getColumnIndex(QuizContract.CategoriesTable.COLUMN_CATEGORY)));
                categoryList.add(category);
            } while (c.moveToNext());
        }
        c.close();
        return categoryList;
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuizContract.QuestionTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION2)));
                question.setAnswerStr(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_ANSWERSTR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_DIFFICULTY)));
                question.setCategoryId(c.getInt(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_CATEGORY_ID)));
                question.setVoiceOver(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_VOICE)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

    public List<Question> getQuestion(int categoryId, String difficulty) {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        /*String[] selectionArgs = new String[]{difficulty};
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionTable.TABLE_NAME +
                " WHERE " + QuizContract.QuestionTable.COLUMN_DIFFICULTY + " = ?", selectionArgs);*/

        String selection = QuizContract.QuestionTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuizContract.QuestionTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryId), difficulty};
        Cursor c = db.query(
                QuizContract.QuestionTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuizContract.QuestionTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION2)));
                question.setAnswerStr(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_ANSWERSTR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_DIFFICULTY)));
                question.setCategoryId(c.getInt(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_CATEGORY_ID)));
                question.setVoiceOver(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_VOICE)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
