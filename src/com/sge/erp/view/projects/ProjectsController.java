package com.sge.erp.view.projects;

import com.sge.erp.model.Client;
import com.sge.erp.model.Project;
import com.sge.erp.persistence.ManagerClient;
import com.sge.erp.view.clients.ClientListController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ProjectsController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    @FXML
    private AnchorPane container;

    private ManagerClient mc;
    private AnchorPane listPane;
    ProjectsController pl;
    Project projectSelected;



}
