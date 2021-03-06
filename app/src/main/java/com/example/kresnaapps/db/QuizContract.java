package com.example.kresnaapps.db;

import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract(){

    }

    public static class CategoriesTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_categories";
        public static final String COLUMN_CATEGORY = "category";
    }

    public static class QuestionTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_question";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_ANSWERSTR = "answerStr";
        public static final String COLUMN_DIFFICULTY = "difficulty";
        public static final String COLUMN_CATEGORY_ID = "category_id";
        public static final String COLUMN_VOICE = "voice_over";
    }
}
