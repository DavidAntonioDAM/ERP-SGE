package com.sge.erp.view.clients;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.sge.erp.model.Client;
import com.sge.erp.persistence.ManagerClient;
import com.sge.erp.view.home.HomeController;
import com.sge.erp.view.login.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
            loadUI("clients_list");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private AnchorPane container;

    private ManagerClient mc;
    private AnchorPane listPane;
    ClientListController cl;


    @FXML
    void loadAll(MouseEvent event) {
        cl.loadAll();
        loadUI("clients_list");
    }

    @FXML
    void addClient(MouseEvent event) {
        loadUI("add_client");
    }

    public void reloadList(){
        cl.loadAll();
    }

    public void loadUI(String ui) {
        AnchorPane root = null;

        if (listPane == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                root = loader.load();

                cl = loader.getController();
                cl.setMc(mc);
                cl.loadAll();

                listPane = root;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            switch (ui){
                case "clients_list":
                    root = listPane;

                    break;
                case "add_client":
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                        root = loader.load();

                        ClientsAddClientController cacc = loader.getController();
                        cacc.setMc(mc);
                        cacc.setCc(this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
            }
        }

        container.getChildren().setAll(root);
    }
}
