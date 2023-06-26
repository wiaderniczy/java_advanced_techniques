module com.example.lab13 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.scripting;
                            
    opens com.example.lab13 to javafx.fxml;
    exports com.example.lab13;
}