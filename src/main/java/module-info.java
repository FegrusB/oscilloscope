module com.example.oscilloscope {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.oscilloscope to javafx.fxml;
    exports com.example.oscilloscope;
    exports com.example.oscilloscope.signalService;
    opens com.example.oscilloscope.signalService to javafx.fxml;
}