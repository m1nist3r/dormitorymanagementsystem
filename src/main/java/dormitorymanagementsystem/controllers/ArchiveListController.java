package dormitorymanagementsystem.controllers;

import com.jfoenix.controls.JFXDrawer;
import dormitorymanagementsystem.model.admin.Admin;
import dormitorymanagementsystem.model.archive.Archive;
import dormitorymanagementsystem.model.archive.ArchiveDAO;
import dormitorymanagementsystem.util.SidePanelInstance;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ArchiveListController {

    @FXML
    private TableView<Archive> archiveTable;
    @FXML
    private TableColumn<Archive, Integer> archiveIdColumn;
    @FXML
    private TableColumn<Archive, Date> archiveYearColumn;
    @FXML
    private TableColumn<Archive, String> archivePathColumn;
    @FXML
    private TableColumn<Archive, String> archiveNameColumn;
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

            //Setting TableView
            settingTableView();

            //Searchinf for Resident
            try {
                searchArchives();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }


    private void settingTableView() {
        archiveIdColumn.setCellValueFactory(cellData -> cellData.getValue().idBackupProperty().asObject());
        archiveYearColumn.setCellValueFactory(cellData -> cellData.getValue().yearBackUpProperty());
        archivePathColumn.setCellValueFactory(cellData -> cellData.getValue().pathToArchiveProperty());
        archiveNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameArchiveProperty());

        archiveTable.getColumns().forEach(column -> column.setReorderable(false));
    }

    private void searchArchives() throws SQLException {
        try {
            //Get all Residents information
            ObservableList<Archive> resData = ArchiveDAO.searchArchives();
            //Populate Residents on TableView
            populateArchives(resData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting residents information from DB.\n" + e.getMessage());
            throw e;
        }
    }

    @FXML
    private void populateArchives(ObservableList<Archive> resData) {
        //Set items to the residentTable
        archiveTable.setItems(resData);
    }

    public void addArchive() {
        Alert alert = new Alert(Alert.AlertType.NONE, "Stworzyć backup ?",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String dateNow = dateFormat.format(date);
            //String[] command = {"mysqldump.exe", "-u root dormitory --no-create-info --skip-triggers --no-create-db --skip-comments --compact > dump_" + dateFormat.format(date) + ".sql"};
            String command = "mysqldump -u root dormitory --no-create-info --skip-triggers --no-create-db --skip-comments --compact -r C:\\Users\\HORROR\\IdeaProjects\\dormitorymanagementsystem\\src\\main\\resources\\archive\\dump_" + dateNow + ".sql";
            try {
                Process process = Runtime.getRuntime().exec(command);
                int processComplete = process.waitFor();
                if (processComplete == 0) {
                    System.out.println("Backup taken successfully");
                    Archive archive = new Archive();
                    archive.setNameArchive("dump_" + dateNow);
                    archive.setPathToArchive("src/main/resources/archive/dump_" + dateNow + ".sql");
                    archive.setYearBackUp(java.sql.Date.valueOf(dateNow));
                    ArchiveDAO.insertArchive(archive);
                    searchArchives();
                } else {
                    System.out.println("Could not take mysql backup");
                }

            } catch (IOException | InterruptedException | SQLException exp) {
                exp.printStackTrace();
            }
        }
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

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
