package com.sge.erp.view.home;

import com.sge.erp.view.mainWindow.MainWindowController;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class HomeController {
    private MainWindowController mainController;

    public void setMainController(MainWindowController mainController) {
        this.mainController = mainController;
    }

    @FXML
    void goClients(MouseEvent event) {
        mainController.loadUI("../clients/clients");
    }

    @FXML
    void goEmployees(MouseEvent event) {
        mainController.loadUI("../employees/employees");
    }

    @FXML
    void goProjects(MouseEvent event) {
        mainController.loadUI("../projects/projects");
    }

    @FXML
    void goReports(MouseEvent event) {
        //mainController.loadUI("../reports/reports");
    }
}
