package dormitorymanagementsystem.controllers;

import dormitorymanagementsystem.model.admin.Admin;
import dormitorymanagementsystem.model.admin.AdminDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;

public class LoginWindowController {

    @FXML
    private PasswordField loginPassword;

    @FXML
    private TextField loginPesel;

    @FXML
    private ImageView loginImageView;


    private Stage primaryStage;


    private static void showAlert(Window owner, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Form Error!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void initialize() {
        /*Platform.runLater(() -> {

        });*/
        centerImage();
    }

    @FXML
    private void loginActivity() throws SQLException, IOException {
        Window owner = loginImageView.getScene().getWindow();
        if (loginPesel.getText().isEmpty()) {
            showAlert(owner,
                    "Please enter your pesel");
            return;
        }
        if (loginPassword.getText().isEmpty()) {
            showAlert(owner,
                    "Please enter a password");
            return;
        }

        String pesel = loginPesel.getText();
        String password = loginPassword.getText();

        Admin admin = AdminDAO.searchAdminByPesPas(pesel, password);
        login(admin);
    }

    private void login(Admin admin) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/MainWindow.fxml"));
        Parent root = loader.load();
        MainWindowController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        controller.setAdmin(admin);
        primaryStage.setHeight(760);
        primaryStage.setWidth(1280);
        primaryStage.getScene().setRoot(root);
    }

    private void centerImage() {
        Image img = loginImageView.getImage();
        if (img != null) {
            double w;
            double h;

            double ratioX = loginImageView.getFitWidth() / img.getWidth();
            double ratioY = loginImageView.getFitHeight() / img.getHeight();

            double reducCoeff;
            reducCoeff = Math.min(ratioX, ratioY);

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            loginImageView.setX((loginImageView.getFitWidth() - w) / 2);
            loginImageView.setY((loginImageView.getFitHeight() - h) / 2);

        }
    }
}
