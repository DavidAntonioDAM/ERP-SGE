package com.sge.erp.view.project;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.sge.erp.model.Staff;
import com.sge.erp.model.Task;
import com.sge.erp.persistence.ManagerStaff;
import com.sge.erp.persistence.ManagerTask;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaskListController {

    private ArrayList<Task> tasks = new ArrayList<>();
    private ManagerTask mt;
    private ManagerStaff ms;
    private Task taskSelected;
    private ProjectController pc;

    @FXML
    private JFXListView<AnchorPane> list;

    @FXML
    private JFXTextField jtfSearch;

    @FXML
    void addTask(MouseEvent event) {
        pc.loadUI("add_task");
    }

    @FXML
    void selectTask(MouseEvent event) {
        String name = "";

        ObservableList<AnchorPane> listTask = list.getSelectionModel().getSelectedItems();
        Pane panel = (Pane)  listTask.get(0).getChildren().get(0);
        name = ((Label)panel.getChildren().get(0)).getText();

        try {
            taskSelected = mt.getTask(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        pc.setTaskSelected(taskSelected);
        pc.loadUI("mod_task");
    }

    @FXML
    void filter(MouseEvent event) {
        try {
            tasks = mt.getTasksFilter(
                    jtfSearch.getText(),
                    pc.getSelectedProject().getId_project()
            );
            loadList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void loadAll() {
        try {
            tasks = mt.getProjectTask(pc.getSelectedProject().getId_project());
            loadList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadList() {
        Staff st;

        try {
            list.getItems().clear();
            for (Task t : tasks) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("task_card.fxml"));
                AnchorPane card = loader.load();

                TaskCardController tcc = loader.getController();

                tcc.getJlNameTask().setText(t.getName());

                if (t.getDni()!=null && !t.getDni().equalsIgnoreCase("null")){
                    st = ms.getStaff(t.getDni());
                    tcc.getJlMember().setText(st.getName() + " " + st.getSurname());
                } else {
                    tcc.getJlMember().setText("");
                }
                tcc.getJtaDescription().setText(t.getDescription());
                tcc.getJlState().setText(t.getState());

                switch (t.getState()) {
                    case "Pendiente":
                        tcc.getJlState().setStyle("-fx-text-fill: #923dba");
                        break;
                    case "Pausada":
                        tcc.getJlState().setStyle("-fx-text-fill: #ff5d27");
                        break;
                    case "En Curso":
                        tcc.getJlState().setStyle("-fx-text-fill: #0075f3");
                        break;
                    case "Completada":
                        tcc.getJlState().setStyle("-fx-text-fill: #0ed145");
                        break;
                }

                list.getItems().add(card);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setMt(ManagerTask mt) {
        this.mt = mt;
    }

    public void setMs(ManagerStaff ms) {
        this.ms = ms;
    }

    public void setPc(ProjectController pc) {
        this.pc = pc;
    }
}
