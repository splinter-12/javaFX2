package ma.enset.tpBaseDonnes.présentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationJavaFx extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        TabPane root = FXMLLoader.load(getClass().getResource("view/main.fxml"));
        Scene scene = new Scene(root,400,400);
        primaryStage.setTitle("gestion des produits");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}