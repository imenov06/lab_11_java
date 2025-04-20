module org.example.versusjfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.versusjfx to javafx.fxml;
    exports org.example.versusjfx;
}