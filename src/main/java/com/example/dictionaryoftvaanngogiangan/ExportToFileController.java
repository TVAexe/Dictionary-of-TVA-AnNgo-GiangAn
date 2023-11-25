package com.example.dictionaryoftvaanngogiangan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ExportToFileController {
@FXML
    private TextField dictionaryExportToFileBar;
@FXML
    private TextField myDictionaryExportToFileBar;
@FXML
    private Button dictionaryExportToFileButton;
@FXML
    private Button myDictionaryExportToFileButton;
@FXML
private AnchorPane fileChooseView;
@FXML
void chooseFileDictionary(ActionEvent event){
    Stage stage =(Stage) fileChooseView.getScene().getWindow();
    FileChooser fc = new FileChooser();
    fc.setTitle("Choose a file.txt");
    FileChooser.ExtensionFilter txtFiler = new FileChooser.ExtensionFilter("Txt Files","*.txt");
    fc.getExtensionFilters().add(txtFiler);
    File file = fc.showOpenDialog(stage);
}
@FXML
void chooseFileMyDictionary(ActionEvent event){
    Stage stage =(Stage) fileChooseView.getScene().getWindow();
    FileChooser fc = new FileChooser();
    fc.setTitle("Choose a file.txt");
    FileChooser.ExtensionFilter txtFiler = new FileChooser.ExtensionFilter("Txt Files","*.txt");
    fc.getExtensionFilters().add(txtFiler);
    File file = fc.showOpenDialog(stage);
}

}
