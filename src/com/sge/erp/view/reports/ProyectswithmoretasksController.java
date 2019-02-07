package com.sge.erp.view.reports;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.sge.erp.model.Project;
import com.sge.erp.model.Task;
import com.sge.erp.persistence.ManagerProjects;
import com.sge.erp.persistence.ManagerTask;
import com.sge.erp.utility.MoreAdvancedProjectsExcel;
import com.sge.erp.utility.PathSelector;
import com.sge.erp.utility.ProjectsWithMoreTaskExcel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProyectswithmoretasksController implements Initializable {

    private File f;

    @FXML
    private JFXTextField path;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            mt = new ManagerTask();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Project> projects;
    private ManagerProjects mp;
    private ManagerTask mt;
    private ReportsController rc;

    @FXML
    private JFXListView<AnchorPane> list;

    void loadAll() {
        try {
            projects = mp.getProjectwithmoreTask();
            loadList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setMp(ManagerProjects mp) {
        this.mp = mp;
    }

    public void setPc(ReportsController rc) {
        this.rc = rc;
    }

    private void loadList() {

        try {
            list.getItems().clear();
            int contador = 0;

            for (Project p : projects) {

                ArrayList<Task> tasks = mt.getProjectTask(p.getId_project());
                int taskComplete = 0;
                double projectComplete = 0;

                if (tasks.size() != 0) {

                    for (Task t : tasks) {
                        if (t.getState().equalsIgnoreCase("completada")) {
                            taskComplete++;
                        }
                    }
                    projectComplete = ((100 * taskComplete) / tasks.size()) * 0.01;

                }

                if (tasks.size() >= 1) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("projects_card_task.fxml"));
                    AnchorPane card = loader.load();
                    ProjectCardTaskController cc = loader.getController();

                    cc.getlProjectName().setText(p.getName());
                    cc.getJtaProjectDesc().setText(p.getDescription());
                    cc.getChart().setProgress(projectComplete);
                    cc.getPercent().setText(String.valueOf(((int) (projectComplete * 100))) + "%");
                    cc.getlTareas().setText("Numero de tareas: " + tasks.size());

                    list.getItems().add(card);
                }
                contador++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void selectPath(MouseEvent event) {
        PathSelector ps = new PathSelector();
        f = ps.selectPath(rc.getStage());
        path.setText(f.toString());
    }

    @FXML
    void jfxbSave(MouseEvent event) {
        try {
            ProjectsWithMoreTaskExcel pwet = new ProjectsWithMoreTaskExcel();
            pwet.create(f);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
