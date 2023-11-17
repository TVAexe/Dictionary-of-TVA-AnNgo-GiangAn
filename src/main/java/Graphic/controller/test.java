package Graphic.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)  {
        FXMLLoader test = new FXMLLoader(getClass().getResource(
                "src/main/java/Graphic/panes/scence.fxml"));
        try {
            Parent r = test.load();
            Scene sc = new Scene(r);
            primaryStage.setScene(sc);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("fuck");
        }

    }
}
