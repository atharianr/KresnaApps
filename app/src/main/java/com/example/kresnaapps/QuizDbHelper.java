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
        Question qne1 = new Question("2", String.valueOf(R.drawable.kelereng_dua), String.valueOf(R.drawable.kelereng), String.valueOf(R.drawable.kelereng), String.valueOf(R.drawable.kelereng), String.valueOf(R.drawable.kelereng_dua), Question.DIFFICULTY_EASY, Category.LEARN_NUMBERS);
        addQuestion(qne1);
        Question qne2 = new Question("1", String.valueOf(R.drawable.kelereng_dua), String.valueOf(R.drawable.kelereng_dua), String.valueOf(R.drawable.kelereng_dua), String.valueOf(R.drawable.kelereng), String.valueOf(R.drawable.kelereng), Question.DIFFICULTY_EASY, Category.LEARN_NUMBERS);
        addQuestion(qne2);

        // LEARN NUMBER - MEDIUM
        Question qme1 = new Question("2", String.valueOf(R.drawable.kelereng_dua), String.valueOf(R.drawable.kelereng), String.valueOf(R.drawable.kelereng), String.valueOf(R.drawable.kelereng), String.valueOf(R.drawable.kelereng_dua), Question.DIFFICULTY_MEDIUM, Category.LEARN_NUMBERS);
        addQuestion(qme1);
        Question qme2 = new Question("1", String.valueOf(R.drawable.kelereng_dua), String.valueOf(R.drawable.kelereng_dua), String.valueOf(R.drawable.kelereng_dua), String.valueOf(R.drawable.kelereng), String.valueOf(R.drawable.kelereng), Question.DIFFICULTY_MEDIUM, Category.LEARN_NUMBERS);
        addQuestion(qme2);

        // LEARN NUMBER - HARD
        Question qnh1 = new Question("2", String.valueOf(R.drawable.kelereng_dua), String.valueOf(R.drawable.kelereng), String.valueOf(R.drawable.kelereng), String.valueOf(R.drawable.kelereng), String.valueOf(R.drawable.kelereng_dua), Question.DIFFICULTY_HARD, Category.LEARN_NUMBERS);
        addQuestion(qnh1);
        Question qnh2 = new Question("1", String.valueOf(R.drawable.kelereng_dua), String.valueOf(R.drawable.kelereng_dua), String.valueOf(R.drawable.kelereng_dua), String.valueOf(R.drawable.kelereng), String.valueOf(R.drawable.kelereng), Question.DIFFICULTY_HARD, Category.LEARN_NUMBERS);
        addQuestion(qnh2);

        // ADDITION - EASY
        Question qae1 = new Question(String.valueOf(R.drawable.qae1), "1", "2", "3", "4", "2", Question.DIFFICULTY_EASY, Category.LEARN_ADDITION);
        addQuestion(qae1);
        Question qae2 = new Question(String.valueOf(R.drawable.qae2), "6", "7", "8", "9", "7", Question.DIFFICULTY_EASY, Category.LEARN_ADDITION);
        addQuestion(qae2);
        Question qae3 = new Question(String.valueOf(R.drawable.qae3), "10", "9", "8", "7", "8", Question.DIFFICULTY_EASY, Category.LEARN_ADDITION);
        addQuestion(qae3);
        Question qae4 = new Question(String.valueOf(R.drawable.qae4), "4", "3", "2", "1", "4", Question.DIFFICULTY_EASY, Category.LEARN_ADDITION);
        addQuestion(qae4);
        Question qae5 = new Question(String.valueOf(R.drawable.qae5), "3", "4", "5", "6", "6", Question.DIFFICULTY_EASY, Category.LEARN_ADDITION);
        addQuestion(qae5);
        Question qae6 = new Question(String.valueOf(R.drawable.qae6), "9", "10", "11", "12", "9", Question.DIFFICULTY_EASY, Category.LEARN_ADDITION);
        addQuestion(qae6);
        Question qae7 = new Question(String.valueOf(R.drawable.qae7), "6", "8", "10", "12", "6", Question.DIFFICULTY_EASY, Category.LEARN_ADDITION);
        addQuestion(qae7);
        Question qae8 = new Question(String.valueOf(R.drawable.qae8), "1", "3", "5", "7", "5", Question.DIFFICULTY_EASY, Category.LEARN_ADDITION);
        addQuestion(qae8);
        Question qae9 = new Question(String.valueOf(R.drawable.qae9), "8", "9", "10", "11", "9", Question.DIFFICULTY_EASY, Category.LEARN_ADDITION);
        addQuestion(qae9);
        Question qae10 = new Question(String.valueOf(R.drawable.qae10), "13", "12", "11", "10", "10", Question.DIFFICULTY_EASY, Category.LEARN_ADDITION);
        addQuestion(qae10);


        // ADDITION - MEDIUM
        Question qam1 = new Question(String.valueOf(R.drawable.qam1), "11", "12", "13", "14", "12", Question.DIFFICULTY_MEDIUM, Category.LEARN_ADDITION);
        addQuestion(qam1);
        Question qam2 = new Question(String.valueOf(R.drawable.qam2), "10", "11", "12", "13", "11", Question.DIFFICULTY_MEDIUM, Category.LEARN_ADDITION);
        addQuestion(qam2);
        Question qam3 = new Question(String.valueOf(R.drawable.qam3), "10", "11", "12", "13", "13", Question.DIFFICULTY_MEDIUM, Category.LEARN_ADDITION);
        addQuestion(qam3);
        Question qam4 = new Question(String.valueOf(R.drawable.qam4), "13", "14", "15", "16", "15", Question.DIFFICULTY_MEDIUM, Category.LEARN_ADDITION);
        addQuestion(qam4);
        Question qam5 = new Question(String.valueOf(R.drawable.qam5), "19", "18", "17", "16", "17", Question.DIFFICULTY_MEDIUM, Category.LEARN_ADDITION);
        addQuestion(qam5);

        // ADDITION - HARD
        Question qah1 = new Question(String.valueOf(R.drawable.qah1), "23", "24", "25", "26", "23", Question.DIFFICULTY_HARD, Category.LEARN_ADDITION);
        addQuestion(qah1);
        Question qah2 = new Question(String.valueOf(R.drawable.qah2), "29", "30", "31", "32", "31", Question.DIFFICULTY_HARD, Category.LEARN_ADDITION);
        addQuestion(qah2);
        Question qah3 = new Question(String.valueOf(R.drawable.qah3), "33", "34", "35", "36", "36", Question.DIFFICULTY_HARD, Category.LEARN_ADDITION);
        addQuestion(qah3);
        Question qah4 = new Question(String.valueOf(R.drawable.qah4), "36", "37", "39", "40", "37", Question.DIFFICULTY_HARD, Category.LEARN_ADDITION);
        addQuestion(qah4);
        Question qah5 = new Question(String.valueOf(R.drawable.qah5), "41", "51", "31", "42", "41", Question.DIFFICULTY_HARD, Category.LEARN_ADDITION);
        addQuestion(qah5);


        // SUBSTRACTION - EASY
        Question qse1 = new Question(String.valueOf(R.drawable.qse1), "1", "2", "3", "4", "1", Question.DIFFICULTY_EASY, Category.LEARN_SUBSTRACTION);
        addQuestion(qse1);
        Question qse2 = new Question(String.valueOf(R.drawable.qse2), "1", "2", "3", "4", "1", Question.DIFFICULTY_EASY, Category.LEARN_SUBSTRACTION);
        addQuestion(qse2);
        Question qse3 = new Question(String.valueOf(R.drawable.qse3), "1", "2", "3", "4", "1", Question.DIFFICULTY_EASY, Category.LEARN_SUBSTRACTION);
        addQuestion(qse3);
        Question qse4 = new Question(String.valueOf(R.drawable.qse4), "1", "2", "3", "4", "1", Question.DIFFICULTY_EASY, Category.LEARN_SUBSTRACTION);
        addQuestion(qse4);
        Question qse5 = new Question(String.valueOf(R.drawable.qse5), "1", "2", "3", "4", "1", Question.DIFFICULTY_EASY, Category.LEARN_SUBSTRACTION);
        addQuestion(qse5);
        Question qse6 = new Question(String.valueOf(R.drawable.qse6), "1", "2", "3", "4", "1", Question.DIFFICULTY_EASY, Category.LEARN_SUBSTRACTION);
        addQuestion(qse6);
        Question qse7 = new Question(String.valueOf(R.drawable.qse7), "1", "2", "3", "4", "1", Question.DIFFICULTY_EASY, Category.LEARN_SUBSTRACTION);
        addQuestion(qse7);
        Question qse8 = new Question(String.valueOf(R.drawable.qse8), "1", "2", "3", "4", "1", Question.DIFFICULTY_EASY, Category.LEARN_SUBSTRACTION);
        addQuestion(qse8);
        Question qse9 = new Question(String.valueOf(R.drawable.qse9), "1", "2", "3", "4", "1", Question.DIFFICULTY_EASY, Category.LEARN_SUBSTRACTION);
        addQuestion(qse9);
        Question qse10 = new Question(String.valueOf(R.drawable.qse10), "1", "2", "3", "4", "1", Question.DIFFICULTY_EASY, Category.LEARN_SUBSTRACTION);
        addQuestion(qse10);

        // SUBSTRACTION - MEDIUM
        Question qsm1 = new Question("20 - 13 =", "7", "2", "3", "0", "7", Question.DIFFICULTY_MEDIUM, Category.LEARN_SUBSTRACTION);
        addQuestion(qsm1);

        // SUBSTRACTION - HARD
        Question qsh1 = new Question("200 - 130 =", "70", "2", "3", "0", "70", Question.DIFFICULTY_MEDIUM, Category.LEARN_SUBSTRACTION);
        addQuestion(qsh1);

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
        cv.put(QuizContract.QuestionTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTION4, question.getOption4());
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
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION4)));
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
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION4)));
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
