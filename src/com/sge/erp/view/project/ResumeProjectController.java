package com.sge.erp.view.project;

import com.sge.erp.model.Client;
import com.sge.erp.model.Project;
import com.sge.erp.model.Task;
import com.sge.erp.persistence.ManagerClient;
import com.sge.erp.persistence.ManagerTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ResumeProjectController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<Task> tasks = new ArrayList<>();
        try {
            mc = new ManagerClient();
            tasks = mt.getProjectTask(selectedProject.getId_project());

            mc = new ManagerClient();
            Client c = mc.getClient(selectedProject.getNif_client());

            Client.setText(c.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int completed = 0;
        int toDo = 0;
        int pause = 0;
        int doing = 0;

        for (Task t:tasks) {
            switch (t.getState()){
                case "Pendiente":
                    toDo++;
                    break;
                case "En Curso":
                    doing++;
                    break;
                case "Completada":
                    completed++;
                    break;
                case "Pausada":
                    pause++;
                    break;
            }
        }

        dataChart = FXCollections.observableArrayList(
                new PieChart.Data("Completadas", completed),
                new PieChart.Data("Pendientes", toDo),
                new PieChart.Data("Pausadas", pause),
                new PieChart.Data("En curso", doing)
        );

        ProjectChart.setData(dataChart);

        double projectComplete = 0;

        try{
            projectComplete = ((100 * completed) / tasks.size()) * 0.01;
        } catch (Exception e){

        }
        charPercent.setProgress(projectComplete);
        percent.setText(String.valueOf((int)(projectComplete*100))+"%");

        numberTask.setText(String.valueOf(tasks.size()));

        deliveryDate.setText(selectedProject.getDeliver_date());
    }

    private ObservableList<PieChart.Data> dataChart;
    private Project selectedProject;
    private ManagerTask mt;
    private ManagerClient mc;


    @FXML
    private PieChart ProjectChart;

    @FXML
    private Label percent;

    @FXML
    private ProgressIndicator charPercent;

    @FXML
    private Label numberTask;

    @FXML
    private Label Client;

    @FXML
    private Label deliveryDate;

    @FXML
    void SwitchColors(MouseEvent event) {
        ProjectChart.setData(dataChart);
    }

    public ResumeProjectController(Project selectedProject, ManagerTask mt) {
        this.selectedProject = selectedProject;
        this.mt = mt;
    }
}
