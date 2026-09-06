package org.example;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new TelaPrincipal(), 800, 600);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cancelar");
                alert.setHeaderText("Fechar Janela. Deseja sair sem salvar?");
                if(alert.showAndWait().get() == ButtonType.OK){
                    stage.close();
                }
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}

