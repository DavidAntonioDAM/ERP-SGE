package com.sge.erp.view.clients;

import com.jfoenix.controls.JFXTextField;
import com.sge.erp.model.Client;
import com.sge.erp.persistence.ManagerClient;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class ClientModController {

    private ClientsController cc;
    private ManagerClient mc;
    private Client clientToModify;

    @FXML
    private JFXTextField jtfNIF;

    @FXML
    private JFXTextField jtfName;

    @FXML
    private JFXTextField jtfEmail;

    @FXML
    private JFXTextField jtfPhone;

    @FXML
    private JFXTextField jtfAddress;

    @FXML
    void addClient(MouseEvent event) {
        Client c = new Client(
                jtfNIF.getText(),
                jtfName.getText(),
                jtfEmail.getText(),
                jtfPhone.getText(),
                jtfAddress.getText()
        );

        try {
            mc.updateClient(c, clientToModify.getNif());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cc.reloadList();
        cc.loadUI("clients_list");

    }

    @FXML
    void cancel(MouseEvent event) {
        cc.loadUI("clients_list");
    }

    public void setFields(){
        jtfNIF.setText(clientToModify.getNif());
        jtfName.setText(clientToModify.getName());
        jtfAddress.setText(clientToModify.getAddress());
        jtfEmail.setText(clientToModify.getEmail());
        jtfPhone.setText(clientToModify.getPhone());


    }

    public ClientsController getCc() {
        return cc;
    }

    public void setCc(ClientsController cc) {
        this.cc = cc;
    }

    public ManagerClient getMc() {
        return mc;
    }

    public void setMc(ManagerClient mc) {
        this.mc = mc;
    }

    public void setClientToModify(Client clientToModify) {
        this.clientToModify = clientToModify;
    }
}
