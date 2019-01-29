package com.sge.erp.view.projects;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ProjectCardController {

    @FXML
    private Label lProjectName;

    @FXML
    private TextArea jtaProjectDesc;


    public Label getlProjectName() {
        return lProjectName;
    }

    public void setlProjectName(Label lProjectName) {
        this.lProjectName = lProjectName;
    }

    public TextArea getJtaProjectDesc() {
        return jtaProjectDesc;
    }

    public void setJtaProjectDesc(TextArea jtaProjectDesc) {
        this.jtaProjectDesc = jtaProjectDesc;
    }
}
