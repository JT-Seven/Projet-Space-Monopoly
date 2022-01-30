package com.intech.spacemonopoly.model.tiles;

import com.intech.spacemonopoly.model.Color;
import lombok.Getter;

@Getter
public enum TilePosition {

    //CASE DÉPART
    START_TILE(0, new DepartureTile(10, 10)),

    //IMPOTS NON ACHETABLESxf
    FUEL(4, new TaxesTile(10, 6, 200, System.lineSeparator() + "Vous donnez 200$ pour payer l'essence de votre vaisseau.","FUEL")),
    TOLL(38, new TaxesTile(8, 10, 70, System.lineSeparator() + "Vous donnez 70$ pour payez le péage de l'espace.","TOLL")),

    //IMPOTS ACHETABLES
    ELECTRICITY(12, new BuyableTile(8, 0, "Electricity", Color.TRANSPARENT , 150, 0, 0, 0, 0, 0,null)),
    WATER(28, new BuyableTile(0, 8, "Water",Color.TRANSPARENT, 150, 0, 0, 0, 0, 0,null)),

    //CASE PARKING GRATUIT
    FREE_PARKING(20, new FreeParkingTile(0, 0)),

    //CASE PRISON
    prison(30, new JailTile(0, 10)),

    //CASE VISITE PRISON
    visite(10, new JailVisiteTile(10, 0)),

    //FAMILLE MARS
    MARS(9, new BuyableTile(10, 1, "Mars", Color.CYAN, 120, 50, 50, 8, 110, 600,  null)),
    PHOBOS(8, new BuyableTile(10, 2, "Phobos", Color.CYAN, 100, 50, 50, 6, 100, 550, null)),
    DEIMOS(6, new BuyableTile(10, 4, "Deimos", Color.CYAN, 100, 50, 50, 6, 100, 550, null)),

    //FAMILLE TERRE
    TERRE(39, new BuyableTile(9, 10, "Terre", Color.BLEU, 400, 200, 200, 50, 425, 2000, null)),
    LUNE(37, new BuyableTile(7, 10, "Lune", Color.BLEU, 350,  200, 200, 35, 325, 1500, null)),

    //FAMILLE JUPITER
    JUPITER(14, new BuyableTile(6, 0, "Jupiter", Color.FUSHIA, 160, 100, 100, 12, 175, 900, null)),
    CALLISTO(13, new BuyableTile(7, 0, "Callisto", Color.FUSHIA, 140, 100, 100, 10, 150, 750, null)),
    EUROPE(11, new BuyableTile(9, 0, "Europe", Color.FUSHIA, 140, 100, 100, 10, 150, 750,null)),

    //FAMILLE SATURNE
    SATURNE(16, new BuyableTile(4, 0, "Saturne", Color.JAUNE, 180, 100, 100, 14, 185, 950,null)),
    PAN(18, new BuyableTile(2, 0, "Pan", Color.JAUNE, 180, 100, 100, 14, 185, 950,null)),
    TITAN(19, new BuyableTile(1, 0, "Titan", Color.JAUNE, 200, 100, 100, 16, 200, 1000,null)),

    //FAMILLE URANUS
    URANUS(24, new BuyableTile(0, 4, "Uranus", Color.ROUGE, 240, 150, 150, 20, 230, 1100,null)),
    TITANIA(21, new BuyableTile(0, 1, "Titania", Color.ROUGE, 220, 150, 150, 18, 215, 1050,null)),
    OBERON(23, new BuyableTile(0, 3, "Oberon", Color.ROUGE, 220, 150, 150, 18, 215, 1050,null)),

    //FAMILLE NEPTUNE
    NEPTUNE(29, new BuyableTile(0, 9, "Neptune", Color.ORANGE, 280, 150, 150, 24, 255, 1200,null)),
    TRITON(26, new BuyableTile(0, 6, "Triton", Color.ORANGE, 260, 150, 150, 22, 240, 1150,null)),
    GALATEE(27, new BuyableTile(0, 7, "Galatee", Color.ORANGE, 260, 150, 150, 22, 240, 1150,null)),

    //FAMILLE PLUTON
    PLUTON(34, new BuyableTile(4, 10, "Pluton", Color.VERT, 320, 200, 200, 28 , 400, 1400,null )),
    CHARON(32, new BuyableTile(2, 10, "Charon", Color.VERT, 300, 200, 200, 26 , 315, 1275,null)),
    NIX(31, new BuyableTile(1, 10, "Nix", Color.VERT, 300, 200, 200, 26, 315, 1275,null)),

    //FAMILLE SANS SATELITTE
    VENUS(1, new BuyableTile(10, 9, "Venus", Color.MARRON,60, 50, 50, 2, 40, 250,null)),
    MERCURE(3, new BuyableTile(10, 7, "Mercure", Color.MARRON, 70, 50, 50, 4 , 80, 450,null)),

    //FUSÉE
    ARIANE(5, new BuyableTile(10, 5, "Ariane", Color.TRANSPARENT, 200, 0, 0, 25, 0, 0,null)),
    VEGA(15, new BuyableTile(5, 0, "Vega", Color.TRANSPARENT, 200, 0, 0, 25, 0, 0,null)),
    EPSILON(25, new BuyableTile(0, 5, "Epsilon", Color.TRANSPARENT, 200, 0, 0, 25, 0, 0,null)),
    KOUROU(35, new BuyableTile(5, 10, "Kourou", Color.TRANSPARENT, 200, 0, 0, 25,  0, 0,null)),

    //CARTE CHANCE
    CHANCE7(7, new ChanceCardTile(10, 3)),
    CHANCE22(22, new ChanceCardTile(0, 2)),
    CHANCE36(36, new ChanceCardTile(6, 10)),

    //CARTE CAISSE COFFRE DE COMMUNAUTE
    CAISSECOFFRE2(2, new CommunityChestTile(10, 8)),
    CAISSECOFFRE17(17, new CommunityChestTile(3, 0)),
    CAISSECOFFRE33(33, new CommunityChestTile(3, 10));

    private final int position;
    private final Tile tile;

    TilePosition(int position, Tile tile) {
        this.position = position;
        this.tile = tile;
    }
}
