package com.intech.spacemonopoly.controller;

import com.intech.spacemonopoly.controller.boardsubcontroller.AnimationSubController;
import com.intech.spacemonopoly.controller.musicsubcontroller.SongSubController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXSlider;
import javafx.animation.*;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

@Getter
@Setter
public class HomePageController implements Initializable {

    public Sphere allStars = new Sphere();
    public PerspectiveCamera camera = new PerspectiveCamera(true);

    @FXML
    private SubScene subScene;
    @FXML
    private Pane parentTutorial, layoutTitleAndButton;
    @FXML
    private Group groupElements3D = new Group();
    @FXML
    private Pane theFinalBackground3D;
    @FXML
    private Label title;
    @FXML
    private MFXSlider volumeSlider;
    @FXML
    private Pane rocketAnimate;
    @FXML
    private Button createPersona;
    @FXML
    private Button btnTutorials;
    @FXML
    private Button btnSettings;
    @FXML
    private MFXButton slideClose;
    @FXML
    private AnchorPane slideSettings, parentHomePage, parentContainer3DAnd2D;
    @FXML
    private Circle circleAnim;
    @FXML
    private Label titleAnimate;
    @FXML
    private CubicCurve myCurve;
    @FXML
    private ImageView song;
    @FXML
    private ImageView mute;
//    private ArrayList<File> songs;
//    private int songNumber;
//    private MediaPlayer mediaPlayer;

    @Getter
    private SongSubController songSubController;
    private AnimationSubController animationSubController;
    private BoardController boardController;
    private Parent tutorialParent;
    private Parent selectPlayerParent;
    private Parent boardParent;
    private Scene selectPlayerAmountScene;
    private SelectPlayerAmountController selectPlayerAmountController;
    private TutorialController tutorialController;
    private Scene tutorialScene;
    @Getter @Setter
    private Scene homePageScene;
    private CreatePlayerController createPlayerController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.animationSubController = new AnimationSubController(null, this, null, null, null);

        this.songSubController = new SongSubController();
        title.setStyle("-fx-font-family: 'Berlin Sans FB Demi'; -fx-fill: white;");
        animateRocket();
        slideSettings();
        initButtonStyle();
        FXMLLoader selectPlayerAmountLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/SelectPlayerAmount.fxml"));
        FXMLLoader tutorialLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/Tutorial.fxml"));
        FXMLLoader boardClassLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/Board.fxml"));
        tutorialParent = null;
        selectPlayerParent = null;
        boardParent = null;

        try {
            selectPlayerParent = selectPlayerAmountLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            tutorialParent = tutorialLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            boardParent = boardClassLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        boardController = boardClassLoader.getController();

        selectPlayerAmountController = selectPlayerAmountLoader.getController();
        selectPlayerAmountController.setHomePageController(this);

        tutorialController = tutorialLoader.getController();
        tutorialController.setHomePageController(this);

        assert selectPlayerParent != null;
        this.selectPlayerAmountScene = new Scene(selectPlayerParent);

        assert tutorialParent != null;
        this.tutorialScene = new Scene(tutorialParent);

        volumeSlider.setValue(this.songSubController.getMediaPlayer().getVolume() * 100);
        volumeSlider.valueProperty().addListener(observable -> {
            this.songSubController.getMediaPlayer().setVolume(volumeSlider.getValue() / 100);
            if (this.songSubController.getMediaPlayer().getVolume() == 0) {
                this.songSubController.getMediaPlayer().setMute(true);
                song.setVisible(false);
                mute.setVisible(true);
                volumeSlider.setValue(0);
            } else {
                this.songSubController.getMediaPlayer().setMute(false);
                song.setVisible(true);
                mute.setVisible(false);
            }
        });
    }

    public void switchSceneWithSelectPlayer(ActionEvent event) {
        getAnimationSubController().switchSceneToSelectPlayer();
        Timeline timer = new Timeline();
        KeyFrame kf1 = new KeyFrame(Duration.seconds(1), (KeyValue) null);
        timer.getKeyFrames().add(kf1);
        timer.play();
        timer.setOnFinished(actionEvent -> {
            selectPlayerAmountController.getPaneSelectedPlayers().setOpacity(0);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            selectPlayerAmountController.getParentContainerSelectPlayer().getChildren().add(0,getSubScene());
            responsiveScreen(stage, selectPlayerAmountScene);
            stage.setScene(selectPlayerAmountScene);
            stage.show();
        });
        String css = Objects.requireNonNull(this.getClass().getClassLoader().getResource("css/selectAmountPlayer.css")).toExternalForm();
        selectPlayerAmountScene.getStylesheets().add(css);
        songSubController.songClick();

    }

    public void switchSceneWithTutorial(ActionEvent event) {
        getAnimationSubController().switchSceneToTutorial();
        Timeline timer = new Timeline();
        KeyFrame kf1 = new KeyFrame(Duration.seconds(1), (KeyValue) null);
        timer.getKeyFrames().add(kf1);
        timer.play();
        timer.setOnFinished(actionEvent -> {
            tutorialController.getContents_tutorial().setOpacity(0);
            tutorialController.getParentTutorial().getChildren().add(0,getSubScene());
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            responsiveScreen(stage, tutorialScene);
            stage.setScene(tutorialScene);
            stage.show();
        });
        String css = Objects.requireNonNull(this.getClass().getClassLoader().getResource("css/Tutorial.css")).toExternalForm();
        selectPlayerAmountScene.getStylesheets().add(css);
        songSubController.songClick();

    }

    public void responsiveScreen(Stage stage, Scene scene) {
//        ReadOnlyDoubleProperty screenHeight =scene.heightProperty();
//        ReadOnlyDoubleProperty screenWidth =scene.widthProperty();
//        System.out.println(screenHeight);
//        System.out.println(screenWidth);
//        System.out.println(scene.getWidth());
//        System.out.println(scene.getHeight());

        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();

        if(screenHeight >= 1000 && screenWidth >= 1780) {
            stage.setWidth(1920);
            stage.setHeight(1040);
        }  else if (screenHeight > 950 && screenWidth > 1530) {
            selectPlayerAmountController.getBorderPaneCreate().setScaleX(1);
            selectPlayerAmountController.getBorderPaneCreate().setScaleY(1);
        } else if (screenHeight < 945 && screenWidth < 1520) {
            selectPlayerAmountController.getBorderPaneCreate().setScaleX(0.9);
            selectPlayerAmountController.getBorderPaneCreate().setScaleY(0.9);
        } else if (screenHeight < 750 && screenWidth < 1200) {
            System.out.println("Screen Jerek");
            selectPlayerAmountController.getBorderPaneCreate().setScaleX(0.75);
            selectPlayerAmountController.getBorderPaneCreate().setScaleY(0.75);
        }
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

    private void animateRocket() {
        TranslateTransition translateT = new TranslateTransition(Duration.seconds(2.5), titleAnimate);
        translateT.setFromX(0);
        translateT.setToX(53);
        translateT.setInterpolator(Interpolator.EASE_IN);
        translateT.setDelay(Duration.seconds(2.5));
        translateT.play();

        PathTransition pathT = new PathTransition();
        pathT.setDuration(Duration.seconds(3));
        pathT.setInterpolator(Interpolator.EASE_IN);
        pathT.setPath(myCurve);
        pathT.setNode(rocketAnimate);
        pathT.setDelay(Duration.seconds(3));
        pathT.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathT.play();
    }

    public void removeSlideSettings() {
        slideSettings.setTranslateX(290);
    }

    public void managementSong() {
        if (song.isVisible()) {
            this.songSubController.getMediaPlayer().setMute(true);
            song.setVisible(false);
            mute.setVisible(true);
            volumeSlider.setValue(0);

        } else {
            this.songSubController.getMediaPlayer().setMute(false);
            song.setVisible(true);
            mute.setVisible(false);
            volumeSlider.setValue(0.1111);

        }
    }

    public void initButtonStyle() {
        btnTutorials.setOnMouseEntered(event -> {
            btnTutorials.setStyle("-fx-scale-y: 1.3; -fx-scale-x: 1.3; -fx-text-fill: white; -fx-alignment: center-left; -fx-background-color: transparent; -fx-font-size: 23px;");
        });
        btnTutorials.setOnMouseExited(event -> {
            btnTutorials.setStyle("-fx-scale-y: 1; -fx-scale-x: 1; -fx-text-fill: white; -fx-alignment: center-left; -fx-background-color: transparent; -fx-font-size: 23px;");
        });
        btnSettings.setOnMouseEntered(event -> {
            btnSettings.setStyle("-fx-scale-y: 1.3; -fx-scale-x: 1.3; -fx-text-fill: white; -fx-alignment: center-left; -fx-background-color: transparent; -fx-font-size: 23px;");
        });
        btnSettings.setOnMouseExited(event -> {
            btnSettings.setStyle("-fx-scale-y: 1; -fx-scale-x: 1; -fx-text-fill: white; -fx-alignment: center-left; -fx-background-color: transparent; -fx-font-size: 23px;");
        });
        createPersona.setOnMouseEntered(event -> {
            createPersona.setStyle("-fx-scale-y: 1.3; -fx-scale-x: 1.3; -fx-text-fill: white; -fx-alignment: center-left; -fx-background-color: transparent; -fx-font-size: 23px;");
        });
        createPersona.setOnMouseExited(event -> {
            createPersona.setStyle("-fx-scale-y: 1; -fx-scale-x: 1; -fx-text-fill: white; -fx-alignment: center-left; -fx-background-color: transparent; -fx-font-size: 23px;");
        });
    }

    public void environnement3D(Scene scene) {
        getSubScene().setCamera(getCamera());
        getCamera().setNearClip(5);
        getCamera().setFarClip(100000);

        getBoardController().getAnimationSubController().firstAnimationCamera(getCamera());
        getBoardController().getGroupPlanetSolarSystem().setTranslateZ(-2000);
        getGroupElements3D().getChildren().addAll(getBoardController().getInitialise3DElementsSubController().groupPlanetSolarSystem(), environnement3DStar(getSubScene()));

        Rotate rxBox = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
        rxBox.setAngle(-90);
        getTheFinalBackground3D().getChildren().add(groupElements3D);
        getTheFinalBackground3D().getTransforms().addAll(rxBox);

        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case Z:
                    getTheFinalBackground3D().translateZProperty().set(getTheFinalBackground3D().getTranslateZ() + 100);
                    break;
                case S:
                    getTheFinalBackground3D().translateZProperty().set(getTheFinalBackground3D().getTranslateZ() - 100);
                    break;
            }
        });
    }

    public Sphere environnement3DStar(SubScene scene) {
        int i = 0;
        int allStars = 250;
        while (i < allStars) {
            Sphere stars = new Sphere();
            double size = Math.min(Math.random(), 0.3);
            int posX = (int) (Math.floor(Math.random() * scene.getWidth())) - 750;
            int posY = (int) (Math.floor(Math.random() * scene.getHeight())) - 650;
            int posZ = (int) (Math.floor(Math.random() * scene.getWidth())) - 650;
            double delay = (double) (Math.random() * 250);
            double duration = (double) (Math.random() * 250);

            stars.setRadius(Math.max(size, 0.05));
            AmbientLight ambientLight = new AmbientLight();

            TranslateTransition translateTransition = new TranslateTransition();
            translateTransition.setNode(stars);
            translateTransition.setFromX(-1000);
            translateTransition.setFromY(100);
            translateTransition.setFromZ(-100);
            translateTransition.setToZ(posZ);
            translateTransition.setToX(posX);
            translateTransition.setToY(posY);
            translateTransition.setDuration(Duration.seconds(0.1));
            translateTransition.play();

            TranslateTransition translateTransition2 = new TranslateTransition();
            translateTransition2.setNode(stars);
            translateTransition2.setFromX(posX + 500);
            translateTransition2.setFromY(posY + 500);
            translateTransition2.setFromZ(posZ + 500);
            translateTransition2.setToZ(posZ);
            translateTransition2.setToX(posX);
            translateTransition2.setToY(posY);
            translateTransition2.setDelay(Duration.seconds(delay));
            translateTransition2.setDuration(Duration.seconds(1 + duration));
            translateTransition.setOnFinished(actionEvent -> {
                translateTransition2.play();
                translateTransition2.setOnFinished(actionEvent1 -> {
                    translateTransition2.play();
                });
            });

            setAllStars(stars);
            Group groupPointLight = new Group();
            groupPointLight.getChildren().addAll(ambientLight, getAllStars());
            getTheFinalBackground3D().getChildren().add(groupPointLight);
            i++;
        }

        return getAllStars();
    }
}

