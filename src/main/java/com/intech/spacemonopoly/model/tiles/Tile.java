package com.intech.spacemonopoly.model.tiles;

import com.intech.spacemonopoly.model.Game;

public abstract class Tile {

    private final TileType tileType;
    private final int x;
    private final int y;

    protected Tile(TileType tileType, int x, int y) {
        this.tileType = tileType;
        this.x = x;
        this.y = y;
    }

    public abstract void tileAction(Game game);

    public TileType getTileType() {
        return tileType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
