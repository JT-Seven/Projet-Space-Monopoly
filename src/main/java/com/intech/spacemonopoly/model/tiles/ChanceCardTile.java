package com.intech.spacemonopoly.model.tiles;

import com.intech.spacemonopoly.controller.BoardController;
import com.intech.spacemonopoly.model.Game;
import com.intech.spacemonopoly.model.cards.ChanceCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChanceCardTile extends Tile{
    private Logger logger;

    public ChanceCardTile(int x, int y) {
        super(TileType.CHANCE, x, y);
        this.logger = LoggerFactory.getLogger(ChanceCardTile.class);

    }

    @Override
    public void tileAction(Game game) {
        ChanceCard card = drawLuckCard();
        card.getCardAction().accept(game);
        game.getActivePlayer().setMyChanceCards(card.name());
        game.getActivePlayer().setChanceOrCommunityChest(1);
        logger.info("Type de carte chance : " + card.name());
    }

    public  ChanceCard drawLuckCard () {
        ChanceCard[] tabChanceCard = ChanceCard.values();
        double index = Math.floor(Math.random() * tabChanceCard.length);
        switch (tabChanceCard[(int)index]){
            case JAIL_ENTRY_CARD :
                logger.info("Je rentre en prison");
                break;

//            case JAIL_EXIT_CARD :
//                logger.info("J'ai une carte sortie de prison");
//                break;
//
//            case TAKE_MONEY_CARD :
//                logger.info("Je perds 20 dollars");
//                break;
//
//            case MOVE_CARD :
//                logger.info("Je rejoue");
//                break;
        }
        return tabChanceCard[(int)index];
    }

}