package com.intech.spacemonopoly.controller.pawns;

import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.shape.MeshView;
import lombok.Getter;

import java.net.URL;

public enum Pawn {

    UFO("ufo-1", "Un objet volant non identifié, généralement désigné sous l'acronyme ovni, est un phénomène aérien que des témoins affirment avoir observé ou qui a été enregistré par des capteurs (caméra vidéo, appareil photographique, radar, etc.) sans avoir pu être identifié mais dont on ne connaît pas l'origine ou la nature exacte et qui, pour certains, restent inexpliqués même après enquête approfondie. "),
    SPACE_SHIP_3("spaceship-3", "Véhicule spatial de grande taille qui permet d'effectuer de longues distances dans l'espace et, ou d'organiser des missions spatiales de longue durée avec plusieurs personnes. Exemple : Le vaisseau spatial doit abriter quinze astronautes pendant huit ans."),
    SPACE_SHIP_2("spaceship-2", "Véhicule spatial de petite taille qui permet d'effectuer de courtes distances dans l'espace et, ou d'organiser des missions spatiales de courte durée avec deux personnes."),
    SPACE_SHIP_1("spaceship-1", "Véhicule spatial de grade vitesse qui permet d'effectuer de courtes distances a de grades vitesses, ou d'organiser des missions spatiales urgentes."),
    SPACE_SHIP_5("spaceship-5", "Véhicule spatial de grade vitesse qui permet d'effectuer de courtes distances a de grades vitesses, ou d'organiser des missions spatiales urgentes.")
    ;

    private final String path;
    @Getter
    private final String description;
    @Getter
    private final Group object;

    Pawn(String fileName, String description) {
        this.path = fileName;
        this.description = description;
        this.object = loadPawnModel();
    }

    public String getFullPath() {
        return "pictures/pawns/3dpawns/" + path + ".obj";
    }

    public URL getURL() {
        return this.getClass().getClassLoader().getResource(getFullPath());
    }

    public Group loadPawnModel() {
        return new Task<>() {
            @Override
            protected Group call() {
                Group modelRoot = new Group();
                modelRoot.setId(name());

                ObjModelImporter importer = new ObjModelImporter();
                importer.read(getURL());

                for (MeshView view : importer.getImport()) {
                    modelRoot.getChildren().add(view);
                }
                return modelRoot;
            }
        }.call();
    }
}
