/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.poo_project.util;

import ec.edu.espol.poo_project.model.Ficha;
import ec.edu.espol.poo_project.model.FichaComodin;
import ec.edu.espol.poo_project.model.Juego;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.media.Media;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author CARLOS THOME
 */
public class Utilitaria {

    public static int widthWindow = 1280;
    public static int heightWindow = 720;
    public static ArrayList<Ficha> fichasExistentes;

    public static void cargarFichas() {
        fichasExistentes = new ArrayList<>();
        ImageView img;
        for (int k = 0; k < 4; k++) {
            for (int i = 0; i <= 6; i++) {
                for (int j = 0; j <= 6; j++) {
                    img = new ImageView(new Image("/util/Fichas/" + i + "_" + j + ".png"));
                    img.setFitWidth(100);
                    img.setFitHeight(50);
                    fichasExistentes.add(new Ficha(i, j, img));
                }
            }
            img = new ImageView(new Image("/util/Fichas/comodin.gif"));
            img.setFitWidth(100);
            img.setFitHeight(50);
            fichasExistentes.add(new FichaComodin(img));
        }
    }

    public static ArrayList<Ficha> generarMano() {
        ArrayList<Ficha> mano = new ArrayList<>();
        ArrayList<String> verificar = new ArrayList<>();
        int ladoI, ladoD;
        Ficha f;
        int cont = 0;
        while (cont < 5) {
            ladoI = (int) (Math.random() * 7);
            ladoD = (int) (Math.random() * 7);
            if (!verificar.contains(ladoI+""+ladoD)) {
                verificar.add(ladoI+""+ladoD);
                f = Juego.buscarFicha(ladoI, ladoD);
//                System.out.print(ladoI + "-" + ladoD + " ");
//                System.out.println(f);
                mano.add(f);
                cont += 1;
            }
        }
        f = Juego.buscarFicha(-1, -1);
        //System.out.println(f);
        mano.add(f);
        return mano;
    }

    public static String obtenerNombreDialogo() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Diálogo de Entrada de Texto");

        Label label = new Label("Ingrese su nombre");
        TextArea textArea = new TextArea();
        textArea.setPrefRowCount(2); // Establecer el número de filas
        textArea.setPrefColumnCount(10); // Establecer el número de columnas

        Button acceptButton = new Button("Aceptar");
        acceptButton.setOnAction(e -> {
            dialog.close();
        });

        VBox dialogVBox = new VBox(10, label, textArea, acceptButton);
        dialogVBox.setPadding(new Insets(10));
        dialogVBox.setAlignment(Pos.CENTER);

        VBox.setVgrow(acceptButton, Priority.ALWAYS);
        acceptButton.setMaxWidth(Double.MAX_VALUE);
        VBox.setMargin(acceptButton, new Insets(10, 0, 0, 0));

        Scene dialogScene = new Scene(dialogVBox, 250, 150);
        dialog.setScene(dialogScene);

        dialog.showAndWait();

        if (textArea.getText().isEmpty()) {
            return "PlayerDefault";
        } else {
            return textArea.getText();
        }
    }

    public static String obtenerPosicionDialogo() {
        // Crear un diálogo personalizado
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Colocar Ficha");
        alert.setHeaderText(null);

        // Agregar botones "Izquierda" y "Derecha" al diálogo
        ButtonType izquierdaButton = new ButtonType("Izquierda");
        ButtonType derechaButton = new ButtonType("Derecha");
        alert.getButtonTypes().setAll(izquierdaButton, derechaButton);

        // Obtener la respuesta del usuario al presionar un botón
        return alert.showAndWait().map(buttonType -> {
            if (buttonType == izquierdaButton) {
                return "Izquierda";
            } else if (buttonType == derechaButton) {
                return "Derecha";
            } else {
                return null;
            }
        }).orElse(null);
    }

    public static int obtenerNumeroDialogo(String titulo) {
        Map<ButtonType, Integer> numeroPorBoton = new HashMap<>();
        // Crear un diálogo personalizado
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle(titulo);
        alert.setHeaderText(null);

        // Crear botones del 1 al 6
        for (int i = 0; i <= 6; i++) {
            ButtonType boton = new ButtonType(Integer.toString(i));
            alert.getButtonTypes().add(boton);
            numeroPorBoton.put(boton, i);
        }
        // Obtener la respuesta del usuario al presionar un botón
        return alert.showAndWait().map(numeroPorBoton::get).orElse(-1);
    }

    public static VBox crearOpcionesBox() {
        VBox vbox = new VBox();
        Label lbl1 = new Label("Redimensionar pantalla");
        ComboBox<String> comboBox1 = new ComboBox<>();
        comboBox1.getItems().addAll("1920x1080", "1280x720", "854x480");
        comboBox1.setValue("1280x720");
        comboBox1.setOnAction(event -> {
            String valores = comboBox1.getValue();
            String[] ancho_altura = valores.split("x");
            widthWindow = Integer.parseInt(ancho_altura[0]);
            heightWindow = Integer.parseInt(ancho_altura[1]);
        });
        lbl1.setTextFill(Color.WHITE);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setSpacing(20);
        vbox.getChildren().addAll(lbl1, comboBox1);
        return vbox;
    }

    public static Image cargarImagen(String path) {
        Image image = new Image(path);
        return image;
    }

    public static Media cargarMusica(String path) {
        Media media = new Media(Utilitaria.class.getResource(path).toExternalForm());
        return media;
    }

    public static void redimensionarFicha(ImageView i) {
        i.setFitWidth(100);
        i.setFitHeight(50);
    }
}
