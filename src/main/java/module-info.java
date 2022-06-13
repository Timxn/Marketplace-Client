module de.oose.webservice.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires gson;


    opens de.oose.webservice.client to javafx.fxml;
    opens de.oose.webservice.client.gui to javafx.fxml;
    exports de.oose.webservice.client;
    exports de.oose.webservice.client.gui;
}