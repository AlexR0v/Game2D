module ru.alexrov.game2d {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    //requires com.almasb.fxgl.all;
    requires java.desktop;

    opens ru.alexrov.game2d.main to javafx.fxml;
    exports ru.alexrov.game2d.main;
}