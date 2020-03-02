package dormitorymanagementsystem.controllers;

import dormitorymanagementsystem.model.admin.Admin;
import dormitorymanagementsystem.model.payment.Payment;
import dormitorymanagementsystem.model.payment.PaymentDAO;
import dormitorymanagementsystem.model.resident.Resident;
import dormitorymanagementsystem.model.resident.ResidentDAO;
import dormitorymanagementsystem.util.AutoCompleteComboBoxListener;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;

import java.sql.SQLException;
import java.util.Collections;

public class PaymentsPopUpAddController {
    @FXML
    private ComboBox<Resident> paymentResidentComboBox;
    @FXML
    private ComboBox<Double> paymentAmountComboBox;
    @FXML
    private TextField paymentTypeTextField;
    private Admin admin;
    private Stage stage;

    @FXML
    private void initialize() {
        //Wait a momment then run
        Platform.runLater(() -> {
            //Initialization of side panel and open menu button
            try {
                settingComboBox();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void settingComboBox() throws SQLException {
        paymentResidentComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Resident resident) {
                return resident.toString();
            }

            @Override
            public Resident fromString(String s) {
                if (s.isEmpty()) return null;
                String[] substring = s.split(" ");
                Resident resident = null;
                try {
                    resident = ResidentDAO.searchResidentById(Integer.parseInt(substring[3]));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return resident;
            }
        });
        paymentResidentComboBox.setItems(FXCollections.observableArrayList(ResidentDAO.searchResidents()));
        new AutoCompleteComboBoxListener<>(paymentResidentComboBox);
        paymentAmountComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Double aDouble) {
                return String.valueOf(aDouble);
            }

            @Override
            public Double fromString(String s) {
                if (s.isEmpty()) return null;
                return Double.parseDouble(s);
            }
        });
    }

    private ObservableList<Double> getPaymentAmount(String residentTypeId) {
        ObservableList<Double> paymentAmountList = FXCollections.observableArrayList();
        paymentAmountList.add(345.00);
        return paymentAmountList;
    }

    public void paymentConfirm(MouseEvent mouseEvent) {
        if (dataFormat() != null) {
            Alert alert = new Alert(Alert.AlertType.NONE, "Wprowadzić ?",
                    ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                if (PaymentDAO.insertPayment(dataFormat())) {
                    alert = new Alert(Alert.AlertType.INFORMATION, "Opłata została dodana!", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        stage.getScene().getWindow().fireEvent(new WindowEvent(stage.getScene().getWindow(), WindowEvent.WINDOW_CLOSE_REQUEST));
                    }
                }
            }
        }
    }

    private Payment dataFormat() {
        boolean isOk = true;
        Payment payment = new Payment();
        if (paymentResidentComboBox.getValue() != null && !paymentResidentComboBox.getValue().getResidentId().isEmpty()) {
            payment.setIdResident(Integer.parseInt(paymentResidentComboBox.getSelectionModel().getSelectedItem().getResidentId()));
            removeRed(paymentAmountComboBox);
        } else {
            setRed(paymentAmountComboBox);
            isOk = false;
        }
        if (paymentAmountComboBox.getValue() != null && paymentAmountComboBox.getValue() != 0.00) {
            payment.setPaymentAmount(paymentAmountComboBox.getSelectionModel().getSelectedItem());
            removeRed(paymentAmountComboBox);
        } else {
            setRed(paymentAmountComboBox);
            isOk = false;
        }
        if ((!paymentTypeTextField.getText().equals(""))) {
            payment.setPaymentType(paymentTypeTextField.getText());
            removeRed(paymentTypeTextField);
        } else {
            setRed(paymentTypeTextField);
            isOk = false;
        }
        if (isOk) {
            payment.setIdAdmin(getAdmin().getIdAdmin());
            payment.setPaymentBalance(Double.parseDouble(
                    paymentResidentComboBox.getSelectionModel().getSelectedItem().getPayment_fee())
                    + payment.getPaymentAmount());
            return payment;
        } else return null;
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

    public void paymentClose(MouseEvent mouseEvent) {
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void paymentResidentChange(ActionEvent actionEvent) {
/*        if (paymentAmountComboBox.getItems().size() != 0) paymentAmountComboBox.getItems().clear();
        paymentAmountComboBox.setItems(getPaymentAmount(
                "1"));*/
    }
}
