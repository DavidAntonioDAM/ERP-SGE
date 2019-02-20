package com.sge.erp.view.reports;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class ProjectCardTaskController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private Label lProjectName;

    @FXML
    private TextArea jtaProjectDesc;

    @FXML
    private ProgressIndicator chart;

    @FXML
    private Label percent;

    @FXML
    private Label lDate;

    @FXML
    private Label lTareas;

    public Label getlTareas() {
        return lTareas;
    }

    public void setlTareas(Label lTareas) {
        this.lTareas = lTareas;
    }

    public Label getPercent() {
        return percent;
    }

    public void setPercent(Label percent) {
        this.percent = percent;
    }

    public ProgressIndicator getChart() {
        return chart;
    }

    public void setChart(ProgressIndicator chart) {
        this.chart = chart;
    }

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

    public Label getlDate() {
        return lDate;
    }

    public void setlDate(Label lDate) {
        this.lDate = lDate;
    }
}
