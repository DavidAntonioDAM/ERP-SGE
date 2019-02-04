package com.sge.erp.persistence;

import com.sge.erp.model.Task;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ManagerTask extends AdminDataBase {

    public ManagerTask() throws ClassNotFoundException {
        super();
    }

    public void insertTask(Task tk) throws SQLException {
        verifyConnection();
        String sql = "INSERT INTO task (id_project, dni, name, description, state) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, tk.getId_project());
        ps.setString(2, tk.getDni());
        ps.setString(3, tk.getName());
        ps.setString(4, tk.getDescription());
        ps.setString(5, tk.getState());
        ps.executeUpdate();

    }

    public void updateTask(String name, String desc, Task tk) throws SQLException {
        verifyConnection();
        String sql = "UPDATE task SET dni = '" + tk.getDni() + "' , name = '" +
                tk.getName() + "' , state = '" + tk.getState() + "' WHERE name = '" +
                name + "' AND description = '" + desc + "';";
        Statement st = connection.createStatement();
        st.executeUpdate(sql);
        st.close();
    }

    public Task getTask(String name) throws SQLException {
        Task tks;
        verifyConnection();
        String sql = "SELECT * FROM task WHERE name = '" + name + "';";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.next();

        tks = new Task(
                rs.getInt("id_task"),
                rs.getInt("id_project"),
                rs.getString("dni"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getString("state"));

        rs.close();
        st.close();
        
        return tks;
    }

    public ArrayList<Task> getTasks() throws SQLException {
        ArrayList<Task> tks = new ArrayList<>();
        verifyConnection();
        String sql = "SELECT * FROM task;";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {

            tks.add(new Task(
                    rs.getInt("id_task"),
                    rs.getInt("id_project"),
                    rs.getString("dni"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("state")));
        }
        rs.close();
        st.close();
        return tks;

    }

    public void deleteTask(int id_task) throws SQLException {
        verifyConnection();
        String sql = "DELETE FROM task WHERE id_task = '" + id_task + "';";
        Statement st = connection.createStatement();
        st.executeUpdate(sql);
        st.close();

    }

    public ArrayList<Task> getProjectTask(int id_project) throws SQLException {

        ArrayList<Task> tks = new ArrayList<>();
        verifyConnection();
        String sql = "SELECT * FROM task WHERE id_project = " + id_project + ";";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {

            tks.add(new Task(
                    rs.getInt("id_task"),
                    rs.getInt("id_project"),
                    rs.getString("dni"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("state")));
        }

        rs.close();
        st.close();
        return tks;

    }

    public String getTaskName(String dni) throws SQLException {
        String taskName = "";

        verifyConnection();
        String sql = "SELECT * FROM task WHERE dni='" + dni + "';";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        if (rs.next()) {
            taskName = rs.getString("description");
        }
        return taskName;
    }

    public ArrayList<Task> getTasksFilter(String name, int id_project) throws SQLException {
        ArrayList<Task> tasks = new ArrayList<>();
        verifyConnection();
        String sql = "SELECT * FROM task WHERE name LIKE '%" + name + "%' AND id_project=" + id_project + ";";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            tasks.add(new Task(
                    rs.getInt("id_task"),
                    rs.getInt("id_project"),
                    rs.getString("dni"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("state")));
        }

        rs.close();
        st.close();
        return  tasks;
    }

}
