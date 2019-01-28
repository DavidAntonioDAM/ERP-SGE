package com.sge.erp.view.employees;

import com.jfoenix.controls.JFXTextField;
import com.sge.erp.model.Staff;
import com.sge.erp.persistence.ManagerStaff;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeesAddEmployeeController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private ManagerStaff ms;
    private EmployeesController ec;

    @FXML
    private JFXTextField jtfDNI;

    @FXML
    private JFXTextField jtfName;

    @FXML
    private JFXTextField jtfSurname;

    @FXML
    private JFXTextField jtfJob;

    @FXML
    void addEmployee(MouseEvent event) {
        Staff s = new Staff(
                jtfDNI.getText(),
                jtfName.getText(),
                jtfSurname.getText(),
                jtfJob.getText()
        );

        try {
            ms.insertStaff(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ec.reloadList();
        ec.loadUI("employees_list");
    }

    @FXML
    void cancel(MouseEvent event) {
        ec.loadUI("employees_list");
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
