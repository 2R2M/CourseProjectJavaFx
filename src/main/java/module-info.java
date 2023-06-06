module com.example.mycourseproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires api;
    requires service;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;
    requires org.json;


    opens com.example.mycourseproject to javafx.fxml;
    exports com.example.mycourseproject;
    exports com.example.mycourseproject.model;
    opens com.example.mycourseproject.model to javafx.fxml;
    exports com.example.mycourseproject.controller;
    opens com.example.mycourseproject.controller to javafx.fxml;
    exports com.example.mycourseproject.db;
    opens com.example.mycourseproject.db to javafx.fxml;

}