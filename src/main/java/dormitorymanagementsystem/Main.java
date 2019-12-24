package dormitorymanagementsystem;

import dormitorymanagementsystem.controllers.LoginWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@NotNull Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LoginWindow.fxml"));
        Parent root = fxmlLoader.load();
        LoginWindowController controller = fxmlLoader.getController();
        controller.setPrimaryStage(primaryStage);
        primaryStage.setTitle("Dormitory management system");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
