package com.sge.erp.view.employees;

import com.sge.erp.model.Staff;
import com.sge.erp.persistence.ManagerStaff;
import com.sge.erp.utility.DialogCreator;
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
            dc = new DialogCreator(mainContainer);
            loadUI("employees_list");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ManagerStaff ms;
    private Staff staffSelected;
    private Parent listPane;
    private DialogCreator dc;
    EmployeesListController elc;

    @FXML
    private StackPane mainContainer;

    @FXML
    private AnchorPane container;

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
                elc.setEc(this);

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
                case "mod_employee":
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                        root =  loader.load();

                        EmployeeModController emc = loader.getController();
                        emc.setMs(ms);
                        emc.setEc(this);
                        emc.setStaffToModify(staffSelected);
                        emc.setFields();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
        container.getChildren().setAll(root);
    }

    public void setStaffSelected(Staff staffSelected) {
        this.staffSelected = staffSelected;
    }

    public DialogCreator getDc() {
        return dc;
    }
}
