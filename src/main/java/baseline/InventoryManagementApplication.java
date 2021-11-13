/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Ryan Hodge
 */

package baseline;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class InventoryManagementApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Inventory Manager");

        Parent root = FXMLLoader.load(getClass().getResource("InventoryManager.fxml"));

        Scene scene = new Scene(root, 1200, 600);

        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
