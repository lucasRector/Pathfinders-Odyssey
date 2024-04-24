package edu.appstate.cs.umbreon.main;

import javax.sound.sampled.*;

import java.io.File;
//import java.net.URL;

/**
 * Manages sound effects in the game.
 */
public class Sound {
    Clip clip; // The sound clip
    File soundURL[] = new File[30]; // Array to hold URLs of sound files
    long clipTimePosition = 0; // Variable to store current position of the clip

    /**
     * Constructor for the Sound class.
     * Initializes URLs for sound files.
     */
    public Sound() {
        soundURL[0] = new File(Main.BUILDDIR + "sound/Pathfinder's Odyssey.wav");
        soundURL[1] = new File(Main.BUILDDIR + "sound/coin.wav");
        soundURL[2] = new File(Main.BUILDDIR + "sound/powerup.wav");
        soundURL[3] = new File(Main.BUILDDIR + "sound/unlock.wav");
        soundURL[4] = new File(Main.BUILDDIR + "sound/fanfare.wav");
    }

    /**
     * Sets the sound file to be played.
     * 
     * @param i Index of the sound file in the URL array.
     */
    public void setFile(int i) {
        try {
            // Get audio input stream from the specified URL
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            // Get a clip instance
            clip = AudioSystem.getClip();
            // Open the clip with the audio input stream
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts playing the sound clip.
     */
    public void play() {
        if (clip != null) {
            clip.setMicrosecondPosition(clipTimePosition); // Set position before starting playback
            clip.start();
        }
    }

    /**
     * Loops the sound clip continuously.
     */
    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    /**
     * Stops the sound clip.
     */
    public void stop() {
        if (clip != null) {
            clip.stop();
            clipTimePosition = 0; // Reset position when stopped
        }
    }

    /**
     * Pauses the sound clip.
     */
    public void pause() {
        if (clip != null && clip.isRunning()) {
            clipTimePosition = clip.getMicrosecondPosition(); // Save current position
            clip.stop();
        }
    }

    /**
     * Resumes playing the sound clip.
     */
    public void resume() {
        if (clip != null && !clip.isRunning()) {
            clip.setMicrosecondPosition(clipTimePosition); // Set position before resuming playback
            clip.start();
        }
    }
}
