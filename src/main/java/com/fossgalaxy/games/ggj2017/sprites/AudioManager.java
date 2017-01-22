package com.fossgalaxy.games.ggj2017.sprites;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * This is the sound manager
 *
 * He is busy at the moment
 *
 * Partying and whatnot
 *
 * Please leave a message after the ...
 * @author piers
 *
 */
public class AudioManager {
    private String backgroundFileName = "Ritual_Commute_Game_OST (1).aiff";

    private Clip backgroundMusic;

    public AudioManager() {
        try{
            backgroundMusic = AudioSystem.getClip();
            InputStream is = AudioManager.class.getClassLoader().getResourceAsStream(backgroundFileName);
            AudioInputStream sample = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
            backgroundMusic.open(sample);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void startBackground(){
        try {
            backgroundMusic.loop(Integer.MAX_VALUE);
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        }
    }
}
