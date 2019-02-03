package com.sge.erp.view.projects;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.sge.erp.model.Project;
import com.sge.erp.model.Staff;
import com.sge.erp.model.Staff_Team;
import com.sge.erp.model.Team;
import com.sge.erp.persistence.ManagerProjects;
import com.sge.erp.persistence.ManagerStaff;
import com.sge.erp.persistence.ManagerStaff_Team;
import com.sge.erp.persistence.ManagerTeam;
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
import javafx.util.Callback;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class ModTeamController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            ms = new ManagerStaff();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        JFXTreeTableColumn<Employee, String> name = new JFXTreeTableColumn<>("Nombre");
        name.setPrefWidth(190);
        name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ModTeamController.Employee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employee, String> param) {
                return param.getValue().getValue().name;
            }
        });


        JFXTreeTableColumn<Employee, String> job2 = new JFXTreeTableColumn<>("Puesto");
        job2.setPrefWidth(190);
        job2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ModTeamController.Employee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employee, String> param) {
                return param.getValue().getValue().job;
            }
        });

        JFXTreeTableColumn<Employee, String> name2 = new JFXTreeTableColumn<>("Nombre");
        name2.setPrefWidth(190);
        name2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ModTeamController.Employee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employee, String> param) {
                return param.getValue().getValue().name;
            }
        });

        JFXTreeTableColumn<Employee, String> job = new JFXTreeTableColumn<>("Puesto");
        job.setPrefWidth(190);
        job.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ModTeamController.Employee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employee, String> param) {
                return param.getValue().getValue().job;
            }
        });

        employees = FXCollections.observableArrayList();
        team = FXCollections.observableArrayList();
        newInTeam = FXCollections.observableArrayList();
        outOfTeam = FXCollections.observableArrayList();


        ArrayList<Staff> staffs = new ArrayList<>();
        ArrayList<Staff> teamList = new ArrayList<>();

        try {
            staffs = ms.getStandbyEmployees();
            teamList = ms.getStaffsTeam(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Staff s : staffs) {

            Employee e = new Employee(
                    s.getSurname() + ", " + s.getName(),
                    s.getJob());
            employees.add(e);
        }

        for (Staff s : teamList) {

            Employee e = new Employee(
                    s.getSurname() + ", " + s.getName(),
                    s.getJob());
            team.add(e);
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
                tableEmployees.setPredicate(new Predicate<TreeItem<ModTeamController.Employee>>() {
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
                tableTeam.setPredicate(new Predicate<TreeItem<ModTeamController.Employee>>() {
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
    ArrayList<Staff> allStaff;
    private Team selectedTeam;

    ObservableList<Employee> employees;
    ObservableList<Employee> team;
    ObservableList<Employee> outOfTeam;
    ObservableList<Employee> newInTeam;
    private ProjectsController pc;

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
    private JFXComboBox<String> jcbProjectsName;

    @FXML
    void pasarEmpleado(MouseEvent event) {

        int index = tableEmployees.getSelectionModel().getSelectedIndex();
        team.add(employees.get(index));
        newInTeam.add(employees.get(index));
        employees.remove(index);

    }

    @FXML
    void quitarEmpleado(MouseEvent event) {

        int index = tableTeam.getSelectionModel().getSelectedIndex();
        employees.add(team.get(index));
        outOfTeam.add(team.get(index));
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
    void cancel(MouseEvent event) {
        pc.loadUI("team_list");
    }

    @FXML
    void modifyTeam(MouseEvent event) {
        try {
            if(jcbProjectsName.getValue().trim().length() > 0 && jtfTeamName.getText().trim().length() > 0){
                Project p = mp.getProject(jcbProjectsName.getValue());
                Team t1 = new Team(selectedTeam.getId_team(),p.getId_project(), jtfTeamName.getText());
                mt.updateTeam(t1);
                Team t2 = mt.getTeamByName(jtfTeamName.getText());
                if (outOfTeam.size() > 0) {
                    for (Employee e : outOfTeam) {
                        String[] completeName = e.getName().split(",");
                        Staff staff = ms.getStaffByNameSurnameJob(completeName[1].trim(), completeName[0], e.getJob());
                        Staff_Team st = new Staff_Team(t2.getId_team(), staff.getDni());
                        mst.deleteStaff_Team(st);
                    }
                }

                if (newInTeam.size() > 0) {
                    for (Employee e : newInTeam) {
                        String[] completeName = e.getName().split(",");
                        Staff staff = ms.getStaffByNameSurnameJob(completeName[1].trim(), completeName[0], e.getJob());
                        Staff_Team st = new Staff_Team(t2.getId_team(), staff.getDni());
                        mst.insertStaff_Team(st);
                    }
                }
                pc.reloadProjectlist();
                pc.loadUI("team_list");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setFields(){

       // try {

            jtfTeamName.setText(selectedTeam.getName());
            jcbProjectsName.setValue("HOLA");
       /* } catch (SQLException e) {
            e.printStackTrace();
        }*/
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

    public ManagerStaff getMs() {
        return ms;
    }

    public void setMs(ManagerStaff ms) {
        this.ms = ms;
    }

    public Team getSelectedTeam() {
        return selectedTeam;
    }

    public void setSelectedTeam(Team selectedTeam) {
        this.selectedTeam = selectedTeam;
    }

    public ArrayList<Staff> getAllStaff() {
        return allStaff;
    }

    public void setAllStaff(ArrayList<Staff> allStaff) {
        this.allStaff = allStaff;
    }

    public JFXComboBox<String> getJcbProjectsName() {
        return jcbProjectsName;
    }

    public void setJcbProjectsName(JFXComboBox<String> jcbProjectsName) {
        this.jcbProjectsName = jcbProjectsName;
    }
}
