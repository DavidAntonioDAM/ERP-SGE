package com.sge.erp.view.employees;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.sge.erp.model.Client;
import com.sge.erp.model.Staff;
import com.sge.erp.persistence.ManagerClient;
import com.sge.erp.persistence.ManagerStaff;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeesListController {

    private ArrayList<Staff> staffs;
    private ManagerStaff ms;

    @FXML
    private JFXListView<AnchorPane> list;

    @FXML
    private JFXTextField jtfSearch;

    @FXML
    void filterName(MouseEvent event) {

    }

    public void loadAll() {
        try {
            staffs = ms.getStaffs();
            loadList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadList() {

        try {

            list.getItems().clear();

            for (Staff s : staffs) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("employee_card.fxml"));
                AnchorPane card = loader.load();
                EmployeesCardController cc = loader.getController();

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

    public ManagerStaff getMs() {
        return ms;
    }

    public void setMs(ManagerStaff ms) {
        this.ms = ms;
    }
}
