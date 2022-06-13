package de.oose.webservice.client.gui;

import de.oose.webservice.client.Main;
import de.oose.webservice.client.RestAPIClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AnmeldungC {
        @FXML
        private Label exception;

        @FXML
        private Button log_in;

        @FXML
        private TextField name;

        @FXML
        private PasswordField password;

        @FXML
        private Button register;


        public void registerIntoSystem(ActionEvent actionEvent) throws IOException {
                boolean accepted = true;
                if (name.getText().isEmpty() && password.getText().isEmpty()) {
                        exception.setText("Please enter your data!");
                }
                else if (RestAPIClient.register(name.getText(), password.getText())) {
                        //Loads the next scene
                        if (RestAPIClient.login(name.getText(), password.getText())) {
                                Stage stage = (Stage) name.getScene().getWindow();
                                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Menu.fxml"));
                                Scene scene = new Scene(fxmlLoader.load(), 960, 540);
                                stage.setScene(scene);
                                stage.show();
                        }
                } else {
                        //Error Message
                        exception.setText("ERROR: Username or Password wrong!");
                }
        }

        public void logIntoSystem() {
                if (RestAPIClient.login(name.getText(), password.getText())) {
                        Stage stage = (Stage) name.getScene().getWindow();
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Menu.fxml"));
                        Scene scene = null;
                        try {
                                scene = new Scene(fxmlLoader.load(), 960, 540);
                        } catch (IOException e) {
                                throw new RuntimeException(e);
                        }
                        stage.setScene(scene);
                        stage.show();
                }
                exception.setText("Thanks for choosing Armin ;) Try to LogIn!");
        }
}
