package dormitorymanagementsystem.controllers;

import com.jfoenix.controls.JFXDrawer;
import dormitorymanagementsystem.model.admin.Admin;
import dormitorymanagementsystem.model.payment.Payment;
import dormitorymanagementsystem.model.payment.PaymentDAO;
import dormitorymanagementsystem.util.SidePanelInstance;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class PaymentWindowController {

    @FXML
    private TableColumn<Payment, Integer> adminId;
    @FXML
    private TableColumn<Payment, Integer> paymentId;
    @FXML
    private TableColumn<Payment, Integer> residentId;
    @FXML
    private TableColumn<Payment, String> residentNameColumn;
    @FXML
    private TableColumn<Payment, String> residentLastNameColumn;
    @FXML
    private TableColumn<Payment, Double> paymentAmount;
    @FXML
    private TableColumn<Payment, Double> paymentBalance;
    @FXML
    private TableColumn<Payment, Date> paymentDate;
    @FXML
    private TableColumn<Payment, String> paymentType;
    @FXML
    private TableView<Payment> paymentTable;

    @FXML
    private JFXDrawer drawer;
    @FXML
    private TextField searchField;

    private Stage primaryStage;
    private Admin admin;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void initialize() {
        //Wait a momment then run
        Platform.runLater(() -> {
            //Initialization of side panel
            SidePanelInstance sidePanelInstance = new SidePanelInstance(drawer, primaryStage, admin);
            sidePanelInstance.sidePanelInit();

            //Setting TableView
            settingTableView();

            //Searchinf for Payment
            try {
                searchPayments();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void populatePayments(ObservableList<Payment> payData) {
        //Set items to the paymentTable
        paymentTable.setItems(payData);
    }

    @FXML
    public void searchForPayments() throws SQLException {
        cleanTable();
        if (!searchField.getText().equals(""))
            searchPayment(searchField.getText());
        else searchPayments();
    }

    @FXML
    private void searchPayment(String searched) throws SQLException {
        try {
            //Get Resident information
            ObservableList<Payment> payData = PaymentDAO.searchPayments(searched);
            //Populate Resident on TableView and Display on TextArea
            populatePayments(payData);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while getting resident information from DB.\n" + e.getMessage());
            throw e;
        }
    }

    private void searchPayments() throws SQLException {
        try {
            //Get all Residents information
            ObservableList<Payment> payData = PaymentDAO.searchPayments();
            //Populate Residents on TableView
            populatePayments(payData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting residents information from DB.\n" + e.getMessage());
            throw e;
        }
    }

    private void settingTableView() {

        //region TableColumn setCellValueFactory
        residentNameColumn.setCellValueFactory(cellData -> cellData.getValue().residentNameProperty());
        residentLastNameColumn.setCellValueFactory(cellData -> cellData.getValue().residentLastNameProperty());
        paymentAmount.setCellValueFactory(cellData -> cellData.getValue().paymentAmountProperty().asObject());
        paymentBalance.setCellValueFactory(cellData -> cellData.getValue().paymentBalanceProperty().asObject());
        paymentDate.setCellValueFactory(cellData -> cellData.getValue().paymentDateProperty());
        paymentType.setCellValueFactory(cellData -> cellData.getValue().paymentTypeProperty());
        residentId.setCellValueFactory(cellData -> cellData.getValue().idResidentProperty().asObject());
        paymentId.setCellValueFactory(cellData -> cellData.getValue().idPaymentProperty().asObject());
        adminId.setCellValueFactory(cellData -> cellData.getValue().idAdminProperty().asObject());

        //endregion

        //Table Reordering Disable
        paymentTable.getColumns().forEach(column -> column.setReorderable(false));
    }

    private void cleanTable() {
        paymentTable.getItems().clear();
    }


    public void addPayment(MouseEvent mouseEvent) {
        String viewPath = "/fxml/PaymentsPopUpAdd.fxml";
        String titlePopUp = "Add payment";
        createAndShowPopUp(viewPath, titlePopUp);
    }

    public void popUpInspectShow(MouseEvent mouseEvent) {
    }

    public void logout(MouseEvent mouseEvent) throws IOException {
        admin = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LoginWindow.fxml"));
        Parent root = fxmlLoader.load();
        LoginWindowController controller = fxmlLoader.getController();
        controller.setPrimaryStage(primaryStage);
        primaryStage.setTitle("Dormitory management system");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.setHeight(400);
        primaryStage.setWidth(600);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    private void createAndShowPopUp(String view, String title) {
        try {
            // Loader
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(view));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(title);
            stage.setScene(new Scene(root1));
            stage.getScene().getStylesheets().add(
                    getClass().getResource("/css/error.css").toExternalForm());
            PaymentsPopUpAddController controller = fxmlLoader.getController();
            controller.setAdmin(admin);
            controller.setStage(stage);
            stage.getScene().getWindow().addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, e -> {
                try {
                    searchPayments();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
