package com.intech.spacemonopoly.model.tiles;

import com.intech.spacemonopoly.model.Game;

public class JailTile extends Tile {

    public JailTile(int x, int y) {
        super(TileType.JAIL, x, y);
    }

    @Override
    public void tileAction(Game game) {
        game.getActivePlayer().setJail(true);
        game.getActivePlayer().setTurnsInJail(3);
        game.getActivePlayer().setWasJailed(true);
    }
}