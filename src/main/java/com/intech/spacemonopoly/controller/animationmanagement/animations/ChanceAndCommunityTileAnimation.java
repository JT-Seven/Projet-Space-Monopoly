package com.intech.spacemonopoly.controller.animationmanagement.animations;

import animatefx.animation.BounceInUp;
import animatefx.animation.BounceOutDown;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import com.intech.spacemonopoly.controller.BoardController;
import com.intech.spacemonopoly.controller.animationmanagement.Animation;
import com.intech.spacemonopoly.controller.animationmanagement.AnimationManager;
import com.intech.spacemonopoly.model.Player;
import com.intech.spacemonopoly.model.tiles.ChanceCardTile;
import com.intech.spacemonopoly.model.tiles.CommunityChestTile;
import com.intech.spacemonopoly.model.tiles.Tile;
import com.intech.spacemonopoly.model.tiles.TileType;
import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Objects;

public class ChanceAndCommunityTileAnimation extends Animation {

    public ChanceAndCommunityTileAnimation(AnimationManager animationManager) {
        super(animationManager);
    }

    @Override
    public void runAnimation(BoardController boardController, Player activePlayer, Tile playerTile) {
        boardController.getScalePaneAnimationCards().setStyle("-fx-background-color: " + BoardController.toRGBCode(boardController.getGame().getActivePlayer().getColor().getJavaFxColor())+ ";");
        boardController.getFadePaneAnimationCards().setStyle("-fx-background-color: " + BoardController.toRGBCode(boardController.getGame().getActivePlayer().getColor().getJavaFxColor())+ ";");
        boardController.getLabelNamePlayer().setText(boardController.getGame().getActivePlayer().getName());
        boardController.getTextTypeCard().setText(boardController.getGame().getBoard().getTile(boardController.getGame().getActivePlayer().getPosition()).getTileType().name());
        if(boardController.getGame().getBoard().getTile(boardController.getGame().getActivePlayer().getPosition()).getTileType().name() == "COMMUNITY_CHEST"){
            boardController.getTextTypeCard().setText("CAISSE COMMUNAUTAIRE");
        }

        if(boardController.getGame().getBoard().getTile(boardController.getGame().getActivePlayer().getPosition()).getTileType() == TileType.JAIL){
            if(boardController.getGame().getActivePlayer().getChanceOrCommunityChest() == 0){
                boardController.getTextTypeCard().setText("CAISSE COMMUNAUTAIRE");
            }else{
                boardController.getTextTypeCard().setText("CHANCE");
            }
        }

        if (boardController.getGame().getActivePlayer().getMyChanceCards() != null || boardController.getGame().getActivePlayer().getMyCommunityChest() != null) {
            boardController.getButtonSubController().allButtonDisable();
            switch (String.valueOf(boardController.getGame().getActivePlayer().getMyChanceCards())) {
                case "MOVE_CARD":
                    boardController.getTextNameCard().setText("VOUS REJOUEZ");
                    boardController.getLabelDescriptionCard().setText("Vous menez la conquete !");

                    boardController.getBtnConfirm().setVisible(false);
                    boardController.getImageViewValidated().setVisible(false);
                    boardController.getCircleBtnConfirm().setVisible(false);

                    boardController.getVBoxAddProperty().setVisible(false);

                    boardController.getBtnNextTurn().setVisible(false);
                    boardController.getImageViewNextTurn().setVisible(false);

                    boardController.getBtnThrowDice().setVisible(true);
                    boardController.getImageViewDice().setVisible(true);

                    boardController.getCircleBtnThrowDice().setVisible(false);
                    boardController.getGame().nextTurn();

                    animationPaneCardsChanceAndCommunity(boardController, boardController.getPaneCardImgChance(),"pictures/imagesBoard/cardChance.png");
                    break;
                case "TAKE_MONEY_CARD":
                    boardController.getTextNameCard().setText("VOUS AVEZ PERDU DE L'ARGENT");
                    boardController.getLabelDescriptionCard().setText("Vous vous êtes fais voler votre argent !");

                    boardController.getVBoxAddProperty().setVisible(false);

                    boardController.getBtnNextTurn().setVisible(true);
                    boardController.getBtnNextTurn().setDisable(true);
                    boardController.getImageViewNextTurn().setVisible(true);

                    boardController.getBtnThrowDice().setVisible(false);
                    boardController.getImageViewDice().setVisible(false);

                    animationPaneCardsChanceAndCommunity(boardController, boardController.getPaneCardImgChance(),"pictures/imagesBoard/cardChance.png");

                    break;
                case "JAIL_EXIT_CARD":
                    boardController.getTextNameCard().setText("VOUS ÊTES LIBÉRÉ DE PRISON");
                    boardController.getLabelDescriptionCard().setText("Les gardiens de la galaxie ne vous trouverons pas !");

                    boardController.getVBoxAddProperty().setVisible(false);

                    boardController.getBtnNextTurn().setVisible(true);
                    boardController.getBtnNextTurn().setDisable(true);
                    boardController.getImageViewNextTurn().setVisible(true);

                    boardController.getBtnThrowDice().setVisible(false);
                    boardController.getImageViewDice().setVisible(false);

                    animationPaneCardsChanceAndCommunity(boardController, boardController.getPaneCardImgChance(),"pictures/imagesBoard/cardChance.png");
                    break;
                case "JAIL_ENTRY_CARD":
                    boardController.getTextNameCard().setText("ALLEZ EN PRISON ");
                    boardController.getAnimationSubController().animationCageDown();
                    boardController.getLabelDescriptionCard().setText("Vous vous êtes fais arreter par les gardiens de l'espace !");

                    boardController.getVBoxAddProperty().setVisible(false);

                    boardController.getBtnNextTurn().setVisible(true);
                    boardController.getBtnNextTurn().setDisable(true);
                    boardController.getImageViewNextTurn().setVisible(true);

                    boardController.getBtnThrowDice().setVisible(false);
                    boardController.getImageViewDice().setVisible(false);

                    animationPaneCardsChanceAndCommunity(boardController, boardController.getPaneCardImgChance(),"pictures/imagesBoard/cardChance.png");

                    boardController.getSongSubController().jailSong();
                    break;
            }

            switch (String.valueOf(boardController.getGame().getActivePlayer().getMyCommunityChest())) {
                case "MOVE_CARD":
                    boardController.getTextNameCard().setText("VOUS REJOUEZ");
                    boardController.getLabelDescriptionCard().setText("Vous menez la conquete !");

                    boardController.getBtnConfirm().setVisible(false);
                    boardController.getCircleBtnConfirm().setVisible(false);
                    boardController.getImageViewValidated().setVisible(false);

                    boardController.getBtnNextTurn().setVisible(false);
                    boardController.getImageViewNextTurn().setVisible(false);

                    boardController.getVBoxAddProperty().setVisible(false);
                    boardController.getBtnNextTurn().setVisible(false);
                    boardController.getImageViewNextTurn().setVisible(false);

                    boardController.getBtnThrowDice().setVisible(true);
                    boardController.getImageViewDice().setVisible(true);
                    boardController.getCircleBtnThrowDice().setVisible(false);

                    animationPaneCardsChanceAndCommunity(boardController, boardController.getPaneCardImgChance(),"pictures/imagesBoard/cardCommunautaire.png");

                    boardController.getGame().nextTurn();
                    break;
                case "TAKE_MONEY_CARD":
                    boardController.getTextNameCard().setText("VOUS AVEZ PERDU DE L'ARGENT");
                    boardController.getLabelDescriptionCard().setText("Vous vous êtes fais voler votre argent !");

                    boardController.getVBoxAddProperty().setVisible(false);

                    boardController.getBtnNextTurn().setVisible(true);
                    boardController.getBtnNextTurn().setDisable(true);
                    boardController.getImageViewNextTurn().setVisible(true);

                    boardController.getBtnThrowDice().setVisible(false);
                    boardController.getImageViewDice().setVisible(false);
                    
                    animationPaneCardsChanceAndCommunity(boardController, boardController.getPaneCardImgCommunity(),"pictures/imagesBoard/cardCommunautaire.jpg");
                    break;
                case "JAIL_EXIT_CARD":
                    boardController.getTextNameCard().setText("VOUS ÊTES LIBÉRÉ DE PRISON");
                    boardController.getLabelDescriptionCard().setText("Les gardiens de l'espace ne vous trouverons pas !");

                    boardController.getVBoxAddProperty().setVisible(false);

                    boardController.getBtnNextTurn().setVisible(true);
                    boardController.getBtnNextTurn().setDisable(true);
                    boardController.getImageViewNextTurn().setVisible(true);

                    boardController.getBtnThrowDice().setVisible(false);
                    boardController.getImageViewDice().setVisible(false);
                    
                    animationPaneCardsChanceAndCommunity(boardController,boardController.getPaneCardImgCommunity(),"pictures/imagesBoard/cardCommunautaire.jpg");
                    break;
                case "JAIL_ENTRY_CARD":
                    boardController.getAnimationSubController().animationCageDown();

                    boardController.getTextNameCard().setText("ALLEZ EN PRISON ");
                    boardController.getLabelDescriptionCard().setText("Vous vous êtes fais arreter par les gardiens de l'espace !");

                    boardController.getVBoxAddProperty().setVisible(false);

                    boardController.getBtnNextTurn().setVisible(true);
                    boardController.getBtnNextTurn().setDisable(true);
                    boardController.getImageViewNextTurn().setVisible(true);

                    boardController.getBtnThrowDice().setVisible(false);
                    boardController.getImageViewDice().setVisible(false);
                    
                    animationPaneCardsChanceAndCommunity(boardController, boardController.getPaneCardImgCommunity(),"pictures/imagesBoard/cardCommunautaire.jpg");
                    break;
            }

            boardController.getLogger().info("Carte tiré :" + boardController.getGame().getActivePlayer().getMyCommunityChest());
            boardController.getLogger().info("Carte tiré :" + boardController.getGame().getActivePlayer().getMyChanceCards());
            boardController.getGame().getActivePlayer().setMyChanceCards(null);
        }
    }

    public void animationPaneCardsChanceAndCommunity(BoardController boardController, Pane paneImgChaOrCom, String imgViewCardComOrCha) {
        boardController.getPaneCard().setVisible(true);
        boardController.getPaneCardImgChance().setVisible(true);
        boardController.getImgViewCardComOrChance().setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(String.valueOf(imgViewCardComOrCha)))
        ));

        FadeTransition fadeT = new FadeTransition(Duration.seconds(1));
        fadeT.setNode(boardController.getImgViewCardComOrChance());
        fadeT.setFromValue(0);
        fadeT.setToValue(10);
        fadeT.setInterpolator(Interpolator.EASE_IN);

        TranslateTransition cardPos = new TranslateTransition(Duration.seconds(2));
        cardPos.setNode(paneImgChaOrCom);
        cardPos.setToZ(-1000);
        cardPos.setToY(-50);
        cardPos.setToX(-50);
        ParallelTransition pt = new ParallelTransition(fadeT, cardPos);
        pt.play();
        pt.setOnFinished(actionEvent -> {
            paneImgChaOrCom.setVisible(false);
            FadeTransition fadeT2 = new FadeTransition(Duration.seconds(1));
            fadeT2.setNode(boardController.getImgViewCardComOrChance());
            fadeT2.setFromValue(10);
            fadeT2.setToValue(0);
            fadeT2.setInterpolator(Interpolator.EASE_IN);
            fadeT2.play();
            BounceInUp slideUp = new BounceInUp(boardController.getPaneCard());
            slideUp.play();
            slideUp.setOnFinished(actionEvent1 -> {
                boardController.getPaneAnimationNamePlayer().setVisible(true);

                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1));
                fadeTransition.setNode(boardController.getBoxDescriptionForChaAndCom());
                fadeTransition.setFromValue(0);
                fadeTransition.setToValue(10);

                TranslateTransition bracketOne = new TranslateTransition(Duration.seconds(1));
                bracketOne.setToX(-295);
                bracketOne.setNode(boardController.getBracket1());
                TranslateTransition bracketTwo = new TranslateTransition(Duration.seconds(1));
                bracketTwo.setToX(255);
                bracketTwo.setNode(boardController.getBracket2());

                ScaleTransition scaleT = new ScaleTransition(Duration.seconds(1));
                scaleT.setNode(boardController.getScalePaneAnimationCards());
                scaleT.setFromX(1);
                scaleT.setToX(12);

                ParallelTransition pt2 = new ParallelTransition(bracketOne, bracketTwo, scaleT, fadeTransition);
                pt2.play();
                pt2.setOnFinished(event -> {
                    FadeIn fadeT3 = new FadeIn(boardController.getFadePaneAnimationCards());
                    fadeT3.setSpeed(2);
                    fadeT3.play();
                    fadeT3.setOnFinished(actionEvent2 -> {
                        Timeline timer = new Timeline();
                        KeyFrame kf1 = new KeyFrame(Duration.seconds(2), (KeyValue) null);
                        TranslateTransition cardOut = new TranslateTransition(Duration.seconds(0.1));
                        cardOut.setNode(paneImgChaOrCom);
                        cardOut.setToZ(0);
                        cardOut.setToY(0);
                        cardOut.setToX(0);
                        if(Objects.equals(boardController.getGame().getActivePlayer().getMyChanceCards(), "MOVE_CARD") || Objects.equals(boardController.getGame().getActivePlayer().getMyCommunityChest(), "MOVE_CARD")) {
                            cardOut.play();
                            timer.getKeyFrames().add(kf1);
                            timer.play();
                            timer.setOnFinished(actionEvent3 -> {
                                animationPaneCardChanceAndCommunityExit(boardController);
                            });
//                            boardController.getBtnNextTurn().setVisible(false);
//                            boardController.getImageViewNextTurn().setVisible(false);
//                            boardController.getBtnConfirm().setVisible(false);
//                            boardController.getImageViewValidated().setVisible(false);
                            return;
                        } else {
                            cardOut.play();
                            timer.getKeyFrames().add(kf1);
                            timer.play();
                            timer.setOnFinished(actionEvent3 -> {
                                animationPaneCardChanceAndCommunityExit(boardController);
                            });
                            boardController.getBtnNextTurn().setDisable(false);
//                            boardController.getBtnNextTurn().setVisible(true);
//                            boardController.getImageViewNextTurn().setVisible(true);
                            boardController.getAnimationSubController().hoverButtonGame(boardController.getBtnConfirm(), boardController.getCircleBtnConfirm());
                            return;
                        }
                    });
                });
            });
        });
    }
    
    public void animationPaneCardChanceAndCommunityExit(BoardController boardController) {
        FadeOut fadeT = new FadeOut(boardController.getFadePaneAnimationCards());
        fadeT.setSpeed(2);
        fadeT.play();
        fadeT.setOnFinished(actionEvent1 -> {
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5));
            fadeTransition.setNode(boardController.getBoxDescriptionForChaAndCom());
            fadeTransition.setFromValue(10);
            fadeTransition.setToValue(0);

            TranslateTransition bracketOne = new TranslateTransition(Duration.seconds(0.5));
            bracketOne.setToX(-38);
            bracketOne.setNode(boardController.getBracket1());

            TranslateTransition bracketTwo = new TranslateTransition(Duration.seconds(0.5));
            bracketTwo.setToX(6);
            bracketTwo.setNode(boardController.getBracket2());

            ScaleTransition scaleT = new ScaleTransition(Duration.seconds(0.5));
            scaleT.setNode(boardController.getScalePaneAnimationCards());
            scaleT.setFromX(12);
            scaleT.setToX(0.75);

            ParallelTransition pt = new ParallelTransition(bracketOne, bracketTwo, scaleT, fadeTransition);
            pt.play();
            pt.setOnFinished(actionEvent -> {
                BounceOutDown slideOutDown = new BounceOutDown(boardController.getPaneCard());
                slideOutDown.play();
                slideOutDown.setOnFinished(actionEvent2 -> {
                    super.playNextAnimationInQueue(boardController, boardController.getGame().getActivePlayer(), boardController.getGame().getBoard().getTile(boardController.getGame().getActivePlayer().getPosition()));
                    boardController.getPaneCard().setVisible(false);
                    boardController.getPaneAnimationNamePlayer().setVisible(false);
                });
            });
        });
    }
    
    @Override
    public boolean animationCondition(BoardController boardController, Player activePlayer, Tile playerTile) {
        return playerTile instanceof ChanceCardTile || playerTile instanceof CommunityChestTile;
    }
}
