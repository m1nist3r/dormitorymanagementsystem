package dormitorymanagementsystem.util;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import dormitorymanagementsystem.controllers.MainWindowController;
import dormitorymanagementsystem.controllers.SidePanelController;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SidePanelInstance {

    private JFXDrawer drawer;
    private JFXHamburger menuHamburger;
    private Stage primaryStage;

    public SidePanelInstance(JFXDrawer drawer, JFXHamburger menuHamburger, Stage primaryStage) {
        this.drawer = drawer;
        this.menuHamburger = menuHamburger;
        this.primaryStage = primaryStage;
    }

    public void setUpHamburger() {
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(menuHamburger);
        transition.setRate(-1);
        menuHamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isOpened()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
    }

    public void sidePanelInit() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SidePanelContent.fxml"));
            VBox box = loader.load();
            SidePanelController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);
            drawer.setSidePane(box);
            drawer.open();
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
