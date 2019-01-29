package com.sge.erp.view.clients;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.sge.erp.model.Client;
import com.sge.erp.persistence.ManagerClient;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientListController{

    private ArrayList<Client> clients;
    private ManagerClient mc;
    private Client clientSelected;
    private ClientsController cc;

    @FXML
    private JFXListView<AnchorPane> list;

    @FXML
    private JFXTextField jtfSearch;

    @FXML
    void selectClient(MouseEvent event) {

        String dni = "";

        ObservableList<AnchorPane> prueba = list.getSelectionModel().getSelectedItems();

        Pane panel = (Pane)  prueba.get(0).getChildren().get(3);
        dni = ((Label)panel.getChildren().get(0)).getText();

        try {
            clientSelected = mc.getClient(dni);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        cc.setClientSelected(clientSelected);
        cc.loadUI("mod_client");
    }

    void loadAll() {
        try {
            clients = mc.getClients();
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

    public void setMc(ManagerClient mc) {
        this.mc = mc;
    }

    public void setCc(ClientsController cc) {
        this.cc = cc;
    }
}
