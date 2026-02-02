package de.schmizzolin.uhr;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    private static final String MATRIX = """
            x x x
             x x
              x
            """;

    private static VBox root(String matrix) {
        VBox vbox = new VBox(5);
        String[] lines = matrix.split("\n");
        for (String line : lines) {
            HBox hbox = new HBox(5);
            for (char c : line.toCharArray()) {
                if (c == 'x') {
                    Rectangle rect = new Rectangle(20, 20, Color.BLUEVIOLET);
                    hbox.getChildren().add(rect);
                } else if (c == ' ') {
                    Rectangle rect = new Rectangle(20, 20, Color.TRANSPARENT);
                    hbox.getChildren().add(rect);
                }
            }
            vbox.getChildren().add(hbox);
        }
        return vbox;
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(root(MATRIX), 300, 200);
        primaryStage.setTitle("Uhr");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
