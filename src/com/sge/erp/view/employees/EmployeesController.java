package com.sge.erp.view.employees;

import com.sge.erp.persistence.ManagerStaff;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private AnchorPane listPane;
    EmployeesListController elc;

    @FXML
    void loadAll(MouseEvent event) {

    }

    public void loadUI(String ui) {
        AnchorPane root = null;

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
                case "clients_list":
                    root = listPane;

                    break;
            }
        }
        container.getChildren().setAll(root);
    }

}
