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
            if (fieldValidation()){
                if (dniValidate()){
                    ms.insertStaff(s);

                    ec.reloadList();
                    ec.loadUI("employees_list");

                    // POP UP EMPLEADO INSERTADO
                } else {
                    // POP UP EL DNI ESTA MAL
                }
            } else {
                // POP UP RELLENAR TODOS LOS CAMPOS
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cancel(MouseEvent event) {
        ec.loadUI("employees_list");
    }

    private boolean dniValidate() {
        String letter = "";
        String dni = jtfDNI.getText();

        if (dni.length() != 9 || !Character.isLetter(dni.charAt(8))){
            return false;
        } else if (numbersOnly(dni)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean numbersOnly(String dni) {
        String number;
        String dniTMP = "";

        for (int i = 0; i < dni.length() - 1; i++){
            number = dni.substring(i, i+1);

            for (int j = 0; j < 10; j++){
                if (number.equals(String.valueOf(j))){
                    dniTMP += String.valueOf(j);
                }
            }
        }

        if (dniTMP.length() != 8) {
            return false;
        } else {
            return true;
        }
    }

    private boolean fieldValidation() {
        String name = jtfName.getText();
        String surname = jtfSurname.getText();
        String job = jtfJob.getText();

        if (name.length() == 0 || surname.length() == 0 || job.length() == 0){
            return false;
        } else {
            return true;
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
