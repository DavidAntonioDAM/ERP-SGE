package com.sge.erp.view.clients;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
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

public class ClientListController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private ArrayList<Client> clients;
    private ManagerClient mc;

    @FXML
    private JFXListView<AnchorPane> list;

    @FXML
    private JFXTextField jtfSearch;

    void loadAll() {
        try {
            clients = mc.readClients();
            loadList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void filterName(MouseEvent event) {

        try {
            clients = mc.getClientsFilter(jtfSearch.getText());
            loadList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadList() {

        try {

            list.getItems().clear();

            for (Client c : clients) {

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ManagerClient getMc() {
        return mc;
    }

    public void setMc(ManagerClient mc) {
        this.mc = mc;
    }
}
