package com.intech.spacemonopoly;

import com.intech.spacemonopoly.model.Color;
import com.intech.spacemonopoly.model.Game;
import com.intech.spacemonopoly.model.Player;
import com.intech.spacemonopoly.model.tiles.BuyableTile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlayerTest {

    @Test
    public void moveTest() {
        //Arrange
        Player player = new Player("jeremie", Color.BLEU);
        ///Act
        player.move((byte) 12);

        //Assert
        assertEquals(12,player.getPosition());
    }

    @Test
    public void addMoneyTest() {
        //Arrange
        Player player = new Player("jeremie", Color.BLEU);
        ///Act
        player.addMoney(200);

        //Assert
        assertEquals(1700,player.getMoney());
    }

    @Test
    public void addTilePosOnTabTest() {
        //Arrange
        Game game = new Game(new Player[]{new Player("Alan", Color.ORANGE)});
        ///Act
        game.getActivePlayer().setPosition((byte) 1);
        game.getActivePlayer().addBoughtTileToPlayer((BuyableTile) game.getBoard().getTile(game.getActivePlayer().getPosition()));

        //Assert
        assertNotNull(game.getActivePlayer().getBoughtTile());
    }

   /* @Test
    public void incrementCards() {
        Player player = new Player("jeremie", Color.BLEU);
        player.incrementMyCards();

        assertEquals(player.getMyAmountCards(), 1);
    }

    @Test
    public void decrementCards() {
        Player player = new Player("jeremie", Color.BLEU);

        player.incrementMyCards();
        player.incrementMyCards();

        player.decrementMyCards();

        assertEquals(player.getMyAmountCards(), 1);
    }

    */
}



