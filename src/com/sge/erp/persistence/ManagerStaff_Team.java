package com.sge.erp.persistence;

import com.sge.erp.model.Staff_Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ManagerStaff_Team extends AdminDataBase {

    public ManagerStaff_Team() throws ClassNotFoundException {
        super();
    }

    public void insertStaff_Team(Staff_Team st) throws SQLException {

        verifyConnection();
        String sql = "INSERT INTO staff_team (id_team, dni) VALUES (?, ?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, st.getId_team());
        ps.setString(2, st.getDni());
        ps.executeUpdate();

    }

    public Staff_Team getStaff_Team(int id_Team) throws SQLException {
        verifyConnection();

        String sql = "SELECT * FROM staff_team WHERE id_team = '" + id_Team + "';";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        rs.next();

        Staff_Team steam = new Staff_Team(
                rs.getInt("id_team"),
                rs.getString("dni"));


        rs.close();
        st.close();

        return steam;
    }

    public ArrayList<Staff_Team> getStaff_Teams() throws SQLException {
        ArrayList<Staff_Team> steam = new ArrayList<>();
        verifyConnection();

        String sql = "SELECT * FROM staff_team;";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            steam.add(new Staff_Team(
                    rs.getInt("id_team"),
                    rs.getString("dni")));

        }

        rs.close();
        st.close();

        return steam;
    }

    public void updateStaff_Team(Staff_Team stm, int id_team) throws SQLException {
        verifyConnection();
        String sql = "UPDATE staff_team SET id_team = '" + stm.getId_team() + "', dni = '" + stm.getDni() + "' WHERE id_team = '" + id_team + "';";
        Statement st = connection.createStatement();
        st.executeUpdate(sql);
        st.close();

    }

    public void deleteStaff_Team(Staff_Team sTeam) throws SQLException {
        verifyConnection();
        String sql = "DELETE FROM staff_team WHERE id_team = " + sTeam.getId_team() + " AND dni = '" + sTeam.getDni() + "';";
        Statement st = connection.createStatement();
        st.executeUpdate(sql);
        st.close();

    }

}
