package com.sge.erp.utility;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public class DialogCreator {

    StackPane panel;

    public DialogCreator(StackPane panel) {
        this.panel = panel;
    }

    public void showDialog(Text title, Text text) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(title);
        content.setBody(text);

        JFXButton done = new JFXButton("Aceptar");
        done.setButtonType(JFXButton.ButtonType.RAISED);
        done.setStyle("-fx-background-color: #17212B;");
        done.setPrefHeight(32);
        done.setRipplerFill(Paint.valueOf("#ffffff"));
        done.setTextFill(Paint.valueOf("#ffffff"));

        content.setActions(done);

        final JFXDialog dialog = new JFXDialog(panel, content, JFXDialog.DialogTransition.TOP);
        done.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });

        dialog.show();
    }
}
