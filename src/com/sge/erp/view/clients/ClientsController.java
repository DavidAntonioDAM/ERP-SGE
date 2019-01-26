package com.sge.erp.view.clients;

import com.jfoenix.controls.JFXListView;
import com.sge.erp.model.Client;
import com.sge.erp.persistence.ManagerClient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            mc = new ManagerClient();
            loadList();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private JFXListView<AnchorPane> list;

    private ArrayList<Client> clients;
    private ManagerClient mc;

    @FXML
    void cargar(MouseEvent event) throws IOException {
        loadList();
    }

    private void loadList() throws IOException {

        try {
            clients = mc.readClients();
            list.getItems().clear();

            for (Client c:clients) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("client_card.fxml"));
                AnchorPane card = loader.load();
                ClientCardController cc = loader.getController();

                cc.getlClientName().setText(c.getName());
                cc.getlAddress().setText(c.getAddress());
                cc.getlNIF().setText(c.getNif());
                cc.getlTelf().setText(c.getPhone());
                cc.getlEmail().setText(c.getEmail());

                list.getItems().add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
