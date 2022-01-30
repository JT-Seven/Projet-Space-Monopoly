package com.intech.spacemonopoly.controller;

import com.intech.spacemonopoly.controller.boardsubcontroller.AnimationSubController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXSlider;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
public class SelectPlayerAmountController implements Initializable {

    @FXML
    private ImageView song;
    @FXML
    private ImageView mute;
    @FXML
    private Button btnSongMute;
    @FXML
    private HBox hBoxSettingsSelect, hBoxBackSelect;
    @FXML
    private Label titleNbJoueurs;
    @FXML
    private AnchorPane slideSettings, paneSelectedPlayers;
    @FXML
    private BorderPane borderPaneCreate;
    @FXML
    private StackPane parentContainerSelectPlayer;
    @FXML
    private MFXButton select2PlayersButton,slideClose,btnSettings, select3PlayersButton, select4PlayersButton, backHome;
    @FXML
    private MFXSlider volumeSlider;
    @FXML
    private Pane animateSettings;

    @Setter
    private HomePageController homePageController;
    private AnimationSubController animationSubController;
    private CreatePlayerController createPlayerController;
    private Scene createPlayerScene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.animationSubController = new AnimationSubController(null, null, null, this, null);

        FXMLLoader createPlayerLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/CreatePlayer.fxml"));
        Parent createPlayerParent = null;
        slideSettings();
        btnSongMute.setOnMouseClicked(mouseEvent -> {
            homePageController.managementSong();
        });

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

        backHome.setOnMouseClicked(mouseEvent -> {

        });

        try {
            createPlayerParent = createPlayerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        createPlayerController = createPlayerLoader.getController();

        assert createPlayerParent != null;
        this.createPlayerScene = new Scene(createPlayerParent);
    }

    public void responsiveScreen(Stage stage) {
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();

        if(screenHeight >= 850 && screenWidth >= 1780) {
            stage.setWidth(1920);
            stage.setHeight(1040);
        } else if (screenHeight >= 600 && screenWidth >= 1250) {
            createPlayerController.borderPaneCreate.setScaleX(0.9);
            createPlayerController.borderPaneCreate.setScaleY(0.9);
        } else {
            createPlayerController.borderPaneCreate.setScaleX(0.75);
            createPlayerController.borderPaneCreate.setScaleY(0.75);
            stage.setMaximized(true);
            stage.setWidth(1128);
            stage.setHeight(752);
        }
        stage.setMaximized(true);
        stage.setScene(createPlayerScene);
        stage.show();
    }

    public void switchScene(ActionEvent event) {
        getAnimationSubController().switchSceneToCreatePlayer();
        Timeline timer = new Timeline();
        KeyFrame kf1 = new KeyFrame(Duration.seconds(1), (KeyValue) null);
        timer.getKeyFrames().add(kf1);
        timer.play();
        timer.setOnFinished(actionEvent -> {
            createPlayerController.getParentContainerCreatePlayer().getChildren().add(0,homePageController.getSubScene());
            createPlayerController.setHomePageController(this.homePageController);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            responsiveScreen(stage);
        });
        String css = Objects.requireNonNull(this.getClass().getClassLoader().getResource("css/CreatePlayer.css")).toExternalForm();
        createPlayerScene.getStylesheets().add(css);
        homePageController.getSongSubController().songClick();
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

    public void select4Players(ActionEvent event) {
        switchScene(event);
        createPlayerController.setMaxPlayers(4);
    }

    public void select3Players(ActionEvent event) {
        switchScene(event);
        createPlayerController.setMaxPlayers(3);
    }

    public void select2Players(ActionEvent event) {
        switchScene(event);
        createPlayerController.setMaxPlayers(2);
    }

    public void backHome(ActionEvent event) {
        getAnimationSubController().switchSceneSelectPlayerToHomePage();
        Timeline timer = new Timeline();
        KeyFrame kf1 = new KeyFrame(Duration.seconds(1), (KeyValue) null);
        timer.getKeyFrames().add(kf1);
        timer.play();
        timer.setOnFinished(actionEvent -> {
            homePageController.getLayoutTitleAndButton().setOpacity(0);
            homePageController.getParentContainer3DAnd2D().getChildren().add(0,homePageController.getSubScene());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(homePageController.getHomePageScene());
            stage.show();
        });
        homePageController.removeSlideSettings();
        homePageController.getSongSubController().songClick();
    }

}
