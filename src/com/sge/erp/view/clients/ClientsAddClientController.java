package com.sge.erp.view.clients;

import com.jfoenix.controls.JFXTextField;
import com.sge.erp.model.Client;
import com.sge.erp.persistence.ManagerClient;
import com.sge.erp.persistence.ManagerStaff;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientsAddClientController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private ManagerClient mc;
    private ClientsController cc;

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
            mc.insertClient(c);
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

    public ManagerClient getMc() {
        return mc;
    }

    public void setMc(ManagerClient mc) {
        this.mc = mc;
    }

    public ClientsController getCc() {
        return cc;
    }

    public void setCc(ClientsController cc) {
        this.cc = cc;
    }
}
