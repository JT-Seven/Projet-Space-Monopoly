package com.intech.spacemonopoly.model;

import com.intech.spacemonopoly.model.tiles.BuyableTile;

import java.util.ArrayList;

public class Player {

    private final String name;
    private final Color color;
    private int money;
    private boolean jail;
    private byte position;
    private int turnsInJail;
    private final ArrayList<BuyableTile> boughtTile;
    private boolean statusMyExitJailCard;
    private boolean statusMoveCard;
    private int myAmountCards;
    private String myChanceCards;
    private String myCommunityChest;
    private int chanceOrCommunityChest;
    private boolean newTours;
    private int exitJailCard;
    private boolean wasJailed;
    private int nbDouble;
    private int offer;
    private ArrayList<String> supplier;
    private boolean sellTentative;
    private boolean faillite;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.money = 1500;
        this.jail = false;
        this.position = 0;
        this.turnsInJail = 0;
        this.boughtTile = new ArrayList<>();
        this.statusMyExitJailCard = false;
        this.statusMoveCard = false;
        this.myAmountCards = 0;
        this.myChanceCards = null;
        this.myCommunityChest = null;
        this.newTours = false;
        this.exitJailCard = 0;
        this.wasJailed = false;
        this.nbDouble = 0;
        this.offer = 0;
        this.supplier = new ArrayList<>();
        this.sellTentative = false;
        this.faillite = false;
        this.chanceOrCommunityChest = 0;
    }

    public void move(byte amount) {
        setNewTours(false);
        if (position < 40) {
            if (position + amount > 40) {
                setNewTours(true);
                position = (byte) (position - 40 + amount);
                if(isWasJailed() != true) {
                    addMoney(200);
                }
                return;
            }
            position = (byte) (position + amount);
        }

    }

    public void addBoughtTileToPlayer(BuyableTile tile) {
        this.boughtTile.add(tile);
    }

    public void addMoney(int amount){
        money += amount;
    }

    public void removeMoney(int amount) {
        money -= amount;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int getMoney() {
        return money;
    }

    public boolean isJail() {
        return jail;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setJail(boolean jail) {
        this.jail = jail;
    }

    public byte getPosition() {
        return position;
    }

    public void setTurnsInJail(int turnsInJail) {
        this.turnsInJail = turnsInJail;
    }

    public int getTurnsInJail() {
        return this.turnsInJail;
    }

    public void decrementTurnsInJail() {
        --this.turnsInJail;
    }

    public ArrayList<BuyableTile> getBoughtTile() {
        return boughtTile;
    }

    public String getPawnID() {
        return "pawn-" + name;
    }

    public boolean getNbFamily() {
        return true;
    }

    public void setStatusMyExitJailCard(boolean statusMyExitJailCard) {
        this.statusMyExitJailCard = statusMyExitJailCard;
    }

    public void setPosition(byte b) {
        this.position = b;
    }

    public boolean getStatusMyExitJailCard() {
        return statusMyExitJailCard;
    }

    public boolean isStatusMoveCard() {
        return statusMoveCard;
    }

    public void setStatusMoveCard(boolean statusMoveCard) {
        this.statusMoveCard = statusMoveCard;
    }

    public int getMyAmountCards() {
        return myAmountCards;
    }

    //public void incrementMyCards() {
      //  myAmountCards++;
  //  }

   // public void decrementMyCards() {
     //   myAmountCards--;
   // }

    public String getMyChanceCards() {
        return myChanceCards;
    }

    public String getMyCommunityChest() {
        return myCommunityChest;
    }

    public void setMyChanceCards(String myChanceCards) {
        this.myChanceCards = myChanceCards;
    }

    public void setMyCommunityChest(String myCommunityChest) {
        this.myCommunityChest = myCommunityChest;
    }

    public boolean getNewTours() {
        return newTours;
    }

    public void setNewTours(boolean newTours) {
        this.newTours = newTours;
    }

    public void decrementExitJailCard() {
         exitJailCard--;
    }

    public int getExitJailCard() {
        return exitJailCard;
    }

    public void incrementExitJailCard() {
         exitJailCard++;
    }

    public boolean isWasJailed() {
        return wasJailed;
    }

    public void setWasJailed(boolean wasJailed) {
        this.wasJailed = wasJailed;
    }

    public void incrementnbDouble() {
        nbDouble++;
    }

    public int getNbDouble() {
        return nbDouble;
    }

    public void incrementOffer() {
        this.offer++;
    }

    public void decrementOffer() {
        this.offer--;
    }

    public int getOffer() {
        return offer;
    }

    public ArrayList<String> getSupplier() {
        return supplier;
    }

    public void addSupplier(String newSupplioer) {
        supplier.add(newSupplioer);
    }

    public void setNbDouble(int nbDouble) {
        this.nbDouble = nbDouble;
    }

    public boolean getSellTentative() {
        return sellTentative;
    }

    public void setSellTentative(boolean sellTentative) {
        this.sellTentative = sellTentative;
    }

    public void setFaillite(boolean faillite) {
        this.faillite = faillite;
    }

    public boolean isFaillite() {
        return faillite;
    }
    public int getChanceOrCommunityChest() {
        return chanceOrCommunityChest;
    }

    public void setChanceOrCommunityChest(int chanceOrCommunityChest) {
        this.chanceOrCommunityChest = chanceOrCommunityChest;
    }

}
