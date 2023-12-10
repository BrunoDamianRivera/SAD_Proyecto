package main.java.com.MusicPlayer.player;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class VolumeControl extends JPanel {
    private JSlider volumeSlider;
    private AudioPlayer audioPlayer; // Referencia al reproductor de audio

    public VolumeControl(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Volume"));

        volumeSlider = new JSlider(JSlider.VERTICAL, 0, 100, 50); // Min, Max, Valor inicial
        volumeSlider.setMajorTickSpacing(10);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);

        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!volumeSlider.getValueIsAdjusting()) {
                    float volume = volumeSlider.getValue() / 100f;
                    audioPlayer.setVolume(volume); // Ajusta el volumen del reproductor
                }
            }
        });

        add(volumeSlider);
    }
}

