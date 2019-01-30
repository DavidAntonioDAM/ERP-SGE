package com.sge.erp.view.projects;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.sge.erp.model.Staff;
import com.sge.erp.persistence.ManagerStaff;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProjectsAddTeamController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            ms = new ManagerStaff();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        JFXTreeTableColumn<Employee, String> name = new JFXTreeTableColumn<>("Nombre");
        name.setPrefWidth(50);
        name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Employee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employee, String> param) {
                return param.getValue().getValue().name;
            }
        });

        JFXTreeTableColumn<Employee, String> job = new JFXTreeTableColumn<>("Puesto");
        job.setPrefWidth(50);
        job.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Employee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employee, String> param) {
                return param.getValue().getValue().job;
            }
        });

        ObservableList<Employee> employees = FXCollections.observableArrayList();
        ArrayList<Staff> staffs = new ArrayList<>();
        try {
            staffs = ms.getStandbyEmployees();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Staff s:staffs) {

            Employee e = new Employee(
                    s.getName()+" "+s.getSurname(),
                    s.getJob());
            employees.add(e);
        }

        TreeItem<Employee> root = new RecursiveTreeItem<Employee>(employees,  RecursiveTreeObject::getChildren);
        tableEmployees.getColumns().setAll(name, job);
        tableEmployees.setRoot(root);
        tableEmployees.setShowRoot(false);
    }

    private ManagerStaff ms;

    @FXML
    private JFXTreeTableView<Employee> tableEmployees;

    @FXML
    private JFXTreeTableView<Employee> tableTeam;

    class Employee extends RecursiveTreeObject<Employee>{

        StringProperty name;
        StringProperty job;

        public Employee(String name, String job) {
            this.name = new SimpleStringProperty("name");
            this.job = new SimpleStringProperty("job");
        }
    }
}
