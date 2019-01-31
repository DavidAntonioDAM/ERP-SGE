package com.sge.erp.view.project;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.sge.erp.model.Task;
import com.sge.erp.persistence.ManagerTask;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;

public class TaskAddController {

    private ManagerTask mt;
    private ProjectController pc;

    @FXML
    private JFXTextField jtfDNI;

    @FXML
    private JFXTextField jtfName;

    @FXML
    private JFXComboBox<String> jcbState;

    @FXML
    private JFXTextArea jtaDesc;

    @FXML
    void addTask(MouseEvent event) {
        Task t = new Task(
            4,
            jtfDNI.getText(),
            jtfName.getText(),
            jtaDesc.getText(),
            jcbState.getValue().toString()
        );

        try {
            if (fieldValidation()){
                if (dniValidate()) {
                    mt.insertTask(t);

                    pc.reloadTaskList();
                    pc.loadUI("task_list");
                    // EXITO
                } else {
                    // ERROR DNI
                }
            } else {
                // ERROR CAMPOS
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void cancel(MouseEvent event) {
        pc.loadUI("task_list");
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
        String desc = jtaDesc.getText();
        String state = jcbState.getValue().toString();

        if (name.length() == 0 || state.length() == 0 || desc.length() == 0){
            return false;
        } else {
            return true;
        }
    }

    public void setMt(ManagerTask mt) {
        this.mt = mt;
    }

    public void setPc(ProjectController pc) {
        this.pc = pc;
    }

    public JFXComboBox<String> getJcbState() {
        return jcbState;
    }

    public void setJcbState(JFXComboBox<String> jcbState) {
        this.jcbState = jcbState;
    }
}
