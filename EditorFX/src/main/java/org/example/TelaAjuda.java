package org.example;

import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class TelaAjuda extends BorderPane {
    private TextArea textArea;
    public TelaAjuda() {
        super();
        inicializarComponentes();
    }

    private void inicializarComponentes() {

        textArea=new TextArea("Para aumentar o texto utilize o botão +\nPara diminuir o texto utilize o botão -\n Para mudar a cor, apenas clice em cima do modal e selecione a cor desejada\nPara salvar um arquivo já existente, utilize o botão SALVAR\nPara salvar um arquivo NÃO existente, utilize o botão SALVAR COMO\nPara abrir um arquivo existente, utilize o botão ABRIR");
        this.setCenter(textArea);
        textArea.setEditable(false);}
}
