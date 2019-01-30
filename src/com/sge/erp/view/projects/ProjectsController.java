package com.sge.erp.view.projects;

import com.sge.erp.model.Client;
import com.sge.erp.model.Project;
import com.sge.erp.persistence.ManagerClient;
import com.sge.erp.persistence.ManagerProjects;
import com.sge.erp.view.clients.ClientListController;
import com.sge.erp.view.clients.ClientModController;
import com.sge.erp.view.clients.ClientsAddClientController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProjectsController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            mp = new ManagerProjects();
            loadUI("projects_list");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private ManagerProjects mp;
    private Parent listPane;
    ProjectsListController pl;
    Project projectSelected;

    @FXML
    private AnchorPane container;

    @FXML
    void addProject(MouseEvent event) {
        loadUI("add_project");
    }

    @FXML
    void loadAll(MouseEvent event) {
        pl.loadAll();
        loadUI("projects_list");
    }

    @FXML
    void showTeams(MouseEvent event) {

    }

    public void reloadProjectlist(){
        pl.loadAll();
    }

    public void loadUI(String ui) {
        Parent root = null;

        if (listPane == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                root = loader.load();

                pl = loader.getController();
                pl.setPc(this);
                pl.setMp(mp);
                pl.loadAll();

                listPane = root;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            switch (ui){
                case "projects_list":
                    root = listPane;

                    break;

                case "add_project":
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                        root = loader.load();

                        ProjectAddController pac = loader.getController();
                        pac.setMp(mp);
                        pac.setPc(this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                /*case "mod_project":
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                        root = loader.load();

                        ClientModController cmcc = loader.getController();
                        cmcc.setMc(mc);
                        cmcc.setCc(this);
                        cmcc.setClientToModify(clientSelected);
                        cmcc.setFields();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;*/
            }
        }

        container.getChildren().setAll(root);
    }

}
