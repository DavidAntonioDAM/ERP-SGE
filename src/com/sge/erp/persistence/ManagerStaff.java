package com.sge.erp.persistence;

import com.sge.erp.model.Client;
import com.sge.erp.model.Staff;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ManagerStaff extends AdminDataBase {

    public ManagerStaff() throws ClassNotFoundException {
        super();
    }

    public void insertStaff(Staff sf) throws SQLException {
        verifyConnection();
        String sql = "INSERT INTO staff (dni, name, surname, job) VALUES (?, ?, ?, ?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, sf.getDni());
        ps.setString(2, sf.getName());
        ps.setString(3, sf.getSurname());
        ps.setString(4, sf.getJob());
        ps.executeUpdate();

    }

    public void updateStaff(String dni, Staff sf) throws SQLException {
        verifyConnection();
        String sql = "UPDATE staff SET dni = '" + sf.getDni() + "', name = '" + sf.getName() + "' , surname = '" + sf.getSurname() + "', job = '" + sf.getJob() + "'  WHERE dni =  '" + dni + "';";
        Statement st = connection.createStatement();
        st.executeUpdate(sql);
        st.close();
    }

    public Staff getStaff(String dni) throws SQLException {

        verifyConnection();
        String sql = "SELECT * FROM staff WHERE dni = '" + dni + "';";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.next();

        Staff sfs = new Staff(
                rs.getString("dni"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("job"));


        rs.close();
        st.close();

        return sfs;

    }

    public ArrayList<Staff> getStaffsFilter(String nameFilter) throws SQLException {
        ArrayList<Staff> staffs = new ArrayList<>();
        verifyConnection();

        String sql = "SELECT * FROM staff WHERE name LIKE '%" + nameFilter + "%';";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            staffs.add(new Staff(
                    rs.getString("dni"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("job")));

        }

        rs.close();
        st.close();
        return staffs;
    }

    public ArrayList<Staff> getStaffs() throws SQLException {
        ArrayList<Staff> sfs = new ArrayList<>();
        verifyConnection();
        String sql = "SELECT * FROM staff;";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {

            sfs.add(new Staff(
                    rs.getString("dni"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("job")));

        }
        rs.close();
        st.close();

        return sfs;

    }

    public void deleteStaff(String dni) throws SQLException {
        verifyConnection();
        String sql = "DELETE FROM staff WHERE dni = '" + dni + "'";
        Statement st = connection.createStatement();
        st.executeUpdate(sql);
        st.close();
    }

    public ArrayList<Staff> getStandbyEmployees() throws SQLException {

        ArrayList<Staff> sfs = new ArrayList<>();
        verifyConnection();
        String sql = "select * from staff where dni != any (SELECT dni FROM `task` WHERE dni != '' OR dni is not null);";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {

            sfs.add(new Staff(
                    rs.getString("dni"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("job")));

        }
        rs.close();
        st.close();

        return sfs;
    }
}
