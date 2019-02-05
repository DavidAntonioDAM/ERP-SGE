package com.sge.erp.view.reports;

import com.jfoenix.controls.JFXListView;
import com.sge.erp.model.Staff;
import com.sge.erp.model.Task;
import com.sge.erp.persistence.ManagerStaff;
import com.sge.erp.persistence.ManagerTask;
import com.sge.erp.utility.EfficentStaffExcel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class EfficientworkersController {

    private ArrayList<Staff> staffs;
    private ArrayList<Task> tasks;
    private ManagerStaff ms;
    private ReportsController rc;
    private ManagerTask mk;

    @FXML
    private JFXListView<AnchorPane> list;


    public void loadAll() {
        try {
            staffs = ms.getStaffs();
            tasks = mk.getTasks();
            loadList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadList() {

        try {
            int completada = 0;
            list.getItems().clear();


            for (Staff s : staffs) {
                completada = 0;

                for (Task t : tasks) {

                    if (s.getDni().equalsIgnoreCase(t.getDni())) {
                        if (t.getState().equalsIgnoreCase("completada")) {
                            completada++;
                        }
                    }
                }
                if (completada>=1) {


                    FXMLLoader loader = new FXMLLoader(getClass().getResource("efficientworkers_card.fxml"));
                    AnchorPane card = loader.load();
                    EfficientWorkersCardController cc = loader.getController();

                    cc.getlEmployeeName().setText(s.getName());
                    cc.getlEmployeeSurname().setText(s.getSurname());
                    cc.getlDNI().setText(s.getDni());
                    cc.getlJob().setText(s.getJob());
                    cc.getlTaskCompleted().setText("Tareas Completadas: " + completada);

                    list.getItems().add(card);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setMs(ManagerStaff ms) {
        this.ms = ms;
    }

    public void setMk(ManagerTask mk) {
        this.mk = mk;
    }

    public void setPc(ReportsController rc) {
        this.rc = rc;
    }

    @FXML
    void jfxbSave(MouseEvent event) {
        try {
            EfficentStaffExcel ese = new EfficentStaffExcel();
            ese.create();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
