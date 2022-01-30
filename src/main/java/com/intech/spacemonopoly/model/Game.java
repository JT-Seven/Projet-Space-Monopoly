package com.intech.spacemonopoly.model;

import com.intech.spacemonopoly.model.tiles.BuyableTile;
import lombok.Getter;
import lombok.Setter;

import java.util.AbstractMap;
import java.util.ArrayList;

@Getter @Setter
public class Game {

    private final Board board;
    private final Player[] players;
    private int turn;
    private int bank;
    private AbstractMap.SimpleEntry<Integer, Integer> lastThrowDices;
    private int maxPlayers;

    public Game(Player[] players) {
        this.board = new Board();
        this.players = players;
        this.maxPlayers = players.length;
        this.turn = 0;
        this.bank = 0;
    }

    public AbstractMap.SimpleEntry<Integer, Integer> throwDice() {
//        int randomNumber1 = (int) (Math.random() * 6) + 1;
//        int randomNumber2 = (int) (Math.random() * 6) + 1;

        int randomNumber1 = 0;
        int randomNumber2 = 0;

        if (turn == 0){
            randomNumber1 = 1;
            randomNumber2 = 1;

        } else if (turn == 1){
            randomNumber1 = 3;
            randomNumber2 = 3;

        } else if (turn == 2){
            randomNumber1 = 4;
            randomNumber2 = 3;

        } else if (turn == 3){
            randomNumber1 = 4;
            randomNumber2 = 3;
        }

        this.lastThrowDices = new AbstractMap.SimpleEntry<>(randomNumber1, randomNumber2);
        return this.lastThrowDices;
    }

    public void nextTurn() {
        if(!getActivePlayer().isFaillite() && getActivePlayer().isStatusMoveCard()) {
            getActivePlayer().setStatusMoveCard(false);
            this.turn++;
            this.turn--;
            return;
        }

        if (this.turn == this.maxPlayers - 1) {
            this.turn = 0;
            return;
        }
        this.turn++;
    }

    public void addMoneyPlayerToBank(int amount) {
        this.bank += amount;
    }

    public void addMoneyBankToPlayer(int bank) {
        this.bank = bank;
        getActivePlayer().setMoney(getActivePlayer().getMoney() + bank);
        this.bank = 0;
    }

    public int getPlayerAmountOfBoughtTiles(Color color, Player player) {
        ArrayList<BuyableTile> tabtiles = player.getBoughtTile();
        int amount = 0;
        for (BuyableTile tile : tabtiles) {
            if (color != tile.getColor()) {
                continue;
            }
            amount++;
        }
        return amount;
    }

    public boolean verifyIfPlayerHaveFamily(int numberOfTile, Color color) {
        return numberOfTile == color.getMaxTiles();
    }

    public Board getBoard() {
        return board;
    }

    public int getBank() {
        return bank;
    }

    public Player getActivePlayer() {
        return this.players[this.turn];
    }

    public Player[] getPlayers() {
        return players;
    }

    public Player getPlayer(int index) {
        try {
            return getPlayers()[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public void removePlayerAndBoughtTile() {
        for (BuyableTile buyableTile : getActivePlayer().getBoughtTile()) {
            buyableTile.setLandlord(null);
            buyableTile.setNbHotels(0);
            buyableTile.setNbHouses(0);
        }
        getActivePlayer().getBoughtTile().clear();
        getActivePlayer().setFaillite(true);
    }

    public int getTurn() {
        return turn;
    }
}




