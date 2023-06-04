package com.example.mycourseproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Класс HelloController представляет контроллер для главного окна приложения.
 */
public class HelloController implements Initializable {
    public ListView budgetListView;
    public TextField nameTextField;
    @FXML
    private Label welcomeText;
    private Button opnCatWin;
    DAOFactoryDB daoFactoryDB;
    BudgetDB budgetDB;

    /**
     * Метод обрабатывает событие создания бюджета.
     *
     * @param actionEvent событие создания бюджета
     * @throws SQLException возникает при ошибке работы с базой данных
     */
    public void createBudget(ActionEvent actionEvent) throws SQLException {
        String nameBudget = nameTextField.getText();
        Budget budget = new Budget(nameBudget);
        budgetDB.addInf(budget);
    }

    /**
     * Метод открывает окно с графическим отображением данных о расходах.
     *
     * @param actionEvent событие открытия окна с графическим отображением данных
     */
    public void openExpenseChart(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("charts.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("/com/example/mycourseproject/styles/hellostryle.css").toString());
            stage.setScene(scene);
            stage.setTitle("Графическое отображение данных");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод открывает окно с прогнозированием расходов.
     *
     * @param actionEvent событие открытия окна с прогнозированием расходов
     */
    public void openExpenseForecast(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("prediction.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("/com/example/mycourseproject/styles/hellostryle.css").toString());
            stage.setScene(scene);
            stage.setTitle("Прогнозирование расходов");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод инициализирует контроллер.
     *
     * @param url            URL-адрес корневого объекта, относительно которого происходит загрузка FXML-файла
     * @param resourceBundle объект ResourceBundle, предоставляющий локализованные строки
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        daoFactoryDB = new DAOFactoryDB();
        budgetDB = (BudgetDB) daoFactoryDB.createBudgetDB();
    }

    /**
     * Метод обновляет список бюджетов в ListView при инициализации и при выборе бюджета из списка.
     *
     * @param actionEvent событие обновления списка бюджетов
     */
    public void updateBudget(ActionEvent actionEvent) {
        ObservableList<Budget> observableList = FXCollections.observableArrayList(budgetDB.getAllInf());
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
            System.out.println(budgetDB.getAllInf().get(selectedBudget.getID() - 1).getTitle());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("open-budget.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(loader.load());
                scene.getStylesheets().add(getClass().getResource("/com/example/mycourseproject/styles/hellostryle.css").toString());
                stage.setTitle("Расширенный бюджет");
                stage.setScene(scene);
                stage.show();
                BudgetController budgetController = loader.getController();
                budgetController.setBudget(budgetDB.getAllInf().get(selectedBudget.getID() - 1));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
