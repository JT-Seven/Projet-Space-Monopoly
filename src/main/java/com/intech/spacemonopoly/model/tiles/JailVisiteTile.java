package com.intech.spacemonopoly.model.tiles;

import com.intech.spacemonopoly.model.Game;

public class JailVisiteTile extends Tile{

    public JailVisiteTile(int x, int y) {
        super(TileType.JAIL_VISIT, x, y);
    }

    @Override
    public void tileAction(Game game) {
    }
}
