package com.sge.erp.view.projects;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.sge.erp.model.Staff;
import com.sge.erp.model.Team;
import com.sge.erp.persistence.ManagerProjects;
import com.sge.erp.persistence.ManagerTeam;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class TeamListController {

    private ManagerTeam mt;
    private ManagerProjects mp;
    private ProjectsController pc;
    private ArrayList<Team> teams;
    private Team teamSelected;

    @FXML
    private JFXListView<AnchorPane> list;

    @FXML
    private JFXTextField jtfSearch;

    public void loadAll(){
        try {
            teams = mt.getTeams();
            loadList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void filterTeams(MouseEvent event) {
        try {
            teams = mt.getTeamsFilter(jtfSearch.getText());
            loadList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addTeam(MouseEvent event) {
        pc.loadUI("add_team");
    }

    public void loadList(){
        int counter = 0;
        try {

            list.getItems().clear();
            for (Team t : teams){

                FXMLLoader loader = new FXMLLoader(getClass().getResource("team_card.fxml"));
                AnchorPane card = loader.load();
                TeamCardController tcc = loader.getController();

                tcc.getJlTeamName().setText(t.getName());
                String projectName = mp.getProjectName(t.getId_project());
                tcc.getJlProjectName().setText(projectName);
                ArrayList<Staff> members = mt.getMembers(t.getId_team());
                for (Staff s : members){
                    if (counter == 1) {
                        tcc.getJtaMembers1().appendText(s.getName() + " " + s.getSurname() + " (" + s.getDni() + ") \n");
                        counter = 0;
                    } else {
                        tcc.getJtaMembers().appendText(s.getName() + " " + s.getSurname() + " (" + s.getDni() + ") \n");
                        counter++;
                    }
                }
                list.getItems().add(card);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void selectTeam(MouseEvent event) {
        String team = "";

        ObservableList<AnchorPane> selected = list.getSelectionModel().getSelectedItems();

        Pane panel = (Pane)  selected.get(0).getChildren().get(0);
        team = ((Label)panel.getChildren().get(0)).getText();

        try {
            teamSelected = mt.getTeamByName(team);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pc.setTeamSelected(teamSelected);
        pc.loadUI("mod_team");
    }

    public void setMt(ManagerTeam mt) {
        this.mt = mt;
    }

    public void setPc(ProjectsController pc) {
        this.pc = pc;
    }

    public void setMp(ManagerProjects mp) {
        this.mp = mp;
    }
}
