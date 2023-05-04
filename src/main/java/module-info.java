module com.example.shiftplaner {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.shiftplaner to javafx.fxml;
    exports com.example.shiftplaner;
}