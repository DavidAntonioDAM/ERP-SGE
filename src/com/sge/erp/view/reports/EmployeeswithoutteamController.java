package com.sge.erp.view.reports;

import com.jfoenix.controls.JFXListView;
import com.sge.erp.model.Staff;
import com.sge.erp.persistence.ManagerStaff;
import com.sge.erp.utility.StandbyEmployeesExcel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeswithoutteamController {

    private ArrayList<Staff> staffs;
    private ManagerStaff ms;
    private ReportsController rc;

    @FXML
    private JFXListView<AnchorPane> list;


    public void loadAll() {
        try {
            staffs = ms.getStandbyEmployees();
            loadList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadList() {

        try {

            list.getItems().clear();

            for (Staff s : staffs) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("employee_card_withoutteam.fxml"));
                AnchorPane card = loader.load();
                EmployeesCardWithoutTeamController cc = loader.getController();

                cc.getlEmployeeName().setText(s.getName());
                cc.getlEmployeeSurname().setText(s.getSurname());
                cc.getlDNI().setText(s.getDni());
                cc.getlJob().setText(s.getJob());

                list.getItems().add(card);
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setMs(ManagerStaff ms) {
        this.ms = ms;
    }

    public void setPc(ReportsController rc) {
        this.rc = rc;
    }

    @FXML
    void jfxbSave(MouseEvent event) {
        try {
            StandbyEmployeesExcel see = new StandbyEmployeesExcel();
            see.create();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
