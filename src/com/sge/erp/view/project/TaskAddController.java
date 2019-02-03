package com.sge.erp.view.project;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.sge.erp.model.Staff;
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
    private JFXComboBox<String> jcbName;

    @FXML
    private JFXTextField jtfName;

    @FXML
    private JFXComboBox<String> jcbState;

    @FXML
    private JFXTextArea jtaDesc;

    @FXML
    void addTask(MouseEvent event) {
        String employeeName = jcbName.getValue();
        String dni = "";

        if (employeeName==null){
            dni = null;
        } else {
            if (employeeName.trim().length()>0) {
                dni = employeeName.substring(employeeName.indexOf("(")+1, employeeName.indexOf(")"));
            }
        }

        Task t = new Task(
                pc.getSelectedProject().getId_project(),
                dni,
                jtfName.getText(),
                jtaDesc.getText(),
                jcbState.getValue()
        );

        try {
            if (fieldValidation()) {
                mt.insertTask(t);

                pc.reloadTaskList();
                pc.loadUI("task_list");
                // EXITO
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

    private boolean fieldValidation() {
        String name = jtfName.getText();
        String desc = jtaDesc.getText();
        String state = jcbState.getValue();

        if (name.length() == 0 || state.length() == 0 || desc.length() == 0) {
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

    public JFXComboBox<String> getJcbName() {
        return jcbName;
    }

    public void setJcbName(JFXComboBox<String> jcbName) {
        this.jcbName = jcbName;
    }
}
