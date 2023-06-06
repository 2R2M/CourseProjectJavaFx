package com.example.mycourseproject.controller;

import com.example.mycourseproject.db.ChartDB;
import com.example.mycourseproject.db.DAOFactoryDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.*;

public class Charts implements Initializable {
    public CategoryAxis xAxis;
    public NumberAxis yAxis;
    public LineChart expenseChart;
    public LineChart incomeChart;
    public TextField expSum;
    public TextField expAvg;
    public TextField expMed;
    public TextField incSum;
    public TextField incAvg;
    public TextField incMed;
    public CategoryAxis xAxis_expense;
    private DAOFactoryDB daoFactoryDB;
    ChartDB chartDB;




    public void updateChartExpense(ActionEvent actionEvent) {
        Map<Date, Double> map_data = new HashMap<Date, Double>();
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<Double> sums = new ArrayList<>();
        chartDB.FillListsExpense(dates, sums);
        XYChart.Series series = new XYChart.Series();
        for (int i=0; i < dates.size(); i++) {
            series.getData().add(new XYChart.Data<String, Double>(dates.get(i), sums.get(i)));
        }

        series.setName("Серия расходов");
        expenseChart.getData().add(series);
        expenseChart.getXAxis().setTickLabelFill(Color.WHITE);
        expenseChart.getYAxis().setTickLabelFill(Color.WHITE);
        expenseChart.setTitle("Расходы");
        ObservableList<String> observableDateList = FXCollections.observableArrayList(dates);
        ObservableList<Double> observableDoubleList = FXCollections.observableArrayList(sums);

    }

    public void updateChartIncome(ActionEvent actionEvent) {
       ArrayList<String> dates = new ArrayList<>();
        ArrayList<Double> sums = new ArrayList<>();
        chartDB.FillListsIncome(dates, sums);
            XYChart.Series series = new XYChart.Series();
            for (int i=0; i < dates.size(); i++) {
                series.getData().add(new XYChart.Data<String, Double>(dates.get(i), sums.get(i)));
            }
            series.setName("Серия доходов");

            incomeChart.getData().add(series);
            incomeChart.setTitle("Доходы");
            incomeChart.getXAxis().setTickLabelFill(Color.WHITE);
            incomeChart.getYAxis().setTickLabelFill(Color.WHITE);
        ObservableList<String> observableDateList = FXCollections.observableArrayList(dates);
        ObservableList<Double> observableDoubleList = FXCollections.observableArrayList(sums);

    }

    public void showStatisticsIncome(ActionEvent actionEvent) {
    }

    public void showStatisticsExpense(ActionEvent actionEvent) {

        expSum.setText(chartDB.sumExpense());
      expMed.setText(chartDB.medExpense());
        expAvg.setText(chartDB.avgExpense());
        /*
        /*List<Double> summ = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        AiModel aiModel = new AiModel();
        String temp = aiModel.getText();
        System.out.println(temp);
        if (temp == null)
        {
            System.out.println("Откройте окно прогнозирования и выполните прогноз!");
        }

        aiModel.ReverseDataPreparation(temp, dates, summ);
        XYChart.Series series = new XYChart.Series();
        for (int i=0; i < dates.size(); i++) {
            series.getData().add(new XYChart.Data<String, Double>(dates.get(i), summ.get(i)));
        }
        series.setName("Прогноз");
        expenseChart.getData().add(series);

         */
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        daoFactoryDB = new DAOFactoryDB();
        chartDB = (ChartDB) daoFactoryDB.createChartsDB();
    }

    public void showStatisticsIncom(ActionEvent actionEvent) {

        incSum.setText(chartDB.sumIncome());
        incMed.setText(chartDB.medIncome());
        incAvg.setText(chartDB.avgIncome());
    }
}
