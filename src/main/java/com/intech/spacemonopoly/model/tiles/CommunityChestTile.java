package com.intech.spacemonopoly.model.tiles;

import com.intech.spacemonopoly.model.Game;
import com.intech.spacemonopoly.model.cards.CommunityChest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommunityChestTile extends Tile{
    private Logger logger;


    public CommunityChestTile(int x, int y) {
        super(TileType.COMMUNITY_CHEST, x, y);
        this.logger = LoggerFactory.getLogger(CommunityChestTile.class);

    }

    @Override
    public void tileAction(Game game) {
        CommunityChest card = drawLuckCard();
        card.getCardAction().accept(game);
        // game.getActivePlayer().incrementMyCards();
        game.getActivePlayer().setMyCommunityChest(card.name());
        game.getActivePlayer().setChanceOrCommunityChest(0);
        logger.info("Type de carte caisse communautaire  : " + card.name());
    }

    public CommunityChest drawLuckCard () {
        CommunityChest[] tabChanceCard = CommunityChest.values();
        double index = Math.floor(Math.random() * tabChanceCard.length);
        switch (tabChanceCard[(int)index]){
//            case JAIL_ENTRY_CARD :
//                logger.info("Je rentre en prison");
//                break;

            case JAIL_EXIT_CARD :
                logger.info("J'ai une carte sortie de prison");
                break;

//            case TAKE_MONEY_CARD :
//                logger.info("Je perds 20 dollars");
//                break;
//
//            case MOVE_CARD :
//                break;

        }
        return tabChanceCard[(int)index];
    }
}
