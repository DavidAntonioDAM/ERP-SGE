package com.sge.erp.view.project;

import com.jfoenix.controls.JFXButton;
import com.sge.erp.model.Project;
import com.sge.erp.persistence.ManagerClient;
import com.sge.erp.persistence.ManagerStaff;
import com.sge.erp.persistence.ManagerTask;
import com.sge.erp.utility.DialogCreator;
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

public class ProjectController implements Initializable {


    private MainWindowController mwc;
    private Project selectedProject;
    private Parent listPane;
    private MemberListController mlc;
    private ManagerTask mt;
    private ManagerStaff ms;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            mt = new ManagerTask();
            ms = new ManagerStaff();
            //dc = new DialogCreator(mainContainer);
            loadUI("member_list");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private JFXButton jbMembers;

    @FXML
    private AnchorPane container;

    @FXML
    void addClient(MouseEvent event) {

    }

    @FXML
    void loadAll(MouseEvent event) {
        mlc.loadAll();
        loadUI("member_list");
    }

    public void loadUI(String ui){
        Parent root = null;

        if (listPane == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                root = loader.load();

                mlc = loader.getController();
                mlc.setPc(this);
                mlc.setMt(mt);
                mlc.loadAll();

                listPane = root;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            switch (ui) {
                case "project_list":
                    root = listPane;
                    break;
                case "member_list":
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                        root = loader.load();

                        MemberListController mlc = loader.getController();
                        mlc.setMt(mt);
                        mlc.setPc(this);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
        container.getChildren().setAll(root);
    }

    public MainWindowController getMwc() {
        return mwc;
    }

    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

    public void setMwc(MainWindowController mwc) {
        this.mwc = mwc;
    }

    public void setMt(ManagerTask mt) {
        this.mt = mt;
    }

    public void setMs(ManagerStaff ms) {
        this.ms = ms;
    }
}
