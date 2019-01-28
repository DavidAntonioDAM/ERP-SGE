package com.sge.erp.view.mainWindow;

import com.sge.erp.view.clients.ClientsController;
import com.sge.erp.view.home.HomeController;
import com.sge.erp.view.login.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        windows = new HashMap();

        back = new HashMap<>();
        back.put("../projects/projects", "../home/home");
        back.put("../employees/employees", "../home/home");
        back.put("../clients/clients", "../home/home");
        back.put("Project", "/projects");

        loadUI("../login/login");
    }

    private HashMap<String, String> back;
    private HashMap windows;
    private String lastWindow;

    @FXML
    private BorderPane mainWindow;


    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) mainWindow.getScene().getWindow();
        stage.close();
    }

    @FXML
    void minimize(MouseEvent event) {
        Stage stage = (Stage) mainWindow.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void goBack(MouseEvent event) {
        loadUI(back.get(lastWindow));
    }

    @FXML
    void goHome(MouseEvent event) {
        loadUI("../home/home");
    }

    public void loadUI(String ui) {
        Parent root = null;

        if (windows.containsKey(ui)) {
            root = (Parent) windows.get(ui);

        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));
                root = loader.load();

                switch (ui) {
                    case "../home/home":
                        HomeController hc = loader.getController();
                        hc.setMainController(this);
                        break;

                    case "../login/login":
                        LoginController lc = loader.getController();
                        lc.setMainController(this);
                        break;
                }

                windows.put(ui, root);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        mainWindow.setCenter(root);
        lastWindow = ui;
    }
}
