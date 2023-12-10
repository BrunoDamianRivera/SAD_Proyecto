package main.java.com.MusicPlayer.player;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;

public class MusicPlayerFrame extends JFrame {
    private PlayerControlsPanel playerControlsPanel;
    private PlaylistManager playlistManager;
    private VolumeControl volumeControl;
    private AudioPlayer audioPlayer; 

    public MusicPlayerFrame() {
        setTitle("Music Player");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initializeComponents();
    }

    private void initializeComponents() {
        // Inicializa el AudioPlayer
        audioPlayer = new AudioPlayer();

        // Inicializa los componentes
        playlistManager = new PlaylistManager(audioPlayer); // Actualizado para no usar FileChooserPanel
        playerControlsPanel = new PlayerControlsPanel(audioPlayer);
        volumeControl = new VolumeControl(audioPlayer);

        // Añade los componentes al frame
        this.setLayout(new BorderLayout());
        this.add(playerControlsPanel, BorderLayout.NORTH);
        this.add(playlistManager, BorderLayout.CENTER);
        this.add(volumeControl, BorderLayout.EAST);
    }
}
