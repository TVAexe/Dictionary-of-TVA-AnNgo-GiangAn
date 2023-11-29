package com.example.dictionaryoftvaanngogiangan;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import API.AudioPlay;
import base.Dictionary;
import base.MyDictionary;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class favoriteController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listWord;

    @FXML
    private TextField searchBar;

    @FXML
    private Button search;

    @FXML
    private Label textMeaning;

    @FXML
    private Label WTaget;

    @FXML
    private Button favoriteButton;

    @FXML
    private Button soundButton;

    @FXML
    private Tooltip removeToolTip;

    @FXML
    private Tooltip searchToolTip;

    @FXML
    private Tooltip pronunciationToolTip;

    void listToolTip() {
        searchToolTip.setShowDelay(Duration.ZERO);
        pronunciationToolTip.setShowDelay(Duration.ZERO);
        removeToolTip.setShowDelay(Duration.ZERO);
    }

    @FXML
    private void listWordView() {
        searchBar.addEventHandler(KeyEvent.KEY_RELEASED,event->{
            String word = searchBar.getText().toLowerCase();
            if(!word.trim().isEmpty()){
                List<String> a = MyDictionary.getMyDictionary().getWordsStartingWith(word);
                ObservableList<String> observableList = FXCollections.observableArrayList(a);
                listWord.setItems(observableList);
            }
        });

        listWord.setOnMouseClicked(event -> {
            if (listWord.getSelectionModel().getSelectedItem() != null) {
                String selectedWord = listWord.getSelectionModel().getSelectedItem();
                searchBar.setText(selectedWord);
            }
        });
    }

    @FXML
    private void searchWord(ActionEvent event) {
        String originalColor = search.getStyle();
        search.setStyle("-fx-background-color: #87CEEB");
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(e -> search.setStyle(originalColor));
        pause.play();

        String wordTarget = searchBar.getText().toLowerCase();
        if(MyDictionary.getMyDictionary().searchWord(wordTarget)){
            WTaget.setText(MyDictionary.getMyDictionary().getWord(wordTarget).getWordTarget());
            textMeaning.setText(MyDictionary.getMyDictionary().getWord(wordTarget).toString());
        }
    }

    @FXML
    private void deleteFavoriteWord(ActionEvent event) {
        String wordTarget = searchBar.getText();
        if(!wordTarget.trim().isEmpty() && MyDictionary.getMyDictionary().searchWord(wordTarget)){
            MyDictionary.getMyDictionary().removeWord(wordTarget);
            WTaget.setText("");
            textMeaning.setText("");
            List<String> a = MyDictionary.getMyDictionary().getWordsStartingWith(wordTarget);
            ObservableList<String> observableList = FXCollections.observableArrayList(a);
            listWord.setItems(observableList);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Trạng thái xóa từ yêu thích vừa tra cứu ra khỏi từ điển của bạn:");
            alert.setContentText("Đã xóa thành công !");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Yêu cầu tìm kiếm từ có xuất hiện trong từ điển để có thể xóa khỏi từ điển của bạn!");
            alert.setContentText("không thể xóa !");
            alert.showAndWait();
        }
    }

    @FXML
    private void playSound(ActionEvent event) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        String word = searchBar.getText().trim();
        AudioPlay.TTS(word, "en-us");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listWordView();
    }
}
