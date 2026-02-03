package de.schmizzolin.uhr;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.Locale;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Font font = Font.create(Color.WHITE);
        javafx.scene.layout.StackPane root = new javafx.scene.layout.StackPane();
        movable(stage, root);
        Scene scene = new Scene(root, 360, 120);
        stage.setTitle("Uhr");
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.initStyle(javafx.stage.StageStyle.UNDECORATED);
        stage.show();

        javafx.animation.Timeline timeline = new javafx.animation.Timeline(
                new javafx.animation.KeyFrame(javafx.util.Duration.seconds(0), e -> {
                    LocalTime time = LocalTime.now();
                    String str = String.format(Locale.GERMAN, "%02d:%02d", time.getHour(), time.getMinute());
                    root.getChildren().setAll(font.render(str));
                }),
                new javafx.animation.KeyFrame(javafx.util.Duration.seconds(1))
        );
        timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
        timeline.play();
    }

    private void movable(Stage stage, StackPane root) {
        final double[] dragDelta = new double[2];
        root.setOnMousePressed(e -> {
            dragDelta[0] = stage.getX() - e.getScreenX();
            dragDelta[1] = stage.getY() - e.getScreenY();
        });
        root.setOnMouseDragged(e -> {
            stage.setX(e.getScreenX() + dragDelta[0]);
            stage.setY(e.getScreenY() + dragDelta[1]);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
