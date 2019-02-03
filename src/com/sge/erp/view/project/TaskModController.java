package com.sge.erp.view.project;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.sge.erp.model.Staff;
import com.sge.erp.model.Task;
import com.sge.erp.persistence.ManagerStaff;
import com.sge.erp.persistence.ManagerTask;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class TaskModController {

    private ManagerTask mt;
    private ManagerStaff ms;
    private ProjectController pc;
    private Task taskToModify;

    @FXML
    private JFXButton jbAccept;

    @FXML
    private JFXButton jbCancel;

    @FXML
    private JFXTextField jtfName;

    @FXML
    private JFXComboBox<String> jcbState;

    @FXML
    private JFXTextArea jtaDesc;

    @FXML
    private JFXComboBox<String> jcbName;

    @FXML
    void modTask(MouseEvent event) {
        String employeeName = jcbName.getValue();
        String dni = "";

        if (employeeName==null || employeeName.equals("")){
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
                mt.updateTask(taskToModify.getName(), taskToModify.getDescription(), t);

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

    public void setFields() throws SQLException {
        Staff st;

        if (taskToModify.getDni()==null){
            jcbName.setValue("");
        } else {
            st = ms.getStaff(taskToModify.getDni());
            jcbName.setValue("(" + st.getDni() + ") " + st.getSurname() + ", " + st.getName());
        }

        jtfName.setText(taskToModify.getName());
        jtaDesc.setText(taskToModify.getDescription());
        jcbState.setValue(taskToModify.getState());
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

    public Task getTaskToModify() {
        return taskToModify;
    }

    public void setTaskToModify(Task taskToModify) {
        this.taskToModify = taskToModify;
    }

    public void setMs(ManagerStaff ms) {
        this.ms = ms;
    }
}
