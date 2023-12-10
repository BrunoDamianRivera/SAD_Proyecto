package main.java.com.MusicPlayer.player;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerControlsPanel extends JPanel {
    private JButton playButton;
    private JButton pauseButton;
    private JButton stopButton;
    private AudioPlayer audioPlayer; // Referencia al AudioPlayer

    public PlayerControlsPanel(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;

        playButton = new JButton("Play");
        pauseButton = new JButton("Pause");
        stopButton = new JButton("Stop");

        // Agrega los botones al panel
        this.add(playButton);
        this.add(pauseButton);
        this.add(stopButton);

        // Configura los ActionListeners para los botones
        setupButtonListeners();
    }

    private void setupButtonListeners() {
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioPlayer.play();
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioPlayer.pause();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioPlayer.stop();
            }
        });
    }
}
