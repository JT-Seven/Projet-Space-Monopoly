package com.intech.spacemonopoly.controller.animationmanagement.animations;

import animatefx.animation.*;
import com.intech.spacemonopoly.controller.BoardController;
import com.intech.spacemonopoly.controller.animationmanagement.Animation;
import com.intech.spacemonopoly.controller.animationmanagement.AnimationManager;
import com.intech.spacemonopoly.model.Player;
import com.intech.spacemonopoly.model.tiles.BuyableTile;
import com.intech.spacemonopoly.model.tiles.Tile;
import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.Objects;

public class BuyableTileAnimation extends Animation {

    public BuyableTileAnimation(AnimationManager animationManager) {
        super(animationManager);
    }

    @Override
    public void runAnimation(BoardController boardController, Player activePlayer, Tile playerTile) {
        BuyableTile buyableTile = (BuyableTile) playerTile;
        if (buyableTile.getLandlord() == null) {
            boardController.getPaneBuyableTile().setVisible(true);
            boardController.getPaneBuyableTileAnim().setVisible(true);
            boardController.getPaneBackgroundForAllAnimationBuyable().setVisible(true);
            boardController.getNameBuyableTile().setText(buyableTile.getName());
            boardController.getValueRentTilePrice().setText(buyableTile.getRentPrice() + "$");
            boardController.getLabelUneBase().setText(buyableTile.getRentPriceHouse() + "$");
            boardController.getLabelDeuxBase().setText(buyableTile.getRentPriceHouse() * 2 + "$");
            boardController.getLabelTroisBase().setText(buyableTile.getRentPriceHouse() * 3 + "$");
            boardController.getLabelQuatreBase().setText(buyableTile.getRentPriceHouse() * 4 + "$");
            boardController.getLabelUneStation().setText(buyableTile.getRentPriceHotel() + "$");
            boardController.getLabelTilePrice().setText(buyableTile.getTilePrice() + "$");
            boardController.getLabelTilePriceBigSize().setText(buyableTile.getTilePrice() + "$");
            boardController.getLabelHousePrice().setText(buyableTile.getHousePrice() + "$");
            boardController.getLabelHotelPrice().setText(buyableTile.getHotelPrice() + "$");
            boardController.getLabelNamePlayerBuyableTile().setText(boardController.getGame().getActivePlayer().getName());
            boardController.getBgFamilyColor().setStyle("-fx-background-color: " + BoardController.toRGBCode(buyableTile.getColor().getJavaFxColor()) + ";" + " -fx-background-radius: 8;");
            boardController.getBgFamilyColorPrice().setStyle("-fx-stroke: " + BoardController.toRGBCode(buyableTile.getColor().getJavaFxColor()) + ";");
            boardController.getPaneNamePlayerBuyableTile().setStyle("-fx-background-color : " + BoardController.toRGBCode(boardController.getGame().getActivePlayer().getColor().getJavaFxColor()) + ";");
            if (boardController.getGame().getActivePlayer().getPosition() == 12) {
                animationPaneParticularityArrive(boardController);
                boardController.getImgParticularity().setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("pictures/imagesBoard/tileElectricity_.png"))));
            } else if (boardController.getGame().getActivePlayer().getPosition() == 28) {
                animationPaneParticularityArrive(boardController);
                boardController.getImgParticularity().setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("pictures/imagesBoard/tileWater.png"))));
            }
            boardController.getPaneNamePlayerBuyableTile().setVisible(true);
            boardController.getPaneBuyableTile().setTranslateY(-30);
            BounceIn popUp = new BounceIn(boardController.getPaneBuyableTile());
            popUp.play();
            popUp.setOnFinished(event -> {
                ScaleTransition scaleT = new ScaleTransition(Duration.seconds(0.1));
                scaleT.setNode(boardController.getPaneBuyableTile());
                scaleT.setFromX(1);
                scaleT.setFromY(1);
                scaleT.setToX(1.15);
                scaleT.setToY(1.15);
                scaleT.setInterpolator(Interpolator.EASE_IN);
                scaleT.play();
            });
            SlideInLeft slideLeft = new SlideInLeft(boardController.getPaneBackgroundForAllAnimationBuyable());
            slideLeft.play();
            slideLeft.setOnFinished(event -> {
                super.playNextAnimationInQueue(boardController, activePlayer, playerTile);
            });
        } else if (buyableTile.getLandlord() != null && boardController.getGame().getActivePlayer() != buyableTile.getLandlord()) {
            boardController.getDescriptionAlerte().setText("Vous êtes tombés sur une case achetée. Vous payez " + buyableTile.getRentPrice() + "$\nau joueur  ' " + buyableTile.getLandlord().getName() + " '.");
            boardController.getButtonSubController().allButtonDisable();
            boardController.getCircleBtnThrowDice().setVisible(false);
            boardController.getBtnNextTurn().setVisible(false);
            boardController.getImageViewNextTurn().setVisible(false);
            boardController.getImageViewValidated().setVisible(true);
            boardController.getPaneAlerteInfo().setVisible(true);

            RubberBand rubberBand = new RubberBand(boardController.getPaneAlerteInfo());
            rubberBand.play();
            rubberBand.setOnFinished(event -> {
                boardController.getPaneAnimationNamePlayerAlertInfo().setVisible(true);
                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1));
                fadeTransition.setNode(boardController.getBoxDescriptionAlertInfo());
                fadeTransition.setFromValue(0);
                fadeTransition.setToValue(10);

                TranslateTransition bracketOne = new TranslateTransition(Duration.seconds(1));
                bracketOne.setToX(-295);
                bracketOne.setNode(boardController.getBracketAlertInfo1());
                TranslateTransition bracketTwo = new TranslateTransition(Duration.seconds(1));
                bracketTwo.setToX(255);
                bracketTwo.setNode(boardController.getBracketAlertInfo2());

                ScaleTransition scaleT = new ScaleTransition(Duration.seconds(1));
                scaleT.setNode(boardController.getScalePaneAnimationCardsAlertInfo());
                scaleT.setFromX(1);
                scaleT.setToX(12);

                ParallelTransition pt2 = new ParallelTransition(bracketOne, bracketTwo, scaleT, fadeTransition);
                pt2.play();
                pt2.setOnFinished(event2 -> {
                    FadeIn fadeT3 = new FadeIn(boardController.getFadePaneAnimationCardsAlertInfo());
                    fadeT3.setSpeed(2);
                    fadeT3.play();
                    fadeT3.setOnFinished(event3 -> {
                        boardController.getBtnConfirm().setVisible(true);
                        boardController.getAnimationSubController().hoverButtonGame(boardController.getBtnConfirm(), boardController.getCircleBtnConfirm());
                        boardController.getImageViewValidated().setVisible(true);
                        boardController.getButtonSubController().allButtonClickable();
                        playNextAnimationInQueue(boardController, activePlayer, playerTile);
                    });
                });
            });
        } else {
            playNextAnimationInQueue(boardController, activePlayer, playerTile);
        }
    }

    @Override
    public boolean animationCondition(BoardController boardController, Player activePlayer, Tile playerTile) {
        return playerTile instanceof BuyableTile;
    }

    public void animationPaneParticularityArrive(BoardController boardController) {
        boardController.getPaneParticulariity().setVisible(true);
        ZoomInDown zoomInDown = new ZoomInDown(boardController.getPaneParticulariity());
        zoomInDown.play();
        boardController.getClickTurnDescriptionParticularaity().setOnMouseClicked(mouseEvent -> {
            boardController.getClickTurnDescriptionParticularaity().setVisible(false);
            boardController.getClickTurnDescriptionParticularaity2().setVisible(true);
            boardController.getDescriptionParticularity().setText("Si l'on possède les DEUX cartes de Compagnie de Distribution, on multiplies par 10 la somme des deux dés.");
            FadeIn fadeIn = new FadeIn(boardController.getDescriptionParticularity());
            fadeIn.play();
        });
        boardController.getClickTurnDescriptionParticularaity2().setOnMouseClicked(mouseEvent -> {
            boardController.getClickTurnDescriptionParticularaity2().setVisible(false);
            boardController.getClickTurnDescriptionParticularaity().setVisible(true);
            boardController.getDescriptionParticularity().setText("On multiplies par 4 la somme des deux dés, si l'on possède UNE seule carte de Compagnie de Distribution.");
            FadeIn fadeIn = new FadeIn(boardController.getDescriptionParticularity());
            fadeIn.play();
        });
    }
}
