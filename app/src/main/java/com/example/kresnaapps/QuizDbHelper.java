package com.example.kresnaapps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
                QuizContract.QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_ANSWERSTR + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuizContract.QuestionTable.COLUNM_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuizContract.QuestionTable.COLUNM_CATEGORY_ID + ") REFERENCES " +
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
        Question qne1 = new Question(String.valueOf(R.drawable.qne1), "1", "2", "1", Question.DIFFICULTY_EASY, Category.LEARN_NUMBERS);
        addQuestion(qne1);
        Question qne2 = new Question(String.valueOf(R.drawable.qne2), "2", "3", "2", Question.DIFFICULTY_EASY, Category.LEARN_NUMBERS);
        addQuestion(qne2);
        Question qne3 = new Question(String.valueOf(R.drawable.qne3), "2", "3", "3", Question.DIFFICULTY_EASY, Category.LEARN_NUMBERS);
        addQuestion(qne3);
        Question qne4 = new Question(String.valueOf(R.drawable.qne4), "3", "4", "4", Question.DIFFICULTY_EASY, Category.LEARN_NUMBERS);
        addQuestion(qne4);

        // LEARN NUMBER - MEDIUM
        Question qnm1 = new Question(String.valueOf(R.drawable.qnm1), "4", "5", "5", Question.DIFFICULTY_MEDIUM, Category.LEARN_NUMBERS);
        addQuestion(qnm1);
        Question qnm2 = new Question(String.valueOf(R.drawable.qnm2), "5", "6", "6", Question.DIFFICULTY_MEDIUM, Category.LEARN_NUMBERS);
        addQuestion(qnm2);
        Question qnm3 = new Question(String.valueOf(R.drawable.qnm3), "7", "8", "7", Question.DIFFICULTY_MEDIUM, Category.LEARN_NUMBERS);
        addQuestion(qnm3);

        // LEARN NUMBER - HARD
        Question qnh1 = new Question(String.valueOf(R.drawable.qnh1), "7", "8", "8", Question.DIFFICULTY_HARD, Category.LEARN_NUMBERS);
        addQuestion(qnh1);
        Question qnh2 = new Question(String.valueOf(R.drawable.qnh2), "9", "8", "9", Question.DIFFICULTY_HARD, Category.LEARN_NUMBERS);
        addQuestion(qnh2);
        Question qnh3 = new Question(String.valueOf(R.drawable.qnh3), "10", "11", "10", Question.DIFFICULTY_HARD, Category.LEARN_NUMBERS);
        addQuestion(qnh3);

        // ADDITION - EASY
        Question qae1 = new Question(String.valueOf(R.drawable.qae1), "3", "2", "2", Question.DIFFICULTY_EASY, Category.LEARN_ADDITION);
        addQuestion(qae1);
        Question qae2 = new Question(String.valueOf(R.drawable.qae2), "3", "2", "3", Question.DIFFICULTY_EASY, Category.LEARN_ADDITION);
        addQuestion(qae2);
        Question qae3 = new Question(String.valueOf(R.drawable.qae3), "4", "5", "4", Question.DIFFICULTY_EASY, Category.LEARN_ADDITION);
        addQuestion(qae3);
        Question qae4 = new Question(String.valueOf(R.drawable.qae4), "4", "5", "5", Question.DIFFICULTY_EASY, Category.LEARN_ADDITION);
        addQuestion(qae4);
        Question qae5 = new Question(String.valueOf(R.drawable.qae5), "6", "5", "6", Question.DIFFICULTY_EASY, Category.LEARN_ADDITION);
        addQuestion(qae5);
        Question qae6 = new Question(String.valueOf(R.drawable.qae6), "7", "6", "7", Question.DIFFICULTY_EASY, Category.LEARN_ADDITION);
        addQuestion(qae6);
        Question qae7 = new Question(String.valueOf(R.drawable.qae7), "8", "9", "9", Question.DIFFICULTY_EASY, Category.LEARN_ADDITION);
        addQuestion(qae7);
        Question qae8 = new Question(String.valueOf(R.drawable.qae8), "7", "8", "8", Question.DIFFICULTY_EASY, Category.LEARN_ADDITION);
        addQuestion(qae8);
        Question qae9 = new Question(String.valueOf(R.drawable.qae9), "10", "11", "10", Question.DIFFICULTY_EASY, Category.LEARN_ADDITION);
        addQuestion(qae9);
        Question qae10 = new Question(String.valueOf(R.drawable.qae10), "6", "7", "7", Question.DIFFICULTY_EASY, Category.LEARN_ADDITION);
        addQuestion(qae10);

        // ADDITION - MEDIUM
        Question qam1 = new Question(String.valueOf(R.drawable.qam1), "4", "5", "5", Question.DIFFICULTY_MEDIUM, Category.LEARN_ADDITION);
        addQuestion(qam1);
        Question qam2 = new Question(String.valueOf(R.drawable.qam2), "7", "8", "8", Question.DIFFICULTY_MEDIUM, Category.LEARN_ADDITION);
        addQuestion(qam2);
        Question qam3 = new Question(String.valueOf(R.drawable.qam3), "5", "6", "6", Question.DIFFICULTY_MEDIUM, Category.LEARN_ADDITION);
        addQuestion(qam3);
        Question qam4 = new Question(String.valueOf(R.drawable.qam4), "7", "6", "6", Question.DIFFICULTY_MEDIUM, Category.LEARN_ADDITION);
        addQuestion(qam4);
        Question qam5 = new Question(String.valueOf(R.drawable.qam5), "7", "8", "7", Question.DIFFICULTY_MEDIUM, Category.LEARN_ADDITION);
        addQuestion(qam5);

        // ADDITION - HARD
        Question qah1 = new Question(String.valueOf(R.drawable.qah1), "4", "5", "5", Question.DIFFICULTY_HARD, Category.LEARN_ADDITION);
        addQuestion(qah1);
        Question qah2 = new Question(String.valueOf(R.drawable.qah2), "8", "7", "8", Question.DIFFICULTY_HARD, Category.LEARN_ADDITION);
        addQuestion(qah2);
        Question qah3 = new Question(String.valueOf(R.drawable.qah3), "7", "6", "6", Question.DIFFICULTY_HARD, Category.LEARN_ADDITION);
        addQuestion(qah3);
        Question qah4 = new Question(String.valueOf(R.drawable.qah4), "6", "5", "6", Question.DIFFICULTY_HARD, Category.LEARN_ADDITION);
        addQuestion(qah4);
        Question qah5 = new Question(String.valueOf(R.drawable.qah5), "7", "8", "7", Question.DIFFICULTY_HARD, Category.LEARN_ADDITION);
        addQuestion(qah5);

        // SUBSTRACTION - EASY
        Question qse1 = new Question(String.valueOf(R.drawable.qse1), "1", "2", "1", Question.DIFFICULTY_EASY, Category.LEARN_SUBSTRACTION);
        addQuestion(qse1);
        Question qse2 = new Question(String.valueOf(R.drawable.qse2), "3", "2", "2", Question.DIFFICULTY_EASY, Category.LEARN_SUBSTRACTION);
        addQuestion(qse2);
        Question qse3 = new Question(String.valueOf(R.drawable.qse3), "3", "4", "4", Question.DIFFICULTY_EASY, Category.LEARN_SUBSTRACTION);
        addQuestion(qse3);
        Question qse4 = new Question(String.valueOf(R.drawable.qse4), "4", "5", "5", Question.DIFFICULTY_EASY, Category.LEARN_SUBSTRACTION);
        addQuestion(qse4);
        Question qse5 = new Question(String.valueOf(R.drawable.qse5), "3", "4", "3", Question.DIFFICULTY_EASY, Category.LEARN_SUBSTRACTION);
        addQuestion(qse5);
        Question qse6 = new Question(String.valueOf(R.drawable.qse6), "3", "4", "4", Question.DIFFICULTY_EASY, Category.LEARN_SUBSTRACTION);
        addQuestion(qse6);
        Question qse7 = new Question(String.valueOf(R.drawable.qse7), "6", "5", "6", Question.DIFFICULTY_EASY, Category.LEARN_SUBSTRACTION);
        addQuestion(qse7);
        Question qse8 = new Question(String.valueOf(R.drawable.qse8), "1", "2", "2", Question.DIFFICULTY_EASY, Category.LEARN_SUBSTRACTION);
        addQuestion(qse8);
        Question qse9 = new Question(String.valueOf(R.drawable.qse9), "1", "2", "1", Question.DIFFICULTY_EASY, Category.LEARN_SUBSTRACTION);
        addQuestion(qse9);
        Question qse10 = new Question(String.valueOf(R.drawable.qse10), "4", "5", "5", Question.DIFFICULTY_EASY, Category.LEARN_SUBSTRACTION);
        addQuestion(qse10);

        // SUBSTRACTION - MEDIUM
        Question qsm1 = new Question(String.valueOf(R.drawable.qsm1), "1", "2", "1", Question.DIFFICULTY_MEDIUM, Category.LEARN_SUBSTRACTION);
        addQuestion(qsm1);
        Question qsm2 = new Question(String.valueOf(R.drawable.qsm2), "1", "2", "2", Question.DIFFICULTY_MEDIUM, Category.LEARN_SUBSTRACTION);
        addQuestion(qsm2);
        Question qsm3 = new Question(String.valueOf(R.drawable.qsm3), "3", "2", "2", Question.DIFFICULTY_MEDIUM, Category.LEARN_SUBSTRACTION);
        addQuestion(qsm3);
        Question qsm4 = new Question(String.valueOf(R.drawable.qsm4), "6", "5", "6", Question.DIFFICULTY_MEDIUM, Category.LEARN_SUBSTRACTION);
        addQuestion(qsm4);
        Question qsm5 = new Question(String.valueOf(R.drawable.qsm5), "2", "3", "2", Question.DIFFICULTY_MEDIUM, Category.LEARN_SUBSTRACTION);
        addQuestion(qsm5);

        // SUBSTRACTION - HARD
        Question qsh1 = new Question(String.valueOf(R.drawable.qsh1), "1", "0", "1", Question.DIFFICULTY_HARD, Category.LEARN_SUBSTRACTION);
        addQuestion(qsh1);
        Question qsh2 = new Question(String.valueOf(R.drawable.qsh2), "4", "5", "5", Question.DIFFICULTY_HARD, Category.LEARN_SUBSTRACTION);
        addQuestion(qsh2);
        Question qsh3 = new Question(String.valueOf(R.drawable.qsh3), "2", "1", "1", Question.DIFFICULTY_HARD, Category.LEARN_SUBSTRACTION);
        addQuestion(qsh3);
        Question qsh4 = new Question(String.valueOf(R.drawable.qsh4), "1", "2", "2", Question.DIFFICULTY_HARD, Category.LEARN_SUBSTRACTION);
        addQuestion(qsh4);
        Question qsh5 = new Question(String.valueOf(R.drawable.qsh5), "3", "2", "3", Question.DIFFICULTY_HARD, Category.LEARN_SUBSTRACTION);
        addQuestion(qsh5);

        // MULTI - EASY
        Question qme1 = new Question(String.valueOf(R.drawable.qme1), "4", "8", "4", Question.DIFFICULTY_EASY, Category.LEARN_MULTIPLICATION);
        addQuestion(qme1);
        Question qme2 = new Question(String.valueOf(R.drawable.qme2), "5", "6", "6", Question.DIFFICULTY_EASY, Category.LEARN_MULTIPLICATION);
        addQuestion(qme2);
        Question qme3 = new Question(String.valueOf(R.drawable.qme3), "6", "8", "8", Question.DIFFICULTY_EASY, Category.LEARN_MULTIPLICATION);
        addQuestion(qme3);
        Question qme4 = new Question(String.valueOf(R.drawable.qme4), "15", "10", "10", Question.DIFFICULTY_EASY, Category.LEARN_MULTIPLICATION);
        addQuestion(qme4);
        Question qme5 = new Question(String.valueOf(R.drawable.qme5), "3", "4", "3", Question.DIFFICULTY_EASY, Category.LEARN_MULTIPLICATION);
        addQuestion(qme5);
        Question qme6 = new Question(String.valueOf(R.drawable.qme6), "4", "5", "4", Question.DIFFICULTY_EASY, Category.LEARN_MULTIPLICATION);
        addQuestion(qme6);
        Question qme7 = new Question(String.valueOf(R.drawable.qme7), "12", "2", "2", Question.DIFFICULTY_EASY, Category.LEARN_MULTIPLICATION);
        addQuestion(qme7);
        Question qme8 = new Question(String.valueOf(R.drawable.qme8), "5", "6", "5", Question.DIFFICULTY_EASY, Category.LEARN_MULTIPLICATION);
        addQuestion(qme8);
        Question qme9 = new Question(String.valueOf(R.drawable.qme9), "5", "6", "6", Question.DIFFICULTY_EASY, Category.LEARN_MULTIPLICATION);
        addQuestion(qme9);
        Question qme10 = new Question(String.valueOf(R.drawable.qme10), "7", "8", "7", Question.DIFFICULTY_EASY, Category.LEARN_MULTIPLICATION);
        addQuestion(qme10);

        // MULTI - MEDIUM
        Question qmm1 = new Question(String.valueOf(R.drawable.qmm1), "5", "6", "5", Question.DIFFICULTY_MEDIUM, Category.LEARN_MULTIPLICATION);
        addQuestion(qmm1);
        Question qmm2 = new Question(String.valueOf(R.drawable.qmm2), "6", "8", "8", Question.DIFFICULTY_MEDIUM, Category.LEARN_MULTIPLICATION);
        addQuestion(qmm2);
        Question qmm3 = new Question(String.valueOf(R.drawable.qmm3), "5", "6", "6", Question.DIFFICULTY_MEDIUM, Category.LEARN_MULTIPLICATION);
        addQuestion(qmm3);
        Question qmm4 = new Question(String.valueOf(R.drawable.qmm4), "4", "3", "3", Question.DIFFICULTY_MEDIUM, Category.LEARN_MULTIPLICATION);
        addQuestion(qmm4);
        Question qmm5 = new Question(String.valueOf(R.drawable.qmm5), "4", "2", "4", Question.DIFFICULTY_MEDIUM, Category.LEARN_MULTIPLICATION);
        addQuestion(qmm5);

        // MULTI - HARD
        Question qmh1 = new Question(String.valueOf(R.drawable.qmh1), "5", "6", "6", Question.DIFFICULTY_HARD, Category.LEARN_MULTIPLICATION);
        addQuestion(qmh1);
        Question qmh2 = new Question(String.valueOf(R.drawable.qmh2), "9", "6", "9", Question.DIFFICULTY_HARD, Category.LEARN_MULTIPLICATION);
        addQuestion(qmh2);
        Question qmh3 = new Question(String.valueOf(R.drawable.qmh3), "8", "6", "8", Question.DIFFICULTY_HARD, Category.LEARN_MULTIPLICATION);
        addQuestion(qmh3);
        Question qmh4 = new Question(String.valueOf(R.drawable.qmh4), "4", "2", "4", Question.DIFFICULTY_HARD, Category.LEARN_MULTIPLICATION);
        addQuestion(qmh4);
        Question qmh5 = new Question(String.valueOf(R.drawable.qmh5), "7", "10", "10", Question.DIFFICULTY_HARD, Category.LEARN_MULTIPLICATION);
        addQuestion(qmh5);

        // SOCIAL - EASY
        Question qee1 = new Question("Ibu membeli 3 Soto Banjar, dan Bapak juga membeli 2 Soto Banjar, berapakah jumlah Soto Banjar yang mereka beli ?",
                "1", "2", "2", Question.DIFFICULTY_EASY, Category.SOCIAL_EXPERIENCE);
        addQuestion(qee1);
        Question qee2 = new Question("Ibu membuat 5 Ketupat Kandang, lalu ibu memberikan 2 Ketupat Kandang kepada Ayah, berapakah sisa Ketupat kandang yang dimiliki Ibu ?",
                "3", "7", "3", Question.DIFFICULTY_EASY, Category.SOCIAL_EXPERIENCE);
        addQuestion(qee2);
        Question qee3 = new Question("Ayah membeli Bingka 5 biji, 2 biji Bingka dimakan Ibu, berapa sisa biji Bingka yang ada ?",
                "4", "3", "3", Question.DIFFICULTY_EASY, Category.SOCIAL_EXPERIENCE);
        addQuestion(qee3);
        Question qee4 = new Question("Budi membeli 3 bungkus Klepon, Andi membeli 1 bungkus Klepon. Berapa bungkus Klepon yang dimiliki Budi dan Andi ?",
                "2", "4", "4", Question.DIFFICULTY_EASY, Category.SOCIAL_EXPERIENCE);
        addQuestion(qee4);
        Question qee5 = new Question("Budi membeli 3 bungkus Klepon, Andi membeli 1 bungkus Klepon. Berapa bungkus Klepon yang dimiliki Budi dan Andi ?",
                "4", "5", "4", Question.DIFFICULTY_EASY, Category.SOCIAL_EXPERIENCE);
        addQuestion(qee5);

        // SOCIAL - MEDIUM
        // SOCIAL - HARD
        
        // QUIZ - EASY
        Question qqe1 = new Question(String.valueOf(R.drawable.qqe1),"0", "1", "1", Question.DIFFICULTY_EASY, Category.QUIZ);
        addQuestion(qqe1);
        Question qqe2 = new Question(String.valueOf(R.drawable.qqe2),"3", "2", "3", Question.DIFFICULTY_EASY, Category.QUIZ);
        addQuestion(qqe2);
        Question qqe3 = new Question(String.valueOf(R.drawable.qqe3),"3", "4", "3", Question.DIFFICULTY_EASY, Category.QUIZ);
        addQuestion(qqe3);
        Question qqe4 = new Question(String.valueOf(R.drawable.qqe4),"2", "4", "4", Question.DIFFICULTY_EASY, Category.QUIZ);
        addQuestion(qqe4);
        Question qqe5 = new Question(String.valueOf(R.drawable.qqe5),"7", "10", "10", Question.DIFFICULTY_EASY, Category.QUIZ);
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
        cv.put(QuizContract.QuestionTable.COLUNM_CATEGORY_ID, question.getCategoryId());
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
                question.setCategoryId(c.getInt(c.getColumnIndex(QuizContract.QuestionTable.COLUNM_CATEGORY_ID)));
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

        String selection = QuizContract.QuestionTable.COLUNM_CATEGORY_ID + " = ? " +
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
                question.setCategoryId(c.getInt(c.getColumnIndex(QuizContract.QuestionTable.COLUNM_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
