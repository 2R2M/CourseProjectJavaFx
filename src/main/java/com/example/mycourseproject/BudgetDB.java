package com.example.mycourseproject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BudgetDB implements DAOData<Budget>{
    Connection connection;
    public BudgetDB(Connection connection)
    {
        this.connection = connection;
    }
    @Override
    public List<Budget> getAllInf(){
        ArrayList<Budget> budgets = new ArrayList<Budget>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM budget");
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
        return budgets;
    }

    @Override
    public void addInf(Budget model) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO budget(title) VALUES ('" + model.getTitle() + "');");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    @Override
    public void deleteInf(int id){
        try {

            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM budget WHERE id=" + id);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
