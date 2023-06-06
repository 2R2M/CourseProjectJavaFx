package com.example.mycourseproject.model;

import java.sql.Date;

/**
 * Класс Expense представляет сущность расхода.
 */
public class Expense {
    private int ID;
    private String title;
    private Double sum;
    private String date;
    private int ID_budget;

    /**
     * Конструктор класса Expense.
     *
     * @param title     название расхода
     * @param sum       сумма расхода
     * @param date      дата расхода
     * @param ID_budget идентификатор бюджета, к которому относится расход
     */
    public Expense(String title, Double sum, String date, int ID_budget) {
        this.title = title;
        this.sum = sum;
        this.date = date;
        this.ID_budget = ID_budget;
    }

    /**
     * Конструктор класса Expense с указанием идентификатора.
     *
     * @param id        идентификатор расхода
     * @param title     название расхода
     * @param sum       сумма расхода
     * @param date      дата расхода
     * @param ID_budget идентификатор бюджета, к которому относится расход
     */
    public Expense(int id, String title, Double sum, String date, int ID_budget) {
        this.ID = id;
        this.title = title;
        this.sum = sum;
        this.date = date;
        this.ID_budget = ID_budget;
    }

    /**
     * Получает идентификатор расхода.
     *
     * @return идентификатор расхода
     */
    public int getID() {
        return ID;
    }

    /**
     * Устанавливает идентификатор расхода.
     *
     * @param ID идентификатор расхода
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Получает название расхода.
     *
     * @return название расхода
     */
    public String getTitle() {
        return title;
    }

    /**
     * Устанавливает название расхода.
     *
     * @param title название расхода
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Получает сумму расхода.
     *
     * @return сумма расхода
     */
    public Double getSum() {
        return sum;
    }

    /**
     * Устанавливает сумму расхода.
     *
     * @param sum сумма расхода
     */
    public void setSum(Double sum) {
        this.sum = sum;
    }

    /**
     * Получает дату расхода.
     *
     * @return дата расхода
     */
    public String getDate() {
        return date;
    }

    /**
     * Устанавливает дату расхода.
     *
     * @param date дата расхода
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Получает идентификатор бюджета, к которому относится расход.
     *
     * @return идентификатор бюджета
     */
    public int getID_budget() {
        return ID_budget;
    }

    /**
     * Устанавливает идентификатор бюджета, к которому относится расход.
     *
     * @param ID_budget идентификатор бюджета
     */
    public void setID_budget(int ID_budget) {
        this.ID_budget = ID_budget;
    }
}
