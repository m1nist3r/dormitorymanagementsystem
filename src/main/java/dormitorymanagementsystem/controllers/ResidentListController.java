package dormitorymanagementsystem.controllers;

import com.jfoenix.controls.JFXDrawer;
import dormitorymanagementsystem.model.admin.Admin;
import dormitorymanagementsystem.model.resident.Resident;
import dormitorymanagementsystem.model.resident.ResidentDAO;
import dormitorymanagementsystem.util.SidePanelInstance;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class ResidentListController {
    @FXML
    private Button addAdmin;
    //region TableView and TableColumn components
    @FXML
    private TableView<Resident> residentTable;
    @FXML
    private TableColumn<Resident, Integer> residentIdColumn;
    @FXML
    private TableColumn<Resident, Integer> residentIdTypeColumn;
    @FXML
    private TableColumn<Resident, Integer> residentIdRoomColumn;
    @FXML
    private TableColumn<Resident, String> residentNameColumn;
    @FXML
    private TableColumn<Resident, String> residentLastNameColumn;
    @FXML
    private TableColumn<Resident, String> residentPeselColumn;
    @FXML
    private TableColumn<Resident, String> residentGenderColumn;
    @FXML
    private TableColumn<Resident, Date> residentDobColumn;
    @FXML
    private TableColumn<Resident, String> residentMotherNameColumn;
    @FXML
    private TableColumn<Resident, String> residentFatherNameColumn;
    @FXML
    private TableColumn<Resident, String> residentEmailColumn;
    @FXML
    private TableColumn<Resident, String> residentCountryColumn;
    @FXML
    private TableColumn<Resident, String> residentAddressColumn;
    @FXML
    private TableColumn<Resident, String> residentPhoneNumberColumn;
    @FXML
    private TableColumn<Resident, Date> residentAccommodationColumn;
    @FXML
    private TableColumn<Resident, Date> residentEvictionDateColumn;
    @FXML
    private TableColumn<Resident, Boolean> residentIsBlockedColumn;
    //endregion

    @FXML
    private TextField searchField;
    @FXML
    private JFXDrawer drawer;

    private Stage primaryStage;
    private Admin admin;

    @FXML
    private void initialize() {
        //Wait a momment then run
        Platform.runLater(() -> {
            //Initialization of side panel
            SidePanelInstance sidePanelInstance = new SidePanelInstance(drawer, primaryStage, admin);
            sidePanelInstance.sidePanelInit();
            if (admin.getIdAdminType() == 2) {
                addAdmin.setVisible(true);
            }
            //Setting TableView
            settingTableView();

            //Searchinf for Resident
            try {
                searchResidents();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void populateResidents(ObservableList<Resident> resData) {
        //Set items to the residentTable
        residentTable.setItems(resData);
    }

    @FXML
    public void inspectResidentPopUp() {
        Resident tablePopUpSelectionModel = residentTable
                .getSelectionModel()
                .getSelectedItem();
        int residentId = Integer.parseInt(tablePopUpSelectionModel.getResidentId());
        int residentTypeId = Integer.parseInt(tablePopUpSelectionModel.getResidentTypeId());
        String viewPath = "/fxml/ResidentPopUpDetails.fxml";
        String titlePopUp = "Resident details: " +
                residentTable
                        .getSelectionModel()
                        .getSelectedItem()
                        .getFirstName() + " " +
                residentTable
                        .getSelectionModel()
                        .getSelectedItem()
                        .getLastName();
        createAndShowPopUp(viewPath, titlePopUp, residentId, residentTypeId);
    }

    private void createAndShowPopUp(String view, String title, int residentId, int residentTypeId) {
        try {
            // Loader
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(view));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(title);
            stage.setScene(new Scene(root1));
            ResidentPopUpController controller = fxmlLoader.getController();
            controller.setStage(stage);
            controller.setResidentId(residentId);
            controller.setResidentTypeId(residentTypeId);

            stage.getScene().getWindow().addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, e -> {
                try {
                    searchResidents();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void searchForResident() throws SQLException {
        cleanTable();
        if (!searchField.getText().equals(""))
            searchResident(searchField.getText());
        else searchResidents();
    }

    @FXML
    private void searchResident(String searched) throws SQLException {
        try {
            //Get Resident information
            ObservableList<Resident> resData = ResidentDAO.searchResidents(searched);
            //Populate Resident on TableView and Display on TextArea
            populateResidents(resData);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while getting resident information from DB.\n" + e.getMessage());
            throw e;
        }
    }

    private void searchResidents() throws SQLException {
        try {
            //Get all Residents information
            ObservableList<Resident> resData = ResidentDAO.searchResidents();
            //Populate Residents on TableView
            populateResidents(resData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting residents information from DB.\n" + e.getMessage());
            throw e;
        }
    }

    private void settingTableView() {
        //region TableColumn setCellValueFactory
        residentIdColumn.setCellValueFactory(cellData -> cellData.getValue().residentIdProperty().asObject());
        residentIdTypeColumn.setCellValueFactory(cellData -> cellData.getValue().residentTypeIdProperty().asObject());
        residentIdRoomColumn.setCellValueFactory(cellData -> cellData.getValue().residentRoomIdProperty().asObject());
        residentNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        residentLastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        residentPeselColumn.setCellValueFactory(cellData -> cellData.getValue().peselProperty());
        residentGenderColumn.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        residentDobColumn.setCellValueFactory(cellData -> cellData.getValue().dobDateProperty());
        residentMotherNameColumn.setCellValueFactory(cellData -> cellData.getValue().motherNameProperty());
        residentFatherNameColumn.setCellValueFactory(cellData -> cellData.getValue().fatherNameProperty());
        residentEmailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        residentCountryColumn.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
        residentAddressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        residentPhoneNumberColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        residentAccommodationColumn.setCellValueFactory(cellData -> cellData.getValue().accommodationDateProperty());
        residentEvictionDateColumn.setCellValueFactory(cellData -> cellData.getValue().evictionDateProperty());
        //endregion

        //Table Reordering Disable
        residentIsBlockedColumn.setCellValueFactory(cellData -> cellData.getValue().isBlockedProperty());
        residentTable.getColumns().forEach(column -> column.setReorderable(false));
    }

    private void cleanTable() {
        residentTable.getItems().clear();
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
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


    public void addAdmin() {
        try {
            // Loader
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/addAdmin.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Admin");
            stage.setScene(new Scene(root1));
            stage.getScene().getStylesheets().add(
                    getClass().getResource("/css/error.css").toExternalForm());
            AddAdminController controller = fxmlLoader.getController();
            controller.setStage(stage);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
