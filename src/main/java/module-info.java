module com.example.dictionaryoftvaanngogiangan {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    requires com.almasb.fxgl.all;
    requires java.sql;
    requires javafx.media;

    opens com.example.dictionaryoftvaanngogiangan to javafx.fxml;
    exports com.example.dictionaryoftvaanngogiangan;
}