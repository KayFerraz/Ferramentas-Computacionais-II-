package org.example;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileUtil {
    public static String abrir(File file) {
        String conteudo;
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            byte[] bytes=new byte[(int)randomAccessFile.length()];
            randomAccessFile.readFully(bytes);
            conteudo = new String(bytes);
            randomAccessFile.close();
        }
        catch (IOException e){
            conteudo="";
        }
        return conteudo;
    }
}
