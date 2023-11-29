package com.example.dictionaryoftvaanngogiangan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import base.MyDictionary;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class chooseGame {

    @FXML
    private Button Multiple;

    @FXML
    private Button hangman;

    @FXML
    private AnchorPane viewGameContainer;

    @FXML
    public void multipleChoice (ActionEvent event) throws IOException {
        if (MyDictionary.getMyDictionary().getDictionary().size() < 10) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Cảnh báo:");
            alert.setContentText("Không đủ từ để kiểm tra. Phải đánh dấu và học ít nhất 10 từ!");
            alert.showAndWait();
        }
        else {
            viewGameContainer.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("multipleChoice.fxml"));
            AnchorPane anchorPaneForGame = loader.load();
            viewGameContainer.getChildren().add(anchorPaneForGame);
        }
    }
}
