package org.example;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class TelaPrincipal extends BorderPane {
    private TextArea textArea;
    private Button btAumenta, btDiminui, btAjuda, btAlteraCor;
    private Button btNovo, btAbrir, btSalvar, btSalvarComo, btEstati;
    private boolean emEdicao=false;
    private double fontSize=13;
    private File file;
    Stage stage=new Stage();
    public TelaPrincipal() {
        iniciarComponentes();
        definirEventos();
    }

    private void definirEventos() {
        //tratamento dos eventos
        btNovo.setOnAction(actionEvent -> novo());
        textArea.setOnKeyPressed(event -> { emEdicao=true;});
        btAbrir.setOnAction(e -> abrir());
        btAumenta.setOnAction(e->aumentarFonte());
        btDiminui.setOnAction(e->diminuirFonte());
        btAjuda.setOnAction(e->mostrarAjuda());
        btSalvar.setOnAction(e->botaoSalvar());
        btSalvarComo.setOnAction(e->botaoSalvarComo());
        btAlteraCor.setOnAction(e->alterarCorTxt());
        btEstati.setOnAction(e-> estatistica());
    }


    private void diminuirFonte() {
        if(fontSize>=13){
            Font font = new Font(textArea.getFont().getName(),fontSize);
            textArea.setFont(font);
            fontSize--;
        }else{
            Font font = new Font(textArea.getFont().getName(),fontSize);
            textArea.setFont(font);
        }
    }

    private void mostrarAjuda() {
        Stage stage=new Stage();
        Scene scene = new Scene(new TelaAjuda(), 800, 600);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); //define que é modal, ou seja, uma janela secundária
        stage.showAndWait();
    }

    private void aumentarFonte() {
        fontSize++;
        Font font=new Font(textArea.getFont().getName(),fontSize);
        textArea.setFont(font);
    }


    private void iniciarComponentes()   {
        //painel superior
        HBox hBoxSuperior=new HBox();
        hBoxSuperior.setAlignment(Pos.CENTER_RIGHT);
        hBoxSuperior.setSpacing(15);
        hBoxSuperior.setPadding(new Insets(0,15,0,0));
        hBoxSuperior.setStyle("-fx-background-color: #ffc6f1");
        hBoxSuperior.setPrefHeight(80);
        btAumenta=new Button("+");
        btAumenta.setPrefSize(40,40);
        btDiminui=new Button("-");
        btDiminui.setPrefSize(40,40);
        btAjuda=new Button("Ajuda...");
        btAjuda.setPrefSize(70,40);
        btEstati=new Button("Estatisticas");
        btEstati.setPrefSize(80,40);
        hBoxSuperior.getChildren().addAll(btAumenta, btDiminui, btAjuda,btEstati);
        //painel inferior
        HBox hBoxInferior=new HBox();
        hBoxInferior.setAlignment(Pos.CENTER);
        hBoxInferior.setSpacing(25);
        hBoxInferior.setStyle("-fx-background-color: #ff76a6");
        hBoxInferior.setPrefHeight(80);

        btNovo=new Button("Novo");
        btAbrir=new Button("Abrir");
        btSalvar=new Button("Salvar");
        btSalvarComo=new Button("Salvar Como");
        btAlteraCor=new Button("Alterar Cor");
        btNovo.setPrefSize(120,40);
        btAbrir.setPrefSize(120,40);
        btSalvar.setPrefSize(120,40);
        btSalvarComo.setPrefSize(120,40);
        btAlteraCor.setPrefSize(120,40);
        hBoxInferior.getChildren().addAll(btNovo, btAbrir, btSalvar,btSalvarComo,btAlteraCor);
        //centro
        textArea=new TextArea();
        this.setTop(hBoxSuperior); this.setBottom(hBoxInferior);
        this.setCenter(textArea);

        fontSize=textArea.getFont().getSize();
    }
    private void novo() {
        if (emEdicao) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Deseja apagar o texto já editado ?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                textArea.clear();
                emEdicao=true;
                disabledProperty();
            }else{
                textArea.requestFocus();
                botaoSalvarComo();
            }

        }
        else
            textArea.clear();
    }


    private void abrir() {
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Arquivos Texto","*.txt"));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Código Fonte","*.java","*.cpp","*.html","*.js"));

        file = fileChooser.showOpenDialog(textArea.getScene().getWindow());
        if(file!=null){
            // ler o arquivo aberto
            String str=FileUtil.abrir(file);
            textArea.setText(str);
            emEdicao=true;
            botaoSalvar();
        }else{
            stage.close();
        }
    }


    //SALVAR ARQUIVO ALTERADO
    private void botaoSalvar() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar");

        if (file != null) {
            textArea.requestFocus();
            try(BufferedWriter writer=new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
                emEdicao=false;
            } catch (Exception e) {

            }

        }else {
            stage.close();
        }
    }
    //SALVAR ARQUIVO NOVO
    private void botaoSalvarComo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Como");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivos Texto","*.txt"));

        file=fileChooser.showSaveDialog(textArea.getScene().getWindow());
        if (file != null) {
            try {
                Files.write(file.toPath(), textArea.getText().getBytes());

            } catch (IOException e) {

            }
        }else {
            stage.close();
        }
    }
    private void alterarCorTxt() {
        ColorPicker colorPicker = new ColorPicker(Color.WHITE);
        Button ok = new Button("OK");
//definindo propriedades do botao
        ok.setPrefSize(60, 40);
        ok.setStyle("-fx-background-color: #ff76a6; -fx-text-fill: white; -fx-font-weight: bold;");
        //acão para mudar a cor
        ok.setOnAction(e -> {
            Color corFinal = colorPicker.getValue();
            String hexColor = String.format("#%02X%02X%02X",
                    (int) (corFinal.getRed() * 255),
                    (int) (corFinal.getGreen() * 255),
                    (int) (corFinal.getBlue() * 255));
            //nessa linha que altera a cor, ao inves de passar a cor, passa o parametro
            textArea.setStyle("-fx-control-inner-background: " + hexColor + ";");

        });
        VBox root = new VBox(15);
        root.setStyle("-fx-alignment: center; -fx-padding: 20;");
        root.getChildren().addAll(colorPicker, ok);
        Scene scene = new Scene(root, 300, 200);

        Stage novoSta = new Stage();
        novoSta.setScene(scene);
        novoSta.setTitle("Escolher Cor");
        novoSta.show();
    }

    private void estatistica() {
        if (emEdicao) {
            int qtd = textArea.getText().length();
            int qtdePalavra= textArea.getText().split(" ").length;
            int qtdeLinha =textArea.getText().split("\r\n|\r|\n").length;
            if(textArea.getText().isEmpty()){
                qtd=0;
                qtdePalavra=0;
                qtdeLinha =0;
            }else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Estatísticas");
                alert.setHeaderText("Quantidade de Caracteres");
                alert.setContentText("Quantidade de caracteres: " + qtd+"Quantidade de palavras: " + qtdePalavra + "Quantidade de linhas: " + qtdeLinha) ;

                alert.showAndWait();
            }

        } else {
            stage.close();
        }
    }

}