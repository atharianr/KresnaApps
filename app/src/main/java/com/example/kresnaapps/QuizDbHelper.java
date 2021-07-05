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



        // ADDITION - MEDIUM


        // ADDITION - HARD



        // SUBSTRACTION - EASY


        // SUBSTRACTION - MEDIUM


        // SUBSTRACTION - HARD


        // MULTI - EASY


        // MULTI - MEDIUM


        // MULTI - HARD

        // SOCIAL - EASY
        // SOCIAL - MEDIUM
        // SOCIAL - HARD


        // QUIZ - EASY
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
