package com.sge.erp.view.reports;

import com.sge.erp.persistence.ManagerClient;
import com.sge.erp.persistence.ManagerProjects;
import com.sge.erp.persistence.ManagerStaff;
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
            loadUI("completedprojects");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private ManagerClient mc;
    private ManagerProjects mp;
    private ManagerStaff ms;
    private CompletedprojectsController cpc;
    private ProjectstoendController ptec;
    private ProjectsmoreadvanceController pmac;
    private ProyectswithmoretasksController ptask;
    private ProjectswithmoreclientsController cl;
    private EmployeeswithoutteamController ewtc;


    @FXML
    private AnchorPane container;

    @FXML
    void completedprojects(MouseEvent event) {
        loadUI("completedprojects");
    }

    @FXML
    void efficientworkers(MouseEvent event) {
        loadUI("efficientworkers");
    }

    @FXML
    void employeeswithoutteam(MouseEvent event) {
        loadUI("employeeswithoutteam");
    }

    @FXML
    void moreadvanceprojects(MouseEvent event) {
        loadUI("projectsmoreadvance");
    }

    @FXML
    void projectstoend(MouseEvent event) {
        loadUI("projectstoend");
    }

    @FXML
    void projectswithmoreclients(MouseEvent event) {
        loadUI("projectswithmoreclients");
    }

    @FXML
    void projectswithmoretasks(MouseEvent event) {
        loadUI("projectswithmoretasks");
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
            case "completedprojects":
                    root = listPane;

                break;
            case "efficientworkers":
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                    root = loader.load();


                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "employeeswithoutteam":
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
            case "projectsmoreadvance":
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
            case "projectstoend":
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
            case "projectswithmoreclients":
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
            case "projectswithmoretasks":
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