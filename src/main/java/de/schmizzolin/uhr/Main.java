package de.schmizzolin.uhr;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        StackPane clock = new Clock();

        Scene scene = new Scene(clock, 360, 120);
        stage.setTitle("Uhr");
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        //stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.setFullScreen(true);

        contextMenu(scene);
        movable(scene);

        stage.show();
    }

    private void contextMenu(Scene scene) {
        var menu = new ContextMenu();
        var title = new MenuItem("Uhr");
        title.setDisable(true);

        var fullscreen = new MenuItem("Fullscreen");
        fullscreen.setOnAction(e -> {
            Stage s = (Stage) scene.getWindow();
            s.setFullScreen(!s.isFullScreen());
        });

        var quit = new MenuItem("Quit");
        quit.setOnAction(e -> Platform.exit());
        menu.getItems().addAll(title, new SeparatorMenuItem(), fullscreen, quit);
        scene.setOnContextMenuRequested(e -> menu.show(scene.getRoot(), e.getScreenX(), e.getScreenY()));
    }

    private void movable(Scene scene) {
        final double[] dragDelta = new double[2];
        scene.setOnMousePressed(e -> {
            dragDelta[0] = scene.getWindow().getX() - e.getScreenX();
            dragDelta[1] = scene.getWindow().getY() - e.getScreenY();
        });
        scene.setOnMouseDragged(e -> {
            scene.getWindow().setX(e.getScreenX() + dragDelta[0]);
            scene.getWindow().setY(e.getScreenY() + dragDelta[1]);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
