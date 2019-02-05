package com.sge.erp.persistence;

import com.sge.erp.model.Project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ManagerProjects extends AdminDataBase {

    public ManagerProjects() throws ClassNotFoundException {
        super();
    }

    public void insertProject(Project pj) throws SQLException {
        verifyConnection();
        String sql = "INSERT INTO project (nif_client, name, description, deliver_date) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, pj.getNif_client());
        ps.setString(2, pj.getName());
        ps.setString(3, pj.getDescription());
        ps.setString(4, pj.getDeliver_date());
        ps.executeUpdate();

    }

    public void updateProject(int id_project, Project pj) throws SQLException {
        verifyConnection();
        String sql = "UPDATE project SET (?, ?, ?, ?) WHERE id_project = " + id_project + ";";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, pj.getNif_client());
        ps.setString(2, pj.getName());
        ps.setString(3, pj.getDescription());
        ps.setString(4, pj.getDeliver_date());
        ps.executeUpdate();

    }

    public Project getProject(String name) throws SQLException {
        verifyConnection();
        String sql = "SELECT * FROM project WHERE name = '" + name + "';";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.next();

        Project pjs = new Project(
                rs.getInt("id_project"),
                rs.getString("nif_client"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getString("deliver_date")
        );
        st.close();
        rs.close();
        return pjs;
    }

    public String getProjectName(int id_project) throws SQLException {
        verifyConnection();
        String projectName;
        String sql = "SELECT name FROM project WHERE id_project = " + id_project + ";";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        rs.next();
        projectName = rs.getString("name");
        st.close();
        rs.close();
        return projectName;
    }

    public ArrayList<Project> getProjects() throws SQLException {
        ArrayList<Project> pjs = new ArrayList<>();
        verifyConnection();
        String sql = "SELECT * FROM project";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {

            pjs.add(new Project(
                    rs.getInt("id_project"),
                    rs.getString("nif_client"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("deliver_date")
            ));

        }
        rs.close();
        st.close();
        return pjs;

    }

    public void deleteProject(int id_project) throws SQLException {
        verifyConnection();
        String sql = "DELETE FROM project WHERE id_project = '" + id_project + "'";
        Statement st = connection.createStatement();
        st.executeUpdate(sql);
        st.close();

    }

    public ArrayList<Project> getProjectsFilter(String nameFilter) throws SQLException {
        ArrayList<Project> projects = new ArrayList<>();
        verifyConnection();

        String sql = "SELECT * FROM project WHERE name LIKE '%" + nameFilter + "%';";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            projects.add(new Project(
                    rs.getInt("id_project"),
                    rs.getString("nif_client"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("deliver_date")
            ));
        }

        rs.close();
        st.close();
        return projects;
    }

    public ArrayList<Project> getProjectsNoTeam(int id_project) throws SQLException {
        ArrayList<Project> projects = new ArrayList<>();
        verifyConnection();

        String sql = "SELECT * FROM project WHERE id_project = " + id_project + " OR id_project NOT IN (SELECT id_project FROM team)";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            projects.add(new Project(
                    rs.getInt("id_project"),
                    rs.getString("nif_client"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("deliver_date")
            ));
        }

        rs.close();
        st.close();
        return projects;
    }

    public ArrayList<Project> getProjectwithmoreTask() throws SQLException {
        ArrayList<Project> projects = new ArrayList<>();
        verifyConnection();

        String sql = "SELECT p.* FROM project p, task t WHERE p.id_project = t.id_project GROUP BY p.id_project ORDER BY COUNT(p.id_project) DESC;";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);


        while (rs.next()) {

            projects.add(new Project(
                    rs.getInt("id_project"),
                    rs.getString("nif_client"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("deliver_date")
            ));
        }


        rs.close();
        st.close();
        return projects;
    }

}
