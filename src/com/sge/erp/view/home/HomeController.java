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
        mainController.getTitleWindow().setText("Clientes");
    }

    @FXML
    void goEmployees(MouseEvent event) {
        mainController.loadUI("../employees/employees");
        mainController.getTitleWindow().setText("Personal");
    }

    @FXML
    void goProjects(MouseEvent event) {
        mainController.loadUI("../projects/projects");
        mainController.getTitleWindow().setText("Projectos");
    }

    @FXML
    void goReports(MouseEvent event) {
        //mainController.loadUI("../reports/reports");
        //mainController.getTitleWindow().setText("Informes");
    }
}
