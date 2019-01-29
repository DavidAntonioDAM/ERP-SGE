package com.sge.erp.view.projects;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.sge.erp.model.Client;
import com.sge.erp.model.Project;
import com.sge.erp.persistence.ManagerClient;
import com.sge.erp.persistence.ManagerProjects;
import com.sge.erp.view.clients.ClientCardController;
import com.sge.erp.view.clients.ClientsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectsListController {


    private ArrayList<Project> projects;
    private ManagerProjects mp;
    private Project projectSelected;
    private ProjectsController pc;

    @FXML
    private JFXListView<AnchorPane> list;

    @FXML
    private JFXTextField jtfSearch;

    @FXML
    void filterName(MouseEvent event) {

    }

    void loadAll() {
        try {
            projects = mp.getProjects();
            loadList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private void loadList() {

        try {

            list.getItems().clear();

            for (Project p : projects) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("projects_card.fxml"));
                AnchorPane card = loader.load();
                ProjectCardController cc = loader.getController();

                cc.getlProjectName().setText(p.getName());
                cc.getJtaProjectDesc().setText(p.getDescription());

                list.getItems().add(card);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMp(ManagerProjects mp) {
        this.mp = mp;
    }

    public void setPc(ProjectsController pc) {
        this.pc = pc;
    }
}
