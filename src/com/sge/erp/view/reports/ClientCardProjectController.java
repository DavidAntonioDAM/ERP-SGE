package com.sge.erp.view.reports;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class ClientCardProjectController {

    @FXML
    private Label lClientName;

    @FXML
    private Label lAddress;

    @FXML
    private Label lNIF;

    @FXML
    private Label lTelf;

    @FXML
    private Label lEmail;

    @FXML
    private Label lNumberProjects;

    public Label getlNumberProjects() {
        return lNumberProjects;
    }

    public void setlNumberProjects(Label lNumberProjects) {
        this.lNumberProjects = lNumberProjects;
    }

    public Label getlClientName() {
        return lClientName;
    }

    public void setlClientName(Label lClientName) {
        this.lClientName = lClientName;
    }

    public Label getlAddress() {
        return lAddress;
    }

    public void setlAddress(Label lAddress) {
        this.lAddress = lAddress;
    }

    public Label getlNIF() {
        return lNIF;
    }

    public void setlNIF(Label lNIF) {
        this.lNIF = lNIF;
    }

    public Label getlTelf() {
        return lTelf;
    }

    public void setlTelf(Label lTelf) {
        this.lTelf = lTelf;
    }

    public Label getlEmail() {
        return lEmail;
    }

    public void setlEmail(Label lEmail) {
        this.lEmail = lEmail;
    }
}
