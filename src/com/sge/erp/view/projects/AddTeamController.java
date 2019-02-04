package com.sge.erp.view.projects;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.sge.erp.model.Project;
import com.sge.erp.model.Staff;
import com.sge.erp.model.Staff_Team;
import com.sge.erp.model.Team;
import com.sge.erp.persistence.ManagerProjects;
import com.sge.erp.persistence.ManagerStaff;
import com.sge.erp.persistence.ManagerStaff_Team;
import com.sge.erp.persistence.ManagerTeam;
import com.sge.erp.utility.DialogCreator;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class AddTeamController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            ms = new ManagerStaff();
            dg = new DialogCreator(container);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        JFXTreeTableColumn<Employee, String> name = new JFXTreeTableColumn<>("Nombre");
        name.setPrefWidth(190);
        name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Employee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employee, String> param) {
                return param.getValue().getValue().name;
            }
        });


        JFXTreeTableColumn<Employee, String> job2 = new JFXTreeTableColumn<>("Puesto");
        job2.setPrefWidth(190);
        job2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Employee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employee, String> param) {
                return param.getValue().getValue().job;
            }
        });

        JFXTreeTableColumn<Employee, String> name2 = new JFXTreeTableColumn<>("Nombre");
        name2.setPrefWidth(190);
        name2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Employee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employee, String> param) {
                return param.getValue().getValue().name;
            }
        });

        JFXTreeTableColumn<Employee, String> job = new JFXTreeTableColumn<>("Puesto");
        job.setPrefWidth(190);
        job.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Employee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employee, String> param) {
                return param.getValue().getValue().job;
            }
        });

        employees = FXCollections.observableArrayList();
        team = FXCollections.observableArrayList();

        ArrayList<Staff> staffs = new ArrayList<>();
        try {
            staffs = ms.getStandbyEmployees();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Staff s : staffs) {

            Employee e = new Employee(
                    s.getSurname() + ", " + s.getName(),
                    s.getJob());
            employees.add(e);
        }

        TreeItem<Employee> root = new RecursiveTreeItem<Employee>(employees, RecursiveTreeObject::getChildren);
        TreeItem<Employee> root2 = new RecursiveTreeItem<Employee>(team, RecursiveTreeObject::getChildren);

        tableEmployees.getColumns().setAll(name, job);
        tableEmployees.setRoot(root);
        tableEmployees.setShowRoot(false);

        tableTeam.getColumns().setAll(name2, job2);
        tableTeam.setRoot(root2);
        tableTeam.setShowRoot(false);

        fieldFilterEmployeess.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                tableEmployees.setPredicate(new Predicate<TreeItem<Employee>>() {
                    @Override
                    public boolean test(TreeItem<Employee> employee) {
                        Boolean flag = employee.getValue().getName().contains(newValue);
                        return flag;
                    }
                });
            }
        });

        fieldFilterTeam.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                tableTeam.setPredicate(new Predicate<TreeItem<Employee>>() {
                    @Override
                    public boolean test(TreeItem<Employee> employee) {
                        Boolean flag = employee.getValue().getName().contains(newValue);
                        return flag;
                    }
                });
            }
        });

    }

    private ManagerStaff ms;
    private ManagerTeam mt;
    private ManagerProjects mp;
    private ManagerStaff_Team mst;

    ObservableList<Employee> employees;
    ObservableList<Employee> team;
    private ProjectsController pc;
    private DialogCreator dg;

    @FXML
    private StackPane container;

    @FXML
    private JFXTreeTableView<Employee> tableEmployees;

    @FXML
    private JFXTreeTableView<Employee> tableTeam;

    @FXML
    private JFXTextField fieldFilterEmployeess;

    @FXML
    private JFXTextField fieldFilterTeam;

    @FXML
    private JFXTextField jtfTeamName;

    @FXML
    private JFXTextField jtfProjectName;

    @FXML
    void pasarEmpleado(MouseEvent event) {

        //TreeItem<Employee> employee = tableEmployees.getSelectionModel().getSelectedItem();
        //employee.getParent().getChildren().remove(employee);

        int index = tableEmployees.getSelectionModel().getSelectedIndex();
        team.add(employees.get(index));
        employees.remove(index);

    }

    @FXML
    void quitarEmpleado(MouseEvent event) {

        int index = tableTeam.getSelectionModel().getSelectedIndex();
        employees.add(team.get(index));
        team.remove(index);
    }

    class Employee extends RecursiveTreeObject<Employee> {

        StringProperty name;
        StringProperty job;

        public Employee(String name, String job) {
            this.name = new SimpleStringProperty(name);
            this.job = new SimpleStringProperty(job);
        }

        public String getName() {
            return name.get();
        }

        public StringProperty nameProperty() {
            return name;
        }

        public String getJob() {
            return job.get();
        }

        public StringProperty jobProperty() {
            return job;
        }
    }

    @FXML
    void addTeam(MouseEvent event) {
        try {
            if (fieldValidation()){
                Project p = mp.getProject(jtfProjectName.getText());
                Team t1 = new Team(p.getId_project(), jtfTeamName.getText());
                mt.insertTeam(t1);
                Team t2 = mt.getTeamByName(jtfTeamName.getText());
                for (Employee e : team){
                    String[] completeName = e.getName().split(",");
                    Staff staff = ms.getStaffByNameSurnameJob(completeName[1].trim(),completeName[0], e.getJob());
                    System.out.println(staff.getDni());
                    Staff_Team st = new Staff_Team(t2.getId_team(), staff.getDni());
                    mst.insertStaff_Team(st);
                }
                pc.reloadProjectlist();
                pc.loadUI("team_list");
                pc.getDg().showDialog(new Text("Éxito"),
                        new Text("El Equipo ha sido agregado con éxito a la base de datos."));
                pc.loadUI("team_list");
            } else {
                dg.showDialog(new Text("Error en los campos"),
                        new Text("Alguno, o varios, de los campos no están correctamente rellenos.\n\n" +
                                "Intentelo de nuevo."));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cancel(MouseEvent event) {
        pc.loadUI("team_list");
    }

    public boolean fieldValidation(){
        String teamName = jtfTeamName.getText();
        String projectName = jtfProjectName.getText();

        if (teamName.trim().length()==0 || projectName.trim().length()==0) {
            return false;
        } else {
            return true;
        }
    }

    public void setMt(ManagerTeam mt) {
        this.mt = mt;
    }

    public void setPc(ProjectsController pc) {
        this.pc = pc;
    }

    public void setMp(ManagerProjects mp) {
        this.mp = mp;
    }

    public void setMst(ManagerStaff_Team mst) {
        this.mst = mst;
    }
}

