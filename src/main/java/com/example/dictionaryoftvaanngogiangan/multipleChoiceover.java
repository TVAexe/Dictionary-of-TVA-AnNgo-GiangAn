package com.example.dictionaryoftvaanngogiangan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class multipleChoiceover {

    @FXML
    private Button backToMenu;

    @FXML
    private AnchorPane ending;

    @FXML
    void initialize() {
        backToMenu.setOnAction(event -> {
            backToMenu.setStyle("-fx-background-color: #ff0000;");
            try {
                turnBack(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void turnBack(ActionEvent event) throws IOException {
        ending.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chooseGame.fxml"));
        AnchorPane secondaryAnchorPane = loader.load();
        ending.getChildren().setAll(secondaryAnchorPane);
    }
}

