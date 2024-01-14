package ec.edu.espol.poo_project.controller;

import ec.edu.espol.poo_project.util.UtilitariaView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuController implements Initializable {

    @FXML
    Button btnExit;
    @FXML
    private VBox vBox;
    @FXML
    private AnchorPane apMenu;

    @FXML
    private void switchToNewGame() throws IOException {
        App.setRoot("NewGameView", UtilitariaView.widthWindow, UtilitariaView.heightWindow);
    }

    @FXML
    private void switchToOptions() throws IOException {
        App.setRoot("OptionsView", UtilitariaView.widthWindow, UtilitariaView.heightWindow);
    }

    @FXML
    private void close() throws IOException {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void mouseEntered(MouseEvent event) {
        // Agregar el efecto de sombra cuando el ratón entra en el botón
        Button sourceButton = (Button) event.getSource();
        sourceButton.setEffect(new DropShadow());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BackgroundImage backgroundImage = new BackgroundImage(UtilitariaView.cargarImagen("/util/BackGrounds/BackgroundNewGame 1.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(UtilitariaView.widthWindow, UtilitariaView.heightWindow, false, false, false, false));
        // Establecer el BackgroundImage en el AnchorPane
        apMenu.setBackground(new Background(backgroundImage));
        AnchorPane.setTopAnchor(vBox, (apMenu.getHeight() - vBox.getHeight()) / 2);
        AnchorPane.setBottomAnchor(vBox, (apMenu.getHeight() - vBox.getHeight()) / 2);
        AnchorPane.setLeftAnchor(vBox, (apMenu.getWidth() - vBox.getWidth()) / 2);
        AnchorPane.setRightAnchor(vBox, (apMenu.getWidth() - vBox.getWidth()) / 2);
    }
}
