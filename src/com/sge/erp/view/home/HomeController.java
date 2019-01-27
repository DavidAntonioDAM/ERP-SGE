package com.sge.erp.view.home;

import com.sge.erp.view.mainWindow.MainWindowController;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class HomeController {
    private MainWindowController mainController;

    public MainWindowController getMainController() {
        return mainController;
    }

    public void setMainController(MainWindowController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private AnchorPane home;

    @FXML
    void goClients(MouseEvent event) {
        mainController.loadUI("../clients/clients");
    }

    @FXML
    void goEmployees(MouseEvent event) {
        mainController.loadUI("../clients/clients");
    }

    @FXML
    void goProjects(MouseEvent event) {

    }
}
