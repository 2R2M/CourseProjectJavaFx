package com.example.mycourseproject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс ExpenseDB представляет реализацию интерфейса DAOData для работы с данными расходов в базе данных.
 */
public class ExpenseDB implements DAOData<Expense> {
    private int idBudget;
    Connection connection;

    /**
     * Конструктор класса ExpenseDB.
     *
     * @param connection объект Connection для установления связи с базой данных
     */
    public ExpenseDB(Connection connection) {
        this.connection = connection;
    }

    /**
     * Метод получает все записи о расходах из базы данных, связанных с определенным бюджетом.
     *
     * @return список объектов Expense, представляющих расходы
     */
    @Override
    public List<Expense> getAllInf() {
        List<Expense> expenses = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM expense WHERE id_budget=" + idBudget);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Expense expense = new Expense(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getDouble("sum"),
                        String.valueOf(rs.getDate("date")),
                        rs.getInt("id_budget")
                );
                expenses.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }

    /**
     * Метод добавляет новую запись о расходе в базу данных.
     *
     * @param model объект Expense, представляющий расход
     */
    @Override
    public void addInf(Expense model) {
        String insertQuery = "INSERT INTO expense (title, date, sum, id_budget) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            // Установка значений параметров запроса
            statement.setString(1, model.getTitle());
            statement.setDate(2, Date.valueOf(model.getDate()));
            statement.setDouble(3, model.getSum());
            statement.setInt(4, model.getID_budget());
            // Выполнение запроса на вставку данных в базу данных
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод удаляет запись о расходе из базы данных по заданному идентификатору.
     *
     * @param id идентификатор расхода
     */
    @Override
    public void deleteInf(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM expense where id=?")) {
            // Установка значения параметра запроса
            statement.setInt(1, id);
            // Выполнение запроса на удаление данных
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод возвращает идентификатор бюджета, связанного с объектом ExpenseDB.
     *
     * @return идентификатор бюджета
     */
    public int getId_budget() {
        return idBudget;
    }

    /**
     * Метод устанавливает идентификатор бюджета для объекта ExpenseDB.
     *
     * @param idBudget идентификатор бюджета
     */
    public void setId_budget(int idBudget) {
        this.idBudget = idBudget;
    }
}
