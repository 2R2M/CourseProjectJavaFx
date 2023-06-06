package com.example.mycourseproject.db;

import com.example.mycourseproject.model.Income;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс IncomeDB предоставляет методы для работы с данными о доходах в базе данных.
 * Реализует интерфейс DAOData для операций добавления, получения и удаления данных.
 */
public class IncomeDB implements DAOData<Income> {
    private int idBudget;
    Connection connection;

    /**
     * Конструктор класса IncomeDB.
     *
     * @param connection соединение с базой данных
     */
    public IncomeDB(Connection connection) {
        this.connection = connection;
    }

    /**
     * Метод добавляет информацию о доходе в базу данных.
     *
     * @param model объект типа Income, содержащий информацию о доходе
     */
    @Override
    public void addInf(Income model) {
        String insertQuery = "INSERT INTO income (title, date, sum, id_budget) VALUES (?, ?, ?, ?)";

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
     * Метод получает все информацию о доходах из базы данных.
     *
     * @return список объектов типа Income, содержащих информацию о доходах
     */
    @Override
    public List<Income> getAllInf() {
        List<Income> incomes = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM income WHERE id_budget=" + idBudget);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Income income = new Income(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getDouble("sum"),
                        String.valueOf(rs.getDate("date")),
                        rs.getInt("id_budget")
                );
                incomes.add(income);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incomes;
    }

    /**
     * Метод удаляет информацию о доходе из базы данных по заданному идентификатору.
     *
     * @param id идентификатор дохода
     */
    @Override
    public void deleteInf(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM income where id=?")) {
            // Установка значения параметра запроса
            statement.setInt(1, id); // employeeId - идентификатор сотрудника для удаления
            // Выполнение запроса на удаление данных
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод возвращает идентификатор бюджета.
     *
     * @return идентификатор бюджета
     */
    public int getIdBudget() {
        return idBudget;
    }

    /**
     * Метод устанавливает идентификатор бюджета.
     *
     * @param idBudget идентификатор бюджета
     */
    public void setIdBudget(int idBudget) {
        this.idBudget = idBudget;
    }
}
