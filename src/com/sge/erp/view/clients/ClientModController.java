package com.sge.erp.view.clients;

import com.jfoenix.controls.JFXTextField;
import com.sge.erp.model.Client;
import com.sge.erp.persistence.ManagerClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientModController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

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
            if (fieldValidation()){
                if (phoneValidation()){
                    if (nifValidate()){
                        mc.updateClient(c, clientToModify.getNif());

                        cc.reloadList();
                        cc.loadUI("clients_list");

                        // POP UP CLIENTE MODIFICADO
                    } else {
                        // POP UP NIF INCORRECTO
                    }
                } else {
                    // POP UP TELEFONO INCORRECTO
                }
            } else {
                // POP UP RELLENAR CAMPOS
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    private boolean nifValidate() {
        String nif = jtfNIF.getText();

        if (nif.length() != 9 || !Character.isLetter(nif.charAt(0)) || !Character.isLetter(nif.charAt(8))) {
            return false;
        } else if (numbersOnly(nif)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean numbersOnly(String dni) {
        String number;
        String nifTMP = "";

        for (int i = 1; i < dni.length() - 1; i++){
            number = dni.substring(i, i+1);

            for (int j = 0; j < 10; j++){
                if (number.equals(String.valueOf(j))){
                    nifTMP += String.valueOf(j);
                }
            }
        }

        if (nifTMP.length() != 7) {
            return false;
        } else {
            return true;
        }
    }

    private boolean phoneValidation(){
        String phone = jtfPhone.getText();

        if (phone.length() != 9){
            return false;
        } else {
            return true;
        }
    }

    private boolean fieldValidation() {
        String name = jtfName.getText();
        String email = jtfEmail.getText();
        String address = jtfAddress.getText();

        if (name.length() == 0 || email.length() == 0 || address.length() == 0){
            return false;
        } else {
            return true;
        }
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
