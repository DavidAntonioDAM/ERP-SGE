package com.sge.erp.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sge.erp.model.User;

public class ManagerUsers extends AdminDataBase {

    public ManagerUsers() throws ClassNotFoundException {
        super();
    }

    public void insertUsers(User u) throws SQLException {
        verifyConnection();

        String sql = "INSERT INTO users (user, password) VALUE (?, ?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, u.getUser());
        ps.setString(2, u.getPassword());
        ps.executeUpdate();

    }

    public User readUser(String user_Name) throws SQLException {

        verifyConnection();

        String sql = "SELECT * FROM users WHERE user = '" + user_Name + "';";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        rs.next();

        String usser = rs.getString(1);
        String password = rs.getString(2);
        User u = new User(usser, password);

        rs.close();
        st.close();

        return u;
    }

    public ArrayList<User> readUsers() throws SQLException {
        ArrayList<User> user = new ArrayList<>();
        verifyConnection();

        String sql = "SELECT * FROM users;";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            String usser = rs.getString(1);
            String password = rs.getString(2);
            user.add(new User(usser, password));
        }

        rs.close();
        st.close();

        return user;
    }

    public void updateUsers(User u) throws SQLException {

        verifyConnection();
        String sql = "UPDATE users SET user = '" + u.getUser() + "', password = '" + u.getPassword() + "' WHERE user = '" + u.getUser() + "';";
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
