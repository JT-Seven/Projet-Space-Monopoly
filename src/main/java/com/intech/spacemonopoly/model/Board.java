package com.intech.spacemonopoly.model;


import com.intech.spacemonopoly.model.tiles.Tile;

public class Board {

    private final Tile[] tiles;

    public Board() {
        this.tiles = new Tile[40];
    }

    public void addTile(int index, Tile tile) {
        tiles[index] = tile;
    }

    public Tile getTile(int index) {
        return tiles[index];
    }
}