package com.sge.erp.view.reports;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.sge.erp.model.Staff;
import com.sge.erp.persistence.ManagerStaff;
import com.sge.erp.utility.PathSelector;
import com.sge.erp.utility.StandbyEmployeesExcel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeswithoutteamController {

    private ArrayList<Staff> staffs;
    private ManagerStaff ms;
    private ReportsController rc;
    private File f;

    @FXML
    private JFXListView<AnchorPane> list;


    @FXML
    private StackPane stackpaneid;


    @FXML
    private JFXTextField path;

    @FXML
    void selectPath(MouseEvent event) {

        PathSelector ps = new PathSelector();
        f = ps.selectPath(rc.getStage());
        path.setText(f.toString());
    }

    @FXML
    void jfxbSave(MouseEvent event) {
        try {
            StandbyEmployeesExcel see = new StandbyEmployeesExcel();
            see.create(f);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
}
