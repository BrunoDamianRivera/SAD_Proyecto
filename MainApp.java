package main.java.com.MusicPlayer;

import main.java.com.MusicPlayer.player.MusicPlayerFrame;

public class MainApp {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MusicPlayerFrame frame = new MusicPlayerFrame();
                frame.setVisible(true);
            }
        });
    }
}
