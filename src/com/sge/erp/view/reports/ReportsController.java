package com.sge.erp.view.reports;

import com.sge.erp.persistence.ManagerProjects;
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
        //  loadUI("completedprojects");
    }

    private ManagerProjects mp;


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

        switch (ui) {
            case "completedprojects":
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                    root = loader.load();


                } catch (IOException e) {
                    e.printStackTrace();
                }
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


                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "projectsmoreadvance":
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                    root = loader.load();


                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "projectstoend":
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                    root = loader.load();


                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "projectswithmoreclients":
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                    root = loader.load();


                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "projectswithmoretasks":
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                    root = loader.load();


                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }

        container.getChildren().setAll(root);
    }

}