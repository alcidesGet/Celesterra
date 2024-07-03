package br.com.Inatel.Audio;

import br.com.Inatel.Excecoes.ObjectNotFoundException;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {

    private Clip clip;

    public void play(String filePath) throws ObjectNotFoundException {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new ObjectNotFoundException("Arquivo de áudio não encontrado: " + filePath);
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}
