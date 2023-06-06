package com.example.mycourseproject.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 650);

        scene.getStylesheets().add(getClass().getResource("/com/example/mycourseproject/styles/hellostryle.css").toString());
        //scene.getStylesheets().add(getClass().getResource("hellostyle.css").toString());
        stage.setTitle("Управление бюджетом");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}