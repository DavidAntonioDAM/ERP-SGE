package com.sge.erp.view.employees;

import com.jfoenix.controls.JFXTextField;
import com.sge.erp.model.Staff;
import com.sge.erp.persistence.ManagerStaff;
import com.sge.erp.utility.DialogCreator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeModController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dg = new DialogCreator(container);
    }

    private EmployeesController ec;
    private ManagerStaff ms;
    private Staff staffToModify;
    private DialogCreator dg;

    @FXML
    private StackPane container;

    @FXML
    private JFXTextField jtfDNI;

    @FXML
    private JFXTextField jtfName;

    @FXML
    private JFXTextField jtfSurname;

    @FXML
    private JFXTextField jtfJob;

    @FXML
    void cancel(MouseEvent event) {
        ec.loadUI("employees_list");
    }

    @FXML
    void modEmployee(MouseEvent event) {
        Staff s = new Staff(
                jtfDNI.getText(),
                jtfName.getText(),
                jtfSurname.getText(),
                jtfJob.getText()
        );

        try {
            if (fieldValidation()){
                if (dniValidate()){
                    ms.updateStaff(staffToModify.getDni(), s);

                    ec.reloadList();

                    dg.showDialog(new Text("Éxito"),
                            new Text("El empleado ha sido modificado con éxito."));


                    ec.loadUI("employees_list");
                } else {
                    dg.showDialog(new Text("Error con el DNI"),
                            new Text("El DNI del empleado no está puesto correctamente.\n\n" +
                                    "Intentelo de nuevo."));
                }
            } else {
                dg.showDialog(new Text("Error en los campos"),
                        new Text("Alguno, o varios, de los campos no están correctamente rellenos.\n\n" +
                                "Intentelo de nuevo."));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setFields(){
        jtfDNI.setText(staffToModify.getDni());
        jtfName.setText(staffToModify.getName());
        jtfSurname.setText(staffToModify.getSurname());
        jtfJob.setText(staffToModify.getJob());
    }

    private boolean dniValidate() {
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

    public EmployeesController getEc() {
        return ec;
    }

    public void setEc(EmployeesController ec) {
        this.ec = ec;
    }

    public ManagerStaff getMs() {
        return ms;
    }

    public void setMs(ManagerStaff ms) {
        this.ms = ms;
    }

    public Staff getStaffToModify() {
        return staffToModify;
    }

    public void setStaffToModify(Staff staffToModify) {
        this.staffToModify = staffToModify;
    }
}
