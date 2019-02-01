package com.sge.erp.view.project;

import com.jfoenix.controls.JFXListView;
import com.sge.erp.model.Client;
import com.sge.erp.model.Staff;
import com.sge.erp.model.Task;
import com.sge.erp.persistence.ManagerStaff;
import com.sge.erp.persistence.ManagerTask;
import com.sge.erp.view.clients.ClientCardController;
import com.sge.erp.view.projects.ProjectsListController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberListController {

    private ArrayList<Staff> members = new ArrayList<>();
    private ManagerTask mt;
    private ManagerStaff ms;
    private ProjectController pc;

    @FXML
    private JFXListView<AnchorPane> list;

    void loadAll() {
        try {
            members = ms.getStaffsTeam(pc.getSelectedProject().getId_project());
            loadList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadList() {

        try {
            list.getItems().clear();
            for (Staff s : members) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("member_card.fxml"));
                AnchorPane card = loader.load();

                MemberCardController mcc = loader.getController();
                String taskName = mt.getTaskName(s.getDni());
                mcc.getJlName().setText(s.getName() + " " + s.getSurname());
                mcc.getJlTask().setText(taskName);

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
