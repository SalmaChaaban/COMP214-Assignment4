module com.example.comp214_assignment4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.comp214_assignment4 to javafx.fxml;
    exports com.example.comp214_assignment4;
}