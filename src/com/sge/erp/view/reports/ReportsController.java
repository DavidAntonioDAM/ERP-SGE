package com.sge.erp.view.reports;

import com.sge.erp.persistence.ManagerClient;
import com.sge.erp.persistence.ManagerProjects;
import com.sge.erp.persistence.ManagerStaff;
import com.sge.erp.persistence.ManagerTask;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            mp = new ManagerProjects();
            mc = new ManagerClient();
            ms = new ManagerStaff();
            mk = new ManagerTask();
            loadUI("completed_projects");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private ManagerClient mc;
    private ManagerProjects mp;
    private ManagerStaff ms;
    private ManagerTask mk;
    private CompletedProjectsController cpc;
    private ProjectsToEndController ptec;
    private MoreAdvancedProjectsController pmac;
    private MoreProjectTasksController ptask;
    private MoreClientProjectsController cl;
    private StaffsWithOutTeamController ewtc;
    private EfficientWorkersController ewc;


    @FXML
    private AnchorPane container;

    @FXML
    void completedprojects(MouseEvent event) {
        loadUI("completed_projects");
    }

    @FXML
    void efficientworkers(MouseEvent event) {
        loadUI("efficient_workers");
    }

    @FXML
    void employeeswithoutteam(MouseEvent event) {
        loadUI("staffs_with_out_team");
    }

    @FXML
    void moreadvanceprojects(MouseEvent event) {
        loadUI("more_advanced_projects");
    }

    @FXML
    void projectstoend(MouseEvent event) {
        loadUI("projects_to_end");
    }

    @FXML
    void projectswithmoreclients(MouseEvent event) {
        loadUI("more_client_projects");
    }

    @FXML
    void projectswithmoretasks(MouseEvent event) {
        loadUI("more_project_tasks");
    }

    private Parent listPane;

    public void loadUI(String ui) {
        Parent root = null;
        if (listPane == null) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                root = loader.load();

                cpc = loader.getController();
                cpc.setPc(this);
                cpc.setMp(mp);
                cpc.loadAll();

                listPane = root;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
        switch (ui) {
            case "completed_projects":
                    root = listPane;

                break;
            case "efficient_workers":
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                    root = loader.load();

                    ewc = loader.getController();
                    ewc.setPc(this);
                    ewc.setMs(ms);
                    ewc.setMk(mk);
                    ewc.loadAll();


                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "staffs_with_out_team":
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                    root = loader.load();

                    ewtc = loader.getController();
                    ewtc.setPc(this);
                    ewtc.setMs(ms);
                    ewtc.loadAll();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "more_advanced_projects":
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                    root = loader.load();

                    pmac = loader.getController();
                    pmac.setPc(this);
                    pmac.setMp(mp);
                    pmac.loadAll();



                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "projects_to_end":
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                    root = loader.load();

                    ptec = loader.getController();
                    ptec.setPc(this);
                    ptec.setMp(mp);
                    ptec.loadAll();


                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "more_client_projects":
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                    root = loader.load();

                    cl = loader.getController();
                    cl.setPc(this);
                    cl.setMc(mc);
                    cl.setMp(mp);
                    cl.loadAll();


                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "more_project_tasks":
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                    root = loader.load();

                    ptask = loader.getController();
                    ptask.setPc(this);
                    ptask.setMp(mp);
                    ptask.loadAll();


                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
        }

        container.getChildren().setAll(root);
    }

}