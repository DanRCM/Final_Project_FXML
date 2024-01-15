package ec.edu.espol.poo_project.controller;

import ec.edu.espol.poo_project.util.Utilitaria;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static MediaPlayer musicPlayer;

    public static Scene getScene() {
        return scene;
    }

    public static MediaPlayer getMusicPlayer() {
        return musicPlayer;
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Menu"), Utilitaria.widthWindow, Utilitaria.heightWindow);
        stage.setTitle("Domino Politecnico");
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml, int width, int height) throws IOException {
        scene.setRoot(loadFXML(fxml));
        scene.getWindow().setWidth(width);
        scene.getWindow().setHeight(height);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        Utilitaria.cargarFichas();
        Media musicFile = Utilitaria.cargarMusica("/util/Musica/jazz.mp3");
        MediaPlayer mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
        musicPlayer = mediaPlayer;
        launch();
    }
}
