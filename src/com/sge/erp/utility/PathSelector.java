package com.sge.erp.utility;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class PathSelector {

    public File selectPath(Stage stage){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel", "*.xlsx"),
                new FileChooser.ExtensionFilter("Todos", "*.*"));
        File file = fc.showSaveDialog(stage);
        return file;
    }
}
