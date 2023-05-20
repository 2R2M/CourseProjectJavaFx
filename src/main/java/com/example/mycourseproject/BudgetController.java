package com.example.mycourseproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BudgetController implements Initializable {
    private Budget budget;
    private JDBCPostgreSQL db;
    @FXML
    public ListView expenseListView;
    @FXML
    public ListView incomeListView;

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public void setDb(JDBCPostgreSQL db) {
        this.db = db;
    }

    public void addIncome(ActionEvent actionEvent) {
    }

    public void addExpense(ActionEvent actionEvent) {


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void uploadExpence(ActionEvent actionEvent) {
        List<Expense> expenses= new ArrayList<Expense>();
        List<Income> incomes = new ArrayList<Income>();

        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT * FROM expense WHERE id_budget="+budget.getID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Expense expense = new Expense(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getDouble("sum"),
                        rs.getDate("date"),
                        rs.getInt("id_budget")
                );
                expenses.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ObservableList<Expense> observableExpenseList = FXCollections.observableArrayList(expenses);
        expenseListView.setItems(observableExpenseList);
        expenseListView.setCellFactory(param -> new ListCell<Expense>() {
            @Override
            protected void updateItem(Expense expense, boolean empty) {
                super.updateItem(expense, empty);
                if (empty || expense == null) {
                    setText(null);
                } else {
                    // Здесь вы можете установить собственное название для элемента
                    setText("Категория: " + expense.getTitle()+" Сумма: " + expense.getSum().toString()+" Дата: " + expense.getDate().toString()); // Замените "Название: " на ваше желаемое название
                }
            }
        });
    }

    public void uploadIncome(ActionEvent actionEvent) {

        List<Income> incomes = new ArrayList<Income>();

        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT * FROM income WHERE id_budget="+budget.getID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Income income = new Income(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getDouble("sum"),
                        rs.getDate("date"),
                        rs.getInt("id_budget")
                );
                incomes.add(income);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ObservableList<Income> observableIncomeList = FXCollections.observableArrayList(incomes);
        incomeListView.setItems(observableIncomeList);
        incomeListView.setCellFactory(param -> new ListCell<Income>() {
            @Override
            protected void updateItem(Income income, boolean empty) {
                super.updateItem(income, empty);
                if (empty || income == null) {
                    setText(null);
                } else {
                    // Здесь вы можете установить собственное название для элемента
                    setText("Категория: " + income.getTitle()+" Сумма: " + income.getSum()+" Дата: " + income.getDate().toString()); // Замените "Название: " на ваше желаемое название
                }
            }
        });
    }

    public void DeleteIncome(ActionEvent actionEvent) {
    }

    public void DeleteExpense(ActionEvent actionEvent) {
    }
}
