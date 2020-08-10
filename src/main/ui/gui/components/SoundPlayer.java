package ui.gui.components;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

// Represents a sound player
public class SoundPlayer {
    private static final String POPUP_SOUND = "./data/sound/popupSound.wav";

    // EFFECTS: plays retrieved sound file from POPUP_SOUND
    // Reference: https://stackoverflow.com/a/15526480 (retrieve and play sound file)
    public void playPopUpSound() {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(POPUP_SOUND).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            //keep going
        }
    }
}
