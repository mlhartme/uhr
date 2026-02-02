package de.schmizzolin.uhr;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.Locale;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Font font = Font.create();
        javafx.scene.layout.StackPane root = new javafx.scene.layout.StackPane();
        Scene scene = new Scene(root, 360, 120);
        stage.setTitle("Uhr");
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

    public static void main(String[] args) {
        launch(args);
    }
}
