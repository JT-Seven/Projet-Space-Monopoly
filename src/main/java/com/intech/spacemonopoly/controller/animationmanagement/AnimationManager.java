package com.intech.spacemonopoly.controller.animationmanagement;

import com.intech.spacemonopoly.controller.BoardController;
import com.intech.spacemonopoly.controller.animationmanagement.animations.*;
import com.intech.spacemonopoly.model.Player;
import com.intech.spacemonopoly.model.tiles.Tile;
import lombok.Getter;

import java.util.*;

public class AnimationManager {

    @Getter
    // ceci est une liste que ne peux pas avoir 2 elements identiques
    private final Set<Animation> animations;

    // http://tutorials.jenkov.com/java-collections/queue.html
    // ceci est une file d'attente pour les animations
    private final Queue<Animation> animationQueue;

    public AnimationManager() {
        this.animationQueue = new LinkedList<>();
        this.animations = new LinkedHashSet<>();
        this.registerAnimations();
    }

    private void registerAnimations() {
        animations.addAll(Arrays.asList(
                new NewToursAnimation(this),
                new BuyableTileAnimation(this),
                new ChanceAndCommunityTileAnimation(this),
                new JailTileAnimation(this),
                new TaxesTileAnimation(this)
        ));
    }

    public void fillAnimationQueue(BoardController boardController, Player activePlayer, Tile playerTile) {
        animations.forEach(animation -> {
            if (animation.animationCondition(boardController, activePlayer, playerTile)) {
                animationQueue.add(animation);
            }
        });
    }

    public void playNextAnimation(BoardController boardController, Player activePlayer, Tile playerTile) {
        if (animationQueue.peek() != null) {
            animationQueue.poll().runAnimation(boardController, activePlayer, playerTile);
            return;
        }
        this.enableNextTurnButtons(boardController);
    }

    public void enableNextTurnButtons(BoardController boardController) {
        boardController.getButtonSubController().allButtonClickable();
    }
}
