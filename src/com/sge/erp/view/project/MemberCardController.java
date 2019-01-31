package com.sge.erp.view.project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MemberCardController {

    @FXML
    private Label jlName;

    @FXML
    private Label jlTask;

    public Label getJlName() {
        return jlName;
    }

    public void setJlName(Label jlName) {
        this.jlName = jlName;
    }

    public Label getJlTask() {
        return jlTask;
    }

    public void setJlTask(Label jlTask) {
        this.jlTask = jlTask;
    }
}
