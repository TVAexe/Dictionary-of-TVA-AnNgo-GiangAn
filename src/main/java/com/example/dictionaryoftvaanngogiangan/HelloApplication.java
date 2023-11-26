package com.example.dictionaryoftvaanngogiangan;

import base.Dictionary;
import base.MyDictionary;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Dictionary.getMyDictionary().insertFromFile();
        MyDictionary.getMyDictionary().insertFromFile();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}