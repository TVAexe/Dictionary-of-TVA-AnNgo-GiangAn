package com.example.dictionaryoftvaanngogiangan;

import base.Dictionary;
import base.Word;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class editController {
    @FXML
    private Button saveButton;

    @FXML
    private TextField targetTextField;

    @FXML
    private TextField pronunciationTextField;

    @FXML
    private TextArea meaningTextArea;

    @FXML
    private Tooltip saveToolTip;

    void listToolTip() {
        saveToolTip.setShowDelay(Duration.ZERO);
    }

    public void initialize() {
        targetTextField.setOnAction(event -> {
            String wordTarget = targetTextField.getText().trim().toLowerCase();
            if(wordTarget.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Cảnh báo!");
                alert.setHeaderText("Cảnh báo:");
                alert.setContentText("Ô từ vựng không được để trống");
                alert.showAndWait();
            } else if (Dictionary.getMyDictionary().searchWord(wordTarget)) {
                Word word = Dictionary.getMyDictionary().getWord(wordTarget);
                pronunciationTextField.setText(word.getWordPronunciation());
                String meaning = "";
                for(String str : word.getWordExplain()) {
                    meaning = meaning.concat(str + "\n");
                }
                meaningTextArea.setText(meaning);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Thông báo:");
                alert.setContentText("Từ này không có trong từ điển.");
                alert.showAndWait();
            }
        });
    }

    @FXML
    public void editWord(ActionEvent event) {
        String originalColor = saveButton.getStyle();
        saveButton.setStyle("-fx-background-color: #FF0000");
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(e -> saveButton.setStyle(originalColor));
        pause.play();

        String wordTarget = targetTextField.getText().trim().toLowerCase();
        String wordPronunciation = pronunciationTextField.getText().trim().toLowerCase();
        if(wordTarget.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cảnh báo!");
            alert.setHeaderText("Cảnh báo:");
            alert.setContentText("Ô từ vựng không được để trống");
            alert.showAndWait();
        } else if(!Dictionary.getMyDictionary().searchWord(wordTarget)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cảnh báo!");
            alert.setHeaderText("Cảnh báo:");
            alert.setContentText("Bạn không thể chỉnh sửa vì từ này không có trong từ điển.");
            alert.showAndWait();
        } else {
            String wordExplain = meaningTextArea.getText().trim();
            List<String> wordMeaning = new ArrayList<>();
            if(!wordExplain.isEmpty()) {
                wordMeaning = Arrays.asList(meaningTextArea.getText().split("\n"));
            }
            Dictionary.getMyDictionary().updateWord(wordTarget, wordPronunciation, wordMeaning);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Thông báo:");
            alert.setContentText("Bạn đã sửa thành công.");
            alert.showAndWait();
        }
    }
}

