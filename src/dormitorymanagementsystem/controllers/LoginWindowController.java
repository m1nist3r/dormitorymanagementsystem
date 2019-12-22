package dormitorymanagementsystem.controllers;

import dormitorymanagementsystem.model.resident.Admin;
import dormitorymanagementsystem.model.resident.AdminDAO;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

import java.sql.SQLException;

public class LoginWindowController {

    @FXML
    public PasswordField loginPassword;

    @FXML
    public TextField loginPesel;

    @FXML
    ImageView loginImageView;

    @FXML
    AnchorPane loginAnchorPane;

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            loginImageView.setImage(new Image("dormitorymanagementsystem/vendors/css/Images/logo_pollub.png"));
            loginImageView.setX(20);
        });
    }

    @FXML
    private void loginActivity(MouseEvent event) throws SQLException {
        Window owner = loginImageView.getScene().getWindow();
        if (loginPesel.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your pesel");
            return;
        }
        if (loginPassword.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        String pesel = loginPesel.getText();
        String password = loginPassword.getText();

        Admin admin = AdminDAO.searchAdminByPesPas(pesel, password);
        admin.login(admin.getIdAdminType());


    }
}
