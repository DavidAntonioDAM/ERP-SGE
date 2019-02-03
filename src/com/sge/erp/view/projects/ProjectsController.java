package com.sge.erp.view.projects;

import com.sge.erp.model.Project;
import com.sge.erp.persistence.ManagerProjects;
import com.sge.erp.persistence.ManagerStaff_Team;
import com.sge.erp.persistence.ManagerTeam;
import com.sge.erp.view.mainWindow.MainWindowController;
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
            mt = new ManagerTeam();
            mst = new ManagerStaff_Team();

            loadUI("projects_list");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ManagerProjects mp;
    private Parent listPane;
    private ProjectsListController pl;
    private ManagerStaff_Team mst;

    private TeamListController tlc;
    private ManagerTeam mt;
    private Project projectSelected;
    private MainWindowController mwc;

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
        loadUI("team_list");
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
                case "team_list":
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                        root = loader.load();

                        TeamListController tlc = loader.getController();
                        tlc.setMt(mt);
                        tlc.setMp(mp);
                        tlc.setPc(this);
                        tlc.loadAll();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case "add_team":
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                        root = loader.load();

                        AddTeamController atc = loader.getController();
                        atc.setMt(mt);
                        atc.setMp(mp);
                        atc.setMst(mst);
                        atc.setPc(this);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
        container.getChildren().setAll(root);
    }

    public Project getProjectSelected() {
        return projectSelected;
    }

    public void setProjectSelected(Project projectSelected) {
        this.projectSelected = projectSelected;
    }

    public void setMwc(MainWindowController mwc) {
        this.mwc = mwc;
    }

    public MainWindowController getMwc() {
        return mwc;
    }
}
