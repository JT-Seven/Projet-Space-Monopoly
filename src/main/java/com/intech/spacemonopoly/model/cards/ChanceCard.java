package com.intech.spacemonopoly.model.cards;

import com.intech.spacemonopoly.model.Game;

import java.util.function.Consumer;

public enum ChanceCard {
    //CONTENU CARTE CHANCE (Sur position 7; 22 et 36)
    JAIL_ENTRY_CARD(game -> {
        game.getActivePlayer().setTurnsInJail(3);
        game.getActivePlayer().setJail(true);
        game.getActivePlayer().setPosition((byte) 30);
        game.getActivePlayer().setWasJailed(true);
    }),;
//    JAIL_EXIT_CARD(game -> {
//        game.getActivePlayer().incrementExitJailCard();
//    }),
//
//   TAKE_MONEY_CARD(game -> {
//        game.getActivePlayer().removeMoney(100);
//    }),
//
//    MOVE_CARD(game -> {
//        game.getActivePlayer().setStatusMoveCard(true);
//    });


    private final Consumer<Game> cardAction;
    ChanceCard(Consumer<Game> cardAction) {
        this.cardAction = cardAction;
    }
    public Consumer<Game> getCardAction() {
        return cardAction;
    }
}

