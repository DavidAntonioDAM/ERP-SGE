package com.sge.erp.persistence;

import com.sge.erp.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ManagerUsers extends AdminDataBase {

    public ManagerUsers() throws ClassNotFoundException {
        super();
    }

    public void insertUsers(User u) throws SQLException {
        verifyConnection();

        String sql = "INSERT INTO users (user, password) VALUES (?, ?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, u.getUser());
        ps.setString(2, u.getPassword());
        ps.executeUpdate();

    }

    public User getUser(String user_Name) throws SQLException {

        verifyConnection();

        String sql = "SELECT * FROM users WHERE user = '" + user_Name + "';";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        rs.next();

        User u = new User(rs.getString("user"), rs.getString("password"));

        rs.close();
        st.close();

        return u;
    }

    public ArrayList<User> getUsers() throws SQLException {
        ArrayList<User> user = new ArrayList<>();
        verifyConnection();

        String sql = "SELECT * FROM users;";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            user.add(new User(rs.getString("user"), rs.getString("password")));

        }

        rs.close();
        st.close();

        return user;
    }

    public void updateUsers(User u, String user) throws SQLException {

        verifyConnection();
        String sql = "UPDATE users SET user = '" + u.getUser() + "', password = '" + u.getPassword() + "' WHERE user = '" + user + "';";
        Statement st = connection.createStatement();
        st.executeUpdate(sql);
        st.close();

    }

    public void deleteUsers(String user_Name) throws SQLException {
        verifyConnection();
        String sql = "DELETE FROM users WHERE user = '" + user_Name + "';";
        Statement st = connection.createStatement();
        st.executeUpdate(sql);
        st.close();

    }

}
