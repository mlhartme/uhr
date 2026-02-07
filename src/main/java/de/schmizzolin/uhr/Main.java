package de.schmizzolin.uhr;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.time.LocalTime;
import java.util.Locale;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: black;");
        root.setAlignment(Pos.CENTER);
        root.setBorder(new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                null,
                new BorderWidths(10)
        )));

        Scene scene = new Scene(root, 360, 120);
        stage.setTitle("Uhr");
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.initStyle(StageStyle.UNDECORATED);

        contextMenu(scene, root);
        movable(stage, root);
        refresh(root, Font.create(Color.WHITE));

        stage.show();
    }

    private void refresh(StackPane root, Font font) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> {
                    LocalTime time = LocalTime.now();
                    String str = String.format(Locale.GERMAN, "%02d:%02d", time.getHour(), time.getMinute());
                    root.getChildren().setAll(font.render(str));
                }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
        timeline.play();
    }

    private void contextMenu(Scene scene, StackPane root) {
        var menu = new ContextMenu();
        var fullscreen = new MenuItem("Fullscreen");
        fullscreen.setOnAction(e -> {
            Stage s = (Stage) root.getScene().getWindow();
            s.setFullScreen(!s.isFullScreen());
        });

        var quit = new MenuItem("Quit");
        quit.setOnAction(e -> Platform.exit());
        menu.getItems().addAll(fullscreen, quit);
        scene.setOnContextMenuRequested(e -> menu.show(root, e.getScreenX(), e.getScreenY()));
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
