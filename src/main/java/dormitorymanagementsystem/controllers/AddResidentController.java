package dormitorymanagementsystem.controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import dormitorymanagementsystem.model.resident.Resident;
import dormitorymanagementsystem.model.resident.ResidentDAO;
import dormitorymanagementsystem.model.room.Room;
import dormitorymanagementsystem.model.room.RoomDAO;
import dormitorymanagementsystem.util.SidePanelInstance;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.util.StringConverter;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddResidentController {

    @FXML
    private Button addResident;
    @FXML
    private GridPane addGrid;
    @FXML
    private DatePicker resDob;
    @FXML
    private ComboBox<Pair<String, String>> resSex;
    @FXML
    private DatePicker resEvictionDate;
    @FXML
    private TextField resName;
    @FXML
    private TextField resLastName;
    @FXML
    private TextField resPesel;
    @FXML
    private TextField resCountry;
    @FXML
    private TextField resAddress;
    @FXML
    private TextField resNrPhone;
    @FXML
    private TextField resMatherName;
    @FXML
    private TextField resFatherName;
    @FXML
    private TextField resEmail;
    @FXML
    private ComboBox<Pair<Integer, String>> resType;
    @FXML
    private ComboBox<Room> resRoom;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger menuHamburger;

    private Stage primaryStage;

    void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    //Initiazlization of view before displaying
    @FXML
    private void initialize() {
        resType.setOnAction(this::changeResidentType);
        //Wait a momment then run
        Platform.runLater(() -> {
            //Initialization of side panel and open menu button
            SidePanelInstance sidePanelInstance = new SidePanelInstance(drawer, menuHamburger, primaryStage);
            sidePanelInstance.sidePanelInit();
            settingDatePicker();
            try {
                settingComboBox();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void settingDatePicker() {
        changeDatePickerFormat(resDob);
        changeDatePickerFormat(resEvictionDate);
    }

    private void settingComboBox() throws SQLException {
        resSex.setItems(FXCollections.observableArrayList(
                new Pair<>("M", "Mężczyzna"),
                new Pair<>("K", "Kobieta")
        ));
        resSex.setPromptText("Wybierz płeć");

        resRoom.setConverter(new StringConverter<>() {
            @Override
            public String toString(Room room) {
                return room.getRoomId() + " - " + room.getRoomStatus();
            }

            @Override
            public Room fromString(String s) {
                return null;
            }
        });
        resRoom.setItems(FXCollections.observableArrayList(getFreeRooms()));
        resRoom.getSelectionModel().selectFirst();

        resType.setConverter(new StringConverter<>() {
            @Override
            public String toString(Pair<Integer, String> integerStringPair) {
                return integerStringPair.getKey() + " - " + integerStringPair.getValue();
            }

            @Override
            public Pair<Integer, String> fromString(String s) {
                return null;
            }
        });
        resType.setItems(getResidentType());
        resType.getSelectionModel().selectFirst();
    }

    @NotNull
    private ObservableList<Room> getFreeRooms() throws SQLException {
        return RoomDAO.searchFreeRoom();
    }

    private ObservableList<Pair<Integer, String>> getResidentType() throws SQLException {
        return ResidentDAO.getResidentTypes();
    }

    private void changeDatePickerFormat(@NotNull DatePicker datePicker) {
        datePicker.setConverter(new StringConverter<>() {
            private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public String toString(LocalDate localDate) {
                if (localDate == null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String s) {
                if (s == null || s.trim().isEmpty()) {
                    return null;
                }
                return LocalDate.parse(s, dateTimeFormatter);
            }
        });
    }

    public void changeResidentType(ActionEvent actionEvent) {
    }

    public void addResident(MouseEvent mouseEvent) throws SQLException {
        if (dataFormat() != null)
            ResidentDAO.insertResident(dataFormat());
    }

    private Resident dataFormat() {
        Resident resident = new Resident();
        resident.setResidentTypeId(resType.getValue().getKey());
        if (resEvictionDate.getValue() != null) {
            resident.setEvictionDate(Date.valueOf(resEvictionDate.getValue()));
        } else return null;
        if ((!resName.getText().equals("")) && resName.getText().length() > 2) {
            resident.setFirstName(resName.getText());
        } else return null;
        if ((!resLastName.getText().equals("")) && resLastName.getText().length() > 2) {
            resident.setLastName(resLastName.getText());
        } else return null;
        if (resRoom.getValue() != null) {
            resident.setResidentRoomId(resRoom.getValue().getRoomId());
        } else return null;
        if ((!resPesel.getText().equals("")) && !(resPesel.getText().isEmpty()) && resPesel.getText().length() == 11) {
            resident.setPesel(resPesel.getText());
        } else return null;
        if (resSex.getValue() != null && (!resSex.getValue().getKey().isEmpty())) {
            resident.setGender(resSex.getValue().getKey());
        } else return null;
        if (!resDob.getValue().toString().isEmpty()) {
            resident.setDobDate(Date.valueOf(resDob.getValue()));
        } else return null;
        if ((!resMatherName.getText().equals("")) && resMatherName.getText().length() > 2) {
            resident.setMotherName(resMatherName.getText());
        } else return null;
        if ((!resFatherName.getText().equals("")) && resFatherName.getText().length() > 2) {
            resident.setFatherName(resFatherName.getText());
        } else return null;
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(resEmail.getText());
        if ((!resEmail.getText().equals("")) && matcher.matches()) {
            resident.setEmail(resEmail.getText());
        } else return null;
        if ((!resCountry.getText().equals("")) && resCountry.getText().length() > 2) {
            resident.setCountry(resCountry.getText());
        } else return null;
        if ((!resAddress.getText().equals("")) && resAddress.getText().length() > 2) {
            resident.setAddress(resAddress.getText());
        } else return null;
        if ((!resNrPhone.getText().equals("")) && resNrPhone.getText().length() >= 9) {
            resident.setPhoneNumber(resNrPhone.getText());
        } else return null;

        return resident;
    }
}
