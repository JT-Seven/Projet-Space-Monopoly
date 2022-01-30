package com.intech.spacemonopoly.model.tiles;

import com.intech.spacemonopoly.model.Game;

public class TaxesTile extends Tile {

    private final int taxesPrice;
    private final String description;
    private final String name;

    public TaxesTile(int x, int y, int taxesPrice, String description,String name) {
        super(TileType.TAXES,x, y);
        this.taxesPrice = taxesPrice;
        this.description = description;
        this.name = name;
    }

    @Override
    public void tileAction(Game game) {
        if (game.getActivePlayer().getMoney() < taxesPrice || game.getActivePlayer().getMoney() < 0) {
            game.removePlayerAndBoughtTile();
        } else {
            game.getActivePlayer().removeMoney(taxesPrice);
            game.addMoneyPlayerToBank(taxesPrice);
            System.out.println(getDescription());
        }

    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

}
