package com.example.mycourseproject.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactoryDB implements DAOFactory {
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/CourseProjectBudget";
    static final String USER = "postgres";
    static final String PASS = "Fa22cc33";
    Connection connection;
    public DAOFactoryDB() {
        System.out.println("Testing connection to PostgreSQL JDBC");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
        }
        System.out.println("PostgreSQL JDBC Driver successfully connected");

        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
    }

    @Override
    public DAOData createBudgetDB() {
        return new BudgetDB(connection);
    }

    @Override
    public DAOData createExpenseDB() {
        return new ExpenseDB(connection);
    }

    @Override
    public DAOData createIncomeDB() {
        return new IncomeDB(connection);
    }

    @Override
    public DAOCharts createChartsDB() {
        return new ChartDB(connection);
    }
}
