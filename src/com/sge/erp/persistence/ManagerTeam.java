package com.sge.erp.persistence;

import com.sge.erp.model.Staff;
import com.sge.erp.model.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ManagerTeam extends AdminDataBase {

    public ManagerTeam() throws ClassNotFoundException {
        super();
    }

    public void insertTeam(Team t) throws SQLException {
        verifyConnection();
        String sql = "INSERT INTO team (id_team, id_project, name) VALUE (?, ?, ?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, t.getId_team());
        ps.setInt(2, t.getId_project());
        ps.setString(3, t.getName());
        ps.executeUpdate();

    }

    public Team getTeam(String id_Team) throws SQLException {
        verifyConnection();

        String sql = "SELECT * FROM team WHERE id_team = '" + id_Team + "';";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        rs.next();

        Team t = new Team(
                rs.getInt("id_team"),
                rs.getInt("id_project"),
                rs.getString("name"));


        rs.close();
        st.close();

        return t;
    }

    public ArrayList<Team> getTeams() throws SQLException {
        ArrayList<Team> team = new ArrayList<>();
        verifyConnection();

        String sql = "SELECT * FROM team ;";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            team.add(new Team(
                    rs.getInt("id_team"),
                    rs.getInt("id_project"),
                    rs.getString("name")));

        }

        rs.close();
        st.close();

        return team;
    }

    public ArrayList<Team> getTeamsFilter(String name) throws SQLException {
        ArrayList<Team> teams = new ArrayList<>();
        verifyConnection();

        String sql = "SELECT * FROM team WHERE name LIKE '%" + name + "%';";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            teams.add(new Team(
                    rs.getInt("id_team"),
                    rs.getInt("id_project"),
                    rs.getString("name")));

        }

        rs.close();
        st.close();

        return teams;
    }

    public void updateTeam(Team t) throws SQLException {
        verifyConnection();
        String sql = "UPDATE team SET name = '" + t.getName() + "', id_project = '" + t.getId_project() + "' WHERE id_team = '" + t.getId_team() + "';";
        Statement st = connection.createStatement();
        st.executeUpdate(sql);
        st.close();
    }

    public void deleteTeam(int id_Team) throws SQLException {
        verifyConnection();
        String sql = "DELETE FROM team WHERE id_team = '" + id_Team + "';";
        Statement st = connection.createStatement();
        st.executeUpdate(sql);
        st.close();

    }

    public ArrayList<Staff> getMembers(int id_team) throws SQLException {
        ArrayList<Staff> members = new ArrayList();
        verifyConnection();
        String sql = "SELECT s.name, s.surname, s.dni \n" +
                "FROM staff s, team t, staff_team st\n" +
                "WHERE s.dni = st.dni AND t.id_team = st.id_team AND t.id_team = " + id_team + ";";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            members.add(new Staff(
                    rs.getString("dni"),
                    rs.getString("name"),
                    rs.getString("surname")));
        }

        rs.close();
        st.close();
        return members;
    }

    public Team getTeamByName(String name) throws SQLException {
        verifyConnection();

        String sql = "SELECT * FROM team WHERE name = '" + name + "';";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        rs.next();

        Team t = new Team(
                rs.getInt("id_team"),
                rs.getInt("id_project"),
                rs.getString("name"));


        rs.close();
        st.close();

        return t;
    }

}
