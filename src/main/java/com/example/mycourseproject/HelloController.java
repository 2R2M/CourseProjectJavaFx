package com.example.mycourseproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.Console;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public ListView budgetListView;
    @FXML
    private Label welcomeText;
    private Button opnCatWin;
    JDBCPostgreSQL db;

    public void createBudget(ActionEvent actionEvent){
        db = new JDBCPostgreSQL();
        db.Connection();
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("new-budget.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            NewBudget newBudget = loader.getController();
            newBudget.setDb(db);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openExpenseChart(ActionEvent actionEvent) {
        db = new JDBCPostgreSQL();
        db.Connection();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("charts.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            Charts chart = loader.getController();
            chart.setDb(db);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void openExpenseForecast(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("prediction.fxml"));
            Stage stage = new Stage();
            AiModel model = new AiModel();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void updateBudget(ActionEvent actionEvent) {
        db = new JDBCPostgreSQL();
        db.Connection();
        ArrayList<Budget> budgets = new ArrayList<Budget>();
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT * FROM budget");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Budget budget = new Budget(rs.getInt("ID"),
                        rs.getString("title")

                );
                System.out.println(budget.getTitle());
                budgets.add(budget);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<Budget> observableList = FXCollections.observableArrayList(budgets);
        budgetListView.setItems(observableList);
        budgetListView.setCellFactory(param -> new ListCell<Budget>() {
            @Override
            protected void updateItem(Budget budget, boolean empty) {
                super.updateItem(budget, empty);
                if (empty || budget == null) {
                    setText(null);
                } else {
                    // Здесь вы можете установить собственное название для элемента
                    setText("Название: " + budget.getTitle()); // Замените "Название: " на ваше желаемое название
                }
            }
        });
        budgetListView.setOnMouseClicked(event -> {
            Budget selectedBudget = (Budget) budgetListView.getSelectionModel().getSelectedItem();
            System.out.println(selectedBudget.getTitle().getClass());
            System.out.println(budgets.get(selectedBudget.getID()-1).getTitle());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("open-budget.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));
                stage.show();
                BudgetController budgetController = loader.getController();
                System.out.println(selectedBudget.getTitle().getClass());
                System.out.println(budgets.get(selectedBudget.getID()-1).getTitle());
                budgetController.setDb(db);
                budgetController.setBudget(budgets.get(selectedBudget.getID()-1));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}