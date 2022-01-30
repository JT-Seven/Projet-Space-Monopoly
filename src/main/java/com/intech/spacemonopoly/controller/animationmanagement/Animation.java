package com.intech.spacemonopoly.controller.animationmanagement;

import com.intech.spacemonopoly.controller.BoardController;
import com.intech.spacemonopoly.model.Player;
import com.intech.spacemonopoly.model.tiles.Tile;

public abstract class Animation {

    private final AnimationManager animationManager;

    public Animation(AnimationManager animationManager) {
        this.animationManager = animationManager;
    }

    public abstract void runAnimation(BoardController boardController, Player activePlayer, Tile playerTile);

    public abstract boolean animationCondition(BoardController boardController, Player activePlayer, Tile playerTile);

    public void playNextAnimationInQueue(BoardController boardController, Player activePlayer, Tile playerTile) {
        this.animationManager.playNextAnimation(boardController, activePlayer, playerTile);
    }
}
