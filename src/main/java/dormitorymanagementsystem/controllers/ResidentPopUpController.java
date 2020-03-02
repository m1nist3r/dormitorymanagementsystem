package dormitorymanagementsystem.controllers;

import dormitorymanagementsystem.model.resident.Resident;
import dormitorymanagementsystem.model.resident.ResidentDAO;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.util.Objects;

public class ResidentPopUpController {

    @FXML
    public CheckBox resEditCheckBox;
    @FXML
    private ListView<String> popUpListViewKey;
    @FXML
    private ListView<String> popUpListViewField;
    private Stage stage;
    private int residentId;
    private int residentTypeId;
    private ObservableList<String> residentObservableList;

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            populateListViewFields();
            initListViewKey();
            bindScrollBarValues(Objects.requireNonNull(configPopUpListViewKey()), Objects.requireNonNull(configPopUpListViewField()));
        });
    }

    private void initListViewKey() {
        try {
            ObservableList<Resident> resData = ResidentDAO.searchResidentById(residentId, residentTypeId);
            residentObservableList = resData.get(0).listOfResidentStudent();
            popUpListViewKey.setItems(residentObservableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        popUpListViewKey.setEditable(false);
        popUpListViewKey.setCellFactory(TextFieldListCell.forListView());

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
        popUpListViewField.getItems().add("Stan konta: ");
        switch (residentTypeId) {
            case 1: {
                popUpListViewField.getItems().add("Numer legitymacji: ");
                popUpListViewField.getItems().add("Wydzial: ");
                popUpListViewField.getItems().add("Rok akademicki: ");
                popUpListViewField.getItems().add("Rok studow: ");
                popUpListViewField.getItems().add("Konto studenckie: ");
                break;
            }  // Student of Motherland University
            case 2: {
                popUpListViewField.getItems().add("Uczelnia macierzysta: ");
                popUpListViewField.getItems().add("Nr ERASMUS: ");
                break;
            }  // Student ERASMUS
            case 3: {
                popUpListViewField.getItems().add("Uczelnia macierzysta: ");
                break;
            }  // Other Student
            case 4: {
                popUpListViewField.getItems().add("Student: ");
                popUpListViewField.getItems().add("Tryb studiów: ");
                break;
            }  // Guests
        }
        popUpListViewField.setFocusTraversable(false);
    }

    public void resEditCancel(@NotNull ListView.EditEvent<String> stringEditEvent) {
    }

    public void resEditCommit(@NotNull ListView.EditEvent<String> stringEditEvent) throws SQLException {

        Alert alert = new Alert(Alert.AlertType.NONE, "Edytować ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            residentObservableList.set(stringEditEvent.getIndex(), stringEditEvent.getNewValue());
            int columnIndex = stringEditEvent.getIndex();
            ResidentDAO.updateResident(residentId, residentTypeId, columnIndex, stringEditEvent.getNewValue());
        }
    }

    public void resEditStart(ListView.EditEvent<String> stringEditEvent) {
    }

    public void editIsSelected() {
        if (resEditCheckBox.isSelected()) {
            popUpListViewKey.setEditable(true);
        } else if (!resEditCheckBox.isSelected()) {
            popUpListViewKey.setEditable(false);
        }
    }

    @Nullable
    private ScrollBar configPopUpListViewField() {
        for (Node node : popUpListViewField.lookupAll(".scroll-bar")) {
            if (node instanceof ScrollBar) {
                return (ScrollBar) node;
            }
        }
        return null;
    }

    @Nullable
    private ScrollBar configPopUpListViewKey() {
        for (Node node : popUpListViewKey.lookupAll(".scroll-bar")) {
            if (node instanceof ScrollBar) {
                return (ScrollBar) node;
            }
        }
        return null;
    }

    private void bindScrollBarValues(@NotNull ScrollBar scrollBarInTable, @NotNull ScrollBar scrollBarInPane) {
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
        stage.getScene().getWindow().fireEvent(new WindowEvent(stage.getScene().getWindow(), WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }

    void setResidentId(int residentId) {
        this.residentId = residentId;
    }

    void setResidentTypeId(int residentTypeId) {
        this.residentTypeId = residentTypeId;
    }

    private boolean validate(String value, int indexColumn) {
        return true;
    }

}
