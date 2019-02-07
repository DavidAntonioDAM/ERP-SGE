package com.sge.erp.view.reports;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.sge.erp.model.Client;
import com.sge.erp.model.Project;
import com.sge.erp.persistence.ManagerClient;
import com.sge.erp.persistence.ManagerProjects;
import com.sge.erp.utility.ClientWithMoreProjectsExcel;
import com.sge.erp.utility.MoreAdvancedProjectsExcel;
import com.sge.erp.utility.PathSelector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectswithmoreclientsController {

    private File f;

    @FXML
    private JFXTextField path;

    private ArrayList<Client> clients;
    private ArrayList<Project> projects;
    private ManagerClient mc;
    private ManagerProjects mp;
    private ReportsController rc;

    @FXML
    private JFXListView<AnchorPane> list;

    void loadAll() {
        try {
            clients = mc.getClientswithmoreProjects();
            projects = mp.getProjects();
            loadList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void loadList() {

        try {

            list.getItems().clear();
            int numprojects = 0;

            int cont = 0;
            for (Client c : clients) {


                String nif = clients.get(cont).getNif();
                numprojects = 0;
                for (Project p : projects) {
                    if (p.getNif_client().equalsIgnoreCase(nif)) {
                        numprojects++;
                    }


                }
                cont++;

                FXMLLoader loader = new FXMLLoader(getClass().getResource("client_card_project.fxml"));
                AnchorPane card = loader.load();
                ClientCardProjectController cc = loader.getController();

                cc.getlClientName().setText(c.getName());
                cc.getlAddress().setText(c.getAddress());
                cc.getlNIF().setText(c.getNif());
                cc.getlTelf().setText(c.getPhone());
                cc.getlEmail().setText(c.getEmail());
                cc.getlNumberProjects().setText("Proyectos: " + numprojects);

                list.getItems().add(card);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMc(ManagerClient mc) {
        this.mc = mc;
    }

    public void setMp(ManagerProjects mp) {
        this.mp = mp;
    }

    public void setPc(ReportsController rc) {
        this.rc = rc;
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
            ClientWithMoreProjectsExcel cwmp = new ClientWithMoreProjectsExcel();
            cwmp.create(f);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
