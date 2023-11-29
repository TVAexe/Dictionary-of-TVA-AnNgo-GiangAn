package com.example.dictionaryoftvaanngogiangan;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import API.AudioPlay;
import API.GoogleTranslate;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class onlineTranslateController {

    private boolean enToVi = true;

    @FXML
    private Button englishToVietnamButton;

    @FXML
    private Button vietnamToEnglishButton;

    @FXML
    private Label outputLabel;

    @FXML
    private TextArea inputTextArea;

    @FXML
    private Button outputSoundButton;

    @FXML
    private Button inputSoundButton;

    @FXML
    public void vietnameseToEnglishTranslate() throws IOException {
        String input = inputTextArea.getText().trim();
        enToVi = false;
        String output = GoogleTranslate.translate("vi", "en", input);
        outputLabel.setText(output);
    }

    @FXML
    public void englishToVietnameseTranslate() throws IOException {
        String input = inputTextArea.getText().trim();
        enToVi = true;
        String output = GoogleTranslate.translate("en", "vi", input);
        outputLabel.setText(output);
    }

    @FXML
    public void playOutputSound() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        String output = outputLabel.getText().trim();
        if(enToVi) {
            AudioPlay.TTS(output,"vi-vn");
        }
        else {
            AudioPlay.TTS(output, "en-us");
        }
    }

    @FXML
    public void playInputSound() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        String input = inputTextArea.getText().trim();
        if(enToVi) {
            AudioPlay.TTS(input,"en-us");
        }
        else {
            AudioPlay.TTS(input, "vi-vn");
        }
    }
}
