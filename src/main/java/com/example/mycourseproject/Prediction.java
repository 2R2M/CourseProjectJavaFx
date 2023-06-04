package com.example.mycourseproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Класс Prediction предоставляет методы для работы с прогнозированием расходов.
 * Реализует интерфейс Initializable для инициализации контроллера перед его использованием.
 */
public class Prediction implements Initializable {
    List<String> dates;
    List<Double> amounts;
    private AiModel aiModel;
    private ChartDB chartDB;
    @FXML
    private TextArea AnsTextArea;

    /**
     * Метод генерирует ответ на запрос и отображает его в текстовой области.
     *
     * @param actionEvent событие действия
     * @throws Exception если происходит ошибка во время генерации ответа
     */
    public void generateAnswer(ActionEvent actionEvent) throws Exception {
        chartDB.FillListsExpense(dates, amounts);
        String question = aiModel.DataPreparation(dates, amounts);
        aiModel.sendOpenAIRequest(question);
        AnsTextArea.setText(aiModel.getText());
    }

    /**
     * Метод инициализирует контроллер перед его использованием.
     *
     * @param location  расположение объекта инициализации
     * @param resources ресурсы, используемые объектом инициализации
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DAOFactoryDB daoFactoryDB = new DAOFactoryDB();
        chartDB = (ChartDB) daoFactoryDB.createChartsDB();
        dates = new ArrayList<>();
        amounts = new ArrayList<>();
        aiModel = new AiModel();
    }
}
