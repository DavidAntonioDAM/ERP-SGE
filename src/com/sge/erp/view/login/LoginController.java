package com.sge.erp.view.login;

import com.jfoenix.controls.*;
import com.sge.erp.model.User;
import com.sge.erp.persistence.ManagerUsers;
import com.sge.erp.utility.DialogCreator;
import com.sge.erp.view.mainWindow.MainWindowController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            mu = new ManagerUsers();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        dc = new DialogCreator(login);
    }

    private ManagerUsers mu;
    private MainWindowController mainController;
    private DialogCreator dc;

    public MainWindowController getMainController() {
        return mainController;
    }

    public void setMainController(MainWindowController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private JFXTextField tfUser;

    @FXML
    private JFXPasswordField tfPass1;

    @FXML
    private StackPane login;

    @FXML
    void login(MouseEvent event) {

        User uLocal = new User(tfUser.getText(), tfPass1.getText());

        try {
            User uBBDD = mu.getUser(uLocal.getUser());

            if (uLocal.getPassword().equals(uBBDD.getPassword())) {
                mainController.loadUI("../home/home");
            } else {
                dc.showDialog(
                        new Text("Error de autentificacion"),
                        new Text("La contraseña introducida no concuerda con la del usuario.\n\n" +
                                "Por favor, intentelo de nuevo."));
            }
        } catch (SQLException e) {
            dc.showDialog(
                    new Text("Error con la base de datos"),
                    new Text("Ha habido un error con la base de datos o el usuario que intentas\n" +
                            "introducir no existe.\n\n" +
                            "Por favor, intentelo de nuevo."));

        }
    }
}
