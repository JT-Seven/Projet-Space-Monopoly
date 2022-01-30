package com.intech.spacemonopoly.controller.boardsubcontroller;

import com.intech.spacemonopoly.controller.BoardController;
import com.intech.spacemonopoly.model.tiles.BuyableTile;
import com.intech.spacemonopoly.model.tiles.Tile;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXLabel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class BuyableSubController {
    private final BoardController boardController;

    public BuyableSubController(BoardController boardController) {
        this.boardController = boardController;
    }

    public void onClickBuyBuyableTile() {
        Tile tile = this.boardController.getGame().getBoard().getTile(this.boardController.getGame().getActivePlayer().getPosition());
        if (tile instanceof BuyableTile) {
            BuyableTile buyableTile = (BuyableTile) tile;
            if (boardController.getGame().getActivePlayer().getMoney() < buyableTile.getTilePrice()) {
                this.boardController.getPaneBuyableTile().setVisible(false);
                this.boardController.getPaneBuyableTileAnim().setVisible(false);
                boardController.getBtnNextTurn().setDisable(true);
                boardController.getLabelNamePlayerMissMoney().setText(boardController.getGame().getActivePlayer().getName());
                boardController.getAnimationSubController().animationCardMissMoneyArrive();
                return;
            } else if (boardController.getGame().getActivePlayer().getMoney() < 0){
                boardController.getGame().removePlayerAndBoughtTile();
                return;
            }
            boardController.getGame().getActivePlayer().removeMoney(buyableTile.getTilePrice());
            boardController.getGame().getActivePlayer().addBoughtTileToPlayer((BuyableTile) boardController.getGame().getBoard().getTile(boardController.getGame().getActivePlayer().getPosition()));
            buyableTile.setLandlord(boardController.getGame().getActivePlayer());
            this.boardController.getAnimationSubController().animationOnClickYesBuyable(this.boardController.getGame().getActivePlayer().getPosition(), buyableTile);
        }
        this.boardController.getAnimationSubController().animationCardBuyableExit();
        boardController.updateMoney();
        boardController.updatePlayerAndPawn();
        boardController.updateTile();
    }

    public void onClickDontBuyBuyableTile() {
        this.boardController.getAnimationSubController().animationCardBuyableExit();
    }

    public void onClickPoseProperty(BuyableTile buyableTile) {
        int nbHotels = buyableTile.getNbHotels();
        int nbHouses = buyableTile.getNbHouses();

        if (boardController.getGame().getActivePlayer().getMoney() < buyableTile.getHousePrice()) {
            boardController.getBtnNextTurn().setDisable(true);
            boardController.getLabelNamePlayerMissMoney().setText(boardController.getGame().getActivePlayer().getName());
            boardController.getAnimationSubController().animationCardMissMoneyArrive();
            boardController.getVBoxAddProperty().setVisible(false);
//            boardController.getButtonSubController().allButtonDisable();
            return;
        }

        if (buyableTile.getNbHouses() > 3 || buyableTile.getNbHotels() != 0) {
            nbHotels += 1;
            buyableTile.setNbHouses(0);
            buyableTile.setNbHotels(nbHotels);
            boardController.getGame().getActivePlayer().removeMoney(buyableTile.getHotelPrice());
            //On mettra un set pour ajouter un element graphique qui represente une maison
        } else {
            nbHouses += 1;
            buyableTile.setNbHouses(nbHouses);
            boardController.getGame().getActivePlayer().removeMoney(buyableTile.getHousePrice());
            //On mettra un set pour ajouter un element graphique qui represente un hotel
        }
        boardController.updateMoney();
        boardController.getVBoxAddProperty().setVisible(false);
        boardController.getBtnProperty().setDisable(false);
    }

    public void showBuyableTile() {

    }

    public HBox updatedAndAddHouse(BuyableTile information, com.intech.spacemonopoly.model.Color color) {
        MFXLabel informationsTile2 = new MFXLabel();
        informationsTile2.setTranslateX(25);
        informationsTile2.setText("Case " + information.getName());
        informationsTile2.setLabelAlignment(Pos.CENTER);
        informationsTile2.setTextFill(Color.WHITE);

        MFXButton btnCaseFocused2 = new MFXButton();
        btnCaseFocused2.setPrefWidth(121);
        btnCaseFocused2.setPrefHeight(40);
        btnCaseFocused2.setTranslateX(165);
        btnCaseFocused2.setAlignment(Pos.CENTER);
        btnCaseFocused2.setText("Poser");
        btnCaseFocused2.setId("buyTile");
        btnCaseFocused2.setStyle("-fx-border-color: " +
                boardController.toRGBCode(color.getJavaFxColor()) + ";" +
                "-fx-background-color: transparent;" +
                " -fx-background-radius: 8;" +
                " -fx-text-fill: white;" +
                " -fx-border-radius: 8;");
        btnCaseFocused2.setOnMouseClicked(event -> {
            boardController.getBuyableSubController().onClickPoseProperty(information);
        });
        HBox nameTile2 = new HBox();
        nameTile2.setStyle("-fx-background-color:  #01152a; -fx-background-radius: 8;");
        nameTile2.setAlignment(Pos.CENTER_LEFT);
        nameTile2.setPrefWidth(392);
        nameTile2.setPrefHeight(69);
        nameTile2.setPadding(new Insets(15, 15, 15, 15));
        nameTile2.getChildren().addAll(informationsTile2, btnCaseFocused2);
        return nameTile2;
    }
}
