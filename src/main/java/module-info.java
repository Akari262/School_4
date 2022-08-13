module com.example.school_4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.ooxml;


    opens com.example.school_4 to javafx.fxml;
    exports com.example.school_4;
}