package com.intech.spacemonopoly.controller;

import com.intech.spacemonopoly.controller.boardsubcontroller.AnimationSubController;
import com.intech.spacemonopoly.controller.musicsubcontroller.SongSubController;
import com.intech.spacemonopoly.controller.pawns.Pawn;
import com.intech.spacemonopoly.model.Color;
import com.intech.spacemonopoly.model.Player;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import io.github.palexdev.materialfx.controls.MFXSlider;
import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.MeshView;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.*;

@Getter @Setter
public class CreatePlayerController implements Initializable {

    @FXML
    public HBox hBoxBtnSettings, hBoxBtnBack;
    @FXML
    public Group objDisplayGroup;
    @FXML
    public MeshView objDisplayMeshView;
    private Logger logger;
    @FXML
    private Label avantSelection;
    @FXML
    private MFXSlider volumeSlider;
    @FXML
    private ImageView song;
    @FXML
    private ImageView mute;
    @FXML
    private TextField name;
    @FXML
    private MFXButton pion1, pion2, pion3, pion4;
    @FXML
    public AnchorPane bodyCreatePlayer, slideSettings;
    @FXML
    public MFXButton btnSettings, slideClose, backSelectPlayer;
    @FXML
    public StackPane parentContainerCreatePlayer;
    @FXML @Getter
    public BorderPane borderPaneCreate;
    @FXML
    public Pane validated;
    @FXML
    public Pane paneAnimation;
    @FXML
    public AnchorPane formulaireCreate;
    @FXML
    public Pane animationRocket1;
    @FXML
    public Pane animationRocket2;
    @FXML
    public Pane animationRocket3;
    @FXML
    public Circle circle;
    @FXML
    public AnchorPane success;
    @FXML
    private RadioButton color1, color2, color3, color4;
    @FXML
    private Label error;
    @FXML
    private MFXButton btnValidate;
    @FXML
    public MFXProgressSpinner progressBar2;
    @FXML
    public Text description;
    @Setter @Getter
    private HomePageController homePageController;
    private SelectPlayerAmountController selectPlayerAmountController;
    @FXML
    private Pane showImgSelectedVisibility;
    @Getter
    private BoardController boardController;
    private Parent root;

    @Setter
    private int maxPlayers;
    private Player[] players;
    private Group[] playerPawns;
    private int createPlayerIndex;

    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);
    private SongSubController songSubController;
    private AnimationSubController animationSubController;

    private Map<Button, Pawn> buttonAndPawnLink;
    private Map<Color, ToggleButton> buttonAndColorLink;
    private Pawn selectedPawn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.animationSubController = new AnimationSubController(null, null, this, null, null);

        slideSettings();
        this.selectedPawn = null;

        this.buttonAndPawnLink = new HashMap<>();
        this.buttonAndPawnLink.put(pion1, Pawn.SPACE_SHIP_3);
        this.buttonAndPawnLink.put(pion2, Pawn.UFO);
        this.buttonAndPawnLink.put(pion3, Pawn.SPACE_SHIP_2);
        this.buttonAndPawnLink.put(pion4, Pawn.SPACE_SHIP_5);

        this.buttonAndColorLink = new HashMap<>();
        this.buttonAndColorLink.put(Color.ROUGE, color1);
        this.buttonAndColorLink.put(Color.BLEU, color4);
        this.buttonAndColorLink.put(Color.VERT, color3);
        this.buttonAndColorLink.put(Color.ORANGE, color2);

        this.createPlayerIndex = 0;

        this.logger = LoggerFactory.getLogger(CreatePlayerController.class);
        FXMLLoader boardClassLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/Board.fxml"));

        try {
            root = boardClassLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        boardController = boardClassLoader.getController();
        progressBar2.setProgress(0.0);
        animationRocket();

        name.setOnMouseClicked(mouseEvent -> name.setStyle("-fx-text-inner-color: white;"));

        volumeSlider.valueProperty().addListener(observable -> {
            this.homePageController.getSongSubController().getMediaPlayer().setVolume(volumeSlider.getValue() / 100);
            if (this.homePageController.getSongSubController().getMediaPlayer().getVolume() == 0) {
                this.homePageController.getSongSubController().getMediaPlayer().setMute(true);
                song.setVisible(false);
                mute.setVisible(true);
                volumeSlider.setValue(0);
            } else {
                this.homePageController.getSongSubController().getMediaPlayer().setMute(false);
                song.setVisible(true);
                mute.setVisible(false);
            }
        });
    }

    public void slideSettings() {
        slideSettings.setTranslateX(290);
        btnSettings.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.7));
            slide.setNode(slideSettings);

            slide.setToX(0);
            slide.play();

            slideSettings.setTranslateX(282);

            slide.setOnFinished((ActionEvent e) -> {
                btnSettings.setVisible(true);
                slideClose.setVisible(true);
            });
        });
        slideClose.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.7));
            slide.setNode(slideSettings);

            slide.setToX(290);
            slide.play();

            slideSettings.setTranslateX(0);

            slide.setOnFinished((ActionEvent e) -> {
                btnSettings.setVisible(true);
                slideClose.setVisible(false);
            });
        });
    }

    public void checkFieldsAndStartGame(ActionEvent event) {
        String  nameValue = name.getText();

        if (players == null) {
            this.players = new Player[maxPlayers];
            this.playerPawns = new Group[maxPlayers];
        }

        if (!isValidName(nameValue)) {
            error.setText("Pseudo incorrect!");
            showError();
            return;
        }

        if (!isColorSelected()) {
            error.setText("Veuillez remplir tous les champs!");
            showError();
            return;
        }

        if (selectedPawn == null){
            error.setText("Veuillez selectionner un pion!");
            showError();
            return;
        }

        if (isNameAlreadySelected(name.getText())) {
            error.setText("Ce nom a deja ete choisi!");
            showError();
            return;
        }

        if (isColorAlreadySelected(getSelectedColor())) {
            error.setText("Cette couleur a deja ete choisi!");
            showError();
            return;
        }

        if (isPawnAlreadySelected(this.selectedPawn)) {
            error.setText("Ce pion a deja ete choisi!");
            showError();
            return;
        }

        showImgSelectedVisibility.setVisible(false);

        players[createPlayerIndex] = new Player(name.getText(), getSelectedColor());
        playerPawns[createPlayerIndex] = selectedPawn.loadPawnModel();

        if (createPlayerIndex >= maxPlayers -1) {
            goToBoardPage(event);
            return;
        }

        createPlayerIndex++;
        error.setVisible(false);
        name.setText("");
        enableColorButtons();
        disablePawnButtons();
        this.selectedPawn = null;
    }

    public void goToBoardPage(ActionEvent event) {
        parentContainerCreatePlayer.getChildren().remove(0);
        homePageController.getSongSubController().stopSong();
        homePageController.getSongSubController().songClick();
        this.songSubController = new SongSubController(this);
        this.boardController.setSongSubController(songSubController);

        this.btnValidate.setOnMouseClicked(mouseEvent -> {
            this.success.setVisible(true);
            this.btnValidate.setStyle("-fx-background-color: #4BAE4F; -fx-alignment: center-left; -fx-background-radius: 25; -fx-border-radius: 25; -fx-control-inner-background: transparent; -fx-border-color:transparent; -fx-focus-color: transparent;");
            this.btnValidate.setText(" ");
            validated.setVisible(true);
            FadeTransition fadeT1 = new FadeTransition();
            fadeT1.setDuration(Duration.seconds(0.7));
            fadeT1.setNode(validated);
            fadeT1.setFromValue(0);
            fadeT1.setToValue(10);
            fadeT1.play();
            FadeTransition fadeT2 = new FadeTransition();
            fadeT2.setDuration(Duration.seconds(0.7));
            fadeT2.setNode(btnValidate);
            fadeT2.setFromValue(0);
            fadeT2.setToValue(10);
            fadeT2.play();
            this.progressBar2.setStyle("-fx-fill: white; -fx-focus-color: transparent; -fx-border-color:transparent;");

            Scene scene = btnValidate.getScene();
            String css = Objects.requireNonNull(this.getClass().getClassLoader().getResource("css/Board.css")).toExternalForm();

            scene.getStylesheets().add(css);
            assert root != null;
            root.translateYProperty().set(scene.getHeight());
            this.parentContainerCreatePlayer.getChildren().add(root);
            this.playAnimation(root);

            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            responsiveScreen(stage);
            stage.setScene(scene);
            stage.show();

            Rotate rxBox = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
            Rotate ryBox = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
            Rotate rzBox = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS);
            rxBox.setAngle(-60);
            ryBox.setAngle(0);
            rzBox.setAngle(0);

            boardController.getGroupPlanetSolarSystem().setTranslateX(385.5);
            boardController.getGroupPlanetSolarSystem().setTranslateY(385.5);
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long l) {
                    boardController.getGroupPlanetSolarSystem().rotateProperty().set(boardController.getGroupPlanetSolarSystem().getRotate() - 0.1);
                }
            };
            timer.start();

            boardController.startGame(players, playerPawns);
            boardController.getSubScene().setCamera(boardController.getCamera());
            boardController.getCamera().setNearClip(0.01);
            boardController.getCamera().setTranslateZ(-2000);
            boardController.getCamera().setFarClip(1000000000);
            SmartGroup group = new SmartGroup();
            group.getChildren().add(boardController.getGroupBoard3D());
            boardController.getGroupBoard3D().setTranslateZ(1200);
            boardController.getSubScene().setFill(javafx.scene.paint.Color.TRANSPARENT);
            Group groupUnivers3D = new Group();
            groupUnivers3D.getChildren().addAll(boardController.getInitialise3DElementsSubController().groupPlanetSolarSystem(),boardController.getInitialise3DElementsSubController().vaisseauMove(), boardController.getInitialise3DElementsSubController().environnementStar(boardController.getSubScene()), boardController.getGroupBoard3D());
            boardController.getTheFinalBackground3D().getChildren().add(groupUnivers3D);
            boardController.getTheFinalBackground3D().getTransforms().addAll(rxBox, ryBox, rzBox);
            boardController.getTheFinalBackground3D().setTranslateY(-80);
            boardController.getTheFinalBackground3D().setTranslateY(-140);
            boardController.getTheFinalBackground3D().setTranslateZ(-100);

            initMouseControl(group, scene);

            stage.getScene().setOnKeyPressed(keyEvent -> {
                switch (keyEvent.getCode()) {
                    case U:
                        boardController.getSubSceneWinner().getCamera().translateZProperty().set(boardController.getSubSceneWinner().getCamera().getTranslateZ() + 100);
                        break;
                    case I:
                        boardController.getSubSceneWinner().getCamera().translateZProperty().set(boardController.getSubSceneWinner().getCamera().getTranslateZ() - 100);
                        break;
                    case T:
                        boardController.getPawnSubController().getPlayerPawn(boardController.getGame().getActivePlayer()).translateZProperty().set(boardController.getPawnSubController().getPlayerPawn(boardController.getGame().getActivePlayer()).getTranslateZ() + 100);
                        break;
                    case Y:
                        boardController.getPawnSubController().getPlayerPawn(boardController.getGame().getActivePlayer()).translateZProperty().set(boardController.getPawnSubController().getPlayerPawn(boardController.getGame().getActivePlayer()).getTranslateZ() - 100);
                        break;
                    case Z:
                        boardController.getCamera().translateZProperty().set(boardController.getCamera().getTranslateZ() + 100);
                        break;
                    case S:
                        boardController.getCamera().translateZProperty().set(boardController.getCamera().getTranslateZ() - 100);
                        break;
                    case E:
                        boardController.getCamera().translateYProperty().set(boardController.getCamera().getTranslateY() + 100);
                        break;
                    case D:
                        boardController.getCamera().translateYProperty().set(boardController.getCamera().getTranslateY() - 100);
                        break;
                    case R:
                        boardController.getCamera().translateXProperty().set(boardController.getCamera().getTranslateX() + 100);
                        break;
                    case F:
                        boardController.getCamera().translateXProperty().set(boardController.getCamera().getTranslateX() - 100);
                        break;
                    case NUMPAD7:
                        boardController.getTheFinalBackground3D().getTransforms().clear();
                        Rotate rxBox2 = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
                        rxBox2.setAngle(0);
                        boardController.getTheFinalBackground3D().getTransforms().add(rxBox2);
                        break;
                    case B:
                        boardController.getBodyBoard3D().translateZProperty().set(boardController.getBodyBoard3D().getTranslateZ() + 1);
                        break;
                    case N:
                        boardController.getBodyBoard3D().translateZProperty().set(boardController.getBodyBoard3D().getTranslateZ() - 1);
                        break;
                    case NUMPAD1:
                        boardController.getTheFinalBackground3D().getTransforms().clear();
                        Rotate ryBox_ = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
                        Rotate rzBox_ = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS);
                        ryBox_.setAngle(-55);
                        rzBox_.setAngle(-90);
                        boardController.getTheFinalBackground3D().getTransforms().setAll(rzBox, ryBox);
                        break;
                    case NUMPAD2:
                        boardController.getTheFinalBackground3D().getTransforms().clear();
                        Rotate rxBox2_ = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
                        Rotate ryBox2 = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
                        Rotate rzBox2 = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS);
                        rxBox2_.setAngle(55);
                        ryBox2.setAngle(0);
                        rzBox2.setAngle(180);
                        boardController.getTheFinalBackground3D().getTransforms().setAll(rzBox2, rxBox2_, ryBox2);
                        break;
                    case NUMPAD3:
                        boardController.getTheFinalBackground3D().getTransforms().clear();
                        Rotate ryBox3 = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
                        Rotate rzBox3 = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS);
                        ryBox3.setAngle(55);
                        rzBox3.setAngle(90);
                        boardController.getTheFinalBackground3D().getTransforms().setAll(rzBox3, ryBox3);
                        break;
//                    case Q:
//                        group.rotateByX(10);
//                        break;
//                    case D:
//                        group.rotateByX(-10);
//                        break;
//                    case E:
//                        group.rotateByY(10);
//                        break;
//                    case C:
//                        group.rotateByY(-10);
//                        break;
                }
            });

            Thread th = new Thread(new ThreadController());
            th.start();
        });
    }

    private Color getSelectedColor() {
        RadioButton btnChoiceColor = null;

        if (color1.isSelected()) {
            btnChoiceColor = color1;
            btnChoiceColor.setDisable(true);
        } else if (color2.isSelected()) {
            btnChoiceColor = color2;
        } else if (color3.isSelected()) {
            btnChoiceColor = color3;
        } else if (color4.isSelected()) {
            btnChoiceColor = color4;
        }

        if (btnChoiceColor == null) {
            logger.warn("color selected is equal to null");
            return Color.BLEU;
        }

        return Color.valueOf(btnChoiceColor.getText().toUpperCase(Locale.ROOT));
    }

    public void switchImagePion(ActionEvent event) {
        showImgSelectedVisibility.setVisible(true);
        Pawn pawn = buttonAndPawnLink.get((Button) event.getSource());
        selectedPawn = pawn;
        showImgSelectedVisibility.getChildren().clear();
        showImgSelectedVisibility.getChildren().add(pawn.getObject());
        Node node = showImgSelectedVisibility.getChildren().get(0);

        node.setScaleX(35);
        node.setScaleY(35);
        node.setScaleZ(35);
        node.setLayoutX(120);
        node.setLayoutY(120);

        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(10), node);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360);
        rotateTransition.setCycleCount(500);
        rotateTransition.play();

        description.setText(pawn.getDescription());
        homePageController.getSongSubController().songClick();
        avantSelection.setVisible(false);
    }

    private boolean isColorSelected() {
        return color1.isSelected() || color2.isSelected() || color3.isSelected()|| color4.isSelected();
    }

    private void playAnimation(Parent root) {
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainerCreatePlayer.getChildren().removeAll(bodyCreatePlayer, borderPaneCreate, formulaireCreate, success);
        });

        Timeline timeline1 = new Timeline();
        KeyValue kv1 = new KeyValue(circle.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf1 = new KeyFrame(Duration.seconds(3.2), kv1);
        timeline1.getKeyFrames().add(kf1);
        timeline1.setOnFinished(t -> {
            boardController.getAnimationSubController().animationBeginTurnBoard();
            timeline.play();
        });
        timeline1.play();

        Timeline timer = new Timeline();
        KeyValue start = new KeyValue(boardController.getCircleAnimationQuickly().radiusProperty(), 0, Interpolator.EASE_IN);
        KeyFrame end = new KeyFrame(Duration.seconds(8), start);
        timer.getKeyFrames().add(end);
        timer.setOnFinished(t -> boardController.closePaneBegin());
        timer.play();
    }

    public void disableRadioButton(ActionEvent event) {
        ToggleButton button = (ToggleButton) event.getSource();

        if (button.isSelected()) {
            for (ToggleButton toggleButton : buttonAndColorLink.values()) {
                if (button == toggleButton) continue;
                toggleButton.setDisable(true);
            }
        } else {
            enableColorButtons();
        }
    }

    public boolean isValidName( String nameValue){
        String regEx = "^(?=[a-zA-Z._]{2,16}$)(?!.*[_.]{2})[^_.].*[^_.]$";
        return nameValue.matches(regEx);
    }

    public void backSelectPlayer(ActionEvent event) {
        getAnimationSubController().switchSceneCreatePlayerToSelect();
        Timeline timer = new Timeline();
        KeyFrame kf1 = new KeyFrame(Duration.seconds(1), (KeyValue) null);
        timer.getKeyFrames().add(kf1);
        timer.play();
        timer.setOnFinished(actionEvent -> {
            homePageController.getSelectPlayerAmountController().getParentContainerSelectPlayer().getChildren().add(0, homePageController.getSubScene());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setMaximized(true);
            stage.setScene(homePageController.getSelectPlayerAmountScene());
            stage.show();
        });
        homePageController.removeSlideSettings();
        homePageController.getSongSubController().songClick();
    }

    public void responsiveScreen(Stage stage) {
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();

        if(screenHeight >= 1000 && screenWidth >= 1780) {
            stage.setWidth(1920);
            stage.setHeight(1040);
        } else if (screenHeight >= 900 && screenWidth >= 1400) {
            System.out.println("My Screen");
            boardController.getAp().setTranslateY(-35);
            boardController.getResponsiveBackground().setTranslateY(-35);
        } else {
            boardController.getResponsiveBoard3D().setTranslateZ(600);
            boardController.getResponsiveBackground().setTranslateZ(400);
            stage.setWidth(1150);
            stage.setHeight(703);
        }
    }

    public void animationRocket() {
        TranslateTransition translateAnimation1 = new TranslateTransition(Duration.seconds(12), animationRocket1);
        TranslateTransition translateAnimation2 = new TranslateTransition(Duration.seconds(12), animationRocket2);
        TranslateTransition translateAnimation3 = new TranslateTransition(Duration.seconds(12), animationRocket3);

        translateAnimation1.setInterpolator(Interpolator.LINEAR);
        translateAnimation2.setInterpolator(Interpolator.LINEAR);
        translateAnimation3.setInterpolator(Interpolator.LINEAR);

        animationRocket1.setTranslateY(100);
        animationRocket2.setTranslateY(100);
        animationRocket3.setTranslateY(100);

        translateAnimation1.setToY(-1900);
        translateAnimation2.setToY(-1500);
        translateAnimation3.setToY(-1200);

        translateAnimation1.setCycleCount(TranslateTransition.INDEFINITE);
        translateAnimation2.setCycleCount(TranslateTransition.INDEFINITE);
        translateAnimation3.setCycleCount(TranslateTransition.INDEFINITE);

        translateAnimation1.play();
        translateAnimation2.play();
        translateAnimation3.play();
    }

    private boolean isNameAlreadySelected(String name) {
        if (this.players[0] == null) return false;
        for (Player player : this.players) {
            if (player == null) continue;
            if (player.getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    private boolean isColorAlreadySelected(Color color) {
        if (this.players == null) return false;
        for (Player player : this.players) {
            if (player == null) continue;
            if (player.getColor().equals(color)) return true;
        }
        return false;
    }

    private boolean isPawnAlreadySelected(Pawn selectedPawn) {
        if (this.playerPawns == null) return false;
        if (selectedPawn == null) return false;
        return Arrays.stream(playerPawns).anyMatch(pawn -> {
            if (pawn != null)
                return selectedPawn.getObject().getId().equals(pawn.getId());
            return false;
        });
    }

    private void enableColorButtons() {
        for (Color color : this.buttonAndColorLink.keySet()) {
            if (isColorAlreadySelected(color)) {
                this.buttonAndColorLink.get(color).setSelected(false);
                this.buttonAndColorLink.get(color).setDisable(true);
                continue;
            }
            this.buttonAndColorLink.get(color).setDisable(false);
            this.buttonAndColorLink.get(color).setVisible(true);
        }
    }

    private void disablePawnButtons() {
        for (Button button : buttonAndPawnLink.keySet()) {
            if (isPawnAlreadySelected(buttonAndPawnLink.get(button))) {
                button.setDisable(true);
            }
        }
    }

    private void initMouseControl(SmartGroup group, Scene scene) {
        Rotate xRotate;
        Rotate yRotate;
        group.getTransforms().addAll(
                xRotate = new Rotate(0, Rotate.X_AXIS),
                yRotate = new Rotate(0, Rotate.Y_AXIS)
        );
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        scene.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY + anchorX - event.getSceneX());
        });
    }

    public void showError() {
        error.setVisible(true);
        btnValidate.setStyle("-fx-background-color: #a40505; -fx-background-radius: 25; -fx-border-radius: 25; -fx-control-inner-background: transparent; -fx-border-color:transparent; -fx-focus-color: transparent;");
        homePageController.getSongSubController().songBadClick();
    }

    class ThreadController implements Runnable {

        @Override
        public void run() {
            try {
                for (int i = 0; i < 101; i++) {
                    Thread.sleep(25);
                    progressBar2.setProgress(i / 100.0);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class SmartGroup extends Group {
        Rotate r;
        Transform t = new Rotate();

        void rotateByX(int ang) {
            r = new Rotate(ang, Rotate.X_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().addAll(t);
        }

        void rotateByY(int ang) {
            r = new Rotate(ang, Rotate.Y_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().addAll(t);
        }
    }
}
