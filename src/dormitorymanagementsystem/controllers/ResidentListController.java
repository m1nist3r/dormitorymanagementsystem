package dormitorymanagementsystem.controllers;

import dormitorymanagementsystem.model.resident.Resident;
import dormitorymanagementsystem.model.resident.ResidentDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.Date;

public class ResidentListController {

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
    private TableColumn<Resident, String> residentPassportNumberColumn;
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
    private TextArea resultArea;
    @FXML
    private TextField searchField;
    @FXML
    private ChoiceBox<String> filteringBox;

    //Search an resident
    @FXML
    private void searchResident(String searched) throws SQLException {
        try {
            //Get Resident information
            ObservableList<Resident> resData = ResidentDAO.searchResidents(searched);
            //Populate Resident on TableView and Display on TextArea
            populateResidents(resData);
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Error occurred while getting resident information from DB.\n" + e.getMessage());
            throw e;
        }
    }

    //Search all residents
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

    @FXML
    private void initialize() {
        /*
        The setCellValueFactory(...) that we set on the table columns are used to determine
        which field inside the Resident objects should be used for the particular column.
        The arrow -> indicates that we're using a Java 8 feature called Lambdas.
        (Another option would be to use a PropertyValueFactory, but this is not type-safe

        We're only using StringProperty values for our table columns in this example.
        When you want to use IntegerProperty or DoubleProperty, the setCellValueFactory(...)
        must have an additional asObject():
        */
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
        residentIsBlockedColumn.setCellValueFactory(cellData -> cellData.getValue().isBlockedProperty());

        try {
            searchResidents();
        } catch (SQLException e) {
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

    //Populate Residents for TableView
    @FXML
    private void populateResidents(ObservableList<Resident> resData) {
        //Set items to the residentTable
        residentTable.setItems(resData);
    }

    private void cleanTable() {
        residentTable.getItems().clear();
    }

}
