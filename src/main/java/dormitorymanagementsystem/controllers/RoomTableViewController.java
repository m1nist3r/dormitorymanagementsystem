package dormitorymanagementsystem.controllers;

import com.jfoenix.controls.JFXDrawer;
import dormitorymanagementsystem.model.admin.Admin;
import dormitorymanagementsystem.model.room.Room;
import dormitorymanagementsystem.model.room.RoomDAO;
import dormitorymanagementsystem.util.SidePanelInstance;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;

public class RoomTableViewController {

    @FXML
    public JFXDrawer drawer;
    @FXML
    public TextField searchField;
    @FXML
    public TableView<Room> roomTable;
    public TableColumn<Room, Integer> tableColumnFloor;
    public TableColumn<Room, Integer> tableColumnRoomNumber;
    public TableColumn<Room, String> tableColumnRoomRemark;
    public TableColumn<Room, Integer> tableColumnRoomType;
    public TableColumn<Room, Integer> tableColumnDormitoryNumber;
    public TableColumn<Room, String> tableColumnRoomStatus;
    public TableColumn<Room, Integer> tableColumnResidentNumber;
    private Stage primaryStage;
    private Admin admin;

    private static void showAlert(Window owner, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Form Error!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @FXML
    private void initialize() {
        //Wait a momment then run
        Platform.runLater(() -> {
            //Initialization of side panel and open menu button
            SidePanelInstance sidePanelInstance = new SidePanelInstance(drawer, primaryStage, admin);
            sidePanelInstance.sidePanelInit();
            //sidePanelInstance.setUpHamburger();

            //region TableColumn setCellValueFactory
            tableColumnFloor.setCellValueFactory(cellData -> cellData.getValue().floorProperty().asObject());
            tableColumnRoomNumber.setCellValueFactory(cellData -> cellData.getValue().roomIdProperty().asObject());
            tableColumnRoomRemark.setCellValueFactory(cellData -> cellData.getValue().remarksProperty());
            tableColumnRoomType.setCellValueFactory(cellData -> cellData.getValue().roomTypeProperty().asObject());
            tableColumnDormitoryNumber.setCellValueFactory(cellData -> cellData.getValue().dormitoryNumberProperty().asObject());
            tableColumnRoomStatus.setCellValueFactory(cellData -> cellData.getValue().roomStatusProperty());
            tableColumnResidentNumber.setCellValueFactory(cellData -> cellData.getValue().residentAmountProperty().asObject());
            //endregion

            //Table Reordering Disable
            roomTable.getColumns().forEach(column -> column.setReorderable(false));

            //Searching for Rooms
            try {
                searchRooms();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    //Search all residents
    private void searchRooms() throws SQLException {
        try {
            //Get all Residents information
            ObservableList<Room> resData = RoomDAO.searchRooms();
            //Populate Residents on TableView
            populateRooms(resData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting residents information from DB.\n" + e.getMessage());
            throw e;
        }
    }

    //Search an resident
    @FXML
    private void searchRoom(int searched) throws SQLException {
        try {
            //Get Resident information
            ObservableList<Room> resData = RoomDAO.searchRoomById(searched);
            //Populate Resident on TableView and Display on TextArea
            populateRooms(resData);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while getting resident information from DB.\n" + e.getMessage());
            throw e;
        }
    }

    //Searching for Resident by Name/Surname
    @FXML
    public void searchForRoom() throws SQLException {
        cleanTable();
        if (!searchField.getText().equals(""))
            try {
                searchRoom(Integer.parseInt(searchField.getText()));
            } catch (NumberFormatException e) {
                showAlert(drawer.getScene().getWindow(), "Enter a number!");
            }

        else searchRooms();
    }

    //Populate Residents for TableView
    @FXML
    private void populateRooms(ObservableList<Room> resData) {
        //Set items to the residentTable
        roomTable.setItems(resData);
    }

    //Clean Resident table
    private void cleanTable() {
        roomTable.getItems().clear();
    }

    public void searchForResident() {
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void logout() throws IOException {
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

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
