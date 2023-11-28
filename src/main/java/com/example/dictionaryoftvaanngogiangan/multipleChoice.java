package com.example.dictionaryoftvaanngogiangan;

import base.MyDictionary;
import base.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

import javafx.util.Duration;

public class multipleChoice implements Initializable {

    @FXML
    private Button answerA;

    @FXML
    private Button answerB;

    @FXML
    private Button answerC;

    @FXML
    private Button answerD;

    @FXML
    private Text dataA;

    @FXML
    private Text dataB;

    @FXML
    private Text dataC;

    @FXML
    private Text dataD;

    @FXML
    private Label theQuiz;

    @FXML
    private Button turntoMenu;

    @FXML
    private Label condiction;

    @FXML
    private Label counting;

    @FXML
    private Tooltip nextToolTip;

    @FXML
    private Tooltip returnToolTip;

    @FXML
    private Button musicButton;

    @FXML
    private AnchorPane multipleChoice;

    private SceneController sceneController;

    private int limit = 1;

    private List<Word> tmp = new ArrayList<>();
    private List<String> test = new ArrayList<>();
    private Random rand = new Random();
    private Word currentQuestion;

    private String ans = "";

    int demSoCauHoi = 0;
    int demSoCauSai = 0;

    private MediaPlayer mediaPlayer;

    void listToolTip() {
        returnToolTip.setShowDelay(Duration.ZERO);
        nextToolTip.setShowDelay(Duration.ZERO);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String musicFile = Paths.get("src/main/resources/music/music.wav").toUri().toString();
        Media media = new Media(musicFile);
        mediaPlayer = new MediaPlayer(media);
    }

    @FXML
    void initialize() throws IOException{
        if (demSoCauSai>=3) {
            gameOver();
        } else {
            limit = 1;
            demSoCauHoi++;
            condiction.setText("");
            create();
            loadQuestion();
            counting.setText("Số câu hỏi: " + demSoCauHoi + "\n" + "Số câu trả lời Sai: " + demSoCauSai);
        }
    }

    void create() {
        MyDictionary myDictionary = MyDictionary.getMyDictionary();
        myDictionary.insertFromFile();
        Map<String, Word> database = myDictionary.getDictionary();

        test.addAll(database.keySet());
        tmp.addAll(database.values());
    }

    @FXML
    void loadQuestion() {
        if (!tmp.isEmpty()) {
            int randomIndex = rand.nextInt(tmp.size());
            currentQuestion = tmp.get(randomIndex);

            // Now 'currentQuestion' holds a randomly selected element from 'tmp'
            String s = "";
            s = s + currentQuestion.getWordExplain().get(0);

            theQuiz.setText(s);
            loadAnswer();
        } else {
            System.out.println("The list is empty.");
        }
    }
    @FXML
    void loadAnswer() {
        List<String> ans = new ArrayList<>();
        ans.add(currentQuestion.getWordTarget());

        // Randomly select 3 incorrect answers from the 'test' list
        List<String> incorrectAnswers = new ArrayList<>(test);
        incorrectAnswers.remove(currentQuestion.getWordTarget()); // Remove the correct answer

        for (int i = 0; i < 3; i++) {
            int randomIndex = rand.nextInt(incorrectAnswers.size());
            ans.add(incorrectAnswers.get(randomIndex));
            incorrectAnswers.remove(randomIndex);
        }

        // Shuffle the answers
        java.util.Collections.shuffle(ans);

        // Set the answers to buttons A, B, C, D
        answerA.setText(ans.get(0));
        answerB.setText(ans.get(1));
        answerC.setText(ans.get(2));
        answerD.setText(ans.get(3));
    }

    @FXML
    void getAnsA(ActionEvent event) {
        ans = answerA.getText();
        if (limit>0) {
            checkout();
        }
    }

    @FXML
    void getAnsB(ActionEvent event) {
        ans = answerB.getText();
        if (limit>0) {
            checkout();
        }
    }

    @FXML
    void getAnsC(ActionEvent event) {
        ans = answerC.getText();
        if (limit>0) {
            checkout();
        }
    }

    @FXML
    void getAnsD(ActionEvent event) {
        ans = answerD.getText();
        if (limit>0) {
            checkout();
        }
    }

    void checkout () {
        if (ans.equals(currentQuestion.getWordTarget())) {
            condiction.setText("Correct");
        } else {
            condiction.setText("Wrong answer! The Correct answer is " + currentQuestion.getWordTarget());
            demSoCauSai++;
        }
        counting.setText("Số câu hỏi: " + demSoCauHoi + "\n" + " Số câu trả lời Sai: " + demSoCauSai);
        limit = limit - 1;
    }

    @FXML
    void switchToMenu(ActionEvent event) throws IOException {
        multipleChoice.getChildren().clear();
        mediaPlayer.stop();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chooseGame.fxml"));
        AnchorPane secondaryAnchorPane = loader.load();
        multipleChoice.getChildren().setAll(secondaryAnchorPane);

    }

    @FXML
    public void gameOver() throws IOException {
        multipleChoice.getChildren().clear();
        mediaPlayer.stop();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("multipleChoiceover.fxml"));
        AnchorPane anchorPaneGameOver = loader.load();
        multipleChoice.getChildren().add(anchorPaneGameOver);
    }

    @FXML
    public void musicController(ActionEvent event) {
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
            musicButton.setText("Bật nhạc nền");
        } else {
            mediaPlayer.play();
            musicButton.setText("Tắt nhạc nền");
        }
    }
}