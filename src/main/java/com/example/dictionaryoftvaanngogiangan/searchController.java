package com.example.dictionaryoftvaanngogiangan;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

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

public class searchController implements Initializable {

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
    private Button removeButton;

    @FXML
    private Tooltip favoriteToolTip;

    @FXML
    private Tooltip removeToolTip;

    @FXML
    private Tooltip pronunciationToolTip;

    void listToolTip() {
        favoriteToolTip.setShowDelay(Duration.ZERO);
        removeToolTip.setShowDelay(Duration.ZERO);
        pronunciationToolTip.setShowDelay(Duration.ZERO);
    }

    @FXML
    private void listWordView() {
        searchBar.addEventHandler(KeyEvent.KEY_RELEASED,event->{
            String word = searchBar.getText().toLowerCase();
            if(!word.trim().isEmpty()){
                List<String> a = Dictionary.getMyDictionary().getWordsStartingWith(word);
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

    private String wordTaget = "";
    @FXML
    private void searchWord(ActionEvent event) {
        String originalColor = search.getStyle();
        search.setStyle("-fx-background-color: #87CEEB");
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(e -> search.setStyle(originalColor));
        pause.play();

        wordTaget = searchBar.getText().toLowerCase();
        if(Dictionary.getMyDictionary().searchWord(wordTaget)){
            WTaget.setText(Dictionary.getMyDictionary().getWord(wordTaget).getWordTarget());
            textMeaning.setText(Dictionary.getMyDictionary().getWord(wordTaget).toString());
        }
    }

    @FXML
    private void setFavoriteWord(ActionEvent event) {
        if(!wordTaget.trim().isEmpty() && Dictionary.getMyDictionary().searchWord(wordTaget)){
            String pronun = Dictionary.getMyDictionary().getWord(wordTaget).getWordPronunciation();
            List<String> meaning = Dictionary.getMyDictionary().getWord(wordTaget).getWordExplain();
            MyDictionary.getMyDictionary().setFavorite(wordTaget,pronun,meaning);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Trạng thái thêm vào danh sách các từ đã học:");
            alert.setContentText("Đã thêm thành công !");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Yêu cầu tìm kiếm từ có xuất hiện trong từ điển để có thể thêm vào danh sách đã học !");
            alert.setContentText("không thể thêm vào danh sách từ đã học !");
            alert.showAndWait();
        }
    }

    @FXML
    private  void removeWord (ActionEvent event){
        if(!wordTaget.trim().isEmpty() && Dictionary.getMyDictionary().searchWord(wordTaget)){
            Dictionary.getMyDictionary().removeWord(wordTaget);
            WTaget.setText("");
            textMeaning.setText("");
            List<String> a = Dictionary.getMyDictionary().getWordsStartingWith(wordTaget);
            ObservableList<String> observableList = FXCollections.observableArrayList(a);
            listWord.setItems(observableList);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Trạng thái xóa từ vừa tra cứu ra khỏi từ điển:");
            alert.setContentText("Đã xóa thành công !");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Yêu cầu tìm kiếm từ có xuất hiện trong từ điển để có thể xóa khỏi từ điển !");
            alert.setContentText("không thể xóa !");
            alert.showAndWait();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listWordView();
    }
}
