package dormitorymanagementsystem.controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import dormitorymanagementsystem.model.resident.Admin;
import dormitorymanagementsystem.util.SidePanelInstance;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class MainWindowController {

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger menuHamburger;

    private Stage primaryStage;
    private Admin admin;

    void setAdmin(Admin admin) {
        this.admin = admin;
    }

    void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initialize() {
        Platform.runLater(() -> {
            SidePanelInstance panelInstance = new SidePanelInstance(drawer, menuHamburger, primaryStage);
            panelInstance.sidePanelInit();
            panelInstance.setUpHamburger();
        });
    }

}
