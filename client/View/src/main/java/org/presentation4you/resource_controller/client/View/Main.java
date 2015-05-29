package org.presentation4you.resource_controller.client.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.presentation4you.resource_controller.client.Authentication.Authentication;
import org.presentation4you.resource_controller.commons.Response.ResponseStatus;
import org.presentation4you.resource_controller.commons.Role.Coordinator;

public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        Authentication aut = Authentication.getInstance();
        if (aut.login("admin", "admin") == ResponseStatus.OK) {
            if (aut.getRole() instanceof Coordinator) {
                Parent root = FXMLLoader.load(getClass().getResource("ResourceController.fxml"));
                primaryStage.setTitle("ResourceController");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            }
        }
    }

    public static void main(final String[] args) {
        launch(args);
    }
}