package com.example.dictionaryoftvaanngogiangan;

import base.Dictionary;
import base.MyDictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    if(dictionaryExportToFileBar.getText().trim().isEmpty()){
        Stage stage =(Stage) fileChooseView.getScene().getWindow();
        FileChooser fc = new FileChooser();
        fc.setTitle("Choose a file.txt");
        FileChooser.ExtensionFilter txtFiler = new FileChooser.ExtensionFilter("Txt Files","*.txt");
        fc.getExtensionFilters().add(txtFiler);
        File file = fc.showOpenDialog(stage);
        dictionaryExportToFileBar.setText(file.getAbsolutePath());
        Dictionary.getMyDictionary().dictionaryExportToFile(file.getAbsolutePath());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Trạng thái lưu danh sách các từ đã học:");
        alert.setContentText("Đã lưu thành công !");
        alert.showAndWait();
    }
    else{
        if(isPathExists(dictionaryExportToFileBar.getText()) && dictionaryExportToFileBar.getText().substring(dictionaryExportToFileBar.getText().length()-3).equals("txt")){
            MyDictionary.getMyDictionary().dictionaryExportToFile(dictionaryExportToFileBar.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Trạng thái lưu dữ liệu từ điển:");
            alert.setContentText("Đã lưu thành công !");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Không tìm thấy file hoặc file không phải định dạng txt.");
            alert.setContentText("Lưu thất bại !");
            alert.showAndWait();
        }
    }
}

    private boolean isPathExists(String path) {
        File file = new File(path);
        return file.exists();
    }
@FXML
void chooseFileMyDictionary(ActionEvent event){
    if(myDictionaryExportToFileBar.getText().trim().isEmpty()){
        Stage stage =(Stage) fileChooseView.getScene().getWindow();
        FileChooser fc = new FileChooser();
        fc.setTitle("Choose a file.txt");
        FileChooser.ExtensionFilter txtFiler = new FileChooser.ExtensionFilter("Txt Files","*.txt");
        fc.getExtensionFilters().add(txtFiler);
        File file = fc.showOpenDialog(stage);
        myDictionaryExportToFileBar.setText(file.getAbsolutePath());
        MyDictionary.getMyDictionary().dictionaryExportToFile(file.getAbsolutePath());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Trạng thái lưu danh sách các từ đã học:");
        alert.setContentText("Đã lưu thành công !");
        alert.showAndWait();
    }
    else{
        if(isPathExists(myDictionaryExportToFileBar.getText()) && myDictionaryExportToFileBar.getText().substring(myDictionaryExportToFileBar.getText().length()-3).equals("txt")){
            MyDictionary.getMyDictionary().dictionaryExportToFile(myDictionaryExportToFileBar.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Trạng thái lưu danh sách các từ đã học:");
            alert.setContentText("Đã lưu thành công !");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Không tìm thấy file hoặc file không phải định dạng txt.");
            alert.setContentText("Lưu thất bại !");
            alert.showAndWait();
        }
    }
}

}
