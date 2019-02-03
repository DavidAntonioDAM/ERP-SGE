package com.sge.erp.view.project;

import com.jfoenix.controls.JFXButton;
import com.sge.erp.model.Project;
import com.sge.erp.model.Staff;
import com.sge.erp.model.Task;
import com.sge.erp.persistence.ManagerClient;
import com.sge.erp.persistence.ManagerStaff;
import com.sge.erp.persistence.ManagerTask;
import com.sge.erp.utility.DialogCreator;
import com.sge.erp.view.mainWindow.MainWindowController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProjectController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            mt = new ManagerTask();
            ms = new ManagerStaff();
            tlc = new TaskListController();
            //dc = new DialogCreator(mainContainer);
          //  loadUI("task_list");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private MainWindowController mwc;
    private Project selectedProject;
    private Parent listPane;
    private MemberListController mlc;
    private ManagerTask mt;
    private ManagerStaff ms;
    private TaskListController tlc;
    private Task taskSelected;

    @FXML
    private JFXButton jbMembers;

    @FXML
    private AnchorPane container;

    @FXML
    void loadAll(MouseEvent event) {
        //mlc.loadAll();
        //loadUI("member_list");
    }

    @FXML
    void loadMembers(MouseEvent event) {
        //mlc.loadAll();
        loadUI("member_list");
    }

    @FXML
    void loasTaskList(MouseEvent event) {
        //tlc.loadAll();
        loadUI("task_list");
    }

    public void reloadTaskList() {
        loadUI("task_list");
    }

    public void loadUI(String ui){
        Parent root = null;

       /* if (listPane == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                root = loader.load();

                tlc = loader.getController();
                tlc.setPc(this);
                tlc.setMt(mt);
                tlc.setMs(ms);
                tlc.loadAll();

                listPane = root;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {*/
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
                        mlc.setMs(ms);
                        mlc.setPc(this);
                        mlc.loadAll();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "task_list":
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                        root = loader.load();

                        TaskListController tlc = loader.getController();
                        tlc.setMt(mt);
                        tlc.setMs(ms);
                        tlc.setPc(this);
                        tlc.loadAll();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "add_task":
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                        root = loader.load();

                        TaskAddController tac = loader.getController();
                        tac.setMt(mt);
                        tac.setPc(this);

                        ArrayList<Staff> employees = ms.getStaffByProject(getSelectedProject().getId_project());

                        ObservableList<String> itemsEmployees = FXCollections.observableArrayList();
                        for (Staff s: employees) {
                            itemsEmployees.add(
                                    "(" + s.getDni() + ") " + s.getSurname() + ", " + s.getName()
                            );
                        }
                        tac.getJcbName().setItems(itemsEmployees);

                        ObservableList<String> itemsState =
                                FXCollections.observableArrayList(
                                        "Pendiente",
                                        "Pausada",
                                        "En Curso",
                                        "Completada"
                                );
                        tac.getJcbState().setItems(itemsState);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "mod_task":
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                        root = loader.load();

                        TaskModController tmc = loader.getController();
                        tmc.setMt(mt);
                        tmc.setMs(ms);
                        tmc.setPc(this);

                        ArrayList<Staff> employees = ms.getStaffByProject(getSelectedProject().getId_project());

                        ObservableList<String> itemsEmployees = FXCollections.observableArrayList();
                        itemsEmployees.add("");
                        for (Staff s: employees) {
                            itemsEmployees.add(
                                    "(" + s.getDni() + ") " + s.getSurname() + ", " + s.getName()
                            );
                        }
                        tmc.getJcbName().setItems(itemsEmployees);

                        ObservableList<String> itemsState =
                                FXCollections.observableArrayList(
                                        "Pendiente",
                                        "Pausada",
                                        "En Curso",
                                        "Completada"
                                );
                        tmc.getJcbState().setItems(itemsState);
                        tmc.setTaskToModify(taskSelected);
                        tmc.setFields();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
           // }
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

    public void setTaskSelected(Task taskSelected) {
        this.taskSelected = taskSelected;
    }
}
