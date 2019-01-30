package com.sge.erp.view.projects;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class TeamCardController {
    @FXML
    private Label jlTeamName;

    @FXML
    private TextArea jtaMembers;

    @FXML
    private Label jlProjectName;


    public Label getJlTeamName() {
        return jlTeamName;
    }

    public void setJlTeamName(Label jlTeamName) {
        this.jlTeamName = jlTeamName;
    }

    public TextArea getJtaMembers() {
        return jtaMembers;
    }

    public void setJtaMembers(TextArea jtaMembers) {
        this.jtaMembers = jtaMembers;
    }

    public Label getJlProjectName() {
        return jlProjectName;
    }

    public void setJlProjectName(Label jlProjectName) {
        this.jlProjectName = jlProjectName;
    }
}
