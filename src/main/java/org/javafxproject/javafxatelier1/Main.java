package org.javafxproject.javafxatelier1;

import javafx.application.Application;
import javafx.stage.Stage;
import org.javafxproject.javafxatelier1.controllers.MainController;

import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {MainController.init(stage);}
    public static void main(String[] args) {
        launch();
    }
}