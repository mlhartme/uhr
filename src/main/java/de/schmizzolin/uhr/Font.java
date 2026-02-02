package de.schmizzolin.uhr;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Map;

public class Font {
    private static final String[] DIGITS = { """
            x x x
            x   x
            x   x
            x   x
            x x x
            """, """
              x .
            x x .
              x .
              x .
            x x x
            """, """
            x x x
                x
              x .
            x . . 
            x x x
            """, """
            x x x
                x
              x x
                x
            x x x
            """, """
            x   x
            x   x
            x x x
                x
                x
            """, """
            x x x
            x . .
            x x x
                x
            x x x
            """, """
            x x x
            x . .
            x x x
            x   x
            x x x
            """, """
            x x x
                x
              x .
              x .
              x .
            """, """
            x x x
            x   x
            x x x
            x   x
            x x x
            """, """
            x x x
            x   x
            x x x
                x
            x x x
            """
    };

    private static final String COLON = """
            . . .
            . x .
            . . .
            . x .
            . . .
            """;

    public static Font create() {
        Font font = new Font(Color.BLUE, 20, 2, 8);
        font.add(':', COLON);
        for (int i = 0; i < DIGITS.length; i++) {
            font.add((char) ('0' + i), DIGITS[i]);
        }
        return font;
    }

    private final Map<Character, String> maps;
    private final Color color;
    private final int dotWidth;
    private final int dotSpace;
    private final int charSpace;

    public Font(Color color, int dotWidth, int dotSpace, int charSpace) {
        this.color = color;
        this.maps = new HashMap<>();
        this.dotWidth = dotWidth;
        this.dotSpace = dotSpace;
        this.charSpace = charSpace;
    }

    public void add(char character, String matrix) {
        maps.put(character, check(matrix));
    }

    private static final int HEIGHT = 5;
    private static final int WIDTH = 3;

    private static String check(String matrix) {
        String[] lines = matrix.split("\n");
        if (lines.length != HEIGHT) {
            throw new IllegalArgumentException("height: " + lines.length);
        }
        for (String line : lines) {
            if (line.length() != WIDTH * 2 - 1) {
                throw new IllegalArgumentException("width: " + line.length());
            }
            for (int i = 0; i < line.length(); i += 2) {
                var c = line.charAt(i);
                if (c != 'x' && c != ' ' && c != '.') {
                    throw new IllegalArgumentException("invalid char: " + c);
                }
            }
        }
        return matrix;
    }

    public Pane render(String str) {
        HBox hbox = new HBox(charSpace);
        for (int i = 0; i < str.length(); i++) {
            hbox.getChildren().add(render(str.charAt(i)));
        }
        return hbox;
    }

    private Pane render(char character) {
        VBox vbox = new VBox(dotSpace);
        String matrix = maps.get(character);
        if (matrix == null) {
            throw new IllegalArgumentException("unknown character: " + character);
        }
        String[] lines = matrix.split("\n");
        for (String line : lines) {
            HBox hbox = new HBox(dotSpace);
            for (int i = 0; i < line.length(); i += 2) {
                var c = line.charAt(i);
                if (c == 'x') {
                    Rectangle rect = new Rectangle(dotWidth, dotWidth, color);
                    hbox.getChildren().add(rect);
                } else if (c == ' ' || c == '.') {
                    Rectangle rect = new Rectangle(dotWidth, dotWidth, Color.TRANSPARENT);
                    hbox.getChildren().add(rect);
                }
            }
            vbox.getChildren().add(hbox);
        }
        return vbox;
    }
}
