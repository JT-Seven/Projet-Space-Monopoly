package com.intech.spacemonopoly.controller.boardsubcontroller;

import animatefx.animation.*;
import animatefx.util.ParallelAnimationFX;
import animatefx.util.SequentialAnimationFX;
import com.intech.spacemonopoly.controller.*;
import com.intech.spacemonopoly.controller.dices.DiceAngle;
import com.intech.spacemonopoly.controller.pawns.Pawn;
import com.intech.spacemonopoly.model.Player;
import com.intech.spacemonopoly.model.tiles.JailTile;
import com.intech.spacemonopoly.model.tiles.Tile;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.AbstractMap;
import java.util.HashMap;

public class AnimationSubController {

    private final BoardController boardController;
    private final HomePageController homePageController;
    private final TutorialController tutorialController;
    private final SelectPlayerAmountController selectPlayerAmountController;
    private final CreatePlayerController createPlayerController;
    private final HashMap<Integer, DiceAngle> diceAngleAnimationLink;

    public AnimationSubController(BoardController boardController, HomePageController homePageController, CreatePlayerController createPlayerController, SelectPlayerAmountController selectPlayerAmountController, TutorialController tutorialController) {
        this.boardController = boardController;
        this.homePageController = homePageController;
        this.createPlayerController = createPlayerController;
        this.selectPlayerAmountController = selectPlayerAmountController;
        this.tutorialController = tutorialController;
        this.diceAngleAnimationLink = new HashMap<>();
        this.diceAngleAnimationLink.put(1, new DiceAngle(90, 270, 1350));
        this.diceAngleAnimationLink.put(2, new DiceAngle(0, 90, 1170));
        this.diceAngleAnimationLink.put(3, new DiceAngle(0, 180, 1260));
        this.diceAngleAnimationLink.put(4, new DiceAngle(0, 0, 1080));
        this.diceAngleAnimationLink.put(5, new DiceAngle(0, 270, 1350));
        this.diceAngleAnimationLink.put(6, new DiceAngle(90, 90, 1170));
    }

    public void hoverButtonGame(MFXButton btn, Circle circle) {
        circle.setVisible(false);
        btn.setOnMouseEntered(event -> {
            circle.setVisible(true);
            ScaleTransition scaleTB = new ScaleTransition(Duration.seconds(1));
            scaleTB.setToX(1.15);
            scaleTB.setToY(1.15);
            scaleTB.setCycleCount(1);
            scaleTB.setNode(btn);
            scaleTB.play();

            FadeTransition fadeTC = new FadeTransition(Duration.seconds(0.5));
            fadeTC.setFromValue(0);
            fadeTC.setToValue(10);
            fadeTC.setCycleCount(1);
            fadeTC.setNode(circle);
            fadeTC.play();
        });

        btn.setOnMouseExited(event -> {
            ScaleTransition scaleTB = new ScaleTransition(Duration.seconds(1));
            scaleTB.setToX(1);
            scaleTB.setToY(1);
            scaleTB.setCycleCount(1);
            scaleTB.setNode(btn);
            scaleTB.play();

            FadeTransition fadeTC = new FadeTransition(Duration.seconds(0.5));
            fadeTC.setFromValue(10);
            fadeTC.setToValue(0);
            fadeTC.setCycleCount(1);
            fadeTC.setNode(circle);
            fadeTC.play();
        });
    }

    public void hoverButtonBuyableTile() {
        boardController.getBuy().setOnMouseEntered(event -> {
            boardController.getBuy().setText(" ");
            boardController.getImgBuyTile().setVisible(true);
            FadeTransition fadeT = new FadeTransition();
            fadeT.setDuration(Duration.seconds(.5));
            fadeT.setNode(boardController.getImgBuyTile());
            fadeT.setFromValue(0);
            fadeT.setToValue(10);
            fadeT.play();
            boardController.getBuy().setStyle("-fx-background-color: transparent");
        });
        boardController.getBuy().setOnMouseExited(event -> {
            boardController.getBuy().setText("Acheter");
            boardController.getImgBuyTile().setVisible(false);
            boardController.getBuy().setStyle("-fx-background-color: #000f1e;");
        });

        boardController.getNoBuy().setOnMouseEntered(event -> {
            boardController.getNoBuy().setText(" ");
            boardController.getImgNoBuyTile().setVisible(true);
            FadeTransition fadeT = new FadeTransition();
            fadeT.setDuration(Duration.seconds(.5));
            fadeT.setNode(boardController.getImgNoBuyTile());
            fadeT.setFromValue(0);
            fadeT.setToValue(10);
            fadeT.play();
            boardController.getNoBuy().setStyle("-fx-background-color: transparent;");
        });
        boardController.getNoBuy().setOnMouseExited(event -> {
            boardController.getNoBuy().setText("Passe");
            boardController.getImgNoBuyTile().setVisible(false);

            boardController.getNoBuy().setStyle("-fx-background-color: #000f1e;");
        });
    }

    public void animationCardBuyableTilePaneArrive() {
        SlideInLeft slideLeft = new SlideInLeft(boardController.getPaneBackgroundForAllAnimationBuyable());
        slideLeft.play();
    }

//    public void animationUpdateMoney() {
//        boardController.getSongSubController().moneySong();
//        boardController.getStackPaneUpdateMoney().setVisible(true);
//        ZoomIn zoomIn = new ZoomIn(boardController.getStackPaneUpdateMoney());
//        zoomIn.play();
//        zoomIn.setOnFinished(event0 -> {
//            RotateTransition rotateTransition = new RotateTransition(Duration.INDEFINITE);
//            rotateTransition.setNode(boardController.getImgViewRayonSunAnim());
//            rotateTransition.setFromAngle(0);
//            rotateTransition.setToAngle(360);
//            rotateTransition.setByAngle(360);
//            rotateTransition.setCycleCount(Animation.INDEFINITE);
//            rotateTransition.setAxis(Rotate.Y_AXIS);
//            rotateTransition.play();
//
//            FadeInDownBig fadeInDownBig = new FadeInDownBig(boardController.getPanePassageTileStart());
//            fadeInDownBig.play();
//            fadeInDownBig.setOnFinished(actionEvent -> {
//                Timeline timer = new Timeline();
//                KeyFrame kf1 = new KeyFrame(Duration.seconds(2.5), (KeyValue) null);
//                timer.getKeyFrames().add(kf1);
//                timer.play();
//                timer.setOnFinished(actionEvent1 -> {
//                    FadeOutDownBig fadeOutDownBig = new FadeOutDownBig(boardController.getPanePassageTileStart());
//                    FadeInDownBig fadeInDownBig2 = new FadeInDownBig(boardController.getLabelUpdateMoney());
//
//                    ParallelAnimationFX ptX = new ParallelAnimationFX(fadeOutDownBig, fadeInDownBig2);
//                    ptX.play();
//                    Timeline timer2 = new Timeline();
//                    KeyFrame kf2 = new KeyFrame(Duration.seconds(2.5), (KeyValue) null);
//                    timer2.getKeyFrames().add(kf2);
//                    timer2.play();
//                    timer2.setOnFinished(actionEvent2 -> {
//                        ZoomOut zoomOut = new ZoomOut(boardController.getStackPaneUpdateMoney());
//                        zoomOut.play();
//                        zoomOut.setOnFinished(actionEvent3 -> {
//                            animationUpdateMoneyExit();
//                        });
//                    });
//                });
//            });
//        });
//    }

    public void animationUpdateMoneyExit() {
        boardController.getStackPaneUpdateMoney().setVisible(false);
        ZoomIn zoomIn = new ZoomIn(boardController.getStackPaneUpdateMoney());
        zoomIn.play();

        FadeOutDownBig fadeOutDownBig = new FadeOutDownBig(boardController.getLabelUpdateMoney());
        FadeInDownBig fadeInDownBig2 = new FadeInDownBig(boardController.getPanePassageTileStart());

        ParallelAnimationFX ptX = new ParallelAnimationFX(fadeOutDownBig, fadeInDownBig2);
        ptX.play();

        FadeOutUpBig fadeOutUpBig = new FadeOutUpBig(boardController.getPanePassageTileStart());
        fadeOutUpBig.play();
    }

    public void animationOnClickYesBuyable(int position, Tile tile) {
        Group sphere3D = new Group();

        if(position < 10) {
            this.boardController.getBorderPanes()[tile.getX()][tile.getY()].setBottom(sphere3D);
            if(position == 5) {
                sphere3D.setTranslateY(-45);
            }
            sphere3D.setTranslateY(-80);
            sphere3D.setTranslateX(20);
        }
        else if(position < 20) {
            this.boardController.getBorderPanes()[tile.getX()][tile.getY()].setLeft(sphere3D);
            if(position == 15) {
                sphere3D.setTranslateX(25);
            }
            sphere3D.setTranslateX(80);
            sphere3D.setTranslateY(15);
        }
        else if(position < 30) {
            this.boardController.getBorderPanes()[tile.getX()][tile.getY()].setTop(sphere3D);
            if(position == 25) {
                sphere3D.setTranslateY(45);
            }
            sphere3D.setTranslateY(80);
            sphere3D.setTranslateX(25);
        }
        else if(position < 40) {
            this.boardController.getBorderPanes()[tile.getX()][tile.getY()].setRight(sphere3D);
            if(position == 15) {
                sphere3D.setTranslateX(-45);
            }
            sphere3D.setTranslateX(-80);
            sphere3D.setTranslateY(25);
        }

        Sphere sphere = new Sphere(10);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.valueOf(boardController.toRGBCode(boardController.getGame().getActivePlayer().getColor().getJavaFxColor())));
        sphere.setMaterial(material);
        sphere3D.getChildren().add(sphere);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1));
        scaleTransition.setNode(sphere);
        scaleTransition.setFromX(0);
        scaleTransition.setFromY(0);
        scaleTransition.setToY(1);
        scaleTransition.setToX(1);

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1));
        translateTransition.setNode(sphere);
        translateTransition.setFromZ(0);
        translateTransition.setToZ(-25);

        ParallelTransition pt = new ParallelTransition(scaleTransition, translateTransition);
        pt.play();
    }

    public void animationCardBuyableExit() {
        boardController.getBtnNextTurn().setDisable(true);
        if (this.boardController.getGame().getActivePlayer().getPosition() == 12) {
            BounceOutUp slideUp = new BounceOutUp(boardController.getPaneBuyableTile());
            slideUp.play();
            slideUp.setOnFinished(actionEvent -> {
                boardController.getPaneParticulariity().setVisible(false);
                ZoomOutUp zoomInDown = new ZoomOutUp(boardController.getPaneParticulariity());
                zoomInDown.play();
            });
        } else if (this.boardController.getGame().getActivePlayer().getPosition() == 28) {
            BounceOutUp slideUp = new BounceOutUp(boardController.getPaneBuyableTile());
            slideUp.play();
            slideUp.setOnFinished(actionEvent -> {
                boardController.getPaneParticulariity().setVisible(false);
                ZoomOutUp zoomInDown = new ZoomOutUp(boardController.getPaneParticulariity());
                zoomInDown.play();
            });
        }

        BounceOutUp slideUp = new BounceOutUp(boardController.getPaneBuyableTile());
        slideUp.play();
        slideUp.setOnFinished(event -> {
            boardController.getPaneParticulariity().setVisible(false);
            this.boardController.getPaneBuyableTile().setVisible(false);
            Timeline timer = new Timeline();
            KeyFrame kf1 = new KeyFrame(Duration.seconds(0.1), (KeyValue) null);
            timer.getKeyFrames().add(kf1);
            timer.play();
            timer.setOnFinished(event1 -> {
                boardController.getBtnNextTurn().setDisable(false);
                BounceInDown slideDown = new BounceInDown(this.boardController.getPaneBuyableTile());
                slideDown.play();
            });
        });
        BounceOutUp slideUp2 = new BounceOutUp(boardController.getPaneBuyableTileAnim());
        slideUp2.play();
        slideUp2.setOnFinished(event -> {
            this.boardController.getPaneBuyableTileAnim().setVisible(false);
            Timeline timer = new Timeline();
            KeyFrame kf1 = new KeyFrame(Duration.seconds(0.1), (KeyValue) null);
            timer.getKeyFrames().add(kf1);
            timer.play();
            timer.setOnFinished(event1 -> {
                boardController.getBtnNextTurn().setDisable(false);
                boardController.getPaneNamePlayerBuyableTile().setVisible(false);
                BounceInDown slideDown = new BounceInDown(this.boardController.getPaneBuyableTileAnim());
                slideDown.play();
            });
        });
    }

    public void animationCardMissMoneyArrive() {
        boardController.getPaneMissingMoney().setVisible(true);
        boardController.getPaneMissingMoney().setStyle("-fx-background-color: " + boardController.toRGBCode(boardController.getGame().getActivePlayer().getColor().getJavaFxColor()) + ";");
        SlideInLeft slideLeft = new SlideInLeft(boardController.getPaneBackgroundForAllAnimation());
        slideLeft.play();
        slideLeft.setOnFinished(event -> {
            Timeline timer = new Timeline();
            KeyFrame kf1 = new KeyFrame(Duration.seconds(3), (KeyValue) null);
            timer.getKeyFrames().add(kf1);
            timer.play();
            timer.setOnFinished(event1 -> {
                SlideOutRight slideOutLeft = new SlideOutRight(boardController.getPaneMissingMoney());
                slideOutLeft.play();
                slideOutLeft.setOnFinished(event2 -> {
                    boardController.getButtonSubController().allButtonClickable();
                    boardController.getPaneMissingMoney().setVisible(false);
                    boardController.getBtnNextTurn().setDisable(false);
                    SlideInRight reverse = new SlideInRight(boardController.getPaneMissingMoney());
                    reverse.play();
                    SlideInRight reverse2 = new SlideInRight(boardController.getPaneBackgroundForAllAnimation());
                    reverse2.play();
                });
            });
        });
    }

    public void animationButtonGame(MFXButton btn, Circle circle) {
        boardController.getButtonSubController().allCircleInvisible();
        circle.setVisible(true);
        ScaleTransition scaleTB = new ScaleTransition(Duration.seconds(1));
        scaleTB.setFromX(1);
        scaleTB.setToX(1.15);
        scaleTB.setFromY(1);
        scaleTB.setToY(1.15);
        scaleTB.setCycleCount(TranslateTransition.INDEFINITE);
        scaleTB.setNode(btn);
        scaleTB.setAutoReverse(true);

        ScaleTransition scaleTEx = new ScaleTransition(Duration.seconds(1));
        scaleTEx.setFromX(1);
        scaleTEx.setToX(1.15);
        scaleTEx.setFromY(1);
        scaleTEx.setToY(1.15);
        scaleTEx.setCycleCount(TranslateTransition.INDEFINITE);
        scaleTEx.setNode(circle);
        scaleTEx.setAutoReverse(true);

        ParallelTransition pt = new ParallelTransition(scaleTB, scaleTEx);
        pt.play();
        pt.setOnFinished(event -> {
            pt.play();
        });
    }

    public void animationShowAlertInfo() {
        boardController.getButtonSubController().allButtonDisable();
        boardController.getCircleBtnThrowDice().setVisible(false);
        boardController.getPaneAlerteInfo().setVisible(true);

        RubberBand rubberBand = new RubberBand(boardController.getPaneAlerteInfo());
        rubberBand.play();
        rubberBand.setOnFinished(event -> {
            this.boardController.getPaneAnimationNamePlayerAlertInfo().setVisible(true);
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
                });
            });
        });
    }

    public void animationCageDown() {
        FadeTransition fadeT = new FadeTransition(Duration.seconds(1), boardController.getCage());
        fadeT.setFromValue(0);
        fadeT.setToValue(10);
        fadeT.setInterpolator(Interpolator.EASE_IN);

        TranslateTransition cageDown = new TranslateTransition(Duration.seconds(1), boardController.getCage());
        cageDown.setFromZ(-50);
        cageDown.setToZ(0);
        cageDown.setInterpolator(Interpolator.EASE_IN);
        ParallelTransition pt = new ParallelTransition(fadeT, cageDown);
        pt.play();
        pt.setOnFinished(actionEvent -> {
            FadeTransition fadeT2 = new FadeTransition(Duration.seconds(1), boardController.getCage());
            fadeT2.setFromValue(10);
            fadeT2.setToValue(0);
            fadeT2.setInterpolator(Interpolator.EASE_IN);

            TranslateTransition cageDown2 = new TranslateTransition(Duration.seconds(1), boardController.getCage());
            cageDown2.setFromZ(0);
            cageDown2.setToZ(-50);
            cageDown2.setInterpolator(Interpolator.EASE_IN);
            ParallelTransition pt2 = new ParallelTransition(fadeT2, cageDown2);
            pt2.setDelay(Duration.seconds(3));
            pt2.play();
        });
    }

    public void animationShowAlertInfoJail() {
        boardController.getButtonSubController().allButtonDisable();
        boardController.getCircleBtnThrowDice().setVisible(false);
        boardController.getPaneAlerteInfo().setVisible(true);
//        boardController.getBtnConfirm().setVisible(false);
//        boardController.getImageViewValidated().setVisible(false);
        RubberBand rubberBand = new RubberBand(boardController.getPaneAlerteInfo());
        rubberBand.play();
        rubberBand.setOnFinished(event -> {
            this.boardController.getPaneAnimationNamePlayerAlertInfo().setVisible(true);
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
                    boardController.getBtnNextTurn().setVisible(false);
                    boardController.getImageViewNextTurn().setVisible(false);
                    boardController.getBtnThrowDice().setVisible(true);
                    boardController.getImageViewDice().setVisible(true);
                    boardController.getBtnThrowDice().setDisable(true);
                    boardController.getAnimationSubController().hoverButtonGame(boardController.getBtnThrowDice(), boardController.getCircleBtnThrowDice());
                    if (boardController.getGame().getActivePlayer().isJail() && boardController.getGame().getActivePlayer().getTurnsInJail() == 0) {
                        this.boardController.getAnimationSubController().animationShowAlertInfoExit();
                    }
                });
            });
        });
    }

    public void animationShowAlertInfoJailExit() {
        FadeOut fadeT = new FadeOut(boardController.getFadePaneAnimationCardsAlertInfo());
        fadeT.setSpeed(2);
        fadeT.play();
        fadeT.setOnFinished(actionEvent1 -> {
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5));
            fadeTransition.setNode(boardController.getBoxDescriptionAlertInfo());
            fadeTransition.setFromValue(10);
            fadeTransition.setToValue(0);

            TranslateTransition bracketOne = new TranslateTransition(Duration.seconds(0.5));
            bracketOne.setToX(-38);
            bracketOne.setNode(boardController.getBracketAlertInfo1());

            TranslateTransition bracketTwo = new TranslateTransition(Duration.seconds(0.5));
            bracketTwo.setToX(6);
            bracketTwo.setNode(boardController.getBracketAlertInfo2());

            ScaleTransition scaleT = new ScaleTransition(Duration.seconds(0.5));
            scaleT.setNode(boardController.getScalePaneAnimationCardsAlertInfo());
            scaleT.setFromX(12);
            scaleT.setToX(0.75);

            ParallelTransition pt = new ParallelTransition(bracketOne, bracketTwo, scaleT, fadeTransition);
            pt.play();
            pt.setOnFinished(actionEvent -> {
                BounceOutRight slideOutRight = new BounceOutRight(boardController.getPaneAlerteInfo());
                slideOutRight.play();
                slideOutRight.setOnFinished(actionEvent2 -> {
                    boardController.getPaneAlerteInfo().setVisible(false);
                    BounceInRight slideInRight = new BounceInRight(boardController.getPaneAlerteInfo());
                    slideInRight.play();
                });
            });
        });
    }

    public void animationShowAlertInfoExit() {
        boardController.getButtonSubController().allButtonDisable();
        FadeOut fadeT = new FadeOut(boardController.getFadePaneAnimationCardsAlertInfo());
        fadeT.setSpeed(2);
        fadeT.play();
        fadeT.setOnFinished(actionEvent1 -> {
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5));
            fadeTransition.setNode(boardController.getBoxDescriptionAlertInfo());
            fadeTransition.setFromValue(10);
            fadeTransition.setToValue(0);

            TranslateTransition bracketOne = new TranslateTransition(Duration.seconds(0.5));
            bracketOne.setToX(-38);
            bracketOne.setNode(boardController.getBracketAlertInfo1());

            TranslateTransition bracketTwo = new TranslateTransition(Duration.seconds(0.5));
            bracketTwo.setToX(6);
            bracketTwo.setNode(boardController.getBracketAlertInfo2());

            ScaleTransition scaleT = new ScaleTransition(Duration.seconds(0.5));
            scaleT.setNode(boardController.getScalePaneAnimationCardsAlertInfo());
            scaleT.setFromX(12);
            scaleT.setToX(0.75);

            ParallelTransition pt = new ParallelTransition(bracketOne, bracketTwo, scaleT, fadeTransition);
            pt.play();
            pt.setOnFinished(actionEvent -> {
                BounceOutRight slideOutRight = new BounceOutRight(boardController.getPaneAlerteInfo());
                slideOutRight.play();
                slideOutRight.setOnFinished(actionEvent2 -> {
                    boardController.getPaneAlerteInfo().setVisible(false);
                    boardController.getButtonSubController().allButtonClickable();
                    this.boardController.getPaneAnimationNamePlayer().setVisible(false);
                    BounceInRight slideInRight = new BounceInRight(boardController.getPaneAlerteInfo());
                    slideInRight.play();
                });
            });
        });
    }

    public void animationLayoutPlayer() {
        if (boardController.getGame().getActivePlayer() == boardController.getGame().getPlayer(0)) {
            Pulse pulse = new Pulse(boardController.getLayoutPlayer1());
            pulse.play();
            animationPawnsNextTurn(boardController.getGame().getPlayer(0));
        } else if (boardController.getGame().getActivePlayer() == boardController.getGame().getPlayer(1)) {
            Pulse pulse = new Pulse(boardController.getLayoutPlayer2());
            pulse.play();
            animationPawnsNextTurn(boardController.getGame().getPlayer(1));
        } else if (boardController.getGame().getActivePlayer() == boardController.getGame().getPlayer(2)) {
            Pulse pulse = new Pulse(boardController.getLayoutPlayer3());
            pulse.play();
            animationPawnsNextTurn(boardController.getGame().getPlayer(2));
        } else if (boardController.getGame().getActivePlayer() == boardController.getGame().getPlayer(3)) {
            Pulse pulse = new Pulse(boardController.getLayoutPlayer4());
            pulse.play();
            animationPawnsNextTurn(boardController.getGame().getPlayer(3));
        }
    }

    public void animationPawnsNextTurn(Player player) {

    }

    public void animationVisiblePaneBegin(Pane pane) {
        boardController.getButtonSubController().allButtonDisable();
        if (boardController.getGame().getActivePlayer().isFaillite()) {
            boardController.getGame().nextTurn();
            boardController.getButtonSubController().allButtonClickable();
            boardController.getTextActivePlayer().setText(String.valueOf(boardController.getGame().getActivePlayer().getName()));
            animationVisiblePaneBegin(boardController.getPaneBeginTurn());
            boardController.updateTile();
        } else {
            boardController.getPaneBeginTurn().setVisible(true);
            boardController.getPaneBeginTurn().setScaleX(0);
            boardController.getPaneBeginTurn().setScaleY(0);
            boardController.getPaneBeginTurn().setTranslateY(-50);
            boardController.getPaneBeginTurn().setStyle("-fx-background-color: " + BoardController.toRGBCode(boardController.getGame().getActivePlayer().getColor().getJavaFxColor()) + ";" + "-fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: white;");

            ScaleTransition scaleT = new ScaleTransition(Duration.seconds(0.5));
            scaleT.setFromX(0);
            scaleT.setToX(1);
            scaleT.setFromY(0);
            scaleT.setToY(1);
            scaleT.setNode(pane);
            scaleT.setCycleCount(1);
            scaleT.play();

            scaleT.setOnFinished(event -> {
                if (boardController.getGame().getActivePlayer().getOffer() != 0) {
                    boardController.getNotifImage().setVisible(true);
                    boardController.getSongSubController().notificationSong();

                } else {
                    boardController.getNotifImage().setVisible(false);
                }

                animationLayoutPlayer();
                Timeline timer = new Timeline();
                KeyValue start = new KeyValue(boardController.getCircleAnimationQuickly().radiusProperty(), 0, Interpolator.EASE_IN);
                KeyFrame end = new KeyFrame(Duration.seconds(2.2), start);
                timer.getKeyFrames().add(end);
                timer.setOnFinished(t -> {
                    ScaleTransition scaleT2 = new ScaleTransition(Duration.seconds(0.5));
                    scaleT2.setFromX(1);
                    scaleT2.setToX(0);
                    scaleT2.setFromY(1);
                    scaleT2.setToY(0);
                    scaleT2.setNode(pane);
                    scaleT2.setCycleCount(1);
                    scaleT2.play();
                    scaleT2.setOnFinished(event1 -> {
                        boardController.getButtonSubController().allButtonClickable();
                        boardController.getBtnNextTurn().setVisible(false);
                        boardController.getBtnThrowDice().setVisible(true);
                        boardController.getBtnThrowDice().setDisable(false);
                        boardController.getCircleBtnThrowDice().setVisible(true);

                        if (boardController.getGame().getActivePlayer().getExitJailCard() == 0 && boardController.getGame().getActivePlayer().isJail() && boardController.getBtnThrowDice().isVisible() && boardController.getGame().getActivePlayer().getTurnsInJail() != 0) {
                            boardController.getButtonSubController().allButtonDisable();
                            boardController.getSongSubController().notificationSong();
                            boardController.getDescriptionAlerte().setText("Vous etes en prison ! Pour sortir faites un double ou payer une amende de 100 $ !\nTour restant : " + boardController.getGame().getActivePlayer().getTurnsInJail());
                            this.boardController.getAnimationSubController().animationShowAlertInfoJail();
                            boardController.getPayFine().setVisible(true);
                            boardController.getNoPayFine().setVisible(true);
                            boardController.getNoPayFine().setDisable(false);
                            boardController.getPayFine().setDisable(false);
                            boardController.showInfoAlert();
                        } else if (boardController.getGame().getActivePlayer().getExitJailCard() == 0 && boardController.getGame().getActivePlayer().isJail() && boardController.getBtnThrowDice().isVisible() && boardController.getGame().getActivePlayer().getTurnsInJail() == 0) {
                            boardController.getButtonSubController().allButtonDisable();
                            boardController.getSongSubController().notificationSong();
                            boardController.getDescriptionAlerte().setText("Félicitation !! \n Vous êtes liberés de prison ");
                            this.boardController.getAnimationSubController().animationShowAlertInfoJail();
                            boardController.getPayFine().setVisible(false);
                            boardController.getNoPayFine().setVisible(false);
                            boardController.showInfoAlert();
                        }

                        if (boardController.getGame().getActivePlayer().getExitJailCard() > 0 && boardController.getGame().getActivePlayer().isJail()) {
                            boardController.getButtonSubController().allButtonDisable();
                            boardController.getSongSubController().notificationSong();
                            boardController.getDescriptionAlerte().setText("Vous possedez une carte 'Sortez de prison'. Voulez vous l'utilisez afin de sortir \nde prison sans frais ?");
                            boardController.getYes().setVisible(true);
                            boardController.getNo().setVisible(true);
                            boardController.getYes().setDisable(false);
                            boardController.getNo().setDisable(false);
                            this.boardController.getAnimationSubController().animationShowAlertInfoJail();
                            boardController.showInfoAlert();
                        }
                        boardController.getButtonSubController().allCircleInvisible();
                        hoverButtonGame(boardController.getBtnThrowDice(), boardController.getCircleBtnThrowDice());
                    });
                });
                timer.play();
            });
        }
    }

    public void boardMoveRotation() {
        int position = boardController.getGame().getActivePlayer().getPosition();
        RotateTransition rt = new RotateTransition(Duration.seconds(2), boardController.getGroupBoard3D());
        rt.setCycleCount(1);
        TranslateTransition tt = new TranslateTransition(Duration.seconds(3), boardController.getCamera());
        tt.setCycleCount(1);
        ParallelTransition pt = new ParallelTransition(rt, tt);

        boardController.getGroupBoard3D().setRotate(boardController.getGroupBoard3D().getRotate());
        int passage = 0;
        AbstractMap.SimpleEntry<Integer, Integer> dice = boardController.getGame().throwDice();
        int lastThrowDice = dice.getKey() + dice.getValue();

        for (int i = 0; i <= 4; i++) {
            passage++;
            if (passage <= 1) {
                if (position >= 0 && position <= 9) {
                    rt.setFromAngle(boardController.getGroupBoard3D().getRotate());
                    rt.setToAngle(360);
                    rt.setAxis(Rotate.Z_AXIS);
                    tt.setToZ(-1820);
                    tt.setToY(-130);
                    tt.setToX(385);

                } else if (position >= 10 && position <= 19) {
                    rt.setFromAngle(boardController.getGroupBoard3D().getRotate());
                    rt.setToAngle(270);
                    rt.setAxis(Rotate.Z_AXIS);
                    tt.setToZ(-1830);
                    tt.setToY(-120);
                    tt.setToX(400);

                } else if (position >= 20 && position <= 29) {
                    rt.setFromAngle(boardController.getGroupBoard3D().getRotate());
                    rt.setToAngle(180);
                    rt.setAxis(Rotate.Z_AXIS);
                    tt.setToZ(-1820);
                    tt.setToY(-130);
                    tt.setToX(410);

                } else if (position >= 30 && position <= 39) {
                    rt.setFromAngle(boardController.getGroupBoard3D().getRotate());
                    rt.setToAngle(90);
                    rt.setAxis(Rotate.Z_AXIS);
                    tt.setToZ(-1820);
                    tt.setToY(-140);
                    tt.setToX(400);
                }
                pt.play();

            } else {
                return;
            }
        }
    }

    public void animationThrowDiceResult() {
        boardController.getButtonSubController().allButtonDisable();
        boardController.getStackPaneThrowDice().setVisible(true);
        BounceInDown bounceInDown = new BounceInDown(boardController.getStackPaneThrowDice());
        bounceInDown.play();
        bounceInDown.setOnFinished(actionEvent -> {
            boardController.getImgRayonSunThrowDice().setVisible(true);
            boardController.getLabelThrowDiceResult().setVisible(true);
            ZoomInDown zoomInDown = new ZoomInDown(boardController.getImgRayonSunThrowDice());
            ZoomInDown zoomInDown2 = new ZoomInDown(boardController.getLabelThrowDiceResult());

            ParallelAnimationFX pt = new ParallelAnimationFX(zoomInDown, zoomInDown2);
            pt.play();
            Timeline timer = new Timeline();
            KeyFrame kf1 = new KeyFrame(Duration.seconds(1.75), (KeyValue) null);
            timer.getKeyFrames().add(kf1);
            timer.play();
            timer.setOnFinished(actionEvent1 -> {
                BounceOutUp bounceOutUp = new BounceOutUp(boardController.getStackPaneThrowDice());
                bounceOutUp.play();
                bounceOutUp.setOnFinished(actionEvent2 -> {
                    boardController.getStackPaneThrowDice().setVisible(false);
                    boardController.getImgRayonSunThrowDice().setVisible(false);
                    boardController.getLabelThrowDiceResult().setVisible(false);
                    boardController.getStackPaneThrowDice().setTranslateY(-800);
                    boardController.getImgRayonSunThrowDice().setTranslateY(-300);
                    boardController.getLabelThrowDiceResult().setTranslateY(-300);
                    boardController.getCircleBtnNextTurn().setVisible(true);
                    boardController.getAnimationSubController().hoverButtonGame(boardController.getBtnNextTurn(), boardController.getCircleBtnNextTurn());
                    if (boardController.getGame().getBoard().getTile(boardController.getGame().getActivePlayer().getPosition()) instanceof JailTile) {
                        boardController.getButtonSubController().allButtonClickable();
                    }
                });
            });
        });
    }

    public void animationBeginTurnBoard() {

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2));
        translateTransition.setNode(boardController.getGroupBoard3D());
        translateTransition.setFromZ(boardController.getGroupBoard3D().getTranslateZ());
        translateTransition.setToZ(-360);

        TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(3));
        translateTransition2.setNode(boardController.getCamera());
        translateTransition2.setToY(-100);
        translateTransition2.setToX(500);
        translateTransition2.setFromZ(-35000);
        translateTransition2.setToZ(-1700);

        RotateTransition rt = new RotateTransition(Duration.seconds(2));
        rt.setNode(boardController.getGroupBoard3D());
        rt.setAxis(Rotate.Z_AXIS);
        rt.setFromAngle(0);
        rt.setToAngle(360);
        rt.setCycleCount(1);

        ParallelTransition pt = new ParallelTransition(translateTransition, translateTransition2, rt);
        pt.play();
    }

    public void animationCity(Group city) {
        TranslateTransition tt = new TranslateTransition(Duration.seconds(5));
        tt.setNode(city);
        tt.setFromZ(0);
        tt.setToZ(-10);
        tt.play();
        tt.setOnFinished(actionEvent -> {
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(5));
            tt2.setNode(city);
            tt2.setFromZ(-10);
            tt2.setToZ(0);
            tt2.play();
            tt2.setOnFinished(actionEvent1 -> {
                tt.play();
            });
        });
    }

    public void firstAnimationCamera(Camera camera) {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                camera.setRotationAxis(Rotate.Y_AXIS);
                camera.rotateProperty().set(camera.getRotate() + 0.05);
            }
        };
        timer.start();
    }

    public void animationPlanet(Sphere sphere) {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                Rotate rotate = new Rotate();
                rotate.setAxis(Rotate.X_AXIS);
                rotate.setPivotX(90);

                rotate.setAngle(90);
                sphere.getTransforms().clear();
                sphere.getTransforms().add(rotate);
                sphere.setRotationAxis(Rotate.Z_AXIS);
                sphere.rotateProperty().set(sphere.getRotate() - 0.15);
            }
        };
        timer.start();
    }

    public void singleDiceAnimation(Group dice, DiceAngle diceAngle, RotateTransition rotateT) {
        Rotate rotateDice2 = new Rotate();
        rotateDice2.setAxis(Rotate.Z_AXIS);
        rotateDice2.setAngle(diceAngle.getInitialAngle());
        dice.getTransforms().add(rotateDice2);
        rotateT.setAxis(Rotate.X_AXIS);
        rotateT.setFromAngle(diceAngle.getFromAngle());
        rotateT.setToAngle(diceAngle.getToAngle());
    }

    public void animationDices(Group[] dices) {
        boardController.onClickThrowDice(new ActionEvent());

        RotateTransition rotateT0 = new RotateTransition(Duration.seconds(3));
        rotateT0.setCycleCount(1);
        rotateT0.setNode(dices[0]);

        RotateTransition rotateT1 = new RotateTransition(Duration.seconds(3));
        rotateT1.setCycleCount(1);
        rotateT1.setNode(dices[1]);

        Path pathDice0 = new Path();
        pathDice0.getElements().add(new MoveTo(150, 70));
        pathDice0.getElements().add(new LineTo(190, 199));
        pathDice0.setLayoutX(50);
        pathDice0.setLayoutY(100);

        Path pathDice1 = new Path();
        pathDice1.getElements().add(new MoveTo(150, 70));
        pathDice1.getElements().add(new LineTo(225, 260));
        pathDice1.setLayoutX(70);
        pathDice1.setLayoutY(120);

        AbstractMap.SimpleEntry<Integer, Integer> dice = boardController.getGame().getLastThrowDices();

        singleDiceAnimation(dices[0], diceAngleAnimationLink.get(dice.getKey()), rotateT0);
        singleDiceAnimation(dices[1], diceAngleAnimationLink.get(dice.getValue()), rotateT1);

        PathTransition pathTransition0 = new PathTransition();
        pathTransition0.setDuration(Duration.seconds(3));
        pathTransition0.setInterpolator(Interpolator.EASE_IN);
        pathTransition0.setPath(pathDice0);
        pathTransition0.setNode(dices[0]);
        pathTransition0.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition0.setOnFinished(actionEvent -> {
            Timeline timer = new Timeline();
            KeyFrame kf1 = new KeyFrame(Duration.seconds(5), (KeyValue) null);
            timer.getKeyFrames().add(kf1);
            timer.play();
            timer.setOnFinished(actionEvent1 -> {
                FadeOut fadeT = new FadeOut(dices[0]);
                fadeT.play();
            });

        });

        PathTransition pathTransition1 = new PathTransition();
        pathTransition1.setDuration(Duration.seconds(3));
        pathTransition1.setInterpolator(Interpolator.EASE_IN);
        pathTransition1.setPath(pathDice1);
        pathTransition1.setNode(dices[1]);
        pathTransition1.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition1.setOnFinished(actionEvent -> {
            Timeline timer = new Timeline();
            KeyFrame kf1 = new KeyFrame(Duration.seconds(5), (KeyValue) null);
            timer.getKeyFrames().add(kf1);
            timer.play();
            timer.setOnFinished(actionEvent1 -> {
                FadeOut fadeT = new FadeOut(dices[1]);
                fadeT.play();
            });
        });

        boardController.getBtnThrowDice().setVisible(false);
        boardController.getImageViewDice().setVisible(false);
        boardController.getBtnNextTurn().setVisible(true);
        boardController.getBtnNextTurn().setDisable(true);
        boardController.getImageViewNextTurn().setVisible(true);
        boardController.getSongSubController().throwDiceSong();
        boardController.getButtonSubController().allButtonDisable();

        ParallelTransition parallelTransition = new ParallelTransition(pathTransition0, pathTransition1, rotateT0, rotateT1);
        parallelTransition.play();
        parallelTransition.setOnFinished(actionEvent -> {
            this.boardController.getSongSubController().throwDiceSong();
            this.boardController.getSongSubController().stopEffect();
            this.animationThrowDiceResult();
        });
    }

    //Switch Scene

    public void switchSceneToCreatePlayer() {
        selectPlayerAmountController.getCreatePlayerController().getBackSelectPlayer().setDisable(false);
        FadeOutLeftBig fadeOutRightBig1 = new FadeOutLeftBig(selectPlayerAmountController.getSelect2PlayersButton());
        fadeOutRightBig1.setSpeed(1.5);
        fadeOutRightBig1.play();
        FadeOutLeftBig fadeOutRightBig2 = new FadeOutLeftBig(selectPlayerAmountController.getSelect3PlayersButton());
        fadeOutRightBig2.setSpeed(1);
        fadeOutRightBig2.play();
        FadeOutLeftBig fadeOutRightBig3 = new FadeOutLeftBig(selectPlayerAmountController.getSelect4PlayersButton());
        fadeOutRightBig3.setSpeed(0.5);
        fadeOutRightBig3.play();
        FadeOut fadeOut = new FadeOut(selectPlayerAmountController.getPaneSelectedPlayers());
        fadeOut.play();
        fadeOut.setOnFinished(actionEvent -> {
            FadeInRightBig fadeInRightBig = new FadeInRightBig(selectPlayerAmountController.getCreatePlayerController().getBorderPaneCreate());
            fadeInRightBig.setSpeed(1);
            fadeInRightBig.play();
        });
    }

    public void switchSceneCreatePlayerToSelect() {
        createPlayerController.getBackSelectPlayer().setDisable(true);
        FadeOutRightBig fadeOutRightBig = new FadeOutRightBig(createPlayerController.getBorderPaneCreate());
        fadeOutRightBig.setSpeed(1);
        fadeOutRightBig.play();
        fadeOutRightBig.setOnFinished(actionEvent -> {
            FadeInLeftBig fadeOutRightBig1 = new FadeInLeftBig(createPlayerController.getHomePageController().getSelectPlayerAmountController().getSelect2PlayersButton());
            fadeOutRightBig1.setSpeed(0.8);
            fadeOutRightBig1.play();
            FadeInLeftBig fadeOutRightBig2 = new FadeInLeftBig(createPlayerController.getHomePageController().getSelectPlayerAmountController().getSelect3PlayersButton());
            fadeOutRightBig2.setSpeed(1);
            fadeOutRightBig2.play();
            FadeInLeftBig fadeOutRightBig3 = new FadeInLeftBig(createPlayerController.getHomePageController().getSelectPlayerAmountController().getSelect4PlayersButton());
            fadeOutRightBig3.setSpeed(1.5);
            fadeOutRightBig3.play();
            FadeIn fadeOut = new FadeIn(createPlayerController.getHomePageController().getSelectPlayerAmountController().getPaneSelectedPlayers());
            fadeOut.play();
        });
    }

    public void switchSceneToSelectPlayer() {
        homePageController.getSelectPlayerAmountController().getBackHome().setDisable(false);
        homePageController.getCreatePersona().setDisable(true);
        homePageController.getBtnTutorials().setDisable(true);
        homePageController.getBtnSettings().setDisable(true);
        FadeOut fadeOut = new FadeOut(homePageController.getLayoutTitleAndButton());
        fadeOut.play();
        FadeOut fadeOut2 = new FadeOut(homePageController.getRocketAnimate());
        fadeOut2.play();
        TranslateTransition tt = new TranslateTransition(Duration.seconds(1.2), homePageController.getCircleAnim());
        tt.setFromX(0);
        tt.setToX(-1250);
        tt.setInterpolator(Interpolator.EASE_IN);

        RotateTransition rt = new RotateTransition(Duration.seconds(1.2), homePageController.getCircleAnim());
        rt.setByAngle(360);
        rt.setCycleCount(1);

        SequentialTransition st = new SequentialTransition(tt, rt);
        st.play();
        st.setOnFinished(actionEvent1 -> {
            homePageController.getCreatePersona().setDisable(false);
            homePageController.getBtnTutorials().setDisable(false);
            homePageController.getBtnSettings().setDisable(false);
        });
        FadeIn fadeIn = new FadeIn(homePageController.getSelectPlayerAmountController().getPaneSelectedPlayers());
        fadeIn.setDelay(Duration.seconds(1));
        fadeIn.play();

        FadeInRightBig fadeInRightBig1 = new FadeInRightBig(homePageController.getSelectPlayerAmountController().getSelect2PlayersButton());
        fadeInRightBig1.setDelay(Duration.seconds(1.2));
        fadeInRightBig1.setSpeed(2);
        fadeInRightBig1.play();
        FadeInRightBig fadeInRightBig2 = new FadeInRightBig(homePageController.getSelectPlayerAmountController().getSelect3PlayersButton());
        fadeInRightBig2.setDelay(Duration.seconds(1.2));
        fadeInRightBig2.setSpeed(1.5);
        fadeInRightBig2.play();
        FadeInRightBig fadeInRightBig3 = new FadeInRightBig(homePageController.getSelectPlayerAmountController().getSelect4PlayersButton());
        fadeInRightBig3.setDelay(Duration.seconds(1.2));
        fadeInRightBig3.setSpeed(1);
        fadeInRightBig3.play();
    }

    public void switchSceneSelectPlayerToHomePage() {
        selectPlayerAmountController.getBackHome().setDisable(true);
        FadeOutRightBig fadeOutRightBig1 = new FadeOutRightBig(selectPlayerAmountController.getSelect2PlayersButton());
        fadeOutRightBig1.setSpeed(0.5);
        fadeOutRightBig1.play();
        FadeOutRightBig fadeOutRightBig2 = new FadeOutRightBig(selectPlayerAmountController.getSelect3PlayersButton());
        fadeOutRightBig2.setSpeed(1);
        fadeOutRightBig2.play();
        FadeOutRightBig fadeOutRightBig3 = new FadeOutRightBig(selectPlayerAmountController.getSelect4PlayersButton());
        fadeOutRightBig3.setSpeed(1.5);
        fadeOutRightBig3.play();
        FadeOut fadeOut = new FadeOut(selectPlayerAmountController.getPaneSelectedPlayers());
        fadeOut.play();
        fadeOut.setOnFinished(actionEvent -> {
            FadeIn fadeIn = new FadeIn(selectPlayerAmountController.getHomePageController().getLayoutTitleAndButton());
            fadeIn.play();
            FadeIn fadeIn2 = new FadeIn(selectPlayerAmountController.getHomePageController().getRocketAnimate());
            fadeIn2.play();
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), selectPlayerAmountController.getHomePageController().getCircleAnim());
            tt.setFromX(-1250);
            tt.setToX(0);
            tt.setInterpolator(Interpolator.EASE_IN);

            RotateTransition rt = new RotateTransition(Duration.seconds(0.5), selectPlayerAmountController.getHomePageController().getCircleAnim());
            rt.setByAngle(360);
            rt.setCycleCount(1);

            ParallelTransition pt = new ParallelTransition(tt, rt);
            pt.play();
        });
    }

    public void switchSceneToTutorial() {
        homePageController.getTutorialController().getBackHome().setDisable(false);
        homePageController.getCreatePersona().setDisable(true);
        homePageController.getBtnTutorials().setDisable(true);
        homePageController.getBtnSettings().setDisable(true);
        FadeOut fadeOut = new FadeOut(homePageController.getLayoutTitleAndButton());
        fadeOut.play();
        FadeOut fadeOut2 = new FadeOut(homePageController.getRocketAnimate());
        fadeOut2.play();
        TranslateTransition tt = new TranslateTransition(Duration.seconds(1.2), homePageController.getCircleAnim());
        tt.setFromX(0);
        tt.setToX(-1250);
        tt.setInterpolator(Interpolator.EASE_IN);

        RotateTransition rt = new RotateTransition(Duration.seconds(1.2), homePageController.getCircleAnim());
        rt.setByAngle(360);
        rt.setCycleCount(1);

        SequentialTransition st = new SequentialTransition(tt, rt);
        st.play();
        st.setOnFinished(actionEvent1 -> {
            homePageController.getCreatePersona().setDisable(false);
            homePageController.getBtnTutorials().setDisable(false);
            homePageController.getBtnSettings().setDisable(false);
        });
        FadeIn fadeIn = new FadeIn(homePageController.getTutorialController().getContents_tutorial());
        fadeIn.setDelay(Duration.seconds(1));
        fadeIn.play();
    }

    public void switchSceneTutorialToHomePage() {
        tutorialController.getBackHome().setDisable(true);
        FadeOut fadeOut = new FadeOut(tutorialController.getContents_tutorial());
        fadeOut.play();
        fadeOut.setOnFinished(actionEvent -> {
            FadeIn fadeIn = new FadeIn(tutorialController.getHomePageController().getLayoutTitleAndButton());
            fadeIn.play();
            FadeIn fadeIn2 = new FadeIn(tutorialController.getHomePageController().getRocketAnimate());
            fadeIn2.play();
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), tutorialController.getHomePageController().getCircleAnim());
            tt.setFromX(-1250);
            tt.setToX(0);
            tt.setInterpolator(Interpolator.EASE_IN);

            RotateTransition rt = new RotateTransition(Duration.seconds(0.5), tutorialController.getHomePageController().getCircleAnim());
            rt.setByAngle(360);
            rt.setCycleCount(1);

            ParallelTransition pt = new ParallelTransition(tt, rt);
            pt.play();
        });
    }

    public void animationWinner() {
        updateFieldAndAddPawnsWinner();
        FadeIn fadeIn = new FadeIn(boardController.getParentWinner());
        fadeIn.play();
        fadeIn.setOnFinished(actionEvent0 -> {
            boardController.getConfetieWinner().setOpacity(1);
            boardController.getLogoWinner().setOpacity(1);
            BounceIn bounceIn = new BounceIn(boardController.getConfetieWinner());
            RubberBand rubberBand = new RubberBand(boardController.getLogoWinner());
            rubberBand.setOnFinished(actionEvent1 -> {
                Pulse pulse = new Pulse(boardController.getLogoWinner());
                pulse.setSpeed(0.5);
                pulse.setCycleCount(AnimationFX.INDEFINITE);
                pulse.play();
            });

            ParallelAnimationFX ptX = new ParallelAnimationFX(bounceIn, rubberBand);
            ptX.play();

            FadeInUpBig fadeInUpBig = new FadeInUpBig(boardController.getPaneWinnerSlideUpOne());
            fadeInUpBig.setDelay(Duration.seconds(1.5));
            fadeInUpBig.setSpeed(1);
            fadeInUpBig.play();

            FadeInUpBig fadeInUpBig2 = new FadeInUpBig(boardController.getPaneWinnerSlideUpTwo());
            fadeInUpBig2.setDelay(Duration.seconds(1.5));
            fadeInUpBig2.setSpeed(0.5);
            fadeInUpBig2.play();

        });
    }

    public void updateFieldAndAddPawnsWinner() {
        Rotate rxBox = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
        Rotate ryBox = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
        Rotate rzBox = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS);
        rxBox.setAngle(-60);
        ryBox.setAngle(0);
        rzBox.setAngle(0);

        PerspectiveCamera camera = new PerspectiveCamera(true);
        boardController.getSubSceneWinner().setCamera(camera);
        camera.setNearClip(0.01);
        camera.setTranslateZ(-500);
        camera.setFarClip(1000000000);

        Group groupContentsWinner3D = new Group();
        groupContentsWinner3D.getChildren().addAll(boardController.getPawnSubController().getPlayerPawn(boardController.getGame().getActivePlayer()));
        boardController.getContentsWinner3D().getTransforms().addAll(rxBox, ryBox, rzBox);
        boardController.getContentsWinner3D().getChildren().add(groupContentsWinner3D);

        boardController.getPaneWinnerSlideUpOne().setStyle("-fx-background-color: " + BoardController.toRGBCode(boardController.getGame().getActivePlayer().getColor().getJavaFxColor()) + ";" + "-fx-background-radius: 8;");
        boardController.getPaneWinnerSlideUpTwo().setStyle("-fx-background-color: " + BoardController.toRGBCode(boardController.getGame().getActivePlayer().getColor().getJavaFxColor()) + ";" + "-fx-background-radius: 8;");
        boardController.getWinnerName().setText(boardController.getGame().getActivePlayer().getName());
        boardController.getWinnerMoney().setText(boardController.getGame().getActivePlayer().getMoney() + "$");
        boardController.getBorderPaneWinner().setVisible(true);
    }
}
