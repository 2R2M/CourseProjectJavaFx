package com.example.mycourseproject;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Класс Income представляет доход и содержит информацию о его свойствах.
 */
public class Income {
    private int id;
    private String title;
    private double sum;
    private String date;
    private int ID_budget;

    /**
     * Конструктор класса Income.
     *
     * @param title     название дохода
     * @param sum       сумма дохода
     * @param date      дата дохода
     * @param ID_budget идентификатор бюджета, к которому относится доход
     */
    public Income(String title, double sum, String date, int ID_budget) {
        this.title = title;
        this.sum = sum;
        this.date = date;
        this.ID_budget = ID_budget;
    }

    /**
     * Конструктор класса Income.
     *
     * @param id        идентификатор дохода
     * @param title     название дохода
     * @param sum       сумма дохода
     * @param date      дата дохода
     * @param id_budget идентификатор бюджета, к которому относится доход
     */
    public Income(int id, String title, double sum, String date, int id_budget) {
        this.id = id;
        this.title = title;
        this.sum = sum;
        this.date = date;
        this.ID_budget = ID_budget;
    }

    /**
     * Метод возвращает название дохода.
     *
     * @return название дохода
     */
    public String getTitle() {
        return title;
    }

    /**
     * Метод устанавливает название дохода.
     *
     * @param title название дохода
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Метод возвращает сумму дохода.
     *
     * @return сумма дохода
     */
    public double getSum() {
        return sum;
    }

    /**
     * Метод устанавливает сумму дохода.
     *
     * @param sum сумма дохода
     */
    public void setSum(double sum) {
        this.sum = sum;
    }

    /**
     * Метод возвращает дату дохода.
     *
     * @return дата дохода
     */
    public String getDate() {
        return date;
    }

    /**
     * Метод устанавливает дату дохода.
     *
     * @param date дата дохода
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Метод возвращает идентификатор бюджета, к которому относится доход.
     *
     * @return идентификатор бюджета
     */
    public int getID_budget() {
        return ID_budget;
    }

    /**
     * Метод устанавливает идентификатор бюджета, к которому относится доход.
     *
     * @param ID_budget идентификатор бюджета
     */
    public void setID_budget(int ID_budget) {
        this.ID_budget = ID_budget;
    }

    /**
     * Метод возвращает идентификатор дохода.
     *
     * @return идентификатор дохода
     */
    public int getID() {
        return id;
    }
}
