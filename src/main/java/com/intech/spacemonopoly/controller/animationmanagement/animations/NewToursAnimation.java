package com.intech.spacemonopoly.controller.animationmanagement.animations;

import animatefx.animation.*;
import animatefx.util.ParallelAnimationFX;
import com.intech.spacemonopoly.controller.BoardController;
import com.intech.spacemonopoly.controller.animationmanagement.Animation;
import com.intech.spacemonopoly.controller.animationmanagement.AnimationManager;
import com.intech.spacemonopoly.model.Player;
import com.intech.spacemonopoly.model.tiles.Tile;
import javafx.animation.*;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class NewToursAnimation extends Animation {

    public NewToursAnimation(AnimationManager animationManager) {
        super(animationManager);
    }

    @Override
    public void runAnimation(BoardController boardController, Player activePlayer, Tile playerTile) {
        if (!boardController.getGame().getActivePlayer().isWasJailed()) {
            boardController.getLabelPassageStart().setText("Vous recevez 200$ pour le passage sur Start");
            boardController.getLabelUpdateMoney().setText("+200");
            boardController.getSongSubController().moneySong();
            boardController.getStackPaneUpdateMoney().setVisible(true);
            animationUpdateMoney(boardController, activePlayer, playerTile);
            boardController.getBtnNextTurn().setDisable(true);
        } else {
            this.animationShowAlertInfo(boardController, activePlayer, playerTile);
            boardController.getBtnNextTurn().setVisible(false);
            boardController.getImageViewNextTurn().setVisible(false);
            boardController.getDescriptionAlerte().setText("Le passage sur la case prison empêche la prime de s'appliquer à vous !");
            boardController.getGame().getActivePlayer().setWasJailed(false);
        }
        boardController.getSongSubController().notificationSong();
        boardController.getYes().setDisable(true);
        boardController.getNo().setDisable(true);
        boardController.getYes().setVisible(false);
        boardController.getNo().setVisible(false);
        boardController.getPayFine().setDisable(true);
        boardController.getNoPayFine().setDisable(true);
        boardController.getPayFine().setVisible(false);
        boardController.getNoPayFine().setVisible(false);
    }

    public void animationUpdateMoney(BoardController boardController, Player activePlayer, Tile playerTile) {
        ZoomIn zoomIn = new ZoomIn(boardController.getStackPaneUpdateMoney());
        zoomIn.play();
        zoomIn.setOnFinished(event0 -> {
            RotateTransition rotateTransition = new RotateTransition(Duration.INDEFINITE);
            rotateTransition.setNode(boardController.getImgViewRayonSunAnim());
            rotateTransition.setFromAngle(0);
            rotateTransition.setToAngle(360);
            rotateTransition.setByAngle(360);
            rotateTransition.setCycleCount(javafx.animation.Animation.INDEFINITE);
            rotateTransition.setAxis(Rotate.Y_AXIS);
            rotateTransition.play();

            FadeInDownBig fadeInDownBig = new FadeInDownBig(boardController.getPanePassageTileStart());
            fadeInDownBig.play();
            fadeInDownBig.setOnFinished(actionEvent -> {
                Timeline timer = new Timeline();
                KeyFrame kf1 = new KeyFrame(Duration.seconds(2.5), (KeyValue) null);
                timer.getKeyFrames().add(kf1);
                timer.play();
                timer.setOnFinished(actionEvent1 -> {
                    FadeOutDownBig fadeOutDownBig = new FadeOutDownBig(boardController.getPanePassageTileStart());
                    FadeInDownBig fadeInDownBig2 = new FadeInDownBig(boardController.getLabelUpdateMoney());

                    ParallelAnimationFX ptX = new ParallelAnimationFX(fadeOutDownBig, fadeInDownBig2);
                    ptX.play();
                    Timeline timer2 = new Timeline();
                    KeyFrame kf2 = new KeyFrame(Duration.seconds(2.5), (KeyValue) null);
                    timer2.getKeyFrames().add(kf2);
                    timer2.play();
                    timer2.setOnFinished(actionEvent2 -> {
                        ZoomOut zoomOut = new ZoomOut(boardController.getStackPaneUpdateMoney());
                        zoomOut.play();
                        zoomOut.setOnFinished(actionEvent3 -> {
                            animationUpdateMoneyExit(boardController, activePlayer, playerTile);
                        });
                    });
                });
            });
        });
    }

    public void animationShowAlertInfo(BoardController boardController, Player activePlayer, Tile playerTile) {
        boardController.getButtonSubController().allButtonDisable();
        boardController.getCircleBtnThrowDice().setVisible(false);
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
                boardController.getLogger().info(" Etat joueur actif : " + boardController.getGame().getActivePlayer().isJail());
                fadeT3.setOnFinished(event3 -> {
                    boardController.getBtnConfirm().setVisible(true);
                    boardController.getImageViewValidated().setVisible(true);
                    boardController.getAnimationSubController().hoverButtonGame(boardController.getBtnConfirm(), boardController.getCircleBtnConfirm());
                    boardController.getButtonSubController().allButtonClickable();
                    super.playNextAnimationInQueue(boardController, activePlayer, playerTile);
                });
            });
        });
    }


    @Override
    public boolean animationCondition(BoardController boardController, Player activePlayer, Tile playerTile) {
        return boardController.getGame().getActivePlayer().getNewTours();
    }

    public void animationUpdateMoneyExit(BoardController boardController, Player activePlayer, Tile playerTile) {
        boardController.getStackPaneUpdateMoney().setVisible(false);
        ZoomIn zoomIn = new ZoomIn(boardController.getStackPaneUpdateMoney());
        zoomIn.play();

        FadeOutDownBig fadeOutDownBig = new FadeOutDownBig(boardController.getLabelUpdateMoney());
        FadeInDownBig fadeInDownBig2 = new FadeInDownBig(boardController.getPanePassageTileStart());

        ParallelAnimationFX ptX = new ParallelAnimationFX(fadeOutDownBig, fadeInDownBig2);
        ptX.play();

        FadeOutUpBig fadeOutUpBig = new FadeOutUpBig(boardController.getPanePassageTileStart());
        fadeOutUpBig.play();
        fadeOutUpBig.setOnFinished(event -> {
            super.playNextAnimationInQueue(boardController, activePlayer, playerTile);
        });
    }

}
