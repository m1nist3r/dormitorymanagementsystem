package dormitorymanagementsystem.controllers;

import com.jfoenix.controls.JFXDrawer;
import dormitorymanagementsystem.model.admin.Admin;
import dormitorymanagementsystem.model.resident.*;
import dormitorymanagementsystem.model.room.Room;
import dormitorymanagementsystem.model.room.RoomDAO;
import dormitorymanagementsystem.util.SidePanelInstance;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.util.StringConverter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddResidentController {

    //region Fields
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
    private TextField resStudentNumber;
    private TextField resDepartment;
    private TextField resYearOfStudy;
    private TextField resAcademicYear;
    private TextField resPayAcc;
    private TextField resOriginUniversity;
    private TextField resErasmusNumber;
    private TextField resIsStudent;
    private TextField resIsPartTimeStudent;
    private Label lblStudentNumber;
    private Label lblDepartment;
    private Label lblYearOfStudy;
    private Label lblAcademicYear;
    private Label lblPayAcc;
    private Label lblOriginUniversity;
    private Label lblErasmusNumber;
    private Label lblIsStudent;
    private Label lblIsPartTimeStudent;
    private ArrayList<Node> resTypeCollection;
    private boolean active = false;
    //endregion


    private Stage primaryStage;
    private Admin admin;

    void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    //Initiazlization of view before displaying
    @FXML
    private void initialize() {
        //Wait a momment then run
        Platform.runLater(() -> {
            //Initialization of side panel and open menu button
            SidePanelInstance sidePanelInstance = new SidePanelInstance(drawer, primaryStage, admin);
            sidePanelInstance.sidePanelInit();
            resType.setOnAction(this::changeResidentType);
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

    @NotNull
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

        if (resTypeCollection != null) {
            addGrid.getChildren().removeAll(resTypeCollection);
            resTypeCollection.clear();
        } else resTypeCollection = new ArrayList<>();

        int resTypeNumber = resType.getValue().getKey();

        switch (resTypeNumber) {
            case 1: {
                if (addGrid.getRowCount() != 10) {
                    addGrid.getRowConstraints().add(new RowConstraints());
                }
                resStudentNumber = new TextField();
                resDepartment = new TextField();
                resYearOfStudy = new TextField();
                resAcademicYear = new TextField();
                resPayAcc = new TextField();
                lblStudentNumber = new Label("Numer studenta:");
                lblDepartment = new Label("Wydział:");
                lblYearOfStudy = new Label("Rok studiów:");
                lblAcademicYear = new Label("Rok akademicki:");
                lblPayAcc = new Label("Numer konta studenta:");

                resTypeCollection.add(lblPayAcc);
                resTypeCollection.add(resPayAcc);
                resTypeCollection.add(lblAcademicYear);
                resTypeCollection.add(resAcademicYear);
                resTypeCollection.add(lblYearOfStudy);
                resTypeCollection.add(resYearOfStudy);
                resTypeCollection.add(lblDepartment);
                resTypeCollection.add(resDepartment);
                resTypeCollection.add(lblStudentNumber);
                resTypeCollection.add(resStudentNumber);

                settingGridPane();
                int i = 1;
                int j = 1;
                while (i <= resTypeCollection.size()) {
                    if (i + 3 <= resTypeCollection.size()) {
                        addGrid.add(resTypeCollection.get(i - 1), 0, addGrid.getRowCount() - j);
                        addGrid.add(resTypeCollection.get(i), 1, addGrid.getRowCount() - j);
                        addGrid.add(resTypeCollection.get(i + 1), 2, addGrid.getRowCount() - j - 1);
                        addGrid.add(resTypeCollection.get(i + 2), 3, addGrid.getRowCount() - j - 1);
                        i += 4;
                        j++;
                    } else if (i + 1 <= resTypeCollection.size()) {
                        addGrid.add(resTypeCollection.get(i - 1), 0, addGrid.getRowCount() - j);
                        addGrid.add(resTypeCollection.get(i), 1, addGrid.getRowCount() - j);
                        i += 2;
                        j++;
                    }
                }
                break;
            }
            case 2: {
                if (addGrid.getRowCount() == 10) {
                    addGrid.getRowConstraints().remove(addGrid.getRowCount() - 1);
                }
                resOriginUniversity = new TextField();
                resErasmusNumber = new TextField();
                lblOriginUniversity = new Label("Uniwersytet macierzysty:");
                lblErasmusNumber = new Label("Numer ERASMUS:");
                resTypeCollection.add(lblOriginUniversity);
                resTypeCollection.add(resOriginUniversity);
                resTypeCollection.add(lblErasmusNumber);
                resTypeCollection.add(resErasmusNumber);

                settingGridPane();
                addGrid.add(resTypeCollection.get(0), 0, addGrid.getRowCount() - 2);
                addGrid.add(resTypeCollection.get(1), 1, addGrid.getRowCount() - 2);
                addGrid.add(resTypeCollection.get(2), 2, addGrid.getRowCount() - 2);
                addGrid.add(resTypeCollection.get(3), 3, addGrid.getRowCount() - 2);

                break;
            }
            case 3: {
                if (addGrid.getRowCount() == 10) {
                    addGrid.getRowConstraints().remove(addGrid.getRowCount() - 1);
                }
                lblOriginUniversity = new Label("Uniwersytet macierzysty:");
                resOriginUniversity = new TextField();
                resTypeCollection.add(lblOriginUniversity);
                resTypeCollection.add(resOriginUniversity);

                settingGridPane();
                addGrid.add(resTypeCollection.get(0), 0, addGrid.getRowCount() - 2);
                addGrid.add(resTypeCollection.get(1), 1, addGrid.getRowCount() - 2);

                break;
            }
            case 4: {
                if (addGrid.getRowCount() == 10) {
                    addGrid.getRowConstraints().remove(addGrid.getRowCount() - 1);
                }
                lblIsStudent = new Label("Czy jest studentem:");
                lblIsPartTimeStudent = new Label("Studia zaoczne:");
                resIsStudent = new TextField();
                resIsPartTimeStudent = new TextField();
                resTypeCollection.add(lblIsStudent);
                resTypeCollection.add(resIsStudent);
                resTypeCollection.add(lblIsPartTimeStudent);
                resTypeCollection.add(resIsPartTimeStudent);

                settingGridPane();
                addGrid.add(resTypeCollection.get(0), 0, addGrid.getRowCount() - 2);
                addGrid.add(resTypeCollection.get(1), 1, addGrid.getRowCount() - 2);
                addGrid.add(resTypeCollection.get(2), 2, addGrid.getRowCount() - 2);
                addGrid.add(resTypeCollection.get(3), 3, addGrid.getRowCount() - 2);

                break;
            }

        }
    }

    @FXML
    public void addResident() throws SQLException {
        if (dataFormat() != null) {
            Alert alert = new Alert(Alert.AlertType.NONE, "Zakwaterować ?",
                    ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                ResidentDAO.insertResident(dataFormat());
                RoomDAO.updateRoom(resRoom.getSelectionModel().getSelectedItem().getRoomId(),
                        resRoom.getSelectionModel().getSelectedItem().getResidentAmount(),
                        resRoom.getSelectionModel().getSelectedItem().getRoomType());
            }
        }
    }

    private Resident dataFormat() {
        boolean isOk = true;
        Resident resident;
        switch (resType.getValue().getKey()) {
            case 1:
                resident = new ResidentStudent();
                if (resStudentNumber != null && !resStudentNumber.getText().equals("")) {
                    ((ResidentStudent) resident).setStudentNumber(resStudentNumber.getText());
                    removeRed(resStudentNumber);
                } else if (resStudentNumber != null) {
                    setRed(resStudentNumber);
                    isOk = false;
                }
                if (resDepartment != null && !resDepartment.getText().equals("")) {
                    ((ResidentStudent) resident).setDepartment(resDepartment.getText());
                    removeRed(resDepartment);
                } else if (resDepartment != null) {
                    setRed(resDepartment);
                    isOk = false;
                }
                if (resYearOfStudy != null && !resYearOfStudy.getText().equals("")) {
                    ((ResidentStudent) resident).setYearOfStudy(resYearOfStudy.getText());
                    removeRed(resYearOfStudy);
                } else if (resYearOfStudy != null) {
                    setRed(resYearOfStudy);
                    isOk = false;
                }
                if (resAcademicYear != null && !resAcademicYear.getText().equals("")) {
                    ((ResidentStudent) resident).setDepartment(resAcademicYear.getText());
                    removeRed(resAcademicYear);
                } else if (resAcademicYear != null) {
                    setRed(resAcademicYear);
                    isOk = false;
                }
                if (resPayAcc != null && !resPayAcc.getText().equals("")) {
                    ((ResidentStudent) resident).setDepartment(resPayAcc.getText());
                    removeRed(resPayAcc);
                } else if (resPayAcc != null) {
                    setRed(resPayAcc);
                    isOk = false;
                }
                break;
            case 2:
                resident = new ResidentErasmus();
                if (resOriginUniversity != null && !resOriginUniversity.getText().equals("")) {
                    ((ResidentErasmus) resident).setOriginUniversity(resOriginUniversity.getText());
                    removeRed(resOriginUniversity);
                } else if (resOriginUniversity != null) {
                    setRed(resOriginUniversity);
                    isOk = false;
                }
                if (resErasmusNumber != null && !resErasmusNumber.getText().equals("")) {
                    ((ResidentErasmus) resident).setOriginUniversity(resErasmusNumber.getText());
                    removeRed(resErasmusNumber);
                } else if (resErasmusNumber != null) {
                    setRed(resErasmusNumber);
                    isOk = false;
                }
                break;
            case 3:
                resident = new ResidentForeignStudent();
                if (resOriginUniversity != null && !resOriginUniversity.getText().equals("")) {
                    ((ResidentForeignStudent) resident).setOriginUniversity(resOriginUniversity.getText());
                    removeRed(resOriginUniversity);
                } else if (resOriginUniversity != null) {
                    setRed(resOriginUniversity);
                    isOk = false;
                }
                break;
            case 4:
                resident = new ResidentGuest();
                if (resIsStudent != null && !resIsStudent.getText().equals("")) {
                    ((ResidentGuest) resident).setIsStudent(resIsStudent.getText());
                    removeRed(resIsStudent);
                } else if (resIsStudent != null) {
                    setRed(resIsStudent);
                    isOk = false;
                }
                if (resIsPartTimeStudent != null && !resIsPartTimeStudent.getText().equals("")) {
                    ((ResidentGuest) resident).setIsStudent(resIsPartTimeStudent.getText());
                    removeRed(resIsPartTimeStudent);
                } else if (resIsPartTimeStudent != null) {
                    setRed(resIsPartTimeStudent);
                    isOk = false;
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + resType.getValue().getKey());
        }
        resident.setResidentTypeId(resType.getValue().getKey());
        if (resEvictionDate.getValue() != null) {
            resident.setEvictionDate(Date.valueOf(resEvictionDate.getValue()));
            removeRed(resEvictionDate);
        } else {
            setRed(resEvictionDate);
            isOk = false;
        }
        if ((!resName.getText().equals("")) && resName.getText().length() > 2) {
            resident.setFirstName(resName.getText());
            removeRed(resName);
        } else {
            setRed(resName);
            isOk = false;
        }
        if ((!resLastName.getText().equals("")) && resLastName.getText().length() > 2) {
            resident.setLastName(resLastName.getText());
            removeRed(resLastName);
        } else {
            setRed(resLastName);
            isOk = false;
        }
        if (resRoom.getValue() != null) {
            resident.setResidentRoomId(resRoom.getValue().getRoomId());
        } else {
            isOk = false;
        }
        if ((!resPesel.getText().equals("")) && !(resPesel.getText().isEmpty()) && resPesel.getText().length() == 11) {
            resident.setPesel(resPesel.getText());
            removeRed(resPesel);
        } else {
            setRed(resPesel);
            isOk = false;
        }
        if (resSex.getValue() != null && (!resSex.getValue().getKey().isEmpty())) {
            resident.setGender(resSex.getValue().getKey());
        } else {
            isOk = false;
        }
        if (resDob.getValue() != null) {
            resident.setDobDate(Date.valueOf(resDob.getValue()));
            removeRed(resDob);
        } else {
            setRed(resDob);
            isOk = false;
        }
        if ((!resMatherName.getText().equals("")) && resMatherName.getText().length() > 2) {
            resident.setMotherName(resMatherName.getText());
            removeRed(resMatherName);
        } else {
            setRed(resMatherName);
            isOk = false;
        }
        if ((!resFatherName.getText().equals("")) && resFatherName.getText().length() > 2) {
            resident.setFatherName(resFatherName.getText());
            removeRed(resFatherName);
        } else {
            setRed(resFatherName);
            isOk = false;
        }
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(resEmail.getText());
        if ((!resEmail.getText().equals("")) && matcher.matches()) {
            resident.setEmail(resEmail.getText());
            removeRed(resEmail);
        } else {
            setRed(resEmail);
            isOk = false;
        }
        if ((!resCountry.getText().equals("")) && resCountry.getText().length() > 2) {
            resident.setCountry(resCountry.getText());
            removeRed(resCountry);
        } else {
            setRed(resCountry);
            isOk = false;
        }
        if ((!resAddress.getText().equals("")) && resAddress.getText().length() > 2) {
            resident.setAddress(resAddress.getText());
            removeRed(resAddress);
        } else {
            setRed(resAddress);
            isOk = false;
        }
        if ((!resNrPhone.getText().equals("")) && resNrPhone.getText().length() >= 9) {
            resident.setPhoneNumber(resNrPhone.getText());
            removeRed(resNrPhone);
        } else {
            setRed(resNrPhone);
            isOk = false;
        }
        if (isOk)
            return resident;
        else return null;
    }

    private void setRed(Node tf) {
        ObservableList<String> styleClass = tf.getStyleClass();

        if (!styleClass.contains("error")) {
            styleClass.add("error");
        }
    }

    private void removeRed(Node tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        styleClass.removeAll(Collections.singleton("error"));
    }

    private void settingGridPane() {
        addGrid.getRowConstraints().forEach(rowConstraints -> {
            if (rowConstraints.getMaxHeight() != 0)
                rowConstraints.setPercentHeight(100.0 / addGrid.getRowConstraints().size());
        });
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

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
