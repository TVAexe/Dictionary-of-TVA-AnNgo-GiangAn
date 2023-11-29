package com.example.dictionaryoftvaanngogiangan;

import base.Dictionary;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.util.List;
import java.util.Arrays;

public class addController {
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

    @FXML
    public void saveWord(ActionEvent event) {
        String originalColor = saveButton.getStyle();
        saveButton.setStyle("-fx-background-color: #FF0000");
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(e -> saveButton.setStyle(originalColor));
        pause.play();

        String wordTarget = targetTextField.getText().trim().toLowerCase();
        String wordPronunciation = pronunciationTextField.getText().trim().toLowerCase();
        List<String> wordMeaning = Arrays.asList(meaningTextArea.getText().split("\n"));
        for (String str : wordMeaning) {
            str = str.trim();
        }
        if (wordTarget.isEmpty() || wordPronunciation.isEmpty() || wordMeaning.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cảnh báo!");
            alert.setHeaderText("Cảnh báo:");
            alert.setContentText("Bạn phải hoàn thành cả 3 mục để thêm từ thành công");
            alert.showAndWait();
        }
        else if (Dictionary.getMyDictionary().searchWord(wordTarget)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cảnh báo!");
            alert.setHeaderText("Cảnh báo:");
            alert.setContentText("Từ mà bạn muốn thêm đã có trong từ điển.");
            alert.showAndWait();
        }
        else {
            Dictionary.getMyDictionary().addWordForDictionary(wordTarget, wordPronunciation, wordMeaning);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo!");
            alert.setHeaderText("Thông báo:");
            alert.setContentText("Bạn đã thêm từ thành công vào từ điển.");
            alert.showAndWait();
        }
    }
}
