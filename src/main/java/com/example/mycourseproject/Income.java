package com.example.mycourseproject;

import java.time.LocalDate;
import java.util.Date;

public class Income {
    private int ID;
    private String title;
    private double sum;
    private Date date;
    private int ID_budget;

    public Income(int ID, String title, double sum, Date date, int ID_budget) {
        this.ID = ID;
        this.title = title;
        this.sum = sum;
        this.date = date;
        this.ID_budget = ID_budget;
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

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getID_budget() {
        return ID_budget;
    }

    public void setID_budget(int ID_budget) {
        this.ID_budget = ID_budget;
    }
}