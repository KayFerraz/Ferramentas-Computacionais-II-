package unoeste.fipp.photoshopfx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.rmi.server.ExportException;

public class MainController {

    public BorderPane painel;

    public void onAbrir(ActionEvent actionEvent) throws Exception {
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("image-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("");
        //fica subordinado ao stage principal
        stage.initOwner(painel.getScene().getWindow());
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();
    }

    public void onFechar(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void onSair(ActionEvent actionEvent) {
        Platform.exit();
    }
}
