package br.com.Inatel.Audio;

import br.com.Inatel.Excecoes.ObjectNotFoundException;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioEfeito {

    public void play(String filePath) throws ObjectNotFoundException {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start(); // Toca  uma vez
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new ObjectNotFoundException("Arquivo de áudio não encontrado: " + filePath);
        }
    }
}
