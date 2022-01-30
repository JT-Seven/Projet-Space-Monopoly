package com.intech.spacemonopoly.controller.animationmanagement.animations;

import com.intech.spacemonopoly.controller.BoardController;
import com.intech.spacemonopoly.controller.animationmanagement.Animation;
import com.intech.spacemonopoly.controller.animationmanagement.AnimationManager;
import com.intech.spacemonopoly.model.Player;
import com.intech.spacemonopoly.model.tiles.JailTile;
import com.intech.spacemonopoly.model.tiles.Tile;

public class JailTileAnimation extends Animation {

    public JailTileAnimation(AnimationManager animationManager) {
        super(animationManager);
    }

    @Override
    public void runAnimation(BoardController boardController, Player activePlayer, Tile playerTile) {
        if (boardController.getGame().getActivePlayer().getNbDouble() == 3) {
            boardController.getDescriptionAlerte().setText("Vous etes en prison car vous avez fais 3 double ! \nPour sortir faites un double \nou payer une amende ! ");
            boardController.getBtnConfirm().setVisible(true);
            boardController.getImageViewValidated().setVisible(true);
            boardController.getGame().getActivePlayer().setNbDouble(0);
            boardController.getAnimationSubController().animationShowAlertInfo();

        } else if (boardController.getGame().getActivePlayer().isJail() && boardController.getBtnNextTurn().isVisible()) {
            boardController.getImageViewValidated().setVisible(true);
            boardController.getDescriptionAlerte().setText("Vous etes en prison ! \nPour sortir faites un double \nou payer une amende ! ");
            boardController.getAnimationSubController().animationShowAlertInfo();

        } else {
            boardController.getDescriptionAlerte().setText("Vous etes en prison ! \nPour sortir faites un double \nou payer une amende ! ");
            boardController.getAnimationSubController().animationShowAlertInfoJail();
        }
        boardController.getAnimationSubController().animationCageDown();
        boardController.getSongSubController().jailSong();
        boardController.getBtnNextTurn().setVisible(false);
        boardController.getImageViewNextTurn().setVisible(false);
        boardController.getBtnConfirm().setVisible(false);

        super.playNextAnimationInQueue(boardController, activePlayer, playerTile);
    }

    @Override
    public boolean animationCondition(BoardController boardController, Player activePlayer, Tile playerTile) {
        return playerTile instanceof JailTile;
    }
}
