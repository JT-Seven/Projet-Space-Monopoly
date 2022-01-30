package com.intech.spacemonopoly;

import com.intech.spacemonopoly.model.Color;
import com.intech.spacemonopoly.model.Game;
import com.intech.spacemonopoly.model.Player;
import com.intech.spacemonopoly.model.tiles.*;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TileTest {

    @Test
    public void tileActionDepartureTest() {
        //Arrange

        DepartureTile departureTile = new DepartureTile(1, 1);
        Player playerTest = new Player("Gregory",  Color.ORANGE);
        Game game = new Game(new Player[]{playerTest});

        ///Act
        departureTile.tileAction(game);

        //Assert
        assertEquals(1700, game.getPlayer(0).getMoney());
    }
/*

    @Test
    public void tileActionBuyableWithoutLandlordTest() {
        //Arrange
        BuyableTile buyableTile = new BuyableTile(2, 2, "Greg", Color.JAUNE, 250, 50, 50, 100, 0000, 0000,null);
        Game game = this.boardController.getGame();

        ///Act
        buyableTile.tileAction(game);

        //Assert
        assertEquals(1250, game.getPlayer(0).getMoney());
        assertSame(game.getPlayer(0), buyableTile.getLandlord());
    }
*/

    @Test
    public void tileActionBuyableWithLandlordTest() {
        //Arrange
        Player playerTest = new Player("Gregory",  Color.ORANGE);
        Game game = new Game(new Player[]{playerTest});
        BuyableTile buyableTile = new BuyableTile(2, 2, "Greg", Color.JAUNE, 250, 50, 50, 100, 0000, 0000, game.getPlayers()[1]);

        ///Act
        buyableTile.tileAction(game);


        //Assert
        assertEquals(1400, game.getPlayer(0).getMoney());
        assertEquals(1600, game.getPlayers()[1].getMoney());
    }

    @Test
    public void tileActionBuyableWithHouseTest() {
        //Arrange
        Player playerTest = new Player("Gregory",  Color.ORANGE);
        Game game = new Game(new Player[]{playerTest});
        BuyableTile buyableTile = new BuyableTile(2, 2, "Greg", Color.JAUNE, 250, 50, 50, 100, 250, 500, game.getPlayers()[1]);
        buyableTile.setNbHouses(3);

        ///Act
        buyableTile.tileAction(game);

        //Assert
        assertEquals(750, game.getPlayer(0).getMoney());
        assertEquals(2250, game.getPlayers()[1].getMoney());
    }

    @Test
    public void tileActionBuyableWithHotelTest() {
        //Arrange
        Player playerTest = new Player("Gregory",  Color.ORANGE);
        Game game = new Game(new Player[]{playerTest});
        BuyableTile buyableTile = new BuyableTile(2, 2, "Greg", Color.JAUNE, 250, 50, 50, 100, 250, 500, game.getPlayer(1));
        buyableTile.setNbHotels(2);

        ///Act
        buyableTile.tileAction(game);

        //Assert
        assertEquals(500, game.getPlayer(0).getMoney());
        assertEquals(2500, game.getPlayer(1).getMoney());
    }
    @Test
    public void tileActionTaxesOfOneTileTest() {
        //Arrange
        Player playerTest = new Player("Gregory",  Color.ORANGE);
        Game game = new Game(new Player[]{playerTest});
        TaxesTile taxesTile = new TaxesTile(1, 1, 70, "Test de Greg qui enleve 70$","FUEL");

        ///Act
        taxesTile.tileAction(game);

        //Assert
        assertEquals(70, game.getBank());
        assertEquals(1430, game.getPlayer(0).getMoney());
    }

    @Test
    public void tileActionTaxesOfWaterAndElectricityTileTest() {
        //Arrange
        Player playerTest = new Player("Steven",  Color.BLEU);
        Game game = new Game(new Player[]{playerTest});
        BuyableTile buyableTile = new BuyableTile(2, 2, "Electricity", null, 150, 0, 0, 0, 0, 0, game.getPlayer(1));
        game.setLastThrowDices(new AbstractMap.SimpleEntry<Integer, Integer>(6, 6));
//        ///Act
//        buyableTile.landlordPossesion();

        //Assert
//        assertTrue(playerTest.getPosition() == 12 || playerTest.getPosition() == 28 && buyableTile.landlordPossesion(game));
//        assertEquals(120, 12);
    }

    @Test
    public void tileActionFreeParking() {
        //Arrange
        Player playerTest = new Player("Gregory",  Color.ORANGE);
        Game game = new Game(new Player[]{playerTest});
        TaxesTile taxesTile = new TaxesTile(1, 1, 70, "Test de Greg qui enleve 70$","TOLL");
        FreeParkingTile freeParkingTile = new FreeParkingTile(2, 2);

        //Act
        taxesTile.tileAction(game);
        freeParkingTile.tileAction(game);

        //Assert
        assertEquals(0, game.getBank());
        assertEquals(1570, game.getPlayer(1).getMoney());
    }

    @Test
    public void tileActionBuyableElectricity() {
        //Arrange
        Player playerTest = new Player("Gregory",  Color.ORANGE);
        Game game = new Game(new Player[]{playerTest});
        game.getBoard().addTile(12, TilePosition.ELECTRICITY.getTile());
        Tile tile = game.getBoard().getTile(12);
        BuyableTile tileElectricity = (BuyableTile) tile;
        tileElectricity.setLandlord(playerTest);

        ///Act
        game.nextTurn();
        game.getActivePlayer().setPosition((byte) 12);
        game.setLastThrowDices(new AbstractMap.SimpleEntry<>(6, 6));
        tileElectricity.tileAction(game);

        //Assert
        assertEquals(1548, game.getPlayer(0).getMoney());
        assertEquals(1452, game.getPlayer(1).getMoney());
    }

    @Test
    public void tileActionBuyableElectricityAndWater() {
        //Arrange
        Player playerTest = new Player("Gregory",  Color.ORANGE);
        Game game = new Game(new Player[]{playerTest});
        game.getBoard().addTile(12, TilePosition.ELECTRICITY.getTile());
        game.getBoard().addTile(28, TilePosition.WATER.getTile());
        Tile tile12 = game.getBoard().getTile(12);
        Tile tile28 = game.getBoard().getTile(28);
        BuyableTile tileElectricity = (BuyableTile) tile12;
        BuyableTile tileWater = (BuyableTile) tile28;
        tileElectricity.setLandlord(playerTest);
        tileWater.setLandlord(playerTest);
        playerTest.addBoughtTileToPlayer(tileWater);
        playerTest.addBoughtTileToPlayer(tileElectricity);

        ///Act
        game.nextTurn();
        game.getActivePlayer().setPosition((byte) 12);
        game.setLastThrowDices(new AbstractMap.SimpleEntry<>(6, 6));
        tileElectricity.tileAction(game);

        //Assert
        assertEquals(1620, game.getPlayer(0).getMoney());
        assertEquals(1380, game.getPlayer(1).getMoney());
    }

}
