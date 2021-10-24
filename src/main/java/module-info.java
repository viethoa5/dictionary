module dictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires freetts;

    opens javaController to javafx.fxml;
    exports javaController;
}