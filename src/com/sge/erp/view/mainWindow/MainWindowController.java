package com.sge.erp.view.mainWindow;

import com.sge.erp.model.Project;
import com.sge.erp.view.home.HomeController;
import com.sge.erp.view.login.LoginController;
import com.sge.erp.view.project.ProjectController;
import com.sge.erp.view.project.ResumeProjectController;
import com.sge.erp.view.projects.ProjectsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        windows = new HashMap();

        back = new HashMap<>();
        back.put("../projects/projects", "../home/home");
        back.put("../employees/employees", "../home/home");
        back.put("../clients/clients", "../home/home");
        back.put("../project/project", "../projects/projects");
        back.put("../reports/reports", "../home/home");

        loadUI("../login/login");
    }

    private HashMap<String, String> back;
    private HashMap windows;
    private String lastWindow;
    private Project selectedProject;

    @FXML
    private BorderPane mainWindow;

    @FXML
    private Label titleWindow;


    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) mainWindow.getScene().getWindow();
        stage.close();
    }

    @FXML
    void minimize(MouseEvent event) {
        Stage stage = (Stage) mainWindow.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void goBack(MouseEvent event) {
        loadUI(back.get(lastWindow));
        titleWindow.setText(back.get(lastWindow));
    }

    @FXML
    void goHome(MouseEvent event) {
        loadUI("../home/home");
        titleWindow.setText("Home");
    }

    public void loadUI(String ui) {
        Parent root = null;

        if (windows.containsKey(ui)) {
            root = (Parent) windows.get(ui);

        } else {
            try {

                if (!ui.equalsIgnoreCase("../project/project")){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                    root = loader.load();

                    switch (ui) {
                        case "../home/home":
                            HomeController hc = loader.getController();
                            hc.setMainController(this);
                            break;

                        case "../login/login":

                            LoginController lc = loader.getController();
                            lc.setMainController(this);
                            break;

                        case "../projects/projects":

                            ProjectsController psc = loader.getController();
                            psc.setMwc(this);
                            break;

                    }
                } else {
                    FXMLLoader loader3 = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                    loader3.setControllerFactory(c -> {return  new ProjectController(selectedProject);});
                    root = loader3.load();
                    ProjectController pc = loader3.getController();

                    pc.setMwc(this);
                    pc.setSelectedProject(selectedProject);

                }

                if (!ui.equalsIgnoreCase("../project/project")){
                    windows.put(ui, root);
            }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        mainWindow.setCenter(root);
        lastWindow = ui;
    }

    public Label getTitleWindow() {
        return titleWindow;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }
}

