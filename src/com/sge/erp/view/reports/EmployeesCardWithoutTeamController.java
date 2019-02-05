package com.sge.erp.view.reports;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EmployeesCardWithoutTeamController {

    @FXML
    private Label lEmployeeName;

    @FXML
    private Label lEmployeeSurname;

    @FXML
    private Label lJob;

    @FXML
    private Label lDNI;

    public Label getlEmployeeName() {
        return lEmployeeName;
    }

    public void setlEmployeeName(Label lEmployeeName) {
        this.lEmployeeName = lEmployeeName;
    }

    public Label getlEmployeeSurname() {
        return lEmployeeSurname;
    }

    public void setlEmployeeSurname(Label lEmployeeSurname) {
        this.lEmployeeSurname = lEmployeeSurname;
    }

    public Label getlJob() {
        return lJob;
    }

    public void setlJob(Label lJob) {
        this.lJob = lJob;
    }

    public Label getlDNI() {
        return lDNI;
    }

    public void setlDNI(Label lDNI) {
        this.lDNI = lDNI;
    }
}
