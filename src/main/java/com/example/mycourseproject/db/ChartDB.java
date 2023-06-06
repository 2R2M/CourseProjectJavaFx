package com.example.mycourseproject.db;

import com.example.mycourseproject.db.DAOCharts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ChartDB implements DAOCharts {
    Connection connection;
    public ChartDB(Connection connection){
        this.connection = connection;
    }
    @Override
    public void FillListsExpense(List<String> dates, List<Double> sums)
    {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT SUM(sum), date FROM expense GROUP BY date;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dates.add(rs.getString("date"));
                sums.add(rs.getDouble("sum"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void FillListsIncome(List<String> dates, List<Double> sums)
    {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT SUM(sum), date FROM income GROUP BY date;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dates.add(rs.getString("date"));
                sums.add(rs.getDouble("sum"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String sumExpense(){
        String sum="";
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT SUM(sum) FROM expense;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sum = rs.getString("sum");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }
    @Override
    public String sumIncome(){
        String sum="";
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT SUM(sum) FROM income;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sum = rs.getString("sum");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }


    @Override
    public String avgExpense(){
        String avg ="";
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT AVG(daily_sum) AS average_value\n" +
                    "FROM (\n" +
                    "    SELECT date, SUM(sum) AS daily_sum\n" +
                    "    FROM expense\n" +
                    "    GROUP BY date\n" +
                    ") AS subquery;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                avg = rs.getString("average_value");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avg;
    }
    @Override
    public String medExpense(){
        String med ="";
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT median_value\n" +
                    "FROM (\n" +
                    "    SELECT sum AS median_value,\n" +
                    "           ROW_NUMBER() OVER (ORDER BY sum) AS row_num,\n" +
                    "           COUNT(*) OVER () AS total_rows\n" +
                    "    FROM expense\n" +
                    ") AS subquery\n" +
                    "WHERE row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2));");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               med = rs.getString("median_value");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return med;
    }

    @Override
    public String avgIncome(){
        String avg ="";
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT AVG(daily_sum) AS average_value\n" +
                    "FROM (\n" +
                    "    SELECT date, SUM(sum) AS daily_sum\n" +
                    "    FROM income\n" +
                    "    GROUP BY date\n" +
                    ") AS subquery;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                avg = rs.getString("average_value");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avg;
    }
    @Override
    public String medIncome(){
        String med ="";
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT median_value\n" +
                    "FROM (\n" +
                    "    SELECT sum AS median_value,\n" +
                    "           ROW_NUMBER() OVER (ORDER BY sum) AS row_num,\n" +
                    "           COUNT(*) OVER () AS total_rows\n" +
                    "    FROM income\n" +
                    ") AS subquery\n" +
                    "WHERE row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2));");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                med = rs.getString("median_value");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return med;
    }
}
