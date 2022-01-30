package com.intech.spacemonopoly.model.tiles;

import com.intech.spacemonopoly.model.Color;
import com.intech.spacemonopoly.model.Game;
import com.intech.spacemonopoly.model.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter

public class BuyableTile extends Tile{

    private String name;
    private Color color;
    private final int tilePrice;
    private final int housePrice;
    private final int hotelPrice;
    private int rentPrice;
    private int rentPriceHouse;
    private int rentPriceHotel;
    private int nbHouses = 0;
    private int nbHotels = 0;
    private Player landlord;

    public BuyableTile(int x, int y, String name, Color color, int tilePrice, int housePrice, int hotelPrice, int rentPrice, int rentPriceHouse, int rentPriceHotel, Player landlord) {
        super(TileType.BUYABLE, x, y);
        this.name = name;
        this.tilePrice = tilePrice;
        this.color = color;
        this.housePrice = housePrice;
        this.hotelPrice = hotelPrice;
        this.rentPrice = rentPrice;
        this.rentPriceHouse = rentPriceHouse;
        this.rentPriceHotel = rentPriceHotel;
        this.landlord = landlord;
    }

    public boolean landlordPossesion() {
        boolean twoTile = false;
        boolean electricity = false;
        boolean water = false;

        ArrayList<BuyableTile> tabtiles = landlord.getBoughtTile();

        for (BuyableTile tile : tabtiles) {
            if (tile.getName().contains("Electricity")) {
                electricity = true;
            }
            if (tile.getName().contains("Water")) {
                water = true;
            }
        }
        if (electricity && water) {
            twoTile = true;
        }
        return twoTile;
    }

    @Override
    public void tileAction(Game game) {
        if (this.landlord == null) return;

        if ((game.getActivePlayer().getPosition() == 12 || game.getActivePlayer().getPosition() == 28) && landlordPossesion()) {
            this.rentPrice = (game.getLastThrowDices().getKey() + game.getLastThrowDices().getValue()) * 10;
        } else if(game.getActivePlayer().getPosition() == 12 || game.getActivePlayer().getPosition() == 28) {
            this.rentPrice = (game.getLastThrowDices().getKey() + game.getLastThrowDices().getValue()) * 4;
        }
        System.out.println(landlordPossesion());

        if (this.nbHouses != 0 || this.nbHotels != 0) {
            this.rentPriceHouse = this.nbHouses * rentPriceHouse;
            this.rentPriceHotel = this.nbHotels * rentPriceHotel;
            if (game.getActivePlayer().getMoney() < this.rentPriceHouse || game.getActivePlayer().getMoney() < this.rentPriceHotel) {
                game.removePlayerAndBoughtTile();
            } else {
                game.getActivePlayer().removeMoney(this.rentPriceHouse);
                this.landlord.addMoney(this.rentPriceHouse);
                game.getActivePlayer().removeMoney(this.rentPriceHotel);
                this.landlord.addMoney(this.rentPriceHotel);
            }
            return;
        }

        if (game.getActivePlayer().getMoney() < this.rentPrice) {
            game.removePlayerAndBoughtTile();
        } else {
            game.getActivePlayer().removeMoney(this.rentPrice);
            this.landlord.addMoney(this.rentPrice);
        }
    }
}