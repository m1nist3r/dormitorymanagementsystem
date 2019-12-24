package dormitorymanagementsystem.controllers;

import dormitorymanagementsystem.model.resident.Resident;
import dormitorymanagementsystem.model.resident.ResidentDAO;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Objects;


public class ResidentPopUpController {

    @FXML
    private Button closePopUpDetailsButton;
    private Stage stage;
    @FXML
    private ListView<String> popUpListViewKey;
    @FXML
    private ListView<String> popUpListViewField;
    @FXML
    private int residentId;
    private int residentTypeId;

    @FXML
    private void initialize() {

        Platform.runLater(() -> {
            bindScrollBarValues(Objects.requireNonNull(configPopUpListViewKey()), Objects.requireNonNull(configPopUpListViewField()));
            populateListViewFields();
            populateListViewKey();
        });
    }

    private void populateListViewKey() {
        try {
            ObservableList<Resident> resData = ResidentDAO.searchResidentById(residentId, residentTypeId);
            resData.forEach(resident -> {
                popUpListViewKey.getItems().add(resident.getResidentId());
                popUpListViewKey.getItems().add(resident.getResidentTypeId());
                popUpListViewKey.getItems().add(resident.getResidentRoomId());
                popUpListViewKey.getItems().add(resident.getFirstName());
                popUpListViewKey.getItems().add(resident.getLastName());
                popUpListViewKey.getItems().add(resident.getPesel());
                popUpListViewKey.getItems().add(resident.getGender());
                popUpListViewKey.getItems().add(resident.getDobDate());
                popUpListViewKey.getItems().add(resident.getMotherName());
                popUpListViewKey.getItems().add(resident.getFatherName());
                popUpListViewKey.getItems().add(resident.getEmail());
                popUpListViewKey.getItems().add(resident.getCountry());
                popUpListViewKey.getItems().add(resident.getAddress());
                popUpListViewKey.getItems().add(resident.getPhoneNumber());
                popUpListViewKey.getItems().add(resident.getAccommodationDate());
                popUpListViewKey.getItems().add(resident.getIsBlocked());
                switch (residentTypeId) {
                    case 1: {
                        popUpListViewKey.getItems().add(resident.getStudentNumber());
                        popUpListViewKey.getItems().add(resident.getDepartment());
                        popUpListViewKey.getItems().add(resident.getYearOfStudy());
                        popUpListViewKey.getItems().add(resident.getAcademicYear());
                        popUpListViewKey.getItems().add(resident.getStudentPaymentAccount());
                        break;
                    }
                    case 2: {
                        break;
                    }
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void populateListViewFields() {
        popUpListViewField.getItems().add("Id mieszkanca: ");
        popUpListViewField.getItems().add("Typ mieszkanca: ");
        popUpListViewField.getItems().add("Numer pokoju: ");
        popUpListViewField.getItems().add("Imie: ");
        popUpListViewField.getItems().add("Nazwisko: ");
        popUpListViewField.getItems().add("Pesel: ");
        popUpListViewField.getItems().add("Plec: ");
        popUpListViewField.getItems().add("Data urodzenia: ");
        popUpListViewField.getItems().add("Imie matki: ");
        popUpListViewField.getItems().add("Imie ojca: ");
        popUpListViewField.getItems().add("Email: ");
        popUpListViewField.getItems().add("Kraj: ");
        popUpListViewField.getItems().add("Address: ");
        popUpListViewField.getItems().add("Numer telefonu: ");
        popUpListViewField.getItems().add("Data zakwaterowania: ");
        popUpListViewField.getItems().add("Data wykwaterowania: ");
        popUpListViewField.getItems().add("Zbanowany: ");
        switch (residentTypeId) {
            case 1: {
                popUpListViewField.getItems().add("Numer legitymacji: ");
                popUpListViewField.getItems().add("Wydzial: ");
                popUpListViewField.getItems().add("Rok akademicki: ");
                popUpListViewField.getItems().add("Rok studow: ");
                popUpListViewField.getItems().add("Konto studenckie: ");
                break;
            }
            case 2: {
                break;
            }
        }
    }

    private ScrollBar configPopUpListViewField() {
        for (Node node : popUpListViewField.lookupAll(".scroll-bar")) {
            if (node instanceof ScrollBar) {
                return (ScrollBar) node;
            }
        }
        return null;
    }

    private ScrollBar configPopUpListViewKey() {
        for (Node node : popUpListViewKey.lookupAll(".scroll-bar")) {
            if (node instanceof ScrollBar) {
                return (ScrollBar) node;
            }
        }
        return null;
    }

    private void bindScrollBarValues(ScrollBar scrollBarInTable, ScrollBar scrollBarInPane) {
        // can't use bidi-binding because bar in scrollPane is normalized, bar in table is not
        // scrollBarInTable.valueProperty().bindBidirectional(scrollBarInPane.valueProperty());
        // scale manually
        scrollBarInTable.valueProperty().addListener((src, ov, nv) -> {
            double tableMax = scrollBarInTable.getMax();
            scrollBarInPane.setValue(nv.doubleValue() / tableMax);
        });

        scrollBarInPane.valueProperty().addListener((src, ov, nv) -> {
            double tableMax = scrollBarInTable.getMax();
            scrollBarInTable.setValue(nv.doubleValue() * tableMax);
        });
    }

    @FXML
    private void closePopUpDetails() {
        stage.close();
    }

    public Stage getStage() {
        return stage;
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }

    public int getResidentId() {
        return residentId;
    }

    void setResidentId(int residentId) {
        this.residentId = residentId;
    }

    public int getResidentTypeId() {
        return residentTypeId;
    }

    void setResidentTypeId(int residentTypeId) {
        this.residentTypeId = residentTypeId;
    }

}
