package com.sge.erp.view.clients;

import com.jfoenix.controls.JFXTextField;
import com.sge.erp.model.Client;
import com.sge.erp.persistence.ManagerClient;
import com.sge.erp.persistence.ManagerStaff;
import com.sge.erp.utility.DialogCreator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientsAddClientController implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dg = new DialogCreator(container);
    }

    private ManagerClient mc;
    private ClientsController cc;
    private DialogCreator dg;

    @FXML
    private StackPane container;

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
                        mc.insertClient(c);

                        cc.reloadList();

                        cc.loadUI("clients_list");
                        cc.getDc().showDialog(new Text("Éxito"),
                                new Text("El empleado ha sido añadido con éxito."));

                    } else {
                        dg.showDialog(new Text("Error con el NIF"),
                                new Text("El NIF del cliente no está puesto correctamente.\n\n" +
                                        "Intentelo de nuevo."));
                    }
                } else {
                    dg.showDialog(new Text("Error con el teléfono"),
                            new Text("El teléfono del cliente no está puesto correctamente.\n\n" +
                                    "Intentelo de nuevo."));
                }
            } else {
                dg.showDialog(new Text("Error en los campos"),
                        new Text("Alguno, o varios, de los campos no están correctamente rellenos.\n\n" +
                                "Intentelo de nuevo."));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cancel(MouseEvent event) {
        cc.loadUI("clients_list");
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
