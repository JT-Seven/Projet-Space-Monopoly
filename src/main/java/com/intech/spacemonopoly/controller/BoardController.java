package com.intech.spacemonopoly.controller;

import animatefx.animation.FadeInUpBig;
import animatefx.animation.FadeOutDown;
import com.intech.spacemonopoly.controller.animationmanagement.AnimationManager;
import com.intech.spacemonopoly.controller.boardsubcontroller.*;
import com.intech.spacemonopoly.controller.musicsubcontroller.SongSubController;
import com.intech.spacemonopoly.model.Game;
import com.intech.spacemonopoly.model.Player;
import com.intech.spacemonopoly.model.tiles.BuyableTile;
import com.intech.spacemonopoly.model.tiles.Tile;
import com.intech.spacemonopoly.model.tiles.TilePosition;
import com.intech.spacemonopoly.model.tiles.TileType;
import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXProgressBar;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.*;

@Getter
@Setter
public class BoardController implements Initializable {

    private Game game;
    private BorderPane[][] borderPanes;
    private Logger logger;
    public Box boxBoar3D = new Box();
    public Group[] cardsComAndChance = new Group[2];
    public Sphere allStars = new Sphere();
    PhongMaterial colorForSphereBuyable = new PhongMaterial();
    private final Group[] dices = new Group[2];
    private final Group vaisseauMove = new Group();
    public Sphere[] sphere3D = new Sphere[2];
    private Group groupPlanetSolarSystem = new Group();
    private Group groupFinalBoard3D = new Group();
    private Group groupBoardAndElement = new Group();
    private Group groupAnimationPLanet = new Group();
    private Group cage = new Group();
    private PerspectiveCamera camera = new PerspectiveCamera(true);
    private Translate pivot = new Translate();
    private Rotate camera_Rotation = new Rotate(0, Rotate.Y_AXIS);
    private Box pivotRotationPlanet = new Box(100, 100, 100);

    @FXML
    private Group groupDice, groupBoard3D, groupCardsCommunity, groupChanceCards, groupFinal3D;
    @FXML
    private SubScene subScene, subSceneWinner;
    @FXML
    private Pane board3D, theFinalBackground3D, theFinalPositionCage;
    @FXML
    private StackPane Ap, theFinalCardChaAndCom, stackPaneUpdateMoney, stackPaneThrowDice;
    @FXML
    private Line bgFamilyColorPrice;
    @FXML
    private Circle circleAnimationQuickly;
    @FXML
    private BorderPane layoutPlayer1, borderPaneWinner,  layoutPlayer2, layoutPlayer3, layoutPlayer4, responsiveBoard3D, borderPaneCardCommunity, borderPaneCardChance;
    @FXML
    private GridPane boardGrid;
    @FXML
    private Pane paneBuyableTile, theFinalBoard3D, paneBackgroundForAllAnimation, contentsWinner3D, paneBackgroundForAllAnimationBuyable, PaneNamePlayerBuyableTile, boxDescriptionAlertInfo, boxDescriptionForChaAndCom, theStars;
    @FXML
    private AnchorPane responsiveBackground, parentWinner;
    @FXML
    private MFXButton buy, noBuy;
    @FXML
    public VBox vBoxAddProperty;
    @FXML
    private MFXButton btnThrowDice, clickTurnDescriptionParticularaity, clickTurnDescriptionParticularaity2;
    @FXML
    private MFXButton btnNextTurn;
    @FXML
    private MFXButton btnSellCard;
    @FXML
    private MFXButton btnENDGAME;
    @FXML
    private MFXButton btnProperty;
    @FXML
    private MFXButton btnSettings;
    @FXML
    private MFXButton exitAskBuyableTile;
    @FXML
    private MFXButton closeSpaceSellCard;
    @FXML
    private MFXButton btnPlayer1, btnPlayer2, btnPlayer3;
    @FXML
    private Circle circleBtnProperty, circleBtnThrowDice, circleBtnExchange, circleBtnSettings, circleBtnNextTurn, circleBtnConfirm;
    @FXML
    private MFXScrollPane scrollBar;
    @FXML
    private VBox contentsBox;
    @FXML
    private HBox bodyBoard3D;
    @FXML
    private Text nameBuyableTile, valueRentTilePrice;
    @FXML
    private Pane imgBuyTile, paneWinnerSlideUpOne, paneWinnerSlideUpTwo, imgNoBuyTile, bgFamilyColor, panePassageTileStart;
    @FXML
    private Label labelUneBase, bracket2, bracket1, bracketAlertInfo1, bracketAlertInfo2, labelNamePlayerAlertInfo,
            labelTroisBase, labelQuatreBase,
            labelUneStation, labelTilePrice, labelHousePrice, labelHotelPrice, labelDeuxBase, labelTilePriceBigSize, labelNamePlayerMissMoney, labelNamePlayerBuyableTile, labelNamePlayer;
    @FXML
    private MFXProgressBar ProgressPlayer1, ProgressPlayer2, ProgressPlayer3, ProgressPlayer4;
    @FXML
    private Label textNamePlayer1, winnerName, winnerMoney, textNamePlayer2, textNamePlayer3, textNamePlayer4, labelUpdateMoney, labelThrowDiceResult;
    @FXML
    private Pane paneColorPlayer1, paneColorPlayer2, paneColorPlayer3, paneColorPlayer4, boxDescriptionForPassageStart, imgViewRayonSunAnim;
    @FXML
    private ImageView imagePawnPlayer1, imagePawnPlayer2, imagePawnPlayer3, imagePawnPlayer4, imageViewValidated, imgViewCardComOrChance, imgAlertInfo, imgAction_ChaAndCom,
            imgViewMrMonopolyMoney, imgRayonSunThrowDice;
    @FXML
    private Label labelMoneyPlayer1, labelMoneyPlayer2, labelMoneyPlayer3, labelMoneyPlayer4, labelPassageStart, descriptionParticularity;
    @FXML
    private Pane paneMissingMoney, paneBeginTurn, paneBuyableTileAnim, fadePaneAnimationCards, scalePaneAnimationCards, paneAnimationNamePlayer, paneCardImgChance, paneCardImgCommunity, paneAnimationNamePlayerAlertInfo, scalePaneAnimationCardsAlertInfo, fadePaneAnimationCardsAlertInfo, paneParticulariity;
    @FXML
    private ImageView imageViewNextTurn, logoWinner, confetieWinner, imageViewDice, imagePawnPlayerBeginTurn, imgParticularity;
    @FXML
    private Text textActivePlayer;
    @FXML
    private Pane paneCard;
    @FXML
    private MFXButton btnConfirm;
    @FXML
    private Text textNameCard;
    @FXML
    private Label labelDescriptionCard;
    @FXML
    private Text textTypeCard;
    @FXML
    private Pane paneAlerteInfo;
    @FXML
    private Label DescriptionAlerte;
    @FXML
    private MFXButton yes;
    @FXML
    private MFXButton no;
    @FXML
    private MFXButton payFine;
    @FXML
    private MFXButton noPayFine;
    @FXML
    private Pane paneCardSellingSpace;
    @FXML
    private Text descriptionOffer;
    @FXML
    private Label textOffer;
    @FXML
    private MFXButton buyExitCard;
    @FXML
    private MFXButton noBuyExitCard;
    @FXML
    private ImageView notifImage;
    @FXML
    private Pane paneSetting;


    // Sub controller
    private ButtonSubController buttonSubController;
    private AnimationSubController animationSubController;
    private Initialise3DElementsSubController initialise3DElementsSubController;
    private PawnSubController pawnSubController;
    private BuyableSubController buyableSubController;
    private AnimationManager animationManager;

    @Setter
    private SongSubController songSubController;
    private Player[] tablePlayers;
    int nbClickOnSetting = 0;
    int nbClickOnSellCard = 0;
    int nbClickOnProperty = 0;

    //---------------------------------INITIALIZE-----------------------------------------------------------------------

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // initialise sub controllers
        this.pawnSubController = new PawnSubController(this);
        this.initialise3DElementsSubController = new Initialise3DElementsSubController(this);
        this.buttonSubController = new ButtonSubController(this);
        this.animationSubController = new AnimationSubController(this, null, null, null, null);
        this.buyableSubController = new BuyableSubController(this);
        this.animationManager = new AnimationManager();

        groupBoard3D.getChildren().addAll(initialise3DElementsSubController.createBoard3D(), bodyBoard3D);
        initialise3DElementsSubController.createCardsChanceAndCommunity();
        initialise3DElementsSubController.createCageForJailTile();
        circleBtnThrowDice.setVisible(false);
        functionOfAllButtonClickableForTheGame();

        animationSubController.hoverButtonGame(btnSellCard, circleBtnExchange);
        animationSubController.hoverButtonGame(btnProperty, circleBtnProperty);
        animationSubController.hoverButtonGame(btnSettings, circleBtnSettings);
        animationSubController.hoverButtonGame(btnNextTurn, circleBtnNextTurn);

        this.logger = LoggerFactory.getLogger(BoardController.class);
        this.borderPanes = new BorderPane[11][11];
        this.animationSubController.hoverButtonBuyableTile();
        ProgressPlayer1.setStyle("-fx-accent: red;");
        /*
        this.isOpenSetting = false;
        this.isOpenSellCard = false;
         */
    }

    private void functionOfAllButtonClickableForTheGame() {
        btnThrowDice.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.SECONDARY)) {
                if (event.getClickCount() == 1) {
                    return;
                }
            }
            if (getGame().getActivePlayer().isJail()) {
                getAnimationSubController().animationShowAlertInfoExit();
            }
            Group[] dices = initialise3DElementsSubController.createDices();
            circleBtnThrowDice.setVisible(false);
            btnNextTurn.setVisible(true);
            btnThrowDice.setVisible(false);
            animationSubController.animationDices(dices);
        });
        btnNextTurn.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.SECONDARY)) {
                if (event.getClickCount() == 1) {
                    return;
                }
            }
            btnThrowDice.setVisible(true);
            btnThrowDice.setDisable(true);
            animationSubController.animationVisiblePaneBegin(paneBeginTurn);
        });
        btnConfirm.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                if (mouseEvent.getClickCount() == 1) {
                    return;
                }
            }
            btnConfirm.setVisible(false);
            circleBtnConfirm.setVisible(false);
            imageViewValidated.setVisible(false);
            getAnimationSubController().hoverButtonGame(getBtnNextTurn(), getCircleBtnNextTurn());
//            if (boardController.getGame().getActivePlayer().getMyChanceCards() != null || boardController.getGame().getActivePlayer().getMyCommunityChest() != null) {
//                animationPaneCardChanceAndCommunityExit();
//            }

            if (paneAlerteInfo.isVisible() && getGame().getActivePlayer().isWasJailed()) {
                animationSubController.animationShowAlertInfoJailExit();
//                getBtnThrowDice().setVisible(true);
//                getImageViewDice().setVisible(true);
            } else if (paneAlerteInfo.isVisible()) {
                animationSubController.animationShowAlertInfoExit();
            }
            btnNextTurn.setVisible(true);
            imageViewNextTurn.setVisible(true);
        });
    }

    private void initialiseBoard() {
        for (TilePosition tilePosition : TilePosition.values()) {
            Tile tile = tilePosition.getTile();
            this.game.getBoard().addTile(tilePosition.getPosition(), tile);
            if (this.borderPanes == null) {
                continue;
            }

            BorderPane node = borderPanes[tile.getX()][tile.getY()];

            if (node == null) continue;
            for (Node child : node.getChildren()) {
                if (child == null) continue;
                if (child instanceof Text) {
                    //((Text)child).setText(tilePosition.name().toLowerCase(Locale.ROOT));
                    break;
                }
            }
        }
    }

    private void initialiseGridPaneArray(GridPane boardGrid) {
        for (Node node : boardGrid.getChildren()) {
            if (!(node instanceof BorderPane)) continue;
            int row = 0;
            int column = 0;
            if (GridPane.getRowIndex(node) != null) row = GridPane.getRowIndex(node);
            if (GridPane.getColumnIndex(node) != null) column = GridPane.getColumnIndex(node);
            this.borderPanes[row][column] = (BorderPane) node;
        }
    }

    private void initialisePawns(Group[] pawns) {
        for (int i = 0; i < pawns.length; i++) {
            Player player = this.game.getPlayer(i);
            if (player == null) continue;
            this.pawnSubController.setPlayerPawn(player, pawns[i]);
            this.pawnSubController.getPlayerPawn(player).setScaleX(4);
            this.pawnSubController.getPlayerPawn(player).setScaleY(4);
            this.pawnSubController.getPlayerPawn(player).setScaleZ(4);
        }

        for (Player onePlayer : this.game.getPlayers()) {
            if (onePlayer == null) continue;
            this.pawnSubController.getPlayerPawn(onePlayer).setId(onePlayer.getPawnID());
        }

        int i = 0;
        for (Player player : this.game.getPlayers()) {
            if (player == null) continue;
            this.pawnSubController.setPawnPosition(player, 0);
            this.pawnSubController.setCorrectPawnPosition(this.pawnSubController.getPlayerPawn(player), i);
            i++;
        }
    }

    public void startGame(Player[] players, Group[] pawns) {
        this.game = new Game(players);
        this.initialiseGridPaneArray(boardGrid);
        this.initialiseBoard();
        this.initialisePawns(pawns);
        informationOfPlayer();
        textActivePlayer.setText(game.getActivePlayer().getName());
        Tile tile = game.getBoard().getTile(39);
        BuyableTile terre = (BuyableTile) tile;

        Tile tile2 = game.getBoard().getTile(37);
        BuyableTile lune = (BuyableTile) tile2;
        terre.setLandlord(players[2]);
        lune.setLandlord(players[2]);
        players[2].addBoughtTileToPlayer(terre);
        players[2].addBoughtTileToPlayer(lune);
    }

    //__________________BUTTON ACTION___________________________________________________________________________________

    public void closePaneBegin() {
        paneBeginTurn.setStyle("-fx-background-color: " + toRGBCode(getGame().getActivePlayer().getColor().getJavaFxColor()) + ";" + "-fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: white;");
        FadeInUpBig fadeT1 = new FadeInUpBig(paneBeginTurn);
        fadeT1.play();
        fadeT1.setOnFinished(event -> {
            getAnimationSubController().animationLayoutPlayer();
            Timeline timer = new Timeline();
            KeyFrame kf1 = new KeyFrame(Duration.seconds(1.5), (KeyValue) null);
            timer.getKeyFrames().add(kf1);
            timer.play();
            timer.setOnFinished(event1 -> {
                FadeOutDown fadeT = new FadeOutDown(paneBeginTurn);
                fadeT.play();
                fadeT.setOnFinished(event2 -> {
                    FadeTransition fadeT2 = new FadeTransition(Duration.seconds(1));
                    fadeT2.setNode(paneBeginTurn);
                    fadeT2.setFromValue(0);
                    fadeT2.setToValue(10);
                    fadeT2.setCycleCount(1);
                    fadeT2.play();
                    paneBeginTurn.setVisible(false);
                    buttonSubController.allButtonClickable();
                });
            });
        });
    }

    public void onClickYes(ActionEvent event) {
        songSubController.moneySong();
        buyableSubController.onClickBuyBuyableTile();
    }

    public void onClickNo(ActionEvent event) {
        buyableSubController.onClickDontBuyBuyableTile();
    }

    public void onClickENDGAME(ActionEvent event) {
        game.getPlayer(1).setFaillite(true);
        game.getPlayer(2).setFaillite(true);
        updateMoney();
        updateTile();
        updatePlayerAndPawn();
    }
    public void onClickNextTurn(ActionEvent event) {
        if (vBoxAddProperty.isVisible()) {
            nbClickOnProperty++;
        }
        if (paneSetting.isVisible()) {
            nbClickOnSetting++;
        }
        if (paneCardSellingSpace.isVisible()) {
            nbClickOnSellCard++;
        }

        checkEndGame();
        if(checkEndGame())
            animationSubController.animationWinner();

        game.nextTurn();
        vBoxAddProperty.setVisible(false);
        btnNextTurn.setVisible(false);
        imageViewNextTurn.setVisible(false);
        btnThrowDice.setVisible(true);
        imageViewDice.setVisible(true);
        buttonSubController.allButtonDisable();
        textActivePlayer.setText(game.getActivePlayer().getName());
        paneBeginTurn.setVisible(true);
        paneAlerteInfo.setVisible(false);
        payFine.setVisible(false);
        noPayFine.setVisible(false);
        payFine.setDisable(true);
        noPayFine.setDisable(true);
        paneCardSellingSpace.setVisible(false);
        buyExitCard.setVisible(false);
        noBuyExitCard.setVisible(false);
        paneSetting.setVisible(false);
        getAnimationSubController().animationCardBuyableExit();
        updateMoney();
        updateTile();
        updatePlayerAndPawn();
    }

    public boolean checkEndGame() {
        int countPlayerOnFaillite = 0;
        for (Player player : getGame().getPlayers()) {
            if(player.isFaillite())
                countPlayerOnFaillite++;
        }
        if(countPlayerOnFaillite == getGame().getMaxPlayers() - 1)
            return true;
        return false;
    }

    //-------------------------------------Carte exit et JailCard--------------------------------------

    public void onClickUseExitCard() {
        game.getActivePlayer().decrementExitJailCard();
        game.getActivePlayer().setStatusMyExitJailCard(true);
        getAnimationSubController().animationShowAlertInfoJailExit();
        getBtnThrowDice().setVisible(true);
        getImageViewDice().setVisible(true);
        yes.setVisible(false);
        no.setVisible(false);
        yes.setDisable(true);
        no.setDisable(true);
        getButtonSubController().allButtonClickable();
    }

    public void onClickNoForUseCard() {
        getAnimationSubController().animationShowAlertInfoJailExit();
        getBtnThrowDice().setVisible(true);
        getImageViewDice().setVisible(true);
        game.getActivePlayer().setStatusMyExitJailCard(false);
        yes.setVisible(false);
        no.setVisible(false);
        yes.setDisable(true);
        no.setDisable(true);
        getButtonSubController().allButtonClickable();
    }

    public void onClickNoForPayFine() {
        getAnimationSubController().animationShowAlertInfoJailExit();
        getBtnThrowDice().setVisible(true);
        getImageViewDice().setVisible(true);
        payFine.setVisible(false);
        noPayFine.setVisible(false);
        payFine.setDisable(true);
        noPayFine.setDisable(true);
        getButtonSubController().allButtonClickable();
    }

    public void onClickPayFine() {
        if (game.getActivePlayer().getMoney() < 100) {
            getLabelNamePlayerMissMoney().setText(getGame().getActivePlayer().getName());
            getAnimationSubController().animationCardMissMoneyArrive();
            getAnimationSubController().animationShowAlertInfoExit();
        } else {
            getAnimationSubController().animationShowAlertInfoJailExit();
            getSongSubController().moneySong();
            game.getActivePlayer().removeMoney(100);
            game.getActivePlayer().setJail(false);
            updateMoney();
        }
        payFine.setVisible(false);
        noPayFine.setVisible(false);
        payFine.setDisable(true);
        noPayFine.setDisable(true);
        getButtonSubController().allButtonClickable();
        getBtnThrowDice().setVisible(true);
        getImageViewDice().setVisible(true);
    }

    public void onClickBuyExitCard() {
        if (game.getActivePlayer().getMoney() < 200) {
            getLabelNamePlayerMissMoney().setText(getGame().getActivePlayer().getName());
            getAnimationSubController().animationCardMissMoneyArrive();
        }
        songSubController.moneySong();
        nbClickOnSellCard++;
        getButtonSubController().allButtonClickable();
        paneCardSellingSpace.setVisible(false);
        buyExitCard.setVisible(false);
        noBuyExitCard.setVisible(false);
        textOffer.setVisible(false);
        if (btnPlayer1 != null) {
            btnPlayer1.setDisable(false);
        }
        if (btnPlayer2 != null) {
            btnPlayer2.setDisable(false);
        }
        if (btnPlayer3 != null) {
            btnPlayer3.setDisable(false);
        }

        for (Player player : tablePlayers) {
            if (player.getName() == game.getActivePlayer().getSupplier().get(0)) {
                player.decrementExitJailCard();
                player.addMoney(200);
                game.getActivePlayer().removeMoney(200);
                game.getActivePlayer().incrementExitJailCard();
                logger.info("Carte sortie de prison : " + game.getActivePlayer().getExitJailCard());
                game.getActivePlayer().decrementOffer();
                game.getActivePlayer().getSupplier().remove(0);
                updateMoney();
                break;
            }
        }
    }

    public void onClickNoBuyExitCard() {
        if (btnPlayer1 != null) {
            btnPlayer1.setDisable(false);
        }
        if (btnPlayer2 != null) {
            btnPlayer2.setDisable(false);
        }
        if (btnPlayer3 != null) {
            btnPlayer3.setDisable(false);
        }

        getButtonSubController().allButtonClickable();
        paneCardSellingSpace.setVisible(false);
        buyExitCard.setVisible(false);
        noBuyExitCard.setVisible(false);
        textOffer.setVisible(false);
        game.getActivePlayer().decrementOffer();
        for (Player player : tablePlayers) {
            if (player.getName() == game.getActivePlayer().getSupplier().get(0)) {
                game.getActivePlayer().getSupplier().remove(0);
                break;
            }
        }
    }

    /**
     * displaySellCardSpace()
     * * Affiche des bouton par rapport au nomnbre de joueur
     * ou une description de l'offre
     */

    public void onClickCardSellingSpace() {
        nbClickOnSellCard++;
        if (paneSetting.isVisible()) {
            nbClickOnSetting++;
            paneSetting.setVisible(false);
        } else if (getVBoxAddProperty().isVisible()) {
            nbClickOnProperty++;
            getVBoxAddProperty().setVisible(false);
        }
        logger.info("click sell card :" + nbClickOnSellCard);
        if (nbClickOnSellCard % 2 != 0) {
            getBtnThrowDice().setDisable(true);
            notifImage.setVisible(false);
            if (game.getActivePlayer().getOffer() == 0 && game.getActivePlayer().getExitJailCard() != 0) {
                tablePlayers = game.getPlayers();
                paneCardSellingSpace.setVisible(true);
                textOffer.setVisible(false);
                descriptionOffer.setText("Vendre sa carte \"Sortir de prison\" à");
                if (!game.getActivePlayer().getSellTentative()) {
                    if (btnPlayer1 != null) {
                        btnPlayer1.setDisable(false);
                    }
                    if (btnPlayer2 != null) {
                        btnPlayer2.setDisable(false);
                    }
                    if (btnPlayer3 != null) {
                        btnPlayer3.setDisable(false);
                    }
                } else {
                    if (btnPlayer1 != null) {
                        btnPlayer1.setDisable(true);
                    }
                    if (btnPlayer2 != null) {
                        btnPlayer2.setDisable(true);
                    }
                    if (btnPlayer3 != null) {
                        btnPlayer3.setDisable(true);
                    }
                    //  game.getActivePlayer().setSellTentative(false);
                }

                switch (tablePlayers.length) {
                    case 2:
                        for (Player player : tablePlayers) {
                            if (player.getName() != game.getActivePlayer().getName()) {
                                btnPlayer1.setVisible(true);
                                btnPlayer1.setText(player.getName());
                            }
                        }
                        break;
                    case 3:
                        boolean busy = false;
                        for (Player value : tablePlayers) {
                            if (!busy) {
                                if (value.getName() != game.getActivePlayer().getName()) {
                                    btnPlayer1.setVisible(true);
                                    btnPlayer1.setText(value.getName());
                                    busy = true;
                                }
                            } else {
                                if (value.getName() != game.getActivePlayer().getName()) {
                                    btnPlayer2.setVisible(true);
                                    btnPlayer2.setText(value.getName());
                                    break;
                                }
                            }
                        }
                        System.out.println(tablePlayers.length);
                        break;

                    case 4:
                        boolean busy2 = false;
                        boolean busy3 = false;
                        for (Player player : tablePlayers) {
                            if (!busy2) {
                                if (player.getName() != game.getActivePlayer().getName()) {
                                    btnPlayer1.setVisible(true);
                                    btnPlayer1.setText(player.getName());
                                    busy2 = true;
                                }
                            } else if (!busy3) {
                                if (player.getName() != game.getActivePlayer().getName()) {
                                    btnPlayer2.setVisible(true);
                                    btnPlayer2.setText(player.getName());
                                    busy3 = true;
                                }
                            } else {
                                if (player.getName() != game.getActivePlayer().getName()) {
                                    btnPlayer3.setText(player.getName());
                                    btnPlayer3.setVisible(true);
                                    break;
                                }
                            }
                        }
                }
            } else if (game.getActivePlayer().getOffer() != 0) {
                paneCardSellingSpace.setVisible(true);
                textOffer.setVisible(true);
                descriptionOffer.setText("Offre");
                textOffer.setText(game.getActivePlayer().getSupplier() + " tente de vous vendre sa carte Sortie de prison \nVoulez-vous l'accepter");
                buyExitCard.setVisible(true);
                noBuyExitCard.setVisible(true);
                btnPlayer1.setVisible(false);
                if (btnPlayer2 != null) {
                    btnPlayer2.setVisible(false);
                }
                if (btnPlayer3 != null) {
                    btnPlayer3.setVisible(false);
                }
            } else {
                paneCardSellingSpace.setVisible(true);
                textOffer.setVisible(true);
                descriptionOffer.setText("Offre");
                textOffer.setText("Aucune offre");
                btnPlayer1.setVisible(false);
                if (btnPlayer2 != null) {
                    btnPlayer2.setVisible(false);
                }
                if (btnPlayer3 != null) {
                    btnPlayer3.setVisible(false);
                }
            }
        } else {
            paneCardSellingSpace.setVisible(false);
            getBtnThrowDice().setDisable(false);
        }
    }

    public void onClickSuggestMyCard() {
        nbClickOnSellCard++;
        getButtonSubController().allButtonClickable();
        if (btnPlayer1.isFocused()) {
            btnPlayer1.setDisable(true);
            btnPlayer2.setDisable(true);
            btnPlayer3.setDisable(true);
            game.getActivePlayer().setSellTentative(true);
            String buyer = btnPlayer1.getText();
            paneCardSellingSpace.setVisible(false);
            for (Player player : tablePlayers) {
                if (player.getName() == buyer) {
                    player.incrementOffer();
                    player.addSupplier(game.getActivePlayer().getName());
                    break;
                }
            }
        } else if (btnPlayer2.isFocused()) {
            btnPlayer1.setDisable(true);
            btnPlayer2.setDisable(true);
            btnPlayer3.setDisable(true);
            game.getActivePlayer().setSellTentative(true);
            String buyer = btnPlayer2.getText();
            paneCardSellingSpace.setVisible(false);
            for (Player player : tablePlayers) {
                if (player.getName() == buyer) {
                    player.incrementOffer();
                    player.addSupplier(game.getActivePlayer().getName());
                }
            }
        } else if (btnPlayer3.isFocused()) {
            btnPlayer1.setDisable(true);
            btnPlayer2.setDisable(true);
            btnPlayer3.setDisable(true);
            game.getActivePlayer().setSellTentative(true);
            String buyer = btnPlayer3.getText();
            paneCardSellingSpace.setVisible(false);
            for (Player player : tablePlayers) {
                if (player.getName() == buyer) {
                    player.incrementOffer();
                    player.addSupplier(game.getActivePlayer().getName());
                }
            }

        }
        logger.info("Tentative de vente : " + game.getActivePlayer().getSellTentative());

    }

    //-------------------------Setting--------------------------------

    public void onClickBtnSetting(ActionEvent event) {
        nbClickOnSetting++;
        logger.info("Clic setting :" + nbClickOnSetting);
        if (nbClickOnSetting % 2 != 0) {
            getBtnThrowDice().setDisable(true);
            paneSetting.setVisible(true);
            if (paneCardSellingSpace.isVisible()) {
                nbClickOnSellCard++;
                paneCardSellingSpace.setVisible(false);
            } else if (getVBoxAddProperty().isVisible()) {
                nbClickOnProperty++;
                getVBoxAddProperty().setVisible(false);
            }
        } else {
            paneSetting.setVisible(false);
            getBtnThrowDice().setDisable(false);
        }
    }

    //-------------------------Sound--------------------------------

    public void onClickLoudVolume() {
        this.songSubController.getMediaPlayer().setMute(false);
        this.songSubController.getMediaPlayer().setVolume(0.1111);
    }

    public void onClickMute() {
        this.songSubController.getMediaPlayer().setMute(true);

    }

    public void onClickLoudSoundEffect() {
        this.songSubController.setMuteEffectSonnore(false);
        logger.info("Effet sonnore :" + getSongSubController().getMuteEffectSonnore());
    }

    public void onClickMuteEffectSonnore() {
        this.songSubController.setMuteEffectSonnore(true);
        logger.info("Effect sonnore :" + getSongSubController().getMuteEffectSonnore());
    }

    //--------------------ALERT INFO TILES------------------------------------------------------------------------------

    public void showInfoAlert() {
        paneAlerteInfo.setVisible(true);
    }

    public void onClickUnderstood(ActionEvent event) {
        this.paneAlerteInfo.setVisible(false);
    }

    // -----------------------------------------------------------------------------------------------------------------

    public List<BuyableTile> testIfPlayerGetAllTileOfFamily() {
        List<BuyableTile> buyableTilesWithFullFamily = new ArrayList<>();
        for (BuyableTile buyableTile : this.game.getActivePlayer().getBoughtTile()) {
            int amount = this.game.getPlayerAmountOfBoughtTiles(buyableTile.getColor(), this.game.getActivePlayer());
            if (this.game.verifyIfPlayerHaveFamily(amount, buyableTile.getColor())) {
                buyableTilesWithFullFamily.add(buyableTile);
            }
        }
        return buyableTilesWithFullFamily;
    }

    //--------------------PAWN / PLAYER MOVE AND MONEY------------------------------------------------------------------

    public void onClickThrowDice(ActionEvent event) {
        Player activePlayer = this.game.getActivePlayer();
        Tile tile = game.getBoard().getTile(game.getActivePlayer().getPosition());
        AbstractMap.SimpleEntry<Integer, Integer> dice = this.game.throwDice();
        animationSubController.boardMoveRotation();
        paneAlerteInfo.setVisible(false);
        logger.info("Information avant lancer :");
        logger.info("Joueur : " + activePlayer.getName() + " \n Des1 : " + dice.getKey() + " , Des2 : " + dice.getValue());
        logger.info("Argent : " + activePlayer.getMoney());
        logger.info("Nombre de double : " + activePlayer.getNbDouble());
        logger.info(activePlayer.getName() + " Was jailed : " + activePlayer.isWasJailed());
        logger.info(activePlayer.getName() + " jail status: " + activePlayer.isJail());
        logger.info("turns in jail: " + activePlayer.getTurnsInJail());
        logger.info("Carte sortie de prison : " + activePlayer.getExitJailCard());
        logger.info("Offre : " + activePlayer.getOffer());
        logger.info("Fournisseur : " + activePlayer.getSupplier());

        if (Objects.equals(dice.getKey(), dice.getValue())) {
            activePlayer.incrementnbDouble();
        } else {
            activePlayer.setNbDouble(0);
        }

        if (activePlayer.isJail()) {
            logger.info("dice are not equal: " + (!Objects.equals(dice.getKey(), dice.getValue())));
            logger.info("turns in jail > 0: " + (activePlayer.getTurnsInJail() > 0));
            if (!Objects.equals(dice.getKey(), dice.getValue()) && activePlayer.getTurnsInJail() > 0 && !activePlayer.getStatusMyExitJailCard()) {
                activePlayer.decrementTurnsInJail();
                logger.info("turns in jail after decrement: " + activePlayer.getTurnsInJail());
                return;
            } else {
                logger.info(activePlayer.getName() + " leave jail");
                activePlayer.setJail(false);
                activePlayer.setTurnsInJail(0);
                activePlayer.setStatusMyExitJailCard(false);
            }
        }
        int sumDices = dice.getKey() + dice.getValue();
        this.labelThrowDiceResult.setText(String.valueOf(sumDices));

        TranslateTransition tt = new TranslateTransition(Duration.seconds(5.75));
        tt.setNode(circleAnimationQuickly);
        tt.setToX(50);
        tt.play();
        tt.setOnFinished(actionEvent -> {
            pawnSubController.movePlayerAndPawn(activePlayer, (byte) (dice.getKey() + dice.getValue()));
        });

        if (tile instanceof BuyableTile) {
            BuyableTile buyableTile = (BuyableTile) tile;
            logger.info("Couleur de la buyableTile : " + buyableTile.getColor());
            logger.info("Nombres de maisons : " + buyableTile.getNbHouses());
            logger.info("Nombres d'hotels : " + buyableTile.getNbHotels());
        }
    }

    public void informationOfPlayer() {
        textNamePlayer1.setText(game.getPlayer(0).getName());
        paneColorPlayer1.setStyle("-fx-background-color: " + toRGBCode(game.getPlayer(0).getColor().getJavaFxColor()) + ";" + "-fx-border-color:  white;");
        //imagePawnPlayer1.setImage(pawnSubController.getPlayerPawn(getGame().getPlayer(0)).getImage());

        textNamePlayer2.setText(game.getPlayer(1).getName());
        paneColorPlayer2.setStyle("-fx-background-color: " + toRGBCode(game.getPlayer(1).getColor().getJavaFxColor()) + ";" + "-fx-border-color:  white;");
        //imagePawnPlayer2.setImage(pawnSubController.getPlayerPawn(getGame().getPlayer(1)).getImage());

        if (game.getPlayer(2) != null) {
            textNamePlayer3.setText(game.getPlayer(2).getName());
            paneColorPlayer3.setStyle("-fx-background-color: " + toRGBCode(game.getPlayer(2).getColor().getJavaFxColor()) + ";" + "-fx-border-color:  white;");
            //imagePawnPlayer3.setImage(pawnSubController.getPlayerPawn(getGame().getPlayer(2)).getImage());
        }

        if (game.getPlayer(3) != null) {
            textNamePlayer4.setText(game.getPlayer(3).getName());
            paneColorPlayer4.setStyle("-fx-background-color: " + toRGBCode(game.getPlayer(3).getColor().getJavaFxColor()) + ";" + "-fx-border-color:  white;");
            //imagePawnPlayer4.setImage(pawnSubController.getPlayerPawn(getGame().getPlayer(3)).getImage());
        }
        updateMoney();
    }

    public void updateMoney() {
        if (game.getPlayer(0) != null && game.getPlayer(0).isFaillite()) {
            layoutPlayer1.setDisable(true);
            labelMoneyPlayer1.setText("FAILLITE");
        } else if (game.getPlayer(0) != null) {
            labelMoneyPlayer1.setText(String.valueOf(game.getPlayer(0).getMoney()));
        }

        if (game.getPlayer(1) != null && game.getPlayer(1).isFaillite()) {
            layoutPlayer2.setDisable(true);
            labelMoneyPlayer2.setText("FAILLITE");
        } else if (game.getPlayer(1) != null) {
            labelMoneyPlayer2.setText(String.valueOf(game.getPlayer(1).getMoney()));
        }

        if (game.getPlayer(2) != null && game.getPlayer(2).isFaillite()) {
            layoutPlayer3.setDisable(true);
            labelMoneyPlayer3.setText("FAILLITE");
        } else if (game.getPlayer(2) != null) {
            labelMoneyPlayer3.setText(String.valueOf(game.getPlayer(2).getMoney()));
        } else {
            layoutPlayer3.setVisible(false);
        }

        if (game.getPlayer(3) != null && game.getPlayer(3).isFaillite()) {
            layoutPlayer4.setDisable(true);
            labelMoneyPlayer4.setText("FAILLITE");
        } else if (game.getPlayer(3) != null) {
            labelMoneyPlayer4.setText(String.valueOf(game.getPlayer(3).getMoney()));
        } else {
            layoutPlayer4.setVisible(false);
        }
    }

    public void updateTile() {
        for (TilePosition value : TilePosition.values()) {
            if (value.getTile().getTileType() == TileType.BUYABLE && ((BuyableTile) value.getTile()).getLandlord() == null) {
                for (Node child : this.getBorderPanes()[value.getTile().getX()][value.getTile().getY()].getChildren()) {
                    if (child instanceof Sphere) {
                        Sphere sphere = (Sphere) child;
                        PhongMaterial phongMaterial = (PhongMaterial) sphere.getMaterial();
                        phongMaterial.setDiffuseColor(Color.WHITE);
                    }
                }
            }
        }
    }

    public void updatePlayerAndPawn() {
        pawnSubController.setPawnPosition(game.getActivePlayer(), game.getActivePlayer().getPosition());
        for (Player player : game.getPlayers()) {
            if (player.isFaillite()) {
                pawnSubController.getPlayerPawn(player).setVisible(false);
            }
        }
    }

    //---------------------BUYABLE TILE /HOUSES/HOTELS------------------------------------------------------------------

    public void OnHouseButtonClicked(ActionEvent event) {
        nbClickOnProperty++;
        logger.info("Clic property :" + nbClickOnProperty);
        if (nbClickOnProperty % 2 != 0) {
            getBtnThrowDice().setDisable(true);
            circleBtnThrowDice.setVisible(false);

            vBoxAddProperty.setVisible(true);
            if (paneCardSellingSpace.isVisible()) {
                nbClickOnSellCard++;
                paneCardSellingSpace.setVisible(false);
            } else if (paneSetting.isVisible()) {
                nbClickOnSetting++;
                paneSetting.setVisible(false);
            }
        } else {
            vBoxAddProperty.setVisible(false);
            getBtnThrowDice().setDisable(false);
            circleBtnThrowDice.setVisible(true);
        }
        contentsBox.getChildren().clear();
        List<BuyableTile> buyableTilesWithFullFamily = testIfPlayerGetAllTileOfFamily();
        for (BuyableTile buyableTile : buyableTilesWithFullFamily) {
            contentsBox.getChildren().add(buyableSubController.updatedAndAddHouse(buyableTile, buyableTile.getColor()));
        }
    }

    //---------------------ANIMATION/GRAPHICAL ELEMENTS-----------------------------------------------------------------

    public static String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    //---------------------GETTER---------------------------------------------------------------------------------------

    public int getNextTilePosition(int tilePosition) {
        return tilePosition == 39 ? 0 : ++tilePosition;
    }

    public void setDices(int index, Group dice) {
        this.dices[index] = dice;
    }

    public Group loadModel(URL url) {
        return new Task<>() {
            @Override
            protected Group call() {
                Group modelRoot = new Group();

                ObjModelImporter importer = new ObjModelImporter();
                importer.read(url);

                for (MeshView view : importer.getImport()) {
                    modelRoot.getChildren().add(view);
                }
                return modelRoot;
            }
        }.call();
    }

    public MFXButton getYes() {
        return yes;
    }

    public MFXButton getNo() {
        return no;
    }

    public MFXButton getPayFine() {
        return payFine;
    }

    public MFXButton getNoPayFine() {
        return noPayFine;
    }

    public Pane getPaneAlerteInfo() {
        return paneAlerteInfo;
    }

    public MFXButton getBtnSellCard() {
        return btnSellCard;
    }

    public ButtonSubController getButtonSubController() {
        return buttonSubController;
    }

    public Logger getLogger() {
        return logger;
    }

    public SongSubController getSongSubController() {
        return songSubController;
    }

    public ImageView getNotifImage() {
        return notifImage;
    }
}
