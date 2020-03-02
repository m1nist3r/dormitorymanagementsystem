package dormitorymanagementsystem.controllers;

import dormitorymanagementsystem.model.admin.Admin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class SidePanelController {
    //region Fields
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
    //endregion
    private Stage primaryStage;
    private Admin admin;
    @FXML
    private Button settingsListButton;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initialize() {
        imageInitialization();
    }

    void imageInitialization() {
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addResident.fxml"));
            Parent root = loader.load();
            AddResidentController addResidentController = loader.getController();
            addResidentController.setPrimaryStage(primaryStage);
            addResidentController.setAdmin(admin);
            primaryStage.setHeight(760);
            primaryStage.setWidth(1280);
            primaryStage.getScene().getStylesheets().add(
                    getClass().getResource("/css/error.css").toExternalForm());
            primaryStage.getScene().setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showArchive() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ArchiveList.fxml"));
            Parent root = loader.load();
            ArchiveListController archiveListController = loader.getController();
            archiveListController.setPrimaryStage(primaryStage);
            archiveListController.setAdmin(admin);
            primaryStage.setHeight(760);
            primaryStage.setWidth(1280);
            primaryStage.getScene().setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showListOfResident() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ResidentList.fxml"));
            Parent root = loader.load();
            ResidentListController residentListController = loader.getController();
            residentListController.setPrimaryStage(primaryStage);
            residentListController.setAdmin(admin);
            primaryStage.setHeight(760);
            primaryStage.setWidth(1280);
            primaryStage.getScene().setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showListOfRooms() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RoomTableView.fxml"));
            Parent root = loader.load();
            RoomTableViewController roomTableViewController = loader.getController();
            roomTableViewController.setPrimaryStage(primaryStage);
            roomTableViewController.setAdmin(admin);
            primaryStage.setHeight(760);
            primaryStage.setWidth(1280);
            primaryStage.getScene().setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showPayments() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PaymentList.fxml"));
            Parent root = loader.load();
            PaymentWindowController paymentWindowController = loader.getController();
            paymentWindowController.setPrimaryStage(primaryStage);
            paymentWindowController.setAdmin(admin);
            primaryStage.setHeight(760);
            primaryStage.setWidth(1280);
            primaryStage.getScene().setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showSettings() {

    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
