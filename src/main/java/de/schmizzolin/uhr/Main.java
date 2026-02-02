package de.schmizzolin.uhr;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Rectangle square = new Rectangle(10, 10, Color.DODGERBLUE);
        StackPane root = new StackPane(square);
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Uhr");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
