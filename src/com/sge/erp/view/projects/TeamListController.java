package com.sge.erp.view.projects;

import com.jfoenix.controls.JFXListView;
import com.sge.erp.model.Staff;
import com.sge.erp.model.Team;
import com.sge.erp.persistence.ManagerProjects;
import com.sge.erp.persistence.ManagerTeam;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class TeamListController {

    private ManagerTeam mt;
    private ManagerProjects mp;
    private ProjectsController pc;
    private ArrayList<Team> teams;

    @FXML
    private JFXListView<AnchorPane> list;

    public void loadAll(){
        try {
            teams = mt.getTeams();
            loadList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadList(){
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
                    tcc.getJtaMembers().appendText(s.getName() + " " + s.getSurname() + " (" + s.getDni() + ") ;");
                }
                list.getItems().add(card);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
