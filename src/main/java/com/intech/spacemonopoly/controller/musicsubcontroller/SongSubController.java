package com.intech.spacemonopoly.controller.musicsubcontroller;

import com.intech.spacemonopoly.controller.BoardController;
import com.intech.spacemonopoly.controller.CreatePlayerController;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import lombok.Getter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@Getter
public class SongSubController {

    private final ArrayList<File> songs;
    private int songNumber;
    private MediaPlayer mediaPlayer;
    private MediaPlayer mp;
    private boolean muteEffectSonnore;

    public SongSubController() {
        this.muteEffectSonnore = false;
        songs = new ArrayList<>();
        File directory = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("music/backgroundmusichome/")).getPath());
        File[] files = directory.listFiles();

        if (files != null) {
            songs.addAll(Arrays.asList(files));
            songNumber = 0;
            playSong(songNumber);
        }
        System.out.println("Sons avant partie");

    }
    public SongSubController(CreatePlayerController createPlayerController) {
        songs = new ArrayList<>();
        File directory = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("music/backgroundmusicBoard/")).getPath());
        File[] files = directory.listFiles();

        if (files != null) {
            songs.addAll(Arrays.asList(files));
            songNumber = 0;
            playSong(songNumber);
        }
        System.out.println("Sons apres partie");
    }
    public void songClick() {
        File f = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("music/soundeffectonclick/2.wav")).getPath());
        Media m = new Media(f.toURI().toString());
         mp = new MediaPlayer(m);
        mp.setVolume(0.9999);
        mp.play();
    }

    public void songBadClick() {

        File f = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("music/soundeffectonclick/next.wav")).getPath());
        Media m = new Media(f.toURI().toString());
         mp = new MediaPlayer(m);
        mp.setVolume(0.9999);
        mp.play();
    }
    public void moneySong() {
        if (!muteEffectSonnore) {

            File f = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("music/soundeffectplay/caisse-enregistreuse.wav")).getPath());
            Media m = new Media(f.toURI().toString());
            mp = new MediaPlayer(m);
            mp.setVolume(0.2222);
            mp.play();
        }
    }
   /* public void backgroundPlayMusic(){
        File f = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("music/backgroundmusicBoard/BackgroundPlayMusic.mp3")).getPath());
        Media m = new Media(f.toURI().toString());
        MediaPlayer mp = new MediaPlayer(m);
        mp.setVolume(0.7777);
        mp.play();
    }
    */

    public void jailSong() {
if(muteEffectSonnore == false) {
    File f = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("music/soundeffectplay/jail.mp3")).getPath());
    Media m = new Media(f.toURI().toString());
    mp = new MediaPlayer(m);
    mp.setVolume(0.9999);
    mp.play();
}
    }

    public void throwDiceSong() {
        if(muteEffectSonnore == false) {
            File f = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("music/soundeffectplay/throwDice.mp3")).getPath());
            Media m = new Media(f.toURI().toString());
            mp = new MediaPlayer(m);
            mp.setVolume(0.2222);
            mp.setOnEndOfMedia(() -> mp.seek(Duration.ZERO));
            mp.play();
        }
    }

    public void notificationSong() {
        if (muteEffectSonnore == false) {

            File f = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("music/soundeffectplay/notification.mp3")).getPath());
            Media m = new Media(f.toURI().toString());
            mp = new MediaPlayer(m);
            mp.setVolume(0.2222);
            mp.play();
        }
    }
    private void playSong(int number) {
        Media m = new Media(songs.get(number).toURI().toString());
        mediaPlayer = new MediaPlayer(m);
        mediaPlayer.setOnEndOfMedia(
                () -> {
                    mediaPlayer.stop();
                    mediaPlayer.dispose();
                    songNumber = songNumber < songs.size() - 1 ? songNumber + 1 : 0;
                    playSong(songNumber);
                });
        System.out.println(songs.get(songNumber).getName());
        mediaPlayer.setVolume(0.1111);
        mediaPlayer.play();

    }
    public void stopSong(){
        mediaPlayer.stop();
    }
    public void stopEffect(){
        if(mp != null) {
            mp.stop();
        }
    }
    public MediaPlayer getMp() {
        return mp;
    }

    public void setMp(MediaPlayer mp) {
        this.mp = mp;
    }
    public boolean getMuteEffectSonnore() {
        return muteEffectSonnore;
    }

    public void setMuteEffectSonnore(boolean muteEffectSonnore) {
        this.muteEffectSonnore = muteEffectSonnore;
    }

}
