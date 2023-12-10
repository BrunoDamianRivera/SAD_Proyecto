package main.java.com.MusicPlayer.player;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {
    private Clip clip;
    private AudioInputStream audioInputStream;
    private long clipPosition;
    private boolean isPaused;

    public AudioPlayer() {
        this.isPaused = false;
    }

    public void loadMusic(String filePath) {
        try {
            File audioFile = new File(filePath);
            if (!audioFile.exists()) {
                System.err.println("El archivo no existe: " + filePath);
                return;
            }

            if (clip != null && clip.isOpen()) {
                clip.close();
                System.out.println("Clip cerrado.");
            }
            audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            System.out.println("Archivo cargado: " + filePath);
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Formato de archivo no soportado: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + filePath);
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.err.println("Línea de audio no disponible.");
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            if (isPaused) {
                clip.setMicrosecondPosition(clipPosition);
                clip.start();
                isPaused = false;
                System.out.println("Reanudando la reproducción.");
            } else {
                clip.setFramePosition(0);
                clip.start();
                System.out.println("Iniciando la reproducción.");
            }
        } else {
            System.err.println("Clip no inicializado.");
        }
    }

    public void pause() {
        if (clip != null && clip.isRunning()) {
            clipPosition = clip.getMicrosecondPosition();
            clip.stop();
            isPaused = true;
            System.out.println("Reproducción pausada.");
        } else {
            System.err.println("No hay reproducción activa para pausar.");
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.setFramePosition(0);
            isPaused = false;
            System.out.println("Reproducción detenida.");
        } else {
            System.err.println("Clip no inicializado para detener.");
        }
    }

    public void close() {
        if (clip != null) {
            clip.close();
            System.out.println("Clip cerrado.");
        }
        if (audioInputStream != null) {
            try {
                audioInputStream.close();
                System.out.println("AudioInputStream cerrado.");
            } catch (IOException e) {
                System.err.println("Error al cerrar AudioInputStream.");
                e.printStackTrace();
            }
        }
    }

    public void setVolume(float volume) {
        if (clip != null && clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
            System.out.println("Volumen ajustado a: " + volume);
        } else {
            System.err.println("Control de volumen no soportado o clip no inicializado.");
        }
    }
}
