package com.example.dictionaryoftvaanngogiangan;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class SceneController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private Button gameButton;

    @FXML
    private Button likeButton;

    @FXML
    private Button onlineTranslateButton;

    @FXML
    private Button searchBT;

    @FXML
    private Button exportToFIleButton;

    @FXML
    private AnchorPane viewContainer;

    @FXML
    private Tooltip addWordToolTip;

    @FXML
    private Tooltip searchWordToolTip;

    @FXML
    private Tooltip exportToFileToolTip;

    @FXML
    void listToolTip() {
        addWordToolTip.setShowDelay(Duration.ZERO);
        searchWordToolTip.setShowDelay(Duration.ZERO);
        exportToFileToolTip.setShowDelay(Duration.ZERO);
    }

    @FXML
    void addView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("add.fxml"));
        AnchorPane secondaryAnchorPane = loader.load();
        viewContainer.getChildren().setAll(secondaryAnchorPane);
    }

    @FXML
    void searchView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("search.fxml"));
        AnchorPane secondaryAnchorPane = loader.load();
        viewContainer.getChildren().setAll(secondaryAnchorPane);
    }

    @FXML
    void exportToFileView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("exportToFile.fxml"));
        AnchorPane secondaryAnchorPane = loader.load();
        viewContainer.getChildren().setAll(secondaryAnchorPane);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listToolTip();
    }
}
