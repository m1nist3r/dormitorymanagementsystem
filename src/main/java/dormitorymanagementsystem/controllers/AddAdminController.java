package dormitorymanagementsystem.controllers;

import dormitorymanagementsystem.model.admin.Admin;
import dormitorymanagementsystem.model.admin.AdminDAO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddAdminController {

    public TextField adminName;
    public TextField adminLastName;
    public TextField adminPesel;
    public TextField adminPass;
    public TextField adminEmail;
    public DatePicker adminDOB;
    public TextField adminPhone;
    public ComboBox<Admin> adminTypeComboBox;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize() {
        //Wait a momment then run
        Platform.runLater(() -> {
            //Initialization of side panel and open menu button
            changeDatePickerFormat(adminDOB);

            try {
                settingComboBox();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }


    private void settingComboBox() throws SQLException {
        adminTypeComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Admin admin) {
                return admin.getIdAdminType() + " - " + admin.getIdTypeName();
            }

            @Override
            public Admin fromString(String s) {
                return null;
            }
        });
        adminTypeComboBox.setItems(FXCollections.observableArrayList(getAdminTypes()));
        adminTypeComboBox.getSelectionModel().selectFirst();
    }

    @NotNull
    private ObservableList<Admin> getAdminTypes() throws SQLException {
        return AdminDAO.searchAdminTypes();
    }

    private void changeDatePickerFormat(@NotNull DatePicker datePicker) {
        adminDOB.setConverter(new StringConverter<>() {
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


    public void addAdminClose() {
        stage.close();
    }

    private Admin dataFormat() {
        boolean isOk = true;
        Admin admin = new Admin();
        if (adminDOB.getValue() != null) {
            admin.setAdminDOB(Date.valueOf(adminDOB.getValue()));
            removeRed(adminDOB);
        } else {
            setRed(adminDOB);
            isOk = false;
        }
        if ((!adminName.getText().equals("")) && adminName.getText().length() > 2) {
            admin.setAdminName(adminName.getText());
            removeRed(adminName);
        } else {
            setRed(adminName);
            isOk = false;
        }
        if ((!adminLastName.getText().equals("")) && adminLastName.getText().length() > 2) {
            admin.setAdminLastName(adminLastName.getText());
            removeRed(adminLastName);
        } else {
            setRed(adminLastName);
            isOk = false;
        }
        if ((!adminPesel.getText().equals("")) && !(adminPesel.getText().isEmpty()) && adminPesel.getText().length() == 11) {
            admin.setPesel(adminPesel.getText());
            removeRed(adminPesel);
        } else {
            setRed(adminPesel);
            isOk = false;
        }
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(adminEmail.getText());
        if ((!adminEmail.getText().equals("")) && matcher.matches()) {
            admin.setAdminEmail(adminEmail.getText());
            removeRed(adminEmail);
        } else {
            setRed(adminEmail);
            isOk = false;
        }
        if (adminTypeComboBox.getValue() != null) {
            admin.setIdAdminType(adminTypeComboBox.getSelectionModel().getSelectedItem().getIdAdminType());
            removeRed(adminTypeComboBox);
        } else {
            setRed(adminTypeComboBox);
            isOk = false;
        }
        if ((!adminPass.getText().equals("")) && adminPass.getText().length() > 7) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(adminPass.getText().getBytes());
                byte[] digest = md.digest();
                BigInteger no = new BigInteger(1, digest);
                String hashText = no.toString(16);
                while (hashText.length() < 32) {
                    hashText = "0" + hashText;
                }
                admin.setPassword(hashText);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            }

            removeRed(adminPass);
        } else {
            setRed(adminPass);
            isOk = false;
        }
        if ((!adminPhone.getText().equals("")) && adminPhone.getText().length() >= 9) {
            admin.setAdminPhone(adminPhone.getText());
            removeRed(adminPhone);
        } else {
            setRed(adminPhone);
            isOk = false;
        }
        if (isOk)
            return admin;
        else return null;
    }

    public void addAdmin() throws SQLException {
        if (dataFormat() != null) {
            Alert alert = new Alert(Alert.AlertType.NONE, "Dodać admina ?",
                    ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                if (AdminDAO.insertAdmin(dataFormat())) {
                    alert = new Alert(Alert.AlertType.INFORMATION, "Admin został dodany!", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        stage.getScene().getWindow().fireEvent(new WindowEvent(stage.getScene().getWindow(), WindowEvent.WINDOW_CLOSE_REQUEST));
                    }
                }
            }
        }
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
}
