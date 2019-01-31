package com.sge.erp.view.project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class TaskCardController {


    @FXML
    private Label jlNameTask;

    @FXML
    private TextArea jtaDescription;

    @FXML
    private Label jlState;

    @FXML
    private Label jlMember;

    public Label getJlNameTask() {
        return jlNameTask;
    }

    public void setJlNameTask(Label jlNameTask) {
        this.jlNameTask = jlNameTask;
    }

    public TextArea getJtaDescription() {
        return jtaDescription;
    }

    public void setJtaDescription(TextArea jtaDescription) {
        this.jtaDescription = jtaDescription;
    }

    public Label getJlState() {
        return jlState;
    }

    public void setJlState(Label jlState) {
        this.jlState = jlState;
    }

    public Label getJlMember() {
        return jlMember;
    }

    public void setJlMember(Label jlMember) {
        this.jlMember = jlMember;
    }
}
