package com.example.kresnaapps.db;

public class Question {

    public static final String DIFFICULTY_EASY = "easy";
    public static final String DIFFICULTY_MEDIUM = "medium";
    public static final String DIFFICULTY_HARD = "hard";

    private String question, option1, option2, answerStr, difficulty, voiceOver;
    private int answer, id, categoryId;

    public Question(){

    }

    public Question(String question, String option1, String option2, String answerStr, String difficulty, int categoryId, String voiceOver) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.answerStr = answerStr;
        this.difficulty = difficulty;
        this.categoryId = categoryId;
        this.voiceOver = voiceOver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getAnswerStr() {
        return answerStr;
    }

    public void setAnswerStr(String answerStr) {
        this.answerStr = answerStr;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public static String[] getAllDifficultyLevels(){
        return new String[]{
                DIFFICULTY_EASY,
                DIFFICULTY_MEDIUM,
                DIFFICULTY_HARD
        };
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getVoiceOver() {
        return voiceOver;
    }

    public void setVoiceOver(String voiceOver) {
        this.voiceOver = voiceOver;
    }
}
