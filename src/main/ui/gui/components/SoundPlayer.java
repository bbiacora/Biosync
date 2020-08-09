package ui.gui.components;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


public class SoundPlayer {
    private static final String SOUND_FILE = "./data/sound/popupSound.wav";

    // EFFECTS: plays retrieved sound file
    // Reference: https://stackoverflow.com/a/15526480
    public void playPopUpSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(SOUND_FILE).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            //keep going
        }
    }
}
