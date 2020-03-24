module JavaFxApplication {

    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.sql;
    requires org.jetbrains.annotations;
    requires com.jfoenix;
    requires java.sql.rowset;

    opens dormitorymanagementsystem;
    opens dormitorymanagementsystem.controllers;
    opens fxml;
    opens archive;
    opens css;
    opens img;
}