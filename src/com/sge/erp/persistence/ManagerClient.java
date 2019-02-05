package com.sge.erp.persistence;

import com.sge.erp.model.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ManagerClient extends AdminDataBase {

    public ManagerClient() throws ClassNotFoundException {
        super();
    }

    public void insertClient(Client c) throws SQLException {

        verifyConnection();
        String sql = "INSERT INTO client (nif, name, email, phone, address) VALUE (?, ?, ?, ?, ?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, c.getNif());
        ps.setString(2, c.getName());
        ps.setString(3, c.getEmail());
        ps.setString(4, c.getPhone());
        ps.setString(5, c.getAddress());
        ps.executeUpdate();

    }

    public Client getClient(String nifClient) throws SQLException {
        Client client;
        verifyConnection();

        String sql = "SELECT * FROM client WHERE nif = '" + nifClient + "';";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        rs.next();

        client = new Client(rs.getString("nif"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getString("address"));

        rs.close();
        st.close();

        return client;
    }

    public ArrayList<Client> getClients() throws SQLException {
        ArrayList<Client> client = new ArrayList<>();
        verifyConnection();

        String sql = "SELECT * FROM client;";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            client.add(new Client(rs.getString("nif"), rs.getString("name"), rs.getString("email"), rs.getString("phone"), rs.getString("address")));

        }

        rs.close();
        st.close();
        return client;
    }

    public ArrayList<Client> getClientsFilter(String nameFilter) throws SQLException {
        ArrayList<Client> client = new ArrayList<>();
        verifyConnection();

        String sql = "SELECT * FROM client WHERE name LIKE '%" + nameFilter + "%';";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            String nif = rs.getString(1);
            String name = rs.getString(2);
            String email = rs.getString(3);
            String phone = rs.getString(4);
            String address = rs.getString(5);
            client.add(new Client(nif, name, email, phone, address));
        }

        rs.close();
        st.close();
        return client;
    }
    public ArrayList<Client> getClientswithmoreProjects() throws SQLException {
        ArrayList<Client> client = new ArrayList<>();
        verifyConnection();

        String sql = "SELECT c.* FROM client c inner join project p  on c.nif = p.nif_client GROUP BY c.nif ORDER BY c.nif DESC;";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            client.add(new Client(rs.getString("nif"), rs.getString("name"), rs.getString("email"), rs.getString("phone"), rs.getString("address")));

        }

        rs.close();
        st.close();
        return client;
    }
    public void updateClient(Client c, String nifClient) throws SQLException {
        verifyConnection();
        String sql = "UPDATE client SET nif = '" + c.getNif() + "', name = '" + c.getName() + "', email = '" + c.getEmail() + "', phone = '" + c.getPhone() + "', address = '" + c.getAddress() + "' WHERE nif = '" + nifClient + "';";
        Statement st = connection.createStatement();
        st.executeUpdate(sql);
        st.close();
    }

    public void deleteClient(String nifClient) throws SQLException {
        verifyConnection();
        String sql = "DELETE FROM client WHERE nif = '" + nifClient + "';";
        Statement st = connection.createStatement();
        st.executeUpdate(sql);
        st.close();
    }

}
