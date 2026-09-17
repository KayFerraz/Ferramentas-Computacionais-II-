package unoeste.fipp.photoshopfx.util;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class ProcessadorImagem {
    static public Image converterTonsCinza(Image image){
        BufferedImage bufferedImage;
        bufferedImage= SwingFXUtils.fromFXImage(image,null);
        int pixel[]={0,0,0,0};
        WritableRaster writableRaster=bufferedImage.getRaster();
        int tonsCinza;
        for(int x=0; x< image.getWidth(); x++)
            for(int y=0; y< image.getHeight(); y++){
                writableRaster.getPixel(x,y,pixel);
                tonsCinza=(int)(pixel[0]*0.229+pixel[1]*0.587+pixel[2]*0.114);
                pixel[0]=pixel[1]=pixel[2]=tonsCinza;
                writableRaster.setPixel(x,y,pixel);
            }
        image=SwingFXUtils.toFXImage(bufferedImage,null);
        return image;
    }
    static public Image onPretoBranco(Image image){
        BufferedImage bufferedImage;
        bufferedImage= SwingFXUtils.fromFXImage(image,null);
        for(int i=0; i<image.getWidth(); i++){
            for(int j=0; j<image.getHeight(); j++){
                Color color=new Color(bufferedImage.getRGB(i,j));
                int rc=color.getRed();
                int gc=color.getGreen();
                int bc=color.getBlue();
                int bw= (rc+gc+bc)/3;
                bufferedImage.setRGB(i,j,new Color (bw,bw,bw,bw).getRGB());
            }
        }
        image=SwingFXUtils.toFXImage(bufferedImage,null);
        return image;
    }
    static public Image onEspelharHorizontal(Image image) {

        BufferedImage bufferedImage;
        bufferedImage = SwingFXUtils.fromFXImage(image, null);

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        for (int i = 0; i < width / 2; i++) {
            for (int j = 0; j < height; j++) {

                int pixelEsquerda = bufferedImage.getRGB(i, j);
                int pixelDireita = bufferedImage.getRGB(width - 1 - i, j);

                bufferedImage.setRGB(i, j, pixelDireita);
                bufferedImage.setRGB(width - 1 - i, j, pixelEsquerda);
            }
        }

        image = SwingFXUtils.toFXImage(bufferedImage, null);

        return image;
    }
    static public Image onEspelharVertical(Image image) {

        BufferedImage bufferedImage;
        bufferedImage = SwingFXUtils.fromFXImage(image, null);

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height/2; j++) {

                int pixelCima = bufferedImage.getRGB(i, j);
                int pixelBaixo = bufferedImage.getRGB(i, height - 1 - j);

                bufferedImage.setRGB(i, j, pixelBaixo);
                bufferedImage.setRGB(i, height - 1 - j, pixelCima);
            }
        }

        image = SwingFXUtils.toFXImage(bufferedImage, null);

        return image;
    }
}
