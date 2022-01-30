package com.intech.spacemonopoly.controller.animationmanagement.animations;

import animatefx.animation.FadeIn;
import animatefx.animation.RubberBand;
import com.intech.spacemonopoly.controller.BoardController;
import com.intech.spacemonopoly.controller.animationmanagement.Animation;
import com.intech.spacemonopoly.controller.animationmanagement.AnimationManager;
import com.intech.spacemonopoly.model.Player;
import com.intech.spacemonopoly.model.tiles.TaxesTile;
import com.intech.spacemonopoly.model.tiles.Tile;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class TaxesTileAnimation extends Animation {

    public TaxesTileAnimation(AnimationManager animationManager) {
        super(animationManager);
    }

    @Override
    public void runAnimation(BoardController boardController, Player activePlayer, Tile playerTile) {
        boardController.getButtonSubController().allButtonDisable();
        boardController.getSongSubController().notificationSong();
        boardController.getDescriptionAlerte().setText(((TaxesTile) playerTile).getDescription());
        boardController.getCircleBtnThrowDice().setVisible(false);
        boardController.getPaneAlerteInfo().setVisible(true);
        boardController.getBtnNextTurn().setVisible(false);
        boardController.getImageViewNextTurn().setVisible(false);
        boardController.getImageViewValidated().setVisible(true);

        RubberBand rubberBand = new RubberBand(boardController.getPaneAlerteInfo());
        rubberBand.play();
        rubberBand.setOnFinished(event -> {
            boardController.getPaneAnimationNamePlayerAlertInfo().setVisible(true);

            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1));
            fadeTransition.setNode(boardController.getBoxDescriptionAlertInfo());
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(10);

            TranslateTransition bracketOne = new TranslateTransition(Duration.seconds(1));
            bracketOne.setToX(-295);
            bracketOne.setNode(boardController.getBracketAlertInfo1());
            TranslateTransition bracketTwo = new TranslateTransition(Duration.seconds(1));
            bracketTwo.setToX(255);
            bracketTwo.setNode(boardController.getBracketAlertInfo2());

            ScaleTransition scaleT = new ScaleTransition(Duration.seconds(1));
            scaleT.setNode(boardController.getScalePaneAnimationCardsAlertInfo());
            scaleT.setFromX(1);
            scaleT.setToX(12);

            ParallelTransition pt2 = new ParallelTransition(bracketOne, bracketTwo, scaleT, fadeTransition);
            pt2.play();
            pt2.setOnFinished(event2 -> {
                FadeIn fadeT3 = new FadeIn(boardController.getFadePaneAnimationCardsAlertInfo());
                fadeT3.setSpeed(2);
                fadeT3.play();
                boardController.getLogger().info(" Etat joueur actif : " + boardController.getGame().getActivePlayer().isJail());
                fadeT3.setOnFinished(event3 -> {
                    boardController.getBtnConfirm().setVisible(true);
                    boardController.getAnimationSubController().hoverButtonGame(boardController.getBtnConfirm(), boardController.getCircleBtnConfirm());
                    super.playNextAnimationInQueue(boardController, activePlayer, playerTile);
                });
            });
        });
    }

    @Override
    public boolean animationCondition(BoardController boardController, Player activePlayer, Tile playerTile) {
        return playerTile instanceof TaxesTile;
    }
}
