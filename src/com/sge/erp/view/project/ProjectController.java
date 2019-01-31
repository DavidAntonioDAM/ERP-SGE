package com.sge.erp.view.project;

import com.sge.erp.model.Project;
import com.sge.erp.view.mainWindow.MainWindowController;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ProjectController {


    private MainWindowController mwc;
    private Project selectedProject;

    @FXML
    private AnchorPane container;

    @FXML
    void addClient(MouseEvent event) {

    }

    @FXML
    void loadAll(MouseEvent event) {

    }

    public MainWindowController getMwc() {
        return mwc;
    }

    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

    public void setMwc(MainWindowController mwc) {
        this.mwc = mwc;
    }
}
