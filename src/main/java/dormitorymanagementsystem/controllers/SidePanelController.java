package dormitorymanagementsystem.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;


public class SidePanelController {

    @FXML
    private Button addResidentButton;

    @FXML
    private Button listOfResidentButton;

    @FXML
    private Button listOfRoomButton;

    @FXML
    private Button paymentDetailsList;

    @FXML
    private Button archiveListButton;

//    @FXML
//    private Button settingsListButton;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initialize() {
        Image imageButtom = new Image(getClass().getResourceAsStream("/img/baseline_person_add_black_18dp.png"));
        addResidentButton.setGraphic(new ImageView(imageButtom));
        imageButtom = new Image(getClass().getResourceAsStream("/img/baseline_group_black_18dp.png"));
        listOfResidentButton.setGraphic(new ImageView(imageButtom));
        imageButtom = new Image(getClass().getResourceAsStream("/img/baseline_business_black_18dp.png"));
        listOfRoomButton.setGraphic(new ImageView(imageButtom));
        imageButtom = new Image(getClass().getResourceAsStream("/img/baseline_payment_black_18dp.png"));
        paymentDetailsList.setGraphic(new ImageView(imageButtom));
        imageButtom = new Image(getClass().getResourceAsStream("/img/baseline_archive_black_18dp.png"));
        archiveListButton.setGraphic(new ImageView(imageButtom));
    }

    @FXML
    void addResident() {
        //changeScene("/fxml/");
    }

    @FXML
    void showArchive() {

    }

    @FXML
    void showListOfResident() {
        String pathXML = "/fxml/ResidentList.fxml";
        if (!ResidentListController.is_Active) {
            try {
                changeScene(pathXML);
                ResidentListController.is_Active = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void showListOfRooms() {

    }

    @FXML
    void showPayments() {

    }

    @FXML
    void showSettings() {

    }

    private void changeScene(String pathXML) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathXML));
        Parent root = loader.load();
        primaryStage.setHeight(760);
        primaryStage.setWidth(1280);
        primaryStage.getScene().setRoot(root);
    }
}
