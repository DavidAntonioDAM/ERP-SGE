package com.sge.erp.view.employees;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.sge.erp.model.Client;
import com.sge.erp.model.Staff;
import com.sge.erp.persistence.ManagerClient;
import com.sge.erp.persistence.ManagerStaff;
import com.sge.erp.view.clients.ClientCardController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeesController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }

    private ArrayList<Staff> staff;
    private ManagerStaff ms;

    @FXML
    private JFXListView<AnchorPane> list;

    @FXML
    private JFXTextField jtfSearch;

    @FXML
    void filterName(MouseEvent event) {

    }

    @FXML
    void loadAll(MouseEvent event) {

    }

    private void loadList() {

        try {

            list.getItems().clear();

            for (Staff s : staff) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("employee_card.fxml.fxml"));
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

}
