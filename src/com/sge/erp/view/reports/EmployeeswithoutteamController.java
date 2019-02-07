package com.sge.erp.view.reports;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.sge.erp.model.Staff;
import com.sge.erp.persistence.ManagerStaff;
import com.sge.erp.utility.DialogCreator;
import com.sge.erp.utility.PathSelector;
import com.sge.erp.utility.StandbyEmployeesExcel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeswithoutteamController implements Initializable {

    private ArrayList<Staff> staffs;
    private ManagerStaff ms;
    private ReportsController rc;
    private File f;

    @FXML
    private JFXListView<AnchorPane> list;


    @FXML
    private StackPane stackpaneid;

    private DialogCreator dg;


    @FXML
    private JFXTextField path;

    @FXML
    void selectPath(MouseEvent event) {

        PathSelector ps = new PathSelector();
        f = ps.selectPath(rc.getStage());
        path.setText(f.toString());
    }

    @FXML
    void jfxbSave(MouseEvent event) {
        try {
            StandbyEmployeesExcel see = new StandbyEmployeesExcel();
            if (f != null) {
                see.create(f);
            } else {
                dg.showDialog(new Text("Error Con la ruta"),
                        new Text("La ruta esta vacia.\n\n" +
                                "Intentelo de nuevo."));
            }

        } catch (ClassNotFoundException e) {
            dg.showDialog(new Text("Error en la clase"),
                    new Text("Hubo un pete en la clase .\n\n" +
                            "Intentelo de nuevo."));
        } catch (SQLException e) {
            dg.showDialog(new Text("Error Con la base de datos"),
                    new Text("Error en la base de datos.\n\n" +
                            "Intentelo de nuevo."));
        }
    }

    public void loadAll() {
        try {
            staffs = ms.getStandbyEmployees();
            loadList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadList() {

        try {

            list.getItems().clear();

            for (Staff s : staffs) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("employee_card_withoutteam.fxml"));
                AnchorPane card = loader.load();
                EmployeesCardWithoutTeamController cc = loader.getController();

                cc.getlEmployeeName().setText(s.getName());
                cc.getlEmployeeSurname().setText(s.getSurname());
                cc.getlDNI().setText(s.getDni());
                cc.getlJob().setText(s.getJob());

                list.getItems().add(card);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMs(ManagerStaff ms) {
        this.ms = ms;
    }

    public void setPc(ReportsController rc) {
        this.rc = rc;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dg = new DialogCreator(stackpaneid);
    }
}