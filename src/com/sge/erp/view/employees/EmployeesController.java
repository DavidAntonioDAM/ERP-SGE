package com.sge.erp.view.employees;

import com.sge.erp.persistence.ManagerStaff;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeesController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            ms = new ManagerStaff();
            loadUI("employees_list");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private AnchorPane container;

    private ManagerStaff ms;
    private Parent listPane;
    EmployeesListController elc;

    @FXML
    void loadAll(MouseEvent event) {
        elc.loadAll();
        loadUI("employees_list");
    }

    @FXML
    void addEmployee(MouseEvent event) {
        loadUI("add_employee");
    }

    public void reloadList(){
        elc.loadAll();
    }

    public void loadUI(String ui) {
        Parent root = null;

        if (listPane == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                root = loader.load();

                elc = loader.getController();
                elc.setMs(ms);
                elc.loadAll();

                listPane = root;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            switch (ui) {
                case "employees_list":
                    root = listPane;

                    break;
                case "add_employee":
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                        root =  loader.load();

                        EmployeesAddEmployeeController eaec = loader.getController();
                        eaec.setMs(ms);
                        eaec.setEc(this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
        container.getChildren().setAll(root);
    }

}
