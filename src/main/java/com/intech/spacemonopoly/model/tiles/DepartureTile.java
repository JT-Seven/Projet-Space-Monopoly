package com.intech.spacemonopoly.model.tiles;

import com.intech.spacemonopoly.model.Game;

public class DepartureTile extends Tile {

    public DepartureTile(int x, int y) {
        super(TileType.START, x, y);
    }

    @Override
    public void tileAction(Game game) {
        if(game.getActivePlayer().isWasJailed() != true) {
            game.getActivePlayer().addMoney(400);
        }
    }

}
