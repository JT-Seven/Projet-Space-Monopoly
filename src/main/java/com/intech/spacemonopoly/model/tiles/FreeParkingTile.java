package com.intech.spacemonopoly.model.tiles;

import com.intech.spacemonopoly.model.Game;

public class FreeParkingTile extends Tile{

    public FreeParkingTile(int x, int y) {
        super(TileType.FREE_PARKING, x, y);
    }

    @Override
    public void tileAction(Game game) {
        game.addMoneyBankToPlayer(game.getBank());
    }
}
