module org.javafxproject.javafxatelier1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires static lombok;

    opens org.javafxproject.javafxatelier1 to javafx.fxml;
    opens org.javafxproject.javafxatelier1.controllers to javafx.fxml; // Ajout pour permettre à FXML d'accéder au contrôleur

    exports org.javafxproject.javafxatelier1;
}
