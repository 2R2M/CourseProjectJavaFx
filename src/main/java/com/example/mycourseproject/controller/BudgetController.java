package com.example.mycourseproject.controller;

import com.example.mycourseproject.model.Budget;
import com.example.mycourseproject.db.DAOFactoryDB;
import com.example.mycourseproject.db.ExpenseDB;
import com.example.mycourseproject.db.IncomeDB;
import com.example.mycourseproject.model.Expense;
import com.example.mycourseproject.model.Income;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class BudgetController implements Initializable {
    public TextField incomeTitleField;
    public DatePicker incomeDateField;
    public TextField incomeSumField;
    public TextField expenseTitleField;
    public DatePicker expenseDateField;
    public TextField expenseSumField;
    public Button deleteExpense;
    public Button deleteIncome;
    private Budget budget;

    DAOFactoryDB daoFactoryDB;
    ExpenseDB expenseDB;
    IncomeDB incomeDB;
    @FXML
    public ListView expenseListView;
    @FXML
    public ListView incomeListView;

    public void setBudget(Budget budget) {
        this.budget = budget;
    }


    public void addIncome(ActionEvent actionEvent) {
        incomeDB.addInf(new Income(incomeTitleField.getText(), Double.parseDouble(incomeSumField.getText()),String.valueOf(incomeDateField.getValue()), budget.getID()));
    }

    public void addExpense(ActionEvent actionEvent) {

        expenseDB.addInf(new Expense(expenseTitleField.getText(), Double.parseDouble(expenseSumField.getText()), String.valueOf(expenseDateField.getValue()), budget.getID()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        daoFactoryDB = new DAOFactoryDB();
        expenseDB = (ExpenseDB) daoFactoryDB.createExpenseDB();
        incomeDB = (IncomeDB) daoFactoryDB.createIncomeDB();
        deleteExpense.setOnAction(event -> {
            Expense expense = (Expense) expenseListView.getSelectionModel().getSelectedItem();
            if (expense != null) {
                expenseDB.deleteInf(expense.getID());
            }
            else
            {
                System.out.println("Ошибка");
            }
        });

        deleteIncome.setOnAction(event -> {
            Income income = (Income) incomeListView.getSelectionModel().getSelectedItem();
            if (income != null) {
                incomeDB.deleteInf(income.getID());
            }
            else
            {
                System.out.println("Ошибка");
            }
        });

    }



    public void uploadExpence(ActionEvent actionEvent) {
        expenseDB.setId_budget(budget.getID());
        ObservableList<Expense> observableExpenseList = FXCollections.observableArrayList(expenseDB.getAllInf());
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
        incomeDB.setIdBudget(budget.getID());
        ObservableList<Income> observableIncomeList = FXCollections.observableArrayList(incomeDB.getAllInf());
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
