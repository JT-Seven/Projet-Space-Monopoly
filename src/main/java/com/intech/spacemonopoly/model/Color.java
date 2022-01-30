package com.intech.spacemonopoly.model;

public enum Color {

    ROUGE(javafx.scene.paint.Color.RED, 3),
    BLEU(javafx.scene.paint.Color.BLUE, 2),
    VERT(javafx.scene.paint.Color.GREEN, 3),
    ORANGE(javafx.scene.paint.Color.rgb(255,137,0), 3),
    VIOLET(javafx.scene.paint.Color.PURPLE, 3),
    CYAN(javafx.scene.paint.Color.rgb(17,162,238),3),
    FUSHIA(javafx.scene.paint.Color.rgb(253,156,253), 3),
    JAUNE(javafx.scene.paint.Color.rgb(217,174,0),3),
    NOIR(javafx.scene.paint.Color.BLACK, 0),
    MARRON(javafx.scene.paint.Color.rgb(128,20,218), 2),
    TRANSPARENT(javafx.scene.paint.Color.rgb(21,64,121), 0),
    BLANC(javafx.scene.paint.Color.WHITE, 0),
    ;

    private final javafx.scene.paint.Color color;
    private final int maxTiles;

    public javafx.scene.paint.Color getColor() {
        return color;
    }

    public int getMaxTiles() {
        return maxTiles;
    }

    Color(javafx.scene.paint.Color color, int maxHouses) {
        this.color = color;
        this.maxTiles = maxHouses;
    }

    public javafx.scene.paint.Color getJavaFxColor() {
        return this.color;
    }
}
