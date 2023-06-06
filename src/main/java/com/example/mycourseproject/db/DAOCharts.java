package com.example.mycourseproject.db;

import java.util.List;

public interface DAOCharts {
    void FillListsExpense(List<String> dates, List<Double> sums);
    void FillListsIncome(List<String> dates, List<Double> sums);
    String avgExpense();
    String sumExpense();
    String medExpense();
    String avgIncome();
    String sumIncome();
    String medIncome();
}
