package com.example.mycourseproject.db;

import com.example.mycourseproject.db.DAOCharts;
import com.example.mycourseproject.db.DAOData;

public interface DAOFactory<T> {
    DAOData<T> createBudgetDB();
    DAOData<T> createExpenseDB();
    DAOData<T> createIncomeDB();
    DAOCharts createChartsDB();
}
