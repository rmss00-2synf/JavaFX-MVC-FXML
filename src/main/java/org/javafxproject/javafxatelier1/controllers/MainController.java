package org.javafxproject.javafxatelier1.controllers;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.javafxproject.javafxatelier1.Main;

import java.io.IOException;
public class MainController {
    public static void init(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 500);  // Définir la taille initiale de la fenêtre
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setWidth(710);
            stage.setHeight(500);
            stage.show();
        }
}
