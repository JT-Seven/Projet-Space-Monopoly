package com.intech.spacemonopoly.controller.boardsubcontroller;

import com.intech.spacemonopoly.controller.BoardController;
import com.intech.spacemonopoly.model.Player;
import com.intech.spacemonopoly.model.tiles.*;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.util.Duration;
import lombok.Getter;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PawnSubController {

    private final BoardController boardController;
    @Getter
    private final HashMap<Player, Group> playerPawns;

    public PawnSubController(BoardController boardController) {
        this.boardController = boardController;
        this.playerPawns = new HashMap<>();
    }

    public void movePlayerAndPawn(Player player, byte numberOfTilesMoved) {
        int initialPosition = player.getPosition();
        player.move(numberOfTilesMoved);
        this.playMoveAnimation(player, initialPosition, player.getPosition());
    }

    public void setPawnPosition(Player player, int finalPosition) {
        this.removePawn(player);
        Tile tile = this.boardController.getGame().getBoard().getTile(finalPosition);

        this.boardController.getBoardGrid().add(this.getPlayerPawn(player), tile.getY(), tile.getX());
        setCorrectPawnPosition(this.getPlayerPawn(player), doesTileContainPawn(finalPosition));
    }

    private void removePawn(Player player) {
        this.boardController.getBoardGrid().getChildren().removeIf(node -> {
            if (node == null) return false;
            if (node.getId() == null) return false;
            return node.getId().equalsIgnoreCase(player.getPawnID());
        });
    }

    private int doesTileContainPawn(int position) {
        return (int) Arrays.stream(this.boardController.getGame().getPlayers())
                .filter(player -> player.getPosition() == position)
                .count();
    }

    private void playMoveAnimation(Player player, int initialPosition, int finalPosition) {
        boardController.getSongSubController().stopEffect();

        if(boardController.getGame().getActivePlayer().getNbDouble() == 3){
            boardController.getGame().getActivePlayer().setPosition((byte) 30);

        }

        if (initialPosition == finalPosition) return;
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.3), this.getPlayerPawn(player));

        AtomicInteger finalInitialPosition = new AtomicInteger(initialPosition);
            translateTransition.setOnFinished(event -> {
            if (finalInitialPosition.get() == finalPosition) {
                setPawnPosition(player, finalPosition);
                boardController.getScalePaneAnimationCardsAlertInfo().setStyle("-fx-background-color: " + BoardController.toRGBCode(boardController.getGame().getActivePlayer().getColor().getJavaFxColor())+ ";");
                boardController.getFadePaneAnimationCardsAlertInfo().setStyle("-fx-background-color: " + BoardController.toRGBCode(boardController.getGame().getActivePlayer().getColor().getJavaFxColor())+ ";");
                boardController.getLabelNamePlayerAlertInfo().setText(boardController.getGame().getActivePlayer().getName());

                Tile tile = boardController.getGame().getBoard().getTile(player.getPosition());

                tile.tileAction(boardController.getGame());
                boardController.updateMoney();
                boardController.updatePlayerAndPawn();
                boardController.updateTile();

                boardController.getButtonSubController().allButtonDisable();

                boardController.getAnimationManager().fillAnimationQueue(boardController, player, tile);

                boardController.getAnimationManager().playNextAnimation(boardController, player, tile);
                return;
            }
            int test = playOneAnimation(translateTransition, finalInitialPosition.get());
            finalInitialPosition.set(test);
                if (finalInitialPosition.get() < 10) {
                    RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.3), getPlayerPawn(player));
                    rotateTransition.setFromAngle(getPlayerPawn(player).getRotate());
                    rotateTransition.setToAngle(0);
                    rotateTransition.play();
                } else if (finalInitialPosition.get() < 20) {
                    RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.3), getPlayerPawn(player));
                    rotateTransition.setFromAngle(getPlayerPawn(player).getRotate());
                    rotateTransition.setToAngle(90);
                    rotateTransition.play();
                } else if (finalInitialPosition.get() < 30) {
                    RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.3), getPlayerPawn(player));
                    rotateTransition.setFromAngle(getPlayerPawn(player).getRotate());
                    rotateTransition.setToAngle(180);
                    rotateTransition.play();
                } else if (finalInitialPosition.get() < 40) {
                    RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.3), getPlayerPawn(player));
                    rotateTransition.setFromAngle(getPlayerPawn(player).getRotate());
                    rotateTransition.setToAngle(270);
                    rotateTransition.play();
                }
        });
        translateTransition.play();
       /* boardController.getLogger().info("Information après lancer :");
        boardController.getLogger().info("Argent : " + boardController.getGame().getActivePlayer().getMoney() );
        boardController.getLogger().info("Nombre de double : " + boardController.getGame().getActivePlayer().getNbDouble() );
        boardController.getLogger().info( boardController.getGame().getActivePlayer().getName() + " Was jailed : " +  boardController.getGame().getActivePlayer().isWasJailed());
        boardController.getLogger().info( boardController.getGame().getActivePlayer().getName() + " jail status: " +  boardController.getGame().getActivePlayer().isJail());
        boardController.getLogger().info("turns in jail: " +  boardController.getGame().getActivePlayer().getTurnsInJail());
        boardController.getLogger().info("Carte sortie de prison : " +  boardController.getGame().getActivePlayer().getExitJailCard());
        boardController.getLogger().info("Offre : " +  boardController.getGame().getActivePlayer().getOffer());
        boardController.getLogger().info("Fournisseur : " +  boardController.getGame().getActivePlayer().getSupplier());
        boardController.getLogger().info("Tentative de vente : " +  boardController.getGame().getActivePlayer().getSellTentative());
        */
    }

    private int playOneAnimation(TranslateTransition translateTransition, int initialPosition) {
        AbstractMap.SimpleEntry<Integer, Integer> translateTransitionXAndY = getCorrectTranslateTransition(initialPosition);

        translateTransition.setByX(translateTransitionXAndY.getKey());
        translateTransition.setByY(translateTransitionXAndY.getValue());

        initialPosition = boardController.getNextTilePosition(initialPosition);

        translateTransition.play();

        return initialPosition;
    }

    private AbstractMap.SimpleEntry<Integer, Integer> getCorrectTranslateTransition(int initialPosition) {
        if (initialPosition <= 9) {
            return new AbstractMap.SimpleEntry<>(-68, 0);
        } else if (initialPosition <= 19) {
            return new AbstractMap.SimpleEntry<>(0, -65);
        } else if (initialPosition <= 29){
            return new AbstractMap.SimpleEntry<>(68, 0);
        } else {
            return new AbstractMap.SimpleEntry<>(0, 65);
        }
    }

    public void setCorrectPawnPosition(Group pawn, int amountOfPawnsInTile) {
        switch (amountOfPawnsInTile) {
            case 1:
                pawn.setTranslateY(-15);
                pawn.setTranslateX(-17);
                break;
            case 2:
                pawn.setTranslateY(-15);
                pawn.setTranslateX(7);
                break;
            case 3:
                pawn.setTranslateY(10);
                pawn.setTranslateX(-19);
                break;
            case 4:
                pawn.setTranslateY(10);
                pawn.setTranslateX(5);
                break;
        }
    }

    public void setPlayerPawn(Player player, Group pawn) {
        this.playerPawns.put(player, pawn);
    }

    public Group getPlayerPawn(Player player) {
        return this.getPlayerPawns().get(player);
    }
}
