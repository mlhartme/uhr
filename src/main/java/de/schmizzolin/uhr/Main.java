package de.schmizzolin.uhr;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.Locale;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        LocalTime time = LocalTime.now();
        Font font = Font.create();
        String str = String.format(Locale.GERMAN, "%02d:%02d", time.getHour(), time.getMinute());
        Scene scene = new Scene(font.render(str), 300, 200);
        primaryStage.setTitle("Uhr");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
