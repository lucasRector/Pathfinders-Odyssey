package src.main;
import javax.sound.sampled.*;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(){
        
        soundURL[0] = getClass().getClassLoader().getResource("res/sound/Pathfinder’s Odyssey.wav");
        soundURL[1] = getClass().getClassLoader().getResource("res/sound/coin.wav");
        soundURL[2] = getClass().getClassLoader().getResource("res/sound/powerup.wav");
        soundURL[3] = getClass().getClassLoader().getResource("res/sound/unlock.wav");
        soundURL[4] = getClass().getClassLoader().getResource("res/sound/fanfare.wav");
    }

    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(1000);
    }

    public void stop(){
        clip.stop();
    }
    
}
