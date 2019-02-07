package com.sge.erp.view.reports;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.sge.erp.model.Project;
import com.sge.erp.model.Task;
import com.sge.erp.persistence.ManagerProjects;
import com.sge.erp.persistence.ManagerTask;
import com.sge.erp.utility.DialogCreator;
import com.sge.erp.utility.MoreAdvancedProjectsExcel;
import com.sge.erp.utility.PathSelector;
import com.sge.erp.view.projects.ProjectCardController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProjectsmoreadvanceController implements Initializable {

    private File f;

    @FXML
    private JFXTextField path;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            mt = new ManagerTask();
            dg = new DialogCreator(stackpaneid);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private StackPane stackpaneid;

    private DialogCreator dg;

    private ArrayList<Project> projects;
    private ManagerProjects mp;
    private ManagerTask mt;
    private ReportsController rc;

    @FXML
    private JFXListView<AnchorPane> list;

    void loadAll() {
        try {
            projects = mp.getProjects();
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

                if (projectComplete >= 0.5) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("projects_card.fxml"));
                    AnchorPane card = loader.load();
                    ProjectCardController cc = loader.getController();

                    cc.getlProjectName().setText(p.getName());
                    cc.getJtaProjectDesc().setText(p.getDescription());
                    cc.getChart().setProgress(projectComplete);
                    cc.getPercent().setText(String.valueOf(((int) (projectComplete * 100))) + "%");

                    list.getItems().add(card);
                }


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
            MoreAdvancedProjectsExcel mape = new MoreAdvancedProjectsExcel();
            if (f != null) {
                mape.create(f);
            } else {
                dg.showDialog(new Text("Error Con la ruta"),
                        new Text("La ruta esta vacia.\n\n" +
                                "Intentelo de nuevo."));
            }
        } catch (ClassNotFoundException e) {
            dg.showDialog(new Text("Error en la clase"),
                    new Text("Hubo un pete en la clase .\n\n" +
                            "Intentelo de nuevo."));
        } catch (SQLException e) {
            dg.showDialog(new Text("Error Con la base de datos"),
                    new Text("Error en la base de datos.\n\n" +
                            "Intentelo de nuevo."));
        }
    }
}
