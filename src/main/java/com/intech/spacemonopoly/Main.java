package com.intech.spacemonopoly;

import com.intech.spacemonopoly.controller.BoardController;
import com.intech.spacemonopoly.controller.CreatePlayerController;
import com.intech.spacemonopoly.controller.HomePageController;
import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.BasicConfigurator;

import java.util.Objects;

public class Main extends Application {
    @Getter
    private HomePageController homePageController;

    @Override
    public void start(Stage stage) throws Exception {

        BasicConfigurator.configure();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/HomePage.fxml"));
        Parent root = loader.load();
        homePageController = loader.getController();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        homePageController.environnement3D(scene);

        // Logo
        Image icon = new Image("pictures/logoiconejeu/Ourlogo.jpg");
        stage.getIcons().add(icon);
        // Title
        stage.setTitle("SpaceMonopoly");
        // CSS
        String css = Objects.requireNonNull(this.getClass().getClassLoader().getResource("css/HomePage.css")).toExternalForm();
        scene.getStylesheets().add(css);

        // Logout
        stage.setOnCloseRequest(event -> {
            event.consume();
            logout(stage);
        });

        homePageController.setHomePageScene(scene);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    private void logout(Stage stage) {

        //On d??finit une alerte de type Confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        //Apr??s ca on peut maintenant d??finir un corps, un titre et  un en-t??te
        alert.setTitle("Deconnexion");
        alert.setHeaderText("??tes-vous sur de vouloir vous d??connecter ?");

        //Si la reponse de l'utilisateur est ??gal au boutton "OK" on le deconnect
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }


}
