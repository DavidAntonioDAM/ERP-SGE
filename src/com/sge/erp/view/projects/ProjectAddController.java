package com.sge.erp.view.projects;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.sge.erp.model.Project;
import com.sge.erp.persistence.ManagerProjects;
import com.sge.erp.utility.DialogCreator;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class ProjectAddController {


    private ManagerProjects mp;
    private ProjectsController pc;
    private DialogCreator dg;

    @FXML
    private JFXTextField jtfNif;

    @FXML
    private JFXTextField jtfName;

    @FXML
    private JFXTextArea jtaDescription;

    @FXML
    private JFXDatePicker jdpDate;

    @FXML
    void addProject(MouseEvent event) {

        Project p = new Project(
                jtfNif.getText(),
                jtfName.getText(),
                jtaDescription.getText(),
                String.valueOf(jdpDate.getValue())

        );
        try {
            if (fieldValidation()){
                    if (nifValidate()){
                        mp.insertProject(p);
                        pc.reloadProjectlist();
                        pc.loadUI("projects_list");


                       /* pc.getDc().showDialog(new Text("Éxito"),
                                new Text("El empleado ha sido añadido con éxito."));*/

                    } else {
                        dg.showDialog(new Text("Error con el NIF"),
                                new Text("El NIF del cliente no está puesto correctamente.\n\n" +
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

    @FXML
    void cancel(MouseEvent event) {
        pc.loadUI("projects_list");
    }

    private boolean nifValidate() {
        String nif = jtfNif.getText();

        if (nif.length() != 9 || !Character.isLetter(nif.charAt(0)) || !Character.isLetter(nif.charAt(8))) {
            return false;
        } else if (numbersOnly(nif)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean numbersOnly(String dni) {
        String number;
        String nifTMP = "";

        for (int i = 1; i < dni.length() - 1; i++){
            number = dni.substring(i, i+1);

            for (int j = 0; j < 10; j++){
                if (number.equals(String.valueOf(j))){
                    nifTMP += String.valueOf(j);
                }
            }
        }

        if (nifTMP.length() != 7) {
            return false;
        } else {
            return true;
        }
    }

    private boolean fieldValidation() {
        String name = jtfName.getText();
        String description = jtaDescription.getText();
        String deliver_date = String.valueOf(jdpDate.getValue());

        if (name.length() == 0 || description.length() == 0 || deliver_date.length() == 0){
            return false;
        } else {
            return true;
        }
    }

    public void setMp(ManagerProjects mp) {
        this.mp = mp;
    }

    public void setPc(ProjectsController pc) {
        this.pc = pc;
    }
}
