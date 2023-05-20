package com.example.mycourseproject;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NewBudget {
    private JDBCPostgreSQL db;
    public TextField nameTextField;



    public void setDb(JDBCPostgreSQL db) {
        this.db = db;
    }

    public void createBudget(ActionEvent actionEvent) throws SQLException {
        String nameBudget = nameTextField.getText();
        Statement statement = db.getConnection().createStatement();
        statement.executeUpdate("INSERT INTO budget(title) VALUES ('"+nameBudget+"');");
    }
}
