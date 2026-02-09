package de.schmizzolin.uhr;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.time.LocalTime;
import java.util.Locale;

// Beispiel für eine eigene Pane mit automatischer Aktualisierung
public class Clock extends StackPane {
    private Font font;

    public Clock() {
        updateFont();
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: black;");
        setBorder(new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                null,
                new BorderWidths(10)
        )));

        this.widthProperty().addListener((obs, oldVal, newVal) -> {
            updateFont();
        });
        this.heightProperty().addListener((obs, oldVal, newVal) -> {
            updateFont();
        });
       refresh();
    }

    private void updateFont() {
        int dotSize;
        // we have 5 characters, each of them with 3x5 dots -> display ration is 15:5
        if (getWidth() / getHeight() > 15.0 / 5.0) {
            dotSize = (int) (getHeight() / 5.0);
        } else {
            dotSize = (int) (getWidth() / 15.0);
        }
        this.font = Font.create(Color.WHITE, dotSize, 2, 8);
    }

    private void refresh() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> updateTime()),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
        timeline.play();
    }

    private void updateTime() {
        LocalTime time = LocalTime.now();
        String str = String.format(Locale.GERMAN, "%02d:%02d", time.getHour(), time.getMinute());
        getChildren().setAll(font.render(str));
    }
}
