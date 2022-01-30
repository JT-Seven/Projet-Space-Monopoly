package com.intech.spacemonopoly.controller.boardsubcontroller;

import com.intech.spacemonopoly.controller.BoardController;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.util.Objects;

public class Initialise3DElementsSubController {

    private final BoardController boardController;

    public Initialise3DElementsSubController(BoardController boardController) {
        this.boardController = boardController;
    }

    public Group createCageForJailTile() {
        Group cage = boardController.loadModel(getClass().getClassLoader().getResource("pictures/obj3D/cage.obj"));
        boardController.setCage(cage);
        boardController.getTheFinalPositionCage().getChildren().add(boardController.getCage());
        boardController.getTheFinalPositionCage().setTranslateX(40);
        boardController.getTheFinalPositionCage().setTranslateY(40);
        boardController.getCage().setOpacity(0);
        return boardController.getCage();
    }

    public Group[] createCardsChanceAndCommunity() {
        boardController.getBorderPaneCardCommunity().setTranslateZ(-80);
        boardController.getBorderPaneCardChance().setTranslateZ(-80);
        return this.boardController.getCardsComAndChance();
    }

    public Group createBoard3D() {
        PhongMaterial material = new PhongMaterial();
        Image image = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("pictures/imagesBoard/bg1.png")));
        material.setDiffuseMap(image);

        Group board3D = boardController.loadModel(getClass().getClassLoader().getResource("pictures/obj3D/board-Game-3D.obj"));
        Group ville1 = boardController.loadModel(getClass().getClassLoader().getResource("pictures/obj3D/ville1.obj"));
        Group ville2 = boardController.loadModel(getClass().getClassLoader().getResource("pictures/obj3D/ville2.obj"));
        Group ville3 = boardController.loadModel(getClass().getClassLoader().getResource("pictures/obj3D/ville3.obj"));
        Group ville4 = boardController.loadModel(getClass().getClassLoader().getResource("pictures/obj3D/ville4.obj"));

        ville1.setTranslateX(-185);
        ville1.setTranslateY(185);
        ville1.setTranslateZ(20);

        ville2.setTranslateX(185);
        ville2.setTranslateY(185);
        ville2.setTranslateZ(20);

        ville3.setTranslateX(185);
        ville3.setTranslateY(-185);
        ville3.setTranslateZ(20);

        ville4.setTranslateX(-185);
        ville4.setTranslateY(-185);
        ville4.setTranslateZ(20);

        boardController.getAnimationSubController().animationCity(ville1);
        boardController.getAnimationSubController().animationCity(ville2);
        boardController.getAnimationSubController().animationCity(ville3);
        boardController.getAnimationSubController().animationCity(ville4);

        Box box = new Box(0.1, 580, 46);
        box.setMaterial(material);
        box.setTranslateX(-289);

        Box box2 = new Box(0.1, 580, 46);
        Translate translate = new Translate();
        translate.setZ(1);
        box2.setMaterial(material);
        box2.getTransforms().add(translate);
        box2.setTranslateX(287);

        Box box3 = new Box(0.1, 580, 46);
        Rotate rotate = new Rotate();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setAngle(90);
        box3.getTransforms().addAll(rotate);
        box3.setMaterial(material);
        box3.setTranslateY(-289);

        Box box4 = new Box(0.1, 580, 46);
        Rotate rotatebox2 = new Rotate();
        rotatebox2.setAxis(Rotate.Z_AXIS);
        rotatebox2.setAngle(90);
        box4.getTransforms().addAll(rotatebox2);
        box4.setMaterial(material);
        box4.setTranslateY(285);

        boardController.getBodyBoard3D().setTranslateZ(-2);
        boardController.getBodyBoard3D().setDepthTest(DepthTest.ENABLE);
        
        PointLight pointLight = new PointLight(javafx.scene.paint.Color.WHITE);
        pointLight.setMaxRange(1200);
        pointLight.setTranslateZ(-600);

        PointLight pointLight2 = new PointLight(javafx.scene.paint.Color.WHITE);
        pointLight2.setMaxRange(1200);
        
        Group boxElement = new Group(box, box2, box3, box4);
        boxElement.setTranslateZ(25);
        boardController.getGroupBoardAndElement().getChildren().addAll(pointLight, pointLight2, boxElement, board3D, ville1, ville2, ville3, ville4);
        boardController.getGroupBoardAndElement().setTranslateY(385.5);
        boardController.getGroupBoardAndElement().setTranslateX(385.5);

        return boardController.getGroupBoardAndElement();
    }

    public Group groupPlanetSolarSystem() {
        boardController.getGroupPlanetSolarSystem().getChildren().addAll(earth(), jupiter(), mars(), mercure(), venus(), uranus(), neptune());

        return boardController.getGroupPlanetSolarSystem();
    }

    public Sphere neptune() {
        PhongMaterial material = new PhongMaterial();
        Image image = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("pictures/imagesBoard/textures-all-planet/2k_neptune.jpeg")));

        material.setDiffuseMap(image);
        Sphere neptune = new Sphere(575);
        neptune.setMaterial(material);
        neptune.setTranslateY(-1000);
        neptune.setTranslateZ(2000);
        neptune.setTranslateX(-9000);
        neptune.setRotationAxis(new Point3D(90,0,0));
        neptune.setRotate(90);
        boardController.getAnimationSubController().animationPlanet(neptune);

        return neptune;
    }

    public Sphere uranus() {
        PhongMaterial material = new PhongMaterial();
        Image image = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("pictures/imagesBoard/textures-all-planet/2k_uranus.jpeg")));

        material.setDiffuseMap(image);
        Sphere uranus = new Sphere(575);
        uranus.setMaterial(material);
        uranus.setTranslateY(10000);
        uranus.setTranslateZ(2000);
        uranus.setTranslateX(-3000);
        uranus.setRotationAxis(new Point3D(90,0,0));
        uranus.setRotate(90);
        boardController.getAnimationSubController().animationPlanet(uranus);

        return uranus;
    }

    public Sphere venus() {
        PhongMaterial material = new PhongMaterial();
        Image image = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("pictures/imagesBoard/textures-all-planet/2k_venus_atmosphere.jpeg")));

        material.setDiffuseMap(image);
        Sphere venus = new Sphere(400);
        venus.setMaterial(material);
        venus.setTranslateY(2000);
        venus.setTranslateZ(2000);
        venus.setTranslateX(-2500);
        venus.setRotationAxis(new Point3D(90,0,0));
        venus.setRotate(90);
        boardController.getAnimationSubController().animationPlanet(venus);

        return venus;
    }

    public Sphere mercure() {
        PhongMaterial material = new PhongMaterial();
        Image image = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("pictures/imagesBoard/textures-all-planet/2k_mercury.jpeg")));

        material.setDiffuseMap(image);
        Sphere mercure = new Sphere(300);
        mercure.setMaterial(material);
        mercure.setTranslateY(2000);
        mercure.setTranslateZ(2000);
        mercure.setTranslateX(250);
        mercure.setRotationAxis(new Point3D(90,0,0));
        mercure.setRotate(90);
        boardController.getAnimationSubController().animationPlanet(mercure);

        return mercure;
    }

    public Sphere mars() {
        PhongMaterial material = new PhongMaterial();
        Image image = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("pictures/imagesBoard/textures-all-planet/mars_DiffuseMap.jpg")));
        Image image2 = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("pictures/imagesBoard/textures-all-planet/mars_Glossiness.png")));
        Image image3 = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("pictures/imagesBoard/textures-all-planet/mars_Bump.png")));

        material.setDiffuseMap(image);
        material.setSelfIlluminationMap(image2);
        material.setSpecularMap(image3);
        Sphere mars = new Sphere(500);
        mars.setMaterial(material);
        mars.setTranslateY(2000);
        mars.setTranslateZ(2000);
        mars.setTranslateX(4000);
        mars.setRotationAxis(new Point3D(90,0,0));
        mars.setRotate(90);
        boardController.getAnimationSubController().animationPlanet(mars);

        return mars;
    }

    public Sphere jupiter() {
        PhongMaterial material = new PhongMaterial();
        Image image = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("pictures/imagesBoard/textures-all-planet/2k_jupiter.jpeg")));

        material.setDiffuseMap(image);
        Sphere jupiter = new Sphere(1200);
        jupiter.setMaterial(material);
        jupiter.setTranslateY(-10000);
        jupiter.setTranslateZ(2000);
        jupiter.setTranslateX(-2000);
        jupiter.setRotationAxis(new Point3D(90,0,0));
        jupiter.setRotate(90);
        boardController.getAnimationSubController().animationPlanet(jupiter);

        return jupiter;
    }

    public Group earth() {
        PhongMaterial material = new PhongMaterial();
        Image image = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("pictures/imagesBoard/textures-all-planet/earth-living.jpg")));
        Image image2 = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("pictures/imagesBoard/textures-all-planet/EarthNight_2500x1250.jpg")));
        Image image3 = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("pictures/imagesBoard/textures-all-planet/EarthMask_2500x1250.jpg")));
        Image image4 = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("pictures/imagesBoard/textures-all-planet/Earth_NormalNRM_6K.jpg")));

        material.setDiffuseMap(image);
        material.setSelfIlluminationMap(image2);
        material.setSpecularMap(image3);
        material.setBumpMap(image4);
        Sphere earth = new Sphere(700);
        earth.setMaterial(material);
        earth.setTranslateY(-5000);
        earth.setTranslateZ(2000);
        earth.setTranslateX(2000);
        earth.setRotationAxis(new Point3D(90,0,0));
        earth.setRotate(90);

        AmbientLight ambientLight = new AmbientLight();

        Group groupPointLight = new Group();
        groupPointLight.getChildren().addAll(ambientLight, earth);
        boardController.getAnimationSubController().animationPlanet(earth);

        return groupPointLight;
    }

    public Group vaisseauMove() {
        Group vaisseau1 = boardController.loadModel(getClass().getClassLoader().getResource("pictures/obj3D/vaisseau-passage1.obj"));
        TranslateTransition timer = new TranslateTransition();
        timer.setNode(vaisseau1);
        timer.setFromX(-1701);
        timer.setToX(-1700);
        timer.setDuration(Duration.seconds(15));
        timer.play();
        timer.setOnFinished(actionEvent -> {
            TranslateTransition translateTransition = new TranslateTransition();
            translateTransition.setNode(vaisseau1);
            translateTransition.setFromX(-2000);
            translateTransition.setFromY(300);
            translateTransition.setFromZ(300);
            translateTransition.setToZ(300);
            translateTransition.setToX(2500);
            translateTransition.setToY(-1400);
            translateTransition.setDuration(Duration.seconds(12));
            translateTransition.play();
            translateTransition.setOnFinished(event -> {
                vaisseau1.setVisible(false);
                TranslateTransition translateTransition2 = new TranslateTransition();
                translateTransition2.setNode(vaisseau1);
                translateTransition2.setFromX(2000);
                translateTransition2.setFromY(-300);
                translateTransition2.setFromZ(-300);
                translateTransition2.setToZ(-300);
                translateTransition2.setToX(-2500);
                translateTransition2.setToY(1400);
                translateTransition2.setDelay(Duration.minutes(3));
                translateTransition2.setDuration(Duration.seconds(1));
                translateTransition2.play();
                translateTransition2.setOnFinished(actionEvent1 -> {
                    vaisseau1.setVisible(true);
                    translateTransition.play();
                });
            });
        });

        boardController.getVaisseauMove().getChildren().add(vaisseau1);
//        boardController.getTheFinalBackground3D().getChildren().add(boardController.getVaisseauMove());

        return boardController.getVaisseauMove();
    }

    public Sphere environnementStar(SubScene scene) {
        int i = 0;
        int amount = 250;
        while (i < amount) {
            Sphere stars = new Sphere();
            int size = (int) (Math.random() * 2.2);
            int posX = (int) (Math.floor(Math.random() * scene.getWidth())) - 400;
            int posY = (int) (Math.floor(Math.random() * scene.getHeight())) - 300;
            int posZ = (int) (Math.floor(Math.random() * scene.getWidth())) - 900;
            double delay = (double) (Math.random() * 250);
            double duration = (double) (Math.random() * 250);

            stars.setRadius(0.1 + size);
            AmbientLight ambientLight = new AmbientLight();

            TranslateTransition translateTransition = new TranslateTransition();
            translateTransition.setNode(stars);
            translateTransition.setFromX(0);
            translateTransition.setFromY(0);
            translateTransition.setFromZ(0);
            translateTransition.setToZ(posZ);
            translateTransition.setToX(posX);
            translateTransition.setToY(posY);
            translateTransition.setDuration(Duration.millis(1));
            translateTransition.play();

            TranslateTransition translateTransition2 = new TranslateTransition();
            translateTransition2.setNode(stars);
            translateTransition2.setFromX(posX + 200);
            translateTransition2.setFromY(posY + 750);
            translateTransition2.setFromZ(posZ + 750);
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

            boardController.setAllStars(stars);
            Group groupPointLight = new Group();
            groupPointLight.getChildren().addAll(ambientLight, boardController.getAllStars());
            boardController.getTheFinalBackground3D().getChildren().add(groupPointLight);
            i++;
        }

        return boardController.getAllStars();
    }

    public Group[] createDices() {
        Group dice1 = boardController.loadModel(getClass().getClassLoader().getResource("pictures/obj3D/ThrowDice.obj"));
        Group dice2 = boardController.loadModel(getClass().getClassLoader().getResource("pictures/obj3D/ThrowDice.obj"));

        this.boardController.setDices(0, dice1);
        this.boardController.setDices(1, dice2);
        boardController.getGroupDice().getChildren().addAll(boardController.getDices());
        boardController.getGroupDice().setTranslateX(-160);
        boardController.getGroupDice().setTranslateY(-280);
        boardController.getGroupDice().setTranslateZ(-15);

        return boardController.getDices();
    }
}
