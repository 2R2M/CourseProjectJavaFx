package com.example.mycourseproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Charts {
    public CategoryAxis xAxis;
    public NumberAxis yAxis;
    public LineChart expenseChart;
    private JDBCPostgreSQL db;
    public void setDb(JDBCPostgreSQL db) {
        this.db = db;
    }
    public void showStatistics(ActionEvent actionEvent) {


           }

    public void updateChartExpense(ActionEvent actionEvent) {
        Map<Date, Double> map_data = new HashMap<Date, Double>();
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<Double> sums = new ArrayList<>();
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT SUM(sum), date FROM expense GROUP BY date;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dates.add(rs.getString("date"));
                sums.add(rs.getDouble("sum"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        XYChart.Series series = new XYChart.Series();
        for (int i=0; i < dates.size(); i++) {
            series.getData().add(new XYChart.Data<String, Double>(dates.get(i), sums.get(i)));
        }
        expenseChart.getData().add(series);

        ObservableList<String> observableDateList = FXCollections.observableArrayList(dates);
        ObservableList<Double> observableDoubleList = FXCollections.observableArrayList(sums);

    }

    public void updateChartIncome(ActionEvent actionEvent) {
    }

    public void showStatisticsIncome(ActionEvent actionEvent) {
    }
}
