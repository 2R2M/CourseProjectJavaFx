package com.example.mycourseproject;

public class Budget {
    private int ID;
    private String title;
    public  Budget(){}

    public Budget(int ID, String title) {
        this.ID = ID;
        this.title = title;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
