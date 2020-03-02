package dormitorymanagementsystem.util;

import com.jfoenix.controls.JFXDrawer;
import dormitorymanagementsystem.controllers.SidePanelController;
import dormitorymanagementsystem.model.admin.Admin;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SidePanelInstance {

    private JFXDrawer drawer;
    private Stage primaryStage;
    private Admin admin;

    public SidePanelInstance(JFXDrawer drawer, Stage primaryStage, Admin admin) {
        this.drawer = drawer;
        this.primaryStage = primaryStage;
        this.admin = admin;
    }

    public void sidePanelInit() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SidePanelContent.fxml"));
            VBox box = loader.load();
            SidePanelController controller = loader.getController();
            controller.setAdmin(admin);
            controller.setPrimaryStage(primaryStage);
            drawer.setSidePane(box);
            drawer.open();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
