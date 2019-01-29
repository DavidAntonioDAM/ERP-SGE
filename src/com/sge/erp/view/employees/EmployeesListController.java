package com.sge.erp.view.employees;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.sge.erp.model.Client;
import com.sge.erp.model.Staff;
import com.sge.erp.persistence.ManagerClient;
import com.sge.erp.persistence.ManagerStaff;
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

public class EmployeesListController {

    private ArrayList<Staff> staffs;
    private ManagerStaff ms;
    private Staff staffSelected;
    private EmployeesController ec;

    @FXML
    private JFXListView<AnchorPane> list;

    @FXML
    private JFXTextField jtfSearch;

    @FXML
    void filterName(MouseEvent event) {

        try {
            staffs = ms.getStaffsFilter(jtfSearch.getText());
            loadList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void selectEmployee(MouseEvent event) {

        String dni = "";

        ObservableList<AnchorPane> prueba = list.getSelectionModel().getSelectedItems();
        Pane panel = (Pane)  prueba.get(0).getChildren().get(3);
        dni = ((Label)panel.getChildren().get(0)).getText();

        try {
            staffSelected = ms.getStaff(dni);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ec.setStaffSelected(staffSelected);
        ec.loadUI("mod_employee");
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

    public EmployeesController getEc() {
        return ec;
    }

    public void setEc(EmployeesController ec) {
        this.ec = ec;
    }
}
