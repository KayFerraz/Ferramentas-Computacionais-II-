package unoeste.fipp.photoshopfx;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import unoeste.fipp.photoshopfx.util.ProcessadorImagem;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ImageController implements Initializable {

    public ImageView imageView;
    public Image image=null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FileChooser fileChooser=new FileChooser();
        fileChooser.setInitialDirectory(new File("D:\\"));
        File imageFile = fileChooser.showOpenDialog(null);
        if(imageFile!=null) {
            image = new Image(imageFile.toURI().toString());
            // imageView.setFitWidth(image.getWidth());
            // imageView.setFitHeight(image.getHeight());
            imageView.setImage(image);
        }
    }

    public void onTonsCinza(ActionEvent actionEvent) {
        image=ProcessadorImagem.converterTonsCinza(image);
        imageView.setImage(image);
    }

    public void onPretoBranco(ActionEvent actionEvent) {
        image=ProcessadorImagem.onPretoBranco(image);
        imageView.setImage(image);
    }

    public void onEspelharHorizontal(ActionEvent actionEvent) {
        image=ProcessadorImagem.onEspelharVertical(image);
        imageView.setImage(image);
    }
}
