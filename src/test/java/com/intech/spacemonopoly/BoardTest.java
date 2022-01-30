package com.intech.spacemonopoly;

import com.intech.spacemonopoly.model.Board;
import com.intech.spacemonopoly.model.tiles.DepartureTile;
import com.intech.spacemonopoly.model.tiles.Tile;
import com.intech.spacemonopoly.model.tiles.TilePosition;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BoardTest {
    @Test
    public void addTileTest() {
        //Arrange
        Tile tile = new DepartureTile(0,0);
        ///Act
        Board board = new Board();
        board.addTile(15,tile);
        //Assert
        assertNotNull(board.getTile(15));
    }

    @Test
    public void testTilePosition() {
        Set<Integer> test = new HashSet<>();
        for (TilePosition value : TilePosition.values()) {
            test.add(value.getPosition());
        }

        assertEquals(test.size(), TilePosition.values().length);
    }
}
