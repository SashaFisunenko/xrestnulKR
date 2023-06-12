module com.example.xrestnul {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.xrestnul to javafx.fxml;
    exports com.example.xrestnul;
}