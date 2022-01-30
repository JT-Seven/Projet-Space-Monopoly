package com.intech.spacemonopoly.controller;

import com.intech.spacemonopoly.controller.boardsubcontroller.AnimationSubController;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;
@Getter
public class TutorialController implements Initializable {
    @FXML
    private AnchorPane parentTutorial;
    @FXML
    private Pane paneRules1;
    @FXML
    private MFXButton backHome;
    @FXML
    private Pane paneRules2;
    @FXML
    private Pane paneRules3;
    @FXML
    private Pane paneRules4;
    @FXML
    private Pane paneRules5;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnNext2;
    @FXML
    private Button btnNext3;
    @FXML
    private Button btnNext4;
    @FXML
    private HBox contents_tutorial;
    @FXML
    private Button btnPrevious;
    @FXML
    private Button btnPrevious2;
    @FXML
    private Button btnPrevious3;
    @FXML
    private Button btnPrevious4;
    private Logger logger;
    private HomePageController homePageController;
    private AnimationSubController animationSubController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.animationSubController = new AnimationSubController(null, null, null, null, this);
        this.logger = LoggerFactory.getLogger(BoardController.class);
    }

    //---------------------------Rules----------------------------------------------------------------------------------
    public void onClickNext() {
        btnNext.setOnMouseClicked(mouseEvent -> {
            logger.info("Page 2");
            paneRules2.setVisible(true);
            paneRules1.setVisible(false);

        });
        btnNext2.setOnMouseClicked(mouseEvent -> {
            logger.info("Page 3");
            paneRules2.setVisible(false);
            paneRules3.setVisible(true);
        });

        btnNext3.setOnMouseClicked(mouseEvent -> {
            logger.info("Page 4");
            paneRules3.setVisible(false);
            paneRules4.setVisible(true);
        });
        btnNext4.setOnMouseClicked(mouseEvent -> {
            logger.info("Page 5");
            paneRules4.setVisible(false);
            paneRules5.setVisible(true);
        });
    }


    public void onClickPrevious(){
        btnPrevious.setOnMouseClicked(mouseEvent -> {
            paneRules1.setVisible(true);
            paneRules2.setVisible(false);
        });
        btnPrevious2.setOnMouseClicked(mouseEvent -> {
            paneRules3.setVisible(false);
            paneRules2.setVisible(true);
        });
        btnPrevious3.setOnMouseClicked(mouseEvent -> {
            paneRules4.setVisible(false);
            paneRules3.setVisible(true);
        });
        btnPrevious4.setOnMouseClicked(mouseEvent -> {
            paneRules5.setVisible(false);
            paneRules4.setVisible(true);
        });
    }

    public void backHome(ActionEvent event) {
        getAnimationSubController().switchSceneTutorialToHomePage();
        Timeline timer = new Timeline();
        KeyFrame kf1 = new KeyFrame(Duration.seconds(1), (KeyValue) null);
        timer.getKeyFrames().add(kf1);
        timer.play();
        timer.setOnFinished(actionEvent -> {
            homePageController.getParentContainer3DAnd2D().getChildren().add(0,homePageController.getSubScene());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(homePageController.getHomePageScene());
            stage.show();
        });

        homePageController.removeSlideSettings();
        homePageController.getSongSubController().songClick();
    }

    public void setHomePageController(HomePageController homePageController) {
        this.homePageController = homePageController;
    }
}
