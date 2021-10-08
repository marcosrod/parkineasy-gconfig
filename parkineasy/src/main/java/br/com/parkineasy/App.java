package br.com.parkineasy;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Paths;

/**
 * JavaFX App
 */
public class App extends Application {

    public static final String PARKINEASY_FOLDER = "C:\\Users\\MARCOS\\Desktop\\parkineasy";

    public static void nextScene(String name, int height, int width, URL url, ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(url);
            Stage stage = new Stage();
            stage.setTitle(name);
            stage.setScene(new Scene(root, height, width));
            stage.setResizable(false);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        URL url =
                Paths.get(PARKINEASY_FOLDER + "\\src\\main\\java\\br\\com\\parkineasy\\view\\fxml\\TelaInicial.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        stage.setTitle("Seleção de Módulo");
        stage.setScene(new Scene(root, 520, 400));
        stage.setResizable(false);
        stage.show();
    }

}
