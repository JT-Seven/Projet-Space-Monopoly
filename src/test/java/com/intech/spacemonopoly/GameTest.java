package com.intech.spacemonopoly;

import com.intech.spacemonopoly.model.Color;
import com.intech.spacemonopoly.model.Game;
import com.intech.spacemonopoly.model.Player;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {

    @Test
    public void testNextTurn() {
        Game game = new Game(new Player[]{new Player("Alan", Color.ORANGE), new Player("wqdqw", Color.BLEU)});

        game.nextTurn();

        assertEquals(game.getPlayer(1).getName(), game.getActivePlayer().getName());
    }

    @Test
    public void testThrowDice() {
        Player player = new Player("Alan", Color.ORANGE);
        Game game = new Game(new Player[]{player});

        AbstractMap.SimpleEntry<Integer, Integer> dices = game.throwDice();

        int result = dices.getKey() + dices.getValue();

        assertTrue(result > 1 && result < 13);
    }

    @Test
    public void testBank() {
        Player player = new Player("Alan", Color.ORANGE);
        Game game = new Game(new Player[]{player});

        game.addMoneyPlayerToBank(300);

        assertEquals(game.getBank(), 300);
    }

    @Test
    public void testPlayerBank() {
        Player player = new Player("Alan", Color.ORANGE);
        Game game = new Game(new Player[]{player});

        game.addMoneyPlayerToBank(300);
        game.addMoneyBankToPlayer(150);

        assertEquals(player.getMoney(), 1650);
    }

}
