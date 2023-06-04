package com.example.mycourseproject;

public interface DAOFactory<T> {
    DAOData<T> createBudgetDB();
    DAOData<T> createExpenseDB();
    DAOData<T> createIncomeDB();
    DAOCharts createChartsDB();
}
