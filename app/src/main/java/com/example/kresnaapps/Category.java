package com.example.kresnaapps;

public class Category {
    public static final int LEARN_NUMBERS = 1;
    public static final int LEARN_ADDITION = 2;
    public static final int LEARN_SUBSTRACTION = 3;
    public static final int LEARN_MULTIPLICATION = 4;
    public static final int SOCIAL_EXPERIENCE = 5;
    public static final int QUIZ = 6;

    private int id;
    private String category;

    public Category(){

    }

    public Category(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
