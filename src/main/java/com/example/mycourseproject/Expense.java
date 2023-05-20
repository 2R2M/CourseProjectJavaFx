package com.example.mycourseproject;

import javafx.beans.Observable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Expense {
    private int ID;
    private String title;
    private Double sum;
    private Date date;
    private int ID_budget;


    public Expense(int ID, String title, Double sum, Date date, int
            ID_budget) {
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

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
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
